<%--
  Created by IntelliJ IDEA.
  User: mmil
  Date: 16.12.17
  Time: 15:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Search engine [users-list.jsp]</title>
</head>
<body>
    <c:forEach var="entry" items="${statList}">
        Użytkownik: <c:out value="${entry.key.toString()}, ${entry.value}"/> <br>
    </c:forEach>

</body>
</html>
