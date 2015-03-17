//
// Universidad de Almería
// Grado en Ingeniería Informática
// Fuente Java según Plantilla
//
// ACTIVIDAD 1 : Persistencia de Estructuras de Datos mediante Serialización.
// ASIGNATURA : Estructura de datos y Algoritmos I.
//
package org.eda1.actividad01.serializacionED;

import java.io.Serializable;

/**
 * Clase para crear una ciudad y sus barrios.
 * @author Guillermo Cantón Tortosa
 * @version 1.0. 11.2013
 */
@SuppressWarnings("serial")
public class CiudadBarrios implements Serializable {
	
	private String nombre;
	private double latitud;
	private double altitud;
	private LinkedList<String> barrios;
	
	/**
	 * Constructor de la clase
	 */
	public CiudadBarrios(){
		//todo a nulo
		this.setNombre(null);
		this.setLatitud(0.0);
		this.setAltitud(0.0);
		this.setBarrios(null);
	}
	
	/**
	 * Constructor con parametros de entarda
	 * @param nombre String nombre de la ciudad
	 * @param latitud double latitud de la ciudad
	 * @param altitud double altitud de la ciudad
	 */
	public CiudadBarrios(String nombre, double latitud, double altitud){
		
		this.setNombre(nombre);
		this.setLatitud(latitud);
		this.setAltitud(altitud);
		this.setBarrios(null);
	}
	
	/**
	 * Constructor de la clase con parametros y con lista de barrios
	 * @param nombre String nombre de la ciudad
	 * @param latitud double latitud de la ciudad
	 * @param altitud double altitud de la cuidad
	 * @param barrios LinkedList lista de los barrios de la ciudad
	 */
	public CiudadBarrios(String nombre, double latitud, double altitud, LinkedList<String> barrios){
		
		this.setNombre(nombre);
		this.setLatitud(latitud);
		this.setAltitud(altitud);
		this.setBarrios(barrios);
	}
	
	/**
	 * Permite consultar el nombre de la ciudad
	 * @return String con el nombre de la ciudad
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * Modifica el nombre de la ciudad
	 * @param nombre String nuevo nombre de la ciudad
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * Para obtener latitud de la ciudad
	 * @return latitud double  latitud de la ciudad
	 */
	public double getLatitud() {
		return latitud;
	}
	
	/**
	 * Modifica la latitud de la ciudad
	 * @param latitud double nueva latitud
	 */
	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}
	
	/**
	 * Para obtener Altitud de la ciudad
	 * @return altitud double  altitud de la ciudad
	 */
	public double getAltitud() {
		return altitud;
	}
	
	/**
	 * Modifica la altitud de la ciudad
	 * @param latitud double nueva latitud
	 */
	public void setAltitud(double altitud) {
		this.altitud = altitud;
	}
	
	/**
	 * Para obtener la lista de barrios
	 * @return barrios LinkedList<Strin> lista de barrios
	 */
	public LinkedList<String> getBarrios() {
		return barrios;
	}
	
	/**
	 * Modifica la lista de barrios
	 * @param barrios LinkedList<String> lista nueva de barrios
	 */
	public void setBarrios(LinkedList<String> barrios) {
		this.barrios = barrios;
	}
	
	/**
	 * añade un barrio a la lista
	 * @param barrio String nombre del nuevo barrio
	 */
	public void addBarrio(String barrio){
		this.barrios.add(barrio);
	}
}
