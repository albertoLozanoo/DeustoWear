package clases;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.TreeMap;

public class Usuario {
	private static String nick;
	private static String contraseya;
	public static ArrayList<Articulo> carrito;
	public static ArrayList<Articulo> favoritos;
	public static String avatar;
	
	
	public static Venta ventaActual;
	public static TreeMap<String, Venta> tmVentasUsuario;


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
	public static String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public static String getContraseya() {
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
	

	public static String getLogoAvatar() {
		return avatar;
	}

	public static void setLogoAvatar(String logoAvatar) {
		Usuario.avatar = logoAvatar;
	}
	
	public static String getAvatar() {
		return avatar;
	}

	public static void setAvatar(String avatar) {
		Usuario.avatar = avatar;
	}

	public static TreeMap<String, Venta> getTmVentasUsuario() {
		return tmVentasUsuario;
	}

	public static void setTmVentasUsuario(TreeMap<String, Venta> tmVentasUsuario) {
		Usuario.tmVentasUsuario = tmVentasUsuario;
	}
	
	
	public static Venta getVentaActual() {
		return ventaActual;
	}

	public static void setVentaActual(Venta ventaActual) {
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
	
	public static ArrayList<Articulo> getFavoritos() {
		return favoritos;
	}

	public static void setFavoritos(ArrayList<Articulo> favoritos) {
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
	public static int getNumFavoritos() {
		return favoritos.size();
	}
	
	/**
	 * Metodo que añade un articulo a favoritos
	 * @param f Articulo a añadir 
	 * @return devuelve true si lo puede añaadir, false en caso contrario
	 */
	public static boolean addFavorito(Articulo f) {
		boolean repetido;
		
		repetido = favoritos.contains(f.getID());
		if(repetido==false) {
			favoritos.add(f);
			guardarFavoritosEnFichero();
		}
		return repetido;
	}

	
	/**
	 * Metodo que elimina un aritculo del array de favoritos del usuario
	 * @param id del articulo a eliminar
	 */
	public static void eliminarFavorito(int id) {
		Articulo f = favoritos.remove(id);
		guardarFavoritosEnFichero();
									  
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

	
	
	
	
	
}
