package patronState;
import java.util.Date;

/**
 * Clase Alarma que define los atributos, m�todos y el comportamiento de una alarma
 * @author Carmen Marcos S�nchez de la Blanca
 * @version 13/04/2021
 */
public class Alarma implements Comparable<Alarma>{

	//Atributos privados de la clase Alarma
	private String id;
	private Date hora;

	/**
	 * Constructor de la clase alarma
	 * @param id id de la alarma a crear
	 * @param hora Fecha y hora del d�a actual en el que sonar� la alarma
	 */
	public Alarma (String id, Date hora) {
		this.id = id;
		this.hora = hora; 
	}

	/**
	 * Getter del atributo id de la alarma
	 * @return this.id id de la alarma 
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * Getter del atributo hora de la alarma
	 * @return this.hora fecha de la alarma
	 */
	public Date getHora() {
		return this.hora;
	}

	/**
	 * M�todo compareTo necesario para ordenar las alarmas por hora en la priority queue
	 */
	public int compareTo(Alarma a) {
		return getHora().compareTo(a.getHora());
	}

	/**
	 * M�todo redefinido equals para el id de la alarma
	 */
	@Override
	public boolean equals (Object a) {
		return id.equals(((Alarma)a).id);
	}

	/**
	 * M�todo redefinido toString para mostrar la alarma por pantalla
	 */
	@Override
	public String toString() {
		return "Alarma [id=" + id + ", hora=" + "]";
	}
}