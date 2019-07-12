package ar.org.centro8.curso.java.web.aplicaciones.repositories.interfaces;

import ar.org.centro8.curso.java.web.aplicaciones.entities.Cliente;
import ar.org.centro8.curso.java.web.aplicaciones.entities.Factura;
import ar.org.centro8.curso.java.web.aplicaciones.enums.TipoDocumento;
import java.util.List;
import java.util.stream.Collectors;

public interface I_ClienteR extends I_GenericR<Cliente> {

	default Cliente getById(int id){
		List<Cliente> lista = getAll()
				.stream()
				.filter(a -> a.getId() == id)
				.collect(Collectors.toList());
		return (lista.isEmpty()) ? new Cliente() : lista.get(0);
	}

	default List<Cliente> getByApellido(String apellido){
		return getAll()
				.stream()
				.filter(a -> a.getApellido().equalsIgnoreCase(apellido))
				.collect(Collectors.toList());
	}

	default List<Cliente> getLikeApellido(String apellido){
		return getAll()
				.stream()
				.filter(a -> a.getApellido().toLowerCase().contains(apellido.toLowerCase()))
				.collect(Collectors.toList());
	}

	default List<Cliente> getByApellidoNombre(String apellido, String nombre){
		return getAll()
				.stream()
				.filter(a -> a.getApellido().equalsIgnoreCase(apellido)
								&& a.getNombre().equalsIgnoreCase(nombre))
				.collect(Collectors.toList());
	}

	default List<Cliente> getByTipoNumero(TipoDocumento tipo, String numero){
		return getAll()
				.stream()
				.filter(a -> a.getTipoDocumento().equalsIgnoreCase(tipo.toString())
								&& a.getNumeroDocumento().equalsIgnoreCase(numero))
				.collect(Collectors.toList());
	}

	default List<Cliente> getByDireccion(String direccion){
		return getAll()
				.stream()
				.filter(a -> a.getDireccion().equalsIgnoreCase(direccion))
				.collect(Collectors.toList());
	}

	default List<Cliente> getLikeDireccion(String direccion){
		return getAll()
				.stream()
				.filter(a -> a.getDireccion().toLowerCase().contains(direccion.toLowerCase()))
				.collect(Collectors.toList());
	}

	default Cliente getByFactura(Factura factura){
		return factura.getIdCliente();
	}

	Cliente getByFactura(char letra, int numero);
}
