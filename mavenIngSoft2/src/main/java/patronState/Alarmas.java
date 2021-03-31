package patronState;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class Alarmas {

	//CAMBIAR PRIORITY QUEUE POR SORTED MAP ?
	//ELIMINAR ALARMAS TOTALES ? 


	private AlarmasState state;
	//Facil conseguir la alarma siguiente y ordenarlas en función del tiempo restante
	private PriorityQueue<Alarma> alarmasActivas = new PriorityQueue<Alarma>();


	//private SortedMap<String, Alarma> alarmasActivas = new TreeMap<>();
	private List< Alarma> alarmasDesactivadas = new LinkedList<Alarma>();

	//Constructor de la clase Alarmas
	public Alarmas() {
		state = AlarmasState.init(this);
	}

	//Implementacion de los métodos de señal
	public void setState(AlarmasState value) {
		this.state = value;
	}
	public void nuevaAlarma(String id, Date hora, Alarmas context) {
		state.nuevaAlarma(id, hora, this);
	}
	public void borraAlarma(String id, Alarmas context) {
		state.borraAlarma(id, this);
	}
	public void apagar(Alarmas context) {
		state.apagar(this);
	}
	public void alarmaOff(String id, Alarmas context) {
		state.alarmaOff(id, this);
	}
	public void alarmaOn(String id, Alarmas context) {
		state.alarmaOn(id, this);
	}

	//Implementacion de los método de negocio (REVISAR DATO QUE DEVUELVE TODOS VOID ESQUELETO)
	/**
	 * Método que devuelve la alarma en función del id
	 * @param id
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

	//Contains reescribir equals (IMPORTANTE)
	/**
	 * Añade una nueva alarma preparada para sonar.
	 * @param alarmaAñadir
	 * @return true si se añade la alarma
	 * @return false si ya había una alarma para esa hora
	 */
	public boolean anhadeAlarma(Alarma alarmaAnhadir) {
		Date hora = alarmaAnhadir.getHora();
		String id = alarmaAnhadir.getId();
		Alarma alarmaMismaHora = null;

		//Tengo que buscar dentro de las alarmas activas y dentro de las alarmas desactivas si ya 
		//existe una alarma para esa hora
		//Busco si hay alguna alarma con ese id dentro de las alarmas activas (la cola)
		for (Alarma alarm : alarmasActivas) { 
			if (alarm.getHora().equals(hora)) {
				alarmaMismaHora = alarm;
			}
		}

		for (int i = 0; i<alarmasDesactivadas.size(); i++) {
			if(alarmasDesactivadas.get(i).getHora().equals(hora)) {
				alarmaMismaHora = alarmasDesactivadas.get(i);
			}
		}

		if(alarmaMismaHora != null) {
			return false;
		} else {
			alarmasActivas.add(alarmaAnhadir);
			return true;
		}
	}

	/**
	 * 
	 * @param alarmaEliminar alarma a eliminar 
	 */
	public boolean eliminaAlarma(Alarma alarmaEliminar) {

		//Saco el id de la alarma que quiero eliminar para buscar si existe
		String idAlarmaEliminar = alarmaEliminar.getId();

		//LLamo al método alarma para ver si existe esa alarma por el id
		Alarma alarma =alarma(idAlarmaEliminar);

		//Si no existe retorno false, no puedo eliminar
		if (alarma == null) {
			return false;
			//Si existe tengo que ver si la elimino de Desactivadas o de activadas	
		} else if (alarmasDesactivadas.equals(alarmaEliminar)) { //Borro de desactivadas
			alarmasDesactivadas.remove(alarmaEliminar);
			return true;
		} else { //Borro de activadas
			alarmasActivas.remove(alarmaEliminar);
			return true;
		}
	}

	/**
	 * Método que devuelve la alarma activa más próxima a sonar
	 * @return alarma más próxima
	 */
	public Alarma alarmaMasProxima() {

		if(alarmasActivas.size() == 0) {
			return null;
		}
		return alarmasActivas.peek(); 
	}

	/**
	 * Método que desactiva una alarma
	 * @param alarmaDesactivar alarma que quiero desactivar
	 */
	public void desactivaAlarma(Alarma alarmaDesactivar) {

		//Método remove devuelve true si ha logrado eliminar la alarma (comprobación implicita)
		boolean existe = alarmasActivas.remove(alarmaDesactivar); //Elimino alarma de activas
		if (existe == true) {
			alarmasDesactivadas.add(alarmaDesactivar); //La anhado en desactivas
		}
	}

	/**
	 * Muestra por pantalla un texto que avisa de que está sonando la alarma
	 */
	public void activarMelodia() {
	System.out.println("Sonando alarma");
	}
	
	/**
	 * Muestra por pantalla un texto que indica que se ha apagado la alarma
	 * y lo elimina pasado 5 segundos
	 * @throws InterruptedException
	 */
	public void desactivarMelodia() throws InterruptedException {
		System.out.println("Acaba de apagar la alarma");

	}

}

