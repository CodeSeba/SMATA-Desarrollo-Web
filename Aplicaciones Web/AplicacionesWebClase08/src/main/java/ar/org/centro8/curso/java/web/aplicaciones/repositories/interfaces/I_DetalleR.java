package ar.org.centro8.curso.java.web.aplicaciones.repositories.interfaces;

import ar.org.centro8.curso.java.web.aplicaciones.entities.Articulo;
import ar.org.centro8.curso.java.web.aplicaciones.entities.Detalle;
import ar.org.centro8.curso.java.web.aplicaciones.entities.Factura;
import java.util.List;

public interface I_DetalleR extends I_GenericR<Detalle>{
	Detalle getByFacturaArticulo(Factura factura, Articulo articulo);
	Detalle getByFacturaArticulo(char letra, int numero, int idArticulo);
	List<Detalle> getByFactura(Factura factura);
	List<Detalle> getByFactura(char letra, int numero);
}
