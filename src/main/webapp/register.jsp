<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrew
  Date: 15-Mar-18
  Time: 1:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
Registration
<form method="post">
    Login: <input type="text" name="login" />
    Password: <input type="text" name="password"/>
    Name: <input type="text" name="name"/>
    <p>${requestScope.errorMsg}</p>
    <input type="submit" value="register"/>
</form>
</body>
</html>
