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

			//Gestión de errores en caso de que ya exita una alarma con ese id
			if (alarmas.alarma(idAlarma)!= null) {
				JOptionPane.showMessageDialog(null, "Alarma con id ya existente");
				//Gestión de errores en caso de que el id no haya sido introducido lo notifico
			} else if (idAlarma.equals("")) {
				JOptionPane.showMessageDialog(null, "El id no puede estar vacío");
				//Caso normal: creo la alarma y notifico al usuario
			} else {

				alarmas.nuevaAlarma(idAlarma, horaAlarma);
				//Anhado el id de la alarma a las listas correspondientes
				window.getModelListTotal().add(indexModelTotal , idAlarma);
				window.getModelListActivas().add(indexModelActivas  , idAlarma);
				//Aumento el contador de alarmas por lista
				indexModelActivas++;
				indexModelTotal++;
				if(indexModelDesactivadas == 0) {
					window.getBtnActivar().setEnabled(false);
				//Notifico al usuario de que se ha añadido la alarma correctamente
				JOptionPane.showMessageDialog(null, "Alarma con id: "+ idAlarma+ " ha sido añadida correctamente");
			}

			//Finalmente, si no hay alarmas desactivadas, deshabilito el boton de activar alarmas

			}
		}
	}


	public class BorrarAlarmaListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			//Declaro variable booleana auxiliar
			boolean bool = false;
			//Consigo el id de la alarma que he seleccionado
			String selected = (String) window.getListAlarmasTotales().getSelectedValue();

			//Gestión de errores: si no hay nada seleccionado se lo notifico al usuario
			if (selected == null) {
				JOptionPane.showMessageDialog(null, "No hay alarma seleccionada para borrar");

				//Caso normal: hay un id seleccionado, lo borro, elimino de las listas correspondientes y notifico al usuario
			} else {

				int selectedIndex = window.getListAlarmasTotales().getSelectedIndex();//consigo el indice del id seleccionado
				if (selectedIndex != -1) { //Si el indice es correcto
					window.getModelListTotal().remove(selectedIndex); //Borro este elemento de la lista total
					indexModelTotal--; //Decremento el contador de elementos de la lista total

					//Compruebo si la alarma estaba en activas para eliminarla tambien de esa lista
					for (int i = 0; i <alarmas.alarmasActivas().length; i++) {
						String idAlarmaIt = alarmas.alarmasActivas()[i].getId();
						if (idAlarmaIt == selected) { //Si está en activas pongo la variable bool auxiliar a true
							bool = true;
						}
					}
					//Era una alarma activa y la elimino de la lista, decremento el contador de elementos de dicha lista y notifico
					if (bool == true) { 
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
				//Si no hay ninguna alarma deshabilito la opcion de borrar alarmas
				if (indexModelTotal == 0) {
					window.getBtnBorrar().setEnabled(false);
				}
				alarmas.borraAlarma(selected); //Borro la alarma
			}

		}
	}

	public class AlarmaOffListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			//Consigo el id de la alarma que he seleccionado
			String selected = (String) window.getListAlarmasActivas().getSelectedValue();

			//Gestión de errores: si no hay nada seleccionado se lo notifico al usuario
			if (selected == null) {
				JOptionPane.showMessageDialog(null, "No hay alarma seleccionada para desactivar");

				//Caso normal: hay un id seleccionado, lo desactivo, elimino de la lista de activos, añado a desactivados y notifico al usuario
			} else {
				window.getModelListActivas().removeElement(selected);
				indexModelActivas--;
				window.getModelListDesactivadas().add(indexModelDesactivadas, selected);
				indexModelDesactivadas++;

				//La desactivo a efectos prácticos
				alarmas.alarmaOff(selected);
				//Activo el boton de activar, ya que al menos hay 1 alarma activa
				window.getBtnActivar().setEnabled(true);
				JOptionPane.showMessageDialog(null, "Alarma Desactivada correctamente");
				//Compruebo si hay alguna alarma para desactivar, si no disable boton desactivar
				if (indexModelActivas == 0) {
					window.getBtnDesactivar().setEnabled(false);
				}
			}
		}
	}

	public class AlarmaOnListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			//Consigo el id de la alarma que he seleccionado
			String selected = (String) window.getListAlarmasDesactivadas().getSelectedValue();
			//Gestión de errores: si no hay nada seleccionado se lo notifico al usuario
			if (selected == null) {
				JOptionPane.showMessageDialog(null, "No hay alarma seleccionada para desactivar");

				//Caso normal: hay un id seleccionado, lo activo, elimino de la lista de desactivadas, añado a activas y notifico al usuario
			} else {
				window.getModelListDesactivadas().removeElement(selected);
				indexModelDesactivadas--;
				window.getModelListActivas().add(indexModelActivas, selected);
				indexModelActivas++;

				//La activo a efectos prácticos
				alarmas.alarmaOn(selected);
				//Activo el boton de desactivar
				window.getBtnDesactivar().setEnabled(true);
				JOptionPane.showMessageDialog(null, "Alarma Activada correctamente");
				//Compruebo si hay alguna alarma para activar, si no disable boton activar
				if (indexModelDesactivadas == 0) {
					window.getBtnActivar().setEnabled(false);
				}
			}
		}
	}


	public class ApagarAlarmaListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {


			Alarma alarma= alarmas.alarmaMasProxima(); //CORRECTO NO DA FALLO ESTE ALARMA MÁS PROXIMA

			if (alarma == null) {
				JOptionPane.showMessageDialog(null, "No hay alarmas que apagar");
			} else {

				window.getModelListActivas().removeElement(alarma.getId());
				indexModelActivas--;
				window.getModelListTotal().removeElement(alarma.getId());
				indexModelTotal--;

				alarmas.apagar(); //ESTA LLAMADA DA PROBLEMAS
				JOptionPane.showMessageDialog(null, "La alarma que estaba sonando ya está apagada");

				if (indexModelActivas == 0) {
					window.getBtnActivar().setEnabled(false);
				}

				if (indexModelTotal == 0) {
					window.getBtnActivar().setEnabled(false);
				}
			}
		}
	}
}
