package clases;


import java.io.Serializable;

import enumeration.Colores;
import enumeration.Sexo;
import enumeration.Talla;

public class Camiseta extends Articulo implements Serializable{
	
	/**
	 * Constructor de la calse Camiseta
	 */
	public Camiseta() {
		super();
	}
	
	/**
	 * Constructor de la clase Camiseta
	 * @param id
	 * @param name
	 * @param talla
	 * @param precio
	 * @param color
	 * @param sexo
	 * @param imagen
	 */
	public Camiseta(int id, String name,String talla, double precio, String color,String sexo, String imagen) {
		super(id,name,talla,precio,color,sexo,imagen);
		
	}
	
	/**
	 * Metodo que devuelve la talla de una camiseta
	 * @return talla de la camiseta
	 */
	public String getTalla() {
		return talla;
	}

	/**
	 * Metodo que modifica la talla de una camiseta
	 * @param talla de la camiseta
	 */
	public void setTalla(String talla) {
		this.talla = talla;
	}

	/**
	 * Metodo toString de la clase Camiseta
	 */
	@Override
	public String toString() {
		return "Camiseta [ID=" + ID + ", name=" + name + ", talla=" + talla + ", precio=" + precio + ", color=" + color
				+ ", sexo=" + sexo + ", imagen=" + imagen + "]";
	}
	
	

	
}
