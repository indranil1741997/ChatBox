package com.tobeformatted;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class OperationJSON {
	private static Statement connectToDatabase() {
		Statement statement = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/chatbox", "root", "");
			statement = connection.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return statement;
	}

//	private static Object JSONToString(JsonObject o) {
		// To improvise
		Gson gson = new Gson();
		//Messages javaObject = gson.fromJson("jsonString", Messages.class);
		//Messages javaObject = gson.fromJson("jsonString", Messages.class);
		//return javaObject;
//	}

	public static boolean addMessage(String text, String conv_id) {

		Statement statement = connectToDatabase();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String time = timestamp.toString();

		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("msg", text);
		jsonObject.addProperty("timeStamp", time);

		JsonArray message = new JsonArray();
		try {
			ResultSet resultSet = statement
					.executeQuery("select message from conversation where conv_id='" + conv_id + "'");

			// Optimization required
			if (resultSet.next()) {
				int total_rows = resultSet.getMetaData().getColumnCount();
				for (int i = 0; i < total_rows; i++) {
					JsonObject obj = new JsonObject();
					obj.addProperty(resultSet.getMetaData().getColumnLabel(i + 1).toLowerCase(),
							(String) resultSet.getObject(i + 1));
					message.add(obj);
				}
				message.add(jsonObject);
			} else {
				message.add(jsonObject);
			}

			statement.executeUpdate(
					"update conversation set message = '" + message + "' where conv_id='" + conv_id + "'");

			return true; // use to return statement.executeUpdate
		} catch (Exception e) {
			return false;
		}
	}

	public static Object fetchMessage(ResultSet resultSet) {
		//JsonObject jsonObject = null;
		// Fetch the JSON Object
		// ResultSet resultSet = statement.executeQuery("select * from user_details
		// where email='" + email + "'");
		Object object = null;// = JSONToString(jsonObject);
		return object;
	}
}
