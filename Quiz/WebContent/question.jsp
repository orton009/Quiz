<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import = "java.util.*"
    %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="homepagestyle.css">
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Question Page</h1>
	<div class="container">
		<%
			request.getRequestDispatcher("Question").include(request,response) ;
			HashMap<Integer , String> ques = (HashMap<Integer , String>)request.getSession().getAttribute("questions");
			
		%>
		<div class="container">
		<form action="ScoreCal">
		<ol>
		<% for(Map.Entry<Integer, String> entry : ques.entrySet()) {%>
				<li><%=entry.getValue() %></li>
				<br>
				<%
					request.getRequestDispatcher("Options?quesId="+entry.getKey()).include(request,response) ;
					HashMap<Integer , String> options = (HashMap<Integer , String>)request.getSession().getAttribute("options");
					for(Map.Entry<Integer, String> item : options.entrySet()) {
				%>
						<br><input type="radio" name="<%=entry.getKey()%>" value="<%=item.getKey()%>"><%=item.getValue()%><br>
				<%} %>
		<%} %>
			<div>
				<input type="submit" >
			</div>
		</ol>
		</form>
		</div>
	</div>
</body>
</html>