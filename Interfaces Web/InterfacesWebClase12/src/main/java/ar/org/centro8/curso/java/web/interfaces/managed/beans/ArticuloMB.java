package ar.org.centro8.curso.java.web.interfaces.managed.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import ar.org.centro8.curso.java.web.aplicaciones.entities.Articulo;
import ar.org.centro8.curso.java.web.aplicaciones.repositories.ArticuloR;
import ar.org.centro8.curso.java.web.aplicaciones.connectors.ConnectorMySQL;
import java.util.List;


@ManagedBean(name = "articuloMB")
@SessionScoped
public class ArticuloMB {

	private Articulo articulo;
	private ArticuloR ar;
	private String mensaje;
	private String buscar;

	public ArticuloMB() {
		articulo = new Articulo();
		ar = new ArticuloR(ConnectorMySQL.getConnection());
		mensaje = "";
		buscar = "";
	}

	public void save() {
		ar.save(articulo);
		mensaje = "Se guardo el articulo id= " + articulo.getId();
		articulo = new Articulo();
	}

	public List<Articulo> getAll() {
		return ar.getAll();
	}

	public List<Articulo> getLikeDescripcion() {
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
