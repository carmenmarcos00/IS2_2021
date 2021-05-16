package es.unican.is2.practica5;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class CuentaAhorro extends Cuenta {
	
	//WMC DE LA CLASE: 1+2+3+2+3+2+1+1+1+1+1 = 18
	//WMCn DE LA CLASE: 18/11 = 1.64
	//CBO DE LA CLASE:	1 depende de 1
	//DIT DE LA CLASE:	1
	//NOC DE LA CLASE:	0
	//CCog DE LA CLASE: 1+2+1+2+1 = 7

	private List<Movimiento> mMovimientos; //CBO movimiento
	private LocalDate mFechaDeCaducidadTarjetaDebito;
	private LocalDate mFechaDeCaducidadTarjetaCredito;
	private double limiteDebito;

	public CuentaAhorro(String numCuenta, LocalDate date, LocalDate date2) { //WMC +1 WMC TOTAL = 1 //CCOG= 0
		super(numCuenta);
		this.mFechaDeCaducidadTarjetaDebito = date;
		this.mFechaDeCaducidadTarjetaCredito = date2;
		mMovimientos = new LinkedList<Movimiento>();
		limiteDebito = 1000;
	}

	public void ingresar(double x) throws datoErroneoException { //WMC +1 WMC TOTAL = 2 //CCOG= 1
		if (x <= 0)//WC +1 //CCOG+1
			throw new datoErroneoException("No se puede ingresar una cantidad negativa");
		Movimiento m = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		m.setF(now);
		m.setC("Ingreso en efectivo");
		m.setI(x);
		this.mMovimientos.add(m);
	}

	public void retirar(double x) throws saldoInsuficienteException, datoErroneoException { //WMC +1 WMC TOTAL = 3 //CCOG= 2
		if (x <= 0) //WMC +1 //CCOG+1
			throw new datoErroneoException("No se puede retirar una cantidad negativa");
		if (getSaldo() < x) //WMC +1 //CCOG+1
			throw new saldoInsuficienteException("Saldo insuficiente");
		Movimiento m = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		m.setF(now);
		m.setC("Retirada de efectivo");
		m.setI(-x);
		this.mMovimientos.add(m);
	}

	public void ingresar(String concepto, double x) throws datoErroneoException { //WMC +1 WMC TOTAL = 2 //CCOG=1
		if (x <= 0) //WMC +1 //CCOG+1
			throw new datoErroneoException("No se puede ingresar una cantidad negativa");
		Movimiento m = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		m.setF(now);
		m.setC(concepto);
		m.setI(x);
		this.mMovimientos.add(m);
	}

	public void retirar(String concepto, double x) throws saldoInsuficienteException, datoErroneoException { //WMC +1 WMC TOTAL = 3 //CCOG= 2
		if (getSaldo() < x) //WMC +1 //CCOG+1
			throw new saldoInsuficienteException("Saldo insuficiente");
		if (x <= 0) //WMC +1 //CCOG+1
			throw new datoErroneoException("No se puede retirar una cantidad negativa");
		Movimiento m = new Movimiento();
		LocalDateTime now = LocalDateTime.now();
		m.setF(now);
		m.setC(concepto);
		m.setI(-x);
		this.mMovimientos.add(m);
	}

	public double getSaldo() { //WMC +1 /WMC TOTAL = 2 //CCOG= 1
		double r = 0.0;
		for (int i = 0; i < this.mMovimientos.size(); i++) { //WMC +1 //CCOG+1
			Movimiento m = (Movimiento) mMovimientos.get(i);
			r += m.getI();
		}
		return r;
	}

	public void addMovimiento(Movimiento m) {//WMC +1 //WMC TOTAL = 1 //CCOG=0
		mMovimientos.add(m);
	}

	public List<Movimiento> getMovimientos() { //WMC TOTAL = 1 //CCOG=0
		return mMovimientos;
	}

	public LocalDate getCaducidadDebito() { //WMC TOTAL = 1 //CCOG=0
		return this.mFechaDeCaducidadTarjetaDebito;
	}

	public LocalDate getCaducidadCredito() {  //WMC TOTAL = 1//CCOG=0
		return this.mFechaDeCaducidadTarjetaCredito;
	}

	public double getLimiteDebito() { //WMC TOTAL = 1 //CCOG=0
		return limiteDebito;
	}

}