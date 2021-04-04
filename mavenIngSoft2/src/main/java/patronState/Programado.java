package patronState;

import java.util.Date;

public class Programado extends AlarmasState {

	@Override
	public void alarmaOn(String id, Alarmas context) {

		this.exitAction(context);
		context.setState(this);
		//Inicio acciones asociadas a la transicion
		Alarma alarmaActivar = context.alarma(id);
		context.activaAlarma(alarmaActivar);
		//Fin acciones asociadas a la transicion
		this.entryAction(context);
		this.doAction(context);

	}

	@Override
	public void alarmaOff(String id, Alarmas context) {

		this.exitAction(context);
		Alarma alarmaDesactivar = context.alarma(id);
		//Dependiendo de si quedan alarmas activas o no, cambio de estado o me quedo
		if(context.alarmasActivas().length > 1) { //Me quedo en estado programado porque quedan alarmas activas

			context.setState(this);
			//Inicio acciones asociadas a la transicion
			context.desactivaAlarma(alarmaDesactivar);
			//Fin acciones asociadas a la transicion
			this.entryAction(context);
			this.doAction(context);

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

		this.exitAction(context);
		context.setState(this);
		//Inicio acciones asociadas a la transicion
		Alarma nuevaAlarma = new Alarma (id, hora); //Creo la nueva alarma
		context.anhadeAlarma(nuevaAlarma);//Llamo a método de negocio asociado
		//Fin acciones asociadas a la transicion
		this.entryAction(context);
		this.doAction(context);
	}

	@Override
	public void borraAlarma(String id, Alarmas context) {

		this.exitAction(context);
		Alarma alarmaBorrar = context.alarma(id);
		//Dependiendo de si quedan alarmas activas o no, cambio de estado o me quedo
		if(context.alarmasActivas().length > 1) { //Me quedo en estado programado porque quedan alarmas activas
			context.setState(this);
			//Inicio acciones asociadas a la transicion
			context.eliminaAlarma(alarmaBorrar);
			//Fin acciones asociadas a la transicion
			this.entryAction(context);
			this.doAction(context);

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
		//No tiene implementacion
		//Definicion para posible futura implementacion
		//Mejora modularidad y comprension del codigo
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
