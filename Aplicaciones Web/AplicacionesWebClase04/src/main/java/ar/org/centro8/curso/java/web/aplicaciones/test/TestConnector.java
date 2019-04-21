package ar.org.centro8.curso.java.web.aplicaciones.test;

import ar.org.centro8.curso.java.web.aplicaciones.connectors.ConnectorMySQL;

public class TestConnector {

	public static void main(String[] args) throws Exception {
		ConnectorMySQL.getConnection().createStatement().execute(
				"insert into clientes"
					+ "(nombre,apellido,tipoDocumento,numeroDocumento,direccion) values "
					+ "('Juan','Perez','DNI',11111111,'Medrano 161')"
		);
		
		ConnectorMySQL.getConnection().createStatement().execute(
				"insert into clientes"
					+ "(nombre,apellido,tipoDocumento,numeroDocumento,direccion) values "
					+ "('Jose','Ares','DNI',2222222,'Medrano 162')"
		);
		
		ConnectorMySQL.getConnection().createStatement().execute(
				"insert into clientes"
					+ "(nombre,apellido,tipoDocumento,numeroDocumento,direccion) values "
					+ "('Rosa','Lito','DNI',33333333,'Medrano 163')"
		);
		
		ConnectorMySQL.getConnection().createStatement().execute(
				"insert into clientes"
					+ "(nombre,apellido,tipoDocumento,numeroDocumento,direccion) values "
					+ "('Carmen','Ruiz','DNI',44444444,'Medrano 164')"
		);
		
		ConnectorMySQL.getConnection().createStatement().execute(
				"insert into clientes"
					+ "(nombre,apellido,tipoDocumento,numeroDocumento,direccion) values "
					+ "('Carlos','Rios','DNI',55555555,'Medrano 165')"
		);
		
		ConnectorMySQL.getConnection().createStatement().execute(
				"insert into clientes"
					+ "(nombre,apellido,tipoDocumento,numeroDocumento,direccion) values "
					+ "('Pablo','Marmol','DNI',66666666,'Medrano 166')"
		);
		
		ConnectorMySQL.getConnection().createStatement().execute(
				"insert into clientes"
					+ "(nombre,apellido,tipoDocumento,numeroDocumento,direccion) values "
					+ "('Carolina','Leon','DNI',77777777,'Medrano 167')"
		);
		
		ConnectorMySQL.getConnection().createStatement().execute(
				"insert into clientes"
					+ "(nombre,apellido,tipoDocumento,numeroDocumento,direccion) values "
					+ "('Karina','Vargaz','DNI',88888888,'Medrano 168')"
		);
	}
}
