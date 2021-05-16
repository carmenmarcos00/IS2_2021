package es.unican.is2.listaOrdenada;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Clase Test para ListaOrdenada
 * @author Carmen Marcos Sánchez de la Blanca
 * @version 07/05/2021
 * @param <E> Elementos de la lista. En este caso, para mayor simplicidad, siempre será de tipo Integer para comprobar 
 * el funcionamiento de la clase ListaOrdenada. Si funciona con un entero, funcionará con cualquier otro elemento.
 */
public class ListaOrdenadaTest <E> {


	@Test
	public void ListaOrdenadaAddTest() {
		ListaOrdenada<Integer> sutAdd = new ListaOrdenada<Integer>();

		sutAdd.add(5);
		//CASO VÁLIDO (PRUEBA 3)
		assertTrue(sutAdd.get(0) == 5);

		sutAdd.add(4);
		//CASO VÁLIDO (PRUEBA 4)
		assertTrue (sutAdd.get(0) == 4);
		assertTrue (sutAdd.get(1) == 5);

		sutAdd.add(6);
		//CASO VÁLIDO (PRUEBA 5)
		assertTrue (sutAdd.get(0) == 4);
		assertTrue (sutAdd.get(1) == 5);
		assertTrue (sutAdd.get(2) == 6);

		//CASO NO VÁLIDO (PRUEBA 16)
		try {
			sutAdd.add(null);
			fail("No se ha lanzado la excepción");
		} catch (NullPointerException e) {
			System.out.println("Ha ido bien caso no válido (PRUEBA 14)");
		}
	}

	@Test
	public void ListaOrdenadaGetTest() {

		ListaOrdenada<Integer> sutGet = new ListaOrdenada<Integer>();

		//CASO NO VÁLIDO (PRUEBA 14)
		try {
			sutGet.get(0);
			fail("No se ha lanzado la excepción");
		} catch(IndexOutOfBoundsException e) {
			System.out.println("Ha ido bien caso no válido (PRUEBA 14)");
		}

		sutGet.add(5);
		//CASO VÁLIDO (PRUEBA 1)
		assertTrue(sutGet.get(0) == 5);

		sutGet.add(4);
		sutGet.add(6);
		//CASO VÁLIDO (PRUEBA 2)
		assertTrue(sutGet.get(2) == 6);

		//CASO NO VÁLIDO (PRUEBA 15)
		try {
			sutGet.get(13);
			fail("No se ha lanzado la excepción");
		} catch(IndexOutOfBoundsException e) {
			System.out.println("Ha ido bien caso no válido (PRUEBA 15)");
		}
	}

	@Test
	public void ListaOrdenadaRemoveTest() {
		ListaOrdenada<Integer> sutRemove = new ListaOrdenada<Integer>();

		sutRemove.add(5);
		//CASO VÁLIDO (PRUEBA 6)
		sutRemove.remove(0);
		assertTrue(sutRemove.size() == 0);; //Al eliminar el único elemento que hay, el tamaño debería de ser 0
		try {
			sutRemove.get(0); //No debería de haber elemento 0 y se tiene que lanzar la excepcion
			fail("No se ha lanzado la excepción");
		} catch(IndexOutOfBoundsException e) {
			System.out.println("Ha ido bien caso no válido (PRUEBA 6 )");
		}
		
		
		sutRemove.add(5);
		sutRemove.add(4);
		sutRemove.add(6); //[4,5,6]
		//CASO VÁLIDO (PRUEBA 7)
		sutRemove.remove(1); //Elimino el 5 = [4,6]
		assertTrue(sutRemove.size() == 2); //El tamaño debería de ser 2
		assertTrue(sutRemove.get(0) == 4); //El primer elemento debería de ser 4
		assertTrue(sutRemove.get(1) == 6); //El segundo y último elemento debería de ser el 6
	}


	@Test
	public void ListaOrdenadaSizeTest() {
		ListaOrdenada<Integer> sutSize = new ListaOrdenada<Integer>();
		//CASO VÁLIDO (PRUEBA 8)
		assertTrue(sutSize.size()== 0);

		sutSize.add(5);
		//CASO VÁLIDO (PRUEBA 9)
		assertTrue(sutSize.size() == 1);

		sutSize.add(6);
		sutSize.add(4);
		//CASO VÁLIDO (PRUEBA 10)
		assertTrue(sutSize.size()==3);
	}

	@Test 
	public void ListaOrdenadaClearTest() {
		ListaOrdenada<Integer> sutClear = new ListaOrdenada<Integer>();

		//CASO VÁLIDO (PRUEBA 11)
		sutClear.clear();
		assertTrue(sutClear.size() == 0);

		sutClear.add(5);
		//CASO VÁLIDO (PRUEBA 12)
		sutClear.clear();//NO ELIMINA
		assertTrue (sutClear.size() == 0);

		sutClear.add(5);
		sutClear.add(6);
		sutClear.add(4);
		//CASO VÁLIDO (PRUEBA 13)
		sutClear.clear();
		System.out.println(sutClear.size()); //Debería de ser 0
		assertTrue (sutClear.size() == 0);

	}

}
