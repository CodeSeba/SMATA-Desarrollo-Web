package ar.org.centro8.curso.java.web.aplicaciones.rest.servicios;

import ar.org.centro8.curso.java.web.aplicaciones.connectors.ConnectorMySQL;
import ar.org.centro8.curso.java.web.aplicaciones.entities.Articulo;
import ar.org.centro8.curso.java.web.aplicaciones.repositories.ArticuloR;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/articulos/v1")
public class ArticuloServicio {
	
	private ArticuloR ar = new ArticuloR(ConnectorMySQL.getConnection());
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String info () { return "<h1>Servicio de articulos.</h1>"; }
	
	@GET
	@Path("/add")
	@Produces(MediaType.TEXT_PLAIN)
	public String add(
				@QueryParam("descripcion")	String descripcion,
				@QueryParam("costo")		double costo,
				@QueryParam("precio")		double precio,
				@QueryParam("stock")		int stock,
				@QueryParam("stockMin")		int stockMin,
				@QueryParam("stockMax")		int stockMax) {
		
		Articulo articulo = new Articulo(
								descripcion,
								costo,
								precio,
								stock,
								stockMin,
								stockMax
		);
		
		ar.save(articulo);
		
		return articulo.getId() + "";
		
		// http://localhost:8081/AplicacionesWebClase10/
		//		webresources/articulos/v1/
		//		add?descripcion=Zapallo&costo=222&precio=333&
		//		stock=444&stockMin=111&stockMax=555

	}
	
	@GET
	@Path("/remove")
	@Produces(MediaType.TEXT_PLAIN)
	public String remove(@QueryParam("id") int id) {
		ar.remove(ar.getById(id));
		return "true";
		
		// http://localhost:8081/AplicacionesWebClase10/
		//		webresources/articulos/v1/remove?id=6
	}
	
	private static String lista = "";
	
	@GET
	@Path("/list")
	@Produces(MediaType.TEXT_PLAIN)
	public String list() {
		lista = "";
		ar.getAll().forEach(item -> lista += item + "\n");
		return lista;
		
		// http://localhost:8081/AplicacionesWebClase10/
		//		webresources/articulos/v1/list
	}
	
	
	@GET
	@Path("/list_like_descripcion")
	@Produces(MediaType.TEXT_PLAIN)
	public String listLikeDescripcion(@QueryParam("descripcion") String descripcion) {
		if (descripcion == null) descripcion = "";
		lista = "";
		ar.getLikeDescripcion(descripcion).forEach(item -> lista += item + "\n");
		return lista;
		
		// http://localhost:8081/AplicacionesWebClase10/
		//		webresources/articulos/v1/list_like_descripcion?descripcion=BA
	}
}
