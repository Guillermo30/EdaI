//
// Universidad de Almería
// Grado en Ingeniería Informática
// Fuente Java según Plantilla
//
// PRACTICA 1 : Estructuras de datos lineales de la Java Collections Framework
// ASIGNATURA : Estructura de datos y Algoritmos.
//
package org.eda1.practica01.ejercicio02;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Clase para procesar los datos de las empresas sus poryectos y las ciudades de los proyectos.
 * @author Guillermo Cantón Tortosa
 * @version 1.0. 10.2013
 */
public class ProcesarDatos {
	
	/**
	 * Carga en memoria una lista de EmpresaProyectos almacenado en un fichero
	 * @param archivo String con la dirección del fichero a cargar
	 * @return ArrayList<EmpresaProyectos> lista EmpresaProyectos cargadas del fichero
	 */
	public static ArrayList<EmpresaProyectos> cargarArchivo(String archivo) throws FileNotFoundException {
		
		//variables a utilizar
		ArrayList<EmpresaProyectos> aux = new ArrayList<EmpresaProyectos>();		
		BufferedReader br = new BufferedReader(new FileReader (new File (archivo)));
		String linea;
		String palabrasLinea[] = new String[3]; 
		int x = -1;
		
		try {
			
			while((linea = br.readLine())!=null){
				palabrasLinea = linea.split(" ");	
				
				if(aux.isEmpty()){
					aux.add(new EmpresaProyectos(palabrasLinea[0].toString()));
					aux.get(0).addProyectoCiudad(palabrasLinea[1], palabrasLinea[2]);
					
				}
				else{
					
					for(int i=0;i<aux.size();i++){
						if(aux.get(i).getEmpresa().equals(palabrasLinea[0])){
							x=i;
							break;
						}
					}
					if(x==-1){
						aux.add(new EmpresaProyectos(palabrasLinea[0]));
						aux.get(aux.size()-1).addProyectoCiudad(palabrasLinea[1], palabrasLinea[2]);
					}
					else{
						aux.get(x).addProyectoCiudad(palabrasLinea[1], palabrasLinea[2]);
						x=-1;
					}
				}	
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}  
	
		return aux;
	}
	
	/**
	 *Escribe fichero con una lista de EmpresaProyectos
	 * @param listaEmpresas ArrayList<EmpresaProyectos> EmpresaProyectos a guardar
	 * @param archivo String posicion del fichero en el que vamos a guardar la lista
	 */
	public static void guardarEmpresasProyectosCiudades(ArrayList<EmpresaProyectos> listaEmpresas, String archivo){
		
		try {
			
			FileWriter fw = new FileWriter(archivo);
			PrintWriter pw = new PrintWriter(fw);
			String linea="";
			
			if(!listaEmpresas.isEmpty()){
				for(int i=0; i<listaEmpresas.size(); i++){
					for(int j=0; j<listaEmpresas.get(i).size(); j++){
						for(int k=0; k<listaEmpresas.get(i).getProyectosCiudades().size(); k++){
							linea = listaEmpresas.get(i).getEmpresa()+" "+listaEmpresas.get(i).getProyectoCiudades(j).getProyecto()+" "+listaEmpresas.get(i).getProyectoCiudades(j).getCiudad(k);
						}
					}
					pw.println(linea);
				}
				
				pw.close();
				fw.close();
				
			}else{
				
				pw.close();
				fw.close();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * Obtiene un String con todas la empresas
	 * @param listaEmpresas ArrayList con las empresas
	 * @return String el cual muestra la lista de empresas
	 */					
	public static String devolverEmpresasProyectosCiudades(ArrayList<EmpresaProyectos> listaEmpresas) {
		
		String aux="";
		
		if(!listaEmpresas.isEmpty()){
			
			for (int i=0;i<listaEmpresas.size();i++){
				
				aux+=listaEmpresas.get(i).getEmpresa()+": ";
				
				for (int j=0 ; j<listaEmpresas.get(i).getProyectosCiudades().size() ; j++){
					
						aux+=listaEmpresas.get(i).getProyectoCiudades(j).getProyecto()+"<";
						
						for (int k=0 ; k<listaEmpresas.get(i).getProyectoCiudades(j).getCiudades().size() ; k++){
							
							if(k!=listaEmpresas.get(i).getProyectoCiudades(j).getCiudades().size()-1)	
								aux+=listaEmpresas.get(i).getProyectoCiudades(j).getCiudad(k)+", ";
							else
								aux+=listaEmpresas.get(i).getProyectoCiudades(j).getCiudad(k)+">";
						}
						
						if(j!=listaEmpresas.get(i).getProyectosCiudades().size()-1)
							aux+="; ";
				}
				aux+="\n";
			}
			return aux;
		}
		else
		return aux;
	}
	
	/**
	 * Enumera las empresas que tienen local en una ciudad concerta
	 * @param listaEmpresas ArrayList<EmpresaProyectos> lista de empresas
	 * @param ciudad String ciudad a determinar las empresas que ay
	 * @return
	 */
	public static ArrayList<String> enumerarEmpresasCiudad(ArrayList<EmpresaProyectos> listaEmpresas, String ciudad) {
		
		ArrayList<String> aux=new ArrayList<String>();	
		
		for(int i=0;i<listaEmpresas.size();i++){
			
			for(int j=0;j<listaEmpresas.get(i).size();j++){
				
				for(int k=0;k<listaEmpresas.get(i).getProyectosCiudades().size();k++){
					
					if(listaEmpresas.get(i).getProyectoCiudades(j).getCiudad(k).equals(ciudad)){
						aux.add(listaEmpresas.get(i).getEmpresa());
						break;
					}
				}
			}
				
		}
		return aux;
	}
	
	/**
	 * Obtiene el numero de ciudadades donde esta una empresa.
	 * @param listaEmpresas ArrayList lista de empresas
	 * @param empresa String nombre de la empresa
	 * @return int numero de ciudades
	 */
	public static int contarCiudadesEmpresa(ArrayList<EmpresaProyectos> listaEmpresas, String empresa) {
		
		ArrayList<String> auxiliar=new ArrayList<String>();
		
		for(int i=0;i<listaEmpresas.size();i++){
			if(listaEmpresas.get(i).getEmpresa().equals(empresa)){
				
				for(int j=0;j<listaEmpresas.get(i).getProyectosCiudades().size();j++){
					
					for (int k=0;k<listaEmpresas.get(i).getProyectoCiudades(j).getCiudades().size();k++){
						if(!auxiliar.contains(listaEmpresas.get(i).getProyectoCiudades(j).getCiudad(k)))
							auxiliar.add(listaEmpresas.get(i).getProyectoCiudades(j).getCiudad(k));
					}
				}
			}
		}
		
		return auxiliar.size();
	}
	
	/**
	 * Obtenemos las ciudades donde se realiza un poryecto en concreto.
	 * @param listaEmpresas ArrayList lista de empresas
	 * @param proyecto String proyecto que se realiza
	 * @param empresa String  nombre de la empresa
	 * @return ArrayList<String> lista de las ciudades.
	 */
	public static ArrayList<String> enumerarCiudadesProyectoEmpresa(ArrayList<EmpresaProyectos> listaEmpresas, String proyecto, String empresa) {
		
		ArrayList<String> aux = new ArrayList<String>();
		ArrayList<String> resultado = new ArrayList<String>();
		
		for(int i=0; i<listaEmpresas.size(); i++){
			
			for(int j=0; j<listaEmpresas.get(i).getProyectosCiudades().size(); j++){
				if(listaEmpresas.get(i).getProyectoCiudades(j).getProyecto().equals(proyecto)){
					aux = listaEmpresas.get(i).getProyectoCiudades(j).getCiudades();
					break;
				}
			}
		}
		
		if(aux.isEmpty())return null;
		
		for(int i=0; i<listaEmpresas.size(); i++){
			if(!listaEmpresas.get(i).getEmpresa().equals(empresa)){
				
				for(int j=0; j<listaEmpresas.get(i).getProyectosCiudades().size(); j++){
					
					for(int k=0; k<listaEmpresas.get(i).getProyectoCiudades(j).getCiudades().size(); k++){
						if(aux.contains(listaEmpresas.get(i).getProyectoCiudades(j).getCiudad(k))){
							if(!resultado.contains(listaEmpresas.get(i).getProyectoCiudades(j).getCiudad(k))){
								resultado.add(listaEmpresas.get(i).getProyectoCiudades(j).getCiudad(k));
							}
						}
					}
				}
			}
		}
		
		return resultado;
	}

	/**
	 * Metodo para obtener los proyecyos que tiene una determinada ciudad. 
	 * @param listaEmpresas ArrayList que contiene todas las empresas
	 * @param ciudad String con el nombre de la ciudad a buscar
	 * @return auxiliar ArrayList<String> con la lista de los nombre de los proyectos que tiene sede en dicha ciudad
	 */
	public static ArrayList<String> enumerarProyectosCiudad(ArrayList<EmpresaProyectos> listaEmpresas, String string) {
		
		ArrayList<String> auxiliar=new ArrayList<String>();
		
		for (int i=0;i<listaEmpresas.size();i++){
			
			for (int j=0;j<listaEmpresas.get(i).getProyectosCiudades().size();j++){
				
				for(int k=0; k<listaEmpresas.get(i).getProyectoCiudades(j).size();k++){
					if(listaEmpresas.get(i).getProyectoCiudades(j).getCiudad(k).equals(string)){
						auxiliar.add(listaEmpresas.get(i).getProyectoCiudades(j).getProyecto());
						break;
					}
				}
			}
		}
		
		return auxiliar;
	}

	

	

}
