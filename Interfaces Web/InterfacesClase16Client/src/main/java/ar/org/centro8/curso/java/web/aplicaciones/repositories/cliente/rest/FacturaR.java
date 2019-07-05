package ar.org.centro8.curso.java.web.aplicaciones.repositories.cliente.rest;

import ar.org.centro8.curso.java.web.aplicaciones.entities.Detalle;
import ar.org.centro8.curso.java.web.aplicaciones.entities.Factura;
import ar.org.centro8.curso.java.web.aplicaciones.repositories.interfaces.I_DetalleR;
import ar.org.centro8.curso.java.web.aplicaciones.repositories.interfaces.I_FacturaR;
import ar.org.centro8.curso.java.web.aplicaciones.utils.ClientHttp;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FacturaR implements I_FacturaR{

	private String host;
	private String hostDetalle;
	
	public FacturaR(String host, String hostDetalle) {
		this.host			= host;
		this.hostDetalle	= hostDetalle;
	}

	@Override
	public List<Factura> getByArticulo(int id) {
		return getAll()
				.stream()
				.filter(f -> new DetalleR(host, hostDetalle)
								.getAll()
								.stream()
								.filter(d -> d.getIdArticulo()	== id)
								.allMatch(d -> d.getIdFactura() == f.getId()))
				.collect(Collectors.toList());
	}

	@Override
	public void save(Factura e) {
		String url = host + "/add?letra=" + e.getLetra()
				+ "&numero=" + e.getNumero()
				+ "&fecha=" + e.getFecha()
				+ "&monto=" + e.getMonto()
				+ "&idCliente=" + e.getIdCliente();
		String id = new ClientHttp().getHTML(url);
		id = id.trim();
		System.out.println("fr.save: "+id);
		e.setId(Integer.parseInt(id));
	}

	@Override
	public void remove(Factura e) {
		String url = host + "/remove?id=" + e.getId();
		String response = new ClientHttp().getHTML(url);
		System.out.println(response);
	}

	@Override
	public void update(Factura e) {	}

	@Override
	public List<Factura> getAll() {
		List<Factura> lista = new ArrayList();
		String url = host + "/list";
		String texto = new ClientHttp().getHTML(url);
		// System.out.println(texto);
		String[] lineas = texto.split("\n");
		for (String linea : lineas) {
			linea = linea.substring(8, linea.length() - 2);
			String[] campos = linea.split(", ");
			int id = -1;
			char letra = ' ';
			int numero = -1;
			Date fecha = null;
			double monto = 0;
			int idCliente = -1;

			try { id		= Integer.parseInt(campos[0].substring(campos[0].indexOf("=") + 1));	} catch (Exception e) { }
			try { letra		= campos[1].substring(campos[1].indexOf("=") + 1).charAt(0);			} catch (Exception e) { }
			try { numero	= Integer.parseInt(campos[2].substring(campos[2].indexOf("=") + 1));	} catch (Exception e) { }
			try { fecha		= Date.valueOf(campos[3].substring(campos[3].indexOf("=") + 1));		} catch (Exception e) { }
			try { monto		= Double.parseDouble(campos[4].substring(campos[4].indexOf("=") + 1));	} catch (Exception e) { }
			try { idCliente = Integer.parseInt(campos[5].substring(campos[5].indexOf("=") + 1));	} catch (Exception e) { }

			lista.add(new Factura(id, letra, numero, fecha, monto, idCliente));
		}
		return lista;
	}
}
