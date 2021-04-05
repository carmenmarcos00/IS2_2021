package java_swing_GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VentanaBorrarAlarma extends JFrame {

	private JPanel contentPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaBorrarAlarma frame = new VentanaBorrarAlarma();
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
	public VentanaBorrarAlarma() {
		setTitle("Borrar Alarma");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 325, 219);
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPanel);
		
		JPanel panelCentral = new JPanel();
		contentPanel.add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(null);
		
		JLabel lblAlarmaBorrar = new JLabel("Elige alarma a borrar:");
		lblAlarmaBorrar.setBounds(10, 11, 130, 14);
		panelCentral.add(lblAlarmaBorrar);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(152, 7, 141, 22);
		panelCentral.add(comboBox);
		
		JButton btnNewButton = new JButton("Borrar alarma");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		btnNewButton.setBounds(152, 140, 141, 23);
		panelCentral.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cerrar();
			}
		});
		btnNewButton_1.setBounds(22, 140, 89, 23);
		panelCentral.add(btnNewButton_1);
	}

	/**
	 * Metodo que cierra la ventana 
	 */
	private void cerrar() {
		this.dispose();
	}
	
}
