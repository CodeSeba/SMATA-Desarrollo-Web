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
        ar=new ArticuloR("http://172.16.23.111:8081/Servidor/webresources/articulos/v1");
        mensaje="";
        buscar="";
    }
    public void save(){
        if(validar()){
            ar.save(articulo);
            mensaje="Se ingreso el articulo id= "+articulo.getId();
        }else{
            mensaje="No se pudo ingresar el articulo";
        }
    }
    private boolean validar(){
        return true;
    }
    public List<Articulo>getAll(){
        return ar.getAll();
    }
    public List<Articulo>getLikeDescripcion(){
        return ar.getLikeDescripcion(buscar);
    }
}
