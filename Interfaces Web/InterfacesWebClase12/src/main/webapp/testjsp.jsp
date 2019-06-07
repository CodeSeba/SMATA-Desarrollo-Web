<%-- 
    Document   : testjsp
    Created on : 5 abr. 2019, 17:35:11
    Author     : root
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        
        <%
            out.println("<h2>Hola Mundo Servlet</h2>");
            
            //Test java 8
            out.println(System.getProperty("java.version"));
            
            List<String>lista=new ArrayList();
            lista.add("Lunes");
            lista.add("Martes");
            lista.add("Miércoles");
            lista.add("Jueves");
            lista.add("Viernes");
            /*
            //try{
            //    lista.forEach(item->{
            //        try{
            //            out.println(item);
            //        }catch(Exception ee) {}
            //    });
            //}catch(Exception e) {}
            */
            //lista.stream().forEach(item->out.println(item));
            for(String st:lista) out.println(st);
            
        %>
        
        <h3>Mas código HTML</h3>
        
        <% 
            //las variables declaradas en el otro espacio, son visibles desde aca.
            for(String st:lista) out.println(st);
        
        %>
    </body>
</html>
