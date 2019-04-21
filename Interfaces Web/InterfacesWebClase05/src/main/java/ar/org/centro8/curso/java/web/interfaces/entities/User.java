package ar.org.centro8.curso.java.web.interfaces.entities;

public class User implements Comparable<User> {
	private String id;
	private String name;
	private String email;

	public User(String id, String name, String email) {
		this.id = id;
		this.name = name;
		this.email = email;
	}

	@Override
	public String toString() {
		return "User{" + "id=" + id + ", name=" + name + ", email=" + email + '}';
	}
	
	public String compare() {
		return email + "," + name + "," + id;
	}
	
	@Override
	public int compareTo(User u) {
		return compare().compareTo(u.compare());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
	
}
