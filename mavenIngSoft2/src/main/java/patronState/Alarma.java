package patronState;
import java.util.Date;

public class Alarma {
	
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
}
