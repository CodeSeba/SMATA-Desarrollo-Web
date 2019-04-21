package ar.org.centro8.curso.java.web.interfaces.repositories;

import ar.org.centro8.curso.java.web.interfaces.entities.User;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class UserR {
	
	private static List<User> listaUsuarios = new ArrayList();
	
	public static void add(User user) { listaUsuarios.add(user);	}
	
	public static List<User> getAll() { return listaUsuarios;		}
	
	public static User getById(String id) {
		for(User u:listaUsuarios) {
			if(u.getId().equals(id)) return u;
		}
		return null;
	}
	
	public static List<User> getLikeEmail(String email) {
		return getList(UserR.getAll()
				.stream()
				.filter(u -> u.getEmail()
								.toLowerCase()
								.contains(email.toLowerCase())));
	}
	
	public static List<User> getList(Stream<User> stream) {
		List<User> lista = new ArrayList();
		stream.forEach(lista::add);
		return lista;
	}
}
