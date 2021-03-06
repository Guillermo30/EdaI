package org.eda1.actividad04.ejercicio03;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.TreeMap;
import java.util.TreeSet;

public class Thesaurus {
	
	protected TreeMap<String, TreeSet<String>> thesaurusMap;

	public Thesaurus() {
		
		this.thesaurusMap = new TreeMap<String, TreeSet<String>>();
	}

	public void add(String line) {
		
		String [] fragmLinea = line.split(" ");
		String clave = fragmLinea[0];
		
		if(!this.thesaurusMap.containsKey(clave)){
			
			TreeSet<String> sinonimos = new TreeSet<String>();
			
			for(int i=1; i<fragmLinea.length;i++){
				sinonimos.add(fragmLinea[i]);
			}
			
			this.thesaurusMap.put(clave, sinonimos);
		}
			
	}

	public void add(String word, String synonym) { 
		
		if(this.thesaurusMap.containsKey(word)){
			
			if(!this.thesaurusMap.get(word).contains(synonym)){
				this.thesaurusMap.get(word).add(synonym);
			}	
		}else{
			
			String clave = word;
			TreeSet<String> sinonimos = new TreeSet<String>();
			sinonimos.add(synonym);
			this.thesaurusMap.put(clave, sinonimos);
		}
		
	}

	public TreeSet<String> remove(String word) {
		
		TreeSet<String> salida = new TreeSet<String>();
		
		if(this.thesaurusMap.containsKey(word)){
			salida = this.thesaurusMap.get(word);
			this.thesaurusMap.remove(word);
		}
		
		return salida;
	}

	public boolean remove(String word, String synonym) {
		
		return this.thesaurusMap.get(word).remove(synonym);
	}

	public TreeSet<String> update(String word, LinkedList<String> synonyms) { 
		
		TreeSet<String> treeNuevo = new TreeSet<String>();
		
		if(this.thesaurusMap.containsKey(word)){
			
			Iterator<String> iterSinon = synonyms.iterator();
			
			while(iterSinon.hasNext()){
				
				treeNuevo.add(iterSinon.next());
			}
			
			this.thesaurusMap.get(word).clear();
			Iterator<String> IterSinonimosNuevos = treeNuevo.iterator();
			
			while(IterSinonimosNuevos.hasNext()){
				
				this.thesaurusMap.get(word).add(IterSinonimosNuevos.next());
			}
		}
		return treeNuevo;
	}

	public int size() {
		
		return this.thesaurusMap.size();
	}

	public TreeSet<String> getSynonymous(String word) {
		
		return this.thesaurusMap.get(word);
	}

	public int size(String word) { 
		
		return this.thesaurusMap.get(word).size();
	}

	public boolean isSynonymousOf(String word, String synonym) {
		
		if(this.thesaurusMap.containsKey(word)){
			
			return this.thesaurusMap.get(word).contains(synonym);
		}
		else
			return false; 
	}

	public boolean isSynonymous(String synonym) {
		
		if(this.thesaurusMap.containsKey(synonym)){
			
			return true;
			
		}else{
			
			return this.thesaurusMap.containsValue(synonym);
		}
	}

	public boolean hasSynonymous(String word) {
		
		return !this.thesaurusMap.get(word).isEmpty();
	}

	public String showThesaurus() { 
		
		String salida="";
		Iterator<String> iterClave =this.thesaurusMap.keySet().iterator();
		
		while(iterClave.hasNext()){
			
			String clave = iterClave.next();
			salida+=clave +": ";
			Iterator<String> iterSinonimos = this.thesaurusMap.get(clave).iterator();
			
			while(iterSinonimos.hasNext()){
				
				salida+=iterSinonimos.next() + " ";
			}
			
			salida+="\n";
		}
		return salida;
	}
}