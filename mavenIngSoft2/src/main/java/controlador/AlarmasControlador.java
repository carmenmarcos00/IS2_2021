package controlador;

import vista.IGUI_Alarmas;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.JOptionPane;

import patronState.Alarma;
import patronState.Alarmas;
import patronState.I_Alarmas;

public class AlarmasControlador {

	private I_Alarmas alarmas;
	private IGUI_Alarmas window;

	public AlarmasControlador (I_Alarmas a, IGUI_Alarmas w) {

		alarmas = a;
		window = w;

		/**
		 * Al hacer click sbre el boton de añadir alarma se llama a la clase NuevaAlarmaListener
		 */
		window.addNuevaAlarmaListener(new NuevaAlarmaListener()); 

		window.addAlarmaOnListener(new AlarmaOnListener());

		window.addAlarmaOffListener(new AlarmaOffListener());

		window.addApagarAlarmaListener(new ApagarAlarmaListener());

		window.addBorrarAlarmaListener (new BorrarAlarmaListener());

	}

	public class NuevaAlarmaListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			//Consigo los parametros para crear la alarma
			String idAlarma = window.getIdAlarma();
			Date horaAlarma = window.getHoraAlarma();

			//En caso de que el id no haya sido introducido lo notifico
			if (idAlarma.equals("")) {
				JOptionPane.showMessageDialog(null, "El id no puede estar vacío");
			} else {
				//En caso contrario creo la alarma y notifico
				alarmas.nuevaAlarma(idAlarma, horaAlarma, (Alarmas)alarmas);
				//Anhado el id de la alarma a las listas correspondientes
				window.getModelListTotal().add(alarmas.alarmasDesactivadas().length + alarmas.alarmasDesactivadas().length , idAlarma);
				window.getModelListActivas().add(alarmas.alarmasActivas().length -1 , idAlarma);
				JOptionPane.showMessageDialog(null, "Alarma con id: "+ idAlarma+ " ha sido añadida");
			}
			//Si no hay alarmas desactivadas, deshabilito el boton de activar alarmas
			if(alarmas.alarmasDesactivadas().length == 0) {
				window.getBtnActivar().setEnabled(false);
			}
		}
	}

	public class BorrarAlarmaListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			//Consigo el id de la alarma que he seleccionado
			String selected = (String) window.getListAlarmasTotales().getSelectedValue();

			//Si no hay nada seleccionado se lo notifico al usuario
			if (selected == null) {
				JOptionPane.showMessageDialog(null, "No hay alarma seleccionada para borrar");
				//En caso de que haya un id seleccionado lo borro y lo elimino de las listas
				
			} else {
				int selectedIndex = window.getListAlarmasTotales().getSelectedIndex();//consigo el indice del id seleccionado
				if (selectedIndex != -1) {
					window.getModelListTotal().remove(selectedIndex); //Borro este elemento de la lista total
					//Tengo que ver si esa alarma estaba en programado o en desprogramado para borrar de la otra lista
					for (int i=0; i<=alarmas.alarmasActivas().length; i++) {
						System.out.println(i);
						System.out.println(alarmas.alarmasActivas()[i].getId());
						System.out.println(alarmas.alarma(selected).getId());
						if (alarmas.alarmasActivas()[i].getId().equals(alarmas.alarma(selected).getId())) { //La alarma esta en la lista de alarmas activas
							System.out.println("Está en activas");
							int selectedIndexSec = window.getListAlarmasActivas().getSelectedIndex();
							if (selectedIndex != -1) {
								window.getModelListActivas().remove(selectedIndexSec);
							}
						} else {
							System.out.println("Está en desactivadas");
							int selectedIndexSecD = window.getListAlarmasDesactivadas().getSelectedIndex();
							if (selectedIndex != -1) {
								window.getModelListDesactivadas().remove(selectedIndexSecD);
							}

						}
					}
				}
				alarmas.borraAlarma(selected, (Alarmas)alarmas); //Borro la alarma
			}


			// TODO Auto-generated method stub	
		}
	}

	public class AlarmaOnListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub	
		}
	}

	public class AlarmaOffListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub	
		}
	}

	public class ApagarAlarmaListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			alarmas.apagar((Alarmas) alarmas);
			// TODO Auto-generated method stub	
		}
	}
}
