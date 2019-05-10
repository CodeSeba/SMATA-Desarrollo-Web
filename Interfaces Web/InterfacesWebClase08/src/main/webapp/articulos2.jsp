<%-- 
    Document   : articulos2
    Created on : 3 may. 2019, 16:46:59
    Author     : root
--%>

<%@page import="ar.org.centro8.curso.java.web.aplicaciones.connectors.ConnectorMySQL"%>
<%@page import="ar.org.centro8.curso.java.web.aplicaciones.repositories.ArticuloR"%>
<%@page import="ar.org.centro8.curso.java.web.aplicaciones.entities.Articulo"%>
<%@page import="ar.org.centro8.curso.java.web.interfaces.servlets.utilities.TablaHtml"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/style.css" type="text/css">
    </head>
    <body>
        <h1>Formulario de articulos</h1>
        <form>
            <table>
                <tr><td>Descripción:</td>   <td><input type="text" name="descripcion"/> </td></tr>
                <tr><td>Costo:</td>         <td><input type="text" name="costo"/>       </td></tr>
                <tr><td>Precio:</td>        <td><input type="text" name="precio"/>      </td></tr>
                <tr><td>Stock:</td>         <td><input type="text" name="stock"/>       </td></tr>
                <tr><td>Stock Mínimo:</td>  <td><input type="text" name="stockMin"/>    </td></tr>
                <tr><td>Stock Máximo:</td>  <td><input type="text" name="stockMax"/>    </td></tr>
                <tr>
                    <td><input type="reset" value="Limpiar" /></td>
                    <td><input type="submit" value="Guardar"/></td>
                </tr>
            </table>
        </form>
        
        <%
            try {
                Articulo articulo=new Articulo(
                    request.getParameter("descripcion"),
                    Double.parseDouble(request.getParameter("costo")),
                    Double.parseDouble(request.getParameter("precio")),
                    Integer.parseInt(request.getParameter("stock")),
                    Integer.parseInt(request.getParameter("stockMin")),
                    Integer.parseInt(request.getParameter("stockMax"))
                );
                new ArticuloR(ConnectorMySQL.getConnection()).save(articulo);
                out.println("Se guardo el articulo "+articulo.getId());
            } catch (Exception e) {
                out.println("Error de ingreso de datos");
                e.printStackTrace();
            }
        %>
        
        <% new TablaHtml<Articulo>().getTabla(new ArticuloR(ConnectorMySQL.getConnection()).getAll()); %>
        
    </body>
</html>
