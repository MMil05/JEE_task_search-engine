<%--
  Created by IntelliJ IDEA.
  User: mmil
  Date: 16.12.17
  Time: 19:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form action="AddUserStepsServlet" method="post">   <!-- @WebServlet("/AddUserStepsServlet") -->
    <input type="hidden" name="step" value="3"/>
    <label>User's gender</label>
    <select name="gender">
        <option value="">&lt;select&gt;</option>
        <option value="MAN">MAN</option>
        <option value="WOMAN">WOMAN</option>
    </select>
    <input type="submit" name="add-user" value="done">
</form>
