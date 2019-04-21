package ar.org.centro8.curso.java.web.aplicaciones.utils;

import ar.org.centro8.curso.java.web.aplicaciones.entities.ArticuloML;
import ar.org.centro8.curso.java.web.aplicaciones.repositories.ArticuloMLR;
import java.util.List;

public class TestMLR {
	public static void main(String[] args) {
		ArticuloMLR amlr = new ArticuloMLR();
		
		List<ArticuloML> lista = amlr.getByCriteria("motos");
		System.out.println("-----------------------------------------------------");
		System.out.println("Longitud de lista: " + lista.size());
		lista.forEach(System.out::println);
	}
}
