package com.chatbox.service;

import com.chatbox.model.SessionInfo;

public interface LoginService {
	public boolean validateLogin(String email, String password, SessionInfo userInfo);
}
