<%-- 
    Document   : TestJSTL
    Created on : Apr 26, 2019, 3:41:36 PM
    Author     : seba
--%>

<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"	%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"	%>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="ar.org.centro8.curso.java.web.interfaces.entities.User"%>
<%@page import="ar.org.centro8.curso.java.web.interfaces.repositories.UserR"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Test JSTLP</h1>
		
		<!-- tag out -->
		<c:out value="Hola Mundo JSTL !" />
		
		<!-- tag set -->
		<!-- setea variables -->
		<c:set var="miVariable" value="ValorContenido" scope="request" />
		<c:out value="${miVariable}" />
		
		<!-- Estructura IF -->
		<c:set var="valor" value="100" scope="request" />
		<c:if test="${valor == '100'}">
			<c:out value="Valor igual a 100." />
		</c:if>
		<c:if test="${ ! valor == '100'}">
			<c:out value="Valor no igual a 100." />
		</c:if>
		
		<!-- Captura de parametros -->
		<!-- /TestJSTL.jsp?saludar=true&nombre=Sebas -->
		<c:if test="${param.saludar}">
			<h1>Hola ${param.nombre}</h1>
		</c:if>
		
		<!-- Uso de Beans en JSTL -->
		<jsp:useBean id="now" class="java.util.Date" />
		Fecha: <c:out value="${now}" />
		<br>
		Fecha: <c:out value="${now.day}/${now.month}/${now.year}" />
		<br>
		Fecha: <fmt:formatDate pattern="dd/MM/yyyy" value="${now}" />
		
		<!-- Estructura Choose -->
		<c:set var="categoria" value="C" scope="request" />
		<c:choose>
			<c:when test="${categoria == 'A'}"><h3>Categoria=A</h3></c:when>
			<c:when test="${categoria == 'B'}"><h3>Categoria=B</h3></c:when>
			<c:when test="${categoria == 'C'}"><h3>Categoria=C</h3></c:when>
			<c:otherwise><h3>No es una categoria !</h3></c:otherwise>
		</c:choose>
		
		<hr><br>
		
		<!-- Estructura forEach -->
		<c:forEach var="1" begin="10" end="20" step="2" varStatus="status">
			Indice: <c:out value="${status.index}" />
			Iteración #<c:out value="${status.count}" />
			<br>
		</c:forEach>
		
		<hr><br>
		
		<%
			UserR.add(new User("1", "Ana", "ana@mail.com"));
			UserR.add(new User("2", "Juan", "juan@mail.com"));
			UserR.add(new User("3", "Jose", "jose@mail.com"));
			UserR.add(new User("4", "Carlos", "carlos@mail.com"));
			UserR.add(new User("5", "Maria", "maria@mail.com"));
		%>
		
		<jsp:useBean id="userR" scope="request"
					 class="ar.org.centro8.curso.java.web.interfaces.repositories.UserR"/>
		<c:forEach var="u" items="${userR.getAll()}">
			<c:out value="${u.id} ${u.name} ${u.email}" />
			<br>
		</c:forEach>
		
		<hr><br>
		
		<table border="1">
			<tr><th>id</th><th>nombre</th><th>email</th></tr>
			<c:forEach var="u" items="${userR.getAll()}">
				<tr>
					<td><c:out value="${u.id}" /></td>
					<td><c:out value="${u.name}" /></td>
					<td><c:out value="${u.email}" /></td>
				</tr>
			</c:forEach>
		</table>
		
		<hr><br>
		
		<!-- JSTL SQL -->
		<sql:setDataSource
			var="ds"
			driver="com.mysql.cj.jdbc.Driver"
			url="jdbc:mysql://localhost/cursoJavaWebNegocio"
			user="root"
			password="" />
		
		<sql:query var="allRows" dataSource="${ds}">
			select id,descripcion,costo,precio,stock,stockMin,stockMax from articulos;
		</sql:query>
		
		<c:forEach var="u" items="${allRows.rows}">
			<br>
			<c:out value="${u.id} ${u.descripcion} ${u.costo} ${u.precio} 
							${u.stock} ${u.stockMin} ${u.stockMax}" />
		</c:forEach>
		
    </body>
</html>
