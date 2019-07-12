package ar.org.centro8.curso.java.web.aplicaciones.repositories.interfaces;
import ar.org.centro8.curso.java.web.aplicaciones.entities.Articulo;
import ar.org.centro8.curso.java.web.aplicaciones.entities.Detalle;
import ar.org.centro8.curso.java.web.aplicaciones.entities.Factura;
import java.util.List;
import java.util.stream.Collectors;
public interface I_DetalleR extends I_GenericR<Detalle>{
	
	default Detalle getByFacturaArticulo(Factura factura, Articulo articulo){
		return getByFacturaArticulo(factura.getLetra(), factura.getNumero(), articulo.getId());
	}
	
	default Detalle getByFacturaArticulo(char letra,int numero, int idArticulo){
		List<Detalle> lista = getAll()
								.stream()
								.filter(d -> d.getFactura().getLetra()	== letra	&&
											 d.getFactura().getNumero()	== numero	&&
											 d.getArticulo().getId()	== idArticulo)
								.collect(Collectors.toList());
		return (lista == null || lista.isEmpty()) ? new Detalle() : lista.get(0);
	}
	
	default List<Detalle> getByFactura(Factura factura){
		return getByFactura(factura.getLetra(), factura.getNumero());
	}
	
	default List<Detalle> getByFactura(char letra,int numero){
		return getAll()
				.stream()
				.filter(d -> d.getFactura().getLetra()  == letra &&
							 d.getFactura().getNumero() == numero)
				.collect(Collectors.toList());
	}
}