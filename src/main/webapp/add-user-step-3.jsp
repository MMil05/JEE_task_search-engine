<%--
  Created by IntelliJ IDEA.
  User: mmil
  Date: 16.12.17
  Time: 19:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form action="AddUserStepsServlet" method="post">   <!-- @WebServlet("/AddUserStepsServlet") -->
    <input type="hidden" name="step" value="3"/>
    <label>User's gender</label>
    <select name="gender">
        <c:if test="${empty sessionScope.edit_user_data}">
            <option value="">&lt;select&gt;</option>
        </c:if>
        <option
                <c:if test="${sessionScope.gender == 'MAN'}">selected</c:if> value="MAN">MAN
        </option>
        <option
                <c:if test="${sessionScope.gender == 'WOMAN'}">selected</c:if> value="WOMAN">WOMAN
        </option>
    </select><br><br>
    <input type="submit" name="add-user" value="done">
</form>
