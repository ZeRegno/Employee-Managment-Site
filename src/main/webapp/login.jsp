<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Авторизация</title>
</head>
<body>
<h2>Вход на сайт</h2>
<form action="login" method="post">
    <label for="username">Логин:</label>
    <input type="text" id="username" name="username" required><br><br>
    <label for="password">Пароль:</label>
    <input type="password" id="password" name="password" required><br><br>
    <button type="submit">Войти</button>
</form>

<p style="color: red;">
    <% String errorMessage = (String) request.getAttribute("errorMessage");
        if (errorMessage != null) { %>
    <%= errorMessage %>
    <% } %>
</p>
</body>
</html>
