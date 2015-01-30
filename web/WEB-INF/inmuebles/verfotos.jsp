<%-- 
    Document   : verfotos
    Created on : 28-ene-2015, 12:17:59
    Author     : 2dam
--%>

<%@page import="com.izv.hibernate.Fotos"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ver fotos</title>
        <link rel="stylesheet" type="text/css" href="resources/css/estilos.css"/>
    </head>
    <body>
        <div id="cabecera">
            <img  id="logo" src="resources/images/logo.png"/>
            <h1>INMOBILIARIA</h1>
        </div>
        <div id="contenido">
            <h2>Fotos</h2>
            <%
                List<Fotos> fotos = (List<Fotos>) request.getAttribute("fotos");
                for (int x = 0; x < fotos.size(); x++) {
                    Fotos fo=fotos.get(x);
            %>
            <img src="<%= "fotos/"+fo.getNombre() %>" width="300px" height="300px"/>
            </br>
            <a href="control?target=fotos&op=delete&action=op&foto=<%= fo.getNombre() %>&idinmueble=<%= request.getAttribute("id")%>&id=<%= fo.getId() %>">Borrar</a>
            </br>
            </br>
            <%
                }
            %>
            </br>
            <a href="control?target=inmueble&op=select&action=view">Volver</a>
        </div>
    </body>
</html>
