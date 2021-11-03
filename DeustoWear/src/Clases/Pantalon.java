package Clases;


import Enum.Color;
import Enum.Sexo;
import Enum.Talla;
import Enum.TipoPantalon;

public class Pantalon extends Articulo{
	TipoPantalon tipoPantalon;
	
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
	public Pantalon(int id, String name,Talla talla, double precio, Color color,Sexo sexo, TipoPantalon tipoPantalon,String imagen) {
		super(id,name,talla,precio,color,sexo,imagen);

		this.tipoPantalon = tipoPantalon;
	}

	public TipoPantalon getTipoPantalon() {
		return tipoPantalon;
	}

	public void setTipoPantalon(TipoPantalon tipoPantalon) {
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