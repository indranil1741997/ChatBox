<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@page import="com.chatbox.servlet.UserServlet"%> 
<%@page import="com.chatbox.servlet.MessageServlet"%> 
<%@page import="java.util.ArrayList"%>
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
				<tr>
					<th>Available PPL</th>
				</tr>
				<%
				@SuppressWarnings("unchecked")
				ArrayList<String> userList =  (ArrayList<String>)request.getAttribute("userList"); 
				%>
				<c:forEach items="${userList}" var="user">
				<tr>
					<td><c:out value="${user}"/></td>
					<td>
						<form action="Message?action=getMessage" method="post">
							<input type="radio" name="usrTo"
								value="${user}" /> <input
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
				<%
				@SuppressWarnings("unchecked")
				ArrayList<String> messageList =  (ArrayList<String>)request.getAttribute("message"); 
				%>
				<c:forEach items="${messageList}" var="msg">
				<tr>
					<td><c:out value="${msg}"/></td>
				</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	<div class="floatRight">
		<form action="Message?action=addMessage" method="post">
		<input type="text" name="message"/>
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