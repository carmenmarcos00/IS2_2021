package es.unican.is2.seguros.model;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
public class SeguroTest {

	//Creo los seguros
	Seguro sut1, sut2, sut3, sut4, sut5, sut6, sut7, sut8, sut9, sut1NV, sut2NV, sut3NV, sut4NV, sut5NV, sut6NV; 

	@Before
	public void creoSeguros() throws DatoIncorrectoException {

		//Creo e inicializo los clientes
		Cliente clienteMinusvalido =  new Cliente("Pepe", "1111", true);
		Cliente clienteNoMinusvalido =  new Cliente("Pepe", "1111", false);

		//Creo e incializo los tipos de cobertura
		Cobertura todoRiesgo = Cobertura.TODO_RIESGO ;
		Cobertura tercerosLunas = Cobertura.TERCEROS_LUNAS;
		Cobertura terceros= Cobertura.TERCEROS;

		//------------------------------------ previo a TEST PRECIO()----------------------------------------------------//

		//Inicializo los seguros v�lidos
		
		try {
			sut1 = new Seguro(1, clienteMinusvalido,todoRiesgo);
			sut2 = new Seguro(89, clienteMinusvalido,tercerosLunas);
			sut3 = new Seguro(45, clienteNoMinusvalido,terceros);
			sut4 = new Seguro(90, clienteNoMinusvalido,todoRiesgo);
			sut5 = new Seguro(110, clienteMinusvalido,tercerosLunas);
			sut6 = new Seguro(100, clienteMinusvalido,terceros);
			sut7 = new Seguro(111, clienteNoMinusvalido,todoRiesgo);
			sut8 = new Seguro(100000, clienteNoMinusvalido,tercerosLunas);
			sut9 = new Seguro(1, clienteMinusvalido,terceros);

		} catch (DatoIncorrectoException e) {
			e.printStackTrace();
		}


		//Set de las fechas de siniestro validas
		sut1.setFechaUltimoSiniestro(LocalDate.now().minusYears(30));				//hoy - (30 a�os)
		sut2.setFechaUltimoSiniestro(LocalDate.now().minusYears(3).minusDays(1));	//hoy -(3 a�os y un dia)
		sut3.setFechaUltimoSiniestro(LocalDate.now().minusYears(4)); 				//hoy -(4 a�os)
		sut4.setFechaUltimoSiniestro(LocalDate.now().minusYears(3)); 				//hoy -(3 a�os)
		sut5.setFechaUltimoSiniestro(LocalDate.now().minusYears(1).minusDays(1)); 	//hoy -(1 a�o y un dia)
		sut6.setFechaUltimoSiniestro(LocalDate.now().minusYears(2)); 				//hoy -(2 a�os)
		sut7.setFechaUltimoSiniestro(LocalDate.now().minusYears(1)); 				//hoy -(1 a�o)
		sut8.setFechaUltimoSiniestro(LocalDate.now()); 								//hoy 
		sut9.setFechaUltimoSiniestro(LocalDate.now().minusMonths(1)); 				//hoy -(1 mes)


		//Inicializo los seguros no v�lidos

			sut1NV = new Seguro(-2000, clienteMinusvalido,todoRiesgo);
			sut2NV = new Seguro(0, clienteMinusvalido,tercerosLunas);
			sut3NV = new Seguro(-50, clienteNoMinusvalido,terceros);
			sut4NV = new Seguro(90, clienteNoMinusvalido,todoRiesgo);
			sut5NV = new Seguro(110, clienteMinusvalido,tercerosLunas);
			sut6NV = new Seguro(100, clienteMinusvalido,terceros);



		//Set de las fechas de siniestro de casos no validos
		sut1NV.setFechaUltimoSiniestro(LocalDate.now().minusYears(30));				//hoy - (30 a�os)
		sut2NV.setFechaUltimoSiniestro(LocalDate.now().minusYears(3).minusDays(1));	//hoy -(3 a�os y un dia)
		sut3NV.setFechaUltimoSiniestro(LocalDate.now().minusYears(4)); 				//hoy -(4 a�os)
		sut4NV.setFechaUltimoSiniestro(LocalDate.now().plusDays(1)); 				//MA�ANA
		sut5NV.setFechaUltimoSiniestro(LocalDate.now().plusYears(200)); 			//en 200 a�os
		sut6NV.setFechaUltimoSiniestro(LocalDate.now().plusMonths(1)); 				//en 1 mes
	}


	@Test
	public void testSeguro() {

		//Creo los seguros para el test de seguros
		try {
			Seguro seg1 = new Seguro(1, new Cliente("Pepe", "1111", true), Cobertura.TODO_RIESGO);
			assertTrue(seg1.getCliente().getMinusvalia() == true);
			assertTrue(seg1.getPotenciaCV() == 1);
			assertTrue(seg1.getCobertura() == Cobertura.TODO_RIESGO);
			
		} catch (DatoIncorrectoException e) {
			e.printStackTrace();
		}
		try {
			Seguro seg2 = new Seguro(300000, new Cliente("Pepe", "1111", false), Cobertura.TERCEROS_LUNAS);
			assertTrue(seg2.getCliente().getMinusvalia() == false);
			assertTrue(seg2.getPotenciaCV() == 300000);
			assertTrue(seg2.getCobertura() == Cobertura.TERCEROS_LUNAS);
		} catch (DatoIncorrectoException e) {
			e.printStackTrace();
		}
		try {
			Seguro seg3 = new Seguro(200, new Cliente("Pepe", "1111", true), Cobertura.TERCEROS);
			assertTrue(seg3.getCliente().getMinusvalia() == true);
			assertTrue(seg3.getPotenciaCV() == 200);
			assertTrue(seg3.getCobertura() == Cobertura.TERCEROS);
		} catch (DatoIncorrectoException e) {
			e.printStackTrace();
		}


	}

	@Test
	public void testPrecio() {

		//Casos de prueba v�lidos
		try {
			assertTrue(sut1.precio() == 750);
		} catch (DatoIncorrectoException e1) {
			e1.printStackTrace();
		}

		try { //Caso de prueba v�lido 1
			assertTrue(sut1.precio() == 750);
		} catch (DatoIncorrectoException e) {
			fail("No deber�a lanzar la excepci�n");
		}

		try { //Caso de prueba v�lido 2
			assertTrue(sut2.precio() == 450);
		} catch (DatoIncorrectoException e) {
			fail("No deber�a lanzar la excepci�n");
		}

		try { //Caso de prueba v�lido 3
			assertTrue(sut3.precio() == 400);
		} catch (DatoIncorrectoException e) {
			fail("No deber�a lanzar la excepci�n");
		}


		try { //Caso de prueba v�lido 4
			assertTrue(sut4.precio() == 1100);
		} catch (DatoIncorrectoException e) {
			fail("No deber�a lanzar la excepci�n");
		}

		try { //Caso de prueba v�lido 5
			assertTrue(sut5.precio() == 510);
		} catch (DatoIncorrectoException e) {
			fail("No deber�a lanzar la excepci�n");
		}

		try { //Caso de prueba v�lido 6
			assertTrue(sut6.precio() == 352.5);
		} catch (DatoIncorrectoException e) {
			fail("No deber�a lanzar la excepci�n");
		}

		try { //Caso de prueba v�lido 7
			assertTrue(sut7.precio() == 1400);
		} catch (DatoIncorrectoException e) {
			fail("No deber�a lanzar la excepci�n");
		}

		try { //Caso de prueba v�lido 8
			assertTrue(sut8.precio() == 920);
		} catch (DatoIncorrectoException e) {
			fail("No deber�a lanzar la excepci�n");
		}

		try { //Caso de prueba v�lido 9
			assertTrue(sut9.precio() == 450);
		} catch (DatoIncorrectoException e) {
			fail("No deber�a lanzar la excepci�n");
		}



		//CASOS DE PRUEBA NO V�LIDOS
		try{
			sut1NV.precio();
			fail("No se ha lanzado la excepci�n");
		} catch(DatoIncorrectoException e) {
			System.out.println("Ha ido bien 1");
		}

		try{
			sut2NV.precio();
			fail("No se ha lanzado la excepci�n");
		} catch(DatoIncorrectoException e) {
			System.out.println("Ha ido bien 2");
		}

		try{
			sut3NV.precio();
			fail("No se ha lanzado la excepci�n");
		} catch(DatoIncorrectoException e) {
			System.out.println("Ha ido bien 3");
		}

		try{
			sut4NV.precio();
			System.out.println(sut4NV.precio());
			fail("No se ha lanzado la excepci�n");
		} catch(DatoIncorrectoException e) {
			System.out.println("Ha ido bien 4");
		}

		try{
			sut5NV.precio();
			fail("No se ha lanzado la excepci�n");
		} catch(DatoIncorrectoException e) {
			System.out.println("Ha ido bien 5");
		}

		try{
			sut6NV.precio();
			fail("No se ha lanzado la excepci�n");
		} catch(DatoIncorrectoException e) {
			System.out.println("Ha ido bien 6");
		}
	}
}
