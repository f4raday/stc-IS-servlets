<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrew
  Date: 14-Mar-18
  Time: 23:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<form method="post">
    Login: <input type="text" name="login" />
    Password: <input type="text" name="password"/>
        <p>${requestScope.errorMsg}</p>
    <input type="submit" value="login"/>
</form>
<a href="/register">Register</a>
</body>
</html>
