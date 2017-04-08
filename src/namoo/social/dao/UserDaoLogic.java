package namoo.social.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import namoo.social.entity.User;
import namoo.social.entity.UserToUser;

/**
 * 사용자 정보 데이터 접근 로직
 */
@Repository
public class UserDaoLogic implements UserDao {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public int createUser(User user) {
		//
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append("INSERT INTO TW_USER (USER_ID, USER_NM, EMAIL, PASSWORD) ");
		sqlBuilder.append(" VALUES (?, ?, ?, ?) ");
		
		int resultCount = 0;
		try(Connection connection = ConnectionFactory.getInstance().createConnection();
			PreparedStatement pstmt = connection.prepareStatement(sqlBuilder.toString())) {
			pstmt.setString(1, user.getId());
			pstmt.setString(2, user.getName());
			pstmt.setString(3, user.getEmail());
			pstmt.setString(4, user.getPassword());
			
			resultCount = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return resultCount;
	}
	
	@Override
	public User retrieveUser(String id) {
		//
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append("SELECT USER_ID, USER_NM, EMAIL, PASSWORD ");
		sqlBuilder.append("  FROM TW_USER ");
		sqlBuilder.append(" WHERE USER_ID = '" + id + "'");
		
		User user = null;
		try(Connection conn = ConnectionFactory.getInstance().createConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sqlBuilder.toString())) {
			
			while(rs.next()) {
				String userId = rs.getString("USER_ID");
				String userName = rs.getString("USER_NM");
				String userEmail = rs.getString("EMAIL");
				String userPassword = rs.getString("PASSWORD");
				
				user = new User(userId, userName, userEmail);
				user.setPassword(userPassword);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return user;
	}

	@Override
	public int createUserToUser(UserToUser userToUser) {
		//
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append("INSERT INTO TW_USERTOUSER (FROM_ID, TO_ID) ");
		sqlBuilder.append(" VALUES (?, ?) ");
		
		int resultCount = 0;
		try(Connection connection = ConnectionFactory.getInstance().createConnection();
			PreparedStatement pstmt = connection.prepareStatement(sqlBuilder.toString())) {
			pstmt.setString(1, userToUser.getFromUser().getId());
			pstmt.setString(2, userToUser.getToUser().getId());
			
			resultCount = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return resultCount;
	}

	@Override
	public List<User> retrieveUsersByFromUser(String fromUserId) {
		//
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append("SELECT U.USER_ID, U.USER_NM, U.EMAIL ");
		sqlBuilder.append("  FROM TW_USER U INNER JOIN TW_USERTOUSER UTU ");
		sqlBuilder.append("       ON U.USER_ID = UTU.TO_ID ");
		sqlBuilder.append(" WHERE UTU.FROM_ID = ? ");
		
		List<User> users = new ArrayList<User>();
		try(Connection connection = ConnectionFactory.getInstance().createConnection();
			PreparedStatement pstmt = connection.prepareStatement(sqlBuilder.toString())) {
			pstmt.setString(1, fromUserId);
			
			try(ResultSet rs = pstmt.executeQuery()) {
				while(rs.next()) {
					String userId = rs.getString("USER_ID");
					String userName = rs.getString("USER_NM");
					String email = rs.getString("EMAIL");
					
					User user = new User(userId, userName, email);
					users.add(user);
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return users;
	}

	@Override
	public List<User> retrieveUsersByFromUser(String fromUserId, String searchText) {
		//
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append("SELECT U.USER_ID, U.USER_NM, U.EMAIL ");
		sqlBuilder.append("  FROM TW_USER U INNER JOIN TW_USERTOUSER UTU ");
		sqlBuilder.append("       ON U.USER_ID = UTU.TO_ID ");
		sqlBuilder.append(" WHERE UTU.FROM_ID = ? ");
		sqlBuilder.append("   AND (U.USER_ID LIKE ? OR U.USER_NM LIKE ?) ");
		
		List<User> users = new ArrayList<User>();
		try(Connection connection = ConnectionFactory.getInstance().createConnection();
			PreparedStatement pstmt = connection.prepareStatement(sqlBuilder.toString())) {
			pstmt.setString(1, fromUserId);
			pstmt.setString(2, searchText + "%");
			pstmt.setString(3, searchText + "%");
			
			try(ResultSet rs = pstmt.executeQuery()) {
				while(rs.next()) {
					String userId = rs.getString("USER_ID");
					String userName = rs.getString("USER_NM");
					String email = rs.getString("EMAIL");
					
					User user = new User(userId, userName, email);
					users.add(user);
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return users;
	}
	
	@Override
	public int countOfRelatedUsersByFromUser(String fromUserId) {
		//
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append("SELECT COUNT(TO_ID) CNT");
		sqlBuilder.append("  FROM TW_USERTOUSER ");
		sqlBuilder.append(" WHERE FROM_ID = '" + fromUserId + "'");
		
		int userCount = 0;
		try(Connection connection = ConnectionFactory.getInstance().createConnection();
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sqlBuilder.toString())) {
			
			while(rs.next()) {
				userCount = rs.getInt("CNT");
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return userCount;
	}
	
	@Override
	public List<User> retrieveUsersByToUser(String toUserId) {
		//
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append("SELECT U.USER_ID, U.USER_NM, U.EMAIL ");
		sqlBuilder.append("  FROM TW_USER U INNER JOIN TW_USERTOUSER UTU ");
		sqlBuilder.append("       ON U.USER_ID = UTU.FROM_ID ");
		sqlBuilder.append(" WHERE UTU.TO_ID = ? ");
		
		List<User> users = new ArrayList<User>();
		try(Connection connection = ConnectionFactory.getInstance().createConnection();
			PreparedStatement pstmt = connection.prepareStatement(sqlBuilder.toString())) {
			pstmt.setString(1, toUserId);
			
			try(ResultSet rs = pstmt.executeQuery()) {
				while(rs.next()) {
					String userId = rs.getString("USER_ID");
					String userName = rs.getString("USER_NM");
					String email = rs.getString("EMAIL");
					
					User user = new User(userId, userName, email);
					users.add(user);
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return users;
	}

	@Override
	public List<User> retrieveUsersByToUser(String toUserId, String searchText) {
		//
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append("SELECT U.USER_ID, U.USER_NM, U.EMAIL ");
		sqlBuilder.append("  FROM TW_USER U INNER JOIN TW_USERTOUSER UTU ");
		sqlBuilder.append("       ON U.USER_ID = UTU.FROM_ID ");
		sqlBuilder.append(" WHERE UTU.TO_ID = ? ");
		sqlBuilder.append("   AND (U.USER_ID LIKE ? OR U.USER_NM LIKE ?) ");
		
		List<User> users = new ArrayList<User>();
		try(Connection connection = ConnectionFactory.getInstance().createConnection();
			PreparedStatement pstmt = connection.prepareStatement(sqlBuilder.toString())) {
			pstmt.setString(1, toUserId);
			pstmt.setString(2, searchText + "%");
			pstmt.setString(3, searchText + "%");
			
			try(ResultSet rs = pstmt.executeQuery()) {
				while(rs.next()) {
					String userId = rs.getString("USER_ID");
					String userName = rs.getString("USER_NM");
					String email = rs.getString("EMAIL");
					
					User user = new User(userId, userName, email);
					users.add(user);
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return users;
	}
	
	@Override
	public int countOfRelatedUsersByToUser(String toUserId) {
		//
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append("SELECT COUNT(FROM_ID) CNT");
		sqlBuilder.append("  FROM TW_USERTOUSER ");
		sqlBuilder.append(" WHERE TO_ID = '" + toUserId + "'");
		
		int userCount = 0;
		try(Connection connection = ConnectionFactory.getInstance().createConnection();
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sqlBuilder.toString())) {
			
			while(rs.next()) {
				userCount = rs.getInt("CNT");
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return userCount;
	}
	
	@Override
	public int deleteUserToUser(UserToUser userToUser) {
		//
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append("DELETE FROM TW_USERTOUSER ");
		sqlBuilder.append(" WHERE FROM_ID = ? ");
		sqlBuilder.append("   AND TO_ID = ? ");
		
		int resultCount = 0;
		try(Connection connection = ConnectionFactory.getInstance().createConnection();
			PreparedStatement pstmt = connection.prepareStatement(sqlBuilder.toString())) {
			pstmt.setString(1, userToUser.getFromUser().getId());
			pstmt.setString(2, userToUser.getToUser().getId());
			
			resultCount = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return resultCount;
	}
	
}
