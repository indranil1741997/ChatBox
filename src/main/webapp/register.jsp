<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<title>Login</title>
</head>
<style type="text/css">
.demo {
	background-image: c:url("/img/background.jpg");
}

.error {
	color: red;
	margin-left: 50px;
	position: relative;
}

.div {
	font: 95% Arial, Helvetica, sans-serif;
	width: 600px;
	border-radius: 4px;
	height: auto;
	padding: 16px;
	margin: 10px auto;
	box-sizing: border-box;
	background: white;
	box-shadow: 10px black;
}

h1 {
	font: 95% Arial, Helvetica, sans-serif;
	margin: 10px auto;
	background: white;
	font-size: 240%;
	font-weight: 300;
	text-align: center;
	color: black;
}

h4 {
	margin-left: 40px;
}

.div input[type="text"], .div textarea, .div select {
	-webkit-transition: all 0.30s ease-in-out;
	-moz-transition: all 0.30s ease-in-out;
	-ms-transition: all 0.30s ease-in-out;
	-o-transition: all 0.30s ease-in-out;
	box-sizing: border-box;
	border: none;
	margin-left: 50px;
	border-bottom: 2px solid blue;
	width: 250px;
	margin-bottom: 20px;
	color: black;
	font: 95% Arial, Helvetica, sans-serif;
}

.div input[type="text"]:focus, .div textarea:focus, .div select:focus {
	padding: 3%;
	width: 54%;
	border: none;
	outline: none;
	border-bottom: 2px solid red;
}

.div input[type="submit"], .div input[type="button"] {
	-webkit-transition: all 0.40s ease-in-out;
	-moz-transition: all 0.40s ease-in-out;
	-ms-transition: all 0.40s ease-in-out;
	-o-transition: all 0.40s ease-in-out;
	box-sizing: border-box;
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	width: 150px;
	padding: 3%;
	background: blue;
	border-bottom: 2px solid #30C29E;
	border-top-style: none;
	border-right-style: none;
	border-left-style: none;
	color: #fff;
	margin-left: 200px;
	border-radius: 8px;
}

.div input[type="submit"]:hover, .div input[type="button"]:hover {
	background: BROWN;
}

success {
	margin-left: 200px;
	font-family: serif;
	color: blue;
}
</style>

<body>
	<div class="div">
		<h1>Fill Details</h1>
		<form action="Register" method="post">
			<h4>Email ID</h4>
			<input type="text" name="email" />
			<h4>Name</h4>
			<input type="text" name="name" />
			<h4>Phone</h4>
			<input type="text" name="phone" />
			<h4>Password</h4>
			<input type="text" name="psw"><br> 
			<input type="submit" value="SUBMIT" />
		</form>
	</div>
</body>
</html>
