package org.eda1.examenSegundoParcial.ejercicio02;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.eda1.examenSegundoParcial.ejercicio02.RoadNetwork;
import org.junit.Before;
import org.junit.Test;

public class RoadNetworkExamenParcialTestJUnit4 {

	String directorioEntrada;
	
	@Before
	public void setUp() throws Exception {
		directorioEntrada = System.getProperty("user.dir");

		directorioEntrada = directorioEntrada 
				+ File.separator + "src"
				+ File.separator + "org" 
				+ File.separator + "eda1"
				+ File.separator + "examenSegundoParcial"
				+ File.separator + "ejercicio02"
				+ File.separator;		
	}

	@Test
	public void testNetworkSpanishRoadExamenSegundoParcial() throws FileNotFoundException {
		String graphFile = "graphSpain.txt";
		graphFile = directorioEntrada + graphFile;
		
		RoadNetwork<String> net = RoadNetwork.readRoadNetwork(graphFile);

		assertTrue(net.numberOfVertices() == 21);
		assertTrue(net.numberOfEdges() == 58);
		
		String cadenaSalida = "";
		TreeMap<Double, ArrayList<String>> resultIntervalPaths;
		resultIntervalPaths = net.intervalPaths("Almeria", "Corunya", 2500.0, 2600.0);
		assertTrue(resultIntervalPaths.size() == 8);
		for (Map.Entry<Double, ArrayList<String>> entry : resultIntervalPaths.entrySet())
			cadenaSalida += entry.getKey() + " => " + entry.getValue() + "\n";
				
		String cadenaEsperada = "";
		cadenaEsperada += "2518.0 => [Almeria, Granada, Murcia, Valencia, Albacete, Madrid, Zaragoza, Bilbao, Valladolid, Corunya]" + "\n";
		cadenaEsperada += "2537.0 => [Almeria, Murcia, Granada, Sevilla, Jaen, Madrid, Bilbao, Valladolid, Vigo, Corunya]" + "\n";
		cadenaEsperada += "2542.0 => [Almeria, Granada, Sevilla, Huelva, Badajoz, Madrid, Zaragoza, Bilbao, Valladolid, Corunya]" + "\n";
		cadenaEsperada += "2549.0 => [Almeria, Murcia, Valencia, Barcelona, Zaragoza, Bilbao, Madrid, Valladolid, Vigo, Corunya]" + "\n";
		cadenaEsperada += "2565.0 => [Almeria, Murcia, Valencia, Barcelona, Zaragoza, Madrid, Bilbao, Valladolid, Corunya]" + "\n";
		cadenaEsperada += "2568.0 => [Almeria, Granada, Murcia, Albacete, Valencia, Barcelona, Zaragoza, Bilbao, Valladolid, Vigo, Corunya]" + "\n";
		cadenaEsperada += "2577.0 => [Almeria, Murcia, Albacete, Valencia, Barcelona, Zaragoza, Bilbao, Madrid, Valladolid, Corunya]" + "\n";
		cadenaEsperada += "2590.0 => [Almeria, Granada, Murcia, Valencia, Albacete, Madrid, Zaragoza, Bilbao, Valladolid, Vigo, Corunya]" + "\n";
		assertEquals(cadenaSalida, cadenaEsperada);
		
	}

}
