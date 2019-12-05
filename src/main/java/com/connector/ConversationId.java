package com.connector;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ConversationId
 */
public class ConversationId extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ConversationId() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sessionUsername = null;
		String chatUsername = (String) request.getParameter("conv_id");
		HttpSession session = request.getSession(false);
		if (session != null)
			sessionUsername = (String) session.getAttribute("name");
		else
			request.getRequestDispatcher("index.jsp").include(request, response);

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/chatbox", "root", "");
			Statement statement = connection.createStatement();
			ResultSet resultSetTranx = statement.executeQuery("select * from transaction where" + " usr1='"
					+ sessionUsername + "'and usr2='" + chatUsername + "'");

			if (resultSetTranx.next()) {
				// Get conv_id and fetch the JSON string
				ResultSet resultSet = statement
						.executeQuery("select * from conversation where conv_id='" + resultSetTranx.getString(3) + "'");
				OperationJSON.fetchMessage(resultSet);
			} else {
				String convesation_id = createConversationId(sessionUsername, chatUsername);
				statement.executeUpdate("insert into transaction values" + "('" + sessionUsername + "','" + chatUsername
						+ "','" + convesation_id + "');");
				statement.executeUpdate("insert into conversation value" + "(" + convesation_id + "','" + null + "');");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private String createConversationId(String sessionUsername, String chatUsername) {

		String val = sessionUsername.substring(0, 1);
		// char (1), random A-Z
		int ranCharFirst = 65 + (new Random()).nextInt(90 - 65);
		char firstCharacter = (char) ranCharFirst;
		val += firstCharacter;

		// numbers (6), random 0-9
		Random r = new Random();
		int numbers = 10000 + (int) (r.nextFloat() * 89990);
		val += String.valueOf(numbers);

		val += "-";

		val += chatUsername.substring(0, 1);
		// char (1), random A-Z
		int ranCharSecond = 65 + (new Random()).nextInt(90 - 65);
		char secondCharacter = (char) ranCharSecond;
		val += secondCharacter;

		numbers = 10000 + (int) (r.nextFloat() * 89990);
		val += String.valueOf(numbers);

		return val;
	}

}
