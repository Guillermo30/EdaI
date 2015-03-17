package org.eda1.tema04.Hash;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Principal
{
	public static void main(String[] args) {
				
		// initial values in the tree
		Hash<Integer> hInt = new Hash<Integer>();

		Random rnd = new Random();
		
  		// build the initial tree
		Integer x = null;
		for (int i = 0; i < 10; i++) {
			x = rnd.nextInt(100);
 			hInt.add(x);
		}

		System.out.println(hInt.size());
		System.out.println(hInt.toString());
		
		hInt.remove((Integer)x);
		System.out.println(hInt.size());
		System.out.println(hInt.toString());
		
		
		String[] strArr = {"Hola", "que", "tal", "estas", "amigo", "mio", "y",
				"cuanto", "tiempo", "hace", "que", "no", "te", "veo", "por", "aqui"};	    
		
		Hash<String> hStr = new Hash<String>();

  		// build the initial tree
		for (int i = 0; i < strArr.length; i++)
 			hStr.add(strArr[i]);

		System.out.println(hStr.size());
		System.out.println(hStr.toString());
		
		hStr.remove("veo");
		System.out.println(hStr.size());
		System.out.println(hStr.toString());
		
    }
		
}
