package ar.org.centro8.curso.java.web.aplicaciones.repositories.interfaces;

import ar.org.centro8.curso.java.web.aplicaciones.entities.Articulo;
import ar.org.centro8.curso.java.web.aplicaciones.entities.Cliente;
import ar.org.centro8.curso.java.web.aplicaciones.entities.Factura;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

public interface I_FacturaR extends I_GenericR<Factura> {
    
	default Factura getByLetraNumero(char letra,int numero) {
		List<Factura> lista = getAll()
								.stream()
								.filter(f -> f.getLetra() == letra && f.getNumero() == numero)
								.collect(Collectors.toList());
		return (lista == null || lista.isEmpty()) ? new Factura() : lista.get(0);
	}
	
	default List<Factura>getByFecha(Date date) {
		return getAll()
				.stream()
				.filter(f ->
						f.getFecha().getYear()	== date.getYear()	&&
						f.getFecha().getMonth()	== date.getMonth()	&&
						f.getFecha().getDay()	== date.getDay())
				.collect(Collectors.toList());
	}
	
	default List<Factura>getByCliente(Cliente cliente) {
		return getByCliente(cliente.getId());
	}
	
	default List<Factura>getByCliente(int id) {
		return getAll()
				.stream()
				.filter(f -> f.getIdCliente().getId() == id)
				.collect(Collectors.toList());
	}
	
	List<Factura>getByArticulo(Articulo articulo);
		
	List<Factura>getByArticulo(int id);
}