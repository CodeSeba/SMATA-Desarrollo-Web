package ar.org.centro8.curso.java.web.interfaces.managed.beans;

import ar.org.centro8.curso.java.web.aplicaciones.enums.TipoDocumento;
import java.util.Arrays;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "tipoDocumentoMB")
@SessionScoped
public class TipoDocumentoMB {

	public List<TipoDocumento> getAll() {
		return Arrays.asList(TipoDocumento.values());
	}
	
}
