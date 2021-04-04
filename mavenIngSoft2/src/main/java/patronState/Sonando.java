package patronState;



public class Sonando extends AlarmasState {

	@Override
	public void apagar(String id, Alarmas context) {
		Programado programado =  (Programado) getEstadoProgramado();
		this.exitAction(id, context);
		context.setState(programado);
		//No hay acciones asociadas a la transicion
		programado.entryAction(context);
		programado.doAction(context);
	}

	public void entryAction(Alarmas context) {
		context.activarMelodia();
	}

	public void exitAction(String id, Alarmas context) {
		context.desactivarMelodia();
		Alarma alarma = context.alarma(id);
		context.eliminaAlarma(alarma);
	}

	public void doAction(Alarmas context) {
		//No tiene implementacion
		//Definicion para posible futura implementacion
		//Mejora modularidad y comprension del codigo
	}


}
