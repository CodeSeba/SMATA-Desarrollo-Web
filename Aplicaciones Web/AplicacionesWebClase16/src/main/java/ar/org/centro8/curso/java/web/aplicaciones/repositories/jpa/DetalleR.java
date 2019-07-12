package ar.org.centro8.curso.java.web.aplicaciones.repositories.jpa;

import ar.org.centro8.curso.java.web.aplicaciones.entities.Detalle;
import ar.org.centro8.curso.java.web.aplicaciones.repositories.interfaces.I_DetalleR;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class DetalleR implements I_DetalleR{

	private EntityManagerFactory emf;

	public DetalleR(EntityManagerFactory emf) {
		this.emf = emf;
	}

	@Override
	public void save(Detalle e) {
		if (e == null) return;
		
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(e);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public void remove(Detalle e) {
		if (e == null) return;
		
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.remove(e);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public void update(Detalle e) {
		if (e == null) return;
		
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(e);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public List<Detalle> getAll() {
		return emf
				.createEntityManager()
				.createNamedQuery("Detalle.findAll")
				.getResultList();
	}
}
