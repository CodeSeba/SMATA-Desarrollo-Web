package ar.org.centro8.curso.java.web.interfaces.servlets;

import ar.org.centro8.curso.java.web.interfaces.entities.User;
import ar.org.centro8.curso.java.web.interfaces.repositories.UserR;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "TestJava8", urlPatterns = {"/TestJava8"})
public class TestJava8 extends HttpServlet {

	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
	 * methods.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			/* TODO output your page here. You may use following sample code. */
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Servlet TestJava8</title>");			
			out.println("</head>");
			out.println("<body>");
			
			out.println(System.getProperty("java.version"));
			
			UserR.add(new User("1","Ana","ana@gmail.com"));
			UserR.add(new User("2","Ramón","ramon@gmail.com"));
			UserR.add(new User("3","Luis","luis@gmail.com"));
			UserR.add(new User("4","Maria","maria@gmail.com"));
			UserR.add(new User("5","Jose","jose@gmail.com"));
			
			out.println("<br>-- Listado total --<br>");
			UserR.getAll().forEach(out::println);
			
			// Api Stream JDK 8
			
			// Sintaxis SQL
			// select * from users;
			out.println("<br>1**********************************<br>");
			UserR.getAll().forEach(out::println);
			
			// select nombre from users;
			out.println("<br>2**********************************<br>");
			UserR.getAll()
					.stream()
					.map(User::getName)
					.forEach(out::println);
			
			// select nombre, email from users;
			out.println("<br>3**********************************<br>");
			UserR.getAll()
					.forEach(item -> out.println(
					item.getName() + " : " + item.getEmail() + "<br>"));
			
			UserR.add(new User("10","maria","mariamaria@hotmail.com"));
			UserR.add(new User("11","MARIA","mariamaria@gmail.com"));
			
			// select * from users where nombre = 'maria';
			out.println("<br>4**********************************<br>");
			UserR.getAll()
					.stream()
					.filter(u -> u.getName().equalsIgnoreCase("maria"))
					.forEach(out::println);
			
			UserR.add(new User("12","Monica","moniqueta@hotmail.com"));
			
			// select nombre from users like 'm%';
			out.println("<br>5**********************************<br>");
			UserR.getAll()
					.stream()
					.filter(u -> u.getName().toLowerCase().startsWith("m"))
					.map(User::getName)
					.forEach(out::println);
			
			// select nombre from users where email like '%hotmail.com';
			out.println("<br>6**********************************<br>");
			UserR.getAll()
					.stream()
					.filter(u -> u.getEmail().toLowerCase().endsWith("hotmail.com"))
					.map(User::getName)
					.forEach(out::println);
			
			// select * from users order by email;
			out.println("<br>7**********************************<br>");
			UserR.getAll()
					.stream()
					.sorted(Comparator.comparingInt(item -> item.getEmail().hashCode()))
					.forEach(item ->
							out.println(item + " " + item.getEmail().hashCode() + "<br>"));
			
			// select * from users order by email desc;
			out.println("<br>8**********************************<br>");
			UserR.getAll()
					.stream()
					.sorted(Comparator.comparingInt(item -> item.getEmail().hashCode() * -1))
					.forEach(item ->
							out.println(item + " " + item.getEmail().hashCode() + "<br>"));
			
			// Comparable
			out.println("<br>9**********************************<br>");
			UserR.getAll()
					.stream()
					.sorted()
					.forEach(out::println);
			
			// select nombre, count(*) from users group by nombre;
			
			out.println("<br>10**********************************<br>");
			UserR.getAll()
					.stream()
					.collect(
							// Aca fijamos el agrupamiento
							Collectors.groupingBy(
									User::getName,			// agrupamos por nombre
									Collectors.counting()	// contador
							)
					)
					.forEach((k,v) -> out.println(k + " " + v + "<br>"));
			
			out.println("</body>");
			out.println("</html>");
		}
	}

	// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
	/**
	 * Handles the HTTP <code>GET</code> method.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Handles the HTTP <code>POST</code> method.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Returns a short description of the servlet.
	 *
	 * @return a String containing servlet description
	 */
	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>

}
