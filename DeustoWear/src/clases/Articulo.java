package clases;



import enumeration.Colores;
import enumeration.Sexo;
import enumeration.Talla;

public abstract class Articulo {
	int ID;
	String name;
	String talla;
	double precio;
	String color;
	String sexo;
	String imagen;
	
	/**
	 * Constructor por defecto de la clase Articulo
	 */
	public Articulo() {
		super();
	}
	
	/**
	 * Constructor de la clase Articulo 
	 * @param id ID del articulo (Primary Key)
	 * @param name Nombre del articulo
	 * @param talla Talla del articulo (S,M,L,XL,XXL)
	 * @param precio Precio del articulo
	 * @param color Color del articulo
	 * @param sexo Masculino / Femenino
	 * @param imagen URL img del articulo
	 */
	public Articulo(int id, String name,String talla, double precio, String color,String sexo,String imagen) {
		ID = id;
		this.name = name;
		this.talla = talla;
		this.precio = precio;
		this.color = color;
		this.sexo = sexo;
		this.imagen = imagen;
	}
	
	/**
	 * Metodo que devuelve el ID de un articulo
	 * @return ID del articulo
	 */
	public int getID() {
		return ID;
	}
	/**
	 * Metodo que modifica el id de un articulo
	 * @param iD del articulo
	 */
	public void setID(int iD) {
		ID = iD;
	}
	
	/**
	 * Metodo que devuelve el precio de un articulo
	 * @return precio del articulo
	 */
	public double getPrecio() {
		return precio;
	}
	/**
	 * Metodo que modifica el precio de un articulo
	 * @param precio del articulo
	 */
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	/**
	 * Metodo que devuelve la talla de un articulo
	 * @return talla del articulo
	 */
	public String getTalla() {
		return talla;
	}
	/**
	 * Metodo que modifica la talla de un articulo
	 * @param talla del articulo
	 */
	public void setTalla(String talla) {
		this.talla = talla;
	}
	
	/**
	 * Metodo que devuelve el color de un articulo
	 * @return color del articulo
	 */
	public String getColor() {
		return color;
	}
	/**
	 * Metodo que modifica el color de un articulo
	 * @param color del articulo
	 */
	public void setColor(String color) {
		this.color = color;
	}
	
	/**
	 * Metodo que devuelve la imagen de un articulo
	 * @return imagen del articulo
	 */
	public String getImagen() {
		return imagen;
	}
	/**
	 * Metodo que modifica la imagen de un articulo
	 * @param imagen del articulo
	 */
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	
	/**
	 * Metodo que devuelve el nombre de un articulo
	 * @return nombre del articulo
	 */
	public String getName() {
		return name;
	}
	/**
	 * Metodo que modifica el nombre de un articulo
	 * @param nombre del articulo
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Metodo que devuelve el sexo de un articulo
	 * @return sexo del articulo
	 */
	public String getSexo() {
		return sexo;
	}
	/**
	 * Metodo que modifica el sexo de un articulo
	 * @param sexo del articulo
	 */
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	/**
	 * Metodo toString de la clase Articulo
	 */
	@Override
	public String toString() {
		return "Articulo [ID=" + ID + ", name=" + name + ", talla=" + talla + ", precio=" + precio + ", color=" + color
				+ ", sexo=" + sexo + "]";
	}
	



}