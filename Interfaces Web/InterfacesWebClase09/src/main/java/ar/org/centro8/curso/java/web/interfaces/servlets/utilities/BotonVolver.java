package ar.org.centro8.curso.java.web.interfaces.servlets.utilities;
public class BotonVolver {
    public String getBoton(){
        return "<form action='http://localhost:8082/InterfacesClase04/users.html' >"
                    + "<input type=submit value=Volver >"
                    + "</form>";
    }
}