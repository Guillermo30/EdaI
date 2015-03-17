package org.eda1.examenParcialGrupoA.ejercicio02;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.eda1.estructurasdedatos.AVLTree;
import org.junit.Before;
import org.junit.Test;

public class ProcessTextsWFTestJUnit4 {

	String inputDirectory;
	ProcessText processTextsWF;

	@Before
	public void setUp() throws Exception {
		processTextsWF = new ProcessText();
		
		inputDirectory = System.getProperty("user.dir");

		inputDirectory = inputDirectory + File.separator + 
				"src" + File.separator + 
				"org" + File.separator + 
				"eda1" + File.separator + 
				"examenParcialGrupoA" + File.separator +
				"ejercicio02" + File.separator;
	}

	@Test
	public void testProcesarDatosEPCB() throws FileNotFoundException {
		
		String inputFile = "texto.txt";
		inputFile = inputDirectory + inputFile;
		
		AVLTree<WordFrequency> avlWords = processTextsWF.loadFile(inputFile);
				
		assertTrue(avlWords.size() == 39);
		
		assertTrue(processTextsWF.getFrecuencyOfWord("que") == 3);
		
		ArrayList<String> aL = processTextsWF.getWordsWithFrequency(2);
		
		assertTrue(aL.size() == 6);
		
		String salida = "";
		for (int i = 0; i < aL.size(); i++)
			salida += aL.get(i) + "\n";

		String salidaEsperada = "";
		salidaEsperada += "asignatura" + "\n";
		salidaEsperada += "de" + "\n";
		salidaEsperada += "eda" + "\n";
		salidaEsperada += "la" + "\n";
		salidaEsperada += "más" + "\n";
		salidaEsperada += "tengo" + "\n";
		assertEquals(salida, salidaEsperada);
		
	}

}
