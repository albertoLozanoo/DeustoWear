package Clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


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
		String sent2 = "CREATE TABLE IF NOT EXISTS Usuarios(Nick String, Contraseña String)";
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
	
	public static void intertarUsuarioBBDD(Connection con,Usuario u) {
		String sent = "INSERT INTO Usuarios VALUES('"+u.getNick()+"','"+u.getContraseya()+"')";
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
}