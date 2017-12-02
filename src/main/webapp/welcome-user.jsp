<%--
  Created by IntelliJ IDEA.
  User: mmil
  Date: 02.12.17
  Time: 20:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h2>
    Hello ${name}, your salary: ${salary}
    <c:set var="bonus" scope="request" value="${salary*1.2}"/>
    <c:out value=", bonus: ${bonus}" default="0.00" />
</h2>
<h3>
    Hello ${sessionScope.sessionName}, your salary: ${sessionScope.sessionSalary} (session scope)
</h3>
