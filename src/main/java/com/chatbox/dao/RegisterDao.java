package com.chatbox.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.chatbox.model.User;

public class RegisterDao extends DatabaseCommunication{
	
	public boolean registerUser(User user)
	{
		String sql = "insert into user_details values(?,?,?,?)";
		try
		{
			Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, user.getEmail());
			preparedStatement.setString(2, user.getName());
			preparedStatement.setString(2, user.getPhoneNum());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.executeUpdate(
					"insert into user_details values('" + user.getEmail() + "','" + user.getName() + "','" + user.getPhoneNum() + "','" + user.getPassword() + "');");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
