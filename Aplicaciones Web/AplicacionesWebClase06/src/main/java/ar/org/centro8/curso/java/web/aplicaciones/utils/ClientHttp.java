package ar.org.centro8.curso.java.web.aplicaciones.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

public class ClientHttp {
	
	public String getHTML(String url) {
		String html = "";
		try { URL u = new URL(url);
		try( BufferedReader in = new BufferedReader(
									new InputStreamReader(
										u.openStream()
									)
								);
		) {
			
			List<String> lista = in.lines().collect(Collectors.toList());
			for (String st:lista) {
				html += st + "\n";
			}
			
		} catch(Exception e) { e.printStackTrace(); };
		} catch(Exception ex) { ex.printStackTrace(); }
		
		return html;
	}
}
