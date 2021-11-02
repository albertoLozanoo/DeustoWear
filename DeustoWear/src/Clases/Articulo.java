package Clases;



import Enum.Color;
import Enum.Sexo;
import Enum.Talla;

public abstract class Articulo {
	int ID;
	String name;
	Talla talla;
	double precio;
	Color color;
	Sexo sexo;
	String imagen;
	
	public Articulo() {
		super();
	}
	public Articulo(int id, String name,Talla talla, double precio, Color color,Sexo sexo,String imagen) {
		ID = id;
		this.name = name;
		this.talla = talla;
		this.precio = precio;
		this.color = color;
		this.sexo = sexo;
		this.imagen = imagen;
	}
	
	
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	public Talla getTalla() {
		return talla;
	}
	public void setTalla(Talla talla) {
		this.talla = talla;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Sexo getSexo() {
		return sexo;
	}
	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}
	



}