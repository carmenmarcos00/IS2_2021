package es.unican.is2.practica5;

import java.util.List;

public class CuentaValores extends Cuenta {
	//WMC DE LA CLASE: 1+1+1 = 3
	//WMCn DE LA CLASE: 3/3 = 1
	//CBO DE LA CLASE:	1, llama a 1, luego revisar por quien es llamado
	//DIT DE LA CLASE:	1
	//NOC DE LA CLASE:	0
	//CCog DE LA CLASE: 0

	private List<Valor> valores; //cbo+1
	
	public CuentaValores(String numCuenta, List<Valor> valores) { //WMC +1
		super(numCuenta);
		this.valores = valores;
	}
	
	public List<Valor> getValores() { //WMC +1
		return valores;
	}
	
	public void anhadeValor(Valor v) { //WMC +1
		valores.add(v);
	}
	
	
}
