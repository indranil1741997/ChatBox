package com.chatbox.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Messages {
	
	private String chatUsername;
	private String sessionUsername;
	private List<Message> message = new ArrayList<>();
	
	
	public Messages(String chatUsername, String sessionUsername) {
		super();
		this.chatUsername = chatUsername;
		this.sessionUsername = sessionUsername;
	}
	
	public String getChatUsername() {
		return chatUsername;
	}

	public void setChatUsername(String chatUsername) {
		this.chatUsername = chatUsername;
	}

	public String getSessionUsername() {
		return sessionUsername;
	}

	public void setSessionUsername(String sessionUsername) {
		this.sessionUsername = sessionUsername;
	}

	public List<Message> getMessage() {
		return message;
	}

	public void addMessage(String message) {
		this.message.add(new Message(message));
	}
	
	public void deleteMessage(Message message) {
		this.message.remove(message);
	}

	private class Message{
		
		private String message;
		private String timestamp;
		
		public Message(String message) {
			super();
			this.message = message;
			this.timestamp = new Timestamp(System.currentTimeMillis()).toString();
		}
		
		public String getMessage() {
			return message;
		}
	}
	
}
