package ar.org.centro8.curso.java.web.aplicaciones.repositories.cliente.rest;
import ar.org.centro8.curso.java.web.aplicaciones.entities.Articulo;
import ar.org.centro8.curso.java.web.aplicaciones.repositories.interfaces.I_ArticuloR;
import ar.org.centro8.curso.java.web.aplicaciones.utils.ClientHttp;
import java.util.ArrayList;
import java.util.List;

public class ArticuloR implements I_ArticuloR {
    private String host;
	
    public ArticuloR(String host) { this.host = host; }
	
    @Override public void save(Articulo e) {
        String url=host+"/add?descripcion="+e.getDescripcion()+"&costo="+e.getCosto()
                +"&precio="+e.getPrecio()+"&stock="+e.getStock()
                +"&stockMin="+e.getStockMin()+"&stockMax="+e.getStockMax();
        String id=new ClientHttp().getHTML(url);
        id=id.trim();
        System.out.println(id);
        e.setId(Integer.parseInt(id));
    }
    @Override public void remove(Articulo e) {
        String url=host+"/remove?id="+e.getId();
        String response=new ClientHttp().getHTML(url);
        System.out.println(response);
    }
    @Override public void update(Articulo e) {}
    @Override public List<Articulo> getAll() {
        List<Articulo>lista=new ArrayList();
        String url=host+"/list";
        String texto=new ClientHttp().getHTML(url);
        System.out.println(texto);
        String[] lineas=texto.split("\n");
        for(String linea:lineas){
            linea=linea.substring(9, linea.length()-2);
            String[] campos=linea.split(", ");
            int id=0;
            String descripcion="";
            double costo=0;
            double precio=0;
            int stock=0;
            int stockMin=0;
            int stockMax=0;
            
            try { id=Integer.parseInt(campos[0].substring(campos[0].indexOf("=")+1));       }catch(Exception e) {}
            try { descripcion=campos[1].substring(campos[1].indexOf("=")+1);                }catch(Exception e) {}
            try { costo=Double.parseDouble(campos[2].substring(campos[2].indexOf("=")+1));  }catch(Exception e) {}
            try { precio=Double.parseDouble(campos[3].substring(campos[3].indexOf("=")+1)); }catch(Exception e) {}
            try { stock=Integer.parseInt(campos[4].substring(campos[4].indexOf("=")+1));    }catch(Exception e) {}
            try { stockMin=Integer.parseInt(campos[5].substring(campos[5].indexOf("=")+1)); }catch(Exception e) {}
            try { stockMax=Integer.parseInt(campos[6].substring(campos[6].indexOf("=")+1)); }catch(Exception e) {}
            
            lista.add(new Articulo(id,descripcion,costo,precio,stock,stockMin,stockMax));
        }
        System.out.println("------------------------------------------");
        return lista;
    }
}
