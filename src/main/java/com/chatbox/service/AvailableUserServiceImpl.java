package com.chatbox.service;

import java.util.List;

import com.chatbox.dao.AvailableUserDao;

public class AvailableUserServiceImpl implements AvailableUserService{

	private AvailableUserDao availableUser;
	
	public AvailableUserServiceImpl() {
		super();
		this.availableUser = new AvailableUserDao();
	}

	@Override
	public boolean getAvailableUsers(List<String> userList, String currentUser) {
		if(availableUser.populateUserList(userList, currentUser))
			return true;
		else
			return false;
	}

}
