package clases;

public class DeustoException extends Exception {
	/**
	 * Clase exception para usar nuestras exception creadas
	 */
	private static final long serialVersionUID = 1L;
	private String mensaje;
	
	public DeustoException(String m) {
		this.mensaje = m;
	}
	
	public String toString() {
		return mensaje;
	}
}
