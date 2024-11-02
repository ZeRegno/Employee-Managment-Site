package org.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import static java.sql.DriverManager.getConnection;

@WebServlet("/AddEmployee")
public class AddEmployee extends HttpServlet {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/employees_DB_2";
    private static final String USER = "user1";
    private static final String PASS = "1111";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String lastName = req.getParameter("lastName");
        String firstName = req.getParameter("firstName");
        String middleName = req.getParameter("middleName");
        String address = req.getParameter("address");
        String workMode = req.getParameter("workMode");
        int age = Integer.parseInt(req.getParameter("age"));

        try {
            Class.forName("org.postgresql.Driver");
            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                 PreparedStatement stmt = conn.prepareStatement(
                         "INSERT INTO employees (last_name, first_name, middle_name, address, work_mode, age) VALUES (?, ?, ?, ?, ?, ?)")) {

                stmt.setString(1, lastName);
                stmt.setString(2, firstName);
                stmt.setString(3, middleName);
                stmt.setString(4, address);
                stmt.setString(5, workMode);
                stmt.setInt(6, age);

                int rowsInserted = stmt.executeUpdate();

                if (rowsInserted > 0) {
                    resp.sendRedirect("employeeList.jsp?success=true"); // Перенаправление на страницу списка сотрудников при успешном добавлении
                } else {
                    resp.sendRedirect("addEmployee.jsp?error=true"); // Перенаправление на страницу добавления при ошибке
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new ServletException("PostgreSQL JDBC Driver not found", e);
        }
        catch (SQLException e) {  // Добавление блока catch для SQLException
            e.printStackTrace();
            throw new ServletException("Database access error", e);
        }
    }
}
