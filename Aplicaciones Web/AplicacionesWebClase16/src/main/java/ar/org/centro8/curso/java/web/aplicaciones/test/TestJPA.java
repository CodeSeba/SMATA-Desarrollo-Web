package ar.org.centro8.curso.java.web.aplicaciones.test;

import ar.org.centro8.curso.java.web.aplicaciones.entities.Articulo;
import ar.org.centro8.curso.java.web.aplicaciones.entities.Cliente;
import ar.org.centro8.curso.java.web.aplicaciones.enums.TipoDocumento;
import ar.org.centro8.curso.java.web.aplicaciones.repositories.jpa.ClienteR;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class TestJPA {
    public static void main(String[] args) {
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("JPAPU");
        EntityManager em=emf.createEntityManager();
        
        //Articulo articulo=new Articulo("papa fritas", 20.0, 20.0, 20, 10, 60);
        //em.getTransaction().begin();
        //em.persist(articulo);
        //em.getTransaction().commit();
        
        //System.out.println(articulo);
        
        
        /*
        Query query3=em.createNamedQuery("Articulo.findById");
        query3.setParameter("id", 11);
        Articulo articulo2=(Articulo)query3.getSingleResult();
        if(articulo2!=null){
            em.getTransaction().begin();
            em.remove(articulo2);
            em.getTransaction().commit();
        }
        */
        /*
        Query query4=em.createNamedQuery("Articulo.findById");
        query4.setParameter("id", 12);
        Articulo articulo4=(Articulo)query4.getSingleResult();
        articulo4.setDescripcion("Shampoo");
        em.getTransaction().begin();
        em.persist(articulo4);
        em.getTransaction().commit();
        
        em.createNamedQuery("Articulo.findAll").getResultList().forEach(System.out::println);
        
        //Query query=em.createNamedQuery("Articulo.findByDescripcion");
        //query.setParameter("descripcion", "papa fritas");
        //query.getResultList().forEach(System.out::println);
        
        //Query query2=em.createQuery("SELECT a FROM Articulo a WHERE a.descripcion like :descripcion");
        //query2.setParameter("descripcion", "%pa%");
        //query2.getResultList().forEach(System.out::println);
        */
		/*
		em
			.createNamedQuery("Factura.findAll")
			.getResultList()
			.forEach(System.out::println);
        */
		
		
		ClienteR cr = new ClienteR(emf);
		
		/*
		Cliente cliente = new Cliente("Sara", "O'Connor", TipoDocumento.DNI.toString(), "87654321", "Medrano 162", "I'll be back.");
		cr.save(cliente);
		System.out.println(cliente);
		*/
		
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println(cr.getByFactura('C', 112));
		
        em.close();
        emf.close();
    }
}