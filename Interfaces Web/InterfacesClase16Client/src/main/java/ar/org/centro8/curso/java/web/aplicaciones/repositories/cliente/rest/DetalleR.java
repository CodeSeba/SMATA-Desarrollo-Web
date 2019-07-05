package ar.org.centro8.curso.java.web.aplicaciones.repositories.cliente.rest;

import ar.org.centro8.curso.java.web.aplicaciones.entities.Detalle;
import ar.org.centro8.curso.java.web.aplicaciones.entities.Factura;
import ar.org.centro8.curso.java.web.aplicaciones.repositories.interfaces.I_DetalleR;
import ar.org.centro8.curso.java.web.aplicaciones.utils.ClientHttp;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DetalleR implements I_DetalleR{
	
	private String host;
	private String hostFactura;
	
	public DetalleR(String host, String hostFactura) {
		this.host = host;
		this.hostFactura = hostFactura;
	}

	@Override
	public Detalle getByFacturaArticulo(char letra, int numero, int idArticulo) {
		List<Detalle> lista = getByFactura(letra, numero)
								.stream()
								.filter(d -> d.getIdArticulo() == idArticulo)
								.collect(Collectors.toList());
		return (lista == null || lista.isEmpty()) ? new Detalle() : lista.get(0);
	}

	@Override
	public List<Detalle> getByFactura(char letra, int numero) {
		return getAll()
				.stream()
				.filter(d -> new FacturaR(host, hostFactura)
								.getAll()
								.stream()
								.filter(f -> f.getLetra()  == letra &&
											 f.getNumero() == numero)
								.allMatch(f -> d.getIdFactura() == f.getId()))
				.collect(Collectors.toList());
	}

	@Override
	public void save(Detalle e) {
		String url = host + "/add?idFactura=" + e.getIdFactura()
				+ "&idArticulo=" + e.getIdArticulo()
				+ "&cantidad=" + e.getCantidad()
				+ "&precioUnit=" + e.getPrecioUnit();
		String response = new ClientHttp().getHTML(url);
		System.out.println("dr.save: " + response.trim());
	}

	@Override
	public void remove(Detalle e) {
		String url = host + "/remove?idFactura=" + e.getIdFactura()
				+ "&idArticulo=" + e.getIdArticulo();
		String response = new ClientHttp().getHTML(url);
		System.out.println("dr.remove: " + response);
	}

	@Override
	public void update(Detalle e) {	}

	@Override
	public List<Detalle> getAll() {
		List<Detalle> lista = new ArrayList();
		String url = host + "/list";
		String texto = new ClientHttp().getHTML(url);
		// System.out.println(texto);
		String[] lineas = texto.split("\n");
		for (String linea : lineas) {
			linea = linea.substring(8, linea.length() - 2);
			String[] campos = linea.split(", ");
			
			int idFactura = -1;
			int idArticulo = -1;
			int cantidad = 0;
			double precioUnit = 0;

			try { idFactura		= Integer.parseInt(campos[0].substring(campos[0].indexOf("=") + 1));	} catch (Exception e) { }
			try { idArticulo	= Integer.parseInt(campos[1].substring(campos[1].indexOf("=") + 1));	} catch (Exception e) { }
			try { cantidad		= Integer.parseInt(campos[2].substring(campos[2].indexOf("=") + 1));	} catch (Exception e) { }
			try { precioUnit	= Double.valueOf(campos[3].substring(campos[3].indexOf("=")   + 1));		} catch (Exception e) { }

			lista.add(new Detalle(idFactura, idArticulo, cantidad, precioUnit));
		}
		return lista;
	}
}
