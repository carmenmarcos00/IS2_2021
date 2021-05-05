package es.unican.is2.seguros.vista;

import org.fest.swing.fixture.FrameFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class FESTTestSegurosGUI {
	private FrameFixture demo;

	@Before
	public void setUp() {
		SegurosGUI gui = new SegurosGUI();
		demo = new FrameFixture(gui);
		gui.setVisible(true);	
	}
	@After
	public void tearDown() {
		demo.cleanUp();
	}

	@Test
	public void test() {
		
		//PRUEBA DE QUE LOS TEXTOS DE INICIO Y DE LAS ETIQUETAS DE LA INTERFAZ SON CORRECTOS
		demo.textBox("txtFechaUltimoSiniestro").requireText("dd/mm/yyyy"); 		//Compruebo texto inicial box fecha
		demo.comboBox("comboCobertura").requireItemCount(3);					//Compruebo que el combobox tenga 3 elementos
		demo.comboBox("comboCobertura").requireSelection("TODO_RIESGO");		//Compruebo que este seleccionado el elemento TODO_RIESGO
		demo.textBox("txtPotencia").requireText("75");							//Compruebo texto inicial box potencia
		demo.radioButton("btnMinusvalia").requireText("Minusvalia");			//Compruebo texto del boton minusvalia
		demo.button("btnCalcular").requireText("CALCULAR");						//Compruebo el texto del boton calcular
		demo.label("lblPrecio").requireText("PRECIO");							//Compruebo texto de label precio
		demo.label("lblCobertura").requireText("Cobertura");					//Compruebo texto de label cobertura
		demo.label("lblPotencia").requireText("Potencia");						//Compruebo texto de label potencia
		demo.label("lblUltimoSiniestro").requireText("Ultimo Siniestro");		//Compruebo texto de label ultimo siniestro
		
		
		//PRUEBA SIN FECHA INTRODUCIDA PARA VER SALIDA DE EXCEPCION
		demo.button("btnCalcular").click();										//Clickar con los datos por defecto
		demo.textBox("txtPrecio").requireText("La fecha no se pudo parsear");	//Se espera que salga ese texto en la caja de texto de precio

		//PRUEBA CON DATO DE POTENCIA INCORRECTO(0 o negativo)
		demo.textBox("txtPotencia").setText("0");								//Ponemos un 0
		demo.textBox("txtFechaUltimoSiniestro").setText("12/12/2012");			//Ponemos una fecha válida
		demo.button("btnCalcular").click();										//Click en el boton de calcular
		demo.textBox("txtPrecio").requireText("Dato introducido no valido");	//Se espera que salga ese texto en la caja de texto de precio
		
		//PRUEBA DE CASO VÁLIDO DE SEGURO CON MINUSVALÍA 
		
		demo.comboBox("comboCobertura").selectItem("TERCEROS");
		demo.textBox("txtPotencia").setText("100");								//Ponemos potencia = 100
		demo.radioButton("btnMinusvalia").click();								//Clickamos en la boton de minuvalidos
		demo.button("btnCalcular").click();										//Pulsamos en el boton de calcular
		demo.textBox("txtPrecio").requireText("315.0");							//Se espera este texto en la caja de texto de precio
																				//(400 * 1.05)*0.75
		demo.close();															//Cierro la ventana
																				
		// Sleep para visualizar como se realiza el test
		try {
			Thread.sleep(20000000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
