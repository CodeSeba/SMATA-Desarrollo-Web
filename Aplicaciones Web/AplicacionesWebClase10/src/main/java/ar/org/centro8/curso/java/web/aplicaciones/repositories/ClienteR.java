package ar.org.centro8.curso.java.web.aplicaciones.repositories;

import ar.org.centro8.curso.java.web.aplicaciones.entities.Cliente;
import ar.org.centro8.curso.java.web.aplicaciones.entities.Factura;
import ar.org.centro8.curso.java.web.aplicaciones.enums.TipoDocumento;
import ar.org.centro8.curso.java.web.aplicaciones.repositories.interfaces.I_ClienteR;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ClienteR implements I_ClienteR{
	private Connection conn;
	public ClienteR(Connection conn) { this.conn = conn; }
	
	private List<Cliente> getList(Stream<Cliente> stream) {
		List<Cliente> lista = new ArrayList();
		stream.forEach(lista::add);
		return lista;
	}

	@Override
	public Cliente getById(int id) {
		List<Cliente> lista = getList( getAll().stream().filter(c -> c.getId() == id));
		return (lista.isEmpty()) ? null : lista.get(0);
	}

	@Override
	public List<Cliente> getByApellido(String apellido) {
		return getList( getAll()
				.stream()
				.filter(c -> c.getApellido().equalsIgnoreCase(apellido))
		);
	}

	@Override
	public List<Cliente> getLikeApellido(String apellido) {
		return getList( getAll()
				.stream()
				.filter(c -> c.getApellido().toLowerCase().contains(apellido.toLowerCase()))
		);
	}

	@Override
	public List<Cliente> getByApellidoNombre(String apellido, String nombre) {
		return getList( getAll()
				.stream()
				.filter(c -> c.getApellido().equalsIgnoreCase(apellido)
					&& c.getNombre().equalsIgnoreCase(nombre))
		);
	}

	@Override
	public List<Cliente> getLikeApellidoNombre(String apellido, String nombre) {
		return getList( getAll()
				.stream()
				.filter(c -> c.getApellido().toLowerCase().contains(apellido.toLowerCase())
					&& c.getNombre().toLowerCase().contains(nombre.toLowerCase()))
		);
	}

	@Override
	public List<Cliente> getByTipoNumero(TipoDocumento tipo, String numero) {
		if (tipo == null) return new ArrayList();
		return getList( getAll()
				.stream()
				.filter(c -> c.getTipoDocumento()
								.toString()
								.toLowerCase()
								.contains(tipo.toString().toLowerCase())
						&&
							c.getNumeroDocumento().contains(numero)
				)
		);
	}

	@Override
	public List<Cliente> getByDireccion(String dirreccion) {
		return getList( getAll()
				.stream()
				.filter(c -> c.getDireccion().equalsIgnoreCase(dirreccion))
		);
	}

	@Override
	public List<Cliente> getLikeDireccion(String dirreccion) {
		return getList( getAll()
				.stream()
				.filter(c -> c.getApellido().toLowerCase().contains(dirreccion.toLowerCase()))
		);
	}

	@Override
	public Cliente getByFactura(Factura factura) {
		/*
		List<Cliente> lista = getList( getAll().stream().filter(c -> c.getId() == id));
		return (lista.isEmpty()) ? null : lista.get(0);
		*/
		return null;
	}

	@Override
	public Cliente getByFactura(char letra, int numero) {
		/*
		List<Cliente> lista = getList( getAll().stream().filter(c -> c.getId() == id));
		return (lista.isEmpty()) ? null : lista.get(0);
		*/
		return null;
	}

	@Override
	public void save(Cliente e) {
		if (e == null) return;
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
		if (e == null) return;
		try (PreparedStatement ps = conn.prepareStatement(
				"delete from clientes where id = ?")) {
			ps.setInt(1, e.getId());
			ps.execute();
		} catch (Exception ex) { ex.printStackTrace(); }
	}

	@Override
	public void update(Cliente e) {
		if (e == null) return;
		try (PreparedStatement ps = conn.prepareStatement(
				"update clientes set"
						+ "nombre = ?,"
						+ "apellido = ?,"
						+ "tipoDocumento = ?,"
						+ "numeroDocumento = ?,"
						+ "direccion = ?,"
						+ "comentarios = ?"
						+ "where id = ?")) {
			ps.setString(1, e.getNombre());
			ps.setString(2, e.getApellido());
			ps.setString(3, e.getTipoDocumento().toString());
			ps.setString(4, e.getNumeroDocumento());
			ps.setString(5, e.getDireccion());
			ps.setString(6, e.getComentarios());
			ps.setInt(7, e.getId());
			ps.execute();
		} catch (Exception ex) { ex.printStackTrace(); }
	}

	@Override
	public List<Cliente> getAll() {
		String query = "select * from clientes";
		List<Cliente> lista = new ArrayList();
		try (ResultSet rs = conn.prepareStatement(query).executeQuery()) {
			while (rs.next()) {
				lista.add(new Cliente(
					rs.getInt("id"),
					rs.getString("nombre"),
					rs.getString("apellido"),
					TipoDocumento.valueOf(rs.getString("tipoDocumento")),
					rs.getString("numeroDocumento"),
					rs.getString("direccion"),
					rs.getString("comentarios")
				));
			}
		} catch (Exception e) { e.printStackTrace(); }
		
		return lista;
	}
}
