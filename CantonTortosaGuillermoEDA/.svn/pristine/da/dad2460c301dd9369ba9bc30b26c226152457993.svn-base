package org.eda1.examenParcialGrupoB.ejercicio01;

import java.util.Comparator;

public class MyPQWithHeap<T> implements MyPriorityQueue<T> {
	private Heap<T> heap;
	public MyPQWithHeap(){
		this.heap = new Heap<T>();
	}
	public MyPQWithHeap(Comparator<T> comp){
		this.heap = new Heap<T>(comp);
	}
	public MyPQWithHeap(int initialCapacity, Comparator<T> comp){
		this.heap = new Heap<T>(initialCapacity, comp);
	}
	@Override
	public int size() {
		return this.heap.size();
	}

	@Override
	public boolean isEmpty() {
		return this.heap.isEmpty();
	}

	@Override
	public void add(T element) {
		this.heap.add(element);
		
	}

	@Override
	public T peek() {
		return this.heap.getValue(0);
	}

	@Override
	public T poll() {
		return this.heap.removeMin();
	}

}
