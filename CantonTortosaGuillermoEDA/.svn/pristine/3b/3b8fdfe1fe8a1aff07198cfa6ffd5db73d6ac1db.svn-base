package org.eda1.actividadesSeptiembre.actividad04;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class SimilarWords {

	//public SimilarWords(){
		
	//}
	
	public List<String> readWords(String stringArchivoEntrada) {
		// TODO Auto-generated method stub
		
		List<String> palabras = new ArrayList<String>();
		
		try {
			FileReader fr = new FileReader(stringArchivoEntrada);
			BufferedReader bf = new BufferedReader(fr);
			String string;
			while((string = bf.readLine()) != null){
				//string = bf.readLine();
				palabras.add(string);
			}
			
			fr.close();
			bf.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return palabras;
		
	
	}
	//misma longitud y diferencia en un caracter
	private static boolean oneCharOff(String word1,String word2){
		
		if(word1.length()!=word2.length()){
			return false;
		}
		if(word1.equals(word2)){
			return false;
		}
		
		int diferencias=0;
		for(int i=0;i<word1.length();i++){		
			if(word1.charAt(i)!=(word2.charAt(i))){
				diferencias++;
			}
			if(diferencias>1){
				return false;
			}
		}
		return true;
		
	}

	//actualiza el mapa
	private static <KeyType> void update(Map<KeyType,List<String>> m,KeyType key,String value){
		
		//List<String> aux = new LinkedList<String>();
		
		//aux = m.get(key);
		//aux.add(value);
		//m.put(key, aux);
		if( !m.containsKey(key)){
			
			List<String>aux = new ArrayList<String>();
			m.put(key,aux);
			
			if(value!=null&&!value.equals(key)){
				m.get(key).add(value);
			}		
		}
		
		if(value!=null && !m.get(key).contains(value) && !value.equals(key)){
			m.get(key).add(value);	
		}
	}
	
	public Map<Integer, Integer> computeDistributionOfDictionary(List<String> words){ 
			//int inicializacionKey = 2;
			Map<Integer, Integer> salida = new TreeMap<Integer, Integer>();
			Iterator<String> it2 = words.iterator();
			 //current="";
			
			while(it2.hasNext()){
				//int inicializacionValue = 0;
				//salida.put(inicializacionKey, inicializacionValue);
				String current = it2.next();
				if(!salida.containsKey(current.length())){
					salida.put(current.length(),0);
				}
			}	
			
			it2 = words.iterator();
				
			while(it2.hasNext()){
				String current2 = it2.next();
				salida.put(current2.length(), salida.get(current2.length())+1);
			}
				
		return salida;
	}
	
	//Calcula un mapa en el que las claves son palabras y valores listas de parablas de difierern
	//un unico caracate de la clave correspondiente.Utiliza un algoritomo cuadratico.
	public Map<String, List<String>> computeSimilarWordsBruteForce(List<String> words) {
			
			Map<String, List<String>> salida = new TreeMap<String, List<String>>();
			String[] palabras = new String[words.size()];
			words.toArray(palabras);
			
			for(int i=0 ; i<palabras.length;i++){
					for(int j=i+1;j<palabras.length;j++){
						if(oneCharOff(palabras[i],palabras[j])){
							update(salida,palabras[i],palabras[j]);
							update(salida,palabras[j],palabras[i]);	
						}
					}	
				}	
		return salida;
	}

	public Map<String, List<String>> computeSimilarWordsImproved(List<String> words) {
		
		Map<String, List<String>> salida = new TreeMap<String, List<String>>();
		Map<Integer,List<String>> aux = new TreeMap<Integer, List<String>>();
		//List<String> lista = new LinkedList<String>();
		
		
		for(String word:words){
			update(aux,word.length(),word);
		}
		
		for(List<String>groupsWords:aux.values()){
			
			String[]palabras=new String[groupsWords.size()];
			groupsWords.toArray(palabras);
			
			for(int i=0;i<palabras.length;i++){
				for(int j=i+1;j<palabras.length;j++){
					if(oneCharOff(palabras[i],palabras[j])){
						
						update(salida,palabras[i],palabras[j]);
						update(salida,palabras[j],palabras[i]);
						
					}
				}
			}
		}
		return salida;
	}

	public Map<String, List<String>> computeSimilarWords(List<String> wordsEntry){
		
		Map<String, List<String>> salida = new TreeMap<String, List<String>>();
		Map<Integer,List<String>>aux=new TreeMap<Integer,List<String>>();
		
		for(String word:wordsEntry){
			update(aux,word.length(),word);
		}
		
		for(Map.Entry<Integer,List<String>>entry:aux.entrySet()){
			
			List<String>gruposPalbras=entry.getValue();
			int NumeroGrupo=entry.getKey();
			
			for(int i=0;i<NumeroGrupo;i++){
				Map<String,List<String>>aux2=new TreeMap<String,List<String>>();
				for(String w:gruposPalbras){
					
					String palabrasIdem=w.substring(0,i)+w.substring(i+1);
					update(aux2,palabrasIdem,w);
					
				}
				
				for(List<String>palabraClique:aux2.values()){
					if(palabraClique.size()>=2)
						for(String string1:palabraClique)
							for(String string2:palabraClique)
								if(string1!=string2){
									update(salida,string1,string2);
								}
					
				}
			}
		}
		return salida;
	}

	public List<String> findMostChangeable(Map<String, List<String>> mapSimilarWords) {
		
		List<String>salida=new ArrayList<String>();
		int cambio=0;
		
		for(Map.Entry<String,List<String>>entry:mapSimilarWords.entrySet()){
			
			if(entry.getValue().size()>cambio){
				cambio=entry.getValue().size();
			}
		}
		
		for(Map.Entry<String,List<String>>entry:mapSimilarWords.entrySet()){
			
			if(entry.getValue().size()==cambio){
				salida.add(entry.getKey());
			}
		}
		
		return salida;
	}

	public String showMostChangeables(List<String> mostChangeable,Map<String, List<String>> mapSimilarWords) {
		String salida="";
		
		for(String string:mostChangeable){
			
			salida+=string+": ";
			List<String> words=mapSimilarWords.get(string);
			
			for(String word:words){
				salida+=word+"\n"+" ";
			}
			
			salida+="("+words.size()+" words)\n";
		}	
		return salida;
	}

	public Map<String, Integer> findHighChangeables(Map<String, List<String>> mapSimilarWords, int i) {
		
		Map<String, Integer>salida=new TreeMap<String,Integer>();
		
		for(Map.Entry<String, List<String>>entry: mapSimilarWords.entrySet()){
			
			List<String>words=entry.getValue();
			
			if(words.size()>=i){
				salida.put(entry.getKey(),words.size());
			}
		}
		return salida;
	}

}
