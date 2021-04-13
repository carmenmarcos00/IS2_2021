package vista;

import javax.swing.JFrame;

/**
 * Clase que implementa los métodos necesarios para hacer que la ventana principal vibre cuando suene y cuando apage la alarma
 * @author Carmen Marcos Sánchez de la Blanca
 * @version 13/04/2021
 */
public class FrameUtils {
	
	//Constantes de la clase
	private final static int TIEMPO_VIBRANDO = 10;
	  private final static int VELOCIDAD_VIBRACION = 5;
	  
	  /**
	   * Constructor vacío de la clase
	   */
	  private FrameUtils() { }
	  
	  /**
	   * Método que hace vibrar la ventana principal de la aplicación
	   * @param frame ventana que quiero hacer vibrar
	   */
	  public static void vibrate(JFrame frame) { 
		  
	    try { 
	    	//Consigo las coordenadas inicales del frame
	      final int originalX = frame.getLocationOnScreen().x; 
	      final int originalY = frame.getLocationOnScreen().y;
	      
	      for(int i = 0; i < TIEMPO_VIBRANDO; i++) { 
	        Thread.sleep(10); 
	        frame.setLocation(originalX, originalY + VELOCIDAD_VIBRACION);  //Cambio la situacion del frame
	        Thread.sleep(10); 
	        frame.setLocation(originalX, originalY - VELOCIDAD_VIBRACION);  //Cambio la situacion del frame
	        Thread.sleep(10); 
	        frame.setLocation(originalX + VELOCIDAD_VIBRACION, originalY);  //Cambio la situacion del frame
	        Thread.sleep(10); 
	        frame.setLocation(originalX, originalY); //Vuelvo a dejar la pestaña en su sitio
	      } 
	    } 
	    catch (Exception err) { 
	      err.printStackTrace(); 
	    } 
	  }
}
