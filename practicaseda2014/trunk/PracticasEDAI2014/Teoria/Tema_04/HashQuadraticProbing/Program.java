package org.eda1.HashQuadraticProbing;

import java.util.Random;

public class Program {
	
	public static void main(String[] args) {
				
		HashTableQuadraticProbing<Integer> hTInt = new HashTableQuadraticProbing<Integer>();

		Random rnd = new Random();
		
  		// build the initial tree
		Integer x = null;
		for (int i = 0; i < 10000; i++) {
			x = rnd.nextInt(100000);
			//System.out.println("  " + x);
 			hTInt.add(x);
		}

		System.out.println(hTInt.capacity());
		System.out.println(hTInt.size());
		System.out.println(hTInt.numberOfOccupiedEntries());
		//System.out.println(hTInt.toString());
		
		hTInt.remove((Integer)x);
		System.out.println(hTInt.capacity());
		System.out.println(hTInt.size());
		System.out.println(hTInt.numberOfOccupiedEntries());
		//System.out.println(hTInt.toString());
		
		String[] strArr = {"Hola", "que", "tal", "estas", "amigo", "mio", "y",
				"cuanto", "tiempo", "hace", "que", "no", "te", "veo", "por", "aqui"};	    
		
		HashTableQuadraticProbing<String> hTStr = new HashTableQuadraticProbing<String>();

  		// build the initial hash table
		for (int i = 0; i < strArr.length; i++)
 			hTStr.add(strArr[i]);

		System.out.println(hTStr.capacity());
		System.out.println(hTStr.size());
		System.out.println(hTStr.numberOfOccupiedEntries());
		System.out.println(hTStr.toString());
		
		hTStr.remove("veo");
		System.out.println(hTStr.capacity());
		System.out.println(hTStr.size());
		System.out.println(hTStr.numberOfOccupiedEntries());
		System.out.println(hTStr.toString());
		
    }
		
}
