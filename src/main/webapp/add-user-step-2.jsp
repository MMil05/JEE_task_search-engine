<%--
  Created by IntelliJ IDEA.
  User: mmil
  Date: 16.12.17
  Time: 19:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form action="AddUserStepsServlet" method="post">   <!-- @WebServlet("/AddUserStepsServlet") -->
    <input type="hidden" name="step" value="2"/>
    <label>User's name</label><input name="name" value="${sessionScope.edited_user.name}" type="text"><br/><br/>
    <label>User's surname</label><input name="surname" value="${sessionScope.edited_user.surname}"
                                        type="text"><br/><br/>
    <label>User's age</label><input name="age" value="${sessionScope.edited_user.age}" type="number"><br/><br/>
    <input type="submit" name="add-user" value="next">
</form>
