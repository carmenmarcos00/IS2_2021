package vista;

import controlador.AlarmasControlador;
import patronState.I_Alarmas;
import patronState.Alarmas;

/**
 * Clase main que lanza la aplicacion en el MVC
 * @author Carmen Marcos Sánchez de la Blanca
 * @version 13/04/2021
 */
public class AlarmasMain {
	
	public static void main(String[] args) {
		I_Alarmas alarmas = new Alarmas(); //Creo un objeto del modelo
		IGUI_Alarmas window = new GUI_Alarmas(alarmas); //Creo un objeto de la vista
		AlarmasControlador controlador = new AlarmasControlador (alarmas, window); //Creo un objeto del controlador
		window.setVisible();//Hago visible la ventana mediante el método setVisible
	}
}
