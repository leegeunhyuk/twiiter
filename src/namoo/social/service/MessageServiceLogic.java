package namoo.social.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import namoo.social.dao.MessageDao;
import namoo.social.entity.Message;

@Service
public class MessageServiceLogic implements MessageService {
	@Autowired
	private MessageDao messageDao;
	
	@Override
	public int registMessage(Message message) {
		return messageDao.create(message);
	}

	@Override
	public List<Message> findMessage(String writerId) {
		return messageDao.retrieveMessage(writerId);
	}

	@Override
	public List<Message> findRelatedMessage(String id) {
		return messageDao.retrieveRelatedMessage(id);
	}

}
