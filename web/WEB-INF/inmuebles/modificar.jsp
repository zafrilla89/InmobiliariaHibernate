<%-- 
    Document   : medificar
    Created on : 20-ene-2015, 19:15:12
    Author     : ZAFRA
--%>

<%@page import="java.util.Date"%>
<%@page import="com.izv.hibernate.Inmueble"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modificar imbueble</title>
        <link rel="stylesheet" type="text/css" href="resources/css/estilos.css"/>
    </head>
    <body>
        <div id="cabecera">
            <img  id="logo" src="resources/images/logo.png"/>
            <h1>INMOBILIARIA</h1>
        </div>
        <div id="contenido">
            <h2>Modificar inmueble</h2>
            <%
                Inmueble in = (Inmueble) request.getAttribute("inmueble");
            %>
            <form action="control" method="POST">
                TIPO: <input required="true" type="text" name="tipo" value="<%= in.getTipo()%>" />
                </br>
                DIRECCION: <input required="true" type="text" name="direccion" value="<%= in.getDireccion()%>" />
                </br>
                PRECIO: <input required="true" type="text" name="precio" value="<%= in.getPrecio()%>" />
                </br>
                <input type="hidden" name="target" value="inmueble" />
                <input type="hidden" name="op" value="update" />
                <input type="hidden" name="action" value="op" />
                <input type="hidden" name="id" value="<%= in.getId()%>" />
                <input type="submit" value="enviar" />
            </form>
                <% 
                String mensaje=(String) request.getAttribute("no");
                if(mensaje!=null){
                    out.print(mensaje);
                }
            %>
            </br>
            </br>
            <a href="control?target=inmueble&op=select&action=view">Volver</a>
        </div>
    </body>
</html>
