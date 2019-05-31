package ar.org.centro8.curso.java.web.aplicaciones.repositories;
import ar.org.centro8.curso.java.web.aplicaciones.entities.Articulo;
import ar.org.centro8.curso.java.web.aplicaciones.entities.Detalle;
import ar.org.centro8.curso.java.web.aplicaciones.repositories.interfaces.I_ArticuloR;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
public class ArticuloR implements I_ArticuloR{
    private Connection conn;
    public ArticuloR (Connection conn) { this.conn = conn; }
    @Override public Articulo getById(int idArticulo) {
       List<Articulo>lista =getAll()
                .stream()
                .filter(a->a.getId()==idArticulo)
                .collect(Collectors.toList());
        return (lista==null || lista.isEmpty())?null:lista.get(0);
    }
    @Override public Articulo getByDetalle(Detalle detalle) {
        return getById(detalle.getIdArticulo());
    }
    @Override public int getReponerCantidad(Articulo articulo) {
        return getReponerCantidad(articulo.getId());
    }
    @Override public int getReponerCantidad(int idArticulo) {
        int cantidad=0;
        Articulo articulo=getById(idArticulo);
        if(articulo.getStock()<articulo.getStockMin()) 
            cantidad=articulo.getStockMax()-articulo.getStock();
        return cantidad;
    }
    @Override public List<Articulo> getByDescripcion(String descripcion) {
        return getAll()
                .stream()
                .filter(a->a.getDescripcion().equalsIgnoreCase(descripcion))
                .collect(Collectors.toList());
    }
    @Override public List<Articulo> getLikeDescripcion(String descripcion) {
        return getAll()
                .stream()
                .filter(a->a
                        .getDescripcion()
                        .toLowerCase()
                        .contains(descripcion.toLowerCase()))
                .collect(Collectors.toList());
    }
    @Override public void save(Articulo e) {
        if(e==null) return;
        String query="insert into articulos "
                + "(descripcion,costo,precio,stock,stockMin,stockMax) "
                + "values (?,?,?,?,?,?)";
        try (PreparedStatement ps=conn.prepareStatement(query,1)) {
            ps.setString(1, e.getDescripcion());
            ps.setDouble(2, e.getCosto());
            ps.setDouble(3, e.getPrecio());
            ps.setInt(4, e.getStock());
            ps.setInt(5, e.getStockMin());
            ps.setInt(6, e.getStockMax());
            ps.execute();
            ResultSet rs=ps.getGeneratedKeys();
            if(rs.next()) e.setId(rs.getInt(1));
        } catch (Exception ex) { ex.printStackTrace(); }
    }
    @Override public void remove(Articulo e) {
        if(e==null) return;
        try (PreparedStatement ps=conn
                .prepareStatement("delete from articulos where id=?")){
            ps.setInt(1, e.getId());
            ps.execute();
        } catch (Exception ex) { ex.printStackTrace(); }
    }
    @Override public void update(Articulo e) {
        if(e==null) return;
        try (PreparedStatement ps=conn
                .prepareStatement(
                   "update articulos set descripcion=?, costo=?, precio=?, "
                           + "stock=?, stockMin=?, stockMax=? where id=?"
                )){
            ps.setString(1, e.getDescripcion());
            ps.setDouble(2, e.getCosto());
            ps.setDouble(3, e.getPrecio());
            ps.setInt(4, e.getStock());
            ps.setInt(5, e.getStockMin());
            ps.setInt(6, e.getStockMax());
            ps.setInt(7, e.getId());
            ps.execute();
        } catch (Exception ex) { ex.printStackTrace(); }
    }
    @Override public List<Articulo> getAll() {
        List<Articulo>lista=new ArrayList();
        String query="select * from articulos";
        try ( ResultSet rs=conn.prepareStatement(query).executeQuery()) {
            while(rs.next()){
                lista.add(new Articulo(
                        rs.getInt("id"),
                        rs.getString("descripcion"), 
                        rs.getDouble("costo"), 
                        rs.getDouble("precio"), 
                        rs.getInt("stock"), 
                        rs.getInt("stockMin"), 
                        rs.getInt("stockMax")
                ));
            }
        } catch (Exception e) { e.printStackTrace(); }
        return lista;
    }    
    
}