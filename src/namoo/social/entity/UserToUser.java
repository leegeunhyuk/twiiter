package namoo.social.entity;

public class UserToUser {
	//
	private User fromUser;
	private User toUser;
	
	public UserToUser() {}
	
	public UserToUser(User fromUser, User toUser) {
		//
		this.fromUser = fromUser;
		this.toUser = toUser;
	}
	
	public User getFromUser() {
		return fromUser;
	}
	public void setFromUser(User fromUser) {
		this.fromUser = fromUser;
	}
	public User getToUser() {
		return toUser;
	}
	public void setToUser(User toUser) {
		this.toUser = toUser;
	}
	
}
