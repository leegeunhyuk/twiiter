package namoo.social.dao;

import java.util.List;

import namoo.social.entity.Message;

/**
 * 메시지 데이터 접근 인터페이스
 */
public interface MessageDao {

	/**
	 * 메세지 등록
	 * @param message 메세지
	 * @return
	 */
	public int create(Message message);
	
	/**
	 * 사용자가 등록한 메세지 목록 조회
	 * @param writerId 사용자 아이디
	 * @return
	 */
	public List<Message> retrieveMessage(String writerId);
	
	/**
	 * 팔로워들이 등록한 메세지 목록 조회
	 * @param id 사용자 아이디
	 * @return
	 */
	public List<Message> retrieveRelatedMessage(String id);
}
