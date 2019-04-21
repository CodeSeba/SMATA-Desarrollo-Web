package ar.org.centro8.curso.java.web.interfaces.repositories;

import ar.org.centro8.curso.java.web.interfaces.entities.User;
import java.util.ArrayList;
import java.util.List;

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
}
