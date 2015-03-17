package org.eda1.actividad04.ejercicio02;

import static org.junit.Assert.*;

import java.io.File;
import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

public class SpellCheckerTestJUnit4 {

	String inputDirectory;
	
	@Before
	public void setUp() throws Exception {
		inputDirectory = System.getProperty("user.dir");

		inputDirectory = inputDirectory 
				+ File.separator + "src"
				+ File.separator + "org" 
				+ File.separator + "eda1"
				+ File.separator + "actividad04" 
				+ File.separator + "ejercicio02"
				+ File.separator;		
	}

	@Test
	public void testSpellChecker()  {
		
		SpellCheckerTester spellChecker = new SpellCheckerTester();
		
		String archivoDiccionario = "dictionary.txt";
		archivoDiccionario = inputDirectory + archivoDiccionario;
		
		spellChecker.loadDictionary(archivoDiccionario);
		
		assertTrue(spellChecker.sizeDictionary() == 58109);

		String archivoDocumento = "document.txt";
		archivoDocumento = inputDirectory + archivoDocumento;

		spellChecker.loadDocument(archivoDocumento);
		
		assertTrue(spellChecker.isInDictionary("structure"));
		
		assertFalse(spellChecker.isInDictionary("organization"));
		
		LinkedList<String> differences = spellChecker.showDifferences();

		assertTrue(differences.size() == 3);
		
		assertTrue(differences.contains("organization"));
		
		spellChecker.extendDictionary(differences);
		
		assertTrue(spellChecker.isInDictionary("organization"));

		assertTrue(spellChecker.sizeDictionary() == 58112);
		
	}

}
