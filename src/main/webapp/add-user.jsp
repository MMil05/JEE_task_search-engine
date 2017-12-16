<%--
  Created by IntelliJ IDEA.
  User: mmil
  Date: 02.12.17
  Time: 19:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form action="AddUserServlet" method="post">   <!-- @WebServlet("/AddUserServlet") -->
    <div>
        <label for="id">User's id</label>
        <input name="id" id="id">
    </div>
    <div>
        <label for="name">User's name</label>
        <input name="name" id="name">
    </div>
    <div>
        <label>User's surname</label>
        <input name="surname">
    </div>
    <div>
        <label for="age">User's age</label>
        <input name="age" id="age">
    </div>
    <div>
        <label>User's login</label>
        <input name="login">
    </div>
    <div>
        <label>User's gender</label>
        <select name="gender">
            <option value="">&lt;select&gt;</option>
            <option value="MAN">MAN</option>
            <option value="WOMAN">WOMAN</option>
        </select>
    </div>
    <div>
        <button type="submit">Send data (by POST)</button>
    </div>
</form>
