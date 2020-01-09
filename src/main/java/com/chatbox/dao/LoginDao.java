//package com.chatbox.dao;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.Statement;
//
//import javax.servlet.http.HttpSession;
//
//public class LoginDao extends DatabaseCommunication{
//	public void validateLogin()
//	{
//		Connection connection = getConnection();
//		Statement statement = connection.createStatement();
//		ResultSet resultSet = statement.executeQuery("select * from user_details where email='" + email + "'");
//		if (resultSet.next() && resultSet.getString(4).equals(password)) {
//			HttpSession session = request.getSession();
//			session.setAttribute("user", email);
//			session.setAttribute("name", resultSet.getString(2));
//			response.sendRedirect(request.getContextPath() + "/welcome.jsp");
//		} else {
//			out.print("Sorry, username or password error!");
//			request.getRequestDispatcher("index.jsp").include(request, response);
//			// response.sendRedirect(request.getContextPath() + "/index.jsp");
//		}
//	}
//}
