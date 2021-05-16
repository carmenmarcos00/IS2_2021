package es.unican.is2.practica5;

import java.util.List;

public class CuentaValores extends Cuenta {
	//WMC DE LA CLASE: 1+1+1+2 = 5
	//WMCn DE LA CLASE: 5/4 = 1.25
	//CBO DE LA CLASE:	
	//DIT DE LA CLASE:	1
	//NOC DE LA CLASE:	0
	//CCog DE LA CLASE: 1

	private List<Valor> valores; 

	public CuentaValores(String numCuenta, List<Valor> valores) {//WMC+1 
		super(numCuenta);
		this.valores = valores;
	}

	public List<Valor> getValores() { //WMC+1 
		return valores;
	}

	public void anhadeValor(Valor valor) {//WMC+1 
		valores.add(valor);
	}

	public double getCotizacionValores() { //WMC+1  //WMC TOTAL = 2 //CCOG TOTAL = 1 
		double cotizacionValores = 0;

		for (Valor v: getValores()) { //WMC+1 //CCOG +1
			cotizacionValores += v.getCotizacionActual()*v.getNumValores();
		}
		return cotizacionValores;
	}

}
