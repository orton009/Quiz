<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="homepagestyle.css">
<title>Insert title here</title>
</head>
<body>
<% 
	int score = (Integer)request.getSession().getAttribute("score") ; 
	double per = ((double)score/10)*100 ;
%>
	<h1>Your Score : </h1>
	<div class="container">
		<%=per %>% of your questions were right
		<p>
		<%if(score > 5) {%>
		<br>	Well Done ! 
		<%} else {%>
			Try Harder <a href="http://localhost:8080/Quiz/question.jsp">retake the test</a>
		<%} %>
		</p>
	</div>
</body>
</html>