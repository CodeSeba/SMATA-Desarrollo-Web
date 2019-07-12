package ar.org.centro8.curso.java.web.aplicaciones.repositories.jpa;

import ar.org.centro8.curso.java.web.aplicaciones.entities.Articulo;
import ar.org.centro8.curso.java.web.aplicaciones.entities.Factura;
import ar.org.centro8.curso.java.web.aplicaciones.repositories.interfaces.I_FacturaR;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class FacturaR implements I_FacturaR {
	
	private EntityManagerFactory emf;

	public FacturaR(EntityManagerFactory emf) {
		this.emf = emf;
	}

	@Override
	public void save(Factura e) {
		if (e == null) return;
		
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(e);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public void remove(Factura e) {
		if (e == null) return;
		
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.remove(e);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public void update(Factura e) {
		if (e == null) return;
		
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(e);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public List<Factura> getAll() {
		return emf
				.createEntityManager()
				.createNamedQuery("Factura.findAll")
				.getResultList();
	}

	@Override
	public List<Factura> getByArticulo(Articulo articulo) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public List<Factura> getByArticulo(int id) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
}
