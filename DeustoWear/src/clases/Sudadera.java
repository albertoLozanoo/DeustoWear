package clases;


import enumeration.Colores;
import enumeration.Sexo;
import enumeration.Talla;

public class Sudadera extends Articulo {
	String capucha;
	
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
	public Sudadera(int id,String name, String talla,double precio, String color, String sexo,String imagen,String capucha) {
		super(id, name,talla, precio, color,sexo,imagen);
		this.capucha = capucha;
	}

	public String getTalla() {
		return talla;
	}

	public void setTalla(String talla) {
		this.talla = talla;
	}

	public String getCapucha() {
		return capucha;
	}

	public void setCapucha(String capucha) {
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
