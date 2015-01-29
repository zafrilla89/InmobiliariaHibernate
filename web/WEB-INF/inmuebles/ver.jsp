<%-- 
    Document   : ver
    Created on : 20-ene-2015, 18:42:06
    Author     : ZAFRA
--%>

<%@page import="java.util.Date"%>
<%@page import="java.util.List"%>
<%@page import="com.izv.hibernate.Inmueble"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ver inmuebles</title>
        <link rel="stylesheet" type="text/css" href="resources/css/estilos.css"/>
    </head>
    <body>
        <div id="cabecera">
            <img  id="logo" src="resources/images/logo.png"/>
            <h1>INMOBILIARIA</h1>
        </div>
        <div id="contenido">
            <h2>Todos los inmuebles</h2>
            <table border="1">
                <thead>
                    <tr>
                        <th>TIPO</th>
                        <th>DIRECCION</th>
                        <th>PRECIO</th>
                        <th>FECHA ALTA</th>
                        <th colspan="2">FOTOS</th>
                        <th>MODIFICAR</th>
                        <th>BORRAR</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        List<Inmueble> inmuebles = (List) request.getAttribute("datos");
                        for (int x = 0; x < inmuebles.size(); x++) {
                            Inmueble in = inmuebles.get(x);

                    %>
                    <tr>
                        <td><%= in.getTipo()%></td>
                        <td><%= in.getDireccion()%></td>
                        <td><%= in.getPrecio()%></td>
                        <td>
                            <%
                                Date d = in.getFechaalta();
                                out.print(d.getYear() + 1900 + "-" + d.getMonth() + 1 + "-" + d.getDate());
                            %>
                        </td>
                        <td><a href="control?target=fotosid&op=insert&action=view&id=<%= in.getId()%>"><img class="ver" src="resources/images/nuevo.jpg"/></a></td>
                        <td><a href="control?target=fotosid&op=update&action=view&id=<%= in.getId()%>"><img class="ver" src="resources/images/ver.jpg"/></a></td>
                        <td><a href="control?target=inmueble&op=update&action=view&id=<%= in.getId()%>"><div class="verdiv"><img class="ver" src="resources/images/editar.png"/></div></a></td>
                        <td><a href="control?target=inmueble&op=delete&action=op&id=<%= in.getId()%>"><div class="verdiv"><img class="ver" src="resources/images/borrar.jpg"/></div></a></td>
                    </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
            </br>
            </br>
            <a href="principal.html">Volver</a>
        </div>
    </body>
</html>
