package Clases;


import Enum.Color;
import Enum.Sexo;
import Enum.Talla;

public class Sudadera extends Articulo {
	boolean capucha;
	
	/**
	 * Constructor por defecto de la clase Sudadera
	 */
	public Sudadera() {	
		super();
	}
	
	/**
	 * Constructor especifico de la clase Sudadera
	 * @param id ID de la sudadera 
	 * @param name Nombre de la sudadera
	 * @param talla Talla de la sudadera
	 * @param precio Precio de la sudadera
	 * @param color Color de la sudadera
	 * @param sexo Masculino / Femenino
	 * @param capucha Con capucha / Sin capucha
	 * @param imagen URL img de la sudadera
	 */
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
	
	/**
	 * Metodo toString de la clase Sudadera
	 */
	@Override
	public String toString() {
		return "Sudadera [capucha=" + capucha + ", ID=" + ID + ", name=" + name + ", talla=" + talla + ", precio="
				+ precio + ", color=" + color + ", sexo=" + sexo + ", imagen=" + imagen + "]";
	}
	
}
