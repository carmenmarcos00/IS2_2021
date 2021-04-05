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

public class VentanaDesactivarAlarma extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaDesactivarAlarma frame = new VentanaDesactivarAlarma();
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
	public VentanaDesactivarAlarma() {
		setTitle("Desactivar Alarma");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 325, 219);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panelCentral = new JPanel();
		contentPane.add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(null);
		
		JComboBox comboBoxAlarmasActivas = new JComboBox();
		comboBoxAlarmasActivas.setBounds(161, 11, 136, 22);
		panelCentral.add(comboBoxAlarmasActivas);
		
		JLabel lblAlarmaDesactivar = new JLabel("Elige alarma a desactivar:");
		lblAlarmaDesactivar.setBounds(10, 11, 239, 22);
		panelCentral.add(lblAlarmaDesactivar);
		
		JButton btnDesactivarAlarma = new JButton("Desactivar Alarma");
		btnDesactivarAlarma.setBounds(145, 140, 152, 23);
		panelCentral.add(btnDesactivarAlarma);
		
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
