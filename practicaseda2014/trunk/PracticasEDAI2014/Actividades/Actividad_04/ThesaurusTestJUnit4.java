package org.eda1.actividad04.ejercicio03;

import static org.junit.Assert.*;

import java.io.File;
import java.util.LinkedList;
import java.util.TreeSet;

import org.junit.Before;
import org.junit.Test;

public class ThesaurusTestJUnit4 {

	String directorioEntrada;
	
	@Before
	public void setUp() throws Exception {
		directorioEntrada = System.getProperty("user.dir");

		directorioEntrada = directorioEntrada 
				+ File.separator + "src"
				+ File.separator + "org" 
				+ File.separator + "eda1"
				+ File.separator + "actividad04" 
				+ File.separator + "ejercicio03"
				+ File.separator;
	}

	@Test
	public void testThesaurus()  {
		
		ThesaurusTester thesaurus = new ThesaurusTester();
		
		String thesaurusFile = "Thesaurus.txt";
		thesaurusFile = directorioEntrada + thesaurusFile;
		
		thesaurus.constructThesaurus(thesaurusFile);

		assertTrue(thesaurus.size() == 12);

    	assertTrue(thesaurus.isSynonymousOf("limpio", "aseado"));
    	
    	assertFalse(thesaurus.isSynonymous("aseados"));

    	assertTrue(thesaurus.hasSynonymous("verdadero"));

    	assertTrue(thesaurus.size("trabajador") == 7);
    	
    	TreeSet<String> ts1 = thesaurus.getSynonymous("limpio"); 
    	TreeSet<String> ts2 = new TreeSet<String>();
    	ts2.add("acicalado");
    	ts2.add("arreglado");
    	ts2.add("aseado");
    	ts2.add("atildado");
    	ts2.add("compuesto");
    	ts2.add("engalanado");
    	ts2.add("pulcro");
    	assertEquals(ts1, ts2);

    	thesaurus.add("acabado logrado terminado conseguido rematado");
		assertTrue(thesaurus.size() == 13);
    	assertFalse(thesaurus.isSynonymousOf("acabado", "remartado"));
    	assertTrue(thesaurus.size("acabado") == 4);
    	
    	thesaurus.add("acabado", "completado");
    	assertTrue(thesaurus.size("acabado") == 5);
    	
    	thesaurus.remove("acabado", "complertado");
    	assertTrue(thesaurus.size("acabado") == 5);
    	
    	thesaurus.remove("ganador", "triunfador");
    	assertTrue(thesaurus.size("ganador") == 6);
    	
    	thesaurus.remove("acabado");
		assertTrue(thesaurus.size() == 12);
		
    	assertTrue(thesaurus.isSynonymousOf("falso", "embustero"));
    	assertTrue(thesaurus.size("falso") == 4);
    	LinkedList<String> synonyms = new LinkedList<String>();
    	synonyms.add("erróneo");
    	synonyms.add("inexacto");
    	synonyms.add("infundado");
    	synonyms.add("incorrecto");
    	synonyms.add("amañado");
		thesaurus.update("falso", synonyms);
    	assertFalse(thesaurus.isSynonymousOf("falso", "embustero"));
    	assertFalse(thesaurus.size("falso") == 4);
	}

}
