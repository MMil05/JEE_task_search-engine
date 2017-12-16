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
        <%--<div <c:if test = "${entry.key.gender.toString() == 'MAN'}">style="color: blue;"  </c:if> >--%>
        <%--<c:set var="color" scope="request" value="${entry.key.gender.toString()}"/>--%>
        <c:choose>
            <c:when test="${entry.key.gender.toString() == 'MAN'}">
                <c:set var="rowColor" scope="request" value="color: blue;"/>
            </c:when>
            <c:otherwise>
                <c:set var="rowColor" scope="request" value="color: green;"/>
            </c:otherwise>
        </c:choose>
        <%--<div  <c:if test = "${man == 'MAN'}">style="color: blue;"  </c:if> >--%>
        <div style="${rowColor}">
            Użytkownik: <c:out value="${entry.key.toString()}, ${entry.value}"/> <br>
        </div>
    </c:forEach>
</body>
</html>
