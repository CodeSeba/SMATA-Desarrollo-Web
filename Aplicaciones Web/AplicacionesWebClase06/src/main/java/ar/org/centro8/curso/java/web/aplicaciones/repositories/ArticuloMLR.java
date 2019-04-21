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
		System.out.println("**************************************************");
		String html = new ClientHttp().getHTML(url);
		System.out.println(html);
		System.out.println("**************************************************");
		List<ArticuloML> lista = new ArrayList();
				
		ArrayDeque<String> cola = new ArrayDeque();
		cola.addAll(Arrays.asList(html.split("\n")));
		System.out.println("Cantidad de lineas en pila: " + cola.size());
		
		while (!cola.isEmpty()) {
			String line = cola.poll();
			
			if (line.contains("section id=\"related-searches-section\"")) {
				ArrayDeque<String> colaArticulos = new ArrayDeque();
				colaArticulos.addAll(Arrays.asList(line.split("<")));
				
				System.out.println("**********************************************");
				colaArticulos.forEach(System.out::println);
				System.out.println("**********************************************");
				
				while (!colaArticulos.isEmpty()) {
					String lineaArticulo = colaArticulos.poll();
					if (lineaArticulo.contains(//"div class=\"item__info \">"
												"span class=\"item-loading__status-bar item-loading__hide\">")) {
						String item_id = "";
						String item_url = "";
						String image_content = "";
						String price__fraction = "";
						
						lineaArticulo = cola.poll();
						lineaArticulo = cola.poll();
						
						try {
							image_content = lineaArticulo.substring(9, 117);
						} catch (Exception e) { }
						
						for (int a=1; a<=5; a++){
							lineaArticulo = colaArticulos.poll();
						}
						
						try {
							price__fraction = lineaArticulo.substring(30);
						} catch (Exception e) { }
						
						
						for (int a=1; a<=21; a++){
							lineaArticulo = colaArticulos.poll();
						}
						
						try {
							item_id = lineaArticulo.substring(42, 53);
						} catch (Exception e) { }
						
						for (int a=1; a<=7; a++){
							lineaArticulo = colaArticulos.poll();
						}
						
						System.out.println("---------------------------------");
                        System.out.println(lineaArticulo);
                        System.out.println("---------------------------------");
						
						try {
							item_url = lineaArticulo.substring(37, 115);
						} catch (Exception e) { }
						
						lista.add(new ArticuloML(item_id, item_url, "", image_content, price__fraction));
					}
				}
			}
			
		}
		
		return lista;
	}
	
}
