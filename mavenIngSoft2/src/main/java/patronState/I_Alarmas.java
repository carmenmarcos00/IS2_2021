package patronState;

import java.util.Date;

public interface I_Alarmas {

	public void nuevaAlarma(String id, Date hora);

	public void borraAlarma(String id);

	public void apagar();

	public void alarmaOff(String id);

	public void alarmaOn(String id);

	public Alarma[] alarmasActivas ();

	public Alarma[] alarmasDesactivadas();

	public Alarma alarma(String id);


}
