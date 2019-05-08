package ar.org.centro8.curso.java.web.aplicaciones.repositories.interfaces;

import ar.org.centro8.curso.java.web.aplicaciones.entities.Cliente;
import ar.org.centro8.curso.java.web.aplicaciones.entities.Factura;
import ar.org.centro8.curso.java.web.aplicaciones.enums.TipoDocumento;
import java.util.List;

public interface I_ClienteR extends I_GenericR<Cliente>{
	Cliente getById(int id);
	List<Cliente> getByApellido(String apellido);
	List<Cliente> getLikeApellido(String apellido);
	List<Cliente> getByApellidoNombre(String apellido, String nombre);
	List<Cliente> getLikeApellidoNombre(String apellido, String nombre);
	List<Cliente> getByTipoNumero(TipoDocumento tipo, String numero);
	List<Cliente> getByDireccion(String dirreccion);
	List<Cliente> getLikeDireccion(String dirreccion);
	Cliente getByFactura(Factura factura);
	Cliente getByFactura(char letra, int numero);
}
