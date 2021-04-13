package patronState;

import java.util.Date;

/**
 * Clase que implementa los estados y transiciones asociadas al estado Desprogramado, que hereda de AlarmasState
 * @author Carmen Marcos Sánchez de la Blanca
 * @version 13/04/2021
 */
public class Desprogramado extends AlarmasState{


	/**
	 * Redefinición del método nuevaAlarma de la clase AlarmasState para este estado
	 */
	@Override
	public void nuevaAlarma(String id, Date hora, Alarmas context) {
		//Get el estado destino tras la transicion
		Programado programado =  (Programado) getEstadoProgramado();

		this.exitAction(context);
		context.setState(programado);
		//Inicio acciones asociadas a la transicion
		if(context.alarma(id) == null) {
			Alarma alarma = new Alarma (id, hora);
			context.anhadeAlarma(alarma);
		} else {
			System.out.println("Ya existe alarma con ese id");
		}
		//Fin acciones asociadas a la transicion
		programado.entryAction(context);
		programado.doAction(context);
	}

	/**
	 * Redefinición del método alarmaOn de la clase AlarmasState para este estado
	 */
	@Override
	public void alarmaOn(String id, Alarmas context) {
		//Get el estado destino tras la transicion
		Programado programado = (Programado) getEstadoProgramado();

		this.exitAction(context);
		context.setState(programado);
		//Inicio acciones asociadas a la transición
		Alarma alarmaActivar = context.alarma(id);
		if (alarmaActivar == null) {
			System.out.println("No existe esa alarma");
		} else {
			context.activaAlarma(alarmaActivar);
		}
		//Fin acciones asociadas a la transición
		programado.entryAction(context);
		programado.doAction(context);
	}


	/**
	 * Redefinición del método borraAlarma de la clase AlarmasState para este estado
	 */
	@Override
	public void borraAlarma(String id, Alarmas context) {
		this.exitAction(context);
		context.setState(this);
		//Inicio acciones asociadas a la transicion
		Alarma alarmaBorrar = context.alarma(id);
		if (alarmaBorrar == null) { 
			System.out.println("No puedo borrar una alarma que no existe");
		} else {
			context.eliminaAlarma(context.alarma(id));
		}
		//Fin acciones asociadas a la transicion
		this.entryAction(context);
		this.doAction(context);
	}

	/**
	 * Método con las acciones que hay que realizar siempre que se entre al estado Desprogramdo
	 * @param context objeto de la clase Alarmas
	 */
	public void entryAction(Alarmas context) {
		//No tiene implementacion
		//Definicion para posible futura implementacion
		//Mejora modularidad y comprension del codigo
	}

	/**
	 * Método con las acciones que hay que realizar de manera reiterada mientras se este en el estado Desprogramado
	 * @param context objeto de la clase Alarmas
	 */
	public void doAction(Alarmas context) {
		//No tiene implementacion
		//Definicion para posible futura implementacion
		//Mejora modularidad y comprension del codigo
	}

	/**
	 * Método con las acciones que hay que realizar al abandonar el estado Desprogramado
	 * @param context objeto de la clase Alarmas
	 */
	public void exitAction(Alarmas context) {
		//No tiene implementacion
		//Definicion para posible futura implementacion
		//Mejora modularidad y comprension del codigo
	}
}
