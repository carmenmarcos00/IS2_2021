package es.unican.is2.practica5;

public class Valor {
	
	
	//WMC DE LA CLASE: 1+1+1+1+1+1 = 6
	//WMCn DE LA CLASE: 6/6= 1
	//CBO DE LA CLASE: mirar más adelante 
	//DIT DE LA CLASE:	0
	//NOC DE LA CLASE:	0
	//CCog DE LA CLASE: 0
	
	private String entidad;	
	private int numValores;
	private double cotizacionActual;
	
	public Valor(String entidad, int numValores, double cotizacionActual) {  //WMC +1

		this.entidad = entidad;
		this.numValores = numValores;
		this.cotizacionActual = cotizacionActual;
	}
	
	public int getNumValores() { //WMC +1
		return numValores;
	}

	public void setNumValores(int numValores) { //WMC +1
		this.numValores = numValores;
	}

	public double getCotizacionActual() { //WMC +1
		return cotizacionActual;
	}

	public void setCotizacionActual(double cotizacionActual) { //WMC +1
		this.cotizacionActual = cotizacionActual;
	}

	public String getEntidad() { //WMC +1
		return entidad;
	}


}