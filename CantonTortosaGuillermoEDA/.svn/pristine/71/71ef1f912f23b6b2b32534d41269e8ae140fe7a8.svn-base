//
//Universidad de Almería
//Grado en Ingeniería Informática
//Fuente Java según Plantilla
//
//PRACTICA 3 : Utilización de mapas en Java
//ASIGNATURA : Estructura de datos y Algoritmos.
//
package org.eda1.practica03.ejercicio03;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.TreeSet;

import org.eda1.utilidades.Less;
/**
 * Clase para tratar las concordancias de un texto.
 * @author Guillermo Cantón Tortosa
 * @version 1.0. 12.2013
 */
public class Concordancia {
	
	private Pattern identifierPattern;
	
	/**
	* Constructor de la clase concordancia.
	* @param string expresion.
	*/
	public Concordancia(String expresion){
		
		this.identifierPattern = Pattern.compile(expresion);
	}
	
	/**
	* Metodo para obetener las conocordancias de una cadena.
	* @param string cadena.
	* @return Sting con informacion sober las concordancias.
	*/
	public String concordance(String cadena){
		
		Matcher matcher = identifierPattern.matcher(cadena);
		String identifier ="";
		TreeMap<String, TreeSet<Integer>> mapa = new TreeMap<String, TreeSet<Integer>>(new Less<String>());
		
		while(matcher.find()) { 
			
			identifier= cadena.substring(matcher.start(), matcher.end());
			TreeSet<Integer> linea = new TreeSet<Integer>(new Less<Integer>());					
			linea.add(1);
			
			if(!mapa.containsKey(identifier)){
				mapa.put(identifier, linea);
			}else{
				mapa.get(identifier).addAll(linea);
			}	
		}
		return writeConcordance(mapa);
	}
	
	/**
	* Metodo para obetener las conocordancias de un archivo de texto.
	* @param File filename.
	* @return Sting con informacion sober las concordancias del texto.
	*/
	public String concordance(File filename){
		
		TreeMap<String, TreeSet<Integer>> mapa = new TreeMap<String, TreeSet<Integer>>(new Less<String>());
		
		try{
			
			String identifier ="";
			BufferedReader lector = new BufferedReader(new FileReader(filename));
			int contadorLinea=1;
			
			while (lector.ready()){
				
				String cadena = lector.readLine();
				Matcher matcher = identifierPattern.matcher(cadena);
				
				while(matcher.find()) { 
					
					identifier= cadena.substring(matcher.start(), matcher.end());
					TreeSet<Integer> linea = new TreeSet<Integer>(new Less<Integer>());					
					linea.add(contadorLinea);
					
					if(!mapa.containsKey(identifier)){
						mapa.put(identifier, linea);
					}else{
						mapa.get(identifier).addAll(linea);
					}
				}
				
				contadorLinea++;
			}
			
			lector.close();
		}
		
		catch(IOException e){
			System.out.println(e);
		}
		
		return writeConcordance(mapa);
	}
	
	/**
	* Metodo pirvado obetener una cadena con informacion de las conordancias.
	* @param TreeMap<String, TreeSet<Integer>> map.
	* @return Sting con informacion sober las concordancias.
	*/
	private String writeConcordance(TreeMap<String, TreeSet<Integer>> map){
		
		
		Iterator<String> iteradorPalabra = map.keySet().iterator();
		String salida ="";
		
		while(iteradorPalabra.hasNext()){
			
			String palabra = iteradorPalabra.next();
			salida += palabra;
			
			for(int i=palabra.length(); i<14;i++){
				salida+=" ";
			}
			
			salida+=map.get(palabra).size() +":    ";
			Iterator<Integer> iterLinea = map.get(palabra).iterator();
			
			while(iterLinea.hasNext()){
				
				salida+=iterLinea.next();
				
				if(iterLinea.hasNext())
					salida+="    ";
			}
			
			if(iteradorPalabra.hasNext())
				salida+="\n";
		}
		
		return salida;
	}
	
	/**
	* Metodo para obetener las conocordancias de una cadena.
	* @param string cadena.
	* @return Sting con informacion sober las concordancias.
	*/
	public String newConcordance(String cadena){
		
		String identifier ="";
		Matcher matcher = identifierPattern.matcher(cadena);
		TreeMap<String, TreeMap<Integer, Integer>> mapa = new TreeMap<String, TreeMap<Integer, Integer>>(new Less<String>());
		
		while(matcher.find()) { 
			
			identifier= cadena.substring(matcher.start(), matcher.end());
			
			if(!mapa.containsKey(identifier)){
				
				TreeMap<Integer, Integer> linea = new TreeMap<Integer, Integer>(new Less<Integer>());					
				linea.put(1, 1);
				mapa.put(identifier, linea);
				
			}else{
				
				if(mapa.get(identifier).containsKey(1)){
					int repeticion = mapa.get(identifier).get(1) + 1;
					mapa.get(identifier).put(1, repeticion);
				}
			}
		}
		
		return newWriteConcordance(mapa);
	}
	
	/**
	* Metodo para obetener las conocordancias de un archivo de texto.
	* @param File filename.
	* @return Sting con informacion sober las concordancias del texto.
	*/
	public String newConcordance(File filename){
		
		TreeMap<String, TreeMap<Integer, Integer>> mapa = new TreeMap<String, TreeMap<Integer, Integer>>(new Less<String>());
		
		try{
			
			String identifier ="";
			BufferedReader br = new BufferedReader(new FileReader(filename));
			int contadorLinea=1;
			
			while (br.ready()){
				String cadena = br.readLine();
				Matcher matcher = identifierPattern.matcher(cadena);
				
				while(matcher.find()) { 
					
					identifier= cadena.substring(matcher.start(), matcher.end());
					TreeMap<Integer, Integer> linea = new TreeMap<Integer, Integer>(new Less<Integer>());				
					
					if(!mapa.containsKey(identifier)){
						linea.put(contadorLinea, 1);
						mapa.put(identifier, linea);
						
					}else{
						
						if(mapa.get(identifier).containsKey(contadorLinea)){
							
							int repeticion = mapa.get(identifier).get(contadorLinea) +1;
							mapa.get(identifier).put(contadorLinea, repeticion);
							
						}else{
							linea.put(contadorLinea, 1);
							mapa.get(identifier).putAll(linea);
						}
					}
					
				}
				contadorLinea++;
			}
			br.close();
		}
		catch(IOException e){
			System.out.println(e);
		}
		
		return newWriteConcordance(mapa);
	}
	
	/**
	* Metodo pirvado obetener una cadena con informacion de las conordancias.
	* @param TreeMap<String, TreeMap<Integer>> map.
	* @return Sting con informacion sober las concordancias.
	*/
	private String newWriteConcordance(TreeMap<String, TreeMap<Integer, Integer>> map){
		String salida ="";
		Iterator<String> iteradorPalabra = map.keySet().iterator();
		
		while(iteradorPalabra.hasNext()){
			
			String palabra = iteradorPalabra.next();
			salida+=palabra;
			
			for(int i=palabra.length(); i<14;i++){
				salida+=" ";
			}
			
			Iterator<Integer> iteradorLinea = map.get(palabra).keySet().iterator();
			int contadorCasos=0;
			String casos = "";
			
			while(iteradorLinea.hasNext()){
				
				Integer numLinea = iteradorLinea.next();
				casos+=numLinea+"("+map.get(palabra).get(numLinea)+")";
				contadorCasos+=map.get(palabra).get(numLinea);
				if(iteradorLinea.hasNext())
					casos+="    ";
			}
			
			
			salida+=contadorCasos +":    "+ casos;
			
			if(iteradorPalabra.hasNext())
				salida+="\n";
		}
		return salida;
	}
}
