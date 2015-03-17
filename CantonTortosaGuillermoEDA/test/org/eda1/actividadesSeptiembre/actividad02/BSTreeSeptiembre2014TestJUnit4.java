package org.eda1.actividadesSeptiembre.actividad02;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Before;
import org.junit.Test;

public class BSTreeSeptiembre2014TestJUnit4 {

	private BSTree<Integer> tree;
	
	@Before
	public void setUp() throws Exception {
		Integer[] arr = {120, 87, 43, 65, 140, 99, 17, 130, 22, 150, 56, 100, 125, 145, 135};	    
		tree = new BSTree<Integer>();

		for (int i = 0;i < arr.length; i++)
 			tree.add(arr[i]);		
	}

	@Test
	public void testBSTreeEquals() {
		BSTree<Integer> otherTree = new BSTree<Integer>(tree);
		
		assertTrue(tree.equals(otherTree));
		assertTrue(otherTree.size() == 15);
		otherTree.add(new Integer(155));
		assertTrue(otherTree.size() == 16);
		assertFalse(tree.equals(otherTree));
	}
	
	@Test
	public void testRemoveSmaller() {
		assertEquals(tree.toStringInorder(), "17  22  43  56  65  87  99  100  120  125  130  135  140  145  150  ");
		tree.removeSmaller(99);
		assertEquals(tree.toStringInorder(), "99  100  120  125  130  135  140  145  150  ");
		tree.removeSmaller(131);
		assertEquals(tree.toStringInorder(), "135  140  145  150  ");
	}	
	
	@Test
	public void testRemoveLeaves() {
		assertEquals(tree.toStringInorder(), "17  22  43  56  65  87  99  100  120  125  130  135  140  145  150  ");
		assertTrue(tree.size() == 15);
		tree.removeLeaves();
		assertTrue(tree.size() == 9);
		assertEquals(tree.toStringInorder(), "17  43  65  87  99  120  130  140  150  ");
	}	
	
	@Test
	public void testSumOfLargerThanOrEqualTo() {
		assertTrue(tree.sumOfSmallerThanOrEqualTo(120) == 609);
		assertTrue(tree.sumOfSmallerThanOrEqualTo(119) == 489);
		tree.remove(65);
		assertTrue(tree.sumOfSmallerThanOrEqualTo(120) == 544);
		tree.add(30);
		assertTrue(tree.sumOfSmallerThanOrEqualTo(119) == 454);
	}	

	@Test
	public void testCountOutOfRange() {
		assertTrue(tree.countOutOfRange(88, 129) == 11);
		assertTrue(tree.countOutOfRange(87, 129) == 10);
		assertTrue(tree.countOutOfRange(87, 130) == 9);
		tree.remove(65);
		tree.remove(140);
		assertTrue(tree.countOutOfRange(87, 130) == 7);
	}	
	
	@Test
	public void testCountNodesBetweenLevels() {
		assertTrue(tree.countNodesBetweenLevels(1, 2) == 6);
		assertTrue(tree.countNodesBetweenLevels(1, 3) == 12);
		tree.remove(100);
		assertTrue(tree.countNodesBetweenLevels(1, 3) == 11);
	}	
		
	@Test
	public void testToStringReverseBreadthFirstTraversal() {
		assertEquals(tree.toStringBreadthFirstTraversal(), "120  87  140  43  99  130  150  17  65  100  125  135  145  22  56  ");
		assertEquals(tree.toStringReverseBreadthFirstTraversal(), "56  22  145  135  125  100  65  17  150  130  99  43  140  87  120  ");
		tree.remove(140);
		tree.remove(120);
		assertEquals(tree.toStringBreadthFirstTraversal(), "125  87  145  43  99  130  150  17  65  100  135  22  56  ");
		assertEquals(tree.toStringReverseBreadthFirstTraversal(), "56  22  135  100  65  17  150  130  99  43  145  87  125  ");
	}
		
}
