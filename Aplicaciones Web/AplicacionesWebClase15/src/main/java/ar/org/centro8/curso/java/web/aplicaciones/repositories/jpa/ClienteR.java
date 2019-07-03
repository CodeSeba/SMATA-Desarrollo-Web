package ar.org.centro8.curso.java.web.aplicaciones.repositories.jpa;

import ar.org.centro8.curso.java.web.aplicaciones.entities.Cliente;
import ar.org.centro8.curso.java.web.aplicaciones.entities.Factura;
import ar.org.centro8.curso.java.web.aplicaciones.repositories.interfaces.I_ClienteR;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class ClienteR implements I_ClienteR{
	
	private EntityManagerFactory emf;
	
	public ClienteR(EntityManagerFactory emf) { this.emf = emf; }

	@Override
	public Cliente getByFactura(char letra, int numero) {
		List<Cliente> lista = getAll()
				.stream()
				.filter(
					c -> c.getFacturaList()
					.stream()
					.anyMatch(
						f -> f.getLetra().equals(letra) // Chars son numeros
								&& f.getNumero() == numero
					)
				).collect(Collectors.toList());
				
		return (lista == null || lista.isEmpty()) ? (new Cliente()) : lista.get(0);
	}

	@Override
	public void save(Cliente e) {
		if (e == null) return;
		
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(e);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public void remove(Cliente e) {
		if (e == null) return;
		
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.remove(e);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public void update(Cliente e) {
		if (e == null) return;
		
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(e);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public List<Cliente> getAll() {
		return emf
				.createEntityManager()
				.createNamedQuery("Cliente.findAll")
				.getResultList();
	}
	
}
