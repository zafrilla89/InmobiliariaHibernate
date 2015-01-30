<%-- 
    Document   : subirfotos
    Created on : 28-ene-2015, 12:17:39
    Author     : 2dam
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Subir fotos</title>
        <link rel="stylesheet" type="text/css" href="resources/css/estilos.css"/>
    </head>
    <body>
        <div id="cabecera">
            <img  id="logo" src="resources/images/logo.png"/>
            <h1>INMOBILIARIA</h1>
        </div>
        <div id="contenido">
            <h2>Subir fotos de inmuebles</h2>
            <%
                String id = (String) request.getAttribute("id");
            %>
            <form action="control" method="POST" enctype="multipart/form-data">
                <input type="file" name="archivo" required="true" accept="image/*" />
                </br>
                </br>
                <input type="hidden" name="target" value="fotosid" />
                <input type="hidden" name="op" value="insert" />
                <input type="hidden" name="action" value="op" />
                <input type="hidden" name="id" value="<%= id%>" />
                <input type="submit" value="subir" />
            </form>
            </br>
            </br>
            <a href="control?target=inmueble&op=select&action=view">Volver</a>
        </div>
    </body>
</html>
