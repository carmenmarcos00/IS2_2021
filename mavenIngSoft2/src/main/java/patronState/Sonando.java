package patronState;



public class Sonando extends AlarmasState {

	@Override
	public void apagar( Alarmas context) {
		Programado programado =  (Programado) getEstadoProgramado();
		this.exitAction(context);
		context.setState(programado);
		//Inicio acciones asociadas a la transicion
		context.apagar();
		//Fin acciones asociadas a la transicion
		programado.entryAction(context);
		programado.doAction(context);
	}

	public void entryAction(Alarmas context) {
		context.activarMelodia();
	}

	public void exitAction(Alarmas context) {
		context.desactivarMelodia();
		//context.eliminaAlarma(alarma);
		//APAGAR NECESITARIA TENER EL ID DE LA ALARMA
	}

	public void doAction(Alarmas context) {
		//No tiene implementacion
		//Definicion para posible futura implementacion
		//Mejora modularidad y comprension del codigo
	}


}
