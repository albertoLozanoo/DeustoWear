package clases;

public class DeustoException extends Exception {
	/**
	 * Clase exception para usar nuestras exception creadas
	 */
	private static final long serialVersionUID = 1L;
	private String mensaje;
	
	/**
	 * Constructor de la clase DeustoException
	 * @param m mensaje
	 */
	public DeustoException(String m) {
		this.mensaje = m;
	}
	
	/**
	 * Metodo toString de la clase DeustoException
	 */
	public String toString() {
		return mensaje;
	}
}
