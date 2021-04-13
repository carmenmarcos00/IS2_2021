package patronState;

import java.util.TimerTask;

/**
 * Clase que emplementa los m�todos necesarios para crear una task, extendiendo de TimerTask
 * Usado para implementar el timer de activaci�n de las alarmas
 * @author Carmen Marcos S�nchez de la Blanca
 * @version 13/04/2021
 */
public class AlarmasTask extends TimerTask {
	
	//Atributos privados de la clase AlarmasTask
	private Alarmas context;

	/**
	 * Constructor de la clase 
	 * @param alarmas objeto de la clase Alarmas
	 */
	public AlarmasTask(Alarmas alarmas) {
		context = alarmas;
	}

	/**
	 * Redefinici�n del m�todo run()
	 * Implementa el c�digo que ejecutar� la tarea cuando se active
	 */
	@Override
	public void run() {
		Programado programado = (Programado) AlarmasState.getEstadoProgramado();
		Sonando sonando = (Sonando) AlarmasState.getEstadoSonando();
		programado.exitAction(context);
		context.setState(sonando);
		sonando.entryAction(context);
		sonando.doAction(context);
	}
}


