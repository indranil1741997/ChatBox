package com.connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class OperationJSON {
	private static Statement connectToDatabase()
	{
		Statement statement = null;
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/chatbox", "root", "");
		statement = connection.createStatement();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return statement;
	}

	private static Object JSONToString(JsonObject o)
	{
		//To improvise
		Gson gson = new Gson();
		Object javaObject = gson.fromJson("jsonString", Object.class);
		return javaObject;
	}
	
	private static JsonObject StringToJSON(String str)
	{
		JsonParser jsonParser = new JsonParser();
		JsonObject jo = (JsonObject)jsonParser.parse(str);
		return jo;
	}
	
	public static boolean addMessage(String str)
	{
		Statement statement = connectToDatabase();
		JsonObject jsonObject = StringToJSON(str);
		//Add message to the user bucket
		//statement.executeUpdate(
			//	"insert into user_details values('" + email + "','" + name + "','" + phone + "','" + password + "');");
		return false; //use to return statement.executeUpdate
	}
	
	public static Object fetchMessage(ResultSet resultSet)
	{
		Statement statement = connectToDatabase();
		JsonObject jsonObject = null;
		//Fetch the JSON Object
		//ResultSet resultSet = statement.executeQuery("select * from user_details where email='" + email + "'");
		Object object = JSONToString(jsonObject);
		return object;
	}
}
