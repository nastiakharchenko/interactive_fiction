<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Angel and demon</title>
    <link rel="stylesheet" href="css/style_content.css">
    <link rel="stylesheet" href="css/style_angel_and_demon.css">
</head>
<body class="body_angel_and_demon">
    <div class="content">
        <div class="mystic">
            <div>
              <img src="${sessionScope.image_question}" alt="Quest image">
            </div>
            <p>${sessionScope.question}</p>
            <br>
            <c:if test="${sessionScope.congratulation == false}">
                <form action="angel_and_demon" method="get">
                    <label><input type="radio" name="answer" value="${sessionScope.answer_var_1}">${sessionScope.answer_var_1}</label>
                    <br>
                    <label><input type="radio" name="answer" value="${sessionScope.answer_var_2}">${sessionScope.answer_var_2}</label>
                    <br><br>
                    <input type="submit" name="action" value="Назад">
                    <input type="submit" name="action" value="Відповісти">
                    <input type="submit" name="action" value="Вийти">
                </form>
            </c:if>
            <c:if test="${sessionScope.congratulation == true}">
                <form action="angel_and_demon" method="get">
                    <input type="submit" name="action" value="Почати спочатку">
                    <input type="submit" name="action" value="Повернутися до списку квестів">
                </form>
            </c:if>
        </div>
    </div>
</body>
</html>
