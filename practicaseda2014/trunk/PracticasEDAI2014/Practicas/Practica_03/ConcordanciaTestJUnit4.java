package org.eda1.practica03.ejercicio03;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class ConcordanciaTestJUnit4 {

	String directorioEntrada;
	Concordancia concordancia;

	@Before
	public void setUp() throws Exception {
		directorioEntrada = System.getProperty("user.dir");

		directorioEntrada = directorioEntrada + File.separator
				+ "src" + File.separator
				+ "org" + File.separator
				+ "eda1" + File.separator
				+ "practica03" + File.separator
				+ "ejercicio03" + File.separator;
		
		String expresion = "[a-zA-Z][a-zA-Z0-9]*";
		concordancia = new Concordancia(expresion);
	}

	@Test
	public void testProcesarConcordanciaCadena(){
		
		String cadena = "Hola Mundo";
		
		String cadenaConcordancia = concordancia.concordance(cadena);
		
		ArrayList<String> salidaEsperada = new ArrayList<String>();
		salidaEsperada.add("Hola          1:    1");
		salidaEsperada.add("Mundo         1:    1");
		
		for (String string : salidaEsperada) {
			assertTrue(cadenaConcordancia.contains(string));
		}
		
	}
	
	@Test
	public void testProcesarConcordanciaArchivo() throws IOException {
		
		String archivoEntrada = directorioEntrada + "concord.txt";
		File file = new File(archivoEntrada);
		String cadenaConcordancia = concordancia.concordance(file);
		
		ArrayList<String> salidaEsperada = new ArrayList<String>();
		salidaEsperada.add("a             2:    2    8");
		salidaEsperada.add("b             2:    2    8");
		salidaEsperada.add("double        1:    2");
		salidaEsperada.add("else          1:    6");
		salidaEsperada.add("hypotenuse    2:    2    8");
		salidaEsperada.add("if            1:    4");
		salidaEsperada.add("int           1:    1");
		salidaEsperada.add("m             3:    1    5    7");
		salidaEsperada.add("n             4:    1    4    5    7");
		salidaEsperada.add("sqrt          1:    8");
		
		for (String string : salidaEsperada) {
			assertTrue(cadenaConcordancia.contains(string));
		}
		
		String cadenaNewConcordancia = concordancia.newConcordance(file);
		salidaEsperada.clear();
		salidaEsperada.add("a             3:    2(1)    8(2)");
		salidaEsperada.add("b             3:    2(1)    8(2)");
		salidaEsperada.add("double        1:    2(1)");
		salidaEsperada.add("else          1:    6(1)");
		salidaEsperada.add("hypotenuse    2:    2(1)    8(1)");
		salidaEsperada.add("if            1:    4(1)");
		salidaEsperada.add("int           1:    1(1)");
		salidaEsperada.add("m             4:    1(1)    5(1)    7(2)");
		salidaEsperada.add("n             4:    1(1)    4(1)    5(1)    7(1)");
		salidaEsperada.add("sqrt          1:    8(1)");

		for (String string : salidaEsperada) {
			assertTrue(cadenaNewConcordancia.contains(string));
		}

	}

}
