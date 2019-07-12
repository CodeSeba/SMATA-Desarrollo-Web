package ar.org.centro8.curso.java.web.aplicaciones.managed.beans;

import ar.org.centro8.curso.java.web.aplicaciones.entities.Factura;
import ar.org.centro8.curso.java.web.aplicaciones.repositories.cliente.rest.FacturaR;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="facturaMB")
@SessionScoped
public class FacturaMB {
	private Factura factura;
	private FacturaR fr;
	private String mensaje;
	private String buscar;
	
	public FacturaMB() {
		factura = new Factura();
		fr = new FacturaR(
					"http://172.16.0.55:8081/Servidor/webresources/facturas/v1",
					"http://172.16.0.55:8081/Servidor/webresources/detalles/v1");
		mensaje = "";
		buscar  = "0";
	}
	
	public void save() {
		if (validar()){
			fr.save(factura);
			mensaje = "Se dio de alta la factura id: " + factura.getId();
		} else mensaje = "No se dio de alta la factura.";
	}
	
	private boolean validar() {
		return true;
	}
	
	public List<Factura> getAll() {
		return fr.getAll();
	}
	
	public List<Factura> getLikeArticulo() {
		return fr.getByArticulo(Integer.parseInt(buscar));
	}
	
	public List<Factura> getLikeCliente() {
		return fr.getByCliente(Integer.parseInt(buscar));
	}

	public Factura getFactura() {
		return factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}

	public FacturaR getFr() {
		return fr;
	}

	public void setFr(FacturaR fr) {
		this.fr = fr;
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

	@Override
	public String toString() {
		return "FacturaMB{" + "factura=" + factura + ", fr=" + fr + ", mensaje=" + mensaje + ", buscar=" + buscar + '}';
	}
}
