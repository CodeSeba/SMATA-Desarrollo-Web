package ar.org.centro8.curso.java.web.aplicaciones.repositories;

import ar.org.centro8.curso.java.web.aplicaciones.entities.ArticuloML;
import ar.org.centro8.curso.java.web.aplicaciones.repositories.interfaces.I_Articulo_OnLine;
import ar.org.centro8.curso.java.web.aplicaciones.utils.ClientHttp;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArticuloMLR implements I_Articulo_OnLine<ArticuloML>{
	private String url = "https://listado.mercadolibre.com.ar/";
	
	
	@Override
	public List<ArticuloML> getByCriteria(String criteria) {
		
		String url = String.format(
						"https://listado.mercadolibre.com.ar/%s#D[A:%s]",
						criteria, criteria);
		
		System.out.println("**************************************************");
		System.out.println(url);
		
		
		String html = new ClientHttp().getHTML(url);
		
		System.out.println("**************************************************");
		System.out.println(html);
		
		
		List<ArticuloML> lista = new ArrayList();
		ArrayDeque<String> cola = new ArrayDeque();
		
		cola.addAll(Arrays.asList(html.split("\n")));
		
		System.out.println("**************************************************");
		System.out.println("Cantidad de lineas en cola: " + cola.size());
		
		while (!cola.isEmpty()) {
			
			String line = cola.poll();
			
			// Cambie las palabras clave por palabras que encontre que sean únicas.
			if (line.contains("ol id=\"searchResults\"")) {
				// ol id.. es la tabla o grilla que contiene todos los articulos.

				ArrayDeque<String> colaArticulos = new ArrayDeque();

				colaArticulos.addAll(Arrays.asList(line.split("<li class=\"results-item highlighted article")));
				// <li class... son los contenedores únicos de cada articulo.
				// Decidi "partir" la cadena por cada <li class... que encuentre .split().
				// Esto provoca también que la primera linea no tenga articulo.
				// pero el if dentro del while lo filtra cargando solamente los articulos.
				
				System.out.println("**************************************************");
				System.out.println("Cantidad de posibles Articulos en cola: " + colaArticulos.size());
				colaArticulos.forEach(System.out::println);
				System.out.println("**************************************************");
				
				while (!colaArticulos.isEmpty()) {
					
					String lineaArticulo = colaArticulos.poll();
					
					if (lineaArticulo.contains("item highlighted")) {
						// item highlighted es la palabra clave que no se repite por articulo.
						
						String item_id			= "";
						String item_url			= "";
						String image_content	= "";
						String price__fraction	= "";
						
						// Cree un método privado llamado getSubstring() ya que se repite
						// el código por cada dato que necesitamos extraer de la cadena.
						//
						// getSubstring(linea,inicio,fin)
						//
						// La misma idea de buscar palabras clave que no se repitieran
						// por cada dato que necesitamos.
						item_id			= getSubstring(lineaArticulo, "item-id=\"", "\"><div class=\"carousel\">");
						item_url		= getSubstring(lineaArticulo, "item-url=\"", "\" item-id=\"");
						image_content	= getSubstring(lineaArticulo, "src='", "' srcset='");
						price__fraction = getSubstring(
												lineaArticulo,
												"<span class=\"price__fraction\">",
												"</span></div>");
						
						lista.add(new ArticuloML(item_id, item_url, image_content, price__fraction));
					}
				}
			}
			
		}
		return lista;
	}
	
	private String getSubstring(String linea, String inicio, String fin) {
		
		if (linea.isEmpty()) return "";
		
		try {
			// Tuve varios problemas para parsear las subcadenas.
			// Necesite borrar la subcadena antes de "inicio" para poder encontrar
			// la primera coincidencia de "fin", el problema es que los programadores
			// de ML no codificaron todos los items iguales y por eso
			// no hay repeticiones iguales.
			// Es por eso que "linea" es reemplazada con una nueva linea con el dato
			// buscado en el indice 0.
			linea = linea.substring(
						linea.indexOf(inicio) + inicio.length());
			
			// Otro problema que encontre es que "fin" no aparece en todos los articulos,
			// por eso lo reemplazo por '">' ya que, de nuevo, los progm. de ML
			// no fueron consistentes con su código.
			if ( ! linea.contains(fin) ) fin = "\">";
			
			return linea.substring(
						0, linea.indexOf(fin));
		} catch (Exception e) {
			System.out.println("Error al tratar de conseguir la subcadena de " + linea +".");
			//e.printStackTrace();
		}
		
		return "";
	}
}
