package com.darwuich.compensapp.agendarcita.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/AgendarCitaServlet")
public class AgendarCitaServlet extends HttpServlet {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/proyecto_generales";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String documento = request.getParameter("documento");
        String fecha = request.getParameter("fecha");
        String hora = request.getParameter("hora");
        String ubicacion = request.getParameter("ubicacion");
        String especialista = request.getParameter("especialista");
        String especialidad = request.getParameter("especialidad");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // Validar si el número de documento existe en la base de datos
            String query = "SELECT id FROM usuarios WHERE numero_documento = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, documento);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int usuarioId = resultSet.getInt("id");

                // Insertar la cita
                String sql = "INSERT INTO citas (usuario_id, fecha, hora, ubicacion, especialista, especialidad) VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement insertStatement = connection.prepareStatement(sql);
                insertStatement.setInt(1, usuarioId);
                insertStatement.setString(2, fecha);
                insertStatement.setString(3, hora);
                insertStatement.setString(4, ubicacion);
                insertStatement.setString(5, especialista);
                insertStatement.setString(6, especialidad);

                int rowsInserted = insertStatement.executeUpdate();
            if (rowsInserted > 0) {
    response.sendRedirect("citaConfirmacion.jsp?documento=" + documento + "&ubicacion=" + ubicacion + "&especialista=" + especialista + "&especialidad=" + especialidad + "&hora=" + hora);
}
            } else {
                response.getWriter().println("Error: Número de documento no encontrado.");
            }

            connection.close();
        } catch (ClassNotFoundException e) {
            Logger.getLogger(AgendarCitaServlet.class.getName()).log(Level.SEVERE, null, e);
            response.getWriter().println("Error: Driver de base de datos no encontrado.");
        } catch (SQLException e) {
            Logger.getLogger(AgendarCitaServlet.class.getName()).log(Level.SEVERE, null, e);
            response.getWriter().println("Error: Problema con la base de datos. " + e.getMessage());
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
        return "Servlet para agendar citas médicas";
    }
}
