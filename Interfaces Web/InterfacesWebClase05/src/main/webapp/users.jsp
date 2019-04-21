<%@page import="ar.org.centro8.curso.java.web.interfaces.servlets.utilities.TablaHTML"%>
<%@page import="ar.org.centro8.curso.java.web.interfaces.entities.User"%>
<%@page import="ar.org.centro8.curso.java.web.interfaces.repositories.UserR"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mantenimiento de Usuarios</title>
    </head>
    <body>
        <h1>Mantenimiento de Usuarios</h1>
		<%
			String id		= "";
			String name		= "";
			String email	= "";
		%>
		
		<form>
			<fieldset><legend>Datos de Usuario</legend>
			ID:		<input type=text value="<% out.print(id); %>" name="id"><br>
			Nombre:	<input type=text value="<% out.print(name); %>" name="nombre"><br>
			Email:	<input type=text value="<% out.print(email); %>" name="email"><br>
			
			<input type=submit value="Agregar">
			</fieldset>
		</form>
		
		<%
			try {
				UserR.add(new User(
								request.getParameter("id"),
								request.getParameter("nombre"),
								request.getParameter("email")
							)
				);
				
				id		= "";
				name	= "";
				email	= "";
			} catch (Exception e) { out.println("Error de parametros!");}
		%>
		<br>
		<form>
			Buscar Email: <input type=input name="buscaEmail">
			<input type=submit value="Buscar">
			
		</form>
		<br>
		<%
			//out.printl(request.getParameter("buscaEmail") + "<br>");
			if (request.getParameter("buscaEmail") == null
			|| request.getParameter("buscaEmail").isEmpty())
				out.println(new TablaHTML().getTabla(UserR.getAll()));
			else
				out.println(new TablaHTML()
								.getTabla(UserR
									.getLikeEmail(request.getParameter("buscaEmail"))
								)
							);
		%>
    </body>
</html>
