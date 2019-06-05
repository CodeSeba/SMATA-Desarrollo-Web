package ar.org.centro8.curso.java.web.aplicaciones.test;

import ar.org.centro8.curso.java.web.aplicaciones.utils.ClientHttp;

public class TestClientHTTP {
	public static void main(String[] args) {
		String url = "https://listado.mercadolibre.com.ar/bicicleta#D[A:bicicleta]";
		String html = new ClientHttp().getHTML(url);
		System.out.println(html);
	}
}
