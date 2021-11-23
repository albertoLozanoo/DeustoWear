package clases;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Venta {
	
	private String token;
	private ArrayList<Articulo> arrayArticulos;
	private int numArticulos;
	private double precioTotal;
	private Connection con;
	private Date fechaVenta;
	public static SimpleDateFormat sdf = new SimpleDateFormat("EE, d MM yyyy HH:mm:ss");
	
	public Venta() {
	}

	public Venta(String token, ArrayList<Articulo> arrayArticulos, int numArticulos,int precioTotal, Connection con, Date fechaVenta) {
		super();
		this.token = token;
		this.arrayArticulos = arrayArticulos;
		this.numArticulos = numArticulos;
		this.con = con;
		this.fechaVenta = fechaVenta;
		this.precioTotal = precioTotal;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}


	public ArrayList<Articulo> getArrayArticulos() {
		return arrayArticulos;
	}

	public void setArrayArticulos(ArrayList<Articulo> arrayArticulos) {
		this.arrayArticulos = arrayArticulos;
	}
	
	public void addArticuloAventaActual(Articulo a) {
		if(a != null && !this.arrayArticulos.contains(a)) {
			this.arrayArticulos.add(a);
			numArticulos = numArticulos + 1;
			precioTotal = precioTotal + a.getPrecio();
		}
	}

	public int getNumArticulos() {
		return numArticulos;
	}

	public void setNumArticulos(int numArticulos) {
		this.numArticulos = numArticulos;
	}

	public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}

	public Date getFechaVenta() {
		return fechaVenta;
	}

	public void setFechaVenta(Date fechaVenta) {
		this.fechaVenta = fechaVenta;
	}
	
	public double getPrecioTotal() {
		return precioTotal;
	}

	public void setPrecioTotal(double precioTotal) {
		this.precioTotal = precioTotal;
	}

	public boolean equals(Venta v) {
		if(v.getToken().contentEquals(token)) {
			return true;
		}else {
			return false;
		}
	}

	public int compareTo(Venta v) {
		return v.token.compareTo(this.token);
	}
	
	@Override
	public String toString() {
		return "Venta [token=" + token + ", "+ "arrayArticulos=" + arrayArticulos + ", numArticulos="+ numArticulos + ", con=" + con + ", fechaVenta=" + fechaVenta + "]";
	}
	
	
	
	
	
	
	
	

}
