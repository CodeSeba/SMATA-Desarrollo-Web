package ar.org.centro8.curso.java.web.aplicaciones.test;

import ar.org.centro8.curso.java.web.aplicaciones.connectors.ConnectorMySQL;
import ar.org.centro8.curso.java.web.aplicaciones.entities.Factura;
import ar.org.centro8.curso.java.web.aplicaciones.repositories.FacturaR;

public class TestRepositories2 {
	public static void main(String[] args) {
		FacturaR fr = new FacturaR(ConnectorMySQL.getConnection());
		//Factura factura = new Factura('a', 1001, java.sql.Date.valueOf("2019-04-24"), 5000.0, 1);
		
		//fr.save(factura);
		//System.out.println(factura);
		
		//fr.remove(fr.getAll().get(0));
		
		Factura factura = fr.getAll().get(0);
		factura.setMonto(200.0);
		fr.update(factura);
		
		fr.getAll().forEach(System.out::println);
	}
}
