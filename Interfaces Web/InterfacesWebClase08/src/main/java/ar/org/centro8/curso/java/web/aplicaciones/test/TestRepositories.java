package ar.org.centro8.curso.java.web.aplicaciones.test;

import ar.org.centro8.curso.java.web.aplicaciones.connectors.ConnectorMySQL;
import ar.org.centro8.curso.java.web.aplicaciones.entities.Articulo;
import ar.org.centro8.curso.java.web.aplicaciones.entities.Cliente;
import ar.org.centro8.curso.java.web.aplicaciones.entities.Detalle;
import ar.org.centro8.curso.java.web.aplicaciones.enums.TipoDocumento;
import ar.org.centro8.curso.java.web.aplicaciones.repositories.ArticuloR;
import ar.org.centro8.curso.java.web.aplicaciones.repositories.ClienteR;

public class TestRepositories {
    public static void main(String[] args) {
        ClienteR cr=new ClienteR(ConnectorMySQL.getConnection());
        Cliente cliente=new Cliente(
                "Rojas", 
                "Daniel", 
                TipoDocumento.DNI, 
                "12345679", 
                "Medrano 162", 
                ""
        );
        //cr.save(cliente);
        //System.out.println(cliente);
        
        
        //cr.remove(cr.getById(2));
        
        cliente =cr.getById(4);
        if(cliente!=null){
            cliente.setNombre("Javier");
            cliente.setDireccion("maza 851");
            cr.update(cliente);
        }
        
        cr.getAll().forEach(System.out::println);
        
        //System.out.println(cr.getById(8));
        
        //cr.getByApellido("vargaz").forEach(System.out::println);
        
        //cr.getLikeApellido("v").forEach(System.out::println);
        
        //cr.getLikeDireccion("med").forEach(System.out::println);
        
        //cr.getByApellidoNombre("", "s").forEach(System.out::println);
        
        //cr.getByTipoNumero(null, "111").forEach(System.out::println);
        
        //cr.getByTipoNumero(TipoDocumento.DNI, "111").forEach(System.out::println);
        
        ArticuloR ar=new ArticuloR(ConnectorMySQL.getConnection());
        //Articulo articulo=new Articulo("Tijera", 20, 32, 20, 30, 50);
        //ar.save(articulo);
        //System.out.println(articulo);
        
        Detalle detalle=new Detalle(1, 1, 1, 10);
        System.out.println(ar.getByDetalle(detalle));
        System.out.println(ar.getById(3));
        
        //ar.getAll().forEach(System.out::println);
        
        Articulo articulo2=new Articulo("pinza", 20, 25, 5, 20, 50);
        ar.save(articulo2);
        
        Articulo articulo3=new Articulo("Lima",50,60, 50,30,60);
        ar.save(articulo3);
        
        
        ar
                .getAll()
                .forEach(item->
                        System.out.println(item.getId()+" "+
                                item.getDescripcion()+" "+
                                ar.getReponerCantidad(item)));
        
        
        //ar.getLikeDescripcion("pi").forEach(System.out::println);
        
        
        Articulo articulox=ar.getById(4);
        if(articulox!=null){
            articulox.setDescripcion("Boligrafo");
            articulox.setPrecio(56);
            ar.update(articulox);
        }
        ar.remove(ar.getById(5));
        ar.getAll().forEach(System.out::println);
        
        
    }
}