package org.eda1.actividadesSeptiembre.actividad01;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;

import org.eda1.utilidades.Less;
import org.junit.Before;
import org.junit.Test;

public class HeapSeptiembre2014TestJUnit4 {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testHeap() {
		Less<Integer> less = new Less<Integer>();
    	Heap<Integer> heap = new Heap<Integer>(less);

    	Integer[ ] intArray = {15, 13, 11, 9, 7, 5, 3, 1, 14, 12, 10, 8, 6, 4, 2};

		ArrayList<Integer> theArray = new ArrayList<Integer>();
		for (int i = 0; i < intArray.length; i++)
			theArray.add(intArray[i]);

    	for (int i = 0; i < theArray.size(); i++)
			heap.assign(i, theArray.get(i));

    	String content = heap.toString();
    	assertEquals(content, "[15, 13, 11, 9, 7, 5, 3, 1, 14, 12, 10, 8, 6, 4, 2]");
    	
    	assertTrue(heap.isEmpty() == false);
    	assertTrue(heap.size() == 15);
    	assertTrue(heap.isHeap() == false);
    	
    	heap.makeHeap();
    	
    	assertTrue(heap.isHeap() == true);

    	content = heap.toString();
    	assertEquals(content, "[1, 7, 2, 9, 10, 5, 3, 13, 14, 12, 15, 8, 6, 4, 11]");
    	
    	Iterator<Integer> bFIter = heap.breadthFirstIterator();
    	content = "";
		while(bFIter.hasNext()) {
			Integer value = bFIter.next();
			content = content + value + " ";
		}
    	assertEquals(content, "1 7 2 9 10 5 3 13 14 12 15 8 6 4 11 ");

    	Iterator<Integer> dFIter = heap.depthFirstIterator();
    	content = "";
		while(dFIter.hasNext()) {
			Integer value = dFIter.next();
			content = content + value + " ";
		}
    	assertEquals(content, "13 9 14 7 12 10 15 1 8 5 6 2 4 3 11 ");

    	theArray.clear();
    	for (int i = 25; i > 0; i--)
			theArray.add(i);

    	Heap<Integer> otherHeap = new Heap<Integer>(less);
    	
    	for (int i = 0; i < theArray.size(); i++)
			otherHeap.assign(i, theArray.get(i));
    	assertTrue(otherHeap.isHeap() == false);    	
    	otherHeap.makeHeap();
    	assertTrue(otherHeap.isHeap() == true);
    	
    	Iterator<Integer> otherBFIter = otherHeap.breadthFirstIterator();
    	content = "";
		while(otherBFIter.hasNext()) {
			Integer value = otherBFIter.next();
			content = content + value + " ";
		}
    	assertEquals(content, "1 3 2 7 4 13 11 9 8 5 15 14 25 12 19 10 18 22 17 6 16 21 24 20 23 ");

    	Iterator<Integer> otherDFIter = otherHeap.depthFirstIterator();
    	content = "";
		while(otherDFIter.hasNext()) {
			Integer value = otherDFIter.next();
			content = content + value + " ";
		}
    	assertEquals(content, "10 9 18 7 22 8 17 3 6 5 16 4 21 15 24 1 20 14 23 13 25 2 12 11 19 ");

	}

}
