package org.eda1.examenSegundoParcial.ejercicio01;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Before;
import org.junit.Test;

public class ProcesarDatosExamenParcialTestJUnit4 {

	String directorioEntrada;
	File archivoEntrada;

	@Before
	public void setUp() throws Exception {
	
		directorioEntrada = System.getProperty("user.dir");

		directorioEntrada = directorioEntrada + File.separator + 
				"src" + File.separator + 
				"org" + File.separator + 
				"eda1" + File.separator + 
				"examenSegundoParcial" + File.separator +
				"ejercicio01" + File.separator;
	}

	@Test
	public void testProcesarDatosExamenSegundoParcial() {
		String stringArchivoEntrada = "EmpresasProyectosCiudadesDirecciones.txt";
		stringArchivoEntrada = directorioEntrada + stringArchivoEntrada;

		ProcesarDatos procesarDatos = new ProcesarDatos();

		procesarDatos.cargarArchivo(stringArchivoEntrada);
		
		assertTrue(procesarDatos.size() == 6);

		String cadenaSalida = "";
		TreeMap<String, ArrayList<String>> salidaConsulta1 = procesarDatos.consulta1();
	    assertTrue(salidaConsulta1.size() == 4);
	    for (Map.Entry<String, ArrayList<String>> entry : salidaConsulta1.entrySet())
  			cadenaSalida += entry.getKey() + ": " + entry.getValue() + "\n";

		String cadenaEsperada = "";
		cadenaEsperada += "Adobe: [Photoshop<Madrid.Spain.Europe(Castellana, Chamartin, Gran_Via, Juan_Bravo, Serrano, Velazquez)>]" + "\n";
		cadenaEsperada += "Apple: [Mavericks<Berlin.Germany.Europe(Alexanderplatz, Kurfurstendamm, Unter_den_Linden)>]" + "\n";
		cadenaEsperada += "Microsoft: [PowerPoint<Paris.France.Europe(Champs_Elysees, Haussmann, Montaigne, Passy)>]" + "\n";
		cadenaEsperada += "Oracle: [Java<Barcelona.Spain.Europe(Bergara, La_Rambla)>, Java<London.UK.Europe(Fleet_Street, Harley_Street)>]" + "\n";
		assertEquals(cadenaSalida, cadenaEsperada);
				
		cadenaSalida = "";
		TreeMap<String, ArrayList<String>> salidaConsulta2 = procesarDatos.consulta2();
	    assertTrue(salidaConsulta2.size() == 3);
	    for (Map.Entry<String, ArrayList<String>> entry : salidaConsulta2.entrySet()) {
  			cadenaSalida += entry.getKey() + ", " + entry.getValue().get(0) + ": {";
  			for (int i = 1; i < entry.getValue().size(); i++)
  				if (i != entry.getValue().size() - 1)
  					cadenaSalida += entry.getValue().get(i) + "; ";
  				else
  					cadenaSalida += entry.getValue().get(i);
  			cadenaSalida += "}\n";
  		}
	  
	    cadenaEsperada = "";
	    cadenaEsperada += "Hill_Street, Los_Angeles: {(Apple, Xcode); (Borland, Delphi)}" + "\n";
	    cadenaEsperada += "NW_7th_Street, Miami: {(Microsoft, OutLook); (Oracle, Database_11g)}" + "\n";
	    cadenaEsperada += "Wall_Street, New_York: {(Apple, IOS); (Borland, JBuilder); (Google, Chrome)}" + "\n";
		assertEquals(cadenaSalida, cadenaEsperada);

	}

}
