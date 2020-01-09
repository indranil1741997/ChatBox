package com.chatbox.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chatbox.model.Messages;
import com.google.gson.JsonObject;
import com.tobeformatted.OperationJSON;

public class MessageDao extends DatabaseCommunication {
	
	public void addMessage(Messages message) {
		String sql ="select * from transaction where usr1= ? and usr2= ?"; 
		try {
			Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, message.getSessionUsername());
			preparedStatement.setString(2, message.getChatUsername());
			//preparedStatement.setString(2, message.getMessage());
			preparedStatement.execute();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
//	public String getMessage() throws ClassNotFoundException, SQLException {
//		Statement statement = getConnection().createStatement();
//		
//	}
	
	private void MessageToBeformated(HttpServletRequest request, HttpServletResponse response, String sessionUsername,
			String chatUsername) {
		try {
			
			Statement statement = getConnection().createStatement();
			ResultSet resultSetTranx = statement.executeQuery("select * from transaction where" + " usr1='"
					+ sessionUsername + "'and usr2='" + chatUsername + "'");
			String conv_id = null;
			if (resultSetTranx.next()) {
				// Get conv_id and fetch the JSON string
				request.setAttribute("conv_id", resultSetTranx.getString(3));
				conv_id = resultSetTranx.getString(3);
				// ResultSet resultSet = statement
				// .executeQuery("select * from conversation where conv_id='" +
				// resultSetTranx.getString(3) + "'");

				// OperationJSON.fetchMessage(resultSet);
			} else if (request.getParameter("newMessage") == null) {
				String convesation_id = createConversationId(sessionUsername, chatUsername);
				statement.executeUpdate("insert into transaction values" + "('" + sessionUsername + "','" + chatUsername
						+ "','" + convesation_id + "');");
				JsonObject json = null;
				statement.executeUpdate(
						"insert into conversation values" + "('" + convesation_id + "','" + json + "');");
			}

			if (request.getParameter("newMessage") != null)
				OperationJSON.addMessage(request.getAttribute("newMessage").toString(), conv_id);
			request.getRequestDispatcher("welcome.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private String createConversationId(String sessionUsername, String chatUsername) {
		return UUID.randomUUID().toString();
	}

}
