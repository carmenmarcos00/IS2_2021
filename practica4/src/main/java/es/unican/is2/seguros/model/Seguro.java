package es.unican.is2.seguros.model;

import java.time.LocalDate;

/**
 * Clase Seguros sobre la que se ejecutan los tests pedidos en el primer ejercicio de la pr�ctica 4
 * @author Carmen Marcos S�nchez de la Blanca
 * @version 26/04/2021 
 *
 */
public class Seguro  {

	//Atributos de la clase
	private LocalDate fechaUltimoSiniestro ;
	private int potenciaCV;
	private Cliente cliente;
	private Cobertura cobertura;

	/**
	 * Constructor de la clase de seguro
	 * @param fechaUltimoSiniestro fecha del �ltimo siniestro del coche
	 * @param potenciaCV potencia en cv del coche
	 */
	public Seguro(int potenciaCV, Cliente cliente, Cobertura cobertura) {

		this.potenciaCV = potenciaCV;
		this.cliente = cliente;
		this.cobertura = cobertura;
	}




	//Setters y getters
	public LocalDate getFechaUltimoSiniestro() {
		return this.fechaUltimoSiniestro;
	}

	public void setFechaUltimoSiniestro(LocalDate fechaUltimoSiniestro) {
		this.fechaUltimoSiniestro = fechaUltimoSiniestro;
	}

	public int getPotenciaCV() {
		return this.potenciaCV;
	}

	public Cliente getCliente () {
		return this.cliente;
	}

	public Cobertura getCobertura() {
		return this.cobertura;
	}


	public void setPotenciaCV(int potenciaCV) {
		this.potenciaCV = potenciaCV;
	}

	/**
	 * M�todo que me devuelve el precio base del seguro en funci�n de la cobertura contratada
	 * @return pbase precio base
	 */
	public double getPrecioBase () {

		double pbase = 0;

		if (this.cobertura.equals(Cobertura.TODO_RIESGO)) { 			//Cobertura  = TODO_RIESGO
			pbase = 1000;

		} else if (this.cobertura.equals(Cobertura.TERCEROS_LUNAS)) { 	//Cobertura = TERCEROS LUNAS
			pbase = 600;

		}else if  (cobertura.equals(Cobertura.TERCEROS)) {				//Cobertura = TERCEROS
			pbase = 400;
		}
		return pbase;
	}

	/**
	 * M�todo que me devuelve la cifra a multiplicar sobre el precio base en funcion de la potencia del coche
	 * @return multiplicador sobre el precio base del seguro
	 */
	public double getPorcentajeIncrementoPotencia()  {
		double multiplicador = 0.0;

		if (this.potenciaCV <= 0) { 									//(-infinito, 0]
			multiplicador = -1.0;

		} else if (this.potenciaCV > 0 && this.potenciaCV < 90) { 		//(0, 90)
			multiplicador = 1.0;

		} else if (this.potenciaCV >= 90 && this.potenciaCV <= 110) { 	//[90-110]
			multiplicador = 1.05;

		}else { 														//(110, +infinito)
			multiplicador = 1.20;
		}
		return multiplicador; 
	}

	/**
	 * M�todo que calcula el precio extra del seguro en funci�n de la fecha de �ltimo siiniestro
	 * @return precio extrea
	 */
	public double getExtraSiniestralidad() {

		//Si despues de hoy, error
		if (this.fechaUltimoSiniestro.isAfter(LocalDate.now())) {
			return -1.0;

			//Si antes de hoy (incluido, por eso +1) y despues de hace un a�o incluido
		} else if (this.fechaUltimoSiniestro.isBefore(LocalDate.now().plusDays(1)) && this.fechaUltimoSiniestro.isAfter(LocalDate.now().minusYears(1).minusDays(1))) {
			return 200.0;
			//Si hace m�s de un a�o y menos o igual que hace 3 a�os
		} else if (this.fechaUltimoSiniestro.isBefore(LocalDate.now().minusYears(1)) && this.fechaUltimoSiniestro.isAfter(LocalDate.now().minusYears(3).minusDays(1))) {
			return 50.0;
		} else {
			return 0.0;
		}
	}

	/**
	 * M�todo que calcula la cifra por la que multiplicar el precio final del producto en funci�n de minusval�a
	 * @return multiplicador minusval�a
	 */
	public double getDescuentoMinusvalia() {

		if (this.cliente.getMinusvalia()) {
			return 0.75;
		} else {
			return 1.0;
		}
	}


	/**
	 * M�todo que calcula el precio final del seguro en funci�n de los par�metros dados.
	 * @return precio final del seguro
	 * @throws DatoIncorrectoException En caso de que el dato introducido no sea v�lido (potencia == 0 | potencia <0  o fecha futura)
	 */
	public double precio() throws DatoIncorrectoException {

		//Variables locales auxiliares
		double pbase;
		double multiplicadorPotencia = 0.0;
		double extraSiniestralidad;
		double descuentoMinusvalia;


		//Calculo el precio base mediante la cobertura del seguro
		pbase = getPrecioBase();

		//Calculo incremento por potencia del coche
		multiplicadorPotencia = getPorcentajeIncrementoPotencia();

		if(multiplicadorPotencia == -1.0) {
			throw new DatoIncorrectoException(); 
		}

		//Calculo el precio extra por siniestralidad
		extraSiniestralidad = getExtraSiniestralidad();
		if (extraSiniestralidad == -1.0) {
			throw new DatoIncorrectoException();
		}

		//Calculo si hay descuento por minusval�a
		descuentoMinusvalia = getDescuentoMinusvalia();

		System.out.println( ((pbase * multiplicadorPotencia) + extraSiniestralidad) * descuentoMinusvalia);
		//Calculo final 
		return  ((pbase * multiplicadorPotencia) + extraSiniestralidad) * descuentoMinusvalia ;
	}
}
