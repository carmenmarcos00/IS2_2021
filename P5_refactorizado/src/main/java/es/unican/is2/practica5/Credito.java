package es.unican.is2.practica5;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;


public class Credito extends Tarjeta {


	//WMC DE LA CLASE: 1+3+3+2+3+1+1 =14
	//WMCn DE LA CLASE: 14/2 = 2
	//CBO DE LA CLASE:	
	//DIT DE LA CLASE:	1
	//NOC DE LA CLASE:	0
	//CCog DE LA CLASE:	2+2+1+2 = 7

	private static final double COMISION_RETIRAR = 0.05;
	private double credito;
	private List<Movimiento> movimientosMensuales;
	private List<Movimiento> historicoMovimientos;


	public Credito(String numero, String titular, CuentaAhorro cuenta, double credito, LocalDate fechaCaducidad) { //WMC +1
		super(numero, titular, cuenta, fechaCaducidad);
		this.credito = credito;
		movimientosMensuales = new LinkedList<Movimiento>();
		historicoMovimientos = new LinkedList<Movimiento>();
	}

	/**
	 * Retirada de dinero en cajero con la tarjeta
	 * @param cantRetirar Cantidad a retirar. Se aplica una comisión del 5%.
	 * @throws saldoInsuficienteException
	 * @throws datoErroneoException
	 */
	@Override
	public void retirar(double cantRetirar) throws saldoInsuficienteException, datoErroneoException { //WMC +1 WMC TOTAL = 3 //CCOG TOTAL = 2
		
		if (cantRetirar<0) //WMC +1 //CCOG+1
			throw new datoErroneoException("No se puede retirar una cantidad negativa");


		cantRetirar += cantRetirar * COMISION_RETIRAR; // Añadimos una comisión de un 5%
		Movimiento mov = new Movimiento("Retirada en cajero automático", LocalDateTime.now(), cantRetirar);

		if (getGastosAcumulados()+cantRetirar > credito) //WMC +1 //CCOG+1
			throw new saldoInsuficienteException("Crédito insuficiente");
		else {
			movimientosMensuales.add(mov);
		}
	}

	@Override
	public void pagar(String concepto, double cantRetirar) throws saldoInsuficienteException, datoErroneoException { //WMC +1  WMC DEL METODO = 3 , CCOG =2
		if (cantRetirar<0)  //WMC +1 CCOG+1
			throw new datoErroneoException("No se puede retirar una cantidad negativa");

		if (getGastosAcumulados() + cantRetirar > credito) //WMC +1 //CCOG+1
			throw new saldoInsuficienteException("Saldo insuficiente");

		Movimiento mov = new Movimiento("Compra a crédito en: " + concepto, LocalDateTime.now(), -cantRetirar);
		movimientosMensuales.add(mov);
	}

	public double getGastosAcumulados() { //WMC +1 //WMC TOTAL = 2 //CCOG=1
		double gastosAcumulados = 0.0;
		for (int i = 0; i < this.movimientosMensuales.size(); i++) { //WMC +1 CCOG+1
			Movimiento mov = (Movimiento) movimientosMensuales.get(i);
			gastosAcumulados += mov.getImporte();
		}
		return -gastosAcumulados;
	}

	/**
	 * Método que se invoca automáticamente el día 1 de cada mes
	 */
	public void liquidar() { //WMC +1 WMC TOTAL = 3 //CCOG TOTAL = 2

		Movimiento liq = new Movimiento("Liquidación de operaciones tarjeta crédito",  LocalDateTime.now(), 0);
		double gastosAcumulados = 0.0;


		for (int i = 0; i < this.movimientosMensuales.size(); i++) { //WMC +1 //CCOG+1
			Movimiento mov = (Movimiento) movimientosMensuales.get(i);
			gastosAcumulados += mov.getImporte();
		}
		liq.setImporte(gastosAcumulados);

		if (gastosAcumulados != 0) //WMC +1 //CCOG+1
			cuentaAsociada.addMovimiento(liq);

		historicoMovimientos.addAll(movimientosMensuales);
		movimientosMensuales.clear();
	}

	public List<Movimiento> getMovimientosUltimoMes() {   //WMC +1
		return movimientosMensuales;
	}


	public List<Movimiento> getMovimientos() { //WMC +1
		return historicoMovimientos; 
	}



}