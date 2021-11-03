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
	
	/**
	 * Constructor por defecto de la clase Articulo
	 */
	public Articulo() {
		super();
	}
	
	/**
	 * Constructor de la clase Articulo 
	 * @param id Id del articulo (Primary Key)
	 * @param name Nombre del articulo
	 * @param talla Talla del articulo (S,M,L,XL,XXL)
	 * @param precio Precio del articulo
	 * @param color Color del articulo
	 * @param sexo Masculino / Femenino
	 * @param imagen URL img del articulo
	 */
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

	@Override
	public String toString() {
		return "Articulo [ID=" + ID + ", name=" + name + ", talla=" + talla + ", precio=" + precio + ", color=" + color
				+ ", sexo=" + sexo + ", imagen=" + imagen + "]";
	}
	



}