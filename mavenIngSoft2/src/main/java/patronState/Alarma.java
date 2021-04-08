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

	//No me deja Override
	public int compareTo(Alarma a) {
		return getHora().compareTo(a.getHora());
		//return this....?
	}

	@Override
	public boolean equals (Object a) {
		return id.equals(((Alarma)a).id);
	}

	@Override
	public String toString() {
		return "Alarma [id=" + id + ", hora=" + "]";
	}

}


