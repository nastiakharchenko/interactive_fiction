<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Квести</title>
    <link rel="stylesheet" href="css/style_content.css">
</head>
<body>
    <jsp:include page="/menu.jsp" />

    <div class="content">
        <form class="paddingTop" action="quests" method="get">
            <c:forEach var="quest" items="${sessionScope.questList}">
                <p class="content_text">${quest.description}</p>
                <input class="quest-block"
                       style='background-image: url("${quest.background}");
                               background-position: center center;
                               background-size: cover;'
                       type="submit"  name="current_quest" value="${quest.name}">
                <br>
            </c:forEach>
        </form>
    </div>
</body>
</html>
