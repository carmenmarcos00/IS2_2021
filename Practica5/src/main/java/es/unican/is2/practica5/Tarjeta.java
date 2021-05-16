package es.unican.is2.practica5;

public abstract class Tarjeta {
	protected String mNumero, mTitular;		
	protected CuentaAhorro mCuentaAsociada;
	
	//WMC DE LA CLASE: 1
	//WMCn DE LA CLASE: 1
	//CBO DE LA CLASE:	
	//DIT DE LA CLASE:	0
	//NOC DE LA CLASE:	2
	//CCog DE LA CLASE: 0

	public Tarjeta(String numero, String titular, CuentaAhorro c) { 
	
		mNumero = numero;
		mTitular = titular;
		mCuentaAsociada = c;
	}

	/**
	 * Retirada de dinero en cajero con la tarjeta
	 * @param x Cantidad a retirar. 
	 * @throws saldoInsuficienteException
	 * @throws datoErroneoException
	 */
	public abstract void retirar(double x) throws saldoInsuficienteException, datoErroneoException; //WMC +1

	/**
	 * Pago en establecimiento con la tarjeta
	 * @param datos Concepto del pago
	 * @param x Cantidada a pagar
	 * @throws saldoInsuficienteException
	 * @throws datoErroneoException
	 */
	public abstract void pagoEnEstablecimiento(String datos, double x)
			throws saldoInsuficienteException, datoErroneoException; //WMC +1
	
}