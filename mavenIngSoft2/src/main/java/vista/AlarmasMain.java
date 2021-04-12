package vista;

import controlador.AlarmasControlador;
import patronState.I_Alarmas;
import patronState.Alarmas;

public class AlarmasMain {
	public static void main(String[] args) {
		IGUI_Alarmas window = new GUI_Alarmas(); //Creo un objeto de la vista
		I_Alarmas alarmas = new Alarmas(); //Creo un objeto del modelo
		
		AlarmasControlador controlador = new AlarmasControlador (alarmas, window); //Creo un objeto del controlador
		window.setVisible();//Hago visible la ventana mediante el método setVisible
	}

}
