package ar.org.centro8.curso.java.web.aplicaciones.repositories.interfaces;

import ar.org.centro8.curso.java.web.aplicaciones.entities.Articulo;
import ar.org.centro8.curso.java.web.aplicaciones.entities.Cliente;
import ar.org.centro8.curso.java.web.aplicaciones.entities.Factura;
import java.sql.Date;
import java.util.List;

public interface I_FacturaR extends I_GenericR<Factura>{
    Factura getByLetraNumero(char letra,int numero);
    List<Factura>getByFecha(Date date);
    List<Factura>getByCliente(Cliente cliente);
    List<Factura>getByCliente(int id);
    List<Factura>getByArticulo(Articulo articulo);
    List<Factura>getByArticulo(int id);
}