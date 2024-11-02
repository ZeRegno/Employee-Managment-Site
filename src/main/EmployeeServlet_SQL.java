package org.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.sql.*;
import java.util.List;import java.io.IOException;
import java.io.PrintWriter;

import static java.sql.DriverManager.getConnection;

@WebServlet("/employees")
public class EmployeeServlet_SQL extends HttpServlet {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/employees_DB";
    private static final String USER = "user1";
    private static final String PASS = "1111";

    @Override
    protected void doGet(HttpServletRequest req, @NotNull HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            // Устанавливаем тип контента
            response.setContentType("text/html;charset=UTF-8");
            Class.forName("org.postgresql.Driver");
            // Подключаемся к базе данных
            Connection conn = getConnection(DB_URL, USER, PASS);

            // Выполняем SQL-запрос
            Statement stmt = conn.createStatement();
            String sql = "SELECT id, name, position, salary FROM employees";
            ResultSet rs = stmt.executeQuery(sql);

            // Начинаем формировать HTML-ответ
            out.println("<html><head><title>Employee List</title></head><body>");
            out.println("<h1>Employee List</h1>");
            out.println("<table border='1'><tr><th>ID</th><th>Name</th><th>Position</th><th>Salary</th></tr>");

            // Обрабатываем результаты запроса и выводим их в HTML
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String position = rs.getString("position");
                double salary = rs.getDouble("salary");

                out.println("<tr><td>" + id + "</td><td>" + name + "</td><td>" + position + "</td><td>" + salary + "</td></tr>");
            }

            out.println("</table>");
            out.println("</body></html>");

            // Закрываем ресурсы
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred: " + e.getMessage());
        }
    }
}