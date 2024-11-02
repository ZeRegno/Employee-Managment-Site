<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <title>Добавить сотрудника</title>
</head>
<body>
<h2>Добавить сотрудника</h2>

<% if (request.getParameter("error") != null) { %>
<p style="color: red;">Ошибка при добавлении сотрудника. Попробуйте снова.</p>
<% } %>

<form action="AddEmployee" method="post">
  <label for="lastName">Фамилия:</label>
  <input type="text" id="lastName" name="lastName" required><br><br>

  <label for="firstName">Имя:</label>
  <input type="text" id="firstName" name="firstName" required><br><br>

  <label for="middleName">Отчество:</label>
  <input type="text" id="middleName" name="middleName"><br><br>

  <label for="address">Адрес:</label>
  <input type="text" id="address" name="address" required><br><br>

  <label for="workMode">Режим работы:</label>
  <select id="workMode" name="workMode">
    <option value="полный рабочий день">Полный рабочий день</option>
    <option value="удаленная работа">Удаленная работа</option>
    <option value="смешанный режим">Смешанный режим</option>
  </select><br><br>

  <label for="age">Возраст:</label>
  <input type="number" id="age" name="age" required><br><br>

  <button type="submit">Добавить сотрудника</button>
</form>

<p><a href="index.jsp">На главную</a></p>
</body>
</html>