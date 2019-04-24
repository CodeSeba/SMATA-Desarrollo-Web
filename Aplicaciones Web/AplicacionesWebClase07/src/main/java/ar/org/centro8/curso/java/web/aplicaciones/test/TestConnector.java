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
		
		ConnectorMySQL.getConnection().createStatement().execute(
				"insert into articulos"
					+ "(descripcion,costo,precio,stock,stockMin,stockMax) values "
					+ "('Tornillo',1,2,100,1000,2000)"
		);
		
		ConnectorMySQL.getConnection().createStatement().execute(
				"insert into articulos"
					+ "(descripcion,costo,precio,stock,stockMin,stockMax) values "
					+ "('Destornillador',10,20,20,30,80)"
		);
		
		ConnectorMySQL.getConnection().createStatement().execute(
				"insert into articulos"
					+ "(descripcion,costo,precio,stock,stockMin,stockMax) values "
					+ "('Martillo',50,100,3,10,100)"
		);
		
		ConnectorMySQL.getConnection().createStatement().execute(
				"insert into articulos"
					+ "(descripcion,costo,precio,stock,stockMin,stockMax) values "
					+ "('Pinza',50,100,2,20,150)"
		);
		
		ConnectorMySQL.getConnection().createStatement().execute(
				"insert into articulos"
					+ "(descripcion,costo,precio,stock,stockMin,stockMax) values "
					+ "('Serrucho',100,200,1,5,80)"
		);
		
		ConnectorMySQL.getConnection().createStatement().execute(
				"insert into articulos"
					+ "(descripcion,costo,precio,stock,stockMin,stockMax) values "
					+ "('Mechas',10,20,100,50,300)"
		);
		
		ConnectorMySQL.getConnection().createStatement().execute(
				"insert into articulos"
					+ "(descripcion,costo,precio,stock,stockMin,stockMax) values "
					+ "('Clavos',1,2,1000,500,2000)"
		);
		
		ConnectorMySQL.getConnection().createStatement().execute(
				"insert into articulos"
					+ "(descripcion,costo,precio,stock,stockMin,stockMax) values "
					+ "('Perforadora',300,900,3,10,20)"
		);
	}
}
