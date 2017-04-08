package namoo.social.entity;

import java.sql.Date;

public class Message {
	//
	private String id;
	private String contents;
	private String writerId;
	private Date registDate;
	
	public Message() {}
	
	public Message(String contents, String writerId, Date registDate) {
		//
		this.contents = contents;
		this.writerId = writerId;
		this.registDate = registDate;
	}

	public Message(String id, String contents, String writerId, Date registDate) {
		//
		this(contents, writerId, registDate);
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getWriterId() {
		return writerId;
	}
	public void setWriterId(String writerId) {
		this.writerId = writerId;
	}
	public Date getRegistDate() {
		return registDate;
	}
	public void setRegistDate(Date registDate) {
		this.registDate = registDate;
	}
}
