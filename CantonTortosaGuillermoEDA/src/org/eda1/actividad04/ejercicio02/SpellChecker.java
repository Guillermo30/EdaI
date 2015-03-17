package org.eda1.actividad04.ejercicio02;

import java.util.LinkedList;
import java.util.TreeSet;
import java.util.Iterator;

public class SpellChecker {
	
	protected TreeSet<String> dictionarySet;
	protected TreeSet<String> documentSet;
	
	public SpellChecker(){ 
		
		this.dictionarySet = new TreeSet<String>();
		this.documentSet = new TreeSet<String>();
		
	}
	
	public void addToDictionary(String word) { 
		
		if (!this.dictionarySet.contains(word)){
			this.dictionarySet.add(word);
		}
	}
	
	public void addToDictionary(LinkedList<String> additionalWords) {
		
		Iterator<String> iterPalabras =additionalWords.iterator();
		while(iterPalabras.hasNext()){
			addToDictionary(iterPalabras.next());
		}
	}
	
	public void addToDocument(String line) {
		if(!this.documentSet.contains(line))
			this.documentSet.add(line);
	}
	
	public LinkedList<String> compare() {
		
		LinkedList<String> salida = new LinkedList<String>();
		Iterator<String> iterDocumento = documentToList().iterator();
		
		while(iterDocumento.hasNext()){
			
			String palabra = iterDocumento.next();
			
			if(!isInDictionary(palabra)){
				salida.add(palabra);
			}
		}
		return salida;
	}
	
	public LinkedList<String> dictionaryToList() {
		
		LinkedList<String> salida = new LinkedList<String>();
		Iterator<String> iterDiccionario = this.dictionarySet.iterator();
		
		while(iterDiccionario.hasNext()){
			
			salida.add(iterDiccionario.next());
		}
		return salida;
	}
	
	public LinkedList<String> documentToList() {
		
		LinkedList<String> salida = new LinkedList<String>();
		Iterator<String> iterDocumento = this.documentSet.iterator();
		
		while(iterDocumento.hasNext()){
			
			salida.add(iterDocumento.next());
		}
		return salida;
	}
	
	public boolean isInDictionary(String word) {
		
		return this.dictionarySet.contains(word);
	}
	
	public int sizeDictionary() { 
		
		return this.dictionarySet.size();
	}
}
