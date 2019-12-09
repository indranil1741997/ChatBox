<%@page import="com.mysql.cj.Session"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome</title>
<style>
table {
	overflow-y: scroll;
	height: 100px;
	border: 1px solid black;
	border-collapse: collapse;
}

.floatLeft {
	width: 50%;
	float: left;
}

.floatRight {
	width: 50%;
	float: right;
}

.container {
	overflow: hidden;
}

.floatCorner {
	position: absolute;
	top: 0;
	right: 0;
}
</style>
</head>
<body>
	<h1>Hello User</h1>
	<%
		if (session != null) {
			if (session.getAttribute("user") != null) {
				String name = (String) session.getAttribute("user");
				out.print("Hello, " + name + "  Welcome to ur Profile");
			} else {
				response.sendRedirect("index.jsp");
			}
		}
	%>
	<div class="container">
		<div class="floatLeft">
			<table>
				<tr>
					<th>Available PPL</th>
				</tr>
				<%
					try {
						Class.forName("com.mysql.cj.jdbc.Driver");
						java.sql.Connection connection = java.sql.DriverManager
								.getConnection("jdbc:mysql://localhost:3306/chatbox", "root", "");
						java.sql.Statement statement = connection.createStatement();
						java.sql.ResultSet resultSet = statement.executeQuery(
								"select name from user_details where email!='" + (String) session.getAttribute("user") + "'");
						while (resultSet.next()) {
				%>
				<tr>
					<td><%=resultSet.getString("name")%></td>
					<td>
						<form action="ConversationId" method="post">
							<input type="radio" name="conv_id"
								value="<%=resultSet.getString("name")%>" /> <input
								type="submit" value="SUBMIT" />
						</form>
					</td>
				</tr>
				<%
					}
				%>
			</table>
		</div>

		<div class="floatRight">
			<table>
				<%
					String conv_id = request.getAttribute("conv_id").toString();
					resultSet = statement.executeQuery("select message from conversation where conv_id='" + conv_id + "'");
					while (resultSet.next()) { //to complete
				%>
				<tr>
					<td><%=resultSet.getString("name")%></td>
					<td>
						<form action="ConversationId" method="post">
							<input type="radio" name="conv_id"
								value="<%=resultSet.getString("name")%>" /> <input
								type="submit" value="SUBMIT" />
						</form>
					</td>
				</tr>
				<%
					}
						resultSet.close();
						statement.close();
						connection.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				%>
			</table>
		</div>
	</div>
	<div class="floatCorner">
		<form action="LogoutUser" method="post">
			<input type="submit" value="Logout">
		</form>
	</div>
</body>
</html>