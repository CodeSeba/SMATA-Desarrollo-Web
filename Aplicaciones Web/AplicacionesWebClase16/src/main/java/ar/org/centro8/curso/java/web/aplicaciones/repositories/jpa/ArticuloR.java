package ar.org.centro8.curso.java.web.aplicaciones.repositories.jpa;

import ar.org.centro8.curso.java.web.aplicaciones.entities.Articulo;
import ar.org.centro8.curso.java.web.aplicaciones.repositories.interfaces.I_ArticuloR;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class ArticuloR implements I_ArticuloR {

	private EntityManagerFactory emf;

	public ArticuloR(EntityManagerFactory emf) { this.emf = emf; }

	@Override
	public void save(Articulo e) {
		if (e == null) return;
		
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(e);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public void remove(Articulo e) {
		if (e == null) return;
		
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.remove(e);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public void update(Articulo e) {
		if (e == null) return;
		
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(e);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public List<Articulo> getAll() {
		return emf
				.createEntityManager()
				.createNamedQuery("Articulo.findAll")
				.getResultList();
	}

}
