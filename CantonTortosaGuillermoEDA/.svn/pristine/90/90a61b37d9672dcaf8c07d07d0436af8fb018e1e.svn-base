//
//Universidad de Almería
//Grado en Ingeniería Informática
//Fuente Java según Plantilla
//
//PRACTICA 3 : Utilización de mapas en Java
//ASIGNATURA : Estructura de datos y Algoritmos.
//
package org.eda1.practica03.ejercicio01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Iterator;
import org.eda1.utilidades.Less;



/**
 * Clase para control de una puerta de acceso a una red.
 * @author Guillermo Cantón Tortosa
 * @version 1.0. 12.2013
 */
public class ProcesarDirecciones {
	
	TreeMap<String, TreeMap<String, Integer>> mapa;
	
	/**
	* Constructor de la clase PorcesarDiercciones .
	*/
	public ProcesarDirecciones(){
		
		this.mapa = new TreeMap<String, TreeMap<String, Integer>>(new Less<String>());
	}
	
	/**
	* Metodo para cargar un archivo a memoria.
	* @param archivo String con la ruta del archivo.
	*/
	public void cargarArchivo(String archivo){
		
		try{
			
			BufferedReader bf = new BufferedReader(new FileReader(archivo));
			
			while (bf.ready()){
				
				String linea = bf.readLine();
				String [] lineaTroceada = linea.split(" ");
				
				if(this.mapa.containsKey(lineaTroceada[0])){
					
					if(this.mapa.get(lineaTroceada[0]).containsKey(lineaTroceada[1])){ 
						
						Integer acceso = this.mapa.get(lineaTroceada[0]).get(lineaTroceada[1]);
						this.mapa.get(lineaTroceada[0]).put(lineaTroceada[1], acceso+1); 
						
					}else{
						
						this.mapa.get(lineaTroceada[0]).put(lineaTroceada[1], 1);
					}
					
				}else{
					
					this.mapa.put(lineaTroceada[0], new TreeMap<String, Integer>(new Less<String>()));
					this.mapa.get(lineaTroceada[0]).put(lineaTroceada[1], 1);
					
				}
			}
			bf.close();
		}
		catch(IOException e){
			System.out.println(e);
		}
	}
	
	/**
	* Metodo para obtener el tamaño del mapa.
	* @return int tamaño del mapa.
	*/
	public int tamano(){
		
		return this.mapa.size();
	}
	
	/**
	* Metodo obtener un archivo de texto las direcciones de las maquinas.
	* @param String archivo ruta del archivo.
	*/
	public void generarDirecciones(String archivo){
		
		try{
			
			File archivoEntradas = new File(archivo);
			PrintWriter pw = new PrintWriter(archivoEntradas);
			Iterator<String> iterIP = this.mapa.keySet().iterator();
			
			while(iterIP.hasNext()){
				
				String ip = iterIP.next();
				pw.print(ip +" => {");
				Iterator<String> iteradorM = this.mapa.get(ip).keySet().iterator();
				
				while(iteradorM.hasNext()){
					
					String nombreM = iteradorM.next();
					pw.print(nombreM+"="+this.mapa.get(ip).get(nombreM));
					
					if(iteradorM.hasNext())
						pw.print(", ");
				}
				pw.print("}\n");
			}
			pw.close();
		}
		catch(IOException e){
			System.out.println(e.getMessage());
		}
		
	}
	
	/**
	* Metodo que muestra por pantalla las Ips de las maquinas.
	*/
	public void mostrarDirecciones(){
		
		Iterator<String> iterIP = this.mapa.keySet().iterator();
		
		while(iterIP.hasNext()){
			System.out.println(iterIP.next());
		}
	}
	
	/**
	* Metodo obtener un archivo de texto las direcciones de las maquinas.
	* @param String archivo ruta del archivo.
	*/
	public void generarIncidencias(String archivo){
		
		try{
			
			File entradas = new File(archivo);
			PrintWriter pw = new PrintWriter(entradas);
			Iterator<String> iteradorIP = this.mapa.keySet().iterator();
			
			while(iteradorIP.hasNext()){
				
				String ip = iteradorIP.next();
				
				if(this.mapa.get(ip).size()>1){ 
					
					pw.print(ip +" => {");
					Iterator<String> iteradorMaquina = this.mapa.get(ip).keySet().iterator();
					
					while(iteradorMaquina.hasNext()){
						
						String nombreM = iteradorMaquina.next();
						
						pw.print(nombreM+"="+this.mapa.get(ip).get(nombreM));
						if(iteradorMaquina.hasNext())
							pw.print(", ");
					}
					pw.print("}\n");
				}
			}
			pw.close();
		}
		catch(IOException e){
			System.out.println(e.getMessage());
		}
	}
	
	/**
	* Metodo escribir por pantalla las incidencias.
	*/
	public void mostrarIncidencias(){
		
		Iterator<String> iteradorIP = this.mapa.keySet().iterator();
		Iterator<String> iteradorM = this.mapa.get(iteradorIP.next()).keySet().iterator();

		while(iteradorIP.hasNext()){
			
			while(iteradorM.hasNext()){
				
			System.out.println(iteradorM.next());
			}
		}
		
	}
	
	/**
	* Metodo una lista con los nombres de las maquinas con contadores mayores que un valor pasado por parametro.
	* @param int entero.
	* @return ArrayList<String> salida lista de nombre de maquinas que cumplen la condicion.
	*/
	public ArrayList<String> maquinasConContadorMayorQue(int c){
		
		ArrayList<String> salida = new ArrayList<String>();
		Iterator<String> iteradorIP = this.mapa.keySet().iterator();
		
		while(iteradorIP.hasNext()){
			
			String ip = iteradorIP.next();
			Iterator<String> iteradorM = this.mapa.get(ip).keySet().iterator();
			
			while(iteradorM.hasNext()){
				
				String nombreM = iteradorM.next();
				
				if(this.mapa.get(ip).get(nombreM)>c)
					salida.add(nombreM);
			}
		}
		return salida;
	}
	
	/**
	* Metodo para obtener el numero de maquinas con el mismo numero de contador que el paado por parametro.
	* @param int entero.
	* @return int salida lista de nombre de maquinas que cumplen la condicion.
	*/
	public int maquinasConContadorIgualA(int c){
		
		int salida=0;
		Iterator<String> iterIP = this.mapa.keySet().iterator();
		
		while(iterIP.hasNext()){
			
			String ip = iterIP.next();
			Iterator<String> iterM = this.mapa.get(ip).keySet().iterator();
			
			while(iterM.hasNext()){
				
				String nombreM = iterM.next();
				if(this.mapa.get(ip).get(nombreM)==c)
					salida++;
			}
		}
		return salida;
	}
	
	/**
	* Metodo para obtener el valor del contador de una maquina con una direccion determinada.
	* @param String direccion de la maquina.
	* @param String maquina nombre de la maquina
	* @return int con el valor del contador.
	*/
	public int valorContador(String direccion, String maquina){
		
		return this.mapa.get(direccion).get(maquina);
	}
	
	/**
	* Metodo para obtener una lista con el nombre de las maquinas con incicencias.
	* @param String direccion de la maquina.
	* @return ArrayList<String> salida lista de nombre de maquinas que tiene incidencias.
	*/
	public ArrayList<String> incidenciasGeneradasPor(String direccion){
		
		ArrayList<String> salida = new ArrayList<String>();
		Iterator<String> iteradorIP = this.mapa.keySet().iterator();
		
		while(iteradorIP.hasNext()){
			
			String ip = iteradorIP.next();
			
			if(ip.equals(direccion)){
				
				Iterator<String> iteradorM = this.mapa.get(ip).keySet().iterator();
				
				while(iteradorM.hasNext()){
					
					String nombreM = iteradorM.next();
					salida.add(nombreM);
				}
			}
		}
		return salida;
	}
	
	/**
	* Metodo para las incicdencias generadoas por una direccion.
	* @param String direccion de la maquina.
	* @return int cantiad de incidencias.
	*/
	public int numeroDeIncidenciasGeneradasPor(String direccion){
		
		return this.mapa.get(direccion).size();
	}
	
}
