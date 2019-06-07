package ar.org.centro8.curso.java.web.interfaces.managed.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import ar.org.centro8.curso.java.web.aplicaciones.entities.Cliente;
import ar.org.centro8.curso.java.web.aplicaciones.repositories.ClienteR;
import ar.org.centro8.curso.java.web.aplicaciones.connectors.ConnectorMySQL;
import java.util.List;

@ManagedBean(name = "clienteMB")
@SessionScoped
public class ClienteMB {

	private Cliente cliente;
	private ClienteR cr;
	private String mensaje;
	private String buscar;

	public ClienteMB() {
		cliente = new Cliente();
		cr = new ClienteR(ConnectorMySQL.getConnection());
		mensaje = "";
		buscar = "";
	}

	public void save() {
		cr.save(cliente);
		mensaje = "Se guardo el cliente id= " + cliente.getId();
		cliente = new Cliente();
	}

	public List<Cliente> getAll() {
		return cr.getAll();
	}

	public List<Cliente> getLikeApellido() {
		return cr.getLikeApellido(buscar);
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public ClienteR getCr() {
		return cr;
	}

	public void setCr(ClienteR cr) {
		this.cr = cr;
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
