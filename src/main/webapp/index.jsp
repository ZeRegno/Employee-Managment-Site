<%
    String username = (String) session.getAttribute("username");
    if (username == null) {
        response.sendRedirect("login.jsp"); // Перенаправляем на страницу логина, если нет сессии
    }
%>
<!DOCTYPE html>
<html lang="ru">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <meta charset="UTF-8"> <!-- Правильное место для указания кодировки -->
    <title>Главная страница</title>
    <style>
        /* Стили для заголовка */
        h1 {
            text-align: center;
            margin-top: 20px;
        }

        /* Стили для кнопки, которая должна быть слева */
        .left-button {
            position: absolute;
            left: 10px;
            top: 100px;
        }
    </style>
</head>

<body>
<!-- Заголовок страницы -->
<h1>Главная страница</h1>

<!-- Кнопка для показа списка сотрудников -->
<form action="employees" method="GET">
    <button class="left-button" type="submit">Список сотрудников</button>
</form>
<form action="employess_AgeDistr" method="get">
    <button type="submit">Визуальная демонстрация распределения сотрудников по возрасту</button>
</form>
</body>

</html>