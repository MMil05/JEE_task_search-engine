<%--
  Created by IntelliJ IDEA.
  User: mmil
  Date: 16.12.17
  Time: 19:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form action="AddUserStepsServlet" method="post">   <!-- @WebServlet("/AddUserStepsServlet") -->
    <input type="hidden" name="step" value="1"/>
    <label>User's id</label><input name="id" type="text"
                                   <c:if test="${not empty sessionScope.edit_user_data}">readonly</c:if>
                                   value="${sessionScope.id}"><br/><br/>
    <label>User's login</label><input name="login" value="${sessionScope.login}" type="text"><br/><br/>
    <input type="submit" name="add-user" value="next">
</form>
