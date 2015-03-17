//
//Universidad de Almería
//Grado en Ingeniería Informática
//Fuente Java según Plantilla
//
//PRACTICA 3 : Utilización de mapas en Java
//ASIGNATURA : Estructura de datos y Algoritmos.
//
package org.eda1.practica03.ejercicio02;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.TreeSet;

import org.eda1.utilidades.Less;
/**
 * Clase obtiene informacion de un mapa de empresas, proyectos y ciudades.
 * @author Guillermo Cantón Tortosa
 * @version 1.0. 12.2013
 */
public class ProcesarDatos {
	
	private TreeMap<String, TreeMap<String, TreeSet<String>>> mapa;
	
	/**
	* Constructor de la clase PorcesarDatos, crea el mapa .
	*/
	public ProcesarDatos(){
		
		this.mapa = new TreeMap<String, TreeMap<String, TreeSet<String>>>(new Less<String>());
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
				String empresa = lineaTroceada[0];
				String proyecto = lineaTroceada[1];
				String ciudad = lineaTroceada[2];
				
				if(this.mapa.containsKey(empresa)){
					
					if(this.mapa.get(empresa).containsKey(proyecto)){
						
						if(!this.mapa.get(empresa).get(proyecto).contains(ciudad)){
							
							this.mapa.get(empresa).get(proyecto).add(ciudad);
						}
					}else{
						
						this.mapa.get(empresa).put(proyecto, new TreeSet<String>(new Less<String>()));
						this.mapa.get(empresa).get(proyecto).add(ciudad);
						
					}
				}else{
					
					TreeSet<String> treeSetCiudad = new TreeSet<String>();
					treeSetCiudad.add(ciudad);
					TreeMap<String, TreeSet<String>> treeMapProyectos = new TreeMap<String, TreeSet<String>>(new Less<String>());
					treeMapProyectos.put(proyecto, treeSetCiudad);
					
					this.mapa.put(empresa, treeMapProyectos);
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
	public int size(){
		
		return this.mapa.size();
	}
	
	/**
	 * Muestra por pantalla todas la empresas con los proyectos y las cuidades.
	 */
	public void mostrarEmpresasProyectosCiudades(){
		
		System.out.print(devolverEmpresasProyectosCiudades());
	}

	/**
	 * Guarda en un archivo de texto todas la empresas con los proyectos y las cuidades
	 */
	public void guardarEmpresasProyectosCiudades(String archivo){
		
		try {
			
			File archivoEntradas = new File(archivo);
			PrintWriter pw = new PrintWriter(archivoEntradas);
			pw.print(devolverEmpresasProyectosCiudades());
			pw.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	* Metodo obtener una lista con las ciudades.
	* @return ArrayList<String> salida lista de ciudades.
	*/
	public ArrayList<String> devolverCiudades(){
		
		ArrayList<String> salida = new ArrayList<String>();
		Iterator<String> iterEmpresa = this.mapa.keySet().iterator();
		
		while(iterEmpresa.hasNext()){
			
			String empresa = iterEmpresa.next();
			Iterator<String> iterProyecto = this.mapa.get(empresa).keySet().iterator();
			
			while(iterProyecto.hasNext()){
				
				String proyecto = iterProyecto.next();
				Iterator<String> iteradorCiudades = this.mapa.get(empresa).get(proyecto).iterator();
				
				while(iteradorCiudades.hasNext()){
					
					String ciudades = iteradorCiudades.next();
					
					if(!salida.contains(ciudades)){
						salida.add(ciudades);
					}
				}
			}
		}
		return salida;
	}

	/**
	* Metodo obtener una lista con los proyectos.
	* @return ArrayList<String> salida lista de proyectos.
	*/
	public ArrayList<String> devolverProyectos(){
		
		ArrayList<String> salida = new ArrayList<String>();
		Iterator<String> iterEmpresa = this.mapa.keySet().iterator();
		
		while(iterEmpresa.hasNext()){
			
			String empresa = iterEmpresa.next();
			Iterator<String> iteradorProy = this.mapa.get(empresa).keySet().iterator();
			
			while(iteradorProy.hasNext()){
				
				salida.add(iteradorProy.next());
			}
		}
		return salida;
	}

	/**
	* Metodo obtener una lista con las empresas.
	* @return ArrayList<String> salida lista de empresas.
	*/
	public ArrayList<String> devolverEmpresas(){
		
		ArrayList<String> salida = new ArrayList<String>();
		Iterator<String> iteradorEmpresa = this.mapa.keySet().iterator();
		
		while(iteradorEmpresa.hasNext()){
			
			salida.add(iteradorEmpresa.next());
		}
		return salida;
	}

	/**
	* Metodo obtener la cantidad de proyectos de una determinada empresa.
	* @param empresa nombre de la empresa.
	* @return int numero de proyectos.
	*/
	public int numeroProyectosEmpresa(String empresa){
		
		return this.mapa.get(empresa).size();
	}
	
	/**
	* Metodo obtener la cantidad de ciudades con un determinado proyecto.
	* @param String proyecto.
	* @return int numero de ciudades.
	*/
	public int numeroCiudadesProyecto(String proyecto){
		
		int salida=0;
		Iterator<String> iteradorEmpresa = this.mapa.keySet().iterator();
		
		while(iteradorEmpresa.hasNext()){
			
			String empresa = iteradorEmpresa.next();
			
			if(this.mapa.get(empresa).containsKey(proyecto)){
				
				salida=this.mapa.get(empresa).get(proyecto).size();
			}
		}
		return salida;
	}
	
	/**
	* Metodo obtener la cantidad de ciudades en las que hay una determinada empresa.
	* @param String empresa.
	* @return int numero de ciudades con una empresa determinada.
	*/
	public int numeroCiudadesEmpresa(String empresa){
		
		ArrayList<String> ciudades = new ArrayList<String>();
		Iterator<String> iteradorProyectos = this.mapa.get(empresa).keySet().iterator();
		
		while(iteradorProyectos.hasNext()){
			
			String proyecto = iteradorProyectos.next();
			Iterator<String> iteradorCiudades = this.mapa.get(empresa).get(proyecto).iterator();
			
			while(iteradorCiudades.hasNext()){
				
				String ciudad = iteradorCiudades.next();
				
				if(!ciudades.contains(ciudad)){
					ciudades.add(ciudad);
				}	
			}
		}
		
		return ciudades.size();
	}
	
	/**
	* Metodo que devuelve un String con toda la informacion del mapa.
	* @return Stirng salida.
	*/
	public String devolverEmpresasProyectosCiudades(){
		
		String salida ="";
		Iterator<String> iteradorEmpresa = this.mapa.keySet().iterator();
		
		while(iteradorEmpresa.hasNext()){
			
			String empresa = iteradorEmpresa.next();
			Iterator<String> iteradorProyecto = this.mapa.get(empresa).keySet().iterator();
			salida+=empresa+": ";
			
			while(iteradorProyecto.hasNext()){
				
				String proyecto = iteradorProyecto.next();
				Iterator<String> iteradorCiudades = this.mapa.get(empresa).get(proyecto).iterator();
				salida+=proyecto +"<";
				
				while(iteradorCiudades.hasNext()){
					
					String ciudades = iteradorCiudades.next();
					salida+=ciudades;
					
					if(iteradorCiudades.hasNext()){
						salida+=", ";
					}
				}
				
				salida+=">";
				
				if(iteradorProyecto.hasNext()){
					salida+="; ";
				}
			}
			salida+="\n";
		}
		return salida;
	}

	/**
	* Metodo que devuelve una lista con las empresas que hay en una ciudad.
	* @param String ciudad
	* @return ArrayList<String>  salida.
	*/
	public ArrayList<String> devolverEmpresasCiudad(String ciudad){
		
		ArrayList<String> salida = new ArrayList<String>();
		Iterator<String> iteradorEmpresa = this.mapa.keySet().iterator();
		
		while(iteradorEmpresa.hasNext()){
			
			String empresas= iteradorEmpresa.next();
			Iterator<String> iteradorProyecto = this.mapa.get(empresas).keySet().iterator();
			
			while(iteradorProyecto.hasNext()){
				
				String proyectos = iteradorProyecto.next();
				Iterator<String> iteradorCiudades = this.mapa.get(empresas).get(proyectos).iterator();
				
				while(iteradorCiudades.hasNext()){
					
					String ciudades = iteradorCiudades.next();
					
					if(ciudades.equals(ciudad) && !salida.contains(empresas)){
						salida.add(empresas);
					}
				}
			}
		}
		return salida;
	}

	/**
	* Metodo que devuelve una lista con los proyectos que hay en una ciudad.
	* @param String ciudad
	* @return ArrayList<String>  salida.
	*/
	public ArrayList<String> devolverProyectosCiudad(String ciudad){
		
		ArrayList<String> salida = new ArrayList<String>();
		Iterator<String> iteradorEmpresa = this.mapa.keySet().iterator();
		
		while(iteradorEmpresa.hasNext()){
			
			String empresas= iteradorEmpresa.next();
			Iterator<String> iteradorProyecto = this.mapa.get(empresas).keySet().iterator();
			
			while(iteradorProyecto.hasNext()){
				
				String proyectos = iteradorProyecto.next();
				Iterator<String> iteradorCiudades = this.mapa.get(empresas).get(proyectos).iterator();
				
				while(iteradorCiudades.hasNext()){
					
					String ciudades = iteradorCiudades.next();
					
					if(ciudades.equals(ciudad) && !salida.contains(proyectos)){
						salida.add(proyectos);
					}
				}
			}
		}
		return salida;
	}
	
	/**
	* Metodo que devuelve una lista con los proyectos que hay en una ciudad.
	* @param String ciudad
	* @return ArrayList<String>  salida.
	*/
	public ArrayList<String> devolverCiudadesEmpresa(String empresa){
		
		ArrayList<String> salida = new ArrayList<String>();
		Iterator<String> iteradorEmpresa = this.mapa.keySet().iterator();
		
		while(iteradorEmpresa.hasNext()){
			
			String empresas= iteradorEmpresa.next();
			Iterator<String> iteradorProyecto = this.mapa.get(empresas).keySet().iterator();
			
			while(iteradorProyecto.hasNext()){
				
				String proyectos = iteradorProyecto.next();
				Iterator<String> iteradorCiudades = this.mapa.get(empresas).get(proyectos).iterator();
				
				while(iteradorCiudades.hasNext()){
					
					String ciudades = iteradorCiudades.next();
					
					if(empresas.equals(empresa) && !salida.contains(ciudades)){
						salida.add(ciudades);
					}
				}
			}
		}
		return salida;
	}

	/**
	* Metodo que devuelve una lista con las ciudades que hay con un poryecto y una empresa determinada..
	* @param String proyecto.
	* @param String empresa.
	* @return ArrayList<String>  salida.
	*/
	public ArrayList<String> devolverCiudadesProyectoEmpresa(String proyecto, String empresa){
		
		ArrayList<String> ciudadesProyecto = new ArrayList<String>();
		ArrayList<String> salida = new ArrayList<String>();
		String proyectoCondicionado = "";
		String ciudadCondicionada = "";
		String empresaCondicionada = "";
		Iterator<String> iteradorEmpresa = this.mapa.keySet().iterator();
		
		while(iteradorEmpresa.hasNext()){
			
			String empresas = iteradorEmpresa.next();
			Iterator<String> iteradorProyectos = this.mapa.get(empresas).keySet().iterator();
			
			while(iteradorProyectos.hasNext()){
				
				String proyectos = iteradorProyectos.next();
				Iterator<String> iteradorCiudades = this.mapa.get(empresas).get(proyectos).iterator();
				
				while (iteradorCiudades.hasNext()){
					
					String ciudades = iteradorCiudades.next();
					empresaCondicionada = empresas;
					proyectoCondicionado = proyectos;
					ciudadCondicionada = ciudades;
					
					if(proyectoCondicionado.equals(proyecto)){
						ciudadesProyecto.add(ciudadCondicionada);
					}	
				}
			}
		}
		
		iteradorEmpresa = this.mapa.keySet().iterator();
		
		while(iteradorEmpresa.hasNext()){
			
			String empresas = iteradorEmpresa.next();
			Iterator<String> iteradorProyectos = this.mapa.get(empresas).keySet().iterator();
			
			while(iteradorProyectos.hasNext()){
				
				String proyectos = iteradorProyectos.next();
				Iterator<String> iteradorCiudades = this.mapa.get(empresas).get(proyectos).iterator();
				
				while (iteradorCiudades.hasNext()){
					
					String ciudades = iteradorCiudades.next();
					empresaCondicionada = empresas;
					proyectoCondicionado = proyectos;
					ciudadCondicionada = ciudades;
					
					if(!empresa.equals(empresaCondicionada) && ciudadesProyecto.contains(ciudadCondicionada) && !salida.contains(ciudadCondicionada)){
						salida.add(ciudadCondicionada);
					}	
				}
			}
		}
		return salida;
	}
	
	/**
	* Metodo que devuelve una lista con las empresas que tienen un proyecto y ciudad comunes.
	* @param String empresa.
	* @return ArrayList<String>  salida.
	*/
	public ArrayList<String> devolverEmpresaParesProyectoCiudadesComunes(String empresa){
		
		ArrayList<String> lista = new ArrayList<String>();
		ArrayList<String> salida = new ArrayList<String>();
		Iterator<String> iteradorEmpresa = this.mapa.keySet().iterator();
		
		while(iteradorEmpresa.hasNext()){	
			
			String empresas = iteradorEmpresa.next();
			
			if(empresas.equals(empresa)){ 
				
				Iterator<String> iteradorProyectos = this.mapa.get(empresas).keySet().iterator();
				
				while(iteradorProyectos.hasNext()){
					
					String proyectos = iteradorProyectos.next();
					Iterator<String> iteradorCiudad = this.mapa.get(empresas).get(proyectos).iterator();
					
					while (iteradorCiudad.hasNext()){															
						String ciudades = iteradorCiudad.next();
						lista.add(proyectos+ " " +ciudades);
					}
				}
			}
			
		}
		
		for(int i=0; i<lista.size();i++){
			
			String [] array1 = lista.get(i).split(" ");
			
			for(int j=i+1; j<lista.size();j++){
				
				String [] array2 = lista.get(j).split(" ");
				
				if(array1[1].equals(array2[1]))
					
					salida.add(array1[0]+" - "+array2[0]+" => "+array1[1]);
									
			}
		}
		
		Collections.sort(salida);
		return salida;
	}
	
	/**
	* Metodo que devuelve una una cadena con el poryecto con mayor numero de ciudades.
	* @return String  salida.
	*/
	public String devolverProyectoConMayorNumeroDeCiudades(){
		
		String salida = "";
		int contador = 0;
		Iterator<String> iteradorEmpresa = this.mapa.keySet().iterator();
		
		while(iteradorEmpresa.hasNext()){
			
			String empresas= iteradorEmpresa.next();
			Iterator<String> iteradorProyecto = this.mapa.get(empresas).keySet().iterator();
			
			while(iteradorProyecto.hasNext()){
				
				String proyectos = iteradorProyecto.next();
				int numeroCiudades = this.mapa.get(empresas).get(proyectos).size();
				
				if(numeroCiudades>contador){
					salida = proyectos;
					contador = numeroCiudades;
				}
			}
		}
		return salida;
	}
	
	/**
	* Metodo que devuelve una una cadena con la empresa con mayor numero de Proyectos.
	* @return String salida.
	*/
	public String devolverEmpresaConMayorNumeroDeProyectos(){
		
		String salida = "";
		int contador = 0;
		Iterator<String> iteradorEmpresa = this.mapa.keySet().iterator();
		
		while(iteradorEmpresa.hasNext()){
			
			String empresas= iteradorEmpresa.next();
			int numeroProyectos = this.mapa.get(empresas).size();
			
			if(this.mapa.get(empresas).size()>contador){
				salida = empresas;
				contador = numeroProyectos;
			}
		}
		return salida;
	}
	
	/**
	* Metodo que devuelve una una cadena con la ciudad con mayor numero de Proyectos.
	* @return String salida.
	*/
	public String devolverCiudadConMayorNumeroDeProyectos(){
		
		ArrayList<String> listaCiudades = new ArrayList<String>();
		int contadorMaximoNumeroProyectos = 0;
		String salida="";
		Iterator<String> iteradorEmpresa = this.mapa.keySet().iterator();
		
		while(iteradorEmpresa.hasNext()){
			
			String empresas= iteradorEmpresa.next();
			Iterator<String> iteradorProyecto = this.mapa.get(empresas).keySet().iterator();
			
			while(iteradorProyecto.hasNext()){
				
				String proyectos = iteradorProyecto.next();
				Iterator<String> iteradorCiudades = this.mapa.get(empresas).get(proyectos).iterator();
				
				while(iteradorCiudades.hasNext()){
					
					String ciudades = iteradorCiudades.next();
					listaCiudades.add(ciudades);
					
					if(listaCiudades.contains(ciudades)){
						
						int contador = 0;
						
						for(int i=0; i<listaCiudades.size();i++){
							
							if(ciudades.equals(listaCiudades.get(i))){
								contador++;
							}	
						}
						
						if(contador>contadorMaximoNumeroProyectos){
							
							contadorMaximoNumeroProyectos = contador;
							salida = ciudades;
						}
					}
				}
			}
		}
		
		return salida;
	}
}
