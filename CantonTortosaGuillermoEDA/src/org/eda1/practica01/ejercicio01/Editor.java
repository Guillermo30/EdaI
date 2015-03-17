//
// Universidad de Almería
// Grado en Ingeniería Informática
// Fuente Java según Plantilla
//
// PRACTICA 1 : Estructuras de datos lineales de la Java Collections Framework
// ASIGNATURA : Estructura de datos y Algoritmos.
//
package org.eda1.practica01.ejercicio01;
import java.util.*;
/**
 * Clase para crear un editor de textos.
 * @author Guillermo Cantón Tortosa
 * @version 1.0. 10.2013
 */

public class Editor {
	// declaracion de variables.
	
	public final static char COMMAND_START = '$';
	
	public final static char DELIMITER = '%';

	public final static String INSERT_COMMAND = "$Insert";

	public final static String DELETE_COMMAND = "$Delete";

	public final static String LINE_COMMAND = "$Line";

	public final static String DONE_COMMAND = "$Done";

	public final static String LAST_COMMAND = "$Last";

	public final static String GETLINES_COMMAND = "$GetLines";

	public final static String CHANGE_COMMAND = "$Change";

	public final static String BAD_LINE_MESSAGE = "Error: a command should start with "
			+ COMMAND_START + ".\n";

	public final static String BAD_COMMAND_MESSAGE = "Error: not one of the given commands.\n";

	public final static String INTEGER_NEEDED = "Error: The command should be followed by a blank space, "
			+ "\nfollowed by an integer.\n";

	public final static String TWO_INTEGERS_NEEDED = "Error: The command should be followed by a blank space, "
			+ "\nfollowed by an integer, followed by a blank space, "
			+ "followed by an integer.\n";

	public final static String FIRST_GREATER = "Error: the first line number is greater than the second.\n";

	public final static String FIRST_LESS_THAN_ZERO = "Error: the first line number given is less than 0.\n";

	public final static String SECOND_TOO_LARGE = "Error: the second line number given is greater than the "
			+ "\nnumber of the last line in the text.\n";

	public final static String M_LESS_THAN_ZERO = "Error: the number is less than 0.\n";

	public final static String M_TOO_LARGE = "Error: the number is larger than the number of lines in the text.\n";

	public final static String LINE_TOO_LONG = "Error: the line exceeds the maximum number of characters allowed, ";

	public final static String INCORRECT_DELIMITERS_NUMBER = "Error: Delimiter must occur three times. Please try again.\n";

	public final static String NO_DELIMITERS_BEGIN_END = "Error: Bad Expression format, delimiters should be at the beginning "
			+ "\nand at the end. Please try again.\n";

	public final static String TWO_CONSECUTIVE_DELIMITERS_AT_THE_BEGINNING = "Error: Bad Expression format, two consecutive delimiters "
			+ "\nat the beginning. Please try again.\n";

	public final static int MAX_LINE_LENGTH = 80;

	protected LinkedList<String> text;

	protected ListIterator<String> current;

	protected boolean inserting;

	/**
	* Constructor para el editor.
	*/
	public Editor() {
		text = new LinkedList<String>();
		current = text.listIterator();
		inserting = false;
	}

	
	/**
	* Método que interpreta una cadena de de caracteres, reconociendo los diferentes tipos de 
	* de operaciones sobre el string.
	* @param string cadena a interpretar.
	* @return la carta.
	*/
	public String interpret(String string) {
		if(string.equals(""))throw new RuntimeException(BAD_LINE_MESSAGE);
		if(string.startsWith("$")){
			
			if(string.equals(INSERT_COMMAND)){
				
				this.inserting = true;
				
			}else{
				
				this.inserting = false;
				
				if(string.startsWith(CHANGE_COMMAND)){
					
					try{
						
						change(string);
						
					}catch (RuntimeException e){
						
						throw new RuntimeException(e.getMessage());
					}
					
				}else{
					
					if(string.startsWith(DELETE_COMMAND)){
						
						int i;
						int j;
						String[] cadena = string.split(" ");
						
						try {  
							
							i=Integer.parseInt(cadena[1]);
							j=Integer.parseInt(cadena[2]);            
				        }
				        catch (RuntimeException e){

				            throw new RuntimeException(TWO_INTEGERS_NEEDED);
				        }
						
						if(i>j)throw new RuntimeException(FIRST_GREATER);
						
						if(i<0)throw new RuntimeException(FIRST_LESS_THAN_ZERO);
						
						if(j>=this.text.size())throw new RuntimeException(SECOND_TOO_LARGE);
							delete(i, j);
						
					}else{
						if(string.startsWith(LINE_COMMAND)){
							
							int aux =Integer.parseInt(string.split(" ")[1]);
							
							if(aux>=0&&aux<this.text.size()){
								
								setCurrentLineNumber(aux);
								System.out.println(" la linea es :"+aux);
								
							}else{
								
								return null;
							}
							
						}else{
							
							if(string.equals(DONE_COMMAND)){
								
								return done();
								
							}else{
								
								if(string.equals(LAST_COMMAND)){
									
									int a;
									String cadena ="";
									
									if(this.text.isEmpty()){
										a=0;
									}else{
										a=this.text.size()-1;
									}
									cadena += a;
									return cadena;
									
								}else{
									
									if(string.startsWith(GETLINES_COMMAND)){
										int i;
										int j;
										String[] aux = string.split(" ");
										i= Integer.parseInt(aux[1]);
										j= Integer.parseInt(aux[2]);
										if(i>j)throw new RuntimeException(FIRST_GREATER);
										if(this.text.isEmpty())return null;
										return getLines(i, j);
										
									}else{
										
										throw new RuntimeException(BAD_COMMAND_MESSAGE);
									}
								}
							}
						}
					}
				}
			}
		}else{
			
			if(this.inserting){
				
				insert(string);
				
			}else{
				
				throw new RuntimeException(BAD_LINE_MESSAGE);
			}
		}
		
		return null;
	}

	/**
	* Método que insertar una cadena de de caracteres.
	* @param string cadena a insertar.
	*/
	protected void insert(String s) {
		
		if (s.length() > Editor.MAX_LINE_LENGTH) {
			
			throw new RuntimeException(Editor.LINE_TOO_LONG);
	
		} else {
			
			this.current.add(s);
			
		}
			
	}
	

	/**
	* Método para eliminar lineas de texto.
	* @param int numero de linea.
	* @param int numero de linea hasta la que hay que borrar.
	*/
	protected void delete(int m, int n) {
		if (m > n)
			
			throw new RuntimeException(Editor.FIRST_GREATER);
		
		else if (m < 0)
			
			throw new RuntimeException(Editor.FIRST_LESS_THAN_ZERO);
		
		else if (n >= this.text.size())
			
			throw new RuntimeException(Editor.SECOND_TOO_LARGE);
		
		else {
			
			for (int i = m; i <= n; i++) {
				
				this.text.remove(m);
				this.current = this.text.listIterator(m);
				
			}
		}
	}
	
	/**
	* Método para eliminar lineas de texto.
	* @param int numero de linea desde la que hay que borrar.
	* @param int numero de linea hasta la que hay que borrar.
	*/
	protected void setCurrentLineNumber(int m) {
		
		try {
			
			this.current = this.text.listIterator(m);
			
		} catch (RuntimeException e) {
			
			throw new RuntimeException(Editor.M_TOO_LARGE);
			
		}

	}

	/**
	* Método para imprimir el texto completo.
	* @return String contenido. Devuelve texto.
	*/
	public String done(){
		
		String textosal="";
		boolean ultimoPuntero=false;
		
		for(int i=0; i<this.text.size();i++){
			
			if (this.current.nextIndex()==i){
				
				textosal+=">  "+this.text.get(i)+"\n";
				ultimoPuntero=true;
				
			}else
				textosal+="   "+this.text.get(i)+"\n";
		}
		if(!ultimoPuntero)
			textosal+=">  \n";
		return textosal;
	}

	/**
	* Método para devolver el número de línea que tiene la última línea en el texto.
	* @return String contenido.Devuelve texto.
	*/
	protected String last(){
		
		String contenido="";
		int posicion = text.size()-1;
		
		return contenido+=posicion;	
	}
	
	/**
	* Método para devolver el número de línea y el contenido de dicha línea, desde la línea m hasta la línea n, ambas inclusive.
	* @param int m primera linea a devolver.
	* @param int n ultima linea a devolver.
	* @return String salida. Devuelve texto.
	*/
	protected String getLines(int m, int n) {
		
		String salida = "";
		
		if (m > n)
			
			throw new RuntimeException(Editor.FIRST_GREATER);
		
		else if (m < 0)
			
			throw new RuntimeException(Editor.FIRST_LESS_THAN_ZERO);
		
		else if (n >= this.text.size())
			
			throw new RuntimeException(Editor.SECOND_TOO_LARGE);
		
		else {
			
			for (int i = m; i <= n; i++) {
				salida += i + "\t" + this.text.get(i);
				if (i != n)
					salida += "\n";
				
			}
		}

		return salida;
	}

	/**
	* Método corregir errores.
	* @param String parametros.
	*/
	protected void change(String parameter) {
		
		String fallos = parameter.replace("$Change ", "");
		int numDelimitadores = 0;
		
		for (int i = 0; i < fallos.length(); i++)
			
			if (fallos.charAt(i) == '%')
				
				numDelimitadores++;
		
		if (numDelimitadores != 3)
			
			throw new RuntimeException(Editor.INCORRECT_DELIMITERS_NUMBER); 
		
		if (fallos.charAt(fallos.length() - 1) != '%')
			
			throw new RuntimeException(Editor.NO_DELIMITERS_BEGIN_END); 
		
		if (fallos.charAt(0) == fallos.charAt(1))
			
			throw new RuntimeException(Editor.TWO_CONSECUTIVE_DELIMITERS_AT_THE_BEGINNING); 

		String[] limitadores = new String[3];
		limitadores = parameter.split("%");
		String buscado = "";
		String remplazar = "";
		
		if (limitadores.length == 2) {
			
			buscado = limitadores[1];
			remplazar = ""; 
			
		} else {
			
			try {
				
				buscado = limitadores[1];
				remplazar = limitadores[2];
				
			} catch (RuntimeException e) {
				
				throw new RuntimeException(Editor.TWO_INTEGERS_NEEDED);
				
			}
		}
		
		int lugar = this.current.nextIndex();
		String cadenaAntes = this.text.get(lugar);
		String cadenaProcesada = cadenaAntes.replace(buscado, remplazar);
		delete(lugar, lugar);
		insert(cadenaProcesada);
		this.current = this.text.listIterator(lugar);
	}
	
	/**
	* Método interpreta los comandos leidos por escaner.
	* @param Scanner entrada.
	*/
	protected void tryToDelete(Scanner entrada){
		
		
		interpret(entrada.nextLine());
		
	}
	
	/**
	* Método interpreta los comandos leidos por escaner.
	* @param Scanner entrada.
	*/
	protected void tryToSetCurrentLineNumber(Scanner entrada){
		
		interpret(entrada.nextLine());
		
	}
	
	/**
	* Método interpreta los comandos leidos por escaner.
	* @param Scanner entrada.
	*/
	protected void tryToGetLines(Scanner entrada){
		
		interpret(entrada.nextLine());
		
		
	}
	
	/**
	* Método interpreta los comandos leidos por escaner.
	* @param Scanner entrada.
	*/
	protected void tryToChange(Scanner entrada){
		
		interpret(entrada.nextLine());
		
		
	}
	
}
