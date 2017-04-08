package namoo.social.service;

import java.util.List;

import namoo.social.entity.Message;

public interface MessageService {
	
	public int registMessage(Message message);
	
	public List<Message> findMessage(String writerId);
	
	public List<Message> findRelatedMessage(String id);
}
