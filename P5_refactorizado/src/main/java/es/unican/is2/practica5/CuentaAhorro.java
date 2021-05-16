package es.unican.is2.practica5;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class CuentaAhorro extends Cuenta {
	
	//WMC DE LA CLASE: 1+2+3+2+3+2+1+1+1 = 16
	//WMCn DE LA CLASE: 16 /9 = 1.777
	//CBO DE LA CLASE:	
	//DIT DE LA CLASE:	1
	//NOC DE LA CLASE:	0
	//CCog DE LA CLASE: 7

	private List<Movimiento> movimientos; //CBO movimiento
	private static final int LIMITE_DEBITO = 1000;

	public CuentaAhorro(String numCuenta) { //WMC +1 WMC TOTAL = 1 //CCOG= 0
		super(numCuenta);
		movimientos = new LinkedList<Movimiento>();
	}

	public void ingresar(double cantIngresar) throws datoErroneoException { //WMC +1 WMC TOTAL = 2 //CCOG= 1
		if (cantIngresar <= 0)//WC +1 //CCOG+1
			throw new datoErroneoException("No se puede ingresar una cantidad negativa");
		Movimiento mov = new Movimiento("Ingreso en efectivo", LocalDateTime.now(), cantIngresar );
		this.movimientos.add(mov);
	}

	public void retirar(double cantRetirar) throws saldoInsuficienteException, datoErroneoException { //WMC +1 WMC TOTAL = 3 //CCOG= 2
		if (cantRetirar <= 0) //WMC +1 //CCOG+1
			throw new datoErroneoException("No se puede retirar una cantidad negativa");
		if (getSaldo() < cantRetirar) //WMC +1 //CCOG+1
			throw new saldoInsuficienteException("Saldo insuficiente");
		
		Movimiento mov = new Movimiento("Retirada de efectivo", LocalDateTime.now(), -cantRetirar);
		this.movimientos.add(mov);
	}

	public void ingresar(String concepto, double cantIngresar) throws datoErroneoException { //WMC +1 WMC TOTAL = 2 //CCOG=1
		if (cantIngresar <= 0) //WMC +1 //CCOG+1
			throw new datoErroneoException("No se puede ingresar una cantidad negativa");
		Movimiento m = new Movimiento(concepto, LocalDateTime.now(),cantIngresar);
		this.movimientos.add(m);
	}

	public void retirar(String concepto, double cantRetirar) throws saldoInsuficienteException, datoErroneoException { //WMC +1 WMC TOTAL = 3 //CCOG= 2
		if (getSaldo() < cantRetirar) //WMC +1 //CCOG+1
			throw new saldoInsuficienteException("Saldo insuficiente");
		if (cantRetirar <= 0) //WMC +1 //CCOG+1
			throw new datoErroneoException("No se puede retirar una cantidad negativa");
		Movimiento mov = new Movimiento(concepto, LocalDateTime.now(), -cantRetirar);
		this.movimientos.add(mov);
	}

	public double getSaldo() { //WMC +1 /WMC TOTAL = 2 //CCOG= 1
		double saldo = 0.0;
		for (int i = 0; i < this.movimientos.size(); i++) { //WMC +1 //CCOG+1
			Movimiento mov = (Movimiento) movimientos.get(i);
			saldo += mov.getImporte();
		}
		return saldo;
	}

	public void addMovimiento(Movimiento mov) {//WMC +1 //WMC TOTAL = 1 //CCOG=0
		movimientos.add(mov);
	}

	public List<Movimiento> getMovimientos() { //WMC TOTAL = 1 //CCOG=0
		return movimientos;
	}


	public double getLimiteDebito() { //WMC TOTAL = 1 //CCOG=0
		return LIMITE_DEBITO;
	}

}