package ar.org.centro8.curso.java.web.aplicaciones.test;

import ar.org.centro8.curso.java.web.aplicaciones.connectors.ConnectorMySQL;
import ar.org.centro8.curso.java.web.aplicaciones.entities.Articulo;
import ar.org.centro8.curso.java.web.aplicaciones.entities.Cliente;
import ar.org.centro8.curso.java.web.aplicaciones.entities.Detalle;
import ar.org.centro8.curso.java.web.aplicaciones.enums.TipoDocumento;
import ar.org.centro8.curso.java.web.aplicaciones.repositories.ArticuloR;
import ar.org.centro8.curso.java.web.aplicaciones.repositories.ClienteR;

public class TestRepositories {
	public static void main(String[] args) {
		ClienteR cr = new ClienteR(ConnectorMySQL.getConnection());
		Cliente cliente = new Cliente(
				"Rojas",
				"Daniel",
				TipoDocumento.DNI,
				"12345678",
				"Medrano 162",
				"");
		//cr.save(cliente);
		//System.out.println(cliente);
		
		//cr.remove(cr.getById(2));
		
//		cliente = cr.getById(3);
//		if (cliente != null) {
//			cliente.setNombre("Javier");
//			cliente.setDireccion("maza 851");
//			cr.update(cliente);
//		} else { System.out.println("es nulo"); }
//		
		//cr.getAll().forEach(System.out::println);
		
		//System.out.println(cr.getById(8));
		//cr.getByApellido("vargaz").forEach(System.out::println);
		//cr.getLikeApellido("v").forEach(System.out::println);
		
		//cr.getLikeDireccion("med").forEach(System.out::println);
		//cr.getByApellidoNombre("", "s").forEach(System.out::println);
		
		//cr.getByTipoNumero(null, "111").forEach(System.out::println);
		//cr.getByTipoNumero(TipoDocumento.DNI, "111").forEach(System.out::println);
		
		
		ArticuloR ar = new ArticuloR(ConnectorMySQL.getConnection());
		Articulo articulo = new Articulo("espatula", 100.0, 120.0, 100, 50, 200);
		Articulo articulo2 = new Articulo("balde", 200.0, 240.0, 52, 50, 100);
		
		//ar.save(articulo);
		ar.save(articulo2);
		
		//ar.remove(articulo2);
		
		ar.getAll().forEach(System.out::println);
		
		System.out.println("**************************");
		System.out.println(ar.getById(8));
		
		System.out.println("**************************");
		Detalle detalle = new Detalle(1, 5, 1, 10);
		System.out.println(ar.getByDetalle(detalle));
		
		System.out.println("**************************");
		System.out.println("IdArticulo, Descripcion, Reponer");
		Articulo articulo3 = new Articulo("Tester", 20.0, 25.0, 5, 20, 50);
		ar.save(articulo3);
		
		Articulo articulo4 = new Articulo("Lima", 50.0, 60.0, 50, 30, 60);
		ar.save(articulo4);
		
		ar
			.getAll()
			.forEach(item ->
						System.out.println(item.getId() + " "
							+ item.getDescripcion() + " "
							+ ar.getReponerCantidad(item)));
		
		// System.out.println("IdArticulo, Descripcion, Reponer");
		
		ar.getLikeDescripcion("pi").forEach(System.out::println);
		
		
		System.out.println("**************************");
		System.out.println("Borrar un Articulo y Actualizar otro.");
		ar.remove(ar.getById(22));
		
		Articulo articulo5 = ar.getById(17);
		if (articulo5 != null) {
			articulo5.setDescripcion("Boligrafo");
			articulo5.setPrecio(56.0);
			ar.update(articulo5);
		}
		
		ar.getAll().forEach(System.out::println);
		
	}
}
