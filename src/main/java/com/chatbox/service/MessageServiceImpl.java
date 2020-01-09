package com.chatbox.service;

import com.chatbox.dao.MessageDao;
import com.chatbox.model.Messages;

public class MessageServiceImpl implements MessageService {
	
	private MessageDao messageDao;
	
	public MessageServiceImpl() {
		super();
		this.messageDao = new MessageDao();
	}

	@Override
	public String getMessage(String chatUsername, String sessionUsername) {
		return "";
	}

	@Override
	public void addMessage(Messages message) {
		String msg = getMessage(message.getChatUsername(),message.getSessionUsername());
		messageDao.addMessage(message);
	}

}
