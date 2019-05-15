package ar.org.centro8.curso.java.web.aplicaciones.rest;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("servicio/v1")
public class TestRest {

	@Context
	private UriInfo context;

	/**
	 * Creates a new instance of TestRest
	 */
	public TestRest() {
	}

	@GET
    @Produces(MediaType.TEXT_HTML)
	public String getText() {
		// http://localhost:8081/AplicacionesWebClase09/webresources/servicio/v1
		return "<h1>Se ejecuto un servicio!</h1>";
	}
	
	@GET
    @Produces(MediaType.TEXT_PLAIN)
	@Path("/metodo1")
	public String metodo1(@QueryParam("nro1") int nro1, @QueryParam("nro2") int nro2) {
		// http://localhost:8081/AplicacionesWebClase09/webresources/servicio/v1/metodo1
		int suma = nro1 + nro2;
		return suma+"";
	}


//	@PUT
//    @Consumes(MediaType.TEXT_PLAIN)
//	public void putText(String content) {
//	}
}
