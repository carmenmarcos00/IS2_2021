package es.unican.is2.practica5;

import java.time.LocalDate;

public class Debito extends Tarjeta {
	
	//WMC DE LA CLASE: 1+2+2+1 = 4
	//WMCn DE LA CLASE: 4/4 = 1
	//CBO DE LA CLASE:
	//DIT DE LA CLASE:	1
	//NOC DE LA CLASE:	0
	//CCog DE LA CLASE: 2
	
	private double saldoDiarioDisponible;

	public Debito(String numero, String titular, CuentaAhorro cuenta, LocalDate fechaCaducidad) { //WMC+1
		super(numero, titular, cuenta, fechaCaducidad); 
	}
	
	
	@Override
	public void retirar(double cantRetirar) throws saldoInsuficienteException, datoErroneoException { //WMC+1 WMC TOTAL = 2
		if (saldoDiarioDisponible<cantRetirar) { //WMC+1 CCog+1
			throw new saldoInsuficienteException("Saldo insuficiente");
		}
		this.cuentaAsociada.retirar("Retirada en cajero automático", cantRetirar);
		saldoDiarioDisponible-=cantRetirar;
	}
	
	@Override
	public void pagar(String concepto, double cantPagar) throws saldoInsuficienteException, datoErroneoException { //WMC+1 WMC TOTAL = 2
		if (saldoDiarioDisponible<cantPagar) { //WMC+1 CCog+1
			throw new saldoInsuficienteException("Saldo insuficiente");
		}
		this.cuentaAsociada.retirar("Compra en : " + concepto, cantPagar);
		saldoDiarioDisponible-=cantPagar;
	}
	
	/**
	 * Método invocado automáticamente a las 00:00 de cada día
	 */
	public void restableceSaldo() { //WMC+1
		saldoDiarioDisponible = cuentaAsociada.getLimiteDebito();
	}
}