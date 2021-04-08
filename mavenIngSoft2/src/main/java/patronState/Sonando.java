package patronState;



public class Sonando extends AlarmasState {

	@Override
	public void apagar( Alarmas context) {
		Programado programado =  (Programado) getEstadoProgramado();
		this.exitAction(context);
		context.setState(programado);

		//Inicio de acciones asociadas a la transicion
		Alarma alarma = context.alarmaMasProxima();
		context.eliminaAlarma(alarma);
		context.desactivarMelodia();
		//Fin de acciones asociadas a la transicion
		
		programado.entryAction(context);
		programado.doAction(context);
	}

	public void entryAction(Alarmas context) {
		context.activarMelodia();
	}

	public void exitAction(Alarmas context) {
		


	}

	public void doAction(Alarmas context) {
		//No tiene implementacion
		//Definicion para posible futura implementacion
		//Mejora modularidad y comprension del codigo
	}


}
