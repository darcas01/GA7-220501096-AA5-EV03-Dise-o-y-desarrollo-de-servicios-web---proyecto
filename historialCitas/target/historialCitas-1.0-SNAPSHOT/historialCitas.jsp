<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.darwuich.compensapp.historialcitas.models.Cita" %>
<%
    String documento = request.getParameter("documento");
    // Aquí se debería obtener el historial de citas desde la base de datos
    // Simulación de datos
    List<Cita> citas = (List<Cita>) request.getAttribute("citas");
%>
<table>
    <tr>
        <th>Fecha</th>
        <th>Hora</th>
        <th>Ubicación</th>
        <th>Especialista</th>
        <th>Especialidad</th>
    </tr>
    <%
        for (Cita cita : citas) {
    %>
    <tr>
        <td><%= cita.getFecha() %></td>
        <td><%= cita.getHora() %></td>
        <td><%= cita.getUbicacion() %></td>
        <td><%= cita.getEspecialista() %></td>
        <td><%= cita.getEspecialidad() %></td>
    </tr>
    <%
        }
    %>
</table>


