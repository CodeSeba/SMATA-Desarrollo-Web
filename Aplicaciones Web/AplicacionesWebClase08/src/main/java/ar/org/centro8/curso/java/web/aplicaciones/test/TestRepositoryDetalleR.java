package ar.org.centro8.curso.java.web.aplicaciones.test;

import ar.org.centro8.curso.java.web.aplicaciones.connectors.ConnectorMySQL;
import ar.org.centro8.curso.java.web.aplicaciones.entities.Detalle;
import ar.org.centro8.curso.java.web.aplicaciones.repositories.DetalleR;

public class TestRepositoryDetalleR {
	public static void main(String[] args) {
		
		DetalleR dr = new DetalleR(ConnectorMySQL.getConnection());
		
		Detalle detalle = new Detalle(1, 1, 22, 30);
		
		dr.save(detalle);
	}
}
