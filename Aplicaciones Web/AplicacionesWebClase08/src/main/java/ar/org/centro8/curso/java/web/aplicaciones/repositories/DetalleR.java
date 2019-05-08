package ar.org.centro8.curso.java.web.aplicaciones.repositories;

import ar.org.centro8.curso.java.web.aplicaciones.connectors.ConnectorMySQL;
import ar.org.centro8.curso.java.web.aplicaciones.entities.Articulo;
import ar.org.centro8.curso.java.web.aplicaciones.entities.Detalle;
import ar.org.centro8.curso.java.web.aplicaciones.entities.Factura;
import ar.org.centro8.curso.java.web.aplicaciones.repositories.interfaces.I_DetalleR;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DetalleR implements I_DetalleR {

	private Connection conn;

	public DetalleR(Connection conn) {
		this.conn = conn;
	}

	@Override
	public Detalle getByFacturaArticulo(Factura factura, Articulo articulo) {
		List<Detalle> lista = getAll()
				.stream()
				.filter(d -> d.getIdFactura() == factura.getId() && d.getIdArticulo() == articulo.getId())
				.collect(Collectors.toList());
		return (lista.isEmpty()) ? null : lista.get(0);
	}

	@Override
	public Detalle getByFacturaArticulo(char letra, int numero, int idArticulo) {
		FacturaR fr = new FacturaR(conn);
		ArticuloR ar = new ArticuloR(conn);
		
		return getByFacturaArticulo(fr.getByLetraNumero(letra, numero), ar.getById(idArticulo));
	}

	@Override
	public List<Detalle> getByFactura(Factura factura) {
		List<Detalle> lista = getAll()
				.stream()
				.filter(d -> d.getIdFactura() == factura.getId())
				.collect(Collectors.toList());
		return (lista.isEmpty()) ? null : lista;
	}

	@Override
	public List<Detalle> getByFactura(char letra, int numero) {
		return getByFactura(new FacturaR(conn).getByLetraNumero(letra, numero));
	}

	@Override
	public void save(Detalle e) {
		String query = "insert into detalles (idfactura,idArticulo,cantidad,precioUnit) "
				+ "values (?,?,?,?)";
		// No hace falta solicitar los keys generados
		// porque la base sql, el primary key es compuesta.
		try (PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setInt(1, e.getIdArticulo());
			ps.setInt(2, e.getIdFactura());
			ps.setInt(3, e.getCantidad());
			ps.setDouble(4, e.getPrecioUnit());
			ps.execute();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void remove(Detalle e) {
		if (e == null) return;
		try (PreparedStatement ps = conn.prepareStatement(
				"delete from detalles where (idFactura,idArticulo) = (?,?)")) {
			ps.setInt(1, e.getIdFactura());
			ps.setInt(2, e.getIdArticulo());
			ps.execute();
		} catch (Exception ex) { ex.printStackTrace(); }
	}

	@Override
	public void update(Detalle e) {
		if (e == null) return;
		String query = "update detalles set "
						+ "cantidad = ?,"
						+ "precioUnit = ? "
						+ "where (idFactura,idArtiulo) = (?,?)";
		try (PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setInt(1, e.getCantidad());
			ps.setDouble(2, e.getPrecioUnit());
			ps.setInt(3, e.getIdFactura());
			ps.setInt(4, e.getIdArticulo());
			ps.execute();
		} catch (Exception ex) { ex.printStackTrace(); }
	}

	@Override
	public List<Detalle> getAll() {
		String query = "select * from detalles";
		List<Detalle> lista = new ArrayList();

		try (ResultSet rs = conn.createStatement().executeQuery(query)) {
			while (rs.next()) {
				lista.add(new Detalle(
						rs.getInt("idFactura"),
						rs.getInt("idArticulo"),
						rs.getInt("cantidad"),
						rs.getDouble("precioUnit")
				));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

}
