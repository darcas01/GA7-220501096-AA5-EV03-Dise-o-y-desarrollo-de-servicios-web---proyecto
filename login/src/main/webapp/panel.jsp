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
        <title>JSP Page</title>
    </head>
    <body>
        <%
            if(session.getAttribute("usuario") == null){
                response.sendRedirect("index.html");
                return;
            }
            %>
        <h1>Hello World!</h1>
    </body>
</html>
