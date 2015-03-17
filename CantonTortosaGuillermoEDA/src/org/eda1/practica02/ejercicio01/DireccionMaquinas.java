//
// Universidad de Almer�a
// Grado en Ingenier�a Inform�tica
// Fuente Java seg�n Plantilla
//
// PRACTICA 2 : Uso de �rboles binarios de b�squeda en Java
// ASIGNATURA : Estructura de datos y Algoritmos.
//
package org.eda1.practica02.ejercicio01;

import org.eda1.estructurasdedatos.*;

/**
 * Clase para crear una direccion de maquina.
 * @author Guillermo Cant�n Tortosa
 * @version 1.0. 11.2013
 */
public class DireccionMaquinas implements Comparable<Object> {
	
	private String direccion;
	private BSTree<MaquinaContador> maquinas;
	
	/**
	* Constructor vacio de diereccciones de maquina.
	*/
	public DireccionMaquinas(){
		
		this.direccion="";
		this.maquinas=null;
		
	}
	
	/**
	* Constructor de diereccciones de maquina.
	* @param direccion String con la direccion de la maquina
	*/
	public DireccionMaquinas(String direccion){
		
		this.direccion=direccion;
		this.maquinas=new BSTree<MaquinaContador>();
		
		
	}
	
	/**
	* Constructor de diereccciones de maquina.
	* @param direccion String con la direccion de la maquina
	* @param maquina String con el nombre de la maquina.
	*/
	public DireccionMaquinas(String direccion,String maquina){
		
		this.direccion=direccion;
		this.maquinas = new BSTree<MaquinaContador>();
		MaquinaContador nueva= new MaquinaContador(maquina);
		this.maquinas.add(nueva);
		
		
	}
	
	/**
	* Constructor de diereccciones de maquina.
	* @param direccion String con la direccion de la maquina
	* @param maquina String con el nombre de la maquina.
	* @param contador int el numero de maquinas.
	*/
	public DireccionMaquinas(String direccion,String maquina,int contador){
		
		this.direccion=direccion;
		this.maquinas = new BSTree<MaquinaContador>();
		MaquinaContador nueva= new MaquinaContador(maquina,contador);
		this.maquinas.add(nueva);
		
		
	}

	
	/**
	* M�todo cambiar la direccion de una maquina.
	* @param dir String con la direccion nueva de la maquina.
	* @param int numero de linea hasta la que hay que borrar.
	*/
	public void setDireccion(String dir){
	
		this.direccion = dir;
	
	}
	
	/**
	* M�todo obtener la direccion de una maquina.
	* @return String con la direccion.
	*/
	public String getDireccion(){
		return this.direccion;
	}
	
	/**
	* M�todo obtener las maquinas de la estructura de datos.
	* @return BSTree<MaquinaContador> maquinas guardadas.
	*/
	public BSTree<MaquinaContador>getMaquinas(){
		return this.maquinas;
		
	}
	
	/**
	* M�todo para a�adir una maquina a la estructura de datos.
	* @return BSTree<MaquinaContador> maquinas guardadas.
	*/
	public boolean addMaquinas(MaquinaContador mc){
		
		return this.maquinas.add(mc);
		
	}
	
	/**
	* M�todo para a�adir una maquina el metodo busca la maquina si esta aumenta en contador y si no esta la a�ade.
	* @param mc MaquinaContador maquina a buscar y a�adir.
	* @return Boolean True si esta o la a�ande maquina  False en caso contrariao
	*/
	public boolean addMaquinasWithFind(MaquinaContador mc){
		
		MaquinaContador aux = this.maquinas.find(mc);
		
		if(aux != null){
			aux.incrementarContador();
			return true;
		}else{
			return this.maquinas.add(mc);
		}
	}
	
	/**
	* M�todo CompareTo
	* @param nodo Object objeto a comparar
	* @return int 1 si es mayor, -1 si es menor y 0 si son iguales
	*/
	@Override
	public int compareTo(Object nodo) {		
		return this.direccion.compareTo(((DireccionMaquinas)nodo).getDireccion());
	}
	
	/**
	* M�todo Equals
	* @param obj Object objeto a comparar
	* @return boolean True si es iguala, False si es diferente.
	*/
	@Override
	public boolean equals(Object obj){
		DireccionMaquinas aux = (DireccionMaquinas)obj;
		return this.direccion.equals(aux.direccion);
	}

	/**
	* M�todo ToString
	* @return boolean True si es iguala, False si es diferente.
	*/
	public String toString(){

		String resultado ="("+this.direccion+", "+this.maquinas.toString()+")";
		return resultado;
	}


	
}
