<%-- 
    Document   : panel
    Created on : 4/09/2024, 2:57:07 p. m.
    Author     : darca
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Panel de Usuario</title>
    </head>
    <body>
        <%
            String usuario = (String) session.getAttribute("usuario");
            if (usuario == null) {
                response.sendRedirect("index.html");
                return;
            }
        %>
        <h1>Hola, <%= usuario %>!</h1>
        <p>Bienvenido a tu panel de usuario.</p>
    </body>
</html>
