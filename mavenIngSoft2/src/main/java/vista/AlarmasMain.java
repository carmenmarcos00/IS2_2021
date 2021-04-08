package vista;

import controlador.AlarmasControlador;
import patronState.*;

public class AlarmasMain {
	public static void main(String[] args) {
		IGUI_Alarmas window = new GUI_Alarmas();
		I_Alarmas alarmas = new Alarmas();
		AlarmasControlador controlador = new AlarmasControlador (alarmas, window);
		window.setVisible();
		
	}

}
