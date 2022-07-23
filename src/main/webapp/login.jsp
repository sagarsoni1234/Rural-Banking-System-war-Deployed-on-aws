<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome to login page</title>
</head>
<body>
<form action="loginservlet" method="post">
<div align=center>
<h1>USER LOGIN</h1>
<table>
<tr><td>Enter Username:</td> <td><input type=text name=username></td></tr>
<tr><td>Enter Password:</td> <td><input type=text name=passcode></td></tr>
<tr><td><input type=submit value=login></td> <td><input type=reset ></td></tr>
</table>
</div>
</form>
</body>
</html>