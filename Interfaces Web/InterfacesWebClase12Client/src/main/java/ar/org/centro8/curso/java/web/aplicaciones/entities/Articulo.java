package ar.org.centro8.curso.java.web.aplicaciones.entities;

public class Articulo {
	private int id;
	private String descripcion;
	private double costo;
	private double precio;
	private int stock;
	private int stockMin;
	private int stockMax;
	
	public Articulo() { }

	public Articulo(String descripcion, Double costo, Double precio, int stock, int stockMin, int stockMax) {
		this.descripcion = descripcion;
		this.costo = costo;
		this.precio = precio;
		this.stock = stock;
		this.stockMin = stockMin;
		this.stockMax = stockMax;
	}

	public Articulo(int id, String descripcion, Double costo, Double precio, int stock, int stockMin, int stockMax) {
		this.id = id;
		this.descripcion = descripcion;
		this.costo = costo;
		this.precio = precio;
		this.stock = stock;
		this.stockMin = stockMin;
		this.stockMax = stockMax;
	}

	@Override
	public String toString() {
		return "Articulo{" + "id=" + id + ", descripcion=" + descripcion + ", costo=" + costo + ", precio=" + precio + ", stock=" + stock + ", stockMin=" + stockMin + ", stockMax=" + stockMax + '}';
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getStockMin() {
		return stockMin;
	}

	public void setStockMin(int stockMin) {
		this.stockMin = stockMin;
	}

	public int getStockMax() {
		return stockMax;
	}

	public void setStockMax(int stockMax) {
		this.stockMax = stockMax;
	}
	
}
