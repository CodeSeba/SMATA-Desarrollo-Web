drop database if exists cursoJavaWebNegocio;

create database cursoJavaWebNegocio;

use cursoJavaWebNegocio;

create table articulos(
	id int auto_increment primary key,
	descripcion varchar(40) not null,
	costo double,
	precio double,
	stock int,
	stockMin int,
	stockMax int
);

create table facturas(
	id int auto_increment primary key,
	letra char(1) not null,
	numero int not null,
	fecha date not null,
	monto double not null,
	unique index factura_letra_numero(letra,numero)
);

create table detalles(
	idFactura int not null,
	idArticulo int not null,
	cantidad int not null,
	precioUnit double not null,
	primary key(idFactura, idArticulo),
	foreign key(idFactura) references facturas(id) on delete cascade,
	foreign key(idArticulo) references articulos(id) on delete cascade
);

create table clientes(
	id int auto_increment primary key,
	nombre varchar(20) not null,
	apellido varchar(20) not null,
	tipoDocumento enum('DNI', 'LC', 'LE', 'Pasaporte', 'Cedula_Policial') not null,
	numeroDocumento char(8) not null,
	direccion varchar(50),
	comentarios varchar(255),
	unique index clientes_tipo_numero(tipoDocumento, numeroDocumento)
);

-- Agregamos el campo FK en la tabla para enlazar el cliente
alter table facturas add idCliente int not null;
alter table facturas add constraint foreign key(idCliente)
	references clientes(id) on delete cascade;

show tables;