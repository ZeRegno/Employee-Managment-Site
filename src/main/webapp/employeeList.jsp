<%@ page import="java.util.List" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Employee List</title>
</head>
<body>
<h1>Список сотрудников</h1>
<form action="exportToExcel" method="get">
    <button type="submit">Сохранить в Excel</button>
</form><form action="AddEmployee" method="post">
    <button type="submit">Добавить Работника</button>
</form>
<table border="1">
    <tr>
        <th>№</th>
        <th>ФИО</th>
    </tr>

    <%
        List<String> employeeNames = (List<String>) request.getAttribute("employeeNames");
        int counter = 1;

        for (String fullName : employeeNames) {
            String encodedName = URLEncoder.encode(fullName, "UTF-8");
    %>
    <tr>
        <td><%= counter++ %></td>
        <td><a href="personal_inf?name=<%= encodedName %>"><%= fullName %></a></td>
    </tr>
    <%
        }
    %>
</table>
</body>
</html>
