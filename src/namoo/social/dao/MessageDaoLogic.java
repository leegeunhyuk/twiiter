package namoo.social.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import namoo.social.entity.Message;
import namoo.social.util.exception.NamooSocialException;

/**
 * 메시지 데이터 접근 로직
 */
@Repository
public class MessageDaoLogic implements MessageDao {
	public static final String SQL_SELECT_MESSAGE_WRITERID = "SELECT MSG_ID, CONTENTS, WRITER_ID, REG_DT  FROM TW_MESSAGE WHERE WRITER_ID = ? ORDER BY REG_DT DESC";
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}


	@Override
	public int create(Message message) {
		//
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append("INSERT INTO tw_message ");
		sqlBuilder.append("(msg_id, contents, writer_id, reg_dt) ");
		sqlBuilder.append("VALUES ");
		sqlBuilder.append("(tw_message_seq.nextVal, ?, ?, sysdate) ");
		
		int resultCnt = 0;
		try(Connection conn = ConnectionFactory.getInstance().createConnection();
			PreparedStatement pstmt = conn.prepareStatement(sqlBuilder.toString())) {
			pstmt.setString(1, message.getContents());
			pstmt.setString(2, message.getWriterId());
			
			resultCnt = pstmt.executeUpdate();
		}
		catch(SQLException e) {
			throw new NamooSocialException("메세지 등록 중 오류가 발생했습니다.");
		}
		
		return resultCnt;
	}

	
	@Override
	public List<Message> retrieveMessage(String writerId) {
		return jdbcTemplate.query(SQL_SELECT_MESSAGE_WRITERID, new Object[]{writerId}, MessageDaoLogic::mappingBaseballTeam);
	}

	@Override
	public List<Message> retrieveRelatedMessage(String id) {
		//
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append("SELECT a.writer_id, a.msg_id, a.contents, a.reg_dt ");
		sqlBuilder.append("  FROM TW_MESSAGE a ");
		sqlBuilder.append(" INNER JOIN TW_USERTOUSER b ");
		sqlBuilder.append("       ON a.writer_id = b.to_id ");
		sqlBuilder.append(" WHERE b.from_id = ? ");
		sqlBuilder.append(" ORDER BY a.msg_id DESC ");
		
		List<Message> messages = null;
		try(Connection conn = ConnectionFactory.getInstance().createConnection();
			PreparedStatement pstmt = conn.prepareStatement(sqlBuilder.toString());) {
			pstmt.setString(1, id);
			
			messages = new ArrayList<Message>();
			try(ResultSet rs = pstmt.executeQuery()) {
				while(rs.next()) {
					Message message = new Message();
					message.setId(rs.getString("msg_id"));
					message.setContents(rs.getString("contents"));
					message.setWriterId(rs.getString("writer_id"));
					message.setRegistDate(rs.getDate("reg_dt"));
					
					messages.add(message);
				}
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			throw new NamooSocialException("메세지 조회 중 오류가 발생했습니다. [글쓴이 아이디 : " + id + "]");
		}
		
		return messages;
	}

	
	public static Message mappingBaseballTeam(ResultSet rs, int rowNum) throws SQLException {
		Message message = new Message();
		message.setId(rs.getString("MSG_ID"));
		message.setContents(rs.getString("CONTENTS"));
		message.setWriterId(rs.getString("WRITER_ID"));
		message.setRegistDate(rs.getDate("REG_DT"));
		return message;
	}
	
}
