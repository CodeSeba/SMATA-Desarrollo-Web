package ar.org.centro8.curso.java.web.aplicaciones.test;

import ar.org.centro8.curso.java.web.aplicaciones.entities.Factura;
import ar.org.centro8.curso.java.web.aplicaciones.repositories.cliente.rest.ClienteR;
import ar.org.centro8.curso.java.web.aplicaciones.repositories.cliente.rest.FacturaR;
import java.sql.Date;


public class TestRepository {
    public static void main(String[] args) {
		
		String url="http://172.16.1.245:8081/Servidor/webresources/facturas/v1";
		
//        ArticuloR ar=new ArticuloR(url);
//        Articulo articulo=new Articulo("Coco_Rayado", 20, 40, 40, 40, 80);
//        ar.save(articulo);
//        System.out.println(articulo);
//        System.out.println("*************************************************");
//        //ar.getAll().forEach(System.out::println);
//        ar.getLikeDescripcion("co").forEach(System.out::println);
//        System.out.println("*************************************************");
//        url="http://172.16.1.245:8081/Servidor/webresources/clientes/v1";
//        ClienteR cr=new ClienteR(url);
//        Cliente cliente=new Cliente("Cristian", "Molina", TipoDocumento.DNI, "1234567", "Larrea_123", "_");
//        cr.save(cliente);
//        System.out.println(cliente);
//        System.out.println("*************************************************");
//        //cr.getAll().forEach(System.out::println);
//        cr.getByApellido("Molina").forEach(System.out::println);
        
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++");
		
		ClienteR cr = new ClienteR(url);
		
		cr.getAll().forEach(System.out::println);
		
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++");
		
		String urlDetalle = "http://172.16.1.245:8081/Servidor/webresources/detalles/v1";
		
		FacturaR fr = new FacturaR(url, urlDetalle);
		
		Factura factura = new Factura('a', 4031, Date.valueOf("2019-07-05"), 4000.00, 22);
		fr.save(factura);
		
		fr.getAll().forEach(System.out::println);
		
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++");
        
    }
}