package ar.org.centro8.curso.java.web.aplicaciones.test;

import ar.org.centro8.curso.java.web.aplicaciones.entities.Articulo;
import ar.org.centro8.curso.java.web.aplicaciones.repositories.rest.ArticuloR;

public class TestRepository {
	public static void main(String[] args) {
		String url = "http://172.16.20.21:8081/Servidor/webresources/articulos/v1";
		ArticuloR ar = new ArticuloR(url);
		Articulo articulo = new Articulo("BarrileteCosmico", 9969.99, 9999.99, 10, 20, 30);
		ar.save(articulo);
		System.out.println(articulo);
		
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
		ar.getAll().forEach(System.out::println);
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
	}
}
