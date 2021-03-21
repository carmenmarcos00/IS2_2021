package patronState;

import java.util.Date;

public class Alarmas {
	
	private AlarmasState state;
	
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
	public void apagar(Alarmas context) {
		state.apagar(this);
	}
	public void alarmaOff(String id, Alarmas context) {
		state.alarmaOff(id, this);
	}
	public void alarmaOn(String id, Alarmas context) {
		state.alarmaOn(id, this);
	}
	
	//Implementacion de los m�todo de negocio (REVISAR DATO QUE DEVUELVE TODOS VOID ESQUELETO)
	public void alarma(String id) {
		//C�digo de negocio
	}
	public void anhadeAlarma(Alarma a) {
		//C�digo de negocio
	}
	public void eliminaAlarma(Alarma a) {
		//C�digo de negocio
	}
	public void alarmaMasProxima() {
		//C�digo de negocio 
	}
	public void desactivaAlarma(Alarma a) {
		//C�digo de negocio
	}
	public void activarMelodia() {
		//C�digo de negocio
	}
	public void desactivarMelodia() {
		//C�digo de negocio
	}
}

