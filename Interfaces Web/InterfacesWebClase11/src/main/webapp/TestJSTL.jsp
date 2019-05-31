<%-- 
    Document   : TestJSTL
    Created on : 26 abr. 2019, 15:40:31
    Author     : root
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"  %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="ar.org.centro8.curso.java.web.interfaces.entities.User"%>
<%@page import="ar.org.centro8.curso.java.web.interfaces.repositories.UserR"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/style.css" type="text/css">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Test JSTLP</h1>
        
        <!-- tag out -->
        <c:out value="Hola Mundo JSTL"/>
        
        <!-- tag set -->
        <!-- setea variables -->
        <c:set var="miVariable" value="ValorContenido" scope="request" />
        <c:out value="${miVariable}" />
        
        <!-- Estructura IF -->
        <c:set var="valor" value="100" scope="request"/>
        <c:if test="${valor=='100'}">
            <c:out value="Valor igual a 100"/>
        </c:if>
        <c:if test="${!valor=='100'}">
            <c:out value="Valor no igual a 100"/>
        </c:if>
        
        <!-- Captura de parametros -->
        <!-- 
            http://localhost:8081/InterfacesClase06/
            TestJSTL.jsp?saludar=true&nombre=Carlos 
        -->
        <c:if test="${param.saludar}">
            <h1>Hola ${param.nombre}</h1>
        </c:if>
            
        <-- Uso de Beans en JSTL -->
        <jsp:useBean id="now" class="java.util.Date"/>
        <br>
        Fecha: <c:out value="${now}"/>
        <br>
        Fecha: <c:out value="${now.year}/${now.month}/${now.day}"/>
        <br>
        Fecha: <fmt:formatDate value="${now}" pattern="dd/MM/yyyy" />
        
        
        <!-- Estructura Choose -->
        <c:set var="categoria" value="C" scope="request"/>
        <c:choose>
            <c:when test="${categoria=='A'}"><h3>Categoria=A</h3></c:when>
            <c:when test="${categoria=='B'}"><h3>Categoria=B</h3></c:when>
            <c:when test="${categoria=='C'}"><h3>Categoria=C</h3></c:when>
            <c:otherwise><h3>No es una categoria!</h3></c:otherwise>
        </c:choose>
        
            <hr>
        
            <!-- Estructura forEach -->
            <c:forEach var="i" begin="10" end="20" step="2" varStatus="status">
                indice: <c:out value="${status.index}"/>
                iteración #: <c:out value="${status.count}"/>
            </c:forEach>
            
            <%

            %>
            
            <jsp:useBean id="userR" scope="request" 
                class="ar.org.centro8.curso.java.web.interfaces.repositories.UserR"/>
            <c:forEach var="u" items="${userR.getAll()}">
                <br>
                <c:out value="${u.id} ${u.name} ${u.email}"/>
            </c:forEach>
            
                <table border="1">
                    <tr><th>id</th><th>nombre</th><th>email</th></tr>
                    <c:forEach var="u" items="${userR.getAll()}">
                        <tr>
                            <td><c:out value="${u.id}"/></td>
                            <td><c:out value="${u.name}"/></td>
                            <td><c:out value="${u.email}"/></td>
                        </tr>
                    </c:forEach>
                </table>
                
                <!-- JSTL SQL -->
    <sql:setDataSource
        var="ds"
        driver="com.mysql.cj.jdbc.Driver"
        url="jdbc:mysql://localhost/cursoJavaWebNegocio"
        user="root"
        password="root"
    />
        
    <sql:query var="allRows" dataSource="${ds}">
        select id,descripcion,costo,precio,stock,stockMin,stockMax from articulos;
    </sql:query>
    
    <c:forEach var="u" items="${allRows.rows}">
        <br>
        <c:out value="${u.id} ${u.descripcion} ${u.costo} ${u.precio} 
               ${u.stock} ${u.stockMin} ${u.stockMax}"/>
    </c:forEach>
    
        <form>
            Descripción:<input type="text" name="descripcion"/>
            <input type="submit" value="Buscar"/>
        </form>
        <% 
            String desc="";
            try {
                desc=request.getParameter("descripcion");
            } catch (Exception e) {
            }
        %>
        <sql:query var="rows" dataSource="${ds}">
            <%
                out.println("select id, descripcion, costo, precio, stock, stockMin,"
                        + "stockMax from articulos where descripcion like '%"+desc+"%'");
            %>
        </sql:query>
        <table>
            <tr><th>id</th><th>descripcion</th><th>costo</th><th>precio</th>
                <th>stock</th><th>stockMínimo</th><th>stockMáximo</th></tr>
            <c:forEach var="a" items="${rows.rows}">
                <tr>
                    <td><c:out value="${a.id}"/></td>
                    <td><c:out value="${a.descripcion}"/></td>
                    <td><c:out value="${a.costo}"/></td>
                    <td><c:out value="${a.precio}"/></td>
                    <td><c:out value="${a.stock}"/></td>
                    <td><c:out value="${a.stockMin}"/></td>
                    <td><c:out value="${a.stockMax}"/></td>
                </tr>
            </c:forEach>
        </table>
        
    </body>
</html>
