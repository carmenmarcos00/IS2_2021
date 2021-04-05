package java_swing_GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.JSpinner;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import patronState.Alarmas;
import patronState.Alarma;
public class VentanaNuevaAlarma extends JFrame {

	private JPanel contentPane;
	private JTextField txtIdAlarma;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaNuevaAlarma frame = new VentanaNuevaAlarma();
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
	public VentanaNuevaAlarma() {
		setTitle("Crear Nueva Alarma");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 325, 219);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panelCentral = new JPanel();
		contentPane.add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(null);
		
		JLabel lblIdAlarma = new JLabel("Id Alarma");
		lblIdAlarma.setBounds(48, 33, 70, 20);
		lblIdAlarma.setVerticalAlignment(SwingConstants.TOP);
		panelCentral.add(lblIdAlarma);
		
		JLabel lblHoraAlarma = new JLabel("Hora Alarma");
		lblHoraAlarma.setBounds(48, 64, 104, 14);
		panelCentral.add(lblHoraAlarma);
		
		txtIdAlarma = new JTextField();
		txtIdAlarma.setToolTipText("Introduzca Id de la Alarma");
		txtIdAlarma.setBounds(149, 30, 96, 20);
		panelCentral.add(txtIdAlarma);
		txtIdAlarma.setColumns(10);
		
		Date date = new Date();
		SpinnerDateModel sm = new SpinnerDateModel(date, null, null, Calendar.MINUTE);

		final JSpinner spinner = new JSpinner(sm);
		JSpinner.DateEditor de = new JSpinner.DateEditor(spinner, "hh:mm a");
		de.getTextField().setEditable( false );
		spinner.setEditor(de);
		spinner.setBounds(149, 61, 96, 20);
		panelCentral.add(spinner);

		System.out.println("Spinner:      "+de.getFormat().format(spinner.getValue()));

		
        
        
        
        JButton btnCrearAlarma = new JButton("Crear Alarma");
        btnCrearAlarma.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		String id = txtIdAlarma.getText();
        		Date date = (Date) spinner.getValue();
        		Alarma alarma = new Alarma(id, date);

        	//	boolean anhadeAlarma = patronState.Alarmas.anhadeAlarma(alarma);
        	}
        });
        btnCrearAlarma.setBounds(151, 127, 108, 23);
        panelCentral.add(btnCrearAlarma);
        
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		//Elimino esta ventana pero sigo ejecutando la app
        		cerrar();
        	}
        });

        btnCancelar.setBounds(37, 127, 89, 23);
        panelCentral.add(btnCancelar);
       
	}
	
	/**
	 * Metodo que cierra la ventana 
	 */
	private void cerrar() {
		this.dispose();
	}
	
	private void anhadirAlarma(MouseEvent e, JSpinner spinner) {
		String id = txtIdAlarma.getText();
		Date date = (Date) spinner.getValue();
		Alarma alarma = new Alarma(id, date);
	//	patronState.Alarmas.anhadeAlarma(alarma);
		System.out.println(date);
		
	}
}
