<%--
  Created by IntelliJ IDEA.
  User: mmil
  Date: 16.12.17
  Time: 19:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form action="AddUserStepsServlet" method="post">   <!-- @WebServlet("/AddUserStepsServlet") -->
    <input type="hidden" name="step" value="1"/>
    <label>User's id</label><input name="id" type="text"><br/><br/>
    <label>User's login</label><input name="login" type="text"><br/><br/>
    <input type="submit" name="add-user" value="next">
</form>
