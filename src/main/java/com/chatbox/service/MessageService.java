package com.chatbox.service;

import com.chatbox.model.Messages;

public interface MessageService {
	public String getMessage(String chatUsername, String sessionUsername);
	public void addMessage(Messages message);
}
