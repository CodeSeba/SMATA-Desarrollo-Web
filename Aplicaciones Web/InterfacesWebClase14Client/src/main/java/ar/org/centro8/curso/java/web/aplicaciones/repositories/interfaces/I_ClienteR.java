package ar.org.centro8.curso.java.web.aplicaciones.repositories.interfaces;
import ar.org.centro8.curso.java.web.aplicaciones.entities.Cliente;
import ar.org.centro8.curso.java.web.aplicaciones.entities.Factura;
import ar.org.centro8.curso.java.web.aplicaciones.enums.TipoDocumento;
import java.util.List;
import java.util.stream.Collectors;
public interface I_ClienteR extends I_GenericR<Cliente> {
    default Cliente getById(int id){
        List<Cliente>lista=getAll();
        return (lista.isEmpty())?null:lista.get(0); 
    }
    default List<Cliente> getByApellido(String apellido){ 
        return getAll()
                .stream()
                .filter(p->p.getApellido().equalsIgnoreCase(apellido))
                .collect(Collectors.toList()); 
    }
    default List<Cliente> getLikeApellido(String apellido){ 
        return getAll()
                .stream()
                .filter(p->p.getApellido().toLowerCase().contains(apellido.toLowerCase()))
                .collect(Collectors.toList()); 
    }
    default List<Cliente> getByApellidoNombre(String apellido, String nombre){ 
        return getAll()
                .stream()
                .filter(p->p.getApellido().equalsIgnoreCase(apellido)
                        && p.getNombre().equalsIgnoreCase(nombre))
                .collect(Collectors.toList()); 
    }
    default List<Cliente> getByTipoNumero(TipoDocumento tipo, String numero){ 
        return getAll()
                .stream()
                .filter(p->p.getTipoDocumento().equals(tipo)
                        && p.getNumeroDocumento().equals(numero))
                .collect(Collectors.toList()); 
    }
    default List<Cliente> getByDireccion(String direccion){ 
        return getAll()
                .stream()
                .filter(p->p.getDireccion().equalsIgnoreCase(direccion))
                .collect(Collectors.toList());
    }
    default List<Cliente> getLikeDireccion(String direccion){ 
        return getAll()
                .stream()
                .filter(p->p.getDireccion().toLowerCase().contains(direccion.toLowerCase()))
                .collect(Collectors.toList()); 
    }
    default Cliente getByFactura(Factura factura){ 
        return getAll()
                .stream()
                .filter(p->p.getId()==factura.getIdCliente())
                .collect(Collectors.toList())
                .get(0); 
    }
    default Cliente getByFactura(char letra,int numero){ 
        return null; 
    }
}