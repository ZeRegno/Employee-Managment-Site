package org.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@WebServlet("/exportToExcel")
public class ExportToExcelServlet extends HttpServlet {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/employees_DB_2";
    private static final String USER = "user1";
    private static final String PASS = "1111";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=EmployeeList.xlsx");

        try (Workbook workbook = new XSSFWorkbook();
             OutputStream out = response.getOutputStream()) {

            Sheet sheet = workbook.createSheet("Employees");
            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("№");
            header.createCell(1).setCellValue("ФИО");
            header.createCell(2).setCellValue("Фамилия");
            header.createCell(3).setCellValue("Имя");
            header.createCell(4).setCellValue("Отчество");

            Class.forName("org.postgresql.Driver");
            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT last_name, first_name, middle_name FROM employees")) {

                int rowIdx = 1;
                int counter = 1;
                while (rs.next()) {
                    String lastName = rs.getString("last_name");
                    String firstName = rs.getString("first_name");
                    String middleName = rs.getString("middle_name");
                    String fullName = lastName + " " + firstName + " " + middleName;

                    Row row = sheet.createRow(rowIdx++);
                    row.createCell(0).setCellValue(counter++);
                    row.createCell(1).setCellValue(fullName);
                    row.createCell(2).setCellValue(lastName);
                    row.createCell(3).setCellValue(firstName);
                    row.createCell(4).setCellValue(middleName);

                }
            }

            workbook.write(out); // Записываем Excel-файл в поток вывода
        } catch (Exception e) {
            throw new ServletException("Error generating Excel file", e);
        }
    }
}