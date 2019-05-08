package ar.org.centro8.curso.java.web.aplicaciones.test;

import ar.org.centro8.curso.java.web.aplicaciones.connectors.ConnectorMySQL;
import ar.org.centro8.curso.java.web.aplicaciones.entities.Detalle;
import ar.org.centro8.curso.java.web.aplicaciones.repositories.DetalleR;
import ar.org.centro8.curso.java.web.aplicaciones.repositories.FacturaR;

public class TestRepositoryDetalleR {
	public static void main(String[] args) {
		
		DetalleR dr = new DetalleR(ConnectorMySQL.getConnection());
		
		Detalle detalle = new Detalle();
//		detalle = new Detalle(2, 2, 22, 30);
//		dr.save(detalle);
//		System.out.println(detalle);
//		
//		detalle = new Detalle(3, 3, 44, 50);
//		dr.save(detalle);
//		detalle = new Detalle(4, 4, 55, 60);
//		dr.save(detalle);
		
		dr.getAll().forEach(System.out::println);
		
		FacturaR fr = new FacturaR(ConnectorMySQL.getConnection());
		
//		detalle = dr.getByFactura(fr.getByLetraNumero('A',453)).get(0);
//		
//		System.out.println("++++++++++++++++++++++++++");
//		dr.remove(detalle);
//		
//		dr.getAll().forEach(System.out::println);

		
		
	}
}
