package ar.org.centro8.curso.java.web.interfaces.servlets.utilities;
import java.lang.reflect.Field;
import java.util.List;
public class TablaHtml<E> {
    public String getTabla(List<E> lista){
        if(lista==null || lista.isEmpty()) return "";
        String table="";
        E e=lista.get(0);
        table+="<table border=1>";
        table+="<tr>";
        Field[]campos=e.getClass().getDeclaredFields();
        for(Field f:campos) table+="<th>"+f.getName()+"</th>";
        table+="</tr>";
        for(E x:lista){
            table+="<tr>";
            for(Field f:campos){
                table+="<td>";
                String method="get"+f.getName().substring(0, 1).toUpperCase()
                        +f.getName().substring(1);
                try {
                    table+=e.getClass().getDeclaredMethod(method,null).invoke(x,null)+"";
                } catch (Exception ex) { ex.printStackTrace(); }
                table+="</td>";
            }
            table+="</tr>";
        }
        table+="</table>";
        return table;
    }
}
