package es.unican.is2.practica5;

import java.time.LocalDate;

public abstract class Tarjeta {
	protected String numero, titular;		
	protected CuentaAhorro cuentaAsociada;
	private LocalDate fechaCaducidad;
	
	//WMC DE LA CLASE: 3
	//WMCn DE LA CLASE: 3/3= 1
	//CBO DE LA CLASE:	
	//DIT DE LA CLASE:	0
	//NOC DE LA CLASE:	2
	//CCog DE LA CLASE: 0

	public Tarjeta(String numero, String titular, CuentaAhorro cuenta, LocalDate fechaCaducidad) { //WMC +1
		this.numero = numero;
		this.titular = titular;
		this.cuentaAsociada = cuenta;
		this.fechaCaducidad = fechaCaducidad;
	}
	
	
	public  Cuenta getCuentaAsociada() { //WMC +1
		return cuentaAsociada;
	}


	public  LocalDate getCaducidad() { //WMC +1
		return this.fechaCaducidad;
	}
	
	/**
	 * Retirada de dinero en cajero con la tarjeta
	 * @param cantRetirar Cantidad a retirar. 
	 * @throws saldoInsuficienteException
	 * @throws datoErroneoException
	 */
	public abstract void retirar(double cantRetirar) throws saldoInsuficienteException, datoErroneoException; 

	/**
	 * Pago en establecimiento con la tarjeta
	 * @param concepto Concepto del pago
	 * @param cantPagar Cantidada a pagar
	 * @throws saldoInsuficienteException
	 * @throws datoErroneoException
	 */
	public abstract void pagar(String concepto, double cantPagar)
			throws saldoInsuficienteException, datoErroneoException; 

	
}