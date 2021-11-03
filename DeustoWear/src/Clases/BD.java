package Clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.TreeMap;


public class BD {
	
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
	
	public static void crearTablas(Connection con) {
		String sent1 = "CREATE TABLE IF NOT EXISTS Articulos(ID Integer,Name String, Talla String,Precio Double,Color String, Sexo String, Imagen String)";
		String sent2 = "CREATE TABLE IF NOT EXISTS Usuarios(Nick String, Contrase�a String)";
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
	 * M�todo que recibe los datos de un Usuario y comprueba que est� registrado en la BBDD
	 * @param nom nombre del usuario
	 * @param con contrase�a del usuario
	 * @return 0 si el usuario no est� registrado
	 * 		   1 si el usuario est� registrado pero la contrase�a no es correcta
	 * 		   2 si el usuario est� registrado y la contrase�a es correcta
	 */
	public static int obtenerUsuario(Connection con, String nick, String c) {
		String sentencia = "SELECT Contrase�a FROM Usuarios WHERE 	Nick ='"+nick+"'";
		Statement st = null;
		int resul=0;
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(sentencia);
			if(rs.next()) { //Hemos encontrado una tupla que cumple la condici�n
				if(rs.getString("Contrase�a").equals(c)) {
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
	 * M�todo que recibe los datos de un Usuario y comprueba que est� registrado en la BBDD
	 * @param nom nombre del usuario
	 * @param con contrase�a del usuario
	 * @return 0 si el usuario no est� registrado
	 * 		   1 si el usuario est� registrado pero la contrase�a no es correcta
	 * 		   2 si el usuario est� registrado y la contrase�a es correcta
	 */
	public static int obtenerArticulo(Connection con, int ID, String name) {
		String sentencia = "SELECT ID FROM Articulos WHERE 	Name ='"+name+"'";
		Statement st = null;
		int resul=0;
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(sentencia);
			if(rs.next()) { //Hemos encontrado una tupla que cumple la condici�n
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
	
	
	
	public static void intertarUsuarioBBDD(Connection con,Usuario u) {
		String sent = "INSERT INTO Usuarios VALUES('"+u.getNick()+"','"+u.getContraseya()+"')";
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
	
	public static void intertarArticuloBBDD(Connection con,Articulo a) {
		String sent = "INSERT INTO Articulos VALUES('"+a.getID()+"','"+a.getName()+"','"+a.getPrecio()+"','"+a.getColor()+"','"+a.getSexo()+"','"+a.getImagen()+"')";
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
	 * M�todo que obtiene un mapa con los Usuarios de la BBDD
	 * @param con Conexi�n con la BBDD
	 * @return TreeMap<String,Usuario>
	 */
	public static TreeMap<String, Usuario> obtenerMapaUsuarios(Connection con){
		TreeMap<String, Usuario> tmUsuario = new TreeMap<>();
		
		String sentSQL = "SELECT * FROM Usuarios";
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sentSQL);
			while(rs.next()) { //Mientras no hayamos llegado al final del conjunto de resultados
				String nick = rs.getString("Nick");
				String contraseya = rs.getString("Contrase�a");
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
	 * M�todo que obtiene un mapa con los Articulos de la BBDD
	 * @param con Conexi�n con la BBDD
	 * @return TreeMap<String,Articulo>
	 */
	public static TreeMap<String, Articulo> obtenerMapaArticulos(Connection con){
		TreeMap<String, Articulo> tmArticulo = new TreeMap<>();
		
		String sentSQL = "SELECT * FROM Articulos";
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sentSQL);
			while(rs.next()) { //Mientras no hayamos llegado al final del conjunto de resultados
				String ID = rs.getString("ID");
				String name = rs.getString("Name");
				String talla = rs.getString("Talla");
				int precio = rs.getInt("Precio");
				String color = rs.getString("Color");
				String sexo = rs.getString("Sexo");
				String imagen = rs.getString("Imagen");
			
				Articulo a = new Articulo(ID,name,talla,precio,color,sexo,imagen);
				
				tmArticulo.put(ID, a);
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