<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Products</title>
</head>
<body>
<form method="post" name="checkboxes">
    <c:forEach items="${requestScope.products}" var="elem">
        <!--<p>${elem.description}  ${elem.availableAmount}</p>-->
        <input type="radio" name="elem" value=${elem.description}> ${elem.description} ${elem.availableAmount}<br>
    </c:forEach>
    <input type="submit" name="order">
</form>

<a href="/new_product">New product</a>
</body>
</html>
