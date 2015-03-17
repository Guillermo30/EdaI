//
// Universidad de Almería
// Grado en Ingeniería Informática
// Fuente Java según Plantilla
//
// PRACTICA 2 : Uso de árboles binarios de búsqueda en Java
// ASIGNATURA : Estructura de datos y Algoritmos.
//
package org.eda1.practica02.ejercicio03;

import java.util.Comparator;

/**
 * Clase que implementa un la distancia del comparador o valor del comparador
 * @author Guillermo Cantón Tortosa
 * @version 1.0. 11.2013
 */
public class DistanceComparator implements Comparator<PalabraDistancia>{
	
	public int compare(PalabraDistancia x, PalabraDistancia y){
		
		CorrectorOrtografico aux = new CorrectorOrtografico();
		
		return aux.computeLevenshteinDistance(x.getPalabra(), y.getPalabra());
	}
}
