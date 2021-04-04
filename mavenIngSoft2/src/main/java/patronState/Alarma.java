package patronState;
import java.util.Date;

public class Alarma implements Comparable<Alarma>{

	//Atributos privados de la clase Alarma
	private String id;
	private Date hora;

	//Constructor de la clase alarma
	public Alarma (String id, Date hora) {
		this.id = id;
		this.hora = hora; 
	}

	//Método getter del id de una alarma
	public String getId() {
		return this.id;
	}

	//Método getter de la hora de una alarma
	public Date getHora() {
		return this.hora;
	}

	public int compareTo(Alarma a) {
		Date alarmaDate = a.getHora();
		return this.getHora().compareTo(alarmaDate);

	}
}

// Crear paquete con clase protegida  tR

