package es.unican.is2.practica5;

public class Direccion {
	
	//WMC DE LA CLASE: 7
	//WMCn DE LA CLASE: 7/7 = 1
	//CBO DE LA CLASE:
	//DIT DE LA CLASE:	0
	//NOC DE LA CLASE:	0
	//CCog DE LA CLASE: 0
	
	
	private String calle;
	private String zip;
	private String localidad;

	public Direccion(String calle, String zip, String localidad) { //WMC +1
		this.calle = calle;
		this.zip = zip;
		this.localidad = localidad;
			
	}

	public String getCalle() { //WMC +1
		return calle;
	}

	public void setCalle(String calle) { //WMC +1
		this.calle = calle;
	}

	public String getZip() { //WMC +1
		return zip;
	}

	public void setZip(String zip) { //WMC +1
		this.zip = zip;
	}

	public String getLocalidad() { //WMC +1
		return localidad;
	}

	public void setLocalidad(String localidad) { //WMC +1
		this.localidad = localidad;
	}
}