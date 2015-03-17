
package org.eda1.examenParcialGrupoA.ejercicio02;
/**
 * Clase WordFrequency
 * @author Guillermo Cantón
 * @version 1.0 
 * 
 *
 */
public class WordFrequency implements Comparable<Object> {
	
	private String word;
	
	private int frequency;
	
	public WordFrequency(String w, int f){
		this.word=w;
		this.frequency=f;
	}
	
	public WordFrequency(String w){
		this.word=w;
		this.frequency=1;
	}

	public WordFrequency(){
		this.word="";
		this.frequency=1;
	}
	
	public String getWord() {
		return word;
	}
	
	public void setWord(String word) {
		this.word = word;
	}
	
	public int getFrequency() {
		return frequency;
	}
	
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
	
	public void incrementFrequency(){
		this.frequency++;
	}
	
	@Override
	public int compareTo(Object o) {
		WordFrequency aux = (WordFrequency) o;
		return getWord().compareTo(aux.getWord());
	}
	
	@Override
	public boolean equals(Object obj){
		if(obj == null)
			return false;
		else{
			WordFrequency aux = (WordFrequency) obj;
			return getWord().equals(aux.getWord());
		}
		
	}
}
