package clases;


import enumeration.Colores;
import enumeration.Sexo;
import enumeration.Talla;

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
	public Camiseta(int id, String name,String talla, int precio, String color,String sexo, String imagen) {
		super(id,name,talla,precio,color,sexo,imagen);
		
	}
	
	public String getTalla() {
		return talla;
	}

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
