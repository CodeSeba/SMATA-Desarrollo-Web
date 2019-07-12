package ar.org.centro8.curso.java.web.services.rest;

import ar.org.centro8.curso.java.web.aplicaciones.entities.Cliente;
import ar.org.centro8.curso.java.web.aplicaciones.repositories.interfaces.I_ClienteR;
import ar.org.centro8.curso.java.web.aplicaciones.repositories.jpa.ClienteR;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Path("clientes/v1")
public class ClienteService {
	
	I_ClienteR cr;

	public ClienteService() {
		cr = new ClienteR(Persistence.createEntityManagerFactory("JPAPU"));
	}

	@GET
	public String info(){
		return "Servicio de clientes.";
	}
	
	@GET
	@Path("/add")
	public String add(
					@QueryParam("nombre")			String nombre,
					@QueryParam("apellido")			String apellido,
					@QueryParam("tipoDocumento")	String tipoDocumento,
					@QueryParam("numeroDocumento")	String numeroDocumento,
					@QueryParam("direccion")		String direccion,
					@QueryParam("comentarios")		String comentarios){
		
		Cliente cliente = new Cliente(
								nombre
								,apellido
								,tipoDocumento
								,numeroDocumento
								,direccion
								,comentarios);
		cr.save(cliente);
		
		return cliente.getId() + "";
	}
	
	@GET
	@Path("/remove")
	public String add(@QueryParam("id") String id){
		
		cr.remove(cr.getById(Integer.parseInt(id)));
		
		return "True/False";
	}
	
//	@GET
//	@Path("/add")
//	public String add(
//					@QueryParam("nombre")			String nombre,
//					@QueryParam("apellido")			String apellido,
//					@QueryParam("tipoDocumento")	String tipoDocumento,
//					@QueryParam("numeroDocumento")	String numeroDocumento,
//					@QueryParam("direccion")		String direccion,
//					@QueryParam("comentarios")		String comentarios){
//		
//		Cliente cliente = new Cliente(
//								nombre
//								,apellido
//								,tipoDocumento
//								,numeroDocumento
//								,direccion
//								,comentarios);
//		cr.save(cliente);
//		
//		return cliente.getId() + "";
//	}
	
}
