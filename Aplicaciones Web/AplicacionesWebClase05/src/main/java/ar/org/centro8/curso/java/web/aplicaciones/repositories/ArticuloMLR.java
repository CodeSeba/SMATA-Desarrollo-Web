package ar.org.centro8.curso.java.web.aplicaciones.repositories;

import ar.org.centro8.curso.java.web.aplicaciones.entities.ArticuloML;
import ar.org.centro8.curso.java.web.aplicaciones.repositories.interfaces.I_Articulo_OnLine;
import java.util.List;

public class ArticuloMLR implements I_Articulo_OnLine<ArticuloML>{
	private String url = "https://listado.mercadolibre.com.ar/";
	
	
	@Override
	public List<ArticuloML> getByCriteria(String criteria) {
		return null;
	}
	
}
