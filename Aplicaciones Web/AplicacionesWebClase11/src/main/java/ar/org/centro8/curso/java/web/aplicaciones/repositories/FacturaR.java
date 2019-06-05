package ar.org.centro8.curso.java.web.aplicaciones.repositories;

import ar.org.centro8.curso.java.web.aplicaciones.entities.Articulo;
import ar.org.centro8.curso.java.web.aplicaciones.entities.Cliente;
import ar.org.centro8.curso.java.web.aplicaciones.entities.Factura;
import ar.org.centro8.curso.java.web.aplicaciones.repositories.interfaces.I_FacturaR;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class FacturaR implements I_FacturaR {

	private Connection conn;
	public FacturaR(Connection conn) { this.conn = conn; }
	
	@Override
	public Factura getByLetraNumero(char letra, int numero) {
		List<Factura> lista = getAll()
								.stream()
								.filter(f -> f.getLetra() == letra && f.getNumero() == numero)
								.collect(Collectors.toList());
		return (lista.isEmpty() || lista == null) ? null : lista.get(0);
	}

	@Override
	public List<Factura> getByFecha(Date date) {
		return getAll()
				.stream()
				.filter(p -> p.getFecha() == date)
				.collect(Collectors.toList());
	}

	@Override
	public List<Factura> getByCliente(Cliente cliente) {
		return getByCliente(cliente.getId());
	}

	@Override
	public List<Factura> getByCliente(int id) {
		return getAll()
				.stream()
				.filter(f -> f.getIdCliente() == id)
				.collect(Collectors.toList());
	}

	@Override
	public List<Factura> getByArticulo(Articulo articulo) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public List<Factura> getByArticulo(int id) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void save(Factura e) {
		if (e == null) return;
		String query = "insert into facturas (letra,numero,fecha,monto,idCliente)"
							+ "values (?,?,?,?,?)";
		try (PreparedStatement ps = conn.prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS))
		{
			ps.setString(1, e.getLetra() + "");
			ps.setInt(2, e.getNumero());
			ps.setDate(3, e.getFecha());
			ps.setDouble(4, e.getMonto());
			ps.setInt(5, e.getIdCliente());
			ps.execute();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) e.setId(rs.getInt(1));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void remove(Factura e) {
		if (e == null) return;
		try (PreparedStatement ps = conn.prepareStatement(
				"delete from facturas where id = ?")) {
			ps.setInt(1, e.getId());
			ps.execute();
		} catch (Exception ex) { ex.printStackTrace(); }
	}

	@Override
	public void update(Factura e) {
		if (e == null) return;
		String query = "update facturas set "
						+ "letra = ?,"
						+ "numero = ?,"
						+ "fecha = ?,"
						+ "monto = ?,"
						+ "idCliente = ? "
						+ "where id = ?";
		try (PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setString(1, e.getLetra() + "");
			ps.setInt(2, e.getNumero());
			ps.setDate(3, e.getFecha());
			ps.setDouble(4, e.getMonto());
			ps.setInt(5, e.getIdCliente());
			ps.setInt(6, e.getId());
			ps.execute();
		} catch (Exception ex) { ex.printStackTrace(); }
	}

	@Override
	public List<Factura> getAll() {
		String query = "select * from facturas";
		List<Factura> lista = new ArrayList();
		try (ResultSet rs = conn.createStatement().executeQuery(query)) {
			while(rs.next()) {
				lista.add(new Factura(
							rs.getInt("id"),
							rs.getString("letra").charAt(0),
							rs.getInt("numero"),
							rs.getDate("fecha"),
							rs.getDouble("monto"),
							rs.getInt("idCliente")
				));
			}
		} catch (Exception e) { e.printStackTrace(); }
		
		return lista;
	}
}
