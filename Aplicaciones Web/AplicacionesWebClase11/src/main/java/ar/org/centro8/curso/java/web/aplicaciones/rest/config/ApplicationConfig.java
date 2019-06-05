package ar.org.centro8.curso.java.web.aplicaciones.rest.config;

import java.util.Set;
import javax.ws.rs.core.Application;

@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> resources = new java.util.HashSet<>();
		addRestResourceClasses(resources);
		return resources;
	}

	private void addRestResourceClasses(Set<Class<?>> resources) {
		resources.add(ar.org.centro8.curso.java.web.aplicaciones.rest.TestRest.class);
		resources.add(ar.org.centro8.curso.java.web.aplicaciones.rest.servicios.ArticuloServicio.class);
		resources.add(ar.org.centro8.curso.java.web.aplicaciones.rest.servicios.ClienteServicio.class);
		resources.add(ar.org.centro8.curso.java.web.aplicaciones.rest.servicios.DetalleServicio.class);
		resources.add(ar.org.centro8.curso.java.web.aplicaciones.rest.servicios.FacturaServicio.class);
	}
	
}
