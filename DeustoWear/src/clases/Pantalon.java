package clases;


import java.io.Serializable;

import enumeration.Colores;
import enumeration.Sexo;
import enumeration.Talla;
import enumeration.TipoPantalon;

public class Pantalon extends Articulo implements Serializable{
	String tipoPantalon;
	
	/**
	 * Constructor por defecto de la clase Pantalon
	 */
	public Pantalon() {
		super();
	}
	
	/**
	 * Constructor especifico de la clase Pantalon
	 * @param id ID del pantalon (Primary Key)
	 * @param name Nombre del pantalon
	 * @param talla Talla del pantalon
	 * @param precio Precio del pantalon
	 * @param color Color del pantalon
	 * @param sexo Masculino / Femenino
	 * @param tipoPantalon  Largo / Corto
	 * @param imagen URL img del pantalon
	 */
	public Pantalon(int id, String name,String talla, double precio, String color,String sexo,String imagen,String tipoPantalon) {
		super(id,name,talla,precio,color,sexo,imagen);

		this.tipoPantalon = tipoPantalon;
	}

	/**
	 * Metodo que devuelve el tipo de pantalono (corto/largo)
	 * @return tipoPantalon
	 */
	public String getTipoPantalon() {
		return tipoPantalon;
	}

	/**
	 * Metodo que modifica el tipo de pantalon de un pantalon
	 * @param tipoPantalon 
	 */
	public void setTipoPantalon(String tipoPantalon) {
		this.tipoPantalon = tipoPantalon;
	}

	/**
	 * Metodo toString de la clase Pantalon
	 */
	@Override
	public String toString() {
		return "Pantalon [tipoPantalon=" + tipoPantalon + ", ID=" + ID + ", name=" + name + ", talla=" + talla
				+ ", precio=" + precio + ", color=" + color + ", sexo=" + sexo + ", imagen=" + imagen + "]";
	}
	
	
}