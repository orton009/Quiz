<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="homepagestyle.css">
</head>
<body>
	<h1>Welcome <%=request.getAttribute("username") %></h1>
	<div class="container">
	<h3> Are you ready ? <h3>
	
	<a href="question.jsp">play now</a>
	</div>
</body>
</html>