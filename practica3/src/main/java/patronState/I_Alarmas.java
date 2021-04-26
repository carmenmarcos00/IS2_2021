package patronState;

import java.beans.PropertyChangeListener;
import java.util.Date;

/**
 * Interfaz del modelo para el MVC
 * @author Carmen Marcos S�nchez de la Blanca
 * @version 13/04/2021
 */
public interface I_Alarmas {

	/**
	 * M�todo se�al que implementar�n los estados correspondientes mediante el patr�n State para crear una nueva alarma
	 * @param id id de la alarma a a�adir
	 * @param hora fecha y hora de la alarma a a�adir
	 */
	public void nuevaAlarma(String id, Date hora);

	/**
	 * M�todo se�al que implementar�n los estados correspondientes mediante el patr�n State para borrar una alarma
	 * @param id id de la alarma a borrar
	 */
	public void borraAlarma(String id);

	/**
	 * M�todo se�al que implementar�n los estados correspondientes mediante el patr�n State para apagar una alarma
	 */
	public void apagar();

	/**
	 * M�todo se�al que implementar�n los estados correspondientes mediante el patr�n State para desactivar una alarma
	 * @param id de la alarma a desactivar
	 */
	public void alarmaOff(String id);

	/**
	 * M�todo se�al que implementar�n los estados correspondientes mediante el patr�n State para activar una alarma
	 * @param id id de la alarma a activar
	 */
	public void alarmaOn(String id);

	/**
	 * M�todo que devuelve un array de Alarmas con las alarmas activas de la cola de prioridad
	 * @return array array con las alarmas activas
	 */
	public Alarma[] alarmasActivas ();

	/**
	 * M�todo que devuelve un array de Alarmas con las alarmas desactivadas de la lista 
	 * @return array array con las alarmas desactivadas
	 */
	public Alarma[] alarmasDesactivadas();

	/**
	 * M�todo que devuelve la alarma en funci�n del id
	 * @param id id de la alarma buscada
	 * @return La alarma que tiene como identificador el string introducido
	 */
	public Alarma alarma(String id);

	/**
	 * M�todo que devuelve la alarma activa m�s pr�xima a sonar (sin eliminarla de la cola de prioridad)
	 * @return alarmasActivas.peek() alarma m�s pr�xima
	 */
	public Alarma alarmaMasProxima();

	/**
	 * M�todo que registra los listeners que se suscriben a cambios (patr�n observer)
	 */
	public void addPropertyChangeListener (PropertyChangeListener listener);

}
