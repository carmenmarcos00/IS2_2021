package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerModel;
import javax.swing.SwingConstants;
import java.awt.Toolkit;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.util.Calendar;
import java.util.Date;
import javax.swing.border.LineBorder;

import patronState.Alarmas;

import java.awt.Color;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;


public class GUI_Alarmas implements IGUI_Alarmas{

	private JFrame frmAplicacinAlarmas;
	private JTextField textIdAlarmaNueva;
	private JButton btnCrearAlarma;
	private JButton btnActivarAlarma;
	private JButton btnDesactivarAlarma;
	private JButton btnBorrarAlarma;
	private JButton btnApagarAlarma;
	private JSpinner spinnerHoraAlarma;
	private SpinnerModel horaModel;
	private JList listAlarmasTotales;
	private JList listAlarmasActivas;
	private JList listAlarmasDesactivadas;
	private DefaultListModel modelListTotal;
	private DefaultListModel modelListDesactivadas;
	private DefaultListModel modelListActivas;
	private Alarmas alarmas= new Alarmas();

	public GUI_Alarmas() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		//JFrame principal
		frmAplicacinAlarmas = new JFrame();
		frmAplicacinAlarmas.setTitle("Aplicaci\u00F3n Alarmas");
		frmAplicacinAlarmas.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Carmen\\Desktop\\IS2_2021\\IS2_2021\\mavenIngSoft2\\imagenes\\alarma-de-bombero.png"));
		frmAplicacinAlarmas.setResizable(false);
		frmAplicacinAlarmas.setBounds(100, 100, 578, 537);
		frmAplicacinAlarmas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		//JPanel central donde se situaran el resto de componentes
		JPanel panelCentral = new JPanel();
		frmAplicacinAlarmas.getContentPane().add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(null);


		//JPanel superior izquierda donde se crean las alarmas
		JPanel panelNuevaAlarma = new JPanel();
		panelNuevaAlarma.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelNuevaAlarma.setBounds(20, 23, 254, 208);
		panelCentral.add(panelNuevaAlarma);
		panelNuevaAlarma.setLayout(null);
		
		//Label del titulo
		JLabel lblCrearNuevaAlarma = new JLabel("CREAR NUEVA ALARMA");
		lblCrearNuevaAlarma.setHorizontalAlignment(SwingConstants.CENTER);
		lblCrearNuevaAlarma.setBounds(0, 11, 254, 14);
		panelNuevaAlarma.add(lblCrearNuevaAlarma);
		
		//Label de introduccion de id de la nueva alarma
		JLabel lblIdNuevaAlarma = new JLabel("Id Alarma");
		lblIdNuevaAlarma.setBounds(20, 65, 80, 14);
		panelNuevaAlarma.add(lblIdNuevaAlarma);
		
		//Texto sin formateo para introducir el id
		textIdAlarmaNueva = new JTextField();
		textIdAlarmaNueva.setBounds(97, 62, 134, 20);
		panelNuevaAlarma.add(textIdAlarmaNueva);
		textIdAlarmaNueva.setColumns(10);
		
		//Label de introduccion de hora de la nueva alarma
		JLabel lblHoralNuevaAlarma = new JLabel("Hora Alarma");
		lblHoralNuevaAlarma.setBounds(20, 106, 77, 19);
		panelNuevaAlarma.add(lblHoralNuevaAlarma);

		//Spinner para conseguir la hora que quiero
		SpinnerDateModel model = new SpinnerDateModel(new Date(), null, null, Calendar.MINUTE);
		spinnerHoraAlarma = new JSpinner(model);
		spinnerHoraAlarma.setEditor(new JSpinner.DateEditor(spinnerHoraAlarma, "HH:mm"));
		spinnerHoraAlarma.setBounds(97, 105, 134, 20);
		panelNuevaAlarma.add(spinnerHoraAlarma,  BorderLayout.NORTH);
		
		//Boton para crear la nueva alarma
		btnCrearAlarma = new JButton("Crear");
		btnCrearAlarma.setBounds(77, 174, 84, 23);
		panelNuevaAlarma.add(btnCrearAlarma);

		
		//JPanel superior derecha donde se activan alarmas desactivadas
		JPanel panelAlarmasActivar = new JPanel();
		panelAlarmasActivar.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelAlarmasActivar.setBounds(290, 23, 254, 208);
		panelCentral.add(panelAlarmasActivar);
		panelAlarmasActivar.setLayout(null);
		
		//Label del titulo del panel
		JLabel lblActivarAlarma = new JLabel("ACTIVAR ALARMA");
		lblActivarAlarma.setEnabled(true);
		lblActivarAlarma.setHorizontalAlignment(SwingConstants.CENTER);
		lblActivarAlarma.setBounds(0, 11, 243, 14);
		panelAlarmasActivar.add(lblActivarAlarma);
		
		//Label de seleccion de alarma
		JLabel lblEligeAlarmaActivar = new JLabel("Elige alarma a activar");
		lblEligeAlarmaActivar.setBounds(10, 36, 138, 14);
		panelAlarmasActivar.add(lblEligeAlarmaActivar);
		
		//Lista de donde se elije la alarma a activar
		modelListDesactivadas = new DefaultListModel();
		listAlarmasDesactivadas = new JList(modelListDesactivadas);
		listAlarmasDesactivadas.setBounds(20, 61, 213, 102);
		panelAlarmasActivar.add(listAlarmasDesactivadas);
		
		//Boton para activar la alarma selecionada de la lista
		btnActivarAlarma = new JButton("Activar");
		btnActivarAlarma.setBounds(82, 174, 89, 23);
		panelAlarmasActivar.add(btnActivarAlarma);


		//JPanel inferior derecha donde se desactivan las alarmas activas
		JPanel panelAlarmasDesactivar = new JPanel();
		panelAlarmasDesactivar.setLayout(null);
		panelAlarmasDesactivar.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelAlarmasDesactivar.setBounds(290, 253, 254, 208);
		panelCentral.add(panelAlarmasDesactivar);
		
		//Label del titulo del panel 
		JLabel lblDesactivarAlarma = new JLabel("DESACTIVAR ALARMA");
		lblDesactivarAlarma.setHorizontalAlignment(SwingConstants.CENTER);
		lblDesactivarAlarma.setEnabled(true);
		lblDesactivarAlarma.setBounds(0, 11, 254, 14);
		panelAlarmasDesactivar.add(lblDesactivarAlarma);
		
		//Label de seleccion de alarma
		JLabel lblEligeAlarmaDesactivar = new JLabel("Elige alarma a desactivar");
		lblEligeAlarmaDesactivar.setBounds(10, 36, 204, 14);
		panelAlarmasDesactivar.add(lblEligeAlarmaDesactivar);
		
		//Lista de donde se elije la alarma a desactivar
		modelListActivas = new DefaultListModel();
		listAlarmasActivas = new JList(modelListActivas);
		listAlarmasActivas.setBounds(20, 62, 212, 101);
		panelAlarmasDesactivar.add(listAlarmasActivas);
		//Boton para desactivar la alarma seleccionada de la lista
		btnDesactivarAlarma = new JButton("Desactivar");
		btnDesactivarAlarma.setBounds(74, 174, 101, 23);
		panelAlarmasDesactivar.add(btnDesactivarAlarma);

		
		//Jpanel inferior izquierda donde se borran las alarmas
		JPanel panelBorrarAlarma = new JPanel();
		panelBorrarAlarma.setLayout(null);
		panelBorrarAlarma.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelBorrarAlarma.setBounds(20, 253, 254, 208);
		panelCentral.add(panelBorrarAlarma);
		
		//Label del titulo del panel
		JLabel lblBorrarAlarma = new JLabel("BORRAR ALARMA");
		lblBorrarAlarma.setHorizontalAlignment(SwingConstants.CENTER);
		lblBorrarAlarma.setBounds(0, 11, 243, 14);
		panelBorrarAlarma.add(lblBorrarAlarma);
		
		//Label de seleccion de alarma
		JLabel lblEligeAlarmaBorrar = new JLabel("Elige alarma a borrar");
		lblEligeAlarmaBorrar.setBounds(10, 36, 153, 19);
		panelBorrarAlarma.add(lblEligeAlarmaBorrar);
		
		//Lista de donde se elije la alarma a borrar
		modelListTotal = new DefaultListModel();
		listAlarmasTotales= new JList(modelListTotal);
		listAlarmasTotales.setBounds(20, 62, 212, 101);
		panelBorrarAlarma.add(listAlarmasTotales);
		
		//Boton para borrar la alarma seleccionada de la lista
		btnBorrarAlarma = new JButton("Borrar");
		btnBorrarAlarma.setBounds(79, 174, 84, 23);
		panelBorrarAlarma.add(btnBorrarAlarma);


		//Boton para apagar la alarma que esta sonando (intentar que solo salga cuando suene)
		//O reemplazar por una ventana emergente cuando sea la hora
		btnApagarAlarma = new JButton("APAGAR ALARMA");
		btnApagarAlarma.setBounds(214, 472, 137, 29);
		panelCentral.add(btnApagarAlarma);
	}

	public void setVisible() {
		frmAplicacinAlarmas.setVisible(true);
	}

	public void addNuevaAlarmaListener (ActionListener nuevaAlarma) {
		btnCrearAlarma.addActionListener(nuevaAlarma);
	}

	public void addAlarmaOnListener (ActionListener alarmaOn) {
		btnActivarAlarma.addActionListener(alarmaOn);
	}

	public void addAlarmaOffListener (ActionListener alarmaOff) {
		btnDesactivarAlarma.addActionListener(alarmaOff);
	}

	public void addApagarAlarmaListener (ActionListener apagarAlarma) {
		btnApagarAlarma.addActionListener(apagarAlarma);
	}
	public void addBorrarAlarmaListener (ActionListener borrarAlarma) {
		btnBorrarAlarma.addActionListener(borrarAlarma);
	}

	public Date getHoraAlarma () {
		Date horaDate = new Date();
		Date aux = (Date) spinnerHoraAlarma.getValue();
		horaDate.setHours(aux.getHours());
		horaDate.setMinutes(aux.getMinutes());
		return horaDate;
	}
	
	public String getIdAlarma() {
		String idAlarma = textIdAlarmaNueva.getText();
		return idAlarma;
	}

	public JButton getBtnDesactivar() {
		return btnDesactivarAlarma;
	}

	public JButton getBtnActivar() {
		return btnActivarAlarma;
	}

	public JButton getBtnApagar() {
		return btnApagarAlarma;
	}

	public JButton getBtnBorrar() {
		return btnBorrarAlarma;
	}

	public JButton getBtnCrear() {
		return btnCrearAlarma;
	}

	public JList getListAlarmasActivas() {
		return listAlarmasActivas;
	}

	public JList getListAlarmasDesactivadas() {
		return listAlarmasDesactivadas;
	}

	public JList getListAlarmasTotales() {
		return listAlarmasTotales;
	}
	
	public DefaultListModel getModelListTotal() {
		return modelListTotal;
	}
	
	public DefaultListModel getModelListActivas() {
		return modelListActivas;
	}
	
	public DefaultListModel getModelListDesactivadas() {
		return modelListDesactivadas;
	}

	public void update() {
		JOptionPane.showMessageDialog(null, "Sonando Alarma");
	}	
	
	
}
