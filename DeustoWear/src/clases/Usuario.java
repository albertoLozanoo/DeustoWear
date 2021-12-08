package clases;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeMap;

import javax.swing.JOptionPane;

import ventanas.VentanaInicio;



public class Usuario {
	private static String nick;
	private static String contraseya;
	public static ArrayList<Articulo> carrito = new ArrayList<>();
	public static ArrayList<Articulo> favoritos = new ArrayList<>();
	public static String avatar;
	public static int numVentas;
	
	public static Venta ventaActual = new Venta();
	public static HashMap<Integer, Venta> hmVentasUsuario = new HashMap<>();
	


	/**
	 * Constructor por defecto de la clase Usuario
	 */
	public Usuario() {
	}
	
	/**
	 * Constructor especifico de la clase Usuario
	 * @param nick Nombre del usuario (Primary Key)
	 * @param contraseya Constrseya del usuario
	 */
	public Usuario(String nick,String contraseya) {
		this.nick = nick;
		this.contraseya = contraseya;
	}
	
	public Usuario(String nick,String contrseya, String avatar) {
		this.nick = nick;
		this.contraseya = contrseya;
		this.avatar = avatar;
	}
	public static  String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public  String getContraseya() {
		return contraseya;
	}

	public void setContraseya(String contraseya) {
		this.contraseya = contraseya;
	}

	public static ArrayList<Articulo> getCarrito() {
		return carrito;
	}

	public  void setCarrito(ArrayList<Articulo> carrito) {
		Usuario.carrito = carrito;
	}
	

	public  String getLogoAvatar() {
		return avatar;
	}

	public  void setLogoAvatar(String logoAvatar) {
		Usuario.avatar = logoAvatar;
	}
	
	public  String getAvatar() {
		return avatar;
	}

	public  void setAvatar(String avatar) {
		Usuario.avatar = avatar;
	}

	public  HashMap<Integer, Venta> getHmVentasUsuario() {
		return hmVentasUsuario;
	}

	public  void setHmVentasUsuario(HashMap<Integer, Venta> tmVentasUsuario) {
		Usuario.hmVentasUsuario = hmVentasUsuario;
	}
	
	
	public  Venta getVentaActual() {
		return ventaActual;
	}

	public  void setVentaActual(Venta ventaActual) {
		Usuario.ventaActual = ventaActual;
	}

	
	/*Metodo compareTo que devuelve; 
	 
	  - Un valor negativo, si la Usuario this es menor que la Usuario u
	  - Un valor positivo, si la Usuario this es mayor que la Usuario u
	  - Un 0, Usuario this es igual que la Usuario u
	 **/
	public int compareTo(Usuario u) {
		return u.nick.compareTo(this.nick);
	}
	
	/**
	 * Metodo que devulve la lista de favoritos del usaurio
	 * @return
	 */
	public static  ArrayList<Articulo> getFavoritos() {
		return favoritos;
	}

	/**
	 * Metodo que permite modificar el arrya de favoritos del usuario
	 * @param favoritos
	 */
	public  void setFavoritos(ArrayList<Articulo> favoritos) {
		Usuario.favoritos = favoritos;
	}

	/**
	 * toString clase Usuario
	 */
	@Override
	public String toString() {
		return "Usuario [nick=" + nick + ", contraseya=" + contraseya + "]";
	}
	
	
	/**
	 * Metodo que devuelve el numero de articulos favortios que tiene el usuario
	 * @return
	 */
	public  int getNumFavoritos() {
		return favoritos.size();
	}
	
	/**
	 * Metodo que a�ade un articulo a favoritos
	 * @param a Articulo a a�adir 
	 * @return devuelve true si lo puede a�aadir, false en caso contrario
	 */
	public static void addFavorito(Articulo a) {
		if( a !=null && !favoritos.contains(a)) {
			favoritos.add(a);
		}
	}
	
	/**
	 * Metodo que permite a�adir un articulo al carrito del usuario
	 * @param a Artiuclo a a�adir en el carrito
	 */
	public static void addCarrito(Articulo a) {
		if(a !=null) {
			carrito.add(a);
		}
	}
	
	/**
	 * Metodo que permite eliminar todos los articulos del carrito del usario
	 */
	public static void limpiarCarrito() {
		carrito.clear();
	}
	
	/**
	 * Metodo que permite eliminar un articulo en concreto
	 * @param id del artiuclo a eliminar
	 */
	public  void eliminarArticulo(int id) {
		Articulo f = carrito.remove(id);
	}

	/**
	 * Meotod que permite eliminar las ventas de un usuario 
	 */
	public void eliminarVentasDeHashMap() {
		hmVentasUsuario.clear();
	}
	/**
	 * Metodo que elimina un aritculo del array de favoritos del usuario
	 * @param id del articulo a eliminar
	 */
	public  void eliminarFavorito(int id) {
		Articulo f = favoritos.remove(id);
	}
	
	public static void limpiarFavoritos() {
		favoritos.clear();
	}
	
	/**
	 * Metodo que permite eliminar todos los articulos del carrito de usuario
	 * @param carrito a eliminar
	 */
	public void eliminarCarrito(ArrayList<Articulo> carrito) {
		carrito.clear();
	}
	
	/**
	 * Metodo que permite eliminar un articulo de la lista de favoritos del usuario
	 * @param a artiuclo a eliminar de la lista de favortios
	 */
	public void eliminarFavorito (Articulo a) {
		favoritos.remove(a);
	}
	
	/**
	 * Metodo que recorre el carrito del usuario y consgie la sumaTotal del precio
	 * @return sumaTotal, cantidad total a pagar
	 */
	public static double sumaTotalAPagar() {
		double sumaTotal = 0.0;
		for(Articulo a: getCarrito()) {
			sumaTotal = sumaTotal + a.getPrecio();
		}
		return sumaTotal;
	}
	
	/**
	 * Metodo que guarda en un fichero binario los articulos favoritos del usaurio
	 */
	public static void guardarFavoritosEnFichero() {
		ObjectOutputStream oos = null;
		
		try {
			oos = new ObjectOutputStream(new FileOutputStream(Usuario.getNick()+"FAVORITOS.DAT"));
			oos.writeObject(favoritos);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(oos!=null)
				try {
					oos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
	
	
	/**
	 * Metodo que carga el array de articulos favortios del usuario con la informacion del fichero favoritos
	 */
	public static void cargarFavoritosDelFichero() {
		ObjectInputStream ois = null;
		File f = new File(Usuario.getNick()+"FAVORITOS.DAT");
		if(f.exists()) {
			try {
				ois = new ObjectInputStream(new FileInputStream(f));
				favoritos = (ArrayList<Articulo>) ois.readObject();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if(ois!=null)
					try {
						ois.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		}
	}
	

	public static void comprar() {
		double precioTotal = 0;
		for(Articulo a : carrito) {
			precioTotal = precioTotal + a.getPrecio();
		}
		int token = (int)(Math.random()*100);
		ventaActual = new Venta(token,carrito,carrito.size(),(int)precioTotal , System.currentTimeMillis());
		
		hmVentasUsuario.put(token, ventaActual);
		numVentas = numVentas + 1;
		System.out.println(ventaActual);
		System.out.println(numVentas + ": ventas realizadas " + getNick());
	}
	
	
	/**
	 * Metodo recursivo que obtiene la suma de los elementos de un array de Integer
	 * @param a ArrayList<Integer>
	 * @param i variable que ira aumentando a medida que recorra el array
	 * @return
	 */
	public static int obtenerSumaArrayInt(ArrayList<Integer> a,int i) {
		if(i<a.size()) {
			Integer valor = a.get(i);
			return valor = valor + obtenerSumaArrayInt(a, i+1);
		}else {
			return 0;
		}
	}
	
	/**
	 * Metodo que muestra el contenido por consola de un array de Integer
	 * @param a ArrayList<Integer>
	 * @param i variable que ira aumentando a medida que recorra el array
	 */
	public static void muestraArrayInt(ArrayList<Integer> a,int i) {
		if(i<a.size()) {
			Integer valor = a.get(i);
			System.out.println(valor);
			muestraArrayInt(a, i+1);
		}
	}
	
	
	
	/**
	 * Metodo que usaremos para obtener la informacion del array favoritos que cargaremos al fichero de texto
	 * @return cadena con la informacion de favoritos 
	 */
	/*
	private String obtenerFavorito() {
	
		String texto = "";
		for(Articulo fav: Usuario.getFavoritos()) {
			texto += fav.toString() + "\n";
		}
		return texto; 
		
	}
	lo dejo comentado porque falta implementarlo*/
	
	
	/** 
	 * Metodo que guarda en un fichero de texto los articulos favoritos del usuario utilizando el metodo obtenerFavorito
	 * @throws IOException 
	 */
	 
	/* 
	public void guardarFavoritosEnFicheroTxt() throws IOException {
		PrintWriter pw = null;
		
		
		try {
			String nomfich = Usuario.getNick();
			nomfich = nomfich +".txt";
			pw = new PrintWriter(new FileWriter(nomfich, true));
			String texto = obtenerFavorito();
			pw.println(texto);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			pw.flush();
			pw.close();
		}
	}
	lo dejo comentado porque falta implementarlo*/

}
	
