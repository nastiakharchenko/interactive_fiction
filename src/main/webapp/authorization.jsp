<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="uk">
<head>
    <title>Authorization</title>
    <link rel="stylesheet" href="css/style_authorization.css">
</head>
<body>
    <div class="auth-container">
        <form action="authorization" method="post">
            <input type="text" name="username">
            <br><br>
            <input type="password" name="password">
            <br><br>
            <input type="submit" name="action" value="Ввійти">
            <br><br>
            <input type="submit" name="action" value="Реєстрація">
        </form>
        <br>
        <c:if test="${sessionScope.status == false}">
            <h3>${sessionScope.text_user_notification}</h3>
        </c:if>
    </div>
</body>
</html>
