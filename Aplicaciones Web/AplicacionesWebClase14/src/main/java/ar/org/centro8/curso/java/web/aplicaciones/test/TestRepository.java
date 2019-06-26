package ar.org.centro8.curso.java.web.aplicaciones.test;
import ar.org.centro8.curso.java.web.aplicaciones.entities.Articulo;
import ar.org.centro8.curso.java.web.aplicaciones.repositories.jpa.ArticuloR;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
public class TestRepository {
    public static void main(String[] args) {
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("JPAPU");
        ArticuloR ar=new ArticuloR(emf);
        
        Articulo articulo=new Articulo("Monitor", 2000.00, 3000.00, 20, 20, 30);
        
        ar.save(articulo);
        System.out.println(articulo);
        
        //ar.getAll().forEach(System.out::println);
        ar.getLikeDescripcion("pa").forEach(System.out::println);
        
        emf.close();
    }
}