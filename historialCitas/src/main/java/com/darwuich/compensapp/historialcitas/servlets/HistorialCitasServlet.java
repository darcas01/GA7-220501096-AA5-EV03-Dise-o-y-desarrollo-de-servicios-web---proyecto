package com.darwuich.compensapp.historialcitas.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.darwuich.compensapp.historialcitas.models.Cita;

@WebServlet("/HistorialCitasServlet")
public class HistorialCitasServlet extends HttpServlet {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/proyecto_generales";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String documento = request.getParameter("documento");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            String query = "SELECT fecha, hora, ubicacion, especialista, especialidad FROM citas WHERE usuario_id = (SELECT id FROM usuarios WHERE numero_documento = ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, documento);
            ResultSet resultSet = statement.executeQuery();

            List<Cita> citas = new ArrayList<>();
            while (resultSet.next()) {
                Cita cita = new Cita();
                cita.setFecha(resultSet.getString("fecha"));
                cita.setHora(resultSet.getString("hora"));
                cita.setUbicacion(resultSet.getString("ubicacion"));
                cita.setEspecialista(resultSet.getString("especialista"));
                cita.setEspecialidad(resultSet.getString("especialidad"));
                citas.add(cita);
            }

            request.setAttribute("citas", citas);
            request.getRequestDispatcher("historialCitas.jsp").forward(request, response);
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            response.getWriter().println("Error: " + e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet para mostrar el historial de citas";
    }
}
