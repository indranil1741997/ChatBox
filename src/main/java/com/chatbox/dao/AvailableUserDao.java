package com.chatbox.dao;

import java.sql.Connection;
import java.util.List;

public class AvailableUserDao extends DatabaseCommunication {

	public boolean populateUserList(List<String> userList, String currentUser)
	{
		try {
			Connection connection = getConnection();
			java.sql.Statement statement = connection.createStatement();
			java.sql.ResultSet resultSet = statement.executeQuery(
					"select name from user_details where email!='" + currentUser + "'");
			while (resultSet.next()) {
				userList.add(resultSet.getString("name"));
			}
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}	
	}
}
