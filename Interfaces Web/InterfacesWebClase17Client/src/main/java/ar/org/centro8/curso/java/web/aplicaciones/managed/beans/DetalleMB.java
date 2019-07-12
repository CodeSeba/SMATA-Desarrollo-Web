package ar.org.centro8.curso.java.web.aplicaciones.managed.beans;

import ar.org.centro8.curso.java.web.aplicaciones.entities.Detalle;
import ar.org.centro8.curso.java.web.aplicaciones.repositories.cliente.rest.ArticuloR;
import ar.org.centro8.curso.java.web.aplicaciones.repositories.cliente.rest.DetalleR;
import ar.org.centro8.curso.java.web.aplicaciones.repositories.cliente.rest.FacturaR;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="detalleMB")
@SessionScoped
public class DetalleMB {
	
	private Detalle		detalle;
	private DetalleR	dr;
	private FacturaR	fr;
	private ArticuloR	ar;
	private String		mensaje;
	private String		buscarFactura;
	private String		buscarArticulo;

	public DetalleMB() {
		detalle			= new Detalle();
        dr				= new DetalleR(
								"http://172.16.0.55:8081/Servidor/webresources/detalles/v1",
								"http://172.16.0.55:8081/Servidor/webresources/facturas/v1");
		fr				= new FacturaR(
								"http://172.16.0.55:8081/Servidor/webresources/facturas/v1",
								"http://172.16.0.55:8081/Servidor/webresources/detalles/v1");
		ar				= new ArticuloR("http://172.16.0.55:8081/Servidor/webresources/articulos/v1");
        mensaje			= "";
        buscarFactura	= "";
		buscarArticulo	= "";
	}
	
	public void save() {
		if (validar()){
			dr.save(detalle);
			mensaje = "Se dio de alta el detalle para la factura id: " + detalle.toString();
		} else mensaje = "No se dio de alta la factura.";
	}
	
	private boolean validar() {
		return true;
	}
	
	public List<Detalle> getAll() {
		return dr.getAll();
	}

	public Detalle getByFacturaArticulo() {
		return dr.getByFacturaArticulo(
					fr.getById(Integer.parseInt(buscarFactura)),
					ar.getById(Integer.parseInt(buscarArticulo)));
	}
	
	
	public Detalle getDetalle() {
		return detalle;
	}

	public void setDetalle(Detalle detalle) {
		this.detalle = detalle;
	}

	public DetalleR getDr() {
		return dr;
	}

	public void setDr(DetalleR dr) {
		this.dr = dr;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public FacturaR getFr() {
		return fr;
	}

	public void setFr(FacturaR fr) {
		this.fr = fr;
	}

	public ArticuloR getAr() {
		return ar;
	}

	public void setAr(ArticuloR ar) {
		this.ar = ar;
	}

	public String getBuscarFactura() {
		return buscarFactura;
	}

	public void setBuscarFactura(String buscarFactura) {
		this.buscarFactura = buscarFactura;
	}

	public String getBuscarArticulo() {
		return buscarArticulo;
	}

	public void setBuscarArticulo(String buscarArticulo) {
		this.buscarArticulo = buscarArticulo;
	}
}
