package ar.org.centro8.curso.java.web.aplicaciones.test;

import ar.org.centro8.curso.java.web.aplicaciones.entities.Articulo;
import ar.org.centro8.curso.java.web.aplicaciones.entities.Cliente;
import ar.org.centro8.curso.java.web.aplicaciones.entities.Factura;
import ar.org.centro8.curso.java.web.aplicaciones.enums.TipoDocumento;
import ar.org.centro8.curso.java.web.aplicaciones.repositories.jpa.ArticuloR;
import ar.org.centro8.curso.java.web.aplicaciones.repositories.jpa.ClienteR;
import ar.org.centro8.curso.java.web.aplicaciones.repositories.jpa.FacturaR;
import java.util.Date;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TestRepository {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAPU");
		ArticuloR ar = new ArticuloR(emf);

		Articulo articulo = new Articulo("Monitor", 2000.00, 3000.00, 20, 20, 30);

		ar.save(articulo);
		System.out.println(articulo);

		//ar.getAll().forEach(System.out::println);
		ar.getLikeDescripcion("pa").forEach(System.out::println);

		ClienteR cr = new ClienteR(emf);
		
		Cliente cliente = new Cliente("Ana", "Sosa", TipoDocumento.DNI.toString(), "77776676", "Medrano 162", "nada");
		System.out.println(cliente);
		cr.save(cliente);
		
		System.out.println("+++++++++++++++++++++++++++++++");
		cr.getAll().forEach(System.out::println);
//		
//		System.out.println("+++++++++++++++++++++++++++++++++++++++");
//		System.out.println(cr.getByFactura('a', 2002));
//		
//		System.out.println("+++++++++++++++++++++++++++++++++++++++");
//		FacturaR fr = new FacturaR(emf);
//		
//		Factura factura = new Factura('a', 2003, new Date(), 400.0f, cliente);
//		fr.save(factura);
//		
//		fr.getAll().forEach(System.out::println);
		
		emf.close();
	}
}
