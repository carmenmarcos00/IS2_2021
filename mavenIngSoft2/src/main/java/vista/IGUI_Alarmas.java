package vista;

import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;

public interface IGUI_Alarmas {
	
	public void setVisible();
	
	public void addNuevaAlarmaListener (ActionListener nuevaAlarma);
	
	public void addAlarmaOnListener (ActionListener alarmaOn);
	
	public void addAlarmaOffListener (ActionListener alarmaOff);
	
	public void addApagarAlarmaListener (ActionListener apagarAlarma);
	
	public void addBorrarAlarmaListener (ActionListener borrarAlarma);
	
	public Date getHoraAlarma ();
	
	public String getIdAlarma();
	
	public JButton getBtnDesactivar();
	
	public JButton getBtnActivar();
	
	public JButton getBtnApagar();
	
	public JButton getBtnBorrar();
	
	public JButton getBtnCrear();

	@SuppressWarnings("rawtypes")
	public JList getListAlarmasActivas();
	
	@SuppressWarnings("rawtypes")
	public JList getListAlarmasDesactivadas();
	
	@SuppressWarnings("rawtypes")
	public JList getListAlarmasTotales();
	
	public DefaultListModel getModelListTotal();
	
	public DefaultListModel getModelListActivas();
	
	public DefaultListModel getModelListDesactivadas();
}
