package patronState;

import java.util.Date;

public class Desprogramado extends AlarmasState{

	public void entryAction(Alarmas context) {
		//Código que le duerma hasta que se nuevaAlarma o alarmaOn
	}
	@Override
	public void alarmaOn(String id, Alarmas context) {
	}
	
	@Override
	public void nuevaAlarma(String id, Date hora, Alarmas context) {
	}

	@Override
	public void borraAlarma(String id, Alarmas context) {
	}
	
}
