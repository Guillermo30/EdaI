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
 * Clase para crear una empresa con sus diferentes proyectos.
 * @author Guillermo Cantón Tortosa
 * @version 1.0. 10.2013
 */
public class EmpresaProyectos {
	private String empresa;
	private ArrayList<ProyectoCiudades> proyectosCiudades;
	
	/**
	 * Constructor de EmpresaProyectos
	 */
	public EmpresaProyectos(){
		
	}
	
	/**
	 * Constructor  EmpresaProyectos
	 * @param empr String con nombre de empresa.
	 */
	public EmpresaProyectos(String empr){
		empresa = empr;
		proyectosCiudades = new ArrayList<ProyectoCiudades>();
	}
	
	/**
	 * Metodo para agregar un nuevo proyectoCiudad
	 * @param proyecto String con nombre del proyecto
	 * @param ciudad String con nombre de la ciudad
	 */
	public void addProyectoCiudad(String proyecto, String ciudad){
		boolean encontrada = false;
		for(int i=0; i<this.proyectosCiudades.size(); i++){
			if(this.proyectosCiudades.get(i).getProyecto().equals(proyecto)){
				this.proyectosCiudades.get(i).addCiudad(ciudad);
				encontrada = true;
				break;
			}
		}
		if(!encontrada){
			this.proyectosCiudades.add(new ProyectoCiudades(proyecto));
			this.proyectosCiudades.get(this.proyectosCiudades.size()-1).addCiudad(ciudad);
		}
	}
	
	/**
	 * Modifica el nombre de la empresa
	 * @param empresita String del nuevo nombre 
	 */
	public void setEmpresa(String empresita){
		this.empresa = empresita;
	}
	
	/**
	 * Permite consultar el nombre de la empresa
	 * @return String con el nombre de la empresa
	 */
	public String getEmpresa(){
		return this.empresa;
	}
	
	/**
	 * Obtiene la lista de proyectos de una empresa
	 * @return ArrayList<ProyectoCiudades> lista de proyectos
	 */
	public ArrayList<ProyectoCiudades> getProyectosCiudades(){
		return this.proyectosCiudades;
	}
	
	/**
	 * Obtiene un proyecto determinado
	 * @param i int posicion del proyecto a consultar
	 * @return ProyectoCiudades Devuelve el proyecto y las ciudades.
	 */
	public ProyectoCiudades getProyectoCiudades(int i){
		return this.proyectosCiudades.get(i);
	}
	
	/**
	 * Metodo que devuelve el numero de proyectos que hay
	 * @return int con el tamaño de la lista proyectosCioudades
	 */
	public int size(){
		return this.proyectosCiudades.size();
	}
}
