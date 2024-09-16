    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Detalle de la Cita - CompensApp</title>
    <style>
        body {
            background-color: #ff6600;
            font-family: Arial, sans-serif;
        }
        .container {
            background-color: #f2f2f2;
            margin: 0 auto;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 60%;
            text-align: center;
        }
        h1 {
            color: #ff6600;
        }
        p {
            font-size: 18px;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Detalle de la Cita</h1>
    <%
        // Recuperar los parámetros de la URL
        String documento = request.getParameter("documento");
        String ubicacion = request.getParameter("ubicacion");
        String especialista = request.getParameter("especialista");
        String especialidad = request.getParameter("especialidad");
        String hora = request.getParameter("hora");
    %>
    <p><strong>Número de Documento:</strong> <%= documento %></p>
    <p><strong>Ubicación:</strong> <%= ubicacion %></p>
    <p><strong>Especialista:</strong> <%= especialista %></p>
    <p><strong>Especialidad:</strong> <%= especialidad %></p>
    <p><strong>Hora:</strong> <%= hora %></p>
    <p>¡Su cita ha sido agendada exitosamente!</p>
</div>
</body>
</html>
