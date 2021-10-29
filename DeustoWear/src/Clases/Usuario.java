package Clases;

import java.util.ArrayList;

public class Usuario {
	private String nick;
	private String contraseya;
	public static ArrayList<Articulo> carrito;
	public static ArrayList<Articulo> favoritos;
	

	public Usuario() {
	}
	
	public Usuario(String nick,String con) {
		this.nick = nick;
		con = contraseya;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getContraseya() {
		return contraseya;
	}

	public void setContraseya(String contraseya) {
		this.contraseya = contraseya;
	}

	public static ArrayList<Articulo> getCarrito() {
		return carrito;
	}

	public static void setCarrito(ArrayList<Articulo> carrito) {
		Usuario.carrito = carrito;
	}
	
	public int compareTo(Usuario u) {
		return u.nick.compareTo(this.nick);
	}
	
	public static ArrayList<Articulo> getFavoritos() {
		return favoritos;
	}

	public static void setFavoritos(ArrayList<Articulo> favoritos) {
		Usuario.favoritos = favoritos;
	}
	
}
