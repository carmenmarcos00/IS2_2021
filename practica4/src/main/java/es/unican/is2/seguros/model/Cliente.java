package es.unican.is2.seguros.model;

public class Cliente {
	
	//Variables de la clase
	private String nombre;
	private String dni;
	private boolean minusvalia;
	
	public Cliente (String nombre, String dni, boolean minusvalia) {
		this.nombre = nombre;
		this.dni = dni;
		this.minusvalia = minusvalia;	
	}
	
	
	//Setters
	public void setNombre (String nombre) {
		this.nombre = nombre;
	}
	
	public void setDni (String dni) {
		this.dni = dni;	
	}
	
	public void setMinusvalia (boolean minusvalia) {
		this.minusvalia = minusvalia;
	}
	
	//Getters
	public String getNombre () {
		return this.nombre;
	}
	
	public String getDni() {
		return this.dni;
	}
	
	public boolean getMinusvalia() {
		return this.minusvalia;
	}
	
}
