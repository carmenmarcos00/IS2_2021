package es.unican.is2.practica5;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;


public class Credito extends Tarjeta {
	
	
	//WMC DE LA CLASE: 1+3+3+2+1+1+3+1+1 = 16
	//WMCn DE LA CLASE: 16/9 = 1.77
	//CBO DE LA CLASE:	2
	//DIT DE LA CLASE:	1
	//NOC DE LA CLASE:	0
	//CCog DE LA CLASE:	2+2+1+2 = 7
	
	private double mCredito;
	private List<Movimiento> mMovimientosMensuales; //CBO clase movimiento +1
	private List<Movimiento> mhistoricoMovimientos;
	
	
	public Credito(String numero, String titular, CuentaAhorro c, double credito) { //WMC +1 , CCOG = 0
		super(numero, titular, c);
		mCredito = credito;
		mMovimientosMensuales = new LinkedList<Movimiento>();
		mhistoricoMovimientos = new LinkedList<Movimiento>();
	}

	/**
	 * Retirada de dinero en cajero con la tarjeta
	 * @param x Cantidad a retirar. Se aplica una comisión del 5%.
	 * @throws saldoInsuficienteException
	 * @throws datoErroneoException
	 */
	@Override
	public void retirar(double x) throws saldoInsuficienteException, datoErroneoException { //WMC +1  WMC DEL METODO = 3, CCOG = 2 
		if (x<0) //WMC +1 CCOG+1
			throw new datoErroneoException("No se puede retirar una cantidad negativa");
		
		Movimiento m = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		m.setF(now);
		m.setC("Retirada en cajero automático");
		x += x * 0.05; // Añadimos una comisión de un 5%
		m.setI(-x);
		
		if (getGastosAcumulados()+x > mCredito) //WMC +1 CCOG+1
			throw new saldoInsuficienteException("Crédito insuficiente");
		else {
			mMovimientosMensuales.add(m);
		}
	}

	@Override
	public void pagoEnEstablecimiento(String datos, double x) throws saldoInsuficienteException, datoErroneoException { //WMC +1  WMC DEL METODO = 3 , CCOG =2
		if (x<0) //WMC +1 CCOG+1
			throw new datoErroneoException("No se puede retirar una cantidad negativa");
		
		if (getGastosAcumulados() + x > mCredito) //WMC +1 CCOG+1
			throw new saldoInsuficienteException("Saldo insuficiente");
		
		Movimiento m = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		m.setF(now);
		m.setC("Compra a crédito en: " + datos);
		m.setI(-x);
		mMovimientosMensuales.add(m);
	}
	
    public double getGastosAcumulados() { //WMC +1  WMC DEL METODO = 2 , CCOG = 1
		double r = 0.0;
		for (int i = 0; i < this.mMovimientosMensuales.size(); i++) { //WMC +1 CCOG+1
			Movimiento m = (Movimiento) mMovimientosMensuales.get(i);
			r += m.getI();
		}
		return -r;
	}
	
	
	public LocalDate getCaducidadCredito() { //WMC = 1, CCOG = 0
		return this.mCuentaAsociada.getCaducidadCredito();
	}

	/**
	 * Método que se invoca automáticamente el día 1 de cada mes
	 */
	public void liquidar() { //WMC +1 WMC DEL METODO = 3 , CCOG = 2
		Movimiento liq = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		liq.setF(now);
		liq.setC("Liquidación de operaciones tarjeta crédito");
		double r = 0.0;
		for (int i = 0; i < this.mMovimientosMensuales.size(); i++) { //WMC +1 CCOG+1
			Movimiento m = (Movimiento) mMovimientosMensuales.get(i);
			r += m.getI();
		}
		liq.setI(r);
	
		if (r != 0) //WMC +1 CCOG+1
			mCuentaAsociada.addMovimiento(liq);
		
		mhistoricoMovimientos.addAll(mMovimientosMensuales);
		mMovimientosMensuales.clear();
	}

	public List<Movimiento> getMovimientosUltimoMes() {  //WMC = 1, CCOG = 0
		return mMovimientosMensuales;
	}
	
	public Cuenta getCuentaAsociada() {  //WMC = 1, CCOG = 0
		return mCuentaAsociada;  //CBO clase Cuenta (cbo+1)
	}
	
	public List<Movimiento> getMovimientos() {  //WMC = 1, CCOG = 0
		return mhistoricoMovimientos;
	}

}