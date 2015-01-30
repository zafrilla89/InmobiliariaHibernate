<%-- 
    Document   : nuevo
    Created on : 20-ene-2015, 19:04:36
    Author     : ZAFRA
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Nuevo inmueble</title>
        <link rel="stylesheet" type="text/css" href="resources/css/estilos.css"/>
    </head>
    <body>
        <div id="cabecera">
            <img  id="logo" src="resources/images/logo.png"/>
            <h1>INMOBILIARIA</h1>
        </div>
        <div id="contenido">
            <h2>Insertar nuevo inmueble</h2>
            <form action="control" method="POST">
                TIPO: <input required="true" type="text" name="tipo" value="" />
                </br>
                DIRECCION: <input required="true" type="text" name="direccion" value="" />
                </br>
                PRECIO: <input required="true" type="text" name="precio" value="" />
                </br>
                <input type="hidden" name="usuario" value="web" />
                <input type="hidden" name="target" value="inmueble" />
                <input type="hidden" name="op" value="insert" />
                <input type="hidden" name="action" value="op" />
                <input type="submit" value="enviar" />
            </form>
            <%
                String mensaje = (String) request.getAttribute("no");
                if (mensaje != null) {
                    out.print(mensaje);
                }
            %>
            </br>
            </br>
            <a href="principal.html">Volver</a>
        </div>
    </body>
</html>
