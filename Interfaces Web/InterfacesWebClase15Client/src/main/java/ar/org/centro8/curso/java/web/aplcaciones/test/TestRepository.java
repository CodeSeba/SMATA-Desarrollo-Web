package ar.org.centro8.curso.java.web.aplcaciones.test;

import ar.org.centro8.curso.java.web.aplicaciones.entities.Articulo;
import ar.org.centro8.curso.java.web.aplicaciones.entities.Cliente;
import ar.org.centro8.curso.java.web.aplicaciones.enums.TipoDocumento;
import ar.org.centro8.curso.java.web.aplicaciones.repositories.cliente.rest.ArticuloR;
import ar.org.centro8.curso.java.web.aplicaciones.repositories.cliente.rest.ClienteR;

public class TestRepository {
    public static void main(String[] args) {
        String url="http://172.16.19.254:8081/Servidor/webresources/articulos/v1";
        ArticuloR ar=new ArticuloR(url);
        Articulo articulo=new Articulo("Coco_Rayado", 20, 40, 40, 40, 80);
        ar.save(articulo);
        System.out.println(articulo);
        System.out.println("*************************************************");
        //ar.getAll().forEach(System.out::println);
        ar.getLikeDescripcion("co").forEach(System.out::println);
        System.out.println("*************************************************");
        url="http://172.16.19.254:8081/Servidor/webresources/clientes/v1";
        ClienteR cr=new ClienteR(url);
        Cliente cliente=new Cliente("Cristian", "Molina", TipoDocumento.DNI, "1234567", "Larrea_123", "_");
        cr.save(cliente);
        System.out.println(cliente);
        System.out.println("*************************************************");
        //cr.getAll().forEach(System.out::println);
        cr.getByApellido("Molina").forEach(System.out::println);
        
        
        
    }
}