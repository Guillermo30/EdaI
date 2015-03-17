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
 * Clase  proyectos y ciudades del proyecto se lleva acabo.
 * @author Guillermo Cantón Tortosa
 * @version 1.0. 11.2013
 */
public class ProyectoCiudades implements Comparable<Object>{
	
	private String proyecto;
	private AVLTree<String> ciudades;
	
	/**
	 * Constructor de  ProyectoCiudades.
	 */
	public ProyectoCiudades(){
		
		this.proyecto = "";
		this.ciudades = new AVLTree<String>();
	}
	
	/**
	 * Constructor de  ProyectoCiudades.
	 * @param proy String con el nombre del proyecto.
	 */
	public ProyectoCiudades(String proy){
		
		this.proyecto = proy;
		this.ciudades = new AVLTree<String>();
	}
	
	/**
	 * Constructor de  ProyectoCiudades.
	 * @param proy String con el nombre del proyecto.
	 * @param ciudad String con el nombre de la cuidad.
	 */
	public ProyectoCiudades(String proy, String ciudad){
		
		this.proyecto = proy;
		this.ciudades = new AVLTree<String>();
		this.ciudades.add(ciudad);
	}
	
	/**
	 * Cambia el nombre del proyecto.
	 * @param proy String con el nombre del proyecto.
	 */
	public void setProyecto(String proy){
		
		this.proyecto = proy;
	}
	
	/**
	 * Obtiene el nombre del poryecto.
	 * @return String con el nombre de la empresa.
	 */
	public String getProyecto(){
		
		return this.proyecto;
	}
	
	/**
	 * Añade ciudad.
	 * @param ciudad String con el nombre de la ciudad.
	 * @return booleam False si no la añande y True si la añade correctamente.
	 */
	public boolean addCiudad(String ciudad){
		String encontrado = this.ciudades.find(ciudad);
		if(encontrado == null){
			return this.ciudades.add(ciudad);
		}else{
			return false;
		}
	}
	
	/**
	 * Obtiene una lista de ciudades.
	 * @return resultado AVLTree<String> con lista de ciudades.
	 */
	public AVLTree<String> getCiudades(){
		
		AVLTree<String> resultado = new AVLTree<String>();
		Iterator<String> ite = this.ciudades.iterator();
		
		while(ite.hasNext()){
			
			resultado.add(ite.next());
			
		}
		
		return resultado;
	}
	
	/**
	 * Obtiene una ciudad en una posicion concreta.
	 * @return String con el nombre de la ciudad en esa posicion.
	 */
	public String getCiudad(int index){
		
		String[] aux = (String[])this.ciudades.toArray();
		
		return aux[index];
	}
	
	/**
	 * Obtiene el tamaño de la lista de ciudades.
	 * @return int con el mumero de ciudades de la lista.
	 */
	public int size(){
		
		return this.ciudades.size();
	}

	/**
	* Método CompareTo
	* @param o Object objeto a comparar
	* @return int 1 si es mayor, -1 si es menor y 0 si son iguales
	*/
	@Override
	public int compareTo(Object o) {
		
		ProyectoCiudades aux = (ProyectoCiudades) o;
		
		return this.proyecto.compareTo(aux.proyecto);
	}
	
	
	/**
	* Método ToString
	* @return cadena String.
	*/
	public String toString(){
		String cadena=this.proyecto+"<";
		Iterator<String> ite = this.ciudades.iterator();
		while(ite.hasNext()){
			cadena += ite.next();
			if(ite.hasNext())cadena+=", ";
		}
		cadena+=">";
		return cadena;
	}
}
