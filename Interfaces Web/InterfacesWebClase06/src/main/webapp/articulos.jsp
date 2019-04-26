<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="ar.org.centro8.curso.java.web.interfaces.servlets.utilities.TablaHTML"%>
<%@page import="ar.org.centro8.curso.java.web.interfaces.entities.User"%>
<%@page import="ar.org.centro8.curso.java.web.interfaces.repositories.UserR"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mantenimiento de Articulos</title>
    </head>
    <body>
        <h1>Mantenimiento de Articulos</h1>
		
		<form>
			<fieldset><legend>Datos de Articulo</legend>
				<table>
					<tr><td>Descripcion		</td><td><input type=text name="descripcion">	</td></tr>
					<tr><td>Costo			</td><td><input type=text name="costo">			</td></tr>
					<tr><td>Precio			</td><td><input type=text name="precio">		</td></tr>
					<tr><td>Stock			</td><td><input type=text name="stock">			</td></tr>
					<tr><td>Stock Mínimo	</td><td><input type=text name="stockMin">		</td></tr>
					<tr><td>Stock Máximo	</td><td><input type=text name="stockMax">		</td></tr>
					<tr><td><input type=reset value="Limpiar"></td>
						<td><input type=submit value="Guardar"></td></tr>
				</table>
			</fieldset>
		</form>
		
		<%
			Connection conn = null;
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				conn = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/cursoJavaWebNegocio",
						"root",
						"");
			} catch(Exception e) { out.println("<h3>Ocurrio un error de conexión.</h3>"); }
			
			String	descripcion	= "";
			double	costo		= 0;
			double	precio		= 0;
			int		stock		= 0;
			int		stockMin	= 0;
			int		stockMax	= 0;
			boolean validar		= true;
			
			if (request.getParameter("descripcion") != null
				&& request.getParameter("descripcion").length() >= 3)
				{ descripcion = request.getParameter("descripcion"); }
			else validar = false;
			
			try {
				costo = Double.parseDouble(request.getParameter("costo"));
				precio = Double.parseDouble(request.getParameter("precio"));
				stock = Integer.parseInt(request.getParameter("stock"));
				stockMin = Integer.parseInt(request.getParameter("stockMin"));
				stockMax = Integer.parseInt(request.getParameter("stockMax"));
			} catch(Exception ex) { validar = false; }
			
			if (validar) {
				try(PreparedStatement ps = conn.prepareStatement(
						"insert into articulos "
						+ "(descripcion, costo, precio, stock, stockMin, stockMax) "
						+ "values (?, ?, ?, ?, ?, ?)",
						PreparedStatement.RETURN_GENERATED_KEYS
					)) {
						ps.setString(1, descripcion);
						ps.setDouble(2, costo);
						ps.setDouble(3, precio);
						ps.setInt(4, stock);
						ps.setInt(5, stockMin);
						ps.setInt(6, stockMax);
						ps.execute();
						ResultSet rs = ps.getGeneratedKeys();
						if (rs.next()) {
							out.println("<h3>Se dio de Alta el articulo id: " + rs.getInt(1) + "</h3>");
						}
					} catch(Exception ex) { ex.printStackTrace(); }
			} else { out.println("<h3>Error de Validación de datos.</h3>"); }
		%>
		
		<%
			try (ResultSet rs = conn
					.createStatement()
					.executeQuery("select * from articulos");
				) {
				out.println("<table border=1>");
				out.println("<tr>"
						+ "<th>Id</th>"
						+ "<th>Descripción</th>"
						+ "<th>Costo</th>"
						+ "<th>Precio</th>"
						+ "<th>Stock</th>"
						+ "<th>Stock Mínimo</th>"
						+ "<th>Stock Máximo</th>"
						+ "</tr>");
				while (rs.next()) {
					out.println("<tr>");
					
					out.println("<td>" + rs.getInt("id") +"</td>");
					out.println("<td>" + rs.getString("descripcion") +"</td>");
					out.println("<td>" + rs.getString("costo") +"</td>");
					out.println("<td>" + rs.getDouble("precio") +"</td>");
					out.println("<td>" + rs.getInt("stock") +"</td>");
					out.println("<td>" + rs.getInt("stockMin") +"</td>");
					out.println("<td>" + rs.getInt("stockMax") +"</td>");
					
					out.println("</tr>");
				}
			} catch(Exception ex) { out.println("Error en XXXXXXXXXXXXX"); }
		%>
		
		<%
			try {
				if (conn != null) conn.close();
			} catch(Exception ex) { out.println("<h3>Error al cerrar la conexión.</h3>"); }
		%>
    </body>
</html>
