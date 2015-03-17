package org.eda1.actividad04.ejercicio01;

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

public class HashTableTestJUnit4 {
	String directorioEntrada;
	File archivoEntradas;

	@Before
	public void setUp() throws Exception {
		directorioEntrada = System.getProperty("user.dir");

		directorioEntrada = directorioEntrada + File.separator + 
				"src" + File.separator + 
				"org" + File.separator + 
				"eda1" + File.separator + 
				"actividad04" + File.separator +
				"ejercicio01" + File.separator;
	}

	@Test
	public void testProcesarHash01() {
    	Integer[ ] intArray = {15, 4, 9, 14, 8, 5, 7, 19, 2, 11, 20, 13, 18, 3, 16, 6, 1, 12, 10, 17};

		ArrayList<Integer> aL = new ArrayList<Integer>();

		for (int i = 0; i < intArray.length; i++)
			aL.add(intArray[i]);
	
		HashAVL<Integer> hAVLInt = new HashAVL<Integer>(3, 5.0);
		HashArrayList<Integer> hALInt = new HashArrayList<Integer>(3, 5.0);
		
		for (int i = 0; i < aL.size(); i++) {
 			if (!hAVLInt.add(aL.get(i)))
 				System.out.println("ERROR TO ADD in HashAVL");
 			if (!hALInt.add(aL.get(i)))
				System.out.println("ERROR TO ADD in HashArrayList");
 		}

		assertTrue(hAVLInt.tableLength() == 7);
		assertTrue(hALInt.tableLength() == 7);
		assertTrue(hAVLInt.maxEntrySize() == 3);
		assertTrue(hALInt.maxEntrySize() == 3);
		assertTrue(hAVLInt.minEntrySize() == 2);
		assertTrue(hALInt.minEntrySize() == 2);
		assertTrue(hAVLInt.size() / hAVLInt.tableLength() == 2);
		assertTrue(hALInt.size() / hALInt.tableLength() == 2);		
		assertEquals(hAVLInt.toString(), "[7, 14, 1, 8, 15, 2, 9, 16, 3, 10, 17, 4, 11, 18, 5, 12, 19, 6, 13, 20]");
		assertEquals(hALInt.toString(), "[7, 14, 15, 8, 1, 9, 16, 2, 3, 10, 17, 18, 4, 11, 19, 5, 12, 13, 20, 6]");
		
	}
	
	@Test
	public void testProcesarHash02() throws FileNotFoundException {

		String stringArchivoEntrada = "archivoenteros.txt";
		stringArchivoEntrada = directorioEntrada + stringArchivoEntrada;

		ArrayList<Integer> aL = new ArrayList<Integer>();
		aL = readIntegerFile(stringArchivoEntrada);
		
		HashAVL<Integer> hAVLInt = new HashAVL<Integer>(17, 100.0);
		HashArrayList<Integer> hALInt = new HashArrayList<Integer>(17, 100.0);
		
		int totalSize = aL.size() / 4;
		
		for (int i = 0; i < totalSize; i++) {
 			if (!hAVLInt.add(aL.get(i)))
 				System.out.println("ERROR TO ADD in HashAVL");
 			if (!hALInt.add(aL.get(i)))
				System.out.println("ERROR TO ADD in HashArrayList");
 		}

		double sumAVLFind = 0.0;
		for (int i = 0; i < totalSize; i++) {
			sumAVLFind += (double)hAVLInt.find(aL.get(i));
		}
		double sumALFind = 0.0;
		for (int i = 0; i < totalSize; i++) {
			sumALFind += (double)hALInt.find(aL.get(i));
		}

		assertTrue(hAVLInt.size() == 125000);
		assertTrue(hALInt.size() == 125000);
		assertTrue(hAVLInt.tableLength() == 1361);
		assertTrue(hALInt.tableLength() == 1361);
		assertTrue(hAVLInt.maxEntrySize() == 117);
		assertTrue(hALInt.maxEntrySize() == 117);
		assertTrue(hAVLInt.minEntrySize() == 66);
		assertTrue(hALInt.minEntrySize() == 66);
		assertTrue(hAVLInt.size() / hAVLInt.tableLength() == 91);
		assertTrue(hALInt.size() / hALInt.tableLength() == 91);
		assertEquals(String.format(Locale.US, "%.3f", sumAVLFind), "721417.000");
		assertEquals(String.format(Locale.US, "%.3f", sumALFind), "5848728.000");
		assertEquals(String.format(Locale.US, "%.3f", (sumAVLFind / (double)totalSize)), "5.771");
		assertEquals(String.format(Locale.US, "%.3f", (sumALFind / (double)totalSize)), "46.790");
		assertEquals(String.format(Locale.US, "%.3f", (Math.log(sumALFind / (double)totalSize) / Math.log(2.0))), "5.548");
	}

	@Test
	public void testProcesarHash03() throws FileNotFoundException {

		String stringArchivoEntrada = "archivoenteros.txt";
		stringArchivoEntrada = directorioEntrada + stringArchivoEntrada;

		ArrayList<Integer> aL = new ArrayList<Integer>();
		aL = readIntegerFile(stringArchivoEntrada);
		
		HashAVL<Integer> hAVLInt = new HashAVL<Integer>(17, 100.0);
		HashArrayList<Integer> hALInt = new HashArrayList<Integer>(17, 100.0);
		
		int totalSize = aL.size() / 2;
		
		for (int i = 0; i < totalSize; i++) {
 			if (!hAVLInt.add(aL.get(i)))
 				System.out.println("ERROR TO ADD in HashAVL");
 			if (!hALInt.add(aL.get(i)))
				System.out.println("ERROR TO ADD in HashArrayList");
 		}

		double sumAVLFind = 0.0;
		for (int i = 0; i < totalSize; i++) {
			sumAVLFind += (double)hAVLInt.find(aL.get(i));
		}
		double sumALFind = 0.0;
		for (int i = 0; i < totalSize; i++) {
			sumALFind += (double)hALInt.find(aL.get(i));
		}
		
		assertTrue(hAVLInt.size() == 250000);
		assertTrue(hALInt.size() == 250000);
		assertTrue(hAVLInt.tableLength() == 2729);
		assertTrue(hALInt.tableLength() == 2729);
		assertTrue(hAVLInt.maxEntrySize() == 111);
		assertTrue(hALInt.maxEntrySize() == 111);
		assertTrue(hAVLInt.minEntrySize() == 69);
		assertTrue(hALInt.minEntrySize() == 69);
		assertTrue(hAVLInt.size() / hAVLInt.tableLength() == 91);
		assertTrue(hALInt.size() / hALInt.tableLength() == 91);
		assertEquals(String.format(Locale.US, "%.3f", sumAVLFind), "1441462.000");
		assertEquals(String.format(Locale.US, "%.3f", sumALFind), "11635287.000");
		assertEquals(String.format(Locale.US, "%.3f", (sumAVLFind / (double)totalSize)), "5.766");
		assertEquals(String.format(Locale.US, "%.3f", (sumALFind / (double)totalSize)), "46.541");
		assertEquals(String.format(Locale.US, "%.3f", (Math.log(sumALFind / (double)totalSize) / Math.log(2.0))), "5.540");
	}
	
	@Test
	public void testProcesarHash04() throws FileNotFoundException {

		String stringArchivoEntrada = "archivoenteros.txt";
		stringArchivoEntrada = directorioEntrada + stringArchivoEntrada;

		ArrayList<Integer> aL = new ArrayList<Integer>();
		aL = readIntegerFile(stringArchivoEntrada);
		
		HashAVL<Integer> hAVLInt = new HashAVL<Integer>(17, 100.0);
		HashArrayList<Integer> hALInt = new HashArrayList<Integer>(17, 100.0);
		
		int totalSize = aL.size();
		
		for (int i = 0; i < totalSize; i++) {
 			if (!hAVLInt.add(aL.get(i)))
 				System.out.println("ERROR TO ADD in HashAVL");
 			if (!hALInt.add(aL.get(i)))
				System.out.println("ERROR TO ADD in HashArrayList");
 		}

		double sumAVLFind = 0.0;
		for (int i = 0; i < totalSize; i++) {
			sumAVLFind += (double)hAVLInt.find(aL.get(i));
		}
		double sumALFind = 0.0;
		for (int i = 0; i < totalSize; i++) {
			sumALFind += (double)hALInt.find(aL.get(i));
		}

		assertTrue(hAVLInt.size() == 500000);
		assertTrue(hALInt.size() == 500000);
		assertTrue(hAVLInt.tableLength() == 5471);
		assertTrue(hALInt.tableLength() == 5471);
		assertTrue(hAVLInt.maxEntrySize() == 92);
		assertTrue(hALInt.maxEntrySize() == 92);
		assertTrue(hAVLInt.minEntrySize() == 91);
		assertTrue(hALInt.minEntrySize() == 91);
		assertTrue(hAVLInt.size() / hAVLInt.tableLength() == 91);
		assertTrue(hALInt.size() / hALInt.tableLength() == 91);
		assertEquals(String.format(Locale.US, "%.3f", sumAVLFind), "2880409.000");
		assertEquals(String.format(Locale.US, "%.3f", sumALFind), "23098394.000");
		assertEquals(String.format(Locale.US, "%.3f", (sumAVLFind / (double)totalSize)), "5.761");
		assertEquals(String.format(Locale.US, "%.3f", (sumALFind / (double)totalSize)), "46.197");
		assertEquals(String.format(Locale.US, "%.3f", (Math.log(sumALFind / (double)totalSize) / Math.log(2.0))), "5.530");
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
