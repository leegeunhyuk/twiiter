package namoo.social.service;

import java.util.List;

import namoo.social.entity.User;
import namoo.social.entity.UserToUser;

public interface UserService {
	
	public int registUser(User user);
	
	public User findUser(String id);
	
	public int registUserToUser(UserToUser userToUser);
	
	public List<User> findUsersByFromUser(String fromUserId);
	
	public List<User> findUsersByFromUser(String fromUserId, String searchText);
	
	public int countOfRelatedUsersByFromUser(String fromUserId);
	
	public List<User> findUsersByToUser(String toUserId);
	
	public List<User> findUsersByToUser(String toUserId, String searchText);
	
	public int countOfRelatedUsersByToUser(String toUserId);
	
	public int removeUserToUser(UserToUser userToUser);
}
