package ar.org.centro8.curso.java.web.aplicaciones.repositories.interfaces;
import ar.org.centro8.curso.java.web.aplicaciones.entities.Articulo;
import ar.org.centro8.curso.java.web.aplicaciones.entities.Detalle;
import java.util.List;
import java.util.stream.Collectors;
public interface I_ArticuloR extends I_GenericR<Articulo> {
    default public Articulo getById(int idArticulo) {
        List<Articulo>lista =getAll()
                .stream()
                .filter(a->a.getId()==idArticulo)
                .collect(Collectors.toList());
        return (lista==null || lista.isEmpty())?null:lista.get(0);
    }
    default public Articulo getByDetalle(Detalle detalle) {
        return getById(detalle.getIdArticulo());
    }
    default public int getReponerCantidad(Articulo articulo) {
        return getReponerCantidad(articulo.getId());
    }
    default public int getReponerCantidad(int idArticulo) {
        int cantidad=0;
        Articulo articulo=getById(idArticulo);
        if(articulo.getStock()<articulo.getStockMin()) 
            cantidad=articulo.getStockMax()-articulo.getStock();
        return cantidad;
    }
    default public List<Articulo> getByDescripcion(String descripcion) {
        return getAll()
                .stream()
                .filter(a->a.getDescripcion().equalsIgnoreCase(descripcion))
                .collect(Collectors.toList());
    }
    default public List<Articulo> getLikeDescripcion(String descripcion) {
        return getAll()
                .stream()
                .filter(a->a
                        .getDescripcion()
                        .toLowerCase()
                        .contains(descripcion.toLowerCase()))
                .collect(Collectors.toList());
    }
}