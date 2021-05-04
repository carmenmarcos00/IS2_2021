package es.unican.is2.seguros.model;

import java.time.LocalDate;

/**
 * Clase Seguros sobre la que se ejecutan los tests pedidos en el primer ejercicio de la práctica 4
 * @author Carmen Marcos Sánchez de la Blanca
 * @version 26/04/2021 
 *
 */
public class Seguro  {

	//Atributos de la clase
	private LocalDate fechaUltimoSiniestro ;
	private int potenciaCV;
	//¿NO SALEN EN EL DIAGRAMA DE CLASES?
	private Cliente cliente;
	private Cobertura cobertura;

	/**
	 * Constructor de la clase de seguro
	 * @param fechaUltimoSiniestro fecha del último siniestro del coche
	 * @param potenciaCV potencia en cv del coche
	 */
	public Seguro(int potenciaCV, Cliente cliente, Cobertura cobertura) {
		this.potenciaCV = potenciaCV;
		this.cliente = cliente;
		this.cobertura = cobertura;
	}


	//Setters y getters
	public LocalDate getFechaUltimoSiniestro() {
		return fechaUltimoSiniestro;
	}

	public void setFechaUltimoSiniestro(LocalDate fechaUltimoSiniestro) {
		this.fechaUltimoSiniestro = fechaUltimoSiniestro;
	}

	public int getPotenciaCV() {
		return potenciaCV;
	}
	

	public void setPotenciaCV(int potenciaCV) {
		this.potenciaCV = potenciaCV;
	}

	/**
	 * 
	 * @return
	 */
	public double getPrecioBase () {

		double pbase = 0;

		if (this.cobertura.equals(Cobertura.TODO_RIESGO)) {
			pbase = 1000;

		} else if (this.cobertura.equals(Cobertura.TERCEROS_LUNAS)) {
			pbase = 600;

		}else if  (cobertura.equals(Cobertura.TERCEROS)) {
			pbase = 400;
		}
		return pbase;
	}

	/**
	 * 
	 * @return
	 * @throws DatoIncorrectoException
	 */
	public double getPorcentajeIncrementoPotencia()  {
		double multiplicador = 0.0;

		if (this.potenciaCV <= 0) { //(-infinito, 0]
			multiplicador = -1.0;

		} else if (this.potenciaCV > 0 && this.potenciaCV < 90) { //(0, 90)
			multiplicador = 1.0;

		} else if (this.potenciaCV >= 90 && this.potenciaCV <= 110) { //[90-110]
			multiplicador = 1.05;

		}else { //(110, +infinito)
			multiplicador = 1.20;
		}
		return multiplicador; 
	}

	/**
	 * 
	 * @return
	 */
	public double getExtraSiniestralidad() {
		
		//Si despues de hoy, error
		if (this.fechaUltimoSiniestro.isAfter(LocalDate.now())) {
			return -1.0;

			//Si antes de hoy (incluido, por eso +1) y despues de hace un año incluido
		} else if (this.fechaUltimoSiniestro.isBefore(LocalDate.now().plusDays(1)) && this.fechaUltimoSiniestro.isAfter(LocalDate.now().minusYears(1).minusDays(1))) {
			return 200.0;
			//Si hace más de un año y menos o igual que hace 3 años
		} else if (this.fechaUltimoSiniestro.isBefore(LocalDate.now().minusYears(1)) && this.fechaUltimoSiniestro.isAfter(LocalDate.now().minusYears(3).minusDays(1))) {
			return 50.0;
		} else {
			return 0.0;
		}
	}

	/**
	 * 
	 * @return
	 */
	public double getDescuentoMinusvalia() {

		if (this.cliente.getMinusvalia()) {
			return 0.75;
		} else {
			return 1.0;
		}
	}

	//Método de negocio de la clase
	/**
	 * 
	 * @return
	 * @throws DatoIncorrectoException 
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

		//Calculo si hay descuento por minusvalía
		descuentoMinusvalia = getDescuentoMinusvalia();

		System.out.println( ((pbase * multiplicadorPotencia) + extraSiniestralidad) * descuentoMinusvalia);
		//Calculo final 
		return  ((pbase * multiplicadorPotencia) + extraSiniestralidad) * descuentoMinusvalia ;
	}
}
