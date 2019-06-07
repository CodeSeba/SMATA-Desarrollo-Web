package ar.org.centro8.curso.java.web.aplicaciones.repositories.interfaces;

import ar.org.centro8.curso.java.web.aplicaciones.entities.Articulo;
import ar.org.centro8.curso.java.web.aplicaciones.entities.Detalle;
import java.util.List;
import java.util.stream.Collectors;

public interface I_ArticuloR extends I_GenericR<Articulo> {

	default public Articulo getById(int id) {
		List<Articulo> lista = getAll()
				.stream()
				.filter(a -> a.getId() == id)
				.collect(Collectors.toList());
		return (lista.isEmpty() || lista == null) ? null : lista.get(0);
	}

	default public List<Articulo> getByDescripcion(String descripcion) {
		return getAll()
				.stream()
				.filter(a -> a.getDescripcion().equalsIgnoreCase(descripcion))
				.collect(Collectors.toList());
	}

	default public List<Articulo> getLikeDescripcion(String descripcion) {
		return getAll()
				.stream()
				.filter(a -> a.getDescripcion().toLowerCase().contains(descripcion.toLowerCase()))
				.collect(Collectors.toList());
	}

	default public int getReponerCantidad(Articulo articulo) {
		return (articulo.getStock() < articulo.getStockMin())
				? (articulo.getStockMax() - articulo.getStock())
				: 0;
	}

	default public int getReponerCantidad(int idArticulo) {
		return getReponerCantidad(getById(idArticulo));
	}

	default public Articulo getByDetalle(Detalle detalle) {
		return getById(detalle.getIdArticulo());
	}
}
