package patronState;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Date;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;



public class Alarmas implements I_Alarmas {

	private AlarmasState state;
	//Patron observer con MVC
	private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

	private PriorityQueue<Alarma> alarmasActivas = new PriorityQueue<Alarma>();
	private List< Alarma> alarmasDesactivadas = new LinkedList<Alarma>();

	//Constructor de la clase Alarmas
	public Alarmas() {
		state = AlarmasState.init(this);
	}

	//Implementacion de los métodos de señal
	public void setState(AlarmasState value) {
		AlarmasState antiguo = this.state;
		this.state = value;
		if (value instanceof Sonando) { //Patrón observer
			changeSupport.firePropertyChange("state",antiguo,value); //Notifico
		}
	}

	public void nuevaAlarma(String id, Date hora) {
		state.nuevaAlarma(id, hora, this);
	}

	public void borraAlarma(String id) {
		state.borraAlarma(id, this);
	}

	public void apagar() {
		state.apagar(this);
	}

	public void alarmaOff(String id) {
		state.alarmaOff(id, this);
	}
	public void alarmaOn(String id) {
		state.alarmaOn(id, this);
	}

	//Implementacion de los método de negocio 

	public Alarma[] alarmasActivas () {

		Alarma[] arr1 = new Alarma[alarmasActivas.size()];
		Alarma[] arr2 = alarmasActivas.toArray(arr1); 
		return arr2;
	}

	public Alarma[] alarmasDesactivadas() {
		Alarma[] arr1 = new Alarma[alarmasDesactivadas.size()];
		Alarma[] arr2 = alarmasDesactivadas.toArray(arr1); 
		return arr2;
	}
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

	/**
	 * Añade una nueva alarma preparada para sonar.
	 * @param alarmaAñadir
	 * @return true si se añade la alarma
	 * @return false si ya había una alarma para esa hora
	 */
	public boolean anhadeAlarma(Alarma alarmaAnhadir) {
		Date hora = alarmaAnhadir.getHora();
		Alarma alarmaMismaHora = null;

		//TODO GESTION DE QUE YA EXISTA ALARMA A ESA HORA (PROPAGAR?? O IGNORO)
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
			return false; //GESTION YA EXISTE ALARMA A ESA HORA
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
		//LLamo al método alarma para ver si existe esa alarma por el id
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
	 * Método que activa una alarma ya existente que estaba desactivada
	 * @param alarmaActivar alarma ya existente que hay que activa
	 */
	public void activaAlarma (Alarma alarmaActivar) {
		//Metodo contains devuelve true si está la lista
		boolean existe =alarmasDesactivadas.contains( alarmaActivar);
		if (existe == true) {
			alarmasDesactivadas.remove(alarmaActivar); //Elimino de desactivas
			alarmasActivas.add(alarmaActivar); //Anhado a activas
		}
	}

	/**
	 * Método que desactiva una alarma
	 * @param alarmaDesactivar alarma que quiero desactivar
	 */
	public void desactivaAlarma(Alarma alarmaDesactivar) {

		System.out.println("DESACTIVO ALARMA");

		System.out.println("DESACTIVADAS ANTES: "+alarmasDesactivadas.size());
		System.out.println("ACTIVAS ANTES: "+alarmasActivas.size());

		//Método remove devuelve true si ha logrado eliminar la alarma (comprobación implicita)
		boolean existe = alarmasActivas.remove(alarmaDesactivar); //Elimino alarma de activas
		if (existe == true) {
			alarmasDesactivadas.add(alarmaDesactivar); //La anhado en desactivas
		}
		System.out.println("DESACTIVADAS DESPUES: "+alarmasDesactivadas.size());
		System.out.println("ACTIVAS DESPUES: "+alarmasActivas.size());

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
	public void desactivarMelodia() {
		System.out.println("Acaba de apagar la alarma");
		//alarmasActivas.poll();
	}

	//Método que registra listeners
	public void addPropertyChangeListener (PropertyChangeListener listener){
		changeSupport.addPropertyChangeListener(listener);
	}
}

