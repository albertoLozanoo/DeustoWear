package clases;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.Test;




class MiJUnit {

	public Connection con;
	private Venta vnt;
	private ArrayList<Articulo> articulos;
	public Camiseta c = new Camiseta(121, "camisetaPrueba", "XS", 0.99, "NEGRO", "Hombre", "imagenes/camisetas/camiseta1.png");
	
	/**
	 * Test que comprueba que la conexion con la BBDD es correcta
	 */
	@Test
	public void testAbrirConexion() {
		boolean conectado = false;
		try {
			con = BD.initBD("baseDeDatos.db");
			if(con!=null) {
				conectado = true;
			}else {
				conectado = false;
			}
			assertTrue(conectado);
			BD.closeBD(con);
		} catch (DeustoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * Metodo que compruba si hay articulos en la BBDD, comprobando el metodo contarArticulos()
	 */
	@Test
	public void testGetProductos() {
		try {
			con = BD.initBD("baseDeDatos.db");
			int numArticulos = BD.contarArticulos(con);
			assertNotEquals(0, numArticulos);
		} catch (DeustoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Test que comprueba si el metodo a�adir Camiseta funciona correctamente
	 */
	@Test
	public void testInsertarNuevaCamiseta() {
		try {
			con = BD.initBD("baseDeDatos.db");
			int contAntesDeInsertar = BD.contarArticulos(con);
			BD.insertarCamisetaBBDD(con, c);
			int contDespuesDeInsertar = BD.contarArticulos(con);
			assertTrue(contAntesDeInsertar == contDespuesDeInsertar - 1);
			
			//Eliminamos el articulo que acabamos de insertar para dejar intacta la BBDD
			BD.eliminarArticuloBBDD(con, 121);
			BD.closeBD(con);
		} catch (DeustoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Test que comprueba si el metodo eliminarArticulo funciona correctamente
	 * @throws DeustoException
	 * @throws SQLException
	 */
	@Test
	public void eliminarPantalon() throws DeustoException, SQLException {
		try {
			con = BD.initBD("baseDeDatos.db");
			int contAntesDeEliminar = BD.contarArticulos(con);
			if(contAntesDeEliminar > 0) {
				if(BD.existeArticuloBoolean(con, 123)) {
					BD.eliminarArticuloBBDD(con, 123);
					int contDespuesDeEliminar = BD.contarArticulos(con);
					assertTrue(contAntesDeEliminar == contDespuesDeEliminar + 1);
				}
			}
			//A�adimos el articulo que acabamos de insertar para dejar intacta la BBDD
			Pantalon p = new Pantalon(123, "pantalon", "M", 25, "Azul","Mujer","imagenes/pantalones/pantalon1.png","Corto");
			BD.insertarPantalonBBDD(con, p);
			BD.closeBD(con);
			
		} catch (DeustoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void eliminarCamiseta() throws DeustoException, SQLException {
		try {
			con = BD.initBD("baseDeDatos.db");
			int contAntesDeEliminar = BD.contarArticulos(con);
			if(contAntesDeEliminar > 0) {
				if(BD.existeArticuloBoolean(con, 123)) {
					BD.eliminarArticuloBBDD(con, 123);
					int contDespuesDeEliminar = BD.contarArticulos(con);
					assertTrue(contAntesDeEliminar == contDespuesDeEliminar + 1);
				}
			}
			//A�adimos el articulo que acabamos de insertar para dejar intacta la BBDD
			Camiseta c = new Camiseta(123, "camiseta", "M", 25, "Azul","Mujer","imagenes/camisetas/camiseta1.png");
			BD.insertarPantalonBBDD(con, c);
			BD.closeBD(con);
			
		} catch (DeustoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
