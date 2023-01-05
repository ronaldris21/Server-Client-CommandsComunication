package edu.ucam.domain;

public class Venta {
	private String precioVenta;
	private Coche coche;
	public Venta(String precioVenta, Coche coche) {
		super();
		this.precioVenta = precioVenta;
		this.coche = coche;
	}
	public String getPrecioVenta() {
		return precioVenta;
	}
	public void setPrecioVenta(String precioVenta) {
		this.precioVenta = precioVenta;
	}
	public Coche getCoche() {
		return coche;
	}
	public void setCoche(Coche coche) {
		this.coche = coche;
	}
}
