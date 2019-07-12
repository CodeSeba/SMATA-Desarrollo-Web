package ar.org.centro8.curso.java.web.aplicaciones.test;

import ar.org.centro8.curso.java.web.aplicaciones.entities.Detalle;
import ar.org.centro8.curso.java.web.aplicaciones.repositories.jpa.DetalleR;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TestRepository {

	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAPU");
		
//		ArticuloR ar = new ArticuloR(emf);
//
//		Articulo articulo = new Articulo("Monitor", 2000.00, 3000.00, 20, 20, 30);
//
//		ar.save(articulo);
//		System.out.println(articulo);
//
//		//ar.getAll().forEach(System.out::println);
//		ar.getLikeDescripcion("pa").forEach(System.out::println);
//
//		ClienteR cr = new ClienteR(emf);
//		
//		Cliente cliente = new Cliente("Ana", "Sosa", TipoDocumento.DNI.toString(), "77776676", "Medrano 162", "nada");
//		System.out.println(cliente);
//		cr.save(cliente);
//		
//		System.out.println("+++++++++++++++++++++++++++++++");
//		cr.getAll().forEach(System.out::println);
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

		DetalleR dr = new DetalleR(emf);
		
		Detalle detalle = new Detalle(1, 4);
		detalle.setCantidad(10);
		detalle.setPrecioUnit(56);
		
		System.out.println("Antes de Save: " + detalle);
		
		dr.save(detalle);
		
		System.out.println("Despues de Save: " + detalle);
		
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
		dr.getAll().forEach(System.out::println);
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
		
		emf.close();
	}
}
