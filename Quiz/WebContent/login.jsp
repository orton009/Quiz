<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="homepagestyle.css">
</head>
<body>
<form action="Login" method = "post">
  

  <div class="container">
    <label for="username"><b>Username</b></label>
    <input type="text" placeholder="Enter Username" name="userName" >

    <label for="password"><b>Password</b></label>
    <input type="password" placeholder="Enter Password" name="password">

    <button type="submit">Login</button>
    <a href="signUp.jsp">New User??</a>
   
   <p style="color:red"><% 
  		if(request.getAttribute("err")!=null){%>
  			<%=request.getAttribute("err") %>
  		<% }%>
  </p>
  </div>

  
</form>
</body>
</html>