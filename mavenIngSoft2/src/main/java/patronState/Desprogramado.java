package patronState;

import java.util.Date;

public class Desprogramado extends AlarmasState{



	@Override
	public void alarmaOn(String id, Alarmas context) {
		
		this.exitAction(context);
		context.setState(getEstadoProgramado());
		//Acciones de transicion
		
		//Problemas al no tener definido los do, entry y exit en clase AlarmaState
		//No override
		
		//Además, tengo getters de los estados, no es mejor protected de los estados??
		//En vez de private
		
		//getEstadoProgramado()
	}

	@Override
	public void nuevaAlarma(String id, Date hora, Alarmas context) {

	}

	@Override
	public void borraAlarma(String id, Alarmas context) {

	}


	public void entryAction(Alarmas context) {
	}

	public void doAction(Alarmas context) {
	}

	public void exitAction(Alarmas context) {
	}

}
