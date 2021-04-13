package patronState;

import java.util.TimerTask;

/**
 * Clase que emplementa los métodos necesarios para crear una task, extendiendo de TimerTask
 * Usado para implementar el timer del intervalo de sonido de la alarma cuando suena
 * @author Carmen Marcos Sánchez de la Blanca
 * @version 13/04/2021
 */
public class IntervaloAlarmasTask extends TimerTask {
	
	//Atributos privados de la clase IntervaloAlarmasTask
	private Alarmas context;
	
	/**
	 * Constructor de la clase 
	 * @param alarmas objeto de la clase Alarmas
	 */
	public IntervaloAlarmasTask(Alarmas alarmas) {
		context = alarmas;
	}

	/**
	 * Redefinición del método run()
	 * Implementa el código que ejecutará la tarea cuando se active
	 */
	@Override
	public void run() {
		context.apagar();
	}
}
