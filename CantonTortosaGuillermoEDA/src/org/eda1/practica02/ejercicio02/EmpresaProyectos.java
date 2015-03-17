//
// Universidad de Almería
// Grado en Ingeniería Informática
// Fuente Java según Plantilla
//
// PRACTICA 2 : Uso de árboles binarios de búsqueda en Java
// ASIGNATURA : Estructura de datos y Algoritmos.
//
package org.eda1.practica02.ejercicio02;

import org.eda1.estructurasdedatos.*;

/**
 * Clase para crear una empresa y sus preyectos.
 * @author Guillermo Cantón Tortosa
 * @version 1.0. 11.2013
 */
public class EmpresaProyectos implements Comparable<Object>{
	
	private String empresa;
	private AVLTree<ProyectoCiudades> proyectosCiudades;
	
	/**
	 * Constructor de  EmpresaProyectos.
	 */
	public EmpresaProyectos(){
		
		empresa = "";
		proyectosCiudades = new AVLTree<ProyectoCiudades>();
		
	}
	
	/**
	 * Constructor de  EmpresaProyectos.
	 * @param empr String con el nombre de la empresa.
	 * @param proy String con el nombre del proyecto.
	 * @param ciudad String con el nombre de la cuidad.
	 */
	public EmpresaProyectos(String empr, String proy, String ciudad){
		
		this.empresa = empr;
		this.proyectosCiudades = new AVLTree<ProyectoCiudades>();
		this.proyectosCiudades.add(new ProyectoCiudades(proy, ciudad));
		
	}
	
	/**
	 * Constructor de  EmpresaProyectos.
	 * @param empr String con el nombre de la empresa
	 */
	public EmpresaProyectos(String empr){
		
		empresa = empr;
		proyectosCiudades = new AVLTree<ProyectoCiudades>();
	}
	
	/**
	 * Metodo que añade un nuevo proyecto en una ciudad.
	 * @param proyecto String con el nombre del proyecto.
	 * @param ciudad String con el nombre de la ciudad.
	 * @return boolean True si añande y False si no añade. 
	 */
	public boolean addProyectoCiudad(String proyecto, String ciudad){
		
		Iterator<ProyectoCiudades> itePro = this.proyectosCiudades.iterator();
		
		while(itePro.hasNext()){
			
			ProyectoCiudades aux = itePro.next();
			
			if(aux.getProyecto().equals(proyecto))
				return aux.addCiudad(ciudad);
		}
		
		return this.proyectosCiudades.add(new ProyectoCiudades(proyecto, ciudad));
	}
	
	/**
	 * Metodo que añade un nuevo proyecto en una ciudad utilizando en metodo find del arbol de busqueda.
	 * @param proyecto String con el nombre del proyecto.
	 * @param ciudad String con el nombre de la ciudad.
	 * @return boolean True si añande y False si no añade. 
	 */
	public boolean addProyectoCiudadWithFind(String proyecto, String ciudad){
		
		ProyectoCiudades aux = this.proyectosCiudades.find(new ProyectoCiudades(proyecto));
		
		if(aux==null){
			
			return this.proyectosCiudades.add(new ProyectoCiudades(proyecto, ciudad));
			
		}else{
			
			return aux.addCiudad(ciudad);
		}
	}
	
	/**
	 * Cambia el nombre de la empresa.
	 * @param empr String con el nuevo nombre de la empresa
	 */
	public void setEmpresa(String empr){
		
		this.empresa = empr;
	}
	
	/**
	 * Obtiene el nombre de la empresa.
	 * @return String con el nombre de la empresa.
	 */
	public String getEmpresa(){
		
		return this.empresa;
	}
	
	/**
	 * Obtiene los proyectos asociados a una empresa.
	 * @return ArrayList<ProyectoCiudades> con la lista de proyectos.
	 */
	public AVLTree<ProyectoCiudades> getProyectosCiudades(){
		
		return this.proyectosCiudades;
	}
	
	/**
	 * Obtenemos el proyecto de una posicion determinada
	 * @param i int con la posición del proyecto a consultar
	 * @return ProyectoCiudades proyecto de la consulta.
	 */
	public ProyectoCiudades getProyectoCiudades(int i){
		
		ProyectoCiudades[] aux = (ProyectoCiudades[])this.proyectosCiudades.toArray();
		
		return aux[i];
	}
	
	/**
	 * Metodo que devuelve el numero de proyectos
	 * @return int con el tamaño de la lista
	 */
	
	public int size(){
		return this.proyectosCiudades.size();
	}
	
	/**
	* Método CompareTo
	* @param o Object objeto a comparar
	* @return int 1 si es mayor, -1 si es menor y 0 si son iguales
	*/
	@Override
	public int compareTo(Object o) {
		
		EmpresaProyectos aux = (EmpresaProyectos) o;
		
		return this.empresa.compareTo(aux.empresa);
	}
	
	/**
	* Método ToString
	* @return cadena String.
	*/
	public String toString(){
		
		String cadena=this.empresa+": ";
		Iterator<ProyectoCiudades> ite = this.proyectosCiudades.iterator();
		
		while(ite.hasNext()){
			
			cadena += ite.next().toString();
			
			if(ite.hasNext())cadena += "; ";
		}
		return cadena;
	}
}
