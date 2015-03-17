package org.eda1.examenParcialGrupoA.ejercicio01;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.eda1.utilidades.Less;
import org.junit.Before;
import org.junit.Test;

public class HeapExamenParcialGrupoATestJUnit4 {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testHeap() {
		Less<Integer> less = new Less<Integer>();
    	Heap<Integer> heap = new Heap<Integer>(less);

    	Integer[ ] intArray = {17, 30, 15, 12, 9, 2, 8, 20, 7, 6, 5, 3};

		ArrayList<Integer> theArray = new ArrayList<Integer>();
		for (int i = 0; i < intArray.length; i++)
			theArray.add(intArray[i]);

    	for (int i = 0; i < theArray.size(); i++)
			heap.add(theArray.get(i));

    	String content = heap.toString();
    	assertEquals(content, "[2, 5, 3, 12, 6, 8, 9, 30, 20, 15, 7, 17]");
    	
    	assertTrue(heap.isHeap() == true);
    	
    	ArrayList<Integer> aLOfSumValuesOfBranches = heap.sumValuesOfBranches();
    	
    	assertTrue(aLOfSumValuesOfBranches.size() == 6);
    	
    	String salida = "";
    	for (int i = 0; i < aLOfSumValuesOfBranches.size(); i++)
    		salida += aLOfSumValuesOfBranches.get(i) + "\n";
    	
		String salidaEsperada = "";
		salidaEsperada += "14" + "\n";
		salidaEsperada += "49" + "\n";
		salidaEsperada += "39" + "\n";
		salidaEsperada += "28" + "\n";
		salidaEsperada += "20" + "\n";
		salidaEsperada += "30" + "\n";
		assertEquals(salida, salidaEsperada);
    	
	}

}
