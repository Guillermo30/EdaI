package org.eda1.actividadesSeptiembre.actividad03;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

import org.junit.Before;
import org.junit.Test;

public class HashTableSeptiembre2014TestJUnit4 {
	String directorioEntrada;
	File archivoEntradas;

	@Before
	public void setUp() throws Exception {
		directorioEntrada = System.getProperty("user.dir");

		directorioEntrada = directorioEntrada + File.separator + 
				"src" + File.separator + 
				"org" + File.separator + 
				"eda1" + File.separator + 
				"actividadesSeptiembre" + File.separator +
				"actividad03" + File.separator;
	}

	@Test
	public void testProcesarHashTable() throws FileNotFoundException {

		String stringArchivoEntrada = "archivoenteros.txt";
		stringArchivoEntrada = directorioEntrada + stringArchivoEntrada;

		ArrayList<Integer> aL = new ArrayList<Integer>();
		aL = readIntegerFile(stringArchivoEntrada);
		
		assertTrue(aL.size() == 500000);
		
		HashTableExponentProbing<Integer> hTLPInt = new HashTableExponentProbing<Integer>(1);
		HashTableExponentProbing<Integer> hTQPInt = new HashTableExponentProbing<Integer>(2);
		HashTableExponentProbing<Integer> hTCPInt = new HashTableExponentProbing<Integer>(3);
		HashTableExponentProbing<Integer> hTFoPInt = new HashTableExponentProbing<Integer>(4);
		HashTableExponentProbing<Integer> hTFiPInt = new HashTableExponentProbing<Integer>(5);

		int totalSize = aL.size() / 4;
		
		for (int i = 0; i < totalSize; i++) {
 			if (!hTLPInt.add(aL.get(i))) {
 				System.out.println("ERROR TO ADD in HashLP");
 				return;
 			}
 			if (!hTQPInt.add(aL.get(i))) {
 				System.out.println("ERROR TO ADD in HashQP");
 				return;
 			}
 			if (!hTCPInt.add(aL.get(i))) {
 				System.out.println("ERROR TO ADD in HashCP");
 				return;
 			}
 			if (!hTFoPInt.add(aL.get(i))) {
 				System.out.println("ERROR TO ADD in HashFoP");
 				return;
 			}
 			if (!hTFiPInt.add(aL.get(i))) {
 				System.out.println("ERROR TO ADD in HashFiP");
 				return;
 			}
 		}

		assertTrue(hTLPInt.capacity() == 317701);
		assertTrue(hTLPInt.getNumberOfCollisions() == 78762);
		assertTrue(hTQPInt.capacity() == 317701);
		assertTrue(hTQPInt.getNumberOfCollisions() == 78112);
		assertTrue(hTCPInt.capacity() == 317701);
		assertTrue(hTCPInt.getNumberOfCollisions() == 77527);
		assertTrue(hTFoPInt.capacity() == 317701);
		assertTrue(hTFoPInt.getNumberOfCollisions() == 77612);
		assertTrue(hTFiPInt.capacity() == 317701);
		assertTrue(hTFiPInt.getNumberOfCollisions() == 77841);

	}
		
	private static ArrayList<Integer> readIntegerFile(String archivoEntrada) {
		ArrayList<Integer> aL = new ArrayList<Integer>();		
		
		try {
			BufferedReader in = new BufferedReader(new FileReader(archivoEntrada));
			String cadena;
			int i = 0;
			while ((cadena = in.readLine()) != null) {
				aL.add(Integer.parseInt(cadena));
			}
	
			in.close();
		} catch (IOException e) { }

		return aL;
	}

}
