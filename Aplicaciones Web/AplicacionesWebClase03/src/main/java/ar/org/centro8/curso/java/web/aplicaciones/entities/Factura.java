package ar.org.centro8.curso.java.web.aplicaciones.entities;

import java.util.Date;

public class Factura {
	private int id;
	private char letra;
	private int numero;
	private Date fecha;
	private double monto;
	private int idCliente;
	
	public Factura() { }

	public Factura(char letra, int numero, Date fecha, Double monto, int idCliente) {
		this.letra = letra;
		this.numero = numero;
		this.fecha = fecha;
		this.monto = monto;
		this.idCliente = idCliente;
	}

	public Factura(int id, char letra, int numero, Date fecha, Double monto, int idCliente) {
		this.id = id;
		this.letra = letra;
		this.numero = numero;
		this.fecha = fecha;
		this.monto = monto;
		this.idCliente = idCliente;
	}

	@Override
	public String toString() {
		return "Factura{" + "id=" + id + ", letra=" + letra + ", numero=" + numero + ", fecha=" + fecha + ", monto=" + monto + ", idCliente=" + idCliente + '}';
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public char getLetra() {
		return letra;
	}

	public void setLetra(char letra) {
		this.letra = letra;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	
}
