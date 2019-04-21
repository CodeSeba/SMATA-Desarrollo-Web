package ar.org.centro8.curso.java.web.interfaces.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "TestServlet", urlPatterns = {"/TestServlet"})
public class TestServlet extends HttpServlet {

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

		// Manejo de Exceptoins con Try with resources desde JDK 7
		try (PrintWriter out = response.getWriter()) {
			/* TODO output your page here. You may use following sample code. */
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Servlet TestServlet</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h1>Servlet TestServlet at " + request.getContextPath() + "</h1>");
			out.println("<h2>Servidor de Sebas</h2>");

			try {
				int nro1 = Integer.parseInt(request.getParameter("nro1"));
				int nro2 = Integer.parseInt(request.getParameter("nro2"));
				out.println("Suma= " + (nro1 + nro2));
				// http://localhost:8081/InterfacesClase04/TestServlet?nro1=3&nro2=7
			} catch (Exception e) {
				out.println("Formato de numero incorrecto.");
				System.out.println("-------------------------------");
				System.out.println(e);
				System.out.println("-------------------------------");
			}

			// Consultar el mapa de parametros
			request
					.getParameterMap()
					.forEach((k, v) -> out.print(k + " " + Arrays.toString(v) + "\n"));
			
			List<String> lista=new ArrayList();
			lista.add("Lunes");
			lista.add("Martes");
			lista.add("Miercoles");
			lista.add("Jueves");
			lista.add("Viernes");
			lista.add("Sabado");
			lista.add("Domingo");
			//lista.forEach(item -> out.println(item));
			lista.forEach(out::println);

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
