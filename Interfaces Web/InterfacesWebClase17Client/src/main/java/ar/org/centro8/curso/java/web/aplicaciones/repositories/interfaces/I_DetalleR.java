package ar.org.centro8.curso.java.web.aplicaciones.repositories.interfaces;

import ar.org.centro8.curso.java.web.aplicaciones.entities.Articulo;
import ar.org.centro8.curso.java.web.aplicaciones.entities.Detalle;
import ar.org.centro8.curso.java.web.aplicaciones.entities.Factura;
import java.util.List;
import java.util.stream.Collectors;

public interface I_DetalleR extends I_GenericR<Detalle>{
	
	default Detalle getByFacturaArticulo(Factura factura, Articulo articulo){
		List<Detalle> lista = getAll()
								.stream()
								.filter(d -> d.getIdFactura()  == factura.getId() &&
											 d.getIdArticulo() == articulo.getId())
								.collect(Collectors.toList());
		return (lista == null || lista.isEmpty()) ? null : lista.get(0);
	}
    
	Detalle getByFacturaArticulo(char letra,int numero, int idArticulo);
	
    default List<Detalle> getByFactura(Factura factura){
		return getAll()
				.stream()
				.filter(d -> d.getIdFactura()  == factura.getId())
				.collect(Collectors.toList());
	}
    
	List<Detalle> getByFactura(char letra,int numero);
}