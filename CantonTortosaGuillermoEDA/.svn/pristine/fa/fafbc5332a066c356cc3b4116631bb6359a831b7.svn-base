//
// Universidad de Almería
// Grado en Ingeniería Informática
// Fuente Java según Plantilla
//
// PRACTICA 1 : Estructuras de datos lineales de la Java Collections Framework
// ASIGNATURA : Estructura de datos y Algoritmos.
//
package org.eda1.practica01.ejercicio02;

import java.util.*;

/**
 * Clase para proyectos y relacionalarlos con diudades.
 * @author Guillermo Cantón Tortosa
 * @version 1.0. 10.2013
 */
public class ProyectoCiudades {
	
	private String proyecto;
	private ArrayList<String> ciudades;
	
	/**
	 * Constructor por defecto, vacio.
	 */
	public ProyectoCiudades(){
		
	}
	
	/**
	 * Consturctor de Proyecto ciudades
	 * @param proyecto String nombre del proyecto.
	 */
	public ProyectoCiudades(String proyecto){
		this.proyecto = proyecto;
		this.ciudades = new ArrayList<String>();
	}
	
	/**
	 * Cambia nombre del proyecto
	 * @param proy String nombre del proyecto.
	 */
	public void setProyecto(String proy){
		this.proyecto = proy;
	}
	

	/**
	 * Obtenemos nombre del proyecto
	 * @return String nombre del proyecto.
	 */
	public String getProyecto(){
		return this.proyecto.toString();
	}
	
	/**
	 * Añade una ciudad a las lista de ciudades
	 * @param ciudad String nombre de ciudad.
	 */
	public void addCiudad(String ciudad){
		
		boolean encontrada = false;
		
		for(int i=0; i<this.ciudades.size(); i++){
			if(this.ciudades.get(i).equals(ciudad)){
				encontrada = true;
				break;
			}
		}
		
		if(!encontrada)this.ciudades.add(ciudad);
	}
	
	/**
	 * Obtenemos lista de las ciudades
	 * @return ArrayList<String> lista de ciudades.
	 */
	public ArrayList<String> getCiudades(){
		return this.ciudades;
	}
	
	/**
	 * Obtenemos la ciudad de la poisicion introducida
	 * @param index int posicion de la ciudad a buscar.
	 * @return String nombre de ciudad en esa posicion de la lista.
	 */
	public String getCiudad(int index){
		return this.ciudades.get(index);
	}
	
	/**
	 * Obtenemos tamaño de la lista de ciudades
	 * @return int numero de ciudades.
	 */
	public int size(){
		return this.ciudades.size();
	}
}
