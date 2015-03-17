
package org.eda1.examenParcialGrupoA.ejercicio02;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.eda1.estructurasdedatos.AVLTree;
import org.eda1.estructurasdedatos.Iterator;
/**
 * Clase ProcessText 
 * @author Guillermo Cantón.
 * @version 1.0 
 *
 */
public class ProcessText {
	
	private AVLTree<WordFrequency> avlWords;
	
	public ProcessText(){
		this.avlWords = new AVLTree<WordFrequency>();
	}
	
	public ProcessText(AVLTree<WordFrequency> avlW){
		this.avlWords=avlW;
	}
	
	public AVLTree<WordFrequency> loadFile(String file) throws FileNotFoundException{
		BufferedReader lector = new BufferedReader(new FileReader(file));
		ArrayList<String> arrayLineas = new ArrayList<String>();
		
		try{
			while (lector.ready()){
				arrayLineas.add(removeSpecialCharacters(lector.readLine())); //Añadimos a un array las lineas ya procesadas.
			}
			lector.close();
		}
		catch(IOException e){
			System.out.println(e);
		}
		for(int i=0; i<arrayLineas.size();i++){
			String [] separador = arrayLineas.get(i).split(" ");
			for(int j=0; j<separador.length;j++){
				addWord(separador[j]);
			}
		}
		return this.avlWords;
	}
	
	public String removeSpecialCharacters(String input) {
		String specialChart = ",.;:_()[]{}<>*+-/=%&$|'\"^¿?!¡0123456789";
		String output = input;
		for (int i = 0; i < specialChart.length(); i++) {
		output = output.replace(String.valueOf(specialChart.charAt(i)), "");
		}
		return output.toLowerCase();
	}
	
	public boolean addWord(String word){
		WordFrequency palabraAInsertar = new WordFrequency(word);
		if(this.avlWords.contains(palabraAInsertar)){
			this.avlWords.find(palabraAInsertar).incrementFrequency();
		}
		else{
			 return this.avlWords.add(palabraAInsertar);
		}
		return false;
	}

	public int getFrecuencyOfWord(String string) {
		WordFrequency aux = new WordFrequency(string);
		return this.avlWords.find(aux).getFrequency();
	}
	
	public ArrayList<String> getWordsWithFrequency(int frec) {
		ArrayList<String> salida = new ArrayList<String>();
		
		Iterator<WordFrequency> iterArbol = this.avlWords.iterator();
		while(iterArbol.hasNext()){
			iterArbol.next();
			if((iterArbol.current()).getFrequency()==frec){
				salida.add((iterArbol.current()).getWord());
			}
		}
		return salida;
	}
}
