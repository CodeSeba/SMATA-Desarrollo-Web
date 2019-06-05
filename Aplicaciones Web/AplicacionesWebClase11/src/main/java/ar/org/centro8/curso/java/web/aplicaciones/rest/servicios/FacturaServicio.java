package ar.org.centro8.curso.java.web.aplicaciones.rest.servicios;

import ar.org.centro8.curso.java.web.aplicaciones.connectors.ConnectorMySQL;
import ar.org.centro8.curso.java.web.aplicaciones.entities.Factura;
import ar.org.centro8.curso.java.web.aplicaciones.repositories.FacturaR;
import java.sql.Date;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/facturas/v1")
public class FacturaServicio {
	
	private FacturaR fr = new FacturaR(ConnectorMySQL.getConnection());
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String info () { return "<h1>Servicio de facturas.</h1>"; }
	
	@GET
	@Path("/add")
	@Produces(MediaType.TEXT_PLAIN)
	public String add(
				@QueryParam("letra")		char letra,
				@QueryParam("numero")		int numero,
				@QueryParam("fecha")		String fecha,
				@QueryParam("monto")		double monto,
				@QueryParam("idcliente")	int idCliente) {
		
		Factura factura = new Factura(
								letra,
								numero,
								Date.valueOf(fecha),
								monto,
								idCliente
		);
		
		fr.save(factura);
		
		return factura.getId() + "";
		
		// http://localhost:8081/AplicacionesWebClase10/
		//		webresources/facturas/v1/
		//		add?letra=C&numero=113&fecha=2019-05-02&monto=222&idcliente=9

	}
	
	@GET
	@Path("/remove")
	@Produces(MediaType.TEXT_PLAIN)
	public String remove(	@QueryParam("letra") char letra,
							@QueryParam("numero") int numero) {
		fr.remove(fr.getByLetraNumero(letra, numero));
		return "true";
		
		// http://localhost:8081/AplicacionesWebClase10/
		//		webresources/facturas/v1/remove?letra=C&numero=111
	}
	
	private static String lista = "";
	
	@GET
	@Path("/list")
	@Produces(MediaType.TEXT_PLAIN)
	public String list() {
		lista = "";
		fr.getAll().forEach(item -> lista += item + "\n");
		return lista;
		
		// http://localhost:8081/AplicacionesWebClase10/
		//		webresources/facturas/v1/list
	}
	
	
	@GET
	@Path("/list_by_idcliente")
	@Produces(MediaType.TEXT_PLAIN)
	public String listByIdCliente(@QueryParam("idcliente") int idCliente) {
		//if (idCliente == null) idCliente = "";
		lista = "";
		fr.getByCliente(idCliente).forEach(item -> lista += item + "\n");
		return lista;
		
		// http://localhost:8081/AplicacionesWebClase10/
		//		webresources/facturas/v1/list_by_idcliente?idcliente=112
	}
}
