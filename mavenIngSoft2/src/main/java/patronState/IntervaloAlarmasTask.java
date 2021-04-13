package patronState;

import java.util.TimerTask;

/**
 * Clase que emplementa los m�todos necesarios para crear una task, extendiendo de TimerTask
 * Usado para implementar el timer del intervalo de sonido de la alarma cuando suena
 * @author Carmen Marcos S�nchez de la Blanca
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
	 * Redefinici�n del m�todo run()
	 * Implementa el c�digo que ejecutar� la tarea cuando se active
	 */
	@Override
	public void run() {
		context.apagar();
	}
}
