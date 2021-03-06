package patronState;

import java.util.Date;
import java.util.Timer;

/**
 * Clase que implementa los estados y transiciones asociadas al estado Programado, que hereda de AlarmasState
 * @author Carmen Marcos S?nchez de la Blanca
 * @version 13/04/2021
 */
public class Programado extends AlarmasState {
	private Alarma alarmaMasProxima = null;
	protected Timer timer = new Timer();
	protected AlarmasTask alarmasTask;


	/**
	 * Redefinici?n del m?todo alarmaOn de la clase AlarmasState para este estado
	 */
	@Override
	public void alarmaOn(String id, Alarmas context) {
		Programado programado =  (Programado) getEstadoProgramado();

		this.exitAction(context);
		context.setState(programado);
		//Inicio acciones asociadas a la transicion
		Alarma alarmaActivar = context.alarma(id);
		context.activaAlarma(alarmaActivar);
		//Fin acciones asociadas a la transicion

		programado.entryAction(context);//Cuando la activo, puede ser que sea la siguiente en sonar y tengo que reajustar el timer
		programado.doAction(context);

	}

	/**
	 * Redefinici?n del m?todo alarmaOff de la clase AlarmasState para este estado
	 */
	@Override
	public void alarmaOff(String id, Alarmas context) {

		this.exitAction(context);
		Alarma alarmaDesactivar = context.alarma(id);
		//Elijo estado destino
		if(context.alarmasActivas().length > 1) { //Programado (>1 porque elimino dentro)
			Programado programado =  (Programado) getEstadoProgramado();
			context.setState(programado);
			context.desactivaAlarma(alarmaDesactivar);
			programado.entryAction(context);
			programado.doAction(context);

		} else { //Desprogramado
			Desprogramado desprogramado =  (Desprogramado) getEstadoDesprogramado();
			context.setState(desprogramado);
			context.desactivaAlarma(alarmaDesactivar);//Llamo a m?todo de negocio asociado (transicion)

			if(alarmaMasProxima.equals(alarmaDesactivar)) { //Si alarma eliminada ten?a el timer, reprogramo
				alarmasTask.cancel();
				alarmaMasProxima = context.alarmaMasProxima();//Actualizo alarma m?s proxima (siempre es null)
			}
			desprogramado.entryAction(context);
			desprogramado.doAction(context);
		}
	}

	/**
	 * Redefinici?n del m?todo nuevaAlarma de la clase AlarmasState para este estado
	 */
	@Override
	public void nuevaAlarma(String id, Date hora, Alarmas context) {

		this.exitAction(context);
		context.anhadeAlarma( new Alarma (id, hora)); //Llamo a m?todo de negocio asociado (transicion)

		//Elijo estado destino
		if (context.alarmasActivas().length == 0) { 
			Desprogramado desprogramado = (Desprogramado) getEstadoDesprogramado();
			context.setState(desprogramado);
			desprogramado.entryAction(context);
			desprogramado.doAction(context);

		} else {
			Programado programado = (Programado) getEstadoProgramado();
			context.setState(programado);
			programado.entryAction(context);
			programado.doAction(context);
		}
	}

	/**
	 * Redefinici?n del m?todo borraAlarma de la clase AlarmasState para este estado
	 */
	@Override
	public void borraAlarma(String id, Alarmas context) {

		this.exitAction(context);
		Alarma alarmaBorrar = context.alarma(id);

		//Elijo estado destino
		if(context.alarmasActivas().length == 0) {  //Estado desprogramado

			Desprogramado desprogramado =  (Desprogramado) getEstadoDesprogramado();
			context.setState(desprogramado);
			context.eliminaAlarma(alarmaBorrar); //Llamo a m?todo de negocio asociado (transicion)
			desprogramado.entryAction(context);
			desprogramado.doAction(context);

		} else { //Estado programado

			Programado programado = (Programado) getEstadoProgramado();
			context.setState(programado);
			context.eliminaAlarma(alarmaBorrar);//Llamo a m?todo de negocio asociado (transicion)
			programado.entryAction(context);
			programado.doAction(context);
		}
	}

	/**
	 * M?todo con las acciones que hay que realizar siempre que se entre al estado Programado
	 * @param context objeto de la clase Alarmas
	 */
	public void entryAction(Alarmas context) { //Compruebo que el timer le tenga la alarma masProxima
		//Compruebo si ha cambiado la alarma m?s proxima

		if(context.alarmaMasProxima() == null) {//Si viene de estado sonando
			alarmaMasProxima = null;
		}else {
			if (alarmaMasProxima != context.alarmaMasProxima()) {//Se ha cambiado y reprogramo
				if (alarmasTask != null) { //Cancelo el evento temporizado
					alarmasTask.cancel();
				}
				alarmaMasProxima = context.alarmaMasProxima();//Actualizo 
				alarmasTask = new AlarmasTask(context); //Creo nuevo evento 
				timer.schedule(alarmasTask, alarmaMasProxima.getHora()); //Programo timer
			} 
		}
	}

	/**
	 * M?todo con las acciones que hay que realizar de manera reiterada mientras se este en el estado Programado
	 * @param context objeto de la clase Alarmas
	 */
	public void doAction(Alarmas context) {
		//No tiene implementacion
		//Definicion para posible futura implementacion
		//Mejora modularidad y comprension del codigo
	}

	/**
	 * M?todo con las acciones que hay que realizar al abandonar el estado Programado
	 * @param context objeto de la clase Alarmas
	 */
	public void exitAction(Alarmas context) {
		//No tiene implementacion
		//Definicion para posible futura implementacion
		//Mejora modularidad y comprension del codigo
	}
}



