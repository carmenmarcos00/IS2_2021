package es.unican.is2.practica5;

import java.time.LocalDateTime;

public class Movimiento {
	
	//WMC DE LA CLASE: 6
	//WMCn DE LA CLASE: 6/6 = 1
	//CBO DE LA CLASE:	nececesita a 0 y hay que mirar quien le llama
	//DIT DE LA CLASE:	0
	//NOC DE LA CLASE:	0
	//CCog DE LA CLASE: 0
	
	private String mConcepto;
	private LocalDateTime mFecha;
	private double mImporte;

	public double getI() { //WMC +1
		return mImporte;
	}

	public String getC() { //WMC +1
		return mConcepto;
	}

	public void setC(String newMConcepto) { //WMC +1
		mConcepto = newMConcepto;
	}

	public LocalDateTime getF() { //WMC +1
		return mFecha; 
	}

	public void setF(LocalDateTime newMFecha) {   //WMC +1
		mFecha = newMFecha;
	}

	public void setI(double newMImporte) { //WMC +1
		mImporte = newMImporte;
	}
}