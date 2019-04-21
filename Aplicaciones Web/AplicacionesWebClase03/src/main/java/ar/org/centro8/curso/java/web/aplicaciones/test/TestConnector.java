package ar.org.centro8.curso.java.web.aplicaciones.test;

import ar.org.centro8.curso.java.web.aplicaciones.connectors.ConnectorMySQL;
import java.sql.Connection;
import java.sql.Statement;

public class TestConnector {

	public static void main(String[] args) throws Exception {
		ConnectorMySQL.getConnection().createStatement().execute(
				"insert into clientes"
					+ "(nombre,apellido,tipoDocumento,numeroDocumento) values "
					+ "('Juan','Perez','DNI',11111111,'Medrano 161')"
		);
	}
}
