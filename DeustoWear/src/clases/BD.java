package clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.TreeMap;

import enumeration.Colores;
import enumeration.Sexo;
import enumeration.Talla;


public class BD {
	
	/**
	 * Crea la conexion con la BBDD
	 * @param baseDeDatos
	 * @return deuvelve la cionexion si ha sido exitosa la comunicacion
	 */
	public static Connection initBD(String baseDeDatos) {
		Connection con = null;
		
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:baseDeDatos");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	
	/**
	 * Cierra la BBDD
	 * @param con Conexion
	 */
	public static void closeBD(Connection con) {
		if(con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Metodo que crea las tablas necesarias en la BBDD
	 * @param con Conexion
	 */
	public static void crearTablas(Connection con) {
		String sent1 = "CREATE TABLE IF NOT EXISTS Articulos(ID Integer,Name String, Talla String,Precio Double,Color String, Sexo String, Imagen String)";
		String sent2 = "CREATE TABLE IF NOT EXISTS Usuarios(Nick String, Contraseña String, Avatar String)";
		
		Statement st = null;
		
		try {
			st = con.createStatement();
			st.executeUpdate(sent1);
			st.executeUpdate(sent2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(st!=null) {
				try {
					st.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * Metodo que comprueba si el nickname del usario esta ya introducido en la BBDD
	 * @param con Conexion
	 * @param nick nick introducido por el usuario
	 * @return resul 
	 * 		
	 * 		1. Si el nick ya esta regitrado y se debra escoger otro nick
	 * 		0. Si el nick no esta en la BBDD
	 */
	public static int estaRegistrado(Connection con, String nick) {
		String sentencia = "SELECT Nick FROM Usuarios WHERE Nick ='" + nick + "'";
		Statement st = null;
		int resul = 0;
		
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(sentencia);
			if(rs.next()) {
				if(rs.getString("Nick").equals(nick));
				resul = 1;
			}else {
				resul = 0;
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(st!=null) {
				try {
					st.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
		return resul;
		
	}
	/**
	 * Método que recibe los datos de un Usuario y comprueba que está registrado en la BBDD
	 * @param nom nombre del usuario
	 * @param con contraseña del usuario
	 * @return 0 si el usuario no está registrado
	 * 		   1 si el usuario está registrado pero la contraseña no es correcta
	 * 		   2 si el usuario está registrado y la contraseña es correcta
	 */
	public static int obtenerUsuario(Connection con, String nick, String c) {
		String sentencia = "SELECT Contraseña FROM Usuarios WHERE 	Nick ='"+nick+"'";
		Statement st = null;
		int resul=0;
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(sentencia);
			if(rs.next()) { //Hemos encontrado una tupla que cumple la condición
				if(rs.getString("Contraseña").equals(c)) {
					resul = 2;
				}else {
					resul = 1;
				}
			}else {
				resul = 0;
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(st!=null) {
				try {
					st.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return resul;
	}
	
	/**
	 * Método que recibe los datos de un Articulo y comprueba que está registrado en la BBDD
	 * @param nom nombre del Artiuclo
	 * @param con contraseña del Articulo
	 * @return 0 si el Articulo no está registrado
	 * 		   1 si el Articulo está registrado pero la contraseña no es correcta
	 * 		   2 si el usuario está registrado y la contraseña es correcta
	 */
	public static int obtenerArticulo(Connection con, int ID, String name) {
		String sentencia = "SELECT ID FROM Articulos WHERE 	Name ='"+name+"'";
		Statement st = null;
		int resul=0;
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(sentencia);
			if(rs.next()) { //Hemos encontrado una tupla que cumple la condición
				if(rs.getString("ID").equals(ID)) {
					resul = 2;
				}else {
					resul = 1;
				}
			}else {
				resul = 0;
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(st!=null) {
				try {
					st.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return resul;
	}
	
	/**
	 * Metodo que introduce un Usuario pasado por parametro en la tabla de Usuarios de la BBDD
	 * @param con Conexion
	 * @param u Usuario ha introducir
	 */
	
	public static void intertarUsuarioBBDD(Connection con,Usuario u) {
		String sent = "INSERT INTO Usuarios VALUES('"+u.getNick()+"','"+u.getContraseya()+"','"+u.getLogoAvatar()+"')";
		Statement st = null;
		
		try {
			st = con.createStatement();
			st.executeUpdate(sent);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(st!=null) {
				try {
					st.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public static String conseguirAvatar(Connection con,String nick) {
		String sent = "SELECT Avatar FROM Usuarios WHERE Nick ='"+nick+"'";
		Statement st = null;
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(sent);
			if(rs.next()) { //Hemos encontrado una tupla que cumple la condición
				String avatar = rs.getString("Avatar");
				rs.close();
				return avatar;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(st!=null) {
				try {
					st.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	/**
	 * Método que permite al usuario cambiar su contraseña y se actualiza su info en la BD
	 * @param con Conexion
	 * @param nick El nick del usuario al que le vamos a cambiar la contraseña
	 * @param c La nueva contraseña
	 */
	
	public static void cambiarContrasenya(Connection con, String nick, String c) {
		
		String sent = "UPDATE Usuarios SET Contraseña = '"+c+"' WHERE Nick = '"+nick+"'";
		Statement st = null;
		
		try {
			st = con.createStatement();
			st.executeUpdate(sent);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(st!=null) {
				try {
					st.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	
	/**
	 * Metodo que inserta un articulo pasado por parametro en la tabla de Articulos de la BBDD
	 * @param con Conexion
	 * @param a Articulo ha introducir en la BBDD
	 */
	public static void intertarCamisetaBBDD(Connection con,Articulo a) {
		String sent = "INSERT INTO Articulos VALUES("+a.getID()+",'"+a.getName()+"','"+a.getTalla()+"',"+a.getPrecio()+",'"+a.getColor()+"','"+a.getSexo()+"','"+a.getImagen()+"')";
		Statement st = null;
		
		try {
			st = con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			st.executeUpdate(sent);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(st!=null) {
				try {
					st.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	
	/**
	 * Metodo que elimina a un usurio pasado por parametro de la tabla de Usuarios de la BBDD 
	 * @param con Conexion
	 * @param nick Nombre del usuario a eliminar 
	 */
	
	public static void eliminarUsuarioBBDD(Connection con,String nick){
		String sent ="DELETE FROM Usuarios WHERE nick='"+nick+"'";
		Statement st = null;
		
		try {
			st = con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			st.executeUpdate(sent);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo que elimina un Articulo pasado por paremtro de la tabla de Articulos de la BBDD
	 * @param con Conexion
	 * @param ID ID del articulo a eliminar 
	 */
	
	public static void eliminarArticuloBBDD(Connection con,int ID){
		String sent ="DELETE FROM Articulos WHERE ID='"+ID+"'";
		Statement st = null;
		
		try {
			st = con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			st.executeUpdate(sent);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Método que obtiene un mapa con los Usuarios de la BBDD
	 * @param con Conexión con la BBDD
	 * @return TreeMap<String,Usuario> tmUsuario
	 */
	public static TreeMap<String, Usuario> obtenerMapaUsuarios(Connection con){
		TreeMap<String, Usuario> tmUsuario = new TreeMap<>();
		
		String sentSQL = "SELECT * FROM Usuarios";
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sentSQL);
			while(rs.next()) { //Mientras no hayamos llegado al final del conjunto de resultados
				String nick = rs.getString("Nick");
				String contraseya = rs.getString("Contraseña");
				Usuario u = new Usuario(nick,contraseya);
				tmUsuario.put(nick, u);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return tmUsuario;
	}
	
	
	/**
	 * Método que obtiene un mapa con los Articulos de la BBDD
	 * @param con Conexión con la BBDD
	 * @return TreeMap<String,Articulo>
	 */
	public static TreeMap<String, Articulo> obtenerMapaArticulos(Connection con){
		TreeMap<String, Articulo> tmArticulo = new TreeMap<>();
		
		String sentSQL = "SELECT * FROM Articulos";
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sentSQL);
			while(rs.next()) { //Mientras no hayamos llegado al final del conjunto de resultados
				if (rs instanceof Camiseta) {
					String ID = rs.getString("ID");
					String name = rs.getString("Name");
					String talla = rs.getString("Talla");
					int precio = rs.getInt("Precio");
					String color = rs.getString("Color");
					String sexo = rs.getString("Sexo");
					String imagen = rs.getString("Imagen");
				
					//Camiseta c = new Camiseta(ID,name,talla,precio,color,sexo,imagen);
					//tmArticulo.put(ID, c);		
				}
				
				if(rs instanceof Pantalon) {
					String ID = rs.getString("ID");
					String name = rs.getString("Name");
					String talla = rs.getString("Talla");
					int precio = rs.getInt("Precio");
					String color = rs.getString("Color");
					String sexo = rs.getString("Sexo");
					String imagen = rs.getString("Imagen");
					String tipoPantalon = rs.getString("TipoPantalon");
				
					//Pantalon p = new Pantalon(ID,name,talla,precio,color,sexo,imagen,tipoPantalon);
					//tmArticulo.put(ID, p);		
				}
				
				if(rs instanceof Sudadera) {
					String ID = rs.getString("ID");
					String name = rs.getString("Name");
					String talla = rs.getString("Talla");
					int precio = rs.getInt("Precio");
					String color = rs.getString("Color");
					String sexo = rs.getString("Sexo");
					String imagen = rs.getString("Imagen");
					String capucha = rs.getString("Capucha");
				
					//Sudadera s = new Sudadera(ID,name,talla,precio,color,sexo,imagen,capucha);
					//tmArticulo.put(ID, s);	
				}
			
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return tmArticulo;
	}
}