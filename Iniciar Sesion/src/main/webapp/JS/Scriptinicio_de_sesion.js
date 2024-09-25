document.getElementById('loginForm').addEventListener('submit', function(event) {
    event.preventDefault(); // Evita el envío del formulario por defecto

    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;

    fetch('http://localhost:5000/auth/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ username: username, password: password })
    })
    .then(response => response.text()) // Cambiado a .text() para manejar la respuesta como texto
    .then(data => {
        if (data === 'Autenticación satisfactoria') {
            window.location.href = 'panel.jsp';
        } else {
            document.getElementById('errorMessage').textContent = 'Credenciales no válidas';
        }
    })
    .catch(error => {
        console.error('Error:', error);
        document.getElementById('errorMessage').textContent = 'Ocurrió un error. Inténtalo de nuevo más tarde.';
    });
});

