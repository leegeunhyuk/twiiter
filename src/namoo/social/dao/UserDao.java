package namoo.social.dao;

import java.util.List;

import namoo.social.entity.User;
import namoo.social.entity.UserToUser;

/**
 * 사용자 데이터 접근 인터페이스
 */
public interface UserDao {
	//
	/**
	 * 사용자 등록
	 * @param user 사용자 정보
	 * @return
	 */
	int createUser(User user);
	
	/**
	 * 아이디로 사용자 조회
	 * @param id 사용자 아이디
	 * @return
	 */
	User retrieveUser(String id);

	/**
	 * 팔로워 등록
	 * @param userToUser 사용자 연관관계 정보
	 * @return
	 */
	int createUserToUser(UserToUser userToUser);

	/**
	 * 내가 등록한 친구들 목록 조회 (팔로워 목록 조회)
	 * @param fromUserId 사용자 아이디
	 * @return
	 */
	List<User> retrieveUsersByFromUser(String fromUserId);
	
	/**
	 * 내가 등록한 친구들 목록 검색 (팔로워 검색)
	 * @param fromUserId 사용자 아이디
	 * @param searchText 검색어
	 * @return
	 */
	List<User> retrieveUsersByFromUser(String fromUserId, String searchText);
	
	/**
	 * 내가 등록한 친구들 수 조회 (팔로워 수 조회)
	 * @param fromUserId
	 * @return
	 */
	int countOfRelatedUsersByFromUser(String fromUserId);
	
	/**
	 * 나를 등록한 친구들 목록 조회 (팔로잉 목록 조회)
	 * @param toUserId
	 * @return
	 */
	List<User> retrieveUsersByToUser(String toUserId);
	
	/**
	 * 나를 등록한 친구들 목록 검색 (팔로잉 검색)
	 * @param toUserId
	 * @param searchText
	 * @return
	 */
	List<User> retrieveUsersByToUser(String toUserId, String searchText);
	
	/**
	 * 나를 등록한 친구들 수 조회 (팔로잉 수 조회)
	 * @param toUserId
	 * @return
	 */
	int countOfRelatedUsersByToUser(String toUserId);
	
	/**
	 * 언팔로우
	 * @param userToUser
	 * @return
	 */
	int deleteUserToUser(UserToUser userToUser);
	
}
