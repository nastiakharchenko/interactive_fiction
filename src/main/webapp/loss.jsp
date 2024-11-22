<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Loss</title>
    <link rel="stylesheet" href="css/style_content.css">
</head>
<body>
    <jsp:include page="/menu.jsp" />

    <div class="paddingTop">
        <div class="content">
            <h2>${sessionScope.text_loss}</h2>
            <div class="image-container">
                <img src="${sessionScope.image_loss}" alt="Loss">
            </div>
            <a href="<c:url value='${sessionScope.pathCurrentQuest}'/>">Спробувати ще раз</a>
        </div>
    </div>
</body>
</html>