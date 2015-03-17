//
// Universidad de Almería
// Grado en Ingeniería Informática
// Fuente Java según Plantilla
//
// ACTIVIDAD 1 : Persistencia de Estructuras de Datos mediante Serialización.
// ASIGNATURA : Estructura de datos y Algoritmos I.
//
package org.eda1.actividad01.serializacionED;

import java.io.*;

/**
 * Clase serializacion de textos
 * @author Guillermo Cantón Tortosa
 * @version 1.0. 11.2013
 */
public class ProgramaSerializacion {
	
	/**
	 * Carga en un objeto CiudadBarrios los datos almacenados en un fichero
	 * @param inputFile String  dirección del fichero a leer
	 * @return ArrayList<CiudadBarrios> lista de CiudadBarrios almacenados en el fichero
	 */
	public ArrayList<CiudadBarrios> cargarArchivo(String inputFile) {
		
		ArrayList<CiudadBarrios> ciudades = new ArrayList<CiudadBarrios>();
		FileReader fr;
		
		try {
			
			fr = new FileReader(new File(inputFile));
			BufferedReader br = new BufferedReader(fr);
			String linea = br.readLine();
			
			while(linea!=null){
				CiudadBarrios ciudad = new CiudadBarrios();
				String[] aux = linea.split(" ");
				LinkedList<String> barrios = new LinkedList<String>();
				ciudad.setNombre(aux[0]);
				ciudad.setLatitud(Double.parseDouble(aux[1]));
				ciudad.setAltitud(Double.parseDouble(aux[2]));
				
				for(int i=4; i<aux.length; i++){
					barrios.add(aux[i]);
				}
				ciudad.setBarrios(barrios);
				ciudades.add(ciudad);
				linea = br.readLine();
			}
			
			br.close();
			fr.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ciudades;
	}
	
	/**
	 * Obtenemos en una cadena una lista de CiudadBarrios
	 * @param cB ArrayList<CiudadBarrios> lista a representar
	 * @return String cadena de CiudadBarrios
	 */
	public String mostrarEstructura(ArrayList<CiudadBarrios> cB) {
		
		String cadena = "";
		ListIterator<String> ite;
		
		for(int i=0; i<cB.size(); i++){
			cadena+="["+cB.get(i).getNombre()+", "+cB.get(i).getLatitud()+", "+cB.get(i).getAltitud()+", {";
			if(cB.get(i).getBarrios()!=null){
				ite = cB.get(i).getBarrios().listIterator();
				while(ite.hasNext()){
					cadena += ite.next().toString()+", ";
				}
				cadena = cadena.substring(0, cadena.length()-2);
			}
			cadena += "}]\n";
		}
		return cadena;
	}

}
