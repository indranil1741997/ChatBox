<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome</title>
<style>
table,th,td {
	overflow-y: scroll;
	height: 100px;
	border: 1px solid black;
	border-collapse: collapse;
}
</style>
</head>

<script type="text/javascript">
	function generateConvId() {
		arguments.
	}
</script>
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
	<table>
	<tr><th>Available PPL</th></tr>
<%
	try
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		java.sql.Connection connection = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/chatbox", "root", "");
		java.sql.Statement statement = connection.createStatement();
		java.sql.ResultSet resultSet = statement.executeQuery(
				"select name from user_details where email!='"+ (String)session.getAttribute("user") +"'");
		while(resultSet.next())
		{		
%>
			<tr>
			<td><%=resultSet.getString("name") %></td>
			<td>
				<input type="button" name="conv_id" value="<%=resultSet.getString("name") %>"
				onclick="generateConvId(<%=resultSet.getString("name") %>)"/> 
			</td>
			</tr>
	<%
		}
	%>
    </table>
<%
		resultSet.close();
    	statement.close();
    	connection.close();
    }
	catch(Exception e)
	{
    	e.printStackTrace();
    }
%>

	<form action="Logout" method="post">
		<input type="submit" value="Logout">
	</form>
</body>
</html>