package clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import enumeration.Colores;
import enumeration.Sexo;
import enumeration.Talla;
import enumeration.TipoPantalon;



public class BD {
	
	private static Logger logger = Logger.getLogger( "BaseDatos" );
	
	/**
	 * Crea la conexion con la BBDD
	 * @param baseDeDatos
	 * @return deuvelve la cionexion si ha sido exitosa la comunicacion
	 * @throws DeustoException 
	 */
	public static Connection initBD(String nomBD) throws DeustoException {
		Connection con = null;
		
		try {
			logger.log( Level.INFO, "Carga de libreria org.sqlite.JDBC" );
			Class.forName("org.sqlite.JDBC");
			logger.log( Level.INFO, "Abriendo conexion con " + nomBD );
			con = DriverManager.getConnection("jdbc:sqlite:baseDeDatos");
		} catch (ClassNotFoundException e) {
			logger.log( Level.SEVERE, "Excepcion", e );
			throw new DeustoException("ERROR! LOADING DRIVER");
		} catch (Exception e) {
			logger.log( Level.SEVERE, "Excepcion", e );
		}
		return con;
	}
	
	/**
	 * Cierra la BBDD
	 * @param con Conexion
	 * @throws DeustoException 
	 */
	public static void closeBD(Connection con) throws DeustoException {
		if(con!=null) {
			try {
				con.close();
				logger.log( Level.INFO, "Cerrando conexion" );
			} catch (Exception e) {
				logger.log( Level.SEVERE, "Excepcion", e );
				throw new DeustoException("ERROR! CONNECTION BBDD FAILED");
			}
		}
	}
	
	/**
	 * Metodo que crea las tablas necesarias en la BBDD
	 * @param con Conexion
	 * @throws DeustoException 
	 * 
	 * 
	 * int numArticulos = rs.getInt("Num Articulo");
				double precioTotal = rs.getDouble("Precio total");
				String fVenta = rs.getString("fVenta");
	 */
	public static void crearTablas(Connection con) throws DeustoException {
		String sent1 = "CREATE TABLE IF NOT EXISTS Articulos(ID Integer,Name String, Talla String,Precio Double,Color String, Sexo String, Imagen String, TipoPantalon String, Capucha String, TipoArticulo String)";
		String sent2 = "CREATE TABLE IF NOT EXISTS Usuarios(Nick String, Contraseya String, Avatar String)";
		String sent3 = "CREATE TABLE IF NOT EXISTS Ventas(Nick String, Token long, NumArticulos int, PrecioTotal Double,fVenta String)";
		
		Statement st = null;
		
		try {
			st = con.createStatement();
			logger.log( Level.INFO, "Statement: " + sent1 );
			st.executeUpdate(sent1);
			logger.log( Level.INFO, "Statement: " + sent2 );
			st.executeUpdate(sent2);
			logger.log( Level.INFO, "Statement: " + sent2 );
			st.executeUpdate(sent3);
		} catch (Exception e) {
			logger.log( Level.SEVERE, "Excepcion", e );
			throw new DeustoException("ERROR! CREATION TABLES FAILED");
		} finally {
			if(st!=null) {
				try {
					st.close();
				} catch (Exception e) {
					logger.log( Level.SEVERE, "Excepcion", e );
					throw new DeustoException("ERROR! CLOSING STATEMENT FAILED");
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
	 * @throws DeustoException 
	 */
	public static int estaRegistrado(Connection con, String nick) throws DeustoException {
		String sentencia = "SELECT Nick FROM Usuarios WHERE Nick ='" + nick + "'";
		Statement st = null;
		int resul = 0;
		
		try {
			st = con.createStatement();
			logger.log( Level.INFO, "Statement: " + st );
			ResultSet rs = st.executeQuery(sentencia);
			if(rs.next()) {
				if(rs.getString("Nick").equals(nick));
				resul = 1;
			}else {
				resul = 0;
			}
			rs.close();
		} catch (Exception e) {
			logger.log( Level.SEVERE, "Excepcion", e );
			throw new DeustoException("ERROR! STATEMENT FAILED");
		} finally {
			if(st!=null) {
				try {
					st.close();
				} catch (Exception e) {
					logger.log( Level.SEVERE, "Excepcion", e );
					throw new DeustoException("ERROR! CLOSING STATEMENT FAILED");
				}
			}
		}
		return resul;
	}
	/**
	 * M???todo que recibe los datos de un Usuario y comprueba que est??? registrado en la BBDD
	 * @param nom nombre del usuario
	 * @param con contrase???a del usuario
	 * @return 0 si el usuario no est??? registrado
	 * 		   1 si el usuario est??? registrado pero la contrase???a no es correcta
	 * 		   2 si el usuario est??? registrado y la contrase???a es correcta
	 * @throws DeustoException 
	 */
	public static int obtenerUsuario(Connection con, String nick, String c) throws DeustoException {
		String sentencia = "SELECT Contraseya FROM Usuarios WHERE 	Nick ='"+nick+"'";
		Statement st = null;
		int resul=0;
		try {
			st = con.createStatement();
			logger.log( Level.INFO, "Statement: " + st );
			ResultSet rs = st.executeQuery(sentencia);
			if(rs.next()) { //Hemos encontrado una tupla que cumple la condici???n
				if(rs.getString("Contraseya").equals(c)) {
					resul = 2;
				}else {
					resul = 1;
				}
			}else {
				resul = 0;
			}
			rs.close();
		} catch (Exception e) {
			logger.log( Level.SEVERE, "Excepcion", e );
			throw new DeustoException("ERROR! STATEMENT FAILED");
		} finally {
			if(st!=null) {
				try {
					st.close();
				} catch (Exception e) {
					logger.log( Level.SEVERE, "Excepcion", e );
					throw new DeustoException("ERROR! CLOSING STATEMENT FAILED");
				}
			}
		}
		return resul;
	}
	
	/**
	 * M???todo que recibe los datos de un Articulo y comprueba que est??? registrado en la BBDD
	 * @param nom nombre del Artiuclo
	 * @param con contrase???a del Articulo
	 * @return 0 si el Articulo no est??? registrado
	 * 		   1 si el Articulo est??? registrado pero la contrase???a no es correcta
	 * 		   2 si el usuario est??? registrado y la contrase???a es correcta
	 * @throws DeustoException 
	 */
	public static int obtenerArticulo(Connection con, int ID, String name) throws DeustoException {
		String sentencia = "SELECT ID FROM Articulos WHERE 	Name ='"+name+"'";
		Statement st = null;
		int resul=0;
		try {
			st = con.createStatement();
			logger.log( Level.INFO, "Statement: " + st );
			ResultSet rs = st.executeQuery(sentencia);
			if(rs.next()) { //Hemos encontrado una tupla que cumple la condici???n
				if(rs.getString("ID").equals(ID)) {
					resul = 2;
				}else {
					resul = 1;
				}
			}else {
				resul = 0;
			}
			rs.close();
		} catch (Exception e) {
			logger.log( Level.SEVERE, "Excepcion", e );
			throw new DeustoException("ERROR! STATEMENT FAILED");
		} finally {
			if(st!=null) {
				try {
					st.close();
				} catch (Exception e) {
					logger.log( Level.SEVERE, "Excepcion", e );
					throw new DeustoException("ERROR! CLOSING STATEMENT FAILED");
				}
			}
		}
		return resul;
	}
	
	/**
	 * Metodo que introduce un Usuario pasado por parametro en la tabla de Usuarios de la BBDD
	 * @param con Conexion
	 * @param u Usuario ha introducir
	 * @throws DeustoException 
	 */
	
	public static void intertarUsuarioBBDD(Connection con,Usuario u) throws DeustoException {
		String sent = "INSERT INTO Usuarios VALUES('"+u.getNick()+"','"+u.getContraseya()+"','"+u.getLogoAvatar()+"')";
		Statement st = null;
		
		try {
			st = con.createStatement();
			logger.log( Level.INFO, "Statement: " + st );
			st.executeUpdate(sent);
		} catch (Exception e) {
			logger.log( Level.SEVERE, "Excepcion", e );
			throw new DeustoException("ERROR! STATEMENT FAILED");
		} finally {
			if(st!=null) {
				try {
					st.close();
				} catch (Exception e) {
					logger.log( Level.SEVERE, "Excepcion", e );
					throw new DeustoException("ERROR! CLOSING STATEMENT FAILED");
				}
			}
		}
	}
	
	/**
	 * Metodo que devuelve el path del avatar de un usuario
	 * @param con Conexion con la BBDD
	 * @param nick del usuario
	 * @return String con el path de la imagen
	 * @throws DeustoException
	 */
	public static String conseguirAvatar(Connection con,String nick) throws DeustoException {
		String sent = "SELECT Avatar FROM Usuarios WHERE Nick ='"+nick+"'";
		Statement st = null;
		try {
			st = con.createStatement();
			logger.log( Level.INFO, "Statement: " + st );
			ResultSet rs = st.executeQuery(sent);
			if(rs.next()) { //Hemos encontrado una tupla que cumple la condici???n
				String avatar = rs.getString("Avatar");
				rs.close();
				return avatar;
			}
		} catch (Exception e) {
			logger.log( Level.SEVERE, "Excepcion", e );
			throw new DeustoException("ERROR! STATEMENT FAILED");
		} finally {
			if(st!=null) {
				try {
					st.close();
				} catch (Exception e) {
					logger.log( Level.SEVERE, "Excepcion", e );
					throw new DeustoException("ERROR! CLOSING STATEMENT FAILED");
				}
			}
		}
		return null;
	}
	
	/**
	 * M???todo que permite al usuario cambiar su contrase???a y se actualiza su info en la BD
	 * @param con Conexion
	 * @param nick El nick del usuario al que le vamos a cambiar la contrase???a
	 * @param c La nueva contrase???a
	 * @throws DeustoException 
	 */
	public static void cambiarContrasenya(Connection con, String nick, String c) throws DeustoException {
		
		String sent = "UPDATE Usuarios SET Contraseya = '"+c+"' WHERE Nick = '"+nick+"'";
		Statement st = null;
		
		try {
			st = con.createStatement();
			logger.log( Level.INFO, "Statement: " + st );
			st.executeUpdate(sent);
		} catch (Exception e) {
			logger.log( Level.SEVERE, "Excepcion", e );
			throw new DeustoException("ERROR! STATEMENT FAILED");
		} finally {
			if(st!=null) {
				try {
					st.close();
				} catch (Exception e) {
					logger.log( Level.SEVERE, "Excepcion", e );
					throw new DeustoException("ERROR! CLOSING STATEMENT FAILED");
				}
			}
		}
		
	}
	
	/**
	 * Metodo que inserta una camiseta pasada por parametro en la tabla de Articulos de la BBDD
	 * @param con Conexion
	 * @param a Camiseta ha introducir en la BBDD
	 * @throws DeustoException 
	 */
	public static void insertarCamisetaBBDD(Connection con,Articulo a) throws DeustoException {
		String sent = "INSERT INTO Articulos VALUES("+a.getID()+",'"+a.getName()+"','"+a.getTalla()+"',"+a.getPrecio()+",'"+a.getColor()+"','"+a.getSexo()+"','"+a.getImagen()+"','null','null','c')";
		Statement st = null;
		
		try {
			st = con.createStatement();
			logger.log( Level.INFO, "Statement: " + st );
		} catch (Exception e) {
			logger.log( Level.SEVERE, "Excepcion", e );
		}
		try {
			st.executeUpdate(sent);
		} catch (Exception e) {
			logger.log( Level.SEVERE, "Excepcion", e );
			throw new DeustoException("ERROR! STATEMENT FAILED");
		} finally {
			if(st!=null) {
				try {
					st.close();
				} catch (Exception e) {
					logger.log( Level.SEVERE, "Excepcion", e );
					throw new DeustoException("ERROR! CLOSING STATEMENT FAILED");
				}
			}
		}
		
	}
	
	/**
	 * Metodo que inserta un pantalon pasado por parametro en la tabla de Articulos de la BBDD
	 * @param con Conexion
	 * @param a Pantalon ha introducir en la BBDD
	 * @throws DeustoException 
	 */
	public static void insertarPantalonBBDD(Connection con,Articulo a) throws DeustoException {
		if (a instanceof Pantalon) { 
			String sent = "INSERT INTO Articulos VALUES("+a.getID()+",'"+a.getName()+"','"+a.getTalla()+"',"+a.getPrecio()+",'"+a.getColor()+"','"+a.getSexo()+"','"+a.getImagen()+"','"+((Pantalon) a).getTipoPantalon()+"','null','p')";                             
			Statement st = null;
		
			try {
				st = con.createStatement();
				logger.log( Level.INFO, "Statement: " + st );
			} catch (Exception e) {
				logger.log( Level.SEVERE, "Excepcion", e );
			}
			try {
				st.executeUpdate(sent);
			} catch (Exception e) {
				logger.log( Level.SEVERE, "Excepcion", e );
				throw new DeustoException("ERROR! STATEMENT FAILED");
			} finally {
				if(st!=null) {
					try {
						st.close();
					} catch (Exception e) {
						logger.log( Level.SEVERE, "Excepcion", e );
						throw new DeustoException("ERROR! CLOSING STATEMENT FAILED");
					}
				}
			}
		
		}
	}
	
	/**
	 * Metodo que inserta una sudadera pasado por parametro en la tabla de Articulos de la BBDD
	 * @param con Conexion
	 * @param a Sudadera ha introducir en la BBDD
	 * @throws DeustoException 
	 */
	public static void insertarSudaderaBBDD(Connection con,Articulo a) throws DeustoException {
		if(a instanceof Sudadera) {
			String sent = "INSERT INTO Articulos VALUES("+a.getID()+",'"+a.getName()+"','"+a.getTalla()+"',"+a.getPrecio()+",'"+a.getColor()+"','"+a.getSexo()+"','"+a.getImagen()+"','null','"+ ((Sudadera)a).getCapucha()+"','s')";
			Statement st = null;
			
			try {
				st = con.createStatement();
				logger.log( Level.INFO, "Statement: " + st );
			} catch (Exception e) {
				logger.log( Level.SEVERE, "Excepcion", e );
			}
			try {
				st.executeUpdate(sent);
			} catch (Exception e) {
				logger.log( Level.SEVERE, "Excepcion", e );
				throw new DeustoException("ERROR! STATEMENT FAILED");
			} finally {
				if(st!=null) {
					try {
						st.close();
					} catch (Exception e) {
						logger.log( Level.SEVERE, "Excepcion", e );
						throw new DeustoException("ERROR! CLOSING STATEMENT FAILED");
					}
				}
			}
		}	
	}
	
	/**
	 * Metodo que elimina a un usurio pasado por parametro de la tabla de Usuarios de la BBDD 
	 * @param con Conexion
	 * @param nick Nombre del usuario a eliminar 
	 * @throws DeustoException 
	 */
	public static void eliminarUsuarioBBDD(Connection con,String nick) throws DeustoException{
		String sent ="DELETE FROM Usuarios WHERE nick='"+nick+"'";
		Statement st = null;
		
		try {
			st = con.createStatement();
			logger.log( Level.INFO, "Statement: " + st );
		} catch (Exception e) {
			logger.log( Level.SEVERE, "Excepcion", e );
		}
		try {
			st.executeUpdate(sent);
		} catch (Exception e) {
			logger.log( Level.SEVERE, "Excepcion", e );
			throw new DeustoException("ERROR! STATEMENT FAILED");
		} finally {
			if(st!=null) {
				try {
					st.close();
				} catch (Exception e) {
					logger.log( Level.SEVERE, "Excepcion", e );
					throw new DeustoException("ERROR! CLOSING STATEMENT FAILED");
				}
			}
		}
	}	
	
	
	/**
	 * Metodo que elimina un Articulo pasado por paremtro de la tabla de Articulos de la BBDD
	 * @param con Conexion
	 * @param ID ID del articulo a eliminar 
	 * @throws DeustoException 
	 */
	public static void eliminarArticuloBBDD(Connection con,int ID) throws DeustoException{
		String sent ="DELETE FROM Articulos WHERE ID='"+ID+"'";
		Statement st = null;
		
		try {
			st = con.createStatement();
			logger.log( Level.INFO, "Statement: " + st );
		} catch (Exception e) {
			logger.log( Level.SEVERE, "Excepcion", e );
			throw new DeustoException("ERROR! CREATION STATEMENT FAILED");
		}
		try {
			st.executeUpdate(sent);
		} catch (Exception e) {
			logger.log( Level.SEVERE, "Excepcion", e );
			throw new DeustoException("ERROR! STATEMENT FAILED");
		} finally {
			if(st!=null) {
				try {
					st.close();
				} catch (Exception e) {
					logger.log( Level.SEVERE, "Excepcion", e );
					throw new DeustoException("ERROR! CLOSING STATEMENT FAILED");
				}
			}
		}
	}	
	
	
	/**
	 * M???todo que obtiene un mapa con los Usuarios de la BBDD
	 * @param con Conexi???n con la BBDD
	 * @return TreeMap<String,Usuario> tmUsuario
	 * @throws DeustoException 
	 */
	
	
	public static TreeMap<String, String> cargarMapaUsuariosDeInfoBBDD(Connection con) throws DeustoException{
		TreeMap<String, String> tmUsuario = new TreeMap<>();
		Statement stmt = null;
		
		String sentSQL = "SELECT Nick,Contraseya FROM Usuarios";
		try {
			stmt = con.createStatement();
			logger.log( Level.INFO, "Statement: " + stmt );
			ResultSet rs = stmt.executeQuery(sentSQL);
			while(rs.next()) { 
				String nick = rs.getString("Nick");
				String contraseya = rs.getString("Contraseya");
				tmUsuario.put(nick, contraseya);
				
			
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			logger.log( Level.SEVERE, "Excepcion", e );
			throw new DeustoException("ERROR! STATEMENT FAILED");
		} finally {
			if(stmt!=null) {
				try {
					stmt.close();
				} catch (Exception e) {
					logger.log( Level.SEVERE, "Excepcion", e );
					throw new DeustoException("ERROR! CLOSING STATEMENT FAILED");
				}
			}
		}
		return tmUsuario;
	}
	
	
	/**
	 * M???todo que obtiene un mapa con los Articulos de la BBDD
	 * @param con Conexi???n con la BBDD
	 * @return TreeMap<String,Articulo>
	 * @throws DeustoException 
	 */
	public static TreeMap<Integer, Articulo> cargarMapaArticulosDeInfoBBDD(Connection con) throws DeustoException{
		TreeMap<Integer, Articulo> tmArticulo = new TreeMap<>();
		Statement stmt = null;
		
		String sentSQL = "SELECT * FROM Articulos";
		try {
			stmt = con.createStatement();
			logger.log( Level.INFO, "Statement: " + stmt );
			ResultSet rs = stmt.executeQuery(sentSQL);
			while(rs.next()) { 
					
					int ID = rs.getInt("ID");
					String name = rs.getString("Name");
					String talla = rs.getString("Talla");
					Double precio = rs.getDouble("Precio");
					String color = rs.getString("Color");
					String sexo = rs.getString("Sexo");
					String imagen = rs.getString("Imagen");
					String tipo = rs.getString("TipoArticulo");
					String tipoPantalon = rs.getString("TipoPantalon");
					String capucha = rs.getString("Capucha");
					Articulo a = null;
					if(tipo.equals("c")) {
						a = new Camiseta(ID, name,talla,precio,color,sexo,imagen);
					}else if(tipo.equals("p")) {
						a = new Pantalon(ID,name,talla,precio,color,sexo,imagen,tipoPantalon);	
					}else if(tipo.equals("s")) {
						a = new Sudadera(ID,name,talla,precio,color,sexo,imagen,capucha);
					}
					tmArticulo.put(ID, a);
					
				}
			rs.close();
			stmt.close();
			
		} catch (Exception e) {
			logger.log( Level.SEVERE, "Excepcion", e );
			throw new DeustoException("ERROR! STATEMENT FAILED");
		} finally {
			if(stmt!=null) {
				try {
					stmt.close();
				} catch (Exception e) {
					logger.log( Level.SEVERE, "Excepcion", e );
					throw new DeustoException("ERROR! CLOSING STATEMENT FAILED");
				}
			}
		}
		return tmArticulo;
	}
	
	/**
	 * Metodo que permite cargar un treeMap con todas las camisetas de la BBDD
	 * @param con
	 * @return TreeMap con todas las camiestas regitradas
	 * @throws DeustoException
	 */
	public static TreeMap<Integer,Articulo> cargarCamisetasDeInfoDeBBDD(Connection con) throws DeustoException{
		TreeMap<Integer, Articulo> tmCamisetas = new TreeMap<>();
		Statement stmt = null;
		
		String sentSQL = "SELECT * FROM Articulos WHERE TipoArticulo = 'c'";
		try {
			stmt = con.createStatement();
			logger.log( Level.INFO, "Statement: " + stmt );
			ResultSet rs = stmt.executeQuery(sentSQL);
			while(rs.next()) {
				int ID = rs.getInt("ID");
				String name = rs.getString("Name");
				String talla = rs.getString("Talla");
				int precio = rs.getInt("Precio");
				String color = rs.getString("Color");
				String sexo = rs.getString("Sexo");
				String imagen = rs.getString("Imagen");
				String tipo = rs.getString("TipoArticulo");
				String tipoPantalon = rs.getString("TipoPantalon");
				String capucha = rs.getString("Capucha");
				
				Articulo a = new Camiseta(ID,name,talla,precio,color,sexo,imagen);
				tmCamisetas.put(ID, a);
			}
				rs.close();
				stmt.close();
				System.out.println("Camisetas cargadas con exito.... \n");
			} catch (Exception e) {
				logger.log( Level.SEVERE, "Excepcion", e );
				throw new DeustoException("ERROR! STATEMENT FAILED");
			} finally {
				if(stmt!=null) {
					try {
						stmt.close();
					} catch (Exception e) {
						logger.log( Level.SEVERE, "Excepcion", e );
						throw new DeustoException("ERROR! CLOSING STATEMENT FAILED");
					}
				}
			}
			
			return tmCamisetas;
		}
	
	/**
	 * Metodo que permite cargar un treeMap con todas los pantalones de la BBDD
	 * @param con
	 * @return TreeMap con todas las pantalones regitradas
	 * @throws DeustoException
	 */
	public static TreeMap<Integer,Articulo> cargarPantalonesDeInfoDeBBDD(Connection con) throws DeustoException{
		TreeMap<Integer, Articulo> tmPantalones = new TreeMap<>();
		Statement stmt = null;
		
		String sentSQL = "SELECT * FROM Articulos WHERE TipoArticulo = 'p'";
		try {
			stmt = con.createStatement();
			logger.log( Level.INFO, "Statement: " + stmt );
			ResultSet rs = stmt.executeQuery(sentSQL);
			while(rs.next()) {
				int ID = rs.getInt("ID");
				String name = rs.getString("Name");
				String talla = rs.getString("Talla");
				int precio = rs.getInt("Precio");
				String color = rs.getString("Color");
				String sexo = rs.getString("Sexo");
				String imagen = rs.getString("Imagen");
				String tipo = rs.getString("TipoArticulo");
				String tipoPantalon = rs.getString("TipoPantalon");
				String capucha = rs.getString("Capucha");
				
				Articulo a = new Pantalon(ID,name,talla,precio,color,sexo,imagen,tipoPantalon);
				tmPantalones.put(ID, a);
			}
				rs.close();
				stmt.close();
				System.out.println("Pantalones cargados con exito.... \n");
			} catch (Exception e) {
				logger.log( Level.SEVERE, "Excepcion", e );
				throw new DeustoException("ERROR! STATEMENT FAILED");
			} finally {
				if(stmt!=null) {
					try {
						stmt.close();
					} catch (Exception e) {
						logger.log( Level.SEVERE, "Excepcion", e );
						throw new DeustoException("ERROR! CLOSING STATEMENT FAILED");
					}
				}
			}
			
			return tmPantalones;
		}
	
	/**
	 * Metodo que permite cargar un treeMap con todas las sudaderas de la BBDD
	 * @param con
	 * @return TreeMap con todas las sudaderas regitradas
	 * @throws DeustoException
	 */
	public static TreeMap<Integer,Articulo> cargarSudaderasDeInfoDeBBDD(Connection con) throws DeustoException{
		TreeMap<Integer, Articulo> tmSudaderas = new TreeMap<>();
		Statement stmt=null;
		
		String sentSQL = "SELECT * FROM Articulos WHERE TipoArticulo = 's'";
		try {
			stmt = con.createStatement();
			logger.log( Level.INFO, "Statement: " + stmt );
			ResultSet rs = stmt.executeQuery(sentSQL);
			while(rs.next()) {
				int ID = rs.getInt("ID");
				String name = rs.getString("Name");
				String talla = rs.getString("Talla");
				int precio = rs.getInt("Precio");
				String color = rs.getString("Color");
				String sexo = rs.getString("Sexo");
				String imagen = rs.getString("Imagen");
				String tipo = rs.getString("TipoArticulo");
				String tipoPantalon = rs.getString("TipoPantalon");
				String capucha = rs.getString("Capucha");
				
				Articulo a = new Sudadera(ID,name,talla,precio,color,sexo,imagen,capucha);
				tmSudaderas.put(ID, a);
			}
				rs.close();
				stmt.close();
				System.out.println("Sudadera cargados con exito.... \n");
			} catch (Exception e) {
				logger.log( Level.SEVERE, "Excepcion", e );
				throw new DeustoException("ERROR! STATEMENT FAILED");
			} finally {
				if(stmt!=null) {
					try {
						stmt.close();
					} catch (Exception e) {
						logger.log( Level.SEVERE, "Excepcion", e );
						throw new DeustoException("ERROR! CLOSING STATEMENT FAILED");
					}
				}
			}
			
			return tmSudaderas;
		}
	
	/**
	 * Metodo que actualiza los campos de un articulo de la BBDD 
	 * @param id del articulo que queremos modifcar
	 * @param name nuevo a modificar
	 * @param talla nuevo a modificar
	 * @param precio nuevo a modificar
	 * @param color nuevo a modificar
	 * @param sexo nuevo a modificar
	 * @throws DeustoException 
	 */
	public static void modificarArticulo(Connection con, int id, String name, String talla, double precio, String color, String sexo) throws DeustoException {
		String sent = "UPDATE Articulos SET Name='"+name+"', Talla ='"+talla+"', Precio ="+precio+",Color = '"+color+"',Sexo ='"+sexo+"' WHERE ID =" +id;
		Statement st = null;
		
		try {
			st = con.createStatement();
			logger.log( Level.INFO, "Statement: " + st );
			st.executeUpdate(sent);
		} catch (SQLException e) {
			logger.log( Level.SEVERE, "Excepcion", e );
			throw new DeustoException("ERROR! STATEMENT FAILED");
		} finally {
			if(st!=null) {
				try {
					st.close();
				} catch (Exception e) {
					logger.log( Level.SEVERE, "Excepcion", e );
					throw new DeustoException("ERROR! CLOSING STATEMENT FAILED");
				}
			}
		}
	}
	/**
	 * Metodo que permite registrar una nueva venta en la tabla Ventas de la BBDD
	 * @param con
	 * @param u Usuario al que registrar una nueva ventas
	 * @throws DeustoException
	 */
	
	
	public static void registrarVenta(Connection con,Usuario u) throws DeustoException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Date fVenta = new Date(System.currentTimeMillis());
		String fechaVentaString = sdf.format(fVenta);
		String sent = "INSERT INTO ventas VALUES('"+u.getNick()+"',"+System.currentTimeMillis()+","+u.getCarrito().size()+","+0+",'"+fechaVentaString+"')";
		Statement st = null;
		try {
			st = con.createStatement();
			logger.log( Level.INFO, "Statement: " + st );
			st.executeUpdate(sent);
		} catch (Exception e) {
			logger.log( Level.SEVERE, "Excepcion", e );
			throw new DeustoException("ERROR! STATEMENT FAILED");
		} finally {
			if(st!=null) {
				try {
					st.close();
				} catch (Exception e) {
					logger.log( Level.SEVERE, "Excepcion", e );
					throw new DeustoException("ERROR! CLOSING STATEMENT FAILED");
				}
			}
		}
	}
	
	/**
	 * Metodo que permite conseguir todas las ventas de todos los usuarios registrados
	 * @param con
	 * @return Devuelve un HashMap con toda la informacion de las ventas
	 * @throws DeustoException
	 */
	public static HashMap<String,ArrayList<Venta>> conseguirVentasTotales(Connection con) throws DeustoException {
		HashMap<String, ArrayList<Venta>> hmVentasTotales = new HashMap<>();
		String sent = "SELECT * FROM ventas";
		Statement st = null;
		try {
			st = con.createStatement();
			logger.log( Level.INFO, "Statement: " + st );
			ResultSet rs = st.executeQuery(sent);
			while(rs.next()) {
				String nick = rs.getString("Nick");
				int token = rs.getInt("Token");
				int numArticulos = rs.getInt("NumArticulos");
				double precioTotal = rs.getDouble("PrecioTotal");
				String fVenta = rs.getString("fVenta");
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				Date fVentaDate;
				try {
					fVentaDate = sdf.parse(fVenta);
				} catch (ParseException e) {
					fVentaDate = new Date(System.currentTimeMillis());
				}
				
				Venta v = new Venta(nick, token,numArticulos,precioTotal,fVentaDate);
				if(!hmVentasTotales.containsKey(nick)) {
					hmVentasTotales.put(nick, new ArrayList<Venta>());
					hmVentasTotales.get(nick).add(v);
				}
				
			}
			rs.close();
			return hmVentasTotales;
		} catch (SQLException e) {
			logger.log( Level.SEVERE, "Excepcion", e );
			throw new DeustoException("ERROR! STATEMENT FAILED");
		} finally {
			if(st!=null) {
				try {
					st.close();
				} catch (Exception e) {
					logger.log( Level.SEVERE, "Excepcion", e );
					throw new DeustoException("ERROR! CLOSING STATEMENT FAILED");
				}
			}
		}
	}
	
	/**
	 * Metodo que permite eliminar las ventas de un usuario
	 * @param con
	 * @param u Usuario al que eliminaremos las ventas
	 * @throws DeustoException
	 */
	public static void eliminarVentas(Connection con,Usuario u) throws DeustoException {
		String sent = "DELETE * FROM Ventas WHERE nick='"+u.getNick();
		Statement st = null;
		
		try {
			st = con.createStatement();
			logger.log( Level.INFO, "Statement: " + st );
			st.executeUpdate(sent);
		} catch (Exception e) {
			logger.log( Level.SEVERE, "Excepcion", e );
			throw new DeustoException("ERROR! STATEMENT FAILED");
		} finally {
			if(st!=null) {
				try {
					st.close();
				} catch (Exception e) {
					logger.log( Level.SEVERE, "Excepcion", e );
					throw new DeustoException("ERROR! CLOSING STATEMENT FAILED");
				}
			}
		}
	}
	
	/**
	 * Metodo que permite contar el numero de Articulos en la BBDD
	 * @param con
	 * @return deuvelve el numero de articulos registrados
	 * @throws DeustoException
	 */
	public static int contarArticulos(Connection con) throws DeustoException {
		String sent = "select count(*) from Articulos";
		Statement st = null;
		int resul = 0;
		
		try {
			st = con.createStatement();
			logger.log( Level.INFO, "Statement: " + st );
			ResultSet rs = st.executeQuery(sent);
			resul = rs.getInt(1);
		} catch (Exception e) {
			logger.log( Level.SEVERE, "Excepcion", e );
			throw new DeustoException("ERROR! STATEMENT FAILED");
		} finally {
			if(st!=null) {
				try {
					st.close();
				} catch (Exception e) {
					logger.log( Level.SEVERE, "Excepcion", e );
					throw new DeustoException("ERROR! CLOSING STATEMENT FAILED");
				}
			}
		}
		return resul;
	}
	
	/**
	 * Metodo que comprueba si existe un Articulo con ese ID en la BBDD
	 * @param con Connection con la BBDD
	 * @param id del articulo a verificar
	 * @return int:
	 * Si es 1: Articulo ya registrado
	 * Si es 0: Articulo no registrado
	 * @throws DeustoException
	 */
	public static int existeArticulo(Connection con, int id) throws DeustoException{
		String sent = "SELECT ID FROM Articulos where ID="+id;
		Statement st = null;
		int resul = 0;
		
		try {
			st = con.createStatement();
			logger.log( Level.INFO, "Statement: " + st );
			ResultSet rs = st.executeQuery(sent);
			if(rs.next()) {
				if(rs.getInt("ID") == id) ;
					resul = 1;
				}else {
					resul = 0;
				}
			rs.close();
		} catch (SQLException e) {
			logger.log( Level.SEVERE, "Excepcion", e );
			throw new DeustoException("ERROR! STATEMENT FAILED");
		} finally {
			if(st!=null) {
				try {
					st.close();
				} catch (Exception e) {
					logger.log( Level.SEVERE, "Excepcion", e );
					throw new DeustoException("ERROR! CLOSING STATEMENT FAILED");
				}
			}
		}
		return resul;
	}
	
	/**
	 * Metodo que comrpueba si existe un aritculo ya registrado en la BBDD con el ID pasado por parametro
	 * @param con Connection
	 * @param id del Articulo a comprobar
	 * @return true: si el ID esta registrado en la BBDD 
	 * 		   false: si el id no esta registrado en la BBDD
	 * @throws SQLException
	 * @throws DeustoException
	 */
	public static boolean existeArticuloBoolean(Connection con,int id) throws SQLException, DeustoException {
		boolean existe = false;
		Statement st = con.createStatement();
		String sent = "select * from Articulos where ID ="+id;
		try {
			ResultSet rs = st.executeQuery(sent);
			if(rs.next())
				existe = true;
			rs.close();
		} catch(SQLException e) {
			logger.log( Level.SEVERE, "Excepcion", e );
			throw new DeustoException("ERROR! STATEMENT FAILED");
		} finally {
			if(st!=null) {
				try {
					st.close();
				} catch (Exception e) {
					logger.log( Level.SEVERE, "Excepcion", e );
					throw new DeustoException("ERROR! CLOSING STATEMENT FAILED");
				}
			}
		}
		return existe;
	}
	
	/**
	 * Metodo que devuelve en un ArrayList todos los nicks de los Usuarios registrados en la BBDD
	 * @param con Connection
	 * @return ArrayList con el nick de todos los usuarios
	 * @throws DeustoException
	 * @throws SQLException
	 */
	public static ArrayList<String> conseguirNombresDeUsuarios(Connection con) throws DeustoException, SQLException{
		Statement st = con.createStatement();
		String sent = "SELECT Nick FROM usuarios";
		ArrayList<String> aNombres = new ArrayList<>();
		try {
			ResultSet rs = st.executeQuery(sent);
			while(rs.next()) {
				String n = rs.getString("Nick");
				aNombres.add(n);
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			logger.log( Level.SEVERE, "Excepcion", e );
			throw new DeustoException("ERROR! STATEMENT FAILED");
		}finally {
			if(st!=null) {
				try {
					st.close();
				} catch (Exception e) {
					logger.log( Level.SEVERE, "Excepcion", e );
					throw new DeustoException("ERROR! CLOSING STATEMENT FAILED");
				}
			}
		}
		
		return aNombres;
	}
	
	/**
	 * Metodo que devuelve en un ArrayList todas las ventas que ha realizado un usuario en concreto pasado por parametro
	 * @param con Connection
	 * @param nick del usuario a conseguir las ventas
	 * @return ArrayList de todas las ventas de ese usuario
	 */
	public static ArrayList<Venta> obtenerComprasUsuario(Connection con, String nick){
		ArrayList<Venta> a = new ArrayList<>();
		String sent;
		if(nick.equals("USUARIOS"))
			sent = "SELECT * FROM VENTAS";
		else
			sent = "SELECT * FROM ventas WHERE Nick='"+nick+"'";
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sent);
			while(rs.next()) {
				String nickbd = rs.getString("nick");
				int token = rs.getInt("Token");
				int numArticulos = rs.getInt("NumArticulos");
				double precioTotal = rs.getDouble("PrecioTotal");
				String fVenta = rs.getString("fVenta");
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				Date fVentaDate;
				try {
					fVentaDate = sdf.parse(fVenta);
				} catch (ParseException e) {
					fVentaDate = new Date(System.currentTimeMillis());
				}
				Venta v = new Venta(nickbd,token,numArticulos,precioTotal,fVentaDate);
				a.add(v);
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a;
	}
	
	/*public static int estaRegistrado(Connection con, String nick) throws DeustoException {
		String sentencia = "SELECT Nick FROM Usuarios WHERE Nick ='" + nick + "'";
		Statement st = null;
		int resul = 0;
		
		try {
			st = con.createStatement();
			logger.log( Level.INFO, "Statement: " + st );
			ResultSet rs = st.executeQuery(sentencia);
			if(rs.next()) {
				if(rs.getString("Nick").equals(nick));
				resul = 1;
			}else {
				resul = 0;
			}
			rs.close();
		} catch (Exception e) {
			logger.log( Level.SEVERE, "Excepcion", e );
			throw new DeustoException("ERROR! STATEMENT FAILED");
		} finally {
			if(st!=null) {
				try {
					st.close();
				} catch (Exception e) {
					logger.log( Level.SEVERE, "Excepcion", e );
					throw new DeustoException("ERROR! CLOSING STATEMENT FAILED");
				}
			}
		}
		return resul;
	}*/
	
	
	/**
	 * Metodo que devuelve en un ArrayList todos los nicks de los Usuarios registrados en la BBDD
	 * @param con Connection
	 * @return ArrayList con el nick de todos los usuarios
	 * @throws DeustoException
	 * @throws SQLException
	 */
	public static ArrayList<String> conseguirNombresDeUsuariosDeLasVentas(Connection con) throws DeustoException, SQLException{
		Statement st = con.createStatement();
		String sent = "SELECT DISTINCT(Nick) FROM ventas";
		ArrayList<String> aNombres = new ArrayList<>();
		try {
			ResultSet rs = st.executeQuery(sent);
			while(rs.next()) {
				String n = rs.getString("Nick");
				aNombres.add(n);
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			logger.log( Level.SEVERE, "Excepcion", e );
			throw new DeustoException("ERROR! STATEMENT FAILED");
		}finally {
			if(st!=null) {
				try {
					st.close();
				} catch (Exception e) {
					logger.log( Level.SEVERE, "Excepcion", e );
					throw new DeustoException("ERROR! CLOSING STATEMENT FAILED");
				}
			}
		}
		
		return aNombres;
	}
}
	
	
