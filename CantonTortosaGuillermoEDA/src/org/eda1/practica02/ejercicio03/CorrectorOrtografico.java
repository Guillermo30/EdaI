//
// Universidad de Almería
// Grado en Ingeniería Informática
// Fuente Java según Plantilla
//
// PRACTICA 2 : Uso de árboles binarios de búsqueda en Java
// ASIGNATURA : Estructura de datos y Algoritmos.
//
package org.eda1.practica02.ejercicio03;

import java.io.*;
import java.util.ArrayList;


import org.eda1.estructurasdedatos.*;

/**
 * Clase que implementa un Corrector Ortografico.
 * @author Guillermo Cantón Tortosa
 * @version 1.0. 11.2013
 */
public class CorrectorOrtografico {
	
	AVLTree<String> treePalabras;
	

	/**
	* Constructor del corrector
	* crea solo la esturtuctura de datos.
	*/
	public CorrectorOrtografico(){
		
		this.treePalabras = new AVLTree<String>();
		
	}
	

	/**
	* Constructor del corrector
	* @param treePalabras AVLTree<String> con las palabras.
	*/
	public CorrectorOrtografico(AVLTree<String> treePalabras){
		
		this.treePalabras = treePalabras;
		
	}
	
	
	/**
	* Método cargar en un arbol le diccionario desde un archivo
	* @param archivo String direccion del archivo.
	* @return AVLTree<String> arbol con el diccionario cargado.
	*/
	public AVLTree<String> cargarDiccionario(String archivo){
		
		FileReader fi;
		
		try {
			
			fi = new FileReader(archivo);
			BufferedReader br = new BufferedReader(fi);
			String linea = br.readLine();
			
			while(linea!=null){
				this.treePalabras.add(linea);
				linea = br.readLine();
				
			}
			
			br.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return this.treePalabras;
	}
	
	/**
	* Método para escribir el dicciconario en un archivo.
	* @param archivo String nombre del archivo.
	*/
	public void guardarDiccionario(String archivo){
		
		FileWriter fw;
		
		try {
			
			fw = new FileWriter(archivo);
			PrintWriter pw = new PrintWriter(fw);
			Iterator<String> ite = this.treePalabras.iterator();
			
			while(ite.hasNext()){
				pw.println(ite.next());
			}
			
			pw.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static int minimun(int a, int b, int c){
		if(a<=b && a<=c)return a;
		if(b<=a && b<=c)return b;
		return c;
	}
	
	
	public int computeLevenshteinDistance(String str1, String str2){
		return computeLevenshteinDistance(str1.toCharArray(), str2.toCharArray());
	}
	
	private static int computeLevenshteinDistance(char[] str1, char[] str2){
		int [][]distance = new int[str1.length+1][str2.length+1];
		 
        for(int i=0;i<=str1.length;i++)
        {
                distance[i][0]=i;
        }
        for(int j=0;j<=str2.length;j++)
        {
                distance[0][j]=j;
        }
        for(int i=1;i<=str1.length;i++)
        {
            for(int j=1;j<=str2.length;j++)
            { 
                  distance[i][j]= minimun(distance[i-1][j]+1,
                                        distance[i][j-1]+1,
                                        distance[i-1][j-1]+
                                        ((str1[i-1]==str2[j-1])?0:1));
            }
        }
        return distance[str1.length][str2.length];
	}
	
	
	
	
	public ArrayList<String> listaSugerencias(int n, String s){
		ArrayList<String> resultado = new ArrayList<String>();
		Iterator<String> ite = this.treePalabras.iterator();
		while(ite.hasNext()){
			String cadena = ite.next();
			if(computeLevenshteinDistance(s, cadena)<n)resultado.add(cadena);
		}
		return resultado;
	}
	public void addPalabra(String palabra){
		if(!find(palabra))this.treePalabras.add(palabra);
	}
	public boolean containsPalabra(String palabra){
		return find(palabra);
	}
	public int size(){
		return this.treePalabras.size();
	}
	public boolean add(String palabra){
		if(!find(palabra))return this.treePalabras.add(palabra);
		return false;
	}
	public boolean find(String palabra){
		String aux = this.treePalabras.find(palabra);
		if(aux!=null)return true;
		return false;
	}
}
