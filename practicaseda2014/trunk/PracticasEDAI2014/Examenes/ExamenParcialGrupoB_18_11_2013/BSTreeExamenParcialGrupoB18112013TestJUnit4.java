package org.eda1.examenParcialGrupoB.ejercicio02;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BSTreeExamenParcialGrupoB18112013TestJUnit4 {

	private BSTree<Integer> tree;
	
	@Before
	public void setUp() throws Exception {
		Integer[] arr = {120, 87, 43, 65, 140, 99, 17, 130, 22, 150, 56, 100, 125, 145, 135};	    
		tree = new BSTree<Integer>();

		for (int i = 0;i < arr.length; i++)
 			tree.add(arr[i]);		
	}
	
	@Test
	public void testRemoveLeaves() {
		assertEquals(tree.toStringInorder(), "17  22  43  56  65  87  99  100  120  125  130  135  140  145  150  ");
		assertTrue(tree.size() == 15);
		tree.removeLeaves();
		assertTrue(tree.size() == 9);
		assertEquals(tree.toStringInorder(), "17  43  65  87  99  120  130  140  150  ");
	}	
		
}
