package com.chatbox.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.chatbox.model.SessionInfo;

public class LoginDao extends DatabaseCommunication {

	public boolean verifyLogin(String email, String password, SessionInfo sessionInfo) {
		try {
			Connection connection = getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from user_details where email='" + email + "'");
			if (resultSet.next() && resultSet.getString(4).equals(password)) {
				sessionInfo.setEmail(email);
				sessionInfo.setName(resultSet.getString(2));
				return true;
			} 
			else
				return false;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		} 
	}
}
