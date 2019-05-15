package ar.org.centro8.curso.java.web.aplicaciones.rest;

import ar.org.centro8.curso.java.web.aplicaciones.utils.ClientHttp;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ClientRest {

	public static void main(String[] args) {
		System.out.println(new ClientHttp().getHTML(
				//"http://localhost:8081/AplicacionesWebClase09/webresources/servicio/v1"
				"http://172.16.5.119:8081/AplicacionesClase09/webresources/servicio/v1/metodo1?nro1=78&nro2=22"
		));

		try {
			HttpURLConnection conn = (HttpURLConnection) new URL(
					"http://localhost:8081/AplicacionesWebClase09/webresources/servicio/v1"
			).openConnection();
		InputStream in = conn.getInputStream();
			int car;
			while ((car = in.read()) != -1) {
				System.out.println((char) car);
			}

			System.out.println("***************************************");
			conn.getHeaderFields().forEach((k, v) -> System.out.println(k + " --- " + v));
		} catch (Exception e) {	e.printStackTrace(); }
	}
}
