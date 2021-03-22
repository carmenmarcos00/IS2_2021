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

	//Mñetod getter de la hora de una alarma
	public Date getHora() {
		return this.hora;
	}

	public int compareTo(Alarma a) {

		int retorno ;
		Date alarmaDate = a.getHora();
		Date actualDate=java.util.Calendar.getInstance().getTime();

		if (alarmaDate.compareTo(actualDate) > 0) {
			retorno = -1;
		} 
		if (alarmaDate.compareTo(actualDate) < 0) { //Si es antes la alarma que la fecha actual
			retorno = 1;
		} else {
			retorno = 0;
		}
		return retorno;

	}
}
