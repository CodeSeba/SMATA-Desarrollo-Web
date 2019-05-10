<%-- 
    Document   : articulos
    Created on : 12 abr. 2019, 16:22:44
    Author     : root
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mantenimiento de Articulos</title>
    </head>
    <body>
        <h1>Alta de Articulos</h1>
        <form>
            <table>
                <tr><td>descripción</td><td><input type="text" name="descripcion"></td></tr>
                <tr><td>costo</td><td><input type="text" name="costo"></td></tr>
                <tr><td>precio</td><td><input type="text" name="precio"></td></tr>
                <tr><td>stock</td><td><input type="text" name="stock"></td></tr>
                <tr><td>stock mínimo</td><td><input type="text" name="stockMinimo"></td></tr>
                <tr><td>stock máximo</td><td><input type="text" name="stockMaximo"></td></tr>
                <tr><td><input type="reset" value="Limpiar"></td>
                    <td><input type="submit" value="Guardar"></td></tr>
            </table>
        </form>
        
        <%
            Connection conn=null;
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/cursoJavaWebNegocio", 
                    "root", 
                    "root");
            }catch(Exception e){
                out.println("<h3>Ocurrio un error de conección</h3>");
            }
            boolean validar=true;
            String descripcion="";
            double costo=0;
            double precio=0;
            int stock=0;
            int stockMinimo=0;
            int stockMaximo=0;
            
            if(request.getParameter("descripcion")!=null 
                && request.getParameter("descripcion").length()>=3){
                descripcion=request.getParameter("descripcion");
            }else validar=false;
            try {
                costo=Double.parseDouble(request.getParameter("costo"));
                precio=Double.parseDouble(request.getParameter("precio"));
                stock=Integer.parseInt(request.getParameter("stock"));
                stockMinimo=Integer.parseInt(request.getParameter("stockMinimo"));
                stockMaximo=Integer.parseInt(request.getParameter("stockMaximo"));
            } catch (Exception e) { validar = false; }
            
            if(validar){
                try (PreparedStatement ps=conn.prepareStatement(
                        "insert into articulos "
                        + "(descripcion,costo,precio,stock,stockMin,stockMax)"
                        + " values (?,?,?,?,?,?)",
                        PreparedStatement.RETURN_GENERATED_KEYS
                    )){
                        ps.setString(1, descripcion);
                        ps.setDouble(2, costo);
                        ps.setDouble(3, precio);
                        ps.setInt(4, stock);
                        ps.setInt(5, stockMinimo);
                        ps.setInt(6, stockMaximo);
                        ps.execute();
                        ResultSet rs=ps.getGeneratedKeys();
                        if(rs.next()){
                            out.println("<h3>Se dio de alra el articulo id:"+rs.getInt(1)+" </h3>");
                        }
                } catch (Exception e) { 
                    out.println(e);
                    out.println("<h3>Error de bd</h3>"); 
                }
            }else{
                out.println("<h3>Error de Validación de datos</h3>");
            }
        
        %>
        
        <form>
            Buscar Articulo:
            <input type="text" name="buscarArticulo"/>
            <input type="submit" value="buscar"/>
        </form>
        
        <% 
            String buscar="";
            try {
                    buscar=request.getParameter("buscarArticulo");
            } catch (Exception e) { }
            try (ResultSet rs=conn
                    .createStatement()
                    .executeQuery("select * from articulos "
                    + "where descripcion like'%"+buscar+"%'");
                ){
                out.println("<table>");
                out.println("<tr>"
                    + "<th>Id</th>"
                    + "<th>Descripcion</th>"
                    + "<th>Costo</th>"
                    + "<th>Precio</th>"
                    + "<th>Stock</th>"
                    + "<th>StockMínimo</th>"
                    + "<th>StockMáximo</th>"
                    + "</tr>");
                while(rs.next()){
                    out.println("<tr>");
                    out.println("<td>"+rs.getInt("id")+"</td>");
                    out.println("<td>"+rs.getString("descripcion")+"</td>");
                    out.println("<td>"+rs.getString("costo")+"</td>");
                    out.println("<td>"+rs.getDouble("precio")+"</td>");
                    out.println("<td>"+rs.getInt("stock")+"</td>");
                    out.println("<td>"+rs.getInt("stockMin")+"</td>");
                    out.println("<td>"+rs.getInt("stockMax")+"</td>");
                    out.println("</tr>");
                }
                out.println("<table>");
            }catch(Exception e){
                out.println("Error en el listado");
            }
            

        %>
        
        
        <% 
            try{
                if(conn!=null) conn.close();
            }catch(Exception e) {}
        %>
    </body>
</html>
