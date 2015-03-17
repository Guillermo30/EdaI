//
// Universidad de Almer�a
// Grado en Ingenier�a Inform�tica
// Fuente Java seg�n Plantilla
//
// PRACTICA 2 : Uso de �rboles binarios de b�squeda en Java
// ASIGNATURA : Estructura de datos y Algoritmos.
//
package org.eda1.practica02.ejercicio03;

/**
 * Clase que implementa la palabra y su distancia.
 * @author Guillermo Cant�n Tortosa
 * @version 1.0. 11.2013
 */
public class PalabraDistancia extends DistanceComparator{
	private String palabra;
	private int distancia;
	
	public PalabraDistancia(String pal, int dis){
		
		this.palabra = pal;
		this.distancia = dis;
	}
	
	public PalabraDistancia(String pal){
		
		this.palabra = pal;
	}
	
	public PalabraDistancia(){
		
	}
	
	public void setPalabra(String pal){
		
		
		this.palabra = pal;
	}
	
	public String getPalabra(){
		
		return this.palabra;
	}
	
	public void setfrecuencia(int dis){
		
		this.distancia = dis;
	}
	
	public int getDistancia(){
		
		return this.distancia;
	}
	
	public boolean equals(Object obj){
		
		PalabraDistancia aux = (PalabraDistancia) obj;
		
		if(compare(aux, this)==0)return true;
		return false;
	}
	
	public String toString(){
		
		return this.palabra;
	}
}
