package patronState;

import java.util.Date;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Alarmas {

	private AlarmasState state;
	//Facil conseguir la alarma siguiente y ordenarlas en función del tiempo restante
	private PriorityQueue<Alarma> alarmasActivas = new PriorityQueue<Alarma>();
	//Complejidad temporal de búsqueda, insercción y eliminacion O(1)
	//Key:Id de la alarma
	private HashMap<String, Alarma> alarmasDesactivadas = new HashMap<String,Alarma>();
	private HashMap<String, Alarma> alarmasTotales = new HashMap<String,Alarma>();

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
		Alarma alarmaBuscada = alarmasTotales.get(id);
		if (alarmaBuscada == null) {
			return null;
		} else {
			return alarmaBuscada;
		}
	}

	/**
	 * Añade una nueva alarma preparada para sonar.
	 * @param alarmaAñadir
	 * @return true si se añade la alarma
	 * @return false si ya había una alarma para esa hora
	 */
	public boolean anhadeAlarma(Alarma alarmaAnhadir) {
		Date hora = alarmaAnhadir.getHora();
		if(alarmasTotales.containsValue(hora)) {
			return false;
		} else {
			alarmasTotales.put(alarmaAnhadir.getId(), alarmaAnhadir);
			alarmasActivas.add(alarmaAnhadir);
		}
		return true;
	}

	/**
	 * 
	 * @param alarmaEliminar alarma a eliminar 
	 */
	public boolean eliminaAlarma(Alarma alarmaEliminar) {
		String idAlarma = alarmaEliminar.getId();

		if (!alarmasTotales.containsKey(idAlarma)) {
			return false;
		} else if (alarmasDesactivadas.containsKey(idAlarma)) {
			alarmasDesactivadas.remove(idAlarma);
			alarmasTotales.remove(idAlarma);
			return true;
		} else {
			alarmasActivas.remove(alarmaEliminar);
			alarmasTotales.remove(idAlarma);
			return true;
		}
	}
	
	public Alarma alarmaMasProxima() {
		return alarmasActivas.peek(); 
	}
	
	public void desactivaAlarma(Alarma alarmaDesactivar) {
		alarmasActivas.remove(alarmaDesactivar);
		alarmasDesactivadas.put(alarmaDesactivar.getId(), alarmaDesactivar);
	}
	
	public void activarMelodia() {
		//Código de negocio
		//¿COMO HAGO QUE SUENE?¿JAVA SWING?
	}
	public void desactivarMelodia() {
		//Código de negocio
		//¿COMO HAGO QUE SUENE?¿JAVA SWING?
	}
	
}

