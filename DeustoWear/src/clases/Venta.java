package clases;

import java.io.Serializable;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Venta implements Serializable{
	
	public String nick;
	private int token;
	private ArrayList<Articulo> arrayArticulos;
	private int numArticulos;
	private double precioTotal;
	private Date fechaVenta;
	
	public Venta(int token, ArrayList<Articulo> arrayArticulos, int numArticulos,int precioTotal, Date fechaVenta) {
		super();
		this.token = token;
		this.arrayArticulos = arrayArticulos;
		this.numArticulos = numArticulos;
		this.fechaVenta = fechaVenta;
		this.precioTotal = precioTotal;
	}

	public Venta(int i, ArrayList<Articulo> arrayList, int j, long l) {
		// TODO Auto-generated constructor stub
	}
	

	public Venta() {
		// TODO Auto-generated constructor stub
	}
	
	public Venta(String nick, int token) {
		this.nick = nick;
		this.token = token;
	}
	
	public Venta(String nick,int token,int numArticulos,double precioTotal,Date fechaVenta) {
		this.nick = nick;
		this.token = token;
		this.numArticulos = numArticulos;
		this.precioTotal = precioTotal;
		this.fechaVenta = fechaVenta;
	}




	public int getToken() {
		return token;
	}

	public void setToken(int token) {
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
		if(v.getToken() == token) {
			return true;
		}else {
			return false;
		}
	}

	public int compareTo(Venta v) {
		return v.token - (this.token);
	}
	
	@Override
	public String toString() {
		return "Venta [token=" + token + ", numArticulos="+ numArticulos + ", fechaVenta=" + fechaVenta + "]";
	}
	
	
	
	
	
	
	
	
	

}
