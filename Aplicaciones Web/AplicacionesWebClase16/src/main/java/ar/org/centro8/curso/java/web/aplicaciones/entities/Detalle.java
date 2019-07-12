/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.org.centro8.curso.java.web.aplicaciones.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author root
 */
@Entity
@Table(name = "detalles")
@NamedQueries({
    @NamedQuery(name = "Detalle.findAll", query = "SELECT d FROM Detalle d"),
    @NamedQuery(name = "Detalle.findByIdFactura", query = "SELECT d FROM Detalle d WHERE d.detallePK.idFactura = :idFactura"),
    @NamedQuery(name = "Detalle.findByIdArticulo", query = "SELECT d FROM Detalle d WHERE d.detallePK.idArticulo = :idArticulo"),
    @NamedQuery(name = "Detalle.findByCantidad", query = "SELECT d FROM Detalle d WHERE d.cantidad = :cantidad"),
    @NamedQuery(name = "Detalle.findByPrecioUnit", query = "SELECT d FROM Detalle d WHERE d.precioUnit = :precioUnit")})
public class Detalle implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DetallePK detallePK;
    @Basic(optional = false)
    @Column(name = "cantidad")
    private int cantidad;
    @Basic(optional = false)
    @Column(name = "precioUnit")
    private double precioUnit;
    @JoinColumn(name = "idFactura", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Factura factura;
    @JoinColumn(name = "idArticulo", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Articulo articulo;

    public Detalle() { }

    public Detalle(DetallePK detallePK) {
        this.detallePK = detallePK;
    }

    public Detalle(DetallePK detallePK, int cantidad, double precioUnit) {
        this.detallePK = detallePK;
        this.cantidad = cantidad;
        this.precioUnit = precioUnit;
    }

    public Detalle(int idFactura, int idArticulo) {
        this.detallePK = new DetallePK(idFactura, idArticulo);
    }

    public DetallePK getDetallePK() {
        return detallePK;
    }

    public void setDetallePK(DetallePK detallePK) {
        this.detallePK = detallePK;
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

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detallePK != null ? detallePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detalle)) {
            return false;
        }
        Detalle other = (Detalle) object;
        if ((this.detallePK == null && other.detallePK != null) || (this.detallePK != null && !this.detallePK.equals(other.detallePK))) {
            return false;
        }
        return true;
    }

	@Override
	public String toString() {
		return "Detalle{" + "detallePK=" + detallePK + ", cantidad=" + cantidad + ", precioUnit=" + precioUnit + ", factura=" + factura + ", articulo=" + articulo + '}';
	}
}
