package org.eda1.examenParcialGrupoB.ejercicio01;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;

import org.eda1.utilidades.Greater;

public class ProcesarDatosEPCB {
	
	public static ArrayList<EmpresaProyectoCiudadBeneficio> cargarArchivo(String archivo) throws FileNotFoundException{
		ArrayList<EmpresaProyectoCiudadBeneficio> salida = new ArrayList<EmpresaProyectoCiudadBeneficio>();
				
		BufferedReader lector = new BufferedReader(new FileReader(archivo));
		String [] separador = null;
		
		try {
			while(lector.ready()){
				String linea = lector.readLine();
				separador = linea.split(" ");
				EmpresaProyectoCiudadBeneficio empresaAInsertar = new EmpresaProyectoCiudadBeneficio(separador[0], separador[1], separador[2], Double.valueOf(separador[3]));
				salida.add(empresaAInsertar);
			}
			lector.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return salida;
	}
	public static ArrayList<EmpresaProyectoCiudadBeneficio> getTopK(ArrayList<EmpresaProyectoCiudadBeneficio> ePCB, int k){
		MyPQWithHeap<EmpresaProyectoCiudadBeneficio> colaPrioridad = new MyPQWithHeap<>(new Greater<EmpresaProyectoCiudadBeneficio>());
		
		for(int i=0; i<ePCB.size(); i++){
			colaPrioridad.add(ePCB.get(i));
		}
		
		ArrayList<EmpresaProyectoCiudadBeneficio> salida = new ArrayList<EmpresaProyectoCiudadBeneficio>();
		
		for(int i=0; i<k; i++){
			salida.add(colaPrioridad.poll());
		}
		Collections.sort(salida);
		return salida;
	}
}
