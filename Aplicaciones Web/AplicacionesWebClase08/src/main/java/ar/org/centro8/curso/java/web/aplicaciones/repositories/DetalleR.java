package ar.org.centro8.curso.java.web.aplicaciones.repositories;

import ar.org.centro8.curso.java.web.aplicaciones.entities.Detalle;
import ar.org.centro8.curso.java.web.aplicaciones.entities.Factura;
import ar.org.centro8.curso.java.web.aplicaciones.repositories.interfaces.I_DetalleR;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class DetalleR implements I_DetalleR {
	
	private Connection conn;
	
	public DetalleR(Connection conn) { this.conn = conn; }
	
	@Override
	public Detalle getByFacturaNumero(char letra, int numero) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public Detalle getByFacturaNumero(char letra, int numero, int idArticulo) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public List<Detalle> getByFactura(Factura factura) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public List<Detalle> getByFactura(char letra, int numero) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void save(Detalle e) {
		String query = "insert into detalles (idfactura,idArticulo,cantidad,precioUnit) "
							+ "values (?,?,?,?)";
		// No hace falta solicitar los keys generados
		// porque la base sql, el primary key es compuesta.
		try (PreparedStatement ps = conn.prepareStatement(query)){
			ps.setInt(1, e.getIdArticulo());
			ps.setInt(2, e.getIdFactura());
			ps.setInt(3, e.getCantidad());
			ps.setDouble(4, e.getPrecioUnit());
			ps.execute();
		} catch (Exception ex) { ex.printStackTrace();}
	}

	@Override
	public void remove(Detalle e) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void update(Detalle e) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public List<Detalle> getAll() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
	
}
