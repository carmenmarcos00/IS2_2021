package es.unican.is2.practica5;

public abstract class Cuenta {
	
	//WMC DE LA CLASE: 2
	//WMCn DE LA CLASE: 1
	//CBO DE LA CLASE:	
	//DIT DE LA CLASE:	0
	//NOC DE LA CLASE:	2
	//CCog DE LA CLASE:	0
	
	private String numCuenta;
	
	public Cuenta(String numCuenta) { //WMC +1
		this.numCuenta = numCuenta;
	}
	
	public String getNumCuenta() {//WMC +1
		return numCuenta;
	}
	
	
}
