package patronState;
import java.util.Date;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class Alarmas {

	private AlarmasState state;
	
	//Facil conseguir la alarma siguiente y ordenarlas en funci�n del tiempo restante
	private PriorityQueue<Alarma> alarmasActivas = new PriorityQueue<Alarma>();
	//private SortedMap<String, Alarma> alarmasActivas = new TreeMap<>();
	private List< Alarma> alarmasDesactivadas = new LinkedList<Alarma>();

	//Constructor de la clase Alarmas
	public Alarmas() {
		state = AlarmasState.init(this);
	}

	//Implementacion de los m�todos de se�al
	public void setState(AlarmasState value) {
		this.state = value;
	}
	public void nuevaAlarma(String id, Date hora, Alarmas context) {
		state.nuevaAlarma(id, hora, this);
	}
	public void borraAlarma(String id, Alarmas context) {
		state.borraAlarma(id, this);
	}
	
	//NO SE SI CORRECTO A�ADIR ID PARA SABER QUE ALARMA DESACTIVAR
	public void apagar(String id,Alarmas context) {
		state.apagar(id, this);
	}
	public void alarmaOff(String id, Alarmas context) {
		state.alarmaOff(id, this);
	}
	public void alarmaOn(String id, Alarmas context) {
		state.alarmaOn(id, this);
	}

	//Implementacion de los m�todo de negocio 
	
	
	public Alarmas[] alarmasActivas () {
		Alarmas[] alarmasActivasArr = (Alarmas[]) alarmasActivas.toArray();
		return alarmasActivasArr;
	}
	
	public Alarmas[] alarmasDesactivadas() {
		Alarmas[] alarmasDesactivadasArr = (Alarmas[]) alarmasDesactivadas.toArray();
		return alarmasDesactivadasArr;
	}
	/**
	 * M�todo que devuelve la alarma en funci�n del id
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
	 * A�ade una nueva alarma preparada para sonar.
	 * @param alarmaA�adir
	 * @return true si se a�ade la alarma
	 * @return false si ya hab�a una alarma para esa hora
	 */
	public boolean anhadeAlarma(Alarma alarmaAnhadir) {
		Date hora = alarmaAnhadir.getHora();
		Alarma alarmaMismaHora = null;

		//Tengo que buscar dentro de las alarmas activas y dentro de las alarmas desactivas si ya 
		//existe una alarma para esa hora
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
			return false;
		} else { //Si sigue a null no hay ninguna alarma para esa hora
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

		//LLamo al m�todo alarma para ver si existe esa alarma por el id
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
	 * M�todo que devuelve la alarma activa m�s pr�xima a sonar
	 * @return alarma m�s pr�xima
	 */
	public Alarma alarmaMasProxima() {
		if(alarmasActivas.size() == 0) {
			return null;
		}
		return alarmasActivas.peek(); 
	}
	
	/**
	 * M�todo que activa una alarma ya existente que estaba desactivada
	 * @param alarmaActivar alarma ya existente que hay que activa
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
	 * y lo elimina pasado 5 segundos
	 * @throws InterruptedException
	 */
	public void desactivarMelodia() {
		System.out.println("Acaba de apagar la alarma");
	}
}

