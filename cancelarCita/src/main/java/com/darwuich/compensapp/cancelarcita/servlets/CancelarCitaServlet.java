package com.darwuich.compensapp.cancelarcita.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/CancelarCitaServlet")
public class CancelarCitaServlet extends HttpServlet {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/proyecto_generales";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String documento = request.getParameter("documento");
        String ubicacion = request.getParameter("ubicacion");
        String especialista = request.getParameter("especialista");
        String especialidad = request.getParameter("especialidad");
        String fecha = request.getParameter("fecha");
        String hora = request.getParameter("hora");
        String motivo = request.getParameter("motivo");

        if (documento == null || documento.isEmpty() || ubicacion == null || ubicacion.isEmpty() || 
            especialista == null || especialista.isEmpty() || especialidad == null || especialidad.isEmpty() || 
            fecha == null || fecha.isEmpty() || hora == null || hora.isEmpty() || motivo == null || motivo.isEmpty()) {
            response.getWriter().println("Error: Todos los campos son obligatorios.");
            return;
        }

        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // Cancelar la cita
            String sql = "DELETE FROM citas WHERE usuario_id = (SELECT id FROM usuarios WHERE numero_documento = ?) AND ubicacion = ? AND especialista = ? AND especialidad = ? AND fecha = ? AND hora = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, documento);
            statement.setString(2, ubicacion);
            statement.setString(3, especialista);
            statement.setString(4, especialidad);
            statement.setString(5, fecha);
            statement.setString(6, hora);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                response.sendRedirect("confirmacionCancelacionExitosa.jsp?documento=" + documento + "&ubicacion=" + ubicacion + "&especialista=" + especialista + "&especialidad=" + especialidad + "&fecha=" + fecha + "&hora=" + hora + "&motivo=" + motivo);
            } else {
                response.sendRedirect("cancelarCita.jsp?error=No se pudo cancelar la cita.");
            }
        } catch (ClassNotFoundException e) {
            Logger.getLogger(CancelarCitaServlet.class.getName()).log(Level.SEVERE, null, e);
            response.getWriter().println("Error: Driver de base de datos no encontrado.");
        } catch (SQLException e) {
            Logger.getLogger(CancelarCitaServlet.class.getName()).log(Level.SEVERE, null, e);
            response.getWriter().println("Error: Problema con la base de datos. " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    Logger.getLogger(CancelarCitaServlet.class.getName()).log(Level.SEVERE, null, e);
                }
            }
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
        return "Servlet para cancelar citas médicas";
    }
}
