package ar.org.centro8.curso.java.web.aplicaciones.repositories.interfaces;

import ar.org.centro8.curso.java.web.aplicaciones.entities.Articulo;
import ar.org.centro8.curso.java.web.aplicaciones.entities.Detalle;
import ar.org.centro8.curso.java.web.aplicaciones.entities.Factura;
import java.util.List;

public interface I_ArticuloR extends I_GenericR<Articulo>{
	Articulo getById(int id);
	Articulo getByDetalle(Detalle detalle);
	int getReponerCantidad(Articulo articulo);
	int getReponerCantidad(int idArticulo);
	List<Articulo> getByDescripcion(String descripcion);
	List<Articulo> getLikeDescripcion(String descripcion);
}
