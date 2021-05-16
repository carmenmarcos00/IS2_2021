package es.unican.is2.practica5;

import java.time.LocalDateTime;

public class Movimiento {
	
	//WMC DE LA CLASE: 8
	//WMCn DE LA CLASE: 8/8
	//CBO DE LA CLASE:	
	//DIT DE LA CLASE:	0
	//NOC DE LA CLASE:0
	//CCog DE LA CLASE: 0
	
	private String concepto;
	private LocalDateTime fecha;
	private double importe;
	
	public Movimiento (	String concepto, LocalDateTime fecha, double importe) { //WMC +1
		this.concepto = concepto;
		this.fecha = fecha;
		this.importe = importe;
	}
	
	public Movimiento (	String concepto, LocalDateTime fecha, double importe, boolean cajero) { //WMC +1
		this.concepto = concepto;
		this.fecha = fecha;
		this.importe = importe;
	}

	public double getImporte() { //WMC +1
		return importe;
	}

	public String getConcepto() { //WMC +1
		return concepto;
	}

	public void setConcepto(String newMConcepto) { //WMC +1
		concepto = newMConcepto;
	}

	public LocalDateTime getFecha() { //WMC +1
		return fecha; 
	}

	public void setFecha(LocalDateTime newMFecha) {   //WMC +1
		fecha = newMFecha;
	}

	public void setImporte(double newMImporte) { //WMC +1
		importe = newMImporte;
	}
}