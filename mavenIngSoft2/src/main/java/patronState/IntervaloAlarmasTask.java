package patronState;

import java.util.TimerTask;

public class IntervaloAlarmasTask extends TimerTask {
	
	//Atributos privados de la clase AlarmasTask
	private Alarmas context;
	
	//Constructor de la clase AlarmasTask
	public IntervaloAlarmasTask(Alarmas alarmas) {
		context = alarmas;
	}

	//Método run heredado de la clase java.util.TimerTask
	@Override
	public void run() {
		context.apagar();
	}
}
