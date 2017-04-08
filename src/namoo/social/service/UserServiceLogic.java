package namoo.social.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import namoo.social.dao.UserDao;
import namoo.social.entity.User;
import namoo.social.entity.UserToUser;

@Service
public class UserServiceLogic implements UserService {
	@Autowired
	private UserDao userDao;

	@Override
	public int registUser(User user) {
		return userDao.createUser(user);
	}

	@Override
	public User findUser(String id) {
		return userDao.retrieveUser(id);
	}

	@Override
	public int registUserToUser(UserToUser userToUser) {
		return userDao.createUserToUser(userToUser);
	}

	@Override
	public List<User> findUsersByFromUser(String fromUserId) {
		return userDao.retrieveUsersByFromUser(fromUserId);
	}

	@Override
	public List<User> findUsersByFromUser(String fromUserId, String searchText) {
		return userDao.retrieveUsersByFromUser(fromUserId, searchText);
	}

	@Override
	public int countOfRelatedUsersByFromUser(String fromUserId) {
		return userDao.countOfRelatedUsersByFromUser(fromUserId);
	}

	@Override
	public List<User> findUsersByToUser(String toUserId) {
		return userDao.retrieveUsersByToUser(toUserId);
	}

	@Override
	public List<User> findUsersByToUser(String toUserId, String searchText) {
		return userDao.retrieveUsersByToUser(toUserId, searchText);
	}

	@Override
	public int countOfRelatedUsersByToUser(String toUserId) {
		return userDao.countOfRelatedUsersByToUser(toUserId);
	}

	@Override
	public int removeUserToUser(UserToUser userToUser) {
		return userDao.deleteUserToUser(userToUser);
	}

	
}
