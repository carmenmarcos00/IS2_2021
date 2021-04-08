package patronState;

import java.util.Date;
import java.util.Timer;


public class Programado extends AlarmasState {
	private Alarma alarmaMasProxima;
	protected Timer timer = new Timer();
	protected AlarmasTask alarmasTask;

	@Override
	public void alarmaOn(String id, Alarmas context) {
		Programado programado =  (Programado) getEstadoProgramado();

		this.exitAction(context);
		context.setState(programado);
		//Inicio acciones asociadas a la transicion
		Alarma alarmaActivar = context.alarma(id);
		context.activaAlarma(alarmaActivar);
		//Fin acciones asociadas a la transicion
		programado.entryAction(context);
		programado.doAction(context);

	}

	@Override
	public void alarmaOff(String id, Alarmas context) {

		this.exitAction(context);
		Alarma alarmaDesactivar = context.alarma(id);
		//Dependiendo de si quedan alarmas activas o no, cambio de estado o me quedo
		if(context.alarmasActivas().length > 1) { //Me quedo en estado programado porque quedan alarmas activas
			Programado programado =  (Programado) getEstadoProgramado();
			context.setState(programado);
			//Inicio acciones asociadas a la transicion
			context.desactivaAlarma(alarmaDesactivar);
			//Fin acciones asociadas a la transicion
			programado.entryAction(context);
			programado.doAction(context);

		} else {

			Desprogramado desprogramado =  (Desprogramado) getEstadoDesprogramado();
			context.setState(desprogramado);
			//Inicio acciones asociadas a la transicion
			context.desactivaAlarma(alarmaDesactivar);
			//Fin acciones asociadas a la transicion
			desprogramado.entryAction(context);
			desprogramado.doAction(context);

		}
	}

	@Override
	public void nuevaAlarma(String id, Date hora, Alarmas context) {
		Programado programado = (Programado) getEstadoProgramado();

		this.exitAction(context);
		context.setState(programado);
		//Inicio acciones asociadas a la transicion
		Alarma nuevaAlarma = new Alarma (id, hora); //Creo la nueva alarma
		context.anhadeAlarma(nuevaAlarma);//Llamo a método de negocio asociado
		//Fin acciones asociadas a la transicion
		programado.entryAction(context);
		programado.doAction(context);
	}

	@Override
	public void borraAlarma(String id, Alarmas context) {

		this.exitAction(context);
		Alarma alarmaBorrar = context.alarma(id);
		//Dependiendo de si quedan alarmas activas o no, cambio de estado o me quedo
		if(context.alarmasActivas().length > 1) { //Me quedo en estado programado porque quedan alarmas activas
			Programado programado = (Programado) getEstadoProgramado();
			context.setState(programado);
			//Inicio acciones asociadas a la transicion
			context.eliminaAlarma(alarmaBorrar);
			//Fin acciones asociadas a la transicion
			programado.entryAction(context);
			programado.doAction(context);

		} else {
			Desprogramado desprogramado =  (Desprogramado) getEstadoDesprogramado();
			context.setState(desprogramado);
			//Inicio acciones asociadas a la transicion
			context.eliminaAlarma(alarmaBorrar);
			//Fin acciones asociadas a la transicion
			desprogramado.entryAction(context);
			desprogramado.doAction(context);
		}
	}

	public void entryAction(Alarmas context) {

		//Si la he programado una nueva alarma y es antes que la que estaba con el timer, 
		//elimino el timer para la anterior y creo otro timer para la mas proxima
		if (alarmaMasProxima != context.alarmaMasProxima()) {
			//Cancelo el evento temporizado
			if (alarmasTask != null) {
				alarmasTask.cancel();
			}
			//Actualizo alarma mas proxima
			alarmaMasProxima = context.alarmaMasProxima();
			//Creo evento temporizado para la que acabo de poner
			alarmasTask = new AlarmasTask(context);
			timer.schedule(alarmasTask, alarmaMasProxima.getHora());

			//En caso contrario no hago nada
		} else {
		}
	}

	public void doAction(Alarmas context) {
		//No tiene implementacion
		//Definicion para posible futura implementacion
		//Mejora modularidad y comprension del codigo
	}

	public void exitAction(Alarmas context) {
		//No tiene implementacion
		//Definicion para posible futura implementacion
		//Mejora modularidad y comprension del codigo
	}
}



