package patronState;

import java.util.Date;

public interface I_Alarmas {

	public void nuevaAlarma(String id, Date hora, Alarmas alarmas);

	public void borraAlarma(String id, Alarmas context);

	public void apagar(Alarmas context);

	public void alarmaOff(String id, Alarmas context);

	public void alarmaOn(String id, Alarmas context);

	public Alarma[] alarmasActivas ();

	public Alarma[] alarmasDesactivadas();

	public Alarma alarma(String id);


}
