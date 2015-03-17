package org.eda1.examenParcialGrupoB.ejercicio01;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class ProcesarDatosEPCBTestJUnit4 {

	String directorioEntrada;
	File archivoEntrada;

	@Before
	public void setUp() throws Exception {		
		ProcesarDatosEPCB procesarDatosEPCB = new ProcesarDatosEPCB();
		
		directorioEntrada = System.getProperty("user.dir");

		directorioEntrada = directorioEntrada + File.separator + 
				"src" + File.separator + 
				"org" + File.separator + 
				"eda1" + File.separator + 
				"examenParcialGrupoB" + File.separator +
				"ejercicio01" + File.separator;
	}

	@Test
	public void testProcesarDatosEPCB() throws FileNotFoundException {
		
		String stringArchivoEntrada = "EPCB.txt";
		stringArchivoEntrada = directorioEntrada + stringArchivoEntrada;
		
		ArrayList<EmpresaProyectoCiudadBeneficio> listaEmpresas = ProcesarDatosEPCB.cargarArchivo(stringArchivoEntrada);

		assertTrue(listaEmpresas.size() == 42);
		assertTrue(listaEmpresas.get(1).getBeneficio() == 645903.0);
		
		ArrayList<EmpresaProyectoCiudadBeneficio> mejoresEPCB = ProcesarDatosEPCB.getTopK(listaEmpresas, 5);
		
		String salida = "";
		for (int i = 0; i < mejoresEPCB.size(); i++) {
			salida = salida + mejoresEPCB.get(i).getEmpresa() + " " +
					mejoresEPCB.get(i).getProyecto() + " " +
					mejoresEPCB.get(i).getCiudad() + " " +
					mejoresEPCB.get(i).getBeneficio() + "\n";
		}
		
		String cadenaSalida = "";
		cadenaSalida = cadenaSalida + "Microsoft Word Maryland 897381.0" + "\n";
		cadenaSalida = cadenaSalida + "Adobe Flash Washington 898172.0" + "\n";
		cadenaSalida = cadenaSalida + "Microsoft VisualC++ New_York 919302.0" + "\n";
		cadenaSalida = cadenaSalida + "Ramsoft EZJava New_York 981037.0" + "\n";
		cadenaSalida = cadenaSalida + "Microsoft Word New_York 982573.0" + "\n";
		assertEquals(salida, cadenaSalida);
		
	}

}
