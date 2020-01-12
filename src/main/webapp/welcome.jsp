<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
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
				<jsp:include page="/User" />
				<tr>
					<th>Available PPL</th>
				</tr>
				<c:forEach begin="0" end="${fn:length(userList) - 1}" var="index">
				<tr>
					<td><c:out value="${userList[index]}"/></td>
					<td>
						<form action="Message?action=getMessage" method="post">
							<input type="radio" name="usrTo"
								value="${userList[index]}" /> <input
								type="submit" value="SUBMIT" />
						</form>
					</td>
				</tr>
				</c:forEach>
			</table>
		</div>

		<div class="floatRight">
			<table>
			<tr><th>Messages</th></tr>
				<%try{
					String conv_id = request.getAttribute("conv_id").toString();
					Class.forName("com.mysql.cj.jdbc.Driver");
					java.sql.Connection connection = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/chatbox", "root", "");
					java.sql.Statement statement = connection.createStatement();
					java.sql.ResultSet resultSet = statement.executeQuery("select message from conversation where conv_id='" + conv_id + "'");
					while (resultSet.next()) { //to complete
				%>
				<tr>
					<td><%=resultSet.getString("message")%></td>
								
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
	<div class="floatRight">
		<form action="ConversationId" method="post">
		<input type="text" name="newMessage"/>
		<input type="submit" value="Send Message"/>
		</form>
	</div>
	<div class="floatCorner">
		<form action="Logout" method="post">
			<input type="submit" value="Logout">
		</form>
	</div>
</body>
</html>