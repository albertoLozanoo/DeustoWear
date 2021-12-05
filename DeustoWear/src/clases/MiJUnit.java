package clases;

import static org.junit.Assert.assertTrue;
import java.sql.Connection;
import java.sql.SQLException;
import org.junit.jupiter.api.Test;


class MiJUnit {

	public Connection con;
	Articulo a = new Camiseta(111,"Prueba","S",30,"Naranja","Hombre","pathImage") {
	};
	@Test
	public void eliminarArticulo() throws DeustoException, SQLException {
		
		BD.initBD("baseDeDatos.db");
		int contAntesDeInsertar = BD.contarArticulos(con);
		BD.eliminarArticuloBBDD(con, a.getID());
		int contDespuesDeInsertar = BD.contarArticulos(con);
		assertTrue(contAntesDeInsertar == contDespuesDeInsertar+1);
	}

}
