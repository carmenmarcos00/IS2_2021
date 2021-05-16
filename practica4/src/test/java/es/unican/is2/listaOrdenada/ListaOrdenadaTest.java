package es.unican.is2.listaOrdenada;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Clase Test para ListaOrdenada
 * @author Carmen Marcos S�nchez de la Blanca
 * @version 07/05/2021
 * @param <E> Elementos de la lista. En este caso, para mayor simplicidad, siempre ser� de tipo Integer para comprobar 
 * el funcionamiento de la clase ListaOrdenada. Si funciona con un entero, funcionar� con cualquier otro elemento.
 */
public class ListaOrdenadaTest <E> {


	@Test
	public void ListaOrdenadaAddTest() {
		ListaOrdenada<Integer> sutAdd = new ListaOrdenada<Integer>();

		sutAdd.add(5);
		//CASO V�LIDO (PRUEBA 3)
		assertTrue(sutAdd.get(0) == 5);

		sutAdd.add(4);
		//CASO V�LIDO (PRUEBA 4)
		assertTrue (sutAdd.get(0) == 4);
		assertTrue (sutAdd.get(1) == 5);

		sutAdd.add(6);
		//CASO V�LIDO (PRUEBA 5)
		assertTrue (sutAdd.get(0) == 4);
		assertTrue (sutAdd.get(1) == 5);
		assertTrue (sutAdd.get(2) == 6);

		//CASO NO V�LIDO (PRUEBA 16)
		try {
			sutAdd.add(null);
			fail("No se ha lanzado la excepci�n");
		} catch (NullPointerException e) {
			System.out.println("Ha ido bien caso no v�lido (PRUEBA 14)");
		}
	}

	@Test
	public void ListaOrdenadaGetTest() {

		ListaOrdenada<Integer> sutGet = new ListaOrdenada<Integer>();

		//CASO NO V�LIDO (PRUEBA 14)
		try {
			sutGet.get(0);
			fail("No se ha lanzado la excepci�n");
		} catch(IndexOutOfBoundsException e) {
			System.out.println("Ha ido bien caso no v�lido (PRUEBA 14)");
		}

		sutGet.add(5);
		//CASO V�LIDO (PRUEBA 1)
		assertTrue(sutGet.get(0) == 5);

		sutGet.add(4);
		sutGet.add(6);
		//CASO V�LIDO (PRUEBA 2)
		assertTrue(sutGet.get(2) == 6);

		//CASO NO V�LIDO (PRUEBA 15)
		try {
			sutGet.get(13);
			fail("No se ha lanzado la excepci�n");
		} catch(IndexOutOfBoundsException e) {
			System.out.println("Ha ido bien caso no v�lido (PRUEBA 15)");
		}
	}

	@Test
	public void ListaOrdenadaRemoveTest() {
		ListaOrdenada<Integer> sutRemove = new ListaOrdenada<Integer>();

		sutRemove.add(5);
		//CASO V�LIDO (PRUEBA 6)
		sutRemove.remove(0);
		assertTrue(sutRemove.size() == 0);; //Al eliminar el �nico elemento que hay, el tama�o deber�a de ser 0
		try {
			sutRemove.get(0); //No deber�a de haber elemento 0 y se tiene que lanzar la excepcion
			fail("No se ha lanzado la excepci�n");
		} catch(IndexOutOfBoundsException e) {
			System.out.println("Ha ido bien caso no v�lido (PRUEBA 6 )");
		}
		
		
		sutRemove.add(5);
		sutRemove.add(4);
		sutRemove.add(6); //[4,5,6]
		//CASO V�LIDO (PRUEBA 7)
		sutRemove.remove(1); //Elimino el 5 = [4,6]
		assertTrue(sutRemove.size() == 2); //El tama�o deber�a de ser 2
		assertTrue(sutRemove.get(0) == 4); //El primer elemento deber�a de ser 4
		assertTrue(sutRemove.get(1) == 6); //El segundo y �ltimo elemento deber�a de ser el 6
	}


	@Test
	public void ListaOrdenadaSizeTest() {
		ListaOrdenada<Integer> sutSize = new ListaOrdenada<Integer>();
		//CASO V�LIDO (PRUEBA 8)
		assertTrue(sutSize.size()== 0);

		sutSize.add(5);
		//CASO V�LIDO (PRUEBA 9)
		assertTrue(sutSize.size() == 1);

		sutSize.add(6);
		sutSize.add(4);
		//CASO V�LIDO (PRUEBA 10)
		assertTrue(sutSize.size()==3);
	}

	@Test 
	public void ListaOrdenadaClearTest() {
		ListaOrdenada<Integer> sutClear = new ListaOrdenada<Integer>();

		//CASO V�LIDO (PRUEBA 11)
		sutClear.clear();
		assertTrue(sutClear.size() == 0);

		sutClear.add(5);
		//CASO V�LIDO (PRUEBA 12)
		sutClear.clear();//NO ELIMINA
		assertTrue (sutClear.size() == 0);

		sutClear.add(5);
		sutClear.add(6);
		sutClear.add(4);
		//CASO V�LIDO (PRUEBA 13)
		sutClear.clear();
		System.out.println(sutClear.size()); //Deber�a de ser 0
		assertTrue (sutClear.size() == 0);

	}

}
