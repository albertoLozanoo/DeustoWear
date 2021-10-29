package Clases;
import javax.swing.ImageIcon;

import Enum.Color;
import Enum.Sexo;
import Enum.Talla;
import Enum.TipoPantalon;

public class Pantalon extends Articulo{
	TipoPantalon tipoPantalon;
	
	public Pantalon() {
		super();
	}
	
	public Pantalon(int id, String name,Talla talla, double precio, Color color,Sexo sexo, TipoPantalon tipoPantalon,ImageIcon imagen) {
		super(id,name,talla,precio,color,sexo,imagen);

		this.tipoPantalon = tipoPantalon;
	}

	public TipoPantalon getTipoPantalon() {
		return tipoPantalon;
	}

	public void setTipoPantalon(TipoPantalon tipoPantalon) {
		this.tipoPantalon = tipoPantalon;
	}
	
}