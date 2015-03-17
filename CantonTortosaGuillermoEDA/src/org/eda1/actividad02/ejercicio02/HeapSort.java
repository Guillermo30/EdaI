//
// Universidad de Almería
// Grado en Ingeniería Informática
// Fuente Java según Plantilla
//
// ACTIVIDAD 2 : Implementar operaciones sobre un Heap y heapsort.
// ASIGNATURA : Estructura de datos y Algoritmos I.
//
package org.eda1.actividad02.ejercicio02;

import java.util.ArrayList;

import java.util.Comparator;

import org.eda1.actividad02.ejercicio01.Heap;

/**
 * Clase representar un heap ordenado.
 * @author Guillermo Cantón Tortosa
 * @version 1.0. 11.2013
 */
public class HeapSort<T>{
	
	public static void main(String[] args){
		
	}

	
	/**
	 * Ordena una lista mediante el algoritmo sortHeap
	 * @param List ArrayList<T> lista para ordenar 
	 * @param comp Comparator<T> determina el orden
	 */
	public static <T> void sortHeap(ArrayList<T> aList, Comparator<T> comp){
		
		Heap<T> heap = new Heap<T>(aList.size(), comp);
		
		for(int i=0; i<aList.size(); i++){
			heap.add(aList.get(i));
		}
		
		for(int i=0; i<aList.size(); i++){
			
			aList.set(i, heap.removeMin());
		}
	}


	/**
	 * Ordena una lista mediante el algoritmo heapSort
	 * @param List ArrayList<T> lista para ordenar 
	 * @param comp Comparator<T> determina el orden
	 */
	public static <T> void heapSort(ArrayList<T> aList, Comparator<T> comp){
		
		for(int i=aList.size()-1; i>=0; i--){
			
			siftDown(aList, i, aList.size(), comp);
		}
		
		for(int i=aList.size()-1; i>=0; i--){
			
			T temp = aList.get(0);
			aList.set(0, aList.get(i));
			aList.set(i, temp);
			siftDown(aList, 0, i-1, comp);
		}
		
		for(int i=0; i<(aList.size())/2; i++){
			
			T temp = aList.get(i);
			aList.set(i, aList.get((aList.size()-1)-i));
			aList.set((aList.size()-1)-i, temp);
		}
	}

	
	/**
	 * Metodo para undir un nodo en un heap
	 * @param List ArrayList lista donde esta el hepa almacenado
	 * @param first int  posición del nodo a undir
	 * @param last int  posición hasta donde se desea undir el nodo
	 * @param comp Comparator  determina el orden
	 */

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static <T> void siftDown(ArrayList aList, int first, int last, Comparator comp){
		
		int parent = first, child = (parent << 1) + 1;	

		while (child < last) {
			
			if (child < aList.size() - 1
					&& comp.compare(aList.get(child), aList.get(child + 1)) > 0)
				child++; // child is the right child (child = (2 * parent) + 2)
			
			if (comp.compare(aList.get(child), aList.get(parent)) >= 0)
				break;
			
			T temp = (T) aList.get(parent);
			aList.set(parent, aList.get(child));
			aList.set(child, temp);
			parent = child;
			child = (parent << 1) + 1;
		}
	}


}

