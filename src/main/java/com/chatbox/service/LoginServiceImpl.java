package com.chatbox.service;

import com.chatbox.dao.LoginDao;
import com.chatbox.model.SessionInfo;

public class LoginServiceImpl implements LoginService{

	private LoginDao loginDao;
	
	public LoginServiceImpl() {
		super();
		this.loginDao = new LoginDao();
	}
	
	@Override
	public boolean validateLogin(String email, String password, SessionInfo sessionInfo) {
		return loginDao.verifyLogin(email, password, sessionInfo);
	}
	
}
