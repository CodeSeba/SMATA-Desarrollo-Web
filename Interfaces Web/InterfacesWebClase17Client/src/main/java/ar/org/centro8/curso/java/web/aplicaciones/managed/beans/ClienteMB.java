package ar.org.centro8.curso.java.web.aplicaciones.managed.beans;

import ar.org.centro8.curso.java.web.aplicaciones.entities.Cliente;
import ar.org.centro8.curso.java.web.aplicaciones.enums.TipoDocumento;
import ar.org.centro8.curso.java.web.aplicaciones.repositories.cliente.rest.ClienteR;
import java.util.Arrays;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="clienteMB")
@SessionScoped
public class ClienteMB {
    private Cliente cliente;
    private ClienteR cr;
    private String mensaje;
    private String buscar;

    public ClienteMB() {
        cliente=new Cliente();
        cr=new ClienteR("http://172.16.0.55:8081/Servidor/webresources/clientes/v1");
        mensaje="";
        buscar="";
    }
    
    public void save(){
        if(validar()){
            cr.save(cliente);
            mensaje="Se dio de alta el cliente id: "+cliente.getId();
        }else{
            mensaje="No se dio de alta el cliente";
        }
    }
    
    public List<TipoDocumento> getTipoDocumentos(){
        return Arrays.asList(TipoDocumento.values());
    }
    
    private boolean validar(){
        cliente.setNombre(cliente.getNombre().replace(" ", "_"));
        cliente.setApellido(cliente.getApellido().replace(" ", "_"));
        cliente.setNumeroDocumento(cliente.getNumeroDocumento().replace(" ", "_"));
        cliente.setDireccion(cliente.getDireccion().replace(" ", "_"));
        cliente.setComentarios(cliente.getComentarios().replace(" ", "_"));
        return true;
    }
    public List<Cliente>getAll(){
        return cr.getAll();
    }
	
    public List<Cliente>getLikeApellido(){
        return cr.getLikeApellido(buscar);
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ClienteR getCr() {
        return cr;
    }

    public void setCr(ClienteR cr) {
        this.cr = cr;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getBuscar() {
        return buscar;
    }

    public void setBuscar(String buscar) {
        this.buscar = buscar;
    }
}
