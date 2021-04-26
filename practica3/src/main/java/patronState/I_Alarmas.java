package patronState;

import java.beans.PropertyChangeListener;
import java.util.Date;

/**
 * Interfaz del modelo para el MVC
 * @author Carmen Marcos Sánchez de la Blanca
 * @version 13/04/2021
 */
public interface I_Alarmas {

	/**
	 * Método señal que implementarán los estados correspondientes mediante el patrón State para crear una nueva alarma
	 * @param id id de la alarma a añadir
	 * @param hora fecha y hora de la alarma a añadir
	 */
	public void nuevaAlarma(String id, Date hora);

	/**
	 * Método señal que implementarán los estados correspondientes mediante el patrón State para borrar una alarma
	 * @param id id de la alarma a borrar
	 */
	public void borraAlarma(String id);

	/**
	 * Método señal que implementarán los estados correspondientes mediante el patrón State para apagar una alarma
	 */
	public void apagar();

	/**
	 * Método señal que implementarán los estados correspondientes mediante el patrón State para desactivar una alarma
	 * @param id de la alarma a desactivar
	 */
	public void alarmaOff(String id);

	/**
	 * Método señal que implementarán los estados correspondientes mediante el patrón State para activar una alarma
	 * @param id id de la alarma a activar
	 */
	public void alarmaOn(String id);

	/**
	 * Método que devuelve un array de Alarmas con las alarmas activas de la cola de prioridad
	 * @return array array con las alarmas activas
	 */
	public Alarma[] alarmasActivas ();

	/**
	 * Método que devuelve un array de Alarmas con las alarmas desactivadas de la lista 
	 * @return array array con las alarmas desactivadas
	 */
	public Alarma[] alarmasDesactivadas();

	/**
	 * Método que devuelve la alarma en función del id
	 * @param id id de la alarma buscada
	 * @return La alarma que tiene como identificador el string introducido
	 */
	public Alarma alarma(String id);

	/**
	 * Método que devuelve la alarma activa más próxima a sonar (sin eliminarla de la cola de prioridad)
	 * @return alarmasActivas.peek() alarma más próxima
	 */
	public Alarma alarmaMasProxima();

	/**
	 * Método que registra los listeners que se suscriben a cambios (patrón observer)
	 */
	public void addPropertyChangeListener (PropertyChangeListener listener);

}
