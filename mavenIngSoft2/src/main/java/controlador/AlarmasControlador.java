package controlador;

import vista.IGUI_Alarmas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JOptionPane;

import patronState.Alarma;
import patronState.I_Alarmas;

public class AlarmasControlador {

	//Variables privadas de la clase
	private I_Alarmas alarmas;
	private IGUI_Alarmas window;
	private int indexModelTotal = 0;
	private int indexModelActivas =0;
	private int indexModelDesactivadas = 0;

	//Constructor de la clase AlarmasControlador
	public AlarmasControlador (I_Alarmas a, IGUI_Alarmas w) {

		//Inicializo las variables de la vista y el modelo
		alarmas = a;
		window = w;

		/**
		 * Al hacer click sobre el boton de añadir alarma se llama a la clase NuevaAlarmaListener
		 */
		window.addNuevaAlarmaListener(new NuevaAlarmaListener()); 

		window.addAlarmaOnListener(new AlarmaOnListener());

		window.addAlarmaOffListener(new AlarmaOffListener());

		window.addApagarAlarmaListener(new ApagarAlarmaListener());

		window.addBorrarAlarmaListener (new BorrarAlarmaListener());
	}


	//Clase listener de crear alarma CORRECTO (TODO COMPROBAR MISMA HORA DE ALARMAS)
	public class NuevaAlarmaListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			//Consigo los parametros para crear la alarma
			String idAlarma = window.getIdAlarma();
			Date horaAlarma = window.getHoraAlarma();

			//Si la hora ya ha pasado lo notifico
			if (horaAlarma.before(new Date())) {
				JOptionPane.showMessageDialog(null, "No se puede añadir, esa hora ya ha pasado");

				//Gestión de errores en caso de que ya exita una alarma con ese id
			} else if (alarmas.alarma(idAlarma)!= null) {			
				JOptionPane.showMessageDialog(null, "Alarma con id ya existente");

				//Gestión de errores en caso de que el id no haya sido introducido lo notifico
			} else if (idAlarma.equals("")) {
				JOptionPane.showMessageDialog(null, "El id no puede estar vacío");

				//Caso normal: creo la alarma y notifico al usuario
			} else {

				alarmas.nuevaAlarma(idAlarma, horaAlarma); //Creo la alarma
				//Anhado el id de la alarma a las listas correspondientes
				window.getModelListTotal().add(indexModelTotal , idAlarma);
				window.getModelListActivas().add(indexModelActivas  , idAlarma);
				//Aumento el contador de alarmas por lista
				indexModelActivas++;
				indexModelTotal++;

				//Si no hay alarmas para activar desactivo el boton de activar
				if(indexModelDesactivadas == 0) {
					window.getBtnActivar().setEnabled(false);
				}
				if (indexModelActivas  != 0) {
					window.getBtnBorrar().setEnabled(true);
					window.getBtnDesactivar().setEnabled(true);
				}
				//Notifico al usuario de que se ha añadido la alarma correctamente
				JOptionPane.showMessageDialog(null, "Alarma con id: "+ idAlarma+ " ha sido añadida correctamente");
			}
		}
	}

	//Clase listener de borrar alarma REVISADO
	public class BorrarAlarmaListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			//Declaro variable booleana (auxiliar para ver en que lista está la alarma a borrar)
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
						if (indexModelActivas == 0) {
							window.getBtnDesactivar().setEnabled(false);
						} else {
							window.getBtnDesactivar().setEnabled(true);
						}
					} else { //Era una alarma desactivada
						window.getModelListDesactivadas().removeElement(selected);
						indexModelDesactivadas--;
						JOptionPane.showMessageDialog(null, "Borrada de la lista de desactivadas y de la lista de totales");
						//Si al eliminarla no quedan más es desactivadas, bloqueo el boton de activar
						//Si no hay alarmas desactivadas, deshabilito el boton de activar alarmas
						if (indexModelDesactivadas == 0) {
							window.getBtnActivar().setEnabled(false);
						} else {
							window.getBtnActivar().setEnabled(true);
						}
					}
				}
				//Si no hay ninguna alarma deshabilito la opcion de borrar alarmas
				if (indexModelTotal == 0) {
					window.getBtnBorrar().setEnabled(false);
				}
				System.out.println("AlarmaBorrada");
				alarmas.borraAlarma(selected); //Borro la alarma
			}
		}
	}

	//Clase Listener de desactivar alarma
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

				//La desactivo en el modelo (metodo senhal)
				alarmas.alarmaOff(selected);
				//Activo el boton de activar, ya que al menos hay 1 alarma activa
				window.getBtnActivar().setEnabled(true);
				JOptionPane.showMessageDialog(null, "Alarma Desactivada correctamente");
				//Compruebo si hay alguna alarma para desactivar, si no disable boton desactivar
				if (indexModelActivas == 0) {
					window.getBtnDesactivar().setEnabled(false);
				} else {
					window.getBtnDesactivar().setEnabled(true);
				}
			}
		}
	}

	//Clase Listener de activar alarma
	public class AlarmaOnListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			//Consigo el id de la alarma que he seleccionado
			String selected = (String) window.getListAlarmasDesactivadas().getSelectedValue();
			//Gestión de errores: si no hay nada seleccionado se lo notifico al usuario
			if (selected == null) {
				JOptionPane.showMessageDialog(null, "No hay alarma seleccionada para desactivar");

				//Caso normal: hay un id seleccionado, lo activo, elimino de la lista de desactivadas, añado a activas y notifico al usuario
			} else if (alarmas.alarma(selected).getHora().before(new Date())){
				JOptionPane.showMessageDialog(null, "La hora de activación ya ha pasado y la alarma no será activada. Se le recomienda que borre esta alarma");
				
			}else {

				window.getModelListDesactivadas().removeElement(selected);
				indexModelDesactivadas--;
				window.getModelListActivas().add(indexModelActivas, selected);
				indexModelActivas++;

				//La activo a efectos prácticos
				alarmas.alarmaOn(selected);
				//Activo el boton de desactivar, ya que al menos hay una activa
				window.getBtnDesactivar().setEnabled(true);
				JOptionPane.showMessageDialog(null, "Alarma Activada correctamente");
				//Compruebo si hay alguna alarma para activar, si no disable boton activar
				if (indexModelDesactivadas == 0) {
					window.getBtnActivar().setEnabled(false);
				}
			}
		}
	}

	//Anhadir a magicdraw implementacion MVC?? TODO

	//Clase Listener de Apagar alarma
	public class ApagarAlarmaListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {


			Alarma alarma= alarmas.alarmaMasProxima(); //CORRECTO? NO DA FALLO ESTE ALARMA MÁS PROXIMA

			if (alarma == null) {
				JOptionPane.showMessageDialog(null, "No hay alarmas que apagar");

			}else {
				//Elimino de vistas de activas y total
				window.getModelListActivas().removeElement(alarma.getId());
				indexModelActivas--;
				window.getModelListTotal().removeElement(alarma.getId());
				indexModelTotal--;

				alarmas.apagar(); //ESTA LLAMADA DA PROBLEMAS TODO
				JOptionPane.showMessageDialog(null, "La alarma que estaba sonando ya está apagada");

				window.getBtnActivar().setEnabled(true);
				window.getBtnBorrar().setEnabled(true);
				window.getBtnCrear().setEnabled(true);
				window.getBtnDesactivar().setEnabled(true);
				window.getBtnApagar().setEnabled(false);
			} 
		}
	}

	//DESACTIVO TODOS LOS BOTONES, ESPERO EL INTERVALO Y LLAMO A APAGAR, 
	//EN CASO DE QUE DEN A APAGAR ANTES, ABORTO
}
