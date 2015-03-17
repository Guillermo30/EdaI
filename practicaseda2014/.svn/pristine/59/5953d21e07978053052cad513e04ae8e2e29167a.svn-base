package org.eda1.practica03.ejercicio02;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class ProcesarDatosTestJUnit4 {

	String directorioEntrada;
	File archivoEntrada;

	@Before
	public void setUp() throws Exception {
	
		directorioEntrada = System.getProperty("user.dir");

		directorioEntrada = directorioEntrada + File.separator + 
				"src" + File.separator + 
				"org" + File.separator + 
				"eda1" + File.separator + 
				"practica03" + File.separator +
				"ejercicio02" + File.separator;
	}

	@Test
	public void testProcesarDatos() {
		String stringArchivoEntrada = "masNuevasEmpresasProyectosCiudades.txt";
		stringArchivoEntrada = directorioEntrada + stringArchivoEntrada;

		ProcesarDatos procesarDatos = new ProcesarDatos();

		procesarDatos.cargarArchivo(stringArchivoEntrada);
		
		assertTrue(procesarDatos.size() == 7);
		assertTrue(procesarDatos.numeroProyectosEmpresa("Google") == 6);
		assertTrue(procesarDatos.numeroCiudadesProyecto("Earth") == 8);
		assertTrue(procesarDatos.numeroCiudadesEmpresa("Google") == 20);

		String salida = procesarDatos.devolverEmpresasProyectosCiudades();
		String cadenaSalida = "";
		cadenaSalida = cadenaSalida + "Adobe: Flash<Boston, Charleston, Washington>; Illustrator<Miami, New_Orleans, Sacramento>; Photoshop<Houston, San_Antonio, Seattle>" + "\n";
		cadenaSalida = cadenaSalida + "Apple: IOS<Dallas, Los_Angeles, Miami, New_York, Washington>; Xcode<Atlanta, Berkeley, Detroit, Houston, Miami, Stanford, Washington>; iWork<Atlanta, Chicago, Los_Angeles, Miami, New_Orleans, New_York, Stanford>" + "\n";
		cadenaSalida = cadenaSalida + "Borland: C++Builder<Berkeley, Ohio, Portland, Washington, Wisconsin>; Delphi<Chicago, Detroit, Jackson, Miami, Milwaukee>; JBuilder<Denver, Miami, Santa_Fe, Tucson>" + "\n";
		cadenaSalida = cadenaSalida + "Google: Chrome<Berkeley, Denver, Houston, New_Jersey, New_York, Orlando>; Earth<Atlanta, Boston, Los_Angeles, Miami, New_Jersey, Ohio, Philadelphia, Washington>; Gmail<Miami, New_York, Phoenix, Portland, Wisconsin>; Maps<Detroit, Miami, New_York, Stanford, Washington>; Talk<Detroit, Los_Angeles, Miami, Washington>; Translate<Dallas, Miami, New_York, Sacramento>" + "\n";
		cadenaSalida = cadenaSalida + "Microsoft: Excel<Las_Vegas, Los_Angeles, Phoenix, Sacramento, San_Francisco>; OutLook<Atlanta, Miami, New_Jersey, New_York, Washington>; PowerPoint<Augusta, Dallas, Helena, Miami, Seattle, Washington>; VisualC++<Miami, New_York, Philadelphia, Stanford, Washington>; Word<Maryland, Memphis, Miami, New_York, Orlando, Washington>" + "\n";
		cadenaSalida = cadenaSalida + "Oracle: Database_11g<Augusta, Denver, Los_Angeles, Miami, Redwood_City>; Java<Dallas, Miami, New_York, Sacramento, Washington>; Solaris<Atlanta, Berkeley, New_York, Washington>" + "\n";
		cadenaSalida = cadenaSalida + "Ramsoft: EZJava<New_York, Stanford, Washington>" + "\n";		
		assertEquals(salida, cadenaSalida);

		ArrayList<String> empresas = procesarDatos.devolverEmpresas();
		assertTrue(empresas.size() == 7);
		
		ArrayList<String> proyectos = procesarDatos.devolverProyectos();
		assertTrue(proyectos.size() == 24);
		
		ArrayList<String> ciudades = procesarDatos.devolverCiudades();
		assertTrue(ciudades.size() == 36);

		ArrayList<String> empresasCiudad = procesarDatos.devolverEmpresasCiudad("Miami");
		assertTrue(empresasCiudad.size() == 6);
		salida = "";
		for (int i = 0; i < empresasCiudad.size(); i++)
			salida = salida + empresasCiudad.get(i) + "\n";
		cadenaSalida = "";
		cadenaSalida = cadenaSalida + "Adobe" + "\n";
		cadenaSalida = cadenaSalida + "Apple" + "\n";
		cadenaSalida = cadenaSalida + "Borland" + "\n";
		cadenaSalida = cadenaSalida + "Google" + "\n";
		cadenaSalida = cadenaSalida + "Microsoft" + "\n";
		cadenaSalida = cadenaSalida + "Oracle" + "\n";
		assertEquals(salida, cadenaSalida);
		
		ArrayList<String> proyectosCiudad = procesarDatos.devolverProyectosCiudad("Washington");
		assertTrue(proyectosCiudad.size() == 14);
		salida = "";
		for (int i = 0; i < proyectosCiudad.size(); i++)
			salida = salida + proyectosCiudad.get(i) + "\n";
		cadenaSalida = "";
		cadenaSalida = cadenaSalida + "Flash" + "\n";
		cadenaSalida = cadenaSalida + "IOS" + "\n";
		cadenaSalida = cadenaSalida + "Xcode" + "\n";
		cadenaSalida = cadenaSalida + "C++Builder" + "\n";
		cadenaSalida = cadenaSalida + "Earth" + "\n";
		cadenaSalida = cadenaSalida + "Maps" + "\n";
		cadenaSalida = cadenaSalida + "Talk" + "\n";
		cadenaSalida = cadenaSalida + "OutLook" + "\n";
		cadenaSalida = cadenaSalida + "PowerPoint" + "\n";
		cadenaSalida = cadenaSalida + "VisualC++" + "\n";
		cadenaSalida = cadenaSalida + "Word" + "\n";
		cadenaSalida = cadenaSalida + "Java" + "\n";
		cadenaSalida = cadenaSalida + "Solaris" + "\n";
		cadenaSalida = cadenaSalida + "EZJava" + "\n";
		assertEquals(salida, cadenaSalida);
		
		ArrayList<String> ciudadesEmpresa = procesarDatos.devolverCiudadesEmpresa("Google");
		assertTrue(ciudadesEmpresa.size() == 20);
		salida = "";
		for (int i = 0; i < ciudadesEmpresa.size(); i++)
			salida = salida + ciudadesEmpresa.get(i) + "\n";
		cadenaSalida = "";
		cadenaSalida = cadenaSalida + "Berkeley" + "\n";
		cadenaSalida = cadenaSalida + "Denver" + "\n";
		cadenaSalida = cadenaSalida + "Houston" + "\n";
		cadenaSalida = cadenaSalida + "New_Jersey" + "\n";
		cadenaSalida = cadenaSalida + "New_York" + "\n";
		cadenaSalida = cadenaSalida + "Orlando" + "\n";
		cadenaSalida = cadenaSalida + "Atlanta" + "\n";
		cadenaSalida = cadenaSalida + "Boston" + "\n";
		cadenaSalida = cadenaSalida + "Los_Angeles" + "\n";
		cadenaSalida = cadenaSalida + "Miami" + "\n";
		cadenaSalida = cadenaSalida + "Ohio" + "\n";
		cadenaSalida = cadenaSalida + "Philadelphia" + "\n";
		cadenaSalida = cadenaSalida + "Washington" + "\n";
		cadenaSalida = cadenaSalida + "Phoenix" + "\n";
		cadenaSalida = cadenaSalida + "Portland" + "\n";
		cadenaSalida = cadenaSalida + "Wisconsin" + "\n";
		cadenaSalida = cadenaSalida + "Detroit" + "\n";
		cadenaSalida = cadenaSalida + "Stanford" + "\n";
		cadenaSalida = cadenaSalida + "Dallas" + "\n";
		cadenaSalida = cadenaSalida + "Sacramento" + "\n";
		assertEquals(salida, cadenaSalida);

		ArrayList<String> cuidadesProyectoEmpresa = procesarDatos.devolverCiudadesProyectoEmpresa("Word", "Microsoft");
		assertTrue(cuidadesProyectoEmpresa.size() == 4);
		salida = "";
		for (int i = 0; i < cuidadesProyectoEmpresa.size(); i++)
			salida = salida + cuidadesProyectoEmpresa.get(i) + "\n";
		cadenaSalida = "";
		cadenaSalida = cadenaSalida + "Washington" + "\n";
		cadenaSalida = cadenaSalida + "Miami" + "\n";
		cadenaSalida = cadenaSalida + "New_York" + "\n";
		cadenaSalida = cadenaSalida + "Orlando" + "\n";
		assertEquals(salida, cadenaSalida);

		ArrayList<String> paresProyectos = procesarDatos.devolverEmpresaParesProyectoCiudadesComunes("Oracle");
		assertTrue(paresProyectos.size() == 3);
		salida = "";
		for (int i = 0; i < paresProyectos.size(); i++)
			salida = salida + paresProyectos.get(i) + "\n";
		cadenaSalida = "";
		cadenaSalida = cadenaSalida + "Database_11g - Java => Miami" + "\n";
		cadenaSalida = cadenaSalida + "Java - Solaris => New_York" + "\n";
		cadenaSalida = cadenaSalida + "Java - Solaris => Washington" + "\n";
		assertEquals(salida, cadenaSalida);

		ArrayList<String> paresProyectos1 = procesarDatos.devolverEmpresaParesProyectoCiudadesComunes("Microsoft");
		assertTrue(paresProyectos1.size() == 15);
		salida = "";
		for (int i = 0; i < paresProyectos1.size(); i++)
			salida = salida + paresProyectos1.get(i) + "\n";
		cadenaSalida = "";
		cadenaSalida = cadenaSalida + "OutLook - PowerPoint => Miami" + "\n";
		cadenaSalida = cadenaSalida + "OutLook - PowerPoint => Washington" + "\n";
		cadenaSalida = cadenaSalida + "OutLook - VisualC++ => Miami" + "\n";
		cadenaSalida = cadenaSalida + "OutLook - VisualC++ => New_York" + "\n";
		cadenaSalida = cadenaSalida + "OutLook - VisualC++ => Washington" + "\n";
		cadenaSalida = cadenaSalida + "OutLook - Word => Miami" + "\n";
		cadenaSalida = cadenaSalida + "OutLook - Word => New_York" + "\n";
		cadenaSalida = cadenaSalida + "OutLook - Word => Washington" + "\n";
		cadenaSalida = cadenaSalida + "PowerPoint - VisualC++ => Miami" + "\n";
		cadenaSalida = cadenaSalida + "PowerPoint - VisualC++ => Washington" + "\n";
		cadenaSalida = cadenaSalida + "PowerPoint - Word => Miami" + "\n";
		cadenaSalida = cadenaSalida + "PowerPoint - Word => Washington" + "\n";
		cadenaSalida = cadenaSalida + "VisualC++ - Word => Miami" + "\n";
		cadenaSalida = cadenaSalida + "VisualC++ - Word => New_York" + "\n";
		cadenaSalida = cadenaSalida + "VisualC++ - Word => Washington" + "\n";
		assertEquals(salida, cadenaSalida);

		ArrayList<String> paresProyectos2 = procesarDatos.devolverEmpresaParesProyectoCiudadesComunes("Google");
		assertTrue(paresProyectos2.size() == 22);
		salida = "";
		for (int i = 0; i < paresProyectos2.size(); i++)
			salida = salida + paresProyectos2.get(i) + "\n";
		cadenaSalida = "";
		cadenaSalida = cadenaSalida + "Chrome - Earth => New_Jersey" + "\n";
		cadenaSalida = cadenaSalida + "Chrome - Gmail => New_York" + "\n";
		cadenaSalida = cadenaSalida + "Chrome - Maps => New_York" + "\n";
		cadenaSalida = cadenaSalida + "Chrome - Translate => New_York" + "\n";
		cadenaSalida = cadenaSalida + "Earth - Gmail => Miami" + "\n";
		cadenaSalida = cadenaSalida + "Earth - Maps => Miami" + "\n";
		cadenaSalida = cadenaSalida + "Earth - Maps => Washington" + "\n";
		cadenaSalida = cadenaSalida + "Earth - Talk => Los_Angeles" + "\n";
		cadenaSalida = cadenaSalida + "Earth - Talk => Miami" + "\n";
		cadenaSalida = cadenaSalida + "Earth - Talk => Washington" + "\n";
		cadenaSalida = cadenaSalida + "Earth - Translate => Miami" + "\n";
		cadenaSalida = cadenaSalida + "Gmail - Maps => Miami" + "\n";
		cadenaSalida = cadenaSalida + "Gmail - Maps => New_York" + "\n";
		cadenaSalida = cadenaSalida + "Gmail - Talk => Miami" + "\n";
		cadenaSalida = cadenaSalida + "Gmail - Translate => Miami" + "\n";
		cadenaSalida = cadenaSalida + "Gmail - Translate => New_York" + "\n";
		cadenaSalida = cadenaSalida + "Maps - Talk => Detroit" + "\n";
		cadenaSalida = cadenaSalida + "Maps - Talk => Miami" + "\n";
		cadenaSalida = cadenaSalida + "Maps - Talk => Washington" + "\n";
		cadenaSalida = cadenaSalida + "Maps - Translate => Miami" + "\n";
		cadenaSalida = cadenaSalida + "Maps - Translate => New_York" + "\n";
		cadenaSalida = cadenaSalida + "Talk - Translate => Miami" + "\n";
		assertEquals(salida, cadenaSalida);
		
		salida = procesarDatos.devolverProyectoConMayorNumeroDeCiudades();
		assertEquals(salida, "Earth");

		salida = procesarDatos.devolverEmpresaConMayorNumeroDeProyectos();
		assertEquals(salida, "Google");
		
		salida = procesarDatos.devolverCiudadConMayorNumeroDeProyectos();
		assertEquals(salida, "Miami");
		
	}

}
