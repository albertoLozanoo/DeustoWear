package clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
	 */
	public static void crearTablas(Connection con) throws DeustoException {
		String sent1 = "CREATE TABLE IF NOT EXISTS Articulos(ID Integer,Name String, Talla String,Precio Double,Color String, Sexo String, Imagen String, TipoPantalon String, Capucha String, TipoArticulo String)";
		String sent2 = "CREATE TABLE IF NOT EXISTS Usuarios(Nick String, Contraseña String, Avatar String)";
		String sent3 = "CREATE TABLE IF NOT EXISTS Ventas(Nick String, Token long)";
		
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
	 * Método que recibe los datos de un Usuario y comprueba que está registrado en la BBDD
	 * @param nom nombre del usuario
	 * @param con contraseña del usuario
	 * @return 0 si el usuario no está registrado
	 * 		   1 si el usuario está registrado pero la contraseña no es correcta
	 * 		   2 si el usuario está registrado y la contraseña es correcta
	 * @throws DeustoException 
	 */
	public static int obtenerUsuario(Connection con, String nick, String c) throws DeustoException {
		String sentencia = "SELECT Contraseña FROM Usuarios WHERE 	Nick ='"+nick+"'";
		Statement st = null;
		int resul=0;
		try {
			st = con.createStatement();
			logger.log( Level.INFO, "Statement: " + st );
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
	 * Método que recibe los datos de un Articulo y comprueba que está registrado en la BBDD
	 * @param nom nombre del Artiuclo
	 * @param con contraseña del Articulo
	 * @return 0 si el Articulo no está registrado
	 * 		   1 si el Articulo está registrado pero la contraseña no es correcta
	 * 		   2 si el usuario está registrado y la contraseña es correcta
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
	
	public static String conseguirAvatar(Connection con,String nick) throws DeustoException {
		String sent = "SELECT Avatar FROM Usuarios WHERE Nick ='"+nick+"'";
		Statement st = null;
		try {
			st = con.createStatement();
			logger.log( Level.INFO, "Statement: " + st );
			ResultSet rs = st.executeQuery(sent);
			if(rs.next()) { //Hemos encontrado una tupla que cumple la condición
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
	 * Método que permite al usuario cambiar su contraseña y se actualiza su info en la BD
	 * @param con Conexion
	 * @param nick El nick del usuario al que le vamos a cambiar la contraseña
	 * @param c La nueva contraseña
	 * @throws DeustoException 
	 */
	
	public static void cambiarContrasenya(Connection con, String nick, String c) throws DeustoException {
		
		String sent = "UPDATE Usuarios SET Contraseña = '"+c+"' WHERE Nick = '"+nick+"'";
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
	 * Metodo que inserta un articulo pasado por parametro en la tabla de Articulos de la BBDD
	 * @param con Conexion
	 * @param a Articulo ha introducir en la BBDD
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
	 * Método que obtiene un mapa con los Usuarios de la BBDD
	 * @param con Conexión con la BBDD
	 * @return TreeMap<String,Usuario> tmUsuario
	 * @throws DeustoException 
	 */
	public static TreeMap<String, Usuario> cargarMapaUsuariosDeInfoBBDD(Connection con) throws DeustoException{
		TreeMap<String, Usuario> tmUsuario = new TreeMap<>();
		Statement stmt = null;
		
		String sentSQL = "SELECT Nick,Contraseña FROM Usuarios";
		try {
			stmt = con.createStatement();
			logger.log( Level.INFO, "Statement: " + stmt );
			ResultSet rs = stmt.executeQuery(sentSQL);
			while(rs.next()) { 
				String nick = rs.getString("Nick");
				String contraseya = rs.getString("Contraseña");
				Usuario u = new Usuario(nick,contraseya);
				tmUsuario.put(nick, u);
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
	 * Método que obtiene un mapa con los Articulos de la BBDD
	 * @param con Conexión con la BBDD
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
					int precio = rs.getInt("Precio");
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
	 * Metodo que permite registrar una nueva venta en la tabla Ventas de la BBDD
	 * @param con
	 * @param u Usuario al que registrar una nueva ventas
	 * @throws DeustoException
	 */
	public static void registrarVenta(Connection con,Usuario u) throws DeustoException {
		String sent = "INSERT INTO Ventas VALUES('"+u.getNick()+"','"+System.currentTimeMillis()+"')";
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
		String sent = "SELECT * FROM Ventas";
		Statement st = null;
		try {
			st = con.createStatement();
			logger.log( Level.INFO, "Statement: " + st );
			ResultSet rs = st.executeQuery(sent);
			while(rs.next()) {
				String nick = rs.getString("Nick");
				int token = rs.getInt("Token");
				
				Venta v = new Venta(nick, token);
				if(hmVentasTotales.containsKey(nick)) {
					hmVentasTotales.put(nick, new ArrayList());
				}
				hmVentasTotales.get(nick).add(v);
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
	
}
