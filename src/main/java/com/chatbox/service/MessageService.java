package com.chatbox.service;

import java.util.List;

import com.chatbox.model.Messages;

public interface MessageService {
	public Messages getMessage(String conv_id);
	public void addMessage(String msg, String conv_id);
	public String getConversationId(String chatUsername,String sessionUsername);
	public List<String> populateMessageList(Messages message);
}
