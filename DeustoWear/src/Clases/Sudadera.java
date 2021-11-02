package Clases;


import Enum.Color;
import Enum.Sexo;
import Enum.Talla;

public class Sudadera extends Articulo {
	boolean capucha;
	
	public Sudadera() {	
		super();
	}
	
	public Sudadera(int id,String name, Talla talla,double precio, Color color, Sexo sexo,boolean capucha,String imagen) {
		super(id, name,talla, precio, color,sexo,imagen);
		this.capucha = capucha;
	}

	public Talla getTalla() {
		return talla;
	}

	public void setTalla(Talla talla) {
		this.talla = talla;
	}

	public boolean isCapucha() {
		return capucha;
	}

	public void setCapucha(boolean capucha) {
		this.capucha = capucha;
	}
	
}
