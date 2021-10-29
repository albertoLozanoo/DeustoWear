package Clases;
import javax.swing.ImageIcon;

import Enum.Color;
import Enum.Sexo;
import Enum.Talla;

public class Camiseta extends Articulo{
	
	public Camiseta() {
		super();
	}
	
	public Talla getTalla() {
		return talla;
	}

	public void setTalla(Talla talla) {
		this.talla = talla;
	}

	public Camiseta(int id, String name,Talla talla, double precio, Color color,Sexo sexo, ImageIcon imagen) {
		super(id,name,talla,precio,color,sexo,imagen);
		
		
	}
}
