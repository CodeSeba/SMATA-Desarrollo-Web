package ar.org.centro8.curso.java.web.aplicaciones.repositories.interfaces;

import ar.org.centro8.curso.java.web.aplicaciones.entities.Articulo;
import ar.org.centro8.curso.java.web.aplicaciones.entities.Detalle;
import java.util.List;
import java.util.stream.Collectors;

public interface I_ArticuloR extends I_GenericR<Articulo> {

	default Articulo getById(int idArticulo) {
		List<Articulo> lista = getAll()
				.stream()
				.filter(a -> a.getId() == idArticulo)
				.collect(Collectors.toList());
		return (lista.isEmpty()) ? new Articulo() : lista.get(0);
	}

	default Articulo getByDetalle(Detalle detalle) {
		return getById(detalle.getArticulo().getId());
	}

	default int getReponerCantidad(Articulo articulo) {
		int cantidad = 0;
		if (articulo.getStock() < articulo.getStockMin()) {
			cantidad = articulo.getStockMax() - articulo.getStock();
		}
		return cantidad;
	}

	default int getReponerCantidad(int idArticulo) {
		return getReponerCantidad(getById(idArticulo));
	}

	default List<Articulo> getByDescripcion(String descripcion) {
		return getAll()
				.stream()
				.filter(a -> a.getDescripcion().equalsIgnoreCase(descripcion))
				.collect(Collectors.toList());
	}

	default List<Articulo> getLikeDescripcion(String descripcion) {
		return getAll()
				.stream()
				.filter(a -> a.getDescripcion().toLowerCase().contains(descripcion.toLowerCase()))
				.collect(Collectors.toList());
	}
}
