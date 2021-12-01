package clases;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.TreeMap;



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
	 * */
	
	public int compareTo(Usuario u) {
		return u.nick.compareTo(this.nick);
	}
	
	public static  ArrayList<Articulo> getFavoritos() {
		return favoritos;
	}

	public  void setFavoritos(ArrayList<Articulo> favoritos) {
		Usuario.favoritos = favoritos;
	}

	/**
	 * ToString clase Usuario
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
	 * Metodo que añade un articulo a favoritos
	 * @param a Articulo a añadir 
	 * @return devuelve true si lo puede añaadir, false en caso contrario
	 */
	public static void addFavorito(Articulo a) {
		if( a !=null && !favoritos.contains(a)) {
			favoritos.add(a);
		}
	}
	
	public static void addCarrito(Articulo a) {
		if(a !=null) {
			carrito.add(a);
		}
	}
	
	public  void eliminarArticulo(int id) {
		Articulo f = carrito.remove(id);
	}

	
	/**
	 * Metodo que elimina un aritculo del array de favoritos del usuario
	 * @param id del articulo a eliminar
	 */
	public  void eliminarFavorito(int id) {
		Articulo f = favoritos.remove(id);
	}
	
	public void eliminarCarrito(ArrayList<Articulo> carrito) {
		carrito.clear();
	}
	
	
	public static double sumaTotalAPagar() {
		double sumaTotal = 0.0;
		for(Articulo a: getCarrito()) {
			sumaTotal = sumaTotal + a.getPrecio();
		}
		return sumaTotal;
	}
	
	public static double eliminarPrecioDeCesta(Articulo a) {
		double precioTotal = 0.0;
		for(Articulo art: carrito) {
			precioTotal = precioTotal - a.getPrecio();
		}
		return precioTotal;
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
	
	/*public static void comprar() {
		Double precioTotal = Usuario.sumaTotalAPagar();
		
		ventaActual = new Venta(ventaActual.getToken(),Usuario.getCarrito(),Usuario.getCarrito().size(),System.currentTimeMillis());
		Usuario.tmVentasUsuario.put(ventaActual.getToken(), ventaActual);
		System.out.println(ventaActual);
		System.out.println("Venta Registrada con Exito " + Usuario.getNick());
	}*/

	public static void comprar() {
		double precioTotal = 0;
		for(Articulo a : carrito) {
			precioTotal = precioTotal + a.getPrecio();
		}
		int token = (int)(Math.random()*100);
		Venta v = new Venta(token,carrito,carrito.size(),(int)precioTotal , System.currentTimeMillis());
		
		hmVentasUsuario.put(token, v);
		numVentas = numVentas + 1;
		System.out.println(v);
		System.out.println(numVentas + ": ventas realizadas " + getNick());
	}
	
	public static void recorrerHashVentas() {
		for(int clave : hmVentasUsuario.keySet()) {
			Venta valor = hmVentasUsuario.get(clave);
		}
	}
}
	
