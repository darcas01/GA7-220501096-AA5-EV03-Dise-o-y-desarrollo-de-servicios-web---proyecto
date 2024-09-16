<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Panel de Usuario</title>
        <link rel="stylesheet" href="Registrocss.css">
    </head>
    <body>
        <%
            String usuario = (String) session.getAttribute("usuario");
            if (usuario == null) {
                response.sendRedirect("index.html");
                return;
            }
        %>
        <div class="container">
            <h1>Bienvenido, <%= usuario %>!</h1>
            <p>Este es tu panel de usuario.</p>
            <!-- Aquí puedes agregar más contenido para el panel de usuario -->
        </div>
    </body>
</html>