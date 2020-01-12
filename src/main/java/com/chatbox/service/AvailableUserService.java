package com.chatbox.service;

import java.util.List;

public interface AvailableUserService {
	
	public boolean getAvailableUsers(List<String> userList, String currentUser);

}
