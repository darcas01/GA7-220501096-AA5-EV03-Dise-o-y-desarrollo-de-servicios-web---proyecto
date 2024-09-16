<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Modificar Cita</title>
    <link rel="stylesheet" href="modificarCita.css">
</head>
<body>
    <h1>Modificar Cita</h1>
    <form id="modificarCitaForm" action="ModificarCitaServlet" method="post">
        <label for="documento">Número de Documento:</label>
        <input type="text" id="documento" name="documento" required>

        <div id="formularioModificarCita" style="display: none;">
            <label for="ubicacion">Ubicación:</label>
            <select id="ubicacion" name="ubicacion" required>
                <option value="">Seleccione una Ubicación</option>
                <option value="Kennedy">Kennedy</option>
                <option value="Venecia">Venecia</option>
            </select>

            <label for="especialista">Especialista:</label>
            <select id="especialista" name="especialista" required>
                <option value="">Seleccione un Especialista</option>
                <option value="Dr. Juan Pérez">Dr. Juan Pérez</option>
                <option value="Dra. Ana Martínez">Dra. Ana Martínez</option>
            </select>

            <label for="especialidad">Especialidad:</label>
            <select id="especialidad" name="especialidad" required
                     <option value="">Seleccione una Especialidad</option>
            <option value="Oncología">Oncología</option>
            <option value="Pediatría">Pediatría</option>
        </select>

        <label for="fecha">Fecha:</label>
        <input type="date" id="fecha" name="fecha" required>

        <label for="hora">Hora:</label>
        <input type="time" id="hora" name="hora" required>

        <button type="submit">Modificar Cita</button>
        <p id="errorMessage" class="error"></p>
    </div>
</form>

<c:if test="${not empty param.error}">
    <p class="error">${param.error}</p>
</c:if>

<script>
    document.addEventListener('DOMContentLoaded', function() {
        document.getElementById('documento').addEventListener('input', function() {
            var documento = this.value;
            if (documento) {
                document.getElementById('formularioModificarCita').style.display = 'block';
            } else {
                document.getElementById('formularioModificarCita').style.display = 'none';
            }
        });
    });
</script>
</body>
</html>