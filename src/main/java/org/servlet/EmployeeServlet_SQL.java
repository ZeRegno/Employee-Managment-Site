package org.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.*;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import static java.sql.DriverManager.getConnection;

@WebServlet("/employees")
public class EmployeeServlet_SQL extends HttpServlet {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/employees_DB_2";
    private static final String USER = "user1";
    private static final String PASS = "1111";

    @Override
    protected void doGet(HttpServletRequest req, @NotNull HttpServletResponse response) throws ServletException, IOException {
        List<String> employeeNames = new ArrayList<>();
        try  {


            Class.forName("org.postgresql.Driver");
            // Подключаемся к базе данных
            try (Connection conn = getConnection(DB_URL, USER, PASS); Statement stmt = conn.createStatement()){

                // Выполняем SQL-запрос

                String sql = "SELECT id, last_name, first_name, middle_name FROM employees";
                ResultSet rs = stmt.executeQuery(sql);



                 // Обрабатываем результаты запроса и выводим их в HTML
                while (rs.next()) {
                    String lastName = rs.getString("last_name");
                    String firstName = rs.getString("first_name");
                    String middleName = rs.getString("middle_name");

                    String fullName = lastName + " " + firstName + " " + middleName;
                    String employeeLink = "<a href='personal_inf?name=" + URLEncoder.encode(fullName, "UTF-8") + "'>" + fullName + "</a>";
                    employeeNames.add(employeeLink);
                }

            }
        }

         catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred: " + e.getMessage());
        }
        req.setAttribute("employeeNames", employeeNames);
        req.getRequestDispatcher("/employeeList.jsp").forward(req, response);
    }
}