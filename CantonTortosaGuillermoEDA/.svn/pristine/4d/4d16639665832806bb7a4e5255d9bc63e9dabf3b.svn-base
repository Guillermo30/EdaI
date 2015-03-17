package org.eda1.actividad04.ejercicio02;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class SpellCheckerTester {
	
	protected SpellChecker spellChecker;
	
	public SpellCheckerTester(){
		
		this.spellChecker = new SpellChecker();
	}
	
	public void loadDictionary(String fileName){
		
		try{
			BufferedReader lector = new BufferedReader(new FileReader(fileName));
			
			while (lector.ready()){
				
				this.spellChecker.addToDictionary(lector.readLine());
			}
			lector.close();
		}
		catch(IOException e){
			System.out.println(e);
		}
	}
	
	public void extendDictionary(LinkedList<String> wordsNoInDictionary){
		
		this.spellChecker.addToDictionary(wordsNoInDictionary);
	}
	
	@SuppressWarnings("deprecation")
	
	public void loadDocument(String fileName){
		
		try{
			BufferedReader lector = new BufferedReader(new FileReader(fileName));
			
			while (lector.ready()){
				
				String lineaAFiltrar = lector.readLine().toLowerCase();
				String lineaFiltrada = "";
				
				for(int i=0; i<lineaAFiltrar.length();i++){
					if(Character.isLetterOrDigit(lineaAFiltrar.charAt(i)) || Character.isSpace(lineaAFiltrar.charAt(i))){
						lineaFiltrada+=lineaAFiltrar.charAt(i);
					}
				}	
			
				String [] arrayLinea = lineaFiltrada.split(" ");
				
				for(int i=0; i<arrayLinea.length;i++){
					this.spellChecker.documentSet.add(arrayLinea[i]);
				}
			}
			lector.close();
		}
		catch(IOException e){
			System.out.println(e);
		}
	}
	
	public LinkedList<String> showDifferences(){
		
		return this.spellChecker.compare();
	}
	
	public boolean isInDictionary(String word){
		
		return this.spellChecker.isInDictionary(word);
	}
	
	public int sizeDictionary(){
		
		return this.spellChecker.sizeDictionary();
	}
}
