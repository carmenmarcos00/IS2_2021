package patronState;

import java.util.Date;

public abstract class AlarmasState {
	
	private static Sonando estadoSonando = new Sonando();
	private static Programado estadoProgramado = new Programado();
	private static Desprogramado estadoDesprogramado = new Desprogramado();
	
	public static AlarmasState init(Alarmas context) {
		estadoDesprogramado.entryAction(context);
		return estadoDesprogramado;
	}
	
	public void nuevaAlarma(String id, Date hora, Alarmas context) {};
	public void borraAlarma(String id, Alarmas context) {};
	public void apagar(String id, Alarmas context) {};
	public void alarmaOff(String id, Alarmas context) {};
	public void alarmaOn(String id, Alarmas context) {};
	
	public static AlarmasState getEstadoSonando() {
		return estadoSonando;
	};
	public static AlarmasState getEstadoProgramado() {
		return estadoProgramado;
	};
	public static AlarmasState getEstadoDesprogramado() {
		return estadoDesprogramado;
	}
}
