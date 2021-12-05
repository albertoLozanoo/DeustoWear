package clases;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Venta {
	
	public String nick;
	private int token;
	private ArrayList<Articulo> arrayArticulos;
	private int numArticulos;
	private double precioTotal;
	private long fechaVenta;
	
	//FORMATO DE FECHAS!!!!!!!!!!!!!!!!!!!!!!!!!
	/*private static SimpleDateFormat sdf = new SimpleDateFormat( "dd/MM/yy HH:mm:ss" );
	@Override
	public String toString() {
		return id + "\t" + producto.getId() + "\t" + sdf.format( new Date( fecha ) ) + "\t" + cantidad + " unidades";
	}*/
	
	
	public Venta(int token, ArrayList<Articulo> arrayArticulos, int numArticulos,int precioTotal, long fechaVenta) {
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

	

	public long getFechaVenta() {
		return fechaVenta;
	}

	public void setFechaVenta(long fechaVenta) {
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
