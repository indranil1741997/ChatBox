package com.chatbox.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

import com.chatbox.model.Messages;

public class MessageDao extends DatabaseCommunication {

	public void addMessage(Messages message, String conv_id) {
		String sql = "update conversation set message='?' where conv_id='?'";
		try {
			Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setObject(1, message);
			preparedStatement.setString(2, conv_id);
			preparedStatement.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public Messages getMessage(String conv_id) {
		Messages msg = null;
		try {
			Statement statement = getConnection().createStatement();
			ResultSet resultSet = statement
					.executeQuery("select message from conversation where conv_id='" + conv_id + "'");
			while (resultSet.next())
				msg = (Messages) resultSet.getObject(0);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return msg;
	}

	public String generateConvId(String chatUsername, String sessionUsername) {
		try {
			Statement statement = getConnection().createStatement();
			ResultSet resultSetTranx = statement.executeQuery("select * from transaction where" + " usr1='"
					+ sessionUsername + "'and usr2='" + chatUsername + "'");

			if (resultSetTranx.next()) {
				return resultSetTranx.getString(3);
			} else
				return createConversationId(sessionUsername, chatUsername);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private String createConversationId(String sessionUsername, String chatUsername) {
		return UUID.randomUUID().toString();
	}

}
