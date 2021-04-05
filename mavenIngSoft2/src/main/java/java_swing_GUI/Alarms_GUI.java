package java_swing_GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextPane;
import java.awt.ScrollPane;
import java.awt.List;
import java.awt.Button;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Alarms_GUI extends JFrame {

	private JPanel contentPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Alarms_GUI frame = new Alarms_GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Alarms_GUI() {
		//CARACTERISTICAS DE LA VENTANA PRINCIPAL
		
		//Imagen del marco principal
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Carmen\\Desktop\\IS2_2021\\IS2_2021\\mavenIngSoft2\\imagenes\\alarma-de-bombero.png"));
		//Tipo de letra a usar
		setFont(new Font("Arial", Font.PLAIN, 12));
		//No dejo que la ventana sea redimensionable para que no altere la disposicion de la interfaz
		setResizable(false);
		//Titulo de la aplicacion y de la ventana principal
		setTitle("Aplicacion Alarmas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 520, 350);
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPanel);
		
		//Creo un panel central como contenedor de todos los items
		JPanel panelCentralApp = new JPanel();
		contentPanel.add(panelCentralApp, BorderLayout.CENTER);
		panelCentralApp.setLayout(null);
		
		//Boton de nueva alarma y contenidos asociados
		JButton botonNuevaAlarm = new JButton("NUEVA ALARMA");
		botonNuevaAlarm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnNuevaAlarmaMouseClicked(e);
			}
		});
		botonNuevaAlarm.setBounds(25, 33, 175, 34);
		panelCentralApp.add(botonNuevaAlarm);
		
		//Boton de borrar alarma y contenidos asociados
		JButton botonBorrarAlarm = new JButton("BORRAR ALARMA");
		botonBorrarAlarm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnBorrarAlarmaMouseClicked(e);
			}
		});
		botonBorrarAlarm.setBounds(25, 101, 175, 34);
		panelCentralApp.add(botonBorrarAlarm);
		
		//Boton de desactivar alarma y contenidos asociados
		JButton botonDesactivarAlarm= new JButton("DESACTIVAR ALARMA");
		botonDesactivarAlarm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnDesactivarAlarmaMouseClicked(e);
			}
		});
		botonDesactivarAlarm.setBounds(25, 173, 175, 34);
		panelCentralApp.add(botonDesactivarAlarm);
		
		//Boton de activar alarma y contenidos asociados
		JButton botonActivarAlarm = new JButton("ACTIVAR ALARMA");
		botonActivarAlarm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnActivarAlarmaMouseClicked( e);
			}
		});
		botonActivarAlarm.setBounds(25, 242, 175, 34);
		panelCentralApp.add(botonActivarAlarm);
		
		//Etiqueta de texto de alarmas Activas
		JLabel lblAlarmasActivas = new JLabel("Alarmas Activas");
		lblAlarmasActivas.setBounds(249, 33, 209, 14);
		panelCentralApp.add(lblAlarmasActivas);
		
		//Lista donde se visualizan las alarmas activas		
		DefaultListModel<String> modelActivas = new DefaultListModel<String>();
		JList<String> listAlarmasActivas = new JList<String>( modelActivas );
		listAlarmasActivas.setBounds(249, 54, 209, 67);
		
		/*
		int longitudActivas = patronState.Alarmas.this.alarmasActivas().length;
		
		for ( int i = 0; i < longitudActivas; i++ ){
		  model.addElement( patronState.Alarmas.this.alarmasActivas()[i].getId() );
		}
		*/
		panelCentralApp.add(listAlarmasActivas);
		
		//Etiqueta de texto de alarmas desactivadas
		JLabel lblAlarmasDesactivas = new JLabel("Alarmas Desactivadas");
		lblAlarmasDesactivas.setBounds(249, 138, 209, 14);
		panelCentralApp.add(lblAlarmasDesactivas);
		
		//Lista donde se visualizan las alarmas desactivadas
	
		DefaultListModel<String> modelDesactivadas = new DefaultListModel<String>();
		JList<String> listAlarmasDesactivadas = new JList<String>( modelDesactivadas );
		listAlarmasDesactivadas.setBounds(249, 158, 209, 67);
		
		/*
		int longitudDesactivadas = patronState.Alarmas.this.alarmasDesactivadas().length;
		
		for ( int i = 0; i < longitudDesactivadas; i++ ){
		  model.addElement( patronState.Alarmas.this.alarmasDesactivadas()[i].getId() );
		}
		*/
		panelCentralApp.add(listAlarmasDesactivadas);
		
		//Boton de apagar alarma y contenidos asociados
		JButton botonApagarAlarm = new JButton("APAGAR ALARMA");
		botonApagarAlarm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnApagarAlarmaMouseClicked(e);
			}
		});
		botonApagarAlarm.setBounds(282, 242, 148, 34);
		panelCentralApp.add(botonApagarAlarm);
	}
	
	
	/**
	 * Metodo llamado cuando se hace click en el boton de nueva alarma
	 * Se crea otra ventana donde se introducen los datos y se crea la alarma
	 * (Une las 2 ventanas, Alarms_GUI y VentanaNuevaAlarma)
	 * @param e evento que desencadena la accion
	 */
	private void btnNuevaAlarmaMouseClicked(MouseEvent e) {
		//Creamos la nueva ventana
		VentanaNuevaAlarma vent = new VentanaNuevaAlarma();
		//Hago visible la ventana
		vent.setVisible(true);
	}
	
	/**
	 * Metodo llamado cuando se hace click en el boton de activar alarma
	 * Se crea otra ventana donde se introducen los datos y se activa la alarma
	 * (Une las 2 ventanas, Alarms_GUI y VentanaActivarAlarma)
	 * @param e evento que desencadena la accion
	 */
	private void btnActivarAlarmaMouseClicked(MouseEvent e) {
		//Creamos la nueva ventana
		VentanaActivarAlarma vent = new VentanaActivarAlarma();
		//Hago visible la ventana
		vent.setVisible(true);
	}
	
	/**
	 * Metodo llamado cuando se hace click en el boton de apagar alarma
	 * Se crea otra ventana donde se apaga la alarma
	 * (Une las 2 ventanas, Alarms_GUI y VentanaApagarAlarma)
	 * @param e evento que desencadena la accion
	 */
	private void btnApagarAlarmaMouseClicked(MouseEvent e) {
		//Creamos la nueva ventana
		VentanaApagarAlarma vent = new VentanaApagarAlarma();
		//Hago visible la ventana
		vent.setVisible(true);
	}
	
	/**
	 * Metodo llamado cuando se hace click en el boton de borrar alarma
	 * Se crea otra ventana donde se borra la alarma
	 * (Une las 2 ventanas, Alarms_GUI y VentanaBorrarAlarma)
	 * @param e evento que desencadena la accion
	 */
	private void btnBorrarAlarmaMouseClicked(MouseEvent e) {
		//Creamos la nueva ventana
		VentanaBorrarAlarma vent = new VentanaBorrarAlarma();
		//Hago visible la ventana
		vent.setVisible(true);
	}
	
	/**
	 * Metodo llamado cuando se hace click en el boton de desactivar alarma
	 * Se crea otra ventana donde se desactiva la alarma
	 * (Une las 2 ventanas, Alarms_GUI y VentanaDesactivarAlarma)
	 * @param e evento que desencadena la accion
	 */
	private void btnDesactivarAlarmaMouseClicked(MouseEvent e) {
		//Creamos la nueva ventana
		VentanaDesactivarAlarma vent = new VentanaDesactivarAlarma();
		//Hago visible la ventana
		vent.setVisible(true);
	}
	
	
}
