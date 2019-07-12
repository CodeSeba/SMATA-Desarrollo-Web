package ar.org.centro8.curso.java.web.aplicaciones.managed.beans;
import ar.org.centro8.curso.java.web.aplicaciones.entities.Articulo;
import ar.org.centro8.curso.java.web.aplicaciones.repositories.cliente.rest.ArticuloR;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
@ManagedBean(name="articuloMB")
@SessionScoped
public class ArticuloMB {
    private Articulo articulo;
    private ArticuloR ar;
    private String mensaje;
    private String buscar;
    public ArticuloMB() {
        articulo=new Articulo();
        ar=new ArticuloR("http://172.16.1.245:8081/Servidor/webresources/articulos/v1");
        mensaje="";
        buscar="";
    }
    public void save(){
        if(validar()){
            ar.save(articulo);
            mensaje="Se ingreso el articulo id= "+articulo.getId();
            articulo=new Articulo();
        }
    }
    private boolean validar(){
        if(articulo.getStockMin()>=articulo.getStockMax()){
            mensaje="El stock Mínimo debe ser menor al stock Máximo";
            return false;
        }
        if(articulo.getDescripcion().contains(" ")){
            articulo.setDescripcion(articulo.getDescripcion().replaceAll(" ", "_"));
        }
        return true;
    }
    public List<Articulo>getAll(){
        return ar.getAll();
    }
    public List<Articulo>getLikeDescripcion(){
        return ar.getLikeDescripcion(buscar);
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public ArticuloR getAr() {
        return ar;
    }

    public void setAr(ArticuloR ar) {
        this.ar = ar;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getBuscar() {
        return buscar;
    }

    public void setBuscar(String buscar) {
        this.buscar = buscar;
    }
    
    
}
