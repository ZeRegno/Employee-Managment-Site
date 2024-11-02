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

@WebServlet("/personal_inf")
public class PersonaEmployeeInfServlet extends HttpServlet {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/employees_DB_2";
    private static final String USER = "user1";
    private static final String PASS = "1111";

    @Override
    protected void doGet(HttpServletRequest req, @NotNull HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String fullName = req.getParameter("name");
        try (PrintWriter out = response.getWriter()) {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql = "SELECT last_name, first_name, middle_name, address, work_mode, age FROM employees WHERE CONCAT(last_name, ' ', first_name, ' ', middle_name) = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, fullName);
            ResultSet rs = pstmt.executeQuery();

            // Формируем HTML-страницу с подробной информацией
            if (rs.next()) {
                String lastName = rs.getString("last_name");
                String firstName = rs.getString("first_name");
                String middleName = rs.getString("middle_name");
                String address = rs.getString("address");
                String workMode = rs.getString("work_mode");
                String Age = rs.getString("age");

                out.println("<html><body>");
                out.println("<h1>Информация о сотруднике</h1>");
                out.println("<p><strong>Фамилия: </strong>" + lastName + "</p>");
                out.println("<p><strong>Имя: </strong>" + firstName + "</p>");
                out.println("<p><strong>Отчество: </strong>" + middleName + "</p>");
                out.println("<p><strong>Возраст: </strong>" + Age + "</p>");
                out.println("<p><strong>Адрес: </strong>" + address + "</p>");
                out.println("<p><strong>Режим работы: </strong>" + workMode + "</p>");
                out.println("</body></html>");
            }

            rs.close();
            pstmt.close();
            conn.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}
