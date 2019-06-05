package ar.org.centro8.curso.java.web.aplicaciones.rest.servicios;

import ar.org.centro8.curso.java.web.aplicaciones.connectors.ConnectorMySQL;
import ar.org.centro8.curso.java.web.aplicaciones.entities.Detalle;
import ar.org.centro8.curso.java.web.aplicaciones.repositories.DetalleR;
import ar.org.centro8.curso.java.web.aplicaciones.repositories.FacturaR;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/detalles/v1")
public class DetalleServicio {
	
	private DetalleR dr = new DetalleR(ConnectorMySQL.getConnection());
	private FacturaR fr = new FacturaR(ConnectorMySQL.getConnection());
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String info () { return "<h1>Servicio de detalles.</h1>"; }
	
	@GET
	@Path("/add")
	@Produces(MediaType.TEXT_PLAIN)
	public String add(
				@QueryParam("idFactura")		int idFactura,
				@QueryParam("idArticulo")		int idArticulo,
				@QueryParam("cantidad")			int cantidad,
				@QueryParam("precioUnit")		double precioUnit) {
		
		Detalle detalle = new Detalle(
								idFactura,
								idArticulo,
								cantidad,
								precioUnit);
		
		dr.save(detalle);
		
		return "true";
		
		// http://localhost:8081/AplicacionesWebClase11/
		//		webresources/detalles/v1/
		//		add?idFactura=1&idArticulo=1&cantidad=111&precioUnit=111.11

	}
	
	@GET
	@Path("/remove")
	@Produces(MediaType.TEXT_PLAIN)
	public String remove(	@QueryParam("idFactura")	int idFactura,
							@QueryParam("idArticulo")	int idArticulo) {
		dr.remove(dr.getByFacturaArticulo(
						fr.getById(idFactura).getLetra(),
						fr.getById(idFactura).getNumero(),
						idArticulo));
		return "true";
		
		// http://localhost:8081/AplicacionesWebClase11/
		//		webresources/detalles/v1/remove?idFactura=4&idArticulo=4
	}
	
	private static String lista = "";
	
	@GET
	@Path("/list")
	@Produces(MediaType.TEXT_PLAIN)
	public String list() {
		lista = "";
		dr.getAll().forEach(item -> lista += item + "\n");
		return lista;
		
		// http://localhost:8081/AplicacionesWebClase11/
		//		webresources/detalles/v1/list
	}
	
	
	@GET
	@Path("/list_by_factura")
	@Produces(MediaType.TEXT_PLAIN)
	public String listByIdCliente(@QueryParam("idFactura") int idFactura) {
		//if (idCliente == null) idCliente = "";
		lista = "";
		dr.getByFactura(fr.getById(idFactura)).forEach(item -> lista += item + "\n");
		return lista;
		
		// http://localhost:8081/AplicacionesWebClase11/
		//		webresources/detalles/v1/list_by_factura?idFactura=4
	}
}
