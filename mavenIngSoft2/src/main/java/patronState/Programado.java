package patronState;

import java.util.Date;

public class Programado extends AlarmasState {

	@Override
	public void alarmaOn(String id, Alarmas context) {
	}
	
	@Override
	public void alarmaOff(String id, Alarmas context) {
	}
	
	@Override
	public void nuevaAlarma(String id, Date hora, Alarmas context) {
	}
	
	@Override
	public void borraAlarma(String id, Alarmas context) {
	}
	
	public void entryAction(Alarmas context) {
		//Código que le duerma hasta que se nuevaAlarma o alarmaOn
	}

	public void doAction(Alarmas context) {
	}

	public void exitAction(Alarmas context) {
	}
}
