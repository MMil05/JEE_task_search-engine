<%--
  Created by IntelliJ IDEA.
  User: mm
  Date: 09.01.18
  Time: 05:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>search engine [login.jsp]</title>
</head>
<body>
<%@ include file="go-back.jsp" %>
<h1>Zaloguj:</h1>
<form method="post" action="/login">    <!-- @WebServlet("/login") -->
    <label for="j_username">Username:</label>
    <input type="text" id="j_username" name="j_username"/><br/>
    <label for="j_password">Password:</label>
    <input type="password" id="j_password" name="j_password"/><br/>
    <input type="submit" value="login" name="login"/>
</form>
</body>
</html>
