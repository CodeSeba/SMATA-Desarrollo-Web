package ar.org.centro8.curso.java.web.aplicaciones.repositories.cliente.rest;
import ar.org.centro8.curso.java.web.aplicaciones.entities.Articulo;
import ar.org.centro8.curso.java.web.aplicaciones.entities.Cliente;
import ar.org.centro8.curso.java.web.aplicaciones.entities.Factura;
import ar.org.centro8.curso.java.web.aplicaciones.enums.TipoDocumento;
import ar.org.centro8.curso.java.web.aplicaciones.repositories.interfaces.I_ClienteR;
import ar.org.centro8.curso.java.web.aplicaciones.utils.ClientHttp;
import java.util.ArrayList;
import java.util.List;
public class ClienteR implements I_ClienteR {
    private String host;
    public ClienteR(String host) { this.host = host; }
    @Override public void save(Cliente e) {
        String url=host+"/add?nombre="+e.getNombre()
                +"&apellido="+e.getApellido()
                +"&tipoDocumento="+e.getTipoDocumento()
                +"&numeroDocumento="+e.getNumeroDocumento()
                +"&direccion="+e.getDireccion()
                +"&comentarios"+e.getComentarios();
        String id=new ClientHttp().getHTML(url);
        id=id.trim();
        System.out.println(id);
        e.setId(Integer.parseInt(id));
    }
    @Override public void remove(Cliente e) {
        String url=host+"/remove?id="+e.getId();
        String response=new ClientHttp().getHTML(url);
        System.out.println(response);
    }
    @Override public void update(Cliente e) { }
    @Override public List<Cliente> getAll() {
    List<Cliente>lista=new ArrayList();
        String url=host+"/list";
        String texto=new ClientHttp().getHTML(url);
        //System.out.println(texto);
        String[] lineas=texto.split("\n");
        for(String linea:lineas){
            linea=linea.substring(8, linea.length()-2);
            String[] campos=linea.split(", ");
            int id=0;
            String nombre="";
            String apellido="";
            TipoDocumento tipoDocumento=null;
            String numeroDocumento="";
            String direccion="";
            String comentarios="";
            
            try { id=Integer.parseInt(campos[0].substring(campos[0].indexOf("=")+1));                   }catch(Exception e) {}
            try { nombre=campos[1].substring(campos[1].indexOf("=")+1);                                 }catch(Exception e) {}
            try { apellido=campos[2].substring(campos[2].indexOf("=")+1);                               }catch(Exception e) {}
            try { tipoDocumento=TipoDocumento.valueOf(campos[3].substring(campos[3].indexOf("=")+1));   }catch(Exception e) {}
            try { numeroDocumento=campos[4].substring(campos[4].indexOf("=")+1);                        }catch(Exception e) {}
            try { direccion=campos[5].substring(campos[5].indexOf("=")+1);                              }catch(Exception e) {}
            try { comentarios=campos[6].substring(campos[6].indexOf("=")+1);                            }catch(Exception e) {}
            
            
            lista.add(
                    new Cliente(
                            id,nombre,apellido,tipoDocumento,numeroDocumento,direccion,comentarios
                    )
            );
            
            
        }
        return lista;    
    }
    
}