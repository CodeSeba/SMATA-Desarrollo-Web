package ar.org.centro8.curso.java.web.aplicaciones.rest.servicios;

import ar.org.centro8.curso.java.web.aplicaciones.connectors.ConnectorMySQL;
import ar.org.centro8.curso.java.web.aplicaciones.entities.Cliente;
import ar.org.centro8.curso.java.web.aplicaciones.enums.TipoDocumento;
import ar.org.centro8.curso.java.web.aplicaciones.repositories.ClienteR;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/clientes/v1")
public class ClienteServicio {
	
	private ClienteR cr = new ClienteR(ConnectorMySQL.getConnection());
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String info () { return "<h1>Servicio de clientes.</h1>"; }
	
	@GET
	@Path("/add")
	@Produces(MediaType.TEXT_PLAIN)
	public String add(
				@QueryParam("letra")			String nombre,
				@QueryParam("apellido")			String apellido,
				@QueryParam("tipoDocumento")	String tipoDocumento,
				@QueryParam("numeroDocumento")	String numeroDocumento,
				@QueryParam("direccion")		String direccion,
				@QueryParam("comentarios")		String comentarios) {
		
		Cliente cliente = new Cliente(
								nombre,
								apellido,
								TipoDocumento.valueOf(tipoDocumento),
								numeroDocumento,
								direccion,
								comentarios
		);
		
		cr.save(cliente);
		
		return cliente.getId() + "";
		
		// http://localhost:8081/AplicacionesWebClase09/
		//		webresources/clientes/v1/
		//		add?nombre=Sebas&apellido=Sauce&tipoDocumento=DNI&
		//			numeroDocumento=12345678&direccion=casita&comentarios=todobien
	}
	
	@GET
	@Path("/remove")
	@Produces(MediaType.TEXT_PLAIN)
	public String remove(@QueryParam("id") int id) {
		cr.remove(cr.getById(id));
		return "true";
		
		// http://localhost:8081/AplicacionesWebClase09/
		//		webresources/clientes/v1/remove?id=6
	}
	
	private static String lista = "";
	
	@GET
	@Path("/list")
	@Produces(MediaType.TEXT_PLAIN)
	public String list() {
		lista = "";
		cr.getAll().forEach(item -> lista += item + "\n");
		return lista;
		
		// http://localhost:8081/AplicacionesWebClase09/
		//		webresources/clientes/v1/list
	}
	
	
	@GET
	@Path("/list_like_apellido")
	@Produces(MediaType.TEXT_PLAIN)
	public String listLikeApellido(@QueryParam("apellido") String apellido) {
		if (apellido == null) apellido = "";
		lista = "";
		cr.getLikeApellido(apellido).forEach(item -> lista += item + "\n");
		return lista;
		
		// http://localhost:8081/AplicacionesWebClase09/
		//		webresources/clientes/v1/list_like_apellido?apellido=R
	}
}
