package ar.org.centro8.curso.java.web.aplicaciones.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "articulos")
@NamedQueries({
    @NamedQuery(name = "Articulo.findAll", query = "SELECT a FROM Articulo a"),
    @NamedQuery(name = "Articulo.findById", query = "SELECT a FROM Articulo a WHERE a.id = :id"),
    @NamedQuery(name = "Articulo.findByDescripcion", query = "SELECT a FROM Articulo a WHERE a.descripcion = :descripcion"),
    @NamedQuery(name = "Articulo.findByCosto", query = "SELECT a FROM Articulo a WHERE a.costo = :costo"),
    @NamedQuery(name = "Articulo.findByPrecio", query = "SELECT a FROM Articulo a WHERE a.precio = :precio"),
    @NamedQuery(name = "Articulo.findByStock", query = "SELECT a FROM Articulo a WHERE a.stock = :stock"),
    @NamedQuery(name = "Articulo.findByStockMin", query = "SELECT a FROM Articulo a WHERE a.stockMin = :stockMin"),
    @NamedQuery(name = "Articulo.findByStockMax", query = "SELECT a FROM Articulo a WHERE a.stockMax = :stockMax")})
public class Articulo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "descripcion")
    private String descripcion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "costo")
    private Double costo;
    @Column(name = "precio")
    private Double precio;
    @Column(name = "stock")
    private Integer stock;
    @Column(name = "stockMin")
    private Integer stockMin;
    @Column(name = "stockMax")
    private Integer stockMax;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "articulo", fetch = FetchType.LAZY)
    private List<Detalle> detalleList;

    public Articulo() {
    }

    public Articulo(String descripcion, Double costo, Double precio, Integer stock, Integer stockMin, Integer stockMax, List<Detalle> detalleList) {
        this.descripcion = descripcion;
        this.costo = costo;
        this.precio = precio;
        this.stock = stock;
        this.stockMin = stockMin;
        this.stockMax = stockMax;
        this.detalleList = detalleList;
    }

    public Articulo(Integer id, String descripcion, Double costo, Double precio, Integer stock, Integer stockMin, Integer stockMax) {
        this.id = id;
        this.descripcion = descripcion;
        this.costo = costo;
        this.precio = precio;
        this.stock = stock;
        this.stockMin = stockMin;
        this.stockMax = stockMax;
    }

    public Articulo(String descripcion, Double costo, Double precio, Integer stock, Integer stockMin, Integer stockMax) {
        this.descripcion = descripcion;
        this.costo = costo;
        this.precio = precio;
        this.stock = stock;
        this.stockMin = stockMin;
        this.stockMax = stockMax;
    }

    public Articulo(Integer id, String descripcion, Double costo, Double precio, Integer stock, Integer stockMin, Integer stockMax, List<Detalle> detalleList) {
        this.id = id;
        this.descripcion = descripcion;
        this.costo = costo;
        this.precio = precio;
        this.stock = stock;
        this.stockMin = stockMin;
        this.stockMax = stockMax;
        this.detalleList = detalleList;
    }
    
    

    public Articulo(Integer id) {
        this.id = id;
    }

    public Articulo(Integer id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getStockMin() {
        return stockMin;
    }

    public void setStockMin(Integer stockMin) {
        this.stockMin = stockMin;
    }

    public Integer getStockMax() {
        return stockMax;
    }

    public void setStockMax(Integer stockMax) {
        this.stockMax = stockMax;
    }

    public List<Detalle> getDetalleList() {
        return detalleList;
    }

    public void setDetalleList(List<Detalle> detalleList) {
        this.detalleList = detalleList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Articulo)) {
            return false;
        }
        Articulo other = (Articulo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Articulo{" + "id=" + id + ", descripcion=" + descripcion + ", costo=" + costo + ", precio=" + precio + ", stock=" + stock + ", stockMin=" + stockMin + ", stockMax=" + stockMax + '}';
    }


    
}
