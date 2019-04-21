package ar.org.centro8.curso.java.web.aplicaciones.repositories;

import ar.org.centro8.curso.java.web.aplicaciones.entities.Cliente;
import ar.org.centro8.curso.java.web.aplicaciones.entities.Factura;
import ar.org.centro8.curso.java.web.aplicaciones.repositories.interfaces.I_ClienteR;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class ClienteR implements I_ClienteR{
	private Connection conn;
	public ClienteR(Connection conn) { this.conn = conn; }

	@Override
	public Cliente getById(int id) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public List<Cliente> getByApellido(String apellido) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public List<Cliente> getLikeApellido(String apellido) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public List<Cliente> getByApellidoNombre(String apellido, String nombre) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public List<Cliente> getLikeApellidoNombre(String apellido, String nombre) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public List<Cliente> getByTipoNumero(String tipo, String numero) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public List<Cliente> getByDireccion(String dirreccion) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public List<Cliente> getLikeDireccion(String dirreccion) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public List<Cliente> getByFactura(Factura factura) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public List<Cliente> getByFactura(char letra, int numero) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void save(Cliente e) {
		String query = "insert into clientes"
				+ "(nombre,apellido,tipoDocumento,numeroDocumento,direccion,comentarios)"
				+ "values (?,?,?,?,?,?)";
		
		try(PreparedStatement ps = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, e.getNombre());
			ps.setString(2, e.getApellido());
			ps.setString(3, e.getTipoDocumento().toString());
			ps.setString(4, e.getNumeroDocumento());
			ps.setString(5, e.getDireccion());
			ps.setString(6, e.getComentarios());
			ps.execute();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) e.setId(rs.getInt(1));
		} catch (Exception ex) { ex.printStackTrace(); }
	}

	@Override
	public void remove(Cliente e) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void update(Cliente e) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public List<Cliente> getAll() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
}
