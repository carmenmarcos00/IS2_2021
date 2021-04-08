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
	private int indexModelTotal = 0;
	private int indexModelActivas =0;
	private int indexModelDesactivadas = 0;

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
				alarmas.nuevaAlarma(idAlarma, horaAlarma);
				//Anhado el id de la alarma a las listas correspondientes
				window.getModelListTotal().add(indexModelTotal , idAlarma);
				window.getModelListActivas().add(indexModelActivas  , idAlarma);
				indexModelActivas++;
				indexModelTotal++;

				JOptionPane.showMessageDialog(null, "Alarma con id: "+ idAlarma+ " ha sido añadida");
			}
			//Si no hay alarmas desactivadas, deshabilito el boton de activar alarmas
			if(indexModelDesactivadas == 0) {
				window.getBtnActivar().setEnabled(false);
			}
		}
	}

	public class BorrarAlarmaListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			boolean bool = false;
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
					indexModelTotal--;

					for (int i = 0; i <alarmas.alarmasActivas().length; i++) {
						String idAlarmaIt = alarmas.alarmasActivas()[i].getId();

						if (idAlarmaIt == selected) {
							bool = true;
						}
					}

					if (bool == true) { //Era una alarma activa
						window.getModelListActivas().removeElement(selected);
						indexModelActivas--;
						JOptionPane.showMessageDialog(null, "Borrada de la lista de activas y de la lista de totales");
						//Si al eliminarla no quedan más en activas, bloqueo el boton de desactivar
						System.out.println(alarmas.alarmasActivas().length);
						if (indexModelActivas == 0) {
							window.getBtnDesactivar().setEnabled(false);
						}
					} else { //Era una alarma desactivada
						window.getModelListDesactivadas().removeElement(selected);
						indexModelDesactivadas--;
						JOptionPane.showMessageDialog(null, "Borrada de la lista de desactivadas y de la lista de totales");
						//Si al eliminarla no quedan más es desactivadas, bloqueo el boton de activar
						//Si no hay alarmas desactivadas, deshabilito el boton de activar alarmas
						if(indexModelDesactivadas == 0) {
							window.getBtnActivar().setEnabled(false);
						}
					}
				}
			}
			//Si no hay ninguna alarma deshabilito la opcion de borrar alarmas
			if (indexModelTotal == 0) {
				window.getBtnBorrar().setEnabled(false);
			}
			alarmas.borraAlarma(selected); //Borro la alarma
		}
	}

	public class AlarmaOffListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub	
		}
	}


	public class AlarmaOnListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub	
		}
	}


	public class ApagarAlarmaListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			alarmas.apagar();
			// TODO Auto-generated method stub	
		}
	}
}
