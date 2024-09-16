package com.darwuich.compensapp.modificarcita.servlets;

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

/**
 * Servlet implementation class ModificarCitaServlet
 */
@WebServlet("/ModificarCitaServlet")
public class ModificarCitaServlet extends HttpServlet {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/proyecto_generales";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String documento = request.getParameter("documento");
        String fecha = request.getParameter("fecha");
        String hora = request.getParameter("hora");
        String ubicacion = request.getParameter("ubicacion");
        String especialista = request.getParameter("especialista");
        String especialidad = request.getParameter("especialidad");

        if (documento == null || documento.isEmpty() || fecha == null || fecha.isEmpty() || 
            hora == null || hora.isEmpty() || ubicacion == null || ubicacion.isEmpty() || 
            especialista == null || especialista.isEmpty() || especialidad == null || especialidad.isEmpty()) {
            response.getWriter().println("Error: Todos los campos son obligatorios.");
            return;
        }

        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // Actualizar la cita
            String sql = "UPDATE citas SET fecha = ?, hora = ?, ubicacion = ?, especialista = ?, especialidad = ? WHERE usuario_id = (SELECT id FROM usuarios WHERE numero_documento = ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, fecha);
            statement.setString(2, hora);
            statement.setString(3, ubicacion);
            statement.setString(4, especialista);
            statement.setString(5, especialidad);
            statement.setString(6, documento);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                response.sendRedirect("confirmacionModificacion.jsp?documento=" + documento + "&ubicacion=" + ubicacion + "&especialista=" + especialista + "&especialidad=" + especialidad + "&hora=" + hora);
            } else {
                response.sendRedirect("modificarCita.jsp?error=No se pudo modificar la cita.");
            }
        } catch (ClassNotFoundException e) {
            Logger.getLogger(ModificarCitaServlet.class.getName()).log(Level.SEVERE, null, e);
            response.getWriter().println("Error: Driver de base de datos no encontrado.");
        } catch (SQLException e) {
            Logger.getLogger(ModificarCitaServlet.class.getName()).log(Level.SEVERE, null, e);
            response.getWriter().println("Error: Problema con la base de datos. " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    Logger.getLogger(ModificarCitaServlet.class.getName()).log(Level.SEVERE, null, e);
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
        return "Servlet para modificar citas médicas";
    }
}