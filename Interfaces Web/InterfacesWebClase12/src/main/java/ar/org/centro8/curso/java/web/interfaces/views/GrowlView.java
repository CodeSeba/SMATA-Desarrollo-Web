package ar.org.centro8.curso.java.web.interfaces.views;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "growlView")
@SessionScoped
public class GrowlView {
     
    private String message;
 
    public String getMessage() {
        return message;
    }
 
    public void setMessage(String message) {
        this.message = message;
    }
     
    public void saveMessage(String msg) {
        FacesContext context = FacesContext.getCurrentInstance();
         
        context.addMessage(null, new FacesMessage("Successful",  "Guardado: \n" + msg));
        //context.addMessage(null, new FacesMessage("Second Message", "Additional Message Detail"));
    }
}
