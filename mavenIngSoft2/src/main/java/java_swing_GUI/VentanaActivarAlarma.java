package java_swing_GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VentanaActivarAlarma extends JFrame {

	private JPanel contentPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaActivarAlarma frame = new VentanaActivarAlarma();
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
	public VentanaActivarAlarma() {
		setTitle("Activar Alarma");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 325, 219);
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPanel);
		
		JPanel panelCentral = new JPanel();
		contentPanel.add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(null);
		
		JComboBox comboBoxAlarmasDesactivadas = new JComboBox();
		comboBoxAlarmasDesactivadas.setBounds(161, 11, 136, 22);
		panelCentral.add(comboBoxAlarmasDesactivadas);
		
		JLabel lblAlarmaActivar = new JLabel("Elige alarma a activar:");
		lblAlarmaActivar.setBounds(10, 11, 239, 22);
		panelCentral.add(lblAlarmaActivar);
		
		JButton btnActivarAlarma = new JButton("Activar Alarma");
		btnActivarAlarma.setBounds(145, 140, 152, 23);
		panelCentral.add(btnActivarAlarma);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cerrar();
			}
		});
		btnCancelar.setBounds(28, 140, 89, 23);
		panelCentral.add(btnCancelar);
	}

	/**
	 * Metodo que cierra la ventana 
	 */
	private void cerrar() {
		this.dispose();
	}
}
