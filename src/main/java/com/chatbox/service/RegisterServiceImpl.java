package com.chatbox.service;

import com.chatbox.dao.RegisterDao;
import com.chatbox.model.User;

public class RegisterServiceImpl implements RegisterService{

	private RegisterDao registerDao;
	
	public RegisterServiceImpl() {
		super();
		this.registerDao = new RegisterDao();
	}

	@Override
	public boolean validateParameters(User user) {
		//validation of parameteres will be done here
		if(registerDao.registerUser(user))
			return true;
		else
			return false;
	}
	
}
