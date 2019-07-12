package ar.org.centro8.curso.java.web.aplicaciones.entities;
public class Detalle {
    private int idFactura;
    private int idArticulo;
    private int cantidad;
    private double precioUnit;

    public Detalle() {
    }

    public Detalle(int idFactura, int idArticulo, int cantidad, double precioUnit) {
        this.idFactura = idFactura;
        this.idArticulo = idArticulo;
        this.cantidad = cantidad;
        this.precioUnit = precioUnit;
    }

    @Override
    public String toString() {
        return "Detalle{" + "idFactura=" + idFactura + ", idArticulo=" + idArticulo + ", cantidad=" + cantidad + ", precioUnit=" + precioUnit + '}';
    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public int getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(int idArticulo) {
        this.idArticulo = idArticulo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnit() {
        return precioUnit;
    }

    public void setPrecioUnit(double precioUnit) {
        this.precioUnit = precioUnit;
    }

    
    
}