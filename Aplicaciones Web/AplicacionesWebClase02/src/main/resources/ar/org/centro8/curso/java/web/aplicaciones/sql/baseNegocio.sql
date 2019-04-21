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
	unique index factura_letra_numero(letra,numero),
	stockMax int
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

show tables;