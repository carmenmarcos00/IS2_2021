package patronState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Date;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;


/**
 * Clase Alarmas que define los atributos, m�todos y el comportamiento de todas las alarmas.
 * Es el la clase del modelo que implementa los m�todos de la interfaz I_Alarmas en el MVC
 * @author Carmen Marcos S�nchez de la Blanca
 * @version 13/04/2021
 */
public class Alarmas implements I_Alarmas {

	//Atributos privados de la clase
	private AlarmasState state;
	private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);	//Patron observer con MVC
	private PriorityQueue<Alarma> alarmasActivas = new PriorityQueue<Alarma>(); //Cola de prioridad de alarmas activas
	private List< Alarma> alarmasDesactivadas = new LinkedList<Alarma>(); //Lista de las alarmas desactivadas

	private final int INTERVALO_SONANDO = 5000; //Intervalo de borrado de la alarma sonando

	/**
	 * Constructor de la clase alarmas
	 */
	public Alarmas() {
		state = AlarmasState.init(this);
	}

	//Implementacion de los m�todos de se�al

	/**
	 * Setter del estado en el que se encuentra la aplicaci�n de alarmas
	 * @param value objeto de la clase AlarmasState
	 */
	public void setState(AlarmasState value) {
		AlarmasState antiguo = this.state;
		this.state = value;
		if (value instanceof Sonando) { //Patr�n observer
			changeSupport.firePropertyChange("state",antiguo,value); //Notifico
		}
		if ( value instanceof Programado && antiguo instanceof Sonando) { //Notifico
			changeSupport.firePropertyChange("apagaAlarmaTimer",antiguo, value);
			changeSupport.firePropertyChange("BorraDeList", antiguo, value);
		}
	}

	/**
	 * M�todo se�al que implementar�n los estados correspondiente mediante el patr�nn State para a�adir una alarma nueva
	 */
	public void nuevaAlarma(String id, Date hora) {
		state.nuevaAlarma(id, hora, this);
	}

	/**
	 * M�todo se�al que implementar�n los estados correspondientes mediante el patr�n State para borrar una alarma
	 */
	public void borraAlarma(String id) {
		state.borraAlarma(id, this);
	}

	/**
	 * M�todo se�al que implementar�n los estados correspondientes mediante el patr�n State para apagar una alarma
	 */
	public void apagar() {
		state.apagar(this);
	}

	/**
	 * M�todo se�al que implementar�n los estados correspondientes mediante el patr�n State para desactivar una alarma
	 */
	public void alarmaOff(String id) {
		state.alarmaOff(id, this);
	}
	
	/**
	 * M�todo se�al que implementar�n los estados correspondientes mediante el patr�n State para activar una alarma
	 */
	public void alarmaOn(String id) {
		state.alarmaOn(id, this);
	}

	//Implementacion de los m�todo de negocio 

	/**
	 * M�todo que devuelve un array de Alarmas con las alarmas activas de la cola de prioridad
	 */
	public Alarma[] alarmasActivas () {

		Alarma[] arr1 = new Alarma[alarmasActivas.size()];
		Alarma[] arr2 = alarmasActivas.toArray(arr1); 
		return arr2;
	}

	/**
	 * M�todo que devuelve un array de Alarmas con las alarmas desactivadas de la lista 
	 */
	public Alarma[] alarmasDesactivadas() {
		Alarma[] arr1 = new Alarma[alarmasDesactivadas.size()];
		Alarma[] arr2 = alarmasDesactivadas.toArray(arr1); 
		return arr2;
	}

	/**
	 * M�todo que devuelve la alarma en funci�n del id
	 * @param id id de la alarma buscada
	 * @return La alarma que tiene como identificador el string introducido
	 */
	public Alarma alarma(String id) {

		Alarma alarmaBuscada = null;
		//Busco si hay alguna alarma con ese id dentro de las alarmas activas (la cola)
		for (Alarma alarm : alarmasActivas) { 
			if (alarm.getId().equals(id)) {
				alarmaBuscada = alarm;
			}
		}
		//Si ha encontrado una alarma con ese id la devuelvo
		if (alarmaBuscada != null) {
			return alarmaBuscada;
		}
		//Busco si hay alguna alarma con ese id dentro de las alarmas desactivadas(la lista)
		for (int i = 0; i<alarmasDesactivadas.size(); i++) {
			if(alarmasDesactivadas.get(i).getId().equals(id)) {
				alarmaBuscada = alarmasDesactivadas.get(i);
			}
		}
		//Haya encontrado o no la alarma devuelvo alarmaBuscada
		return alarmaBuscada;
	}

	/**
	 * A�ade una nueva alarma preparada para sonar.
	 * @param alarmaA�adir alarma que hay que a�adir
	 * @return true si se a�ade la alarma
	 * @return false si ya hab�a una alarma para esa hora
	 */
	public boolean anhadeAlarma(Alarma alarmaAnhadir) {
		Date hora = alarmaAnhadir.getHora();
		Alarma alarmaMismaHora = null;


		//Busco si hay alguna alarma con esa hora dentro de las alarmas activas (la cola)
		for (Alarma alarm : alarmasActivas) { 
			if (alarm.getHora().equals(hora)) {
				alarmaMismaHora = alarm;
			}
		}
		//Busco si hay alguna alarma con esa hora dentro de alarmasDesactivadas
		for (int i = 0; i<alarmasDesactivadas.size(); i++) {
			if(alarmasDesactivadas.get(i).getHora().equals(hora)) {
				alarmaMismaHora = alarmasDesactivadas.get(i);
			}
		}
		//Si ha sido modificada la alarma en alguno de los bucles anteriores ya existe una alarma en esa hora
		if(alarmaMismaHora != null) {
			return false; //GESTION YA EXISTE ALARMA A ESA HORA
		} else { //Si sigue a null no hay ninguna alarma para esa hora
			alarmasActivas.add(alarmaAnhadir);
			return true;
		}
	}

	/**
	 * M�todo que elimina la alarma pasada como par�metro de entrada
	 * @param alarmaEliminar alarma a eliminar 
	 */
	public boolean eliminaAlarma(Alarma alarmaEliminar) {

		//Saco el id de la alarma que quiero eliminar para buscar si existe
		String idAlarmaEliminar = alarmaEliminar.getId();
		//LLamo al m�todo alarma para ver si existe esa alarma por el id
		Alarma alarma =alarma(idAlarmaEliminar);

		//Si no existe retorno false, no puedo eliminar
		if (alarma == null) {
			return false; 

			//Si existe tengo que ver si la elimino de Desactivadas o de activadas	
		} else if (alarmasDesactivadas.contains(alarmaEliminar)) { //Borro de desactivadas
			alarmasDesactivadas.remove(alarmaEliminar);
			return true;

		} else { //Borro de activadas
			alarmasActivas.remove(alarmaEliminar); 
			return true;
		}	
	}

	/**
	 * M�todo que devuelve la alarma activa m�s pr�xima a sonar (sin eliminarla de la cola de prioridad)
	 * @return alarmasActivas.peek() alarma m�s pr�xima
	 */
	public Alarma alarmaMasProxima() {
		if(alarmasActivas.size() == 0) {
			return null;
		}
		return alarmasActivas.peek(); 
	}

	/**
	 * M�todo que activa una alarma ya existente que estaba desactivada
	 * @param alarmaActivar alarma ya existente que hay que activar
	 */
	public void activaAlarma (Alarma alarmaActivar) {

		//Metodo contains devuelve true si est� la lista
		boolean existe =alarmasDesactivadas.contains( alarmaActivar);
		if (existe == true) {
			alarmasDesactivadas.remove(alarmaActivar); //Elimino de desactivas
			alarmasActivas.add(alarmaActivar); //Anhado a activas
		}
	}

	/**
	 * M�todo que desactiva una alarma
	 * @param alarmaDesactivar alarma que quiero desactivar
	 */
	public void desactivaAlarma(Alarma alarmaDesactivar) {

		//M�todo remove devuelve true si ha logrado eliminar la alarma (comprobaci�n implicita)
		boolean existe = alarmasActivas.remove(alarmaDesactivar); //Elimino alarma de activas
		if (existe == true) {
			alarmasDesactivadas.add(alarmaDesactivar); //La anhado en desactivas
		}

	}

	/**
	 * Muestra por pantalla un texto que avisa de que est� sonando la alarma
	 */
	public void activarMelodia() {
		System.out.println("Sonando alarma");
	}

	/**
	 * Muestra por pantalla un texto que indica que se ha apagado la alarma
	 */
	public void desactivarMelodia() {
		System.out.println("Acaba de apagar la alarma");
	}

	/**
	 * M�todo que registra los listeners que se suscriben a cambios (patr�n observer)
	 */
	public void addPropertyChangeListener (PropertyChangeListener listener){
		changeSupport.addPropertyChangeListener(listener);
	}

	/**
	 * Getter de la constante del intervalo de tiempo que estar� sonando la alarma
	 * @return INTERVALO_SONANDO intervalo de tiempo que estar� sonando la alarma
	 * Al pasar ese tiempo, se apaga la alarma autom�ticamente
	 */
	public int getIntervalo() {
		return INTERVALO_SONANDO;
	}
}

