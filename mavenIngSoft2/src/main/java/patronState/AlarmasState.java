package patronState;

import java.util.Date;

/**
 * Clase AlarmasState del patron State donde se definen los posibles estados 
 * @author Carmen Marcos S�nchez de la Blanca
 * @version 13/04/2021
 */
public abstract class AlarmasState {

	//Variables privadas de la clase
	private static Sonando estadoSonando = new Sonando();
	private static Programado estadoProgramado = new Programado();
	private static Desprogramado estadoDesprogramado = new Desprogramado();

	/**
	 * M�todo que inicia los estados
	 * @param context objeto de la clase Alarmas con el set inicial de alarmas
	 * @return estadoDesprogramado estado inicial de la aplicaci�n al ser lanzada
	 */
	public static AlarmasState init(Alarmas context) {
		estadoDesprogramado.entryAction(context);
		return estadoDesprogramado;
	}

	/**
	 * M�todo se�al que implementar�n los estados correspondientes mediante el patr�n State para crear una nueva alarma
	 * @param id id de la alarma a a�adir
	 * @param hora fecha y hora de la alarma a a�adir
	 * @param context objeto de la clase Alarmas con el estado de todas las alarmas
	 */
	public void nuevaAlarma(String id, Date hora, Alarmas context) {};
	
	/**
	 * M�todo se�al que implementar�n los estados correspondientes mediante el patr�n State para borrar una alarma
	 * @param id id de la alarma a borrar
	 * @param context objeto de la clase Alarmas con el estado de todas las alarmas
	 */
	public void borraAlarma(String id, Alarmas context) {};
	
	/**
	 * M�todo se�al que implementar�n los estados correspondientes mediante el patr�n State para apagar una alarma
	 * @param context objeto de la clase Alarmas con el estado de todas las alarmas
	 */
	public void apagar(Alarmas context) {};
	
	/**
	 * M�todo se�al que implementar�n los estados correspondientes mediante el patr�n State para desactivar una alarma
	 * @param id de la alarma a desactivar
	 * @param context objeto de la clase Alarmas con el estado de todas las alarmas
	 */
	public void alarmaOff(String id, Alarmas context) {};
	
	
	/**
	 * M�todo se�al que implementar�n los estados correspondientes mediante el patr�n State para activar una alarma
	 * @param id id de la alarma a activar
	 * @param context objeto de la clase Alarmas con el estado de todas las alarmas
	 */
	public void alarmaOn(String id, Alarmas context) {};


	/**
	 * Getter de estadoSonando
	 * @return estadoProgramado objeto de la clase Sonando que hereda de AlarmasState
	 */
	public static AlarmasState getEstadoSonando() {
		return estadoSonando;
	};
	
	/**
	 * Getter de estadoProgramado
	 * @return estadoProgramado objeto de la clase Programado que hereda de AlarmasState
	 */
	public static AlarmasState getEstadoProgramado() {
		return estadoProgramado;
	};
	
	/**
	 * Getter de estadoDesprogramado
	 * @return estadoDesprogramado objeto de la clase Desprogramado que hereda de AlarmasState
	 */
	public static AlarmasState getEstadoDesprogramado() {
		return estadoDesprogramado;
	}
}
