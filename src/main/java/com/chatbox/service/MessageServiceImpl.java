package com.chatbox.service;

import java.util.ArrayList;
import java.util.List;

import com.chatbox.dao.MessageDao;
import com.chatbox.model.Messages;

public class MessageServiceImpl extends Messages implements MessageService {
	
	private MessageDao messageDao;
	
	public MessageServiceImpl() {
		super();
		this.messageDao = new MessageDao();
	}

	@Override
	public Messages getMessage(String conv_id) {
		return messageDao.getMessage(conv_id);  
	}

	@Override
	public void addMessage(String msg, String conv_id) {
		Messages Messages = getMessage(conv_id);
		Messages.addMessage(msg);
		messageDao.addMessage(Messages, conv_id);
	}

	@Override
	public String getConversationId(String chatUsername,String sessionUsername) {
		return messageDao.generateConvId(chatUsername,sessionUsername);
	}

	@Override
	public List<String> populateMessageList(Messages message)
	{
		List<Message> msgList = new ArrayList<Message>();
		List<String> msgString = new ArrayList<String>();
		msgList = message.getMessage();
		for(int index=0; index < msgList.size(); index++)
		{
			msgString.add(msgList.get(index).getMessage());
		}
		
		return msgString;
	}
}
