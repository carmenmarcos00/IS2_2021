package es.unican.is2.practica5;

import java.time.LocalDate;

public class Debito extends Tarjeta {
	
	//WMC DE LA CLASE: 1+2+2+1+1+1 = 8
	//WMCn DE LA CLASE: 8/6= 1.33
	//CBO DE LA CLASE:	1 , llama a 1
	//DIT DE LA CLASE:	1
	//NOC DE LA CLASE:	0
	//CCog DE LA CLASE: 1+1 = 2
	
	private double saldoDiarioDisponible;

	public Debito(String numero, String titular, CuentaAhorro c) { //WMC +1 //WMC TOTAL = 1 //CCog TOTAL = 0
		super(numero, titular, c); //CBO c cuenta ahorro cbo+1
	}
	
	
	@Override
	public void retirar(double x) throws saldoInsuficienteException, datoErroneoException { //WMC +1 //WMC TOTAL = 2 //CCog TOTAL = 1
		if (saldoDiarioDisponible<x) { //WMC +1 //CCog+1
			throw new saldoInsuficienteException("Saldo insuficiente");
		}
		this.mCuentaAsociada.retirar("Retirada en cajero automático", x);
		saldoDiarioDisponible-=x;
	}
	
	@Override
	public void pagoEnEstablecimiento(String datos, double x) throws saldoInsuficienteException, datoErroneoException { //WMC +1 //WMC TOTAL = 2 //CCog TOTAL = 1
		if (saldoDiarioDisponible<x) { //WMC +1 //CCog+1
			throw new saldoInsuficienteException("Saldo insuficiente");
		}
		this.mCuentaAsociada.retirar("Compra en : " + datos, x);
		saldoDiarioDisponible-=x;
	}
	
	public LocalDate getCaducidadDebito() { //WMC +1 //WMC TOTAL = 1 //CCog TOTAL = 0
		return this.mCuentaAsociada.getCaducidadDebito();
	}
	
	/**
	 * Método invocado automáticamente a las 00:00 de cada día
	 */
	public void restableceSaldo() { //WMC +1 //WMC TOTAL = 1 //CCog TOTAL = 0
		saldoDiarioDisponible = mCuentaAsociada.getLimiteDebito();
	}
	
	public CuentaAhorro getCuentaAsociada() { //WMC +1 //WMC TOTAL = 1 //CCog TOTAL = 0
		return mCuentaAsociada;
	}

}