package ar.org.centro8.curso.java.web.aplicaciones.repositories.interfaces;
import java.util.List;
public interface I_Articulo_OnLine <E> {
    List<E> getByCriteria(String criteria);
}