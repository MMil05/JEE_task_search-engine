<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
  <title>JEE search engine [index.jsp]</title>
</head>
<body>

<h1>Welcome in JEE search engine welcome page!</h1>

<%@ include file="logged-tab.jsp" %>

<c:if test="${loginErrorMessage != null}">
  <div style="color: red;">${loginErrorMessage}</div>
</c:if>
<c:if test="${okMessage != null}">
  <div style="color: green;">${okMessage}</div>
</c:if>

  <%--<%@ include file="welcome-user.jsp" %>--%>
  <%@ include file="menu.jsp" %>
</body>
</html>
