<%-- 
    Document   : users
    Created on : 5 abr. 2019, 18:00:43
    Author     : root
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="ar.org.centro8.curso.java.web.interfaces.entities.User"%>
<%@page import="ar.org.centro8.curso.java.web.interfaces.repositories.UserR"%>
<%@page import="ar.org.centro8.curso.java.web.interfaces.servlets.utilities.TablaHtml"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mantenimiento de Usuarios</title>
    </head>
    <body>
        <h1>Mantenimiento de Usuarios</h1>
        <% 
            String id="";
            String name="";
            String email="";
        %>
        
        <form>
            ID:<input type="text" value="<% out.print(id); %>" name="id"><br>
            Name:<input type="text" value="<% out.print(name); %>" name="name"><br>
            Email:<input type="email" value="<% out.print(email); %>" name="email"><br>
            <input type="submit" value="Agregar">
        </form>
            
            <%
                try {
                    UserR.add(new User(
                        request.getParameter("id"),
                        request.getParameter("name"),
                        request.getParameter("email")
                    ));
                    id="";
                    name="";
                    email="";
                } catch (Exception e) {
                    out.println("Error de parametros!");
                }
            %>
            
            <form>
                Buscar Email:<input type="input" name="buscaEmail" >
                <input type="submit" value="Buscar" >
            </form>
            
            <%
                //out.println(request.getParameter("buscaEmail")+"<br");
                if(request.getParameter("buscaEmail")==null 
                || request.getParameter("buscaEmail").isEmpty())
                    out.println(new TablaHtml().getTabla(UserR.getAll()));
                else
                    out.println(
                        new TablaHtml()
                                .getTabla(UserR
                                        .getLikeEmail(request
                                                .getParameter("buscaEmail")
                                        )
                                )
                );
            %>
            
            
    </body>
</html>
