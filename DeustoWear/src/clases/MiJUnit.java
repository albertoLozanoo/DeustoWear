package clases;

import static org.junit.Assert.assertTrue;
import java.sql.Connection;
import java.sql.SQLException;
import org.junit.jupiter.api.Test;


class MiJUnit {

	public Connection con;
	public Camiseta c = new Camiseta(121, "camisetaPrueba", "XS", 0.99, "NEGRO", "Hombre", "imagenes/camisetas/camiseta1.png");
	
	/**
	 * Test que comprueba si el metodo añadir Camiseta funciona correctamente
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
	public void eliminarArticulo() throws DeustoException, SQLException {
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
			//Añadimos el articulo que acabamos de insertar para dejar intacta la BBDD
			Pantalon p = new Pantalon(123, "pantalon", "M", 25, "Azul","Mujer","imagenes/pantalones/pantalon1.png","Corto");
			BD.insertarPantalonBBDD(con, p);
			BD.closeBD(con);
			
		} catch (DeustoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
