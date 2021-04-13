package patronState;

import java.util.Timer;

/**
 * Clase que implementa los estados y transiciones asociadas al estado Sonando, que hereda de AlarmasState
 * @author Carmen Marcos Sánchez de la Blanca
 * @version 13/04/2021
 */
public class Sonando extends AlarmasState {

	//Timer para el intervalo de tiempo
	protected Timer timerIntervalo = new Timer();
	protected IntervaloAlarmasTask alarmasIntervaloTask;


	/**
	 * Implementacion del método señal para apagar una alarma para el estado Sonando
	 * @param context objeto de la clase Alarmas
	 */
	@Override
	public void apagar( Alarmas context) {

		Programado programado =  (Programado) getEstadoProgramado();
		this.exitAction(context);

		//Inicio de acciones asociadas a la transicion
		alarmasIntervaloTask.cancel();
		//Fin de acciones asociadas a la transicion

		context.setState(programado);
		context.eliminaAlarma(context.alarmaMasProxima());
		programado.entryAction(context);
		programado.doAction(context);

	}

	/**
	 * Método con las acciones que hay que realizar siempre que se entre al estado Sonando
	 * @param context objeto de la clase Alarmas
	 */	
	public void entryAction(Alarmas context) {
		alarmasIntervaloTask= new IntervaloAlarmasTask(context);
		timerIntervalo.schedule(alarmasIntervaloTask, context.getIntervalo());
		context.activarMelodia();
	}

	/**
	 * Método con las acciones que hay que realizar al abandonar el estado Sonando
	 * @param context objeto de la clase Alarmas
	 */
	public void exitAction(Alarmas context) {
		//Hacer que cuando expire el timer se elimine tambien de la lista
		//y botones se restablezcan
		context.desactivarMelodia();

	}

	/**
	 * Método con las acciones que hay que realizar de manera reiterada mientras se este en el estado Sonando
	 * @param context objeto de la clase Alarmas
	 */
	public void doAction(Alarmas context) {
	}
}
