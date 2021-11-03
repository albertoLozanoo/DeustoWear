package Clases;


import Enum.Color;
import Enum.Sexo;
import Enum.Talla;

public class Camiseta extends Articulo{
	
	/**
	 * Constructor de la calse Camiseta
	 */
	public Camiseta() {
		super();
	}
	
	/**
	 * Constructor de la calse Camiseta
	 * @param id
	 * @param name
	 * @param talla
	 * @param precio
	 * @param color
	 * @param sexo
	 * @param imagen
	 */
	public Camiseta(int id, String name,Talla talla, double precio, Color color,Sexo sexo, String imagen) {
		super(id,name,talla,precio,color,sexo,imagen);
		
	}
	
	public Talla getTalla() {
		return talla;
	}

	public void setTalla(Talla talla) {
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
