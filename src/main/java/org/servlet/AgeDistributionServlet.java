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
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.sql.DriverManager.getConnection;


@WebServlet("/employess_AgeDistr")
public class AgeDistributionServlet extends HttpServlet {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/employees_DB_2";
    private static final String USER = "user1";
    private static final String PASS = "1111";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        List<Integer> ages = new ArrayList<>();

        try {
            // Загрузка драйвера
            Class.forName("org.postgresql.Driver");
         try (Connection connection =getConnection(DB_URL, USER, PASS);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT age FROM employees")) {
            while (resultSet.next()) {
                ages.add(resultSet.getInt("age"));
            }

         }
         catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("Database connection error", e);
         }
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new ServletException("PostgreSQL JDBC Driver not found", e);
        }


        req.setAttribute("ages", ages);
        req.getRequestDispatcher("/AgeDist.jsp").forward(req, response);
    }
}
