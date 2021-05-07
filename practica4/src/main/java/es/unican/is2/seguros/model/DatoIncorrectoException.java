package es.unican.is2.seguros.model;

/**
 * Clase excepción
 * @author Carmen Marcos Sánchez de la Blanca
 * @version 05/05/2021
 */
public class DatoIncorrectoException extends Exception {

	public DatoIncorrectoException() {
		super("Error, dato introducido incorrecto");

	}
}

