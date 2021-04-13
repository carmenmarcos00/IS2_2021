package patronState;

import java.util.Timer;

public class Sonando extends AlarmasState {
	
	//Timer para el intervalo de tiempo
	protected Timer timerIntervalo = new Timer();
	protected IntervaloAlarmasTask alarmasIntervaloTask;


	@Override
	public void apagar( Alarmas context) {
		
		Programado programado =  (Programado) getEstadoProgramado();
		this.exitAction(context);
		
		//Inicio de acciones asociadas a la transicion
		alarmasIntervaloTask.cancel();
		//Fin de acciones asociadas a la transicion
		
		context.setState(programado);
		context.eliminaAlarma(context.alarmaMasProxima());
		programado.entryAction(context);
		programado.doAction(context);
		
	}

	public void entryAction(Alarmas context) {
		alarmasIntervaloTask= new IntervaloAlarmasTask(context);
		timerIntervalo.schedule(alarmasIntervaloTask, context.getIntervalo());
		context.activarMelodia();
	}

	public void exitAction(Alarmas context) {
		//Hacer que cuando expire el timer se elimine tambien de la lista
		//y botones se restablezcan
		context.desactivarMelodia();

	}

	public void doAction(Alarmas context) {
	}
}
