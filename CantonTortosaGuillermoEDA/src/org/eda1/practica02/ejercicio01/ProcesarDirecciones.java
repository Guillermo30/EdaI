//
// Universidad de Almería
// Grado en Ingeniería Informática
// Fuente Java según Plantilla
//
// PRACTICA 2 : Uso de árboles binarios de búsqueda en Java
// ASIGNATURA : Estructura de datos y Algoritmos.
//
package org.eda1.practica02.ejercicio01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.eda1.estructurasdedatos.BSTree;
import org.eda1.estructurasdedatos.Iterator;

/**
 * Clase para procesar las direcciones de maquina.
 * @author Guillermo Cantón Tortosa
 * @version 1.0. 11.2013
 */
public class ProcesarDirecciones {

	static BSTree<DireccionMaquinas> treeDirecciones;

	// consturctores
	
	/**
	* Constructor vacio de Procesar dirreciones crea un arbol vacio.
	*/
	public ProcesarDirecciones() {
		this.treeDirecciones = new BSTree<DireccionMaquinas>();
	}
	
	/**
	* Constructor de procesar direcciones.
	* @param  treeDirecciones BSTree<DireccionMaquinas> arbol de posiciones a procesar.
	*/
	public ProcesarDirecciones(BSTree<DireccionMaquinas> treeDirecciones){
		
		this.treeDirecciones = new BSTree<DireccionMaquinas>();
		Iterator<DireccionMaquinas> ite = treeDirecciones.iterator();
		
		while(ite.hasNext()){
			this.treeDirecciones.add(ite.current());
		}
	}

	/**
	* Carga archivo a la estructura de datos .
	* @param  archivo String nombre de archivo.
	* @return BSTree<DireccionMaquinas> devuelve un arbol con las direcciones leidas del aarchivo.
	*/
	public BSTree<DireccionMaquinas> cargarArchivo(String archivo){
		
		ProcesarDirecciones salida = new ProcesarDirecciones();
		
		try {
			
			FileReader fi = new FileReader(archivo);
			BufferedReader br = new BufferedReader(fi);
			String linea = br.readLine();
			
			while(linea != null){
				salida.addDireccionMaquinaWithFind(linea.split(" ")[0], linea.split(" ")[1]);
				linea = br.readLine();
			}
			br.close();
			fi.close();
			
		} catch (IOException e) {
			
			System.out.println("Error de carga de fichero");
			e.printStackTrace();
			
		}
		
		this.treeDirecciones = salida.treeDirecciones;
		return salida.treeDirecciones;
		
	}
	
	/**
	* Guarda en un archivo de texto las diecciones con incidencias  .
	* @param  archivo String nombre de archivo.
	*/
	public void guardarDireccionesIncidencias(String archivo){
		
		try {
			
			FileWriter fi = new FileWriter(archivo);
			PrintWriter pw = new PrintWriter(fi);
			String resultado ="[";
			Iterator<DireccionMaquinas> it = this.treeDirecciones.iterator();
			
			while(it.hasNext()){
				resultado += it.next().toString();
				if(it.hasNext())resultado += "\n";
			}
			
			resultado += "]";
			pw.print(resultado);
			fi.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	* Añade Dierecciones de maquinas.
	* @param  direccion String dieccion de la maquina.
	* @param  maquina String con el monbre de la maquina.
	* @return bolean False si no intruduce la maquina nueva o True si la añade.
	*/
	public boolean addDireccionMaquina(String direccion, String maquina){
		
		Iterator<DireccionMaquinas> ite = this.treeDirecciones.iterator();
		while(ite.hasNext()){
			
			DireccionMaquinas aux = ite.next();
			if(aux.equals(new DireccionMaquinas(direccion)))return aux.addMaquinas(new MaquinaContador(maquina));
		}
		
		return this.treeDirecciones.add(new DireccionMaquinas(direccion, maquina));
	}
	
	/**
	* Añade Dierecciones de maquinas con busqueda.
	* @param  direccion String dieccion de la maquina.
	* @param  maquina String con el monbre de la maquina.
	* @return bolean False si no intruduce la maquina nueva o True si la añade.
	*/
	public boolean addDireccionMaquinaWithFind(String direccion, String maquina){
		
		DireccionMaquinas aux = this.treeDirecciones.find(new DireccionMaquinas(direccion));
		
		if(aux != null){
			
			return aux.addMaquinasWithFind(new MaquinaContador(maquina));
			
		}else{
			
			return this.treeDirecciones.add(new DireccionMaquinas(direccion, maquina));
			
		}
	}
	
	
	/**
	* Obtiene las maquinas con un determinado contador.
	* @param contardo int numero de contador.
	* @return resultado int con el numero de maquinas con un determinado contador.
	*/
	public int maquinasConContador(int contador){
		
		int resultado = 0;
		Iterator<DireccionMaquinas> iteDireccion = this.treeDirecciones.iterator();
		Iterator<MaquinaContador> iteMaquina;
		
		while(iteDireccion.hasNext()){
			
			iteMaquina = iteDireccion.next().getMaquinas().iterator();
			
			while(iteMaquina.hasNext()){
				
				if(iteMaquina.next().getContador()==contador)
					resultado ++;
			}
		}
		return resultado;
	}
	
	
	/**
	* Obtiene diecciones de las maquinas  con un determinado contador.
	* @param contardo int numero de contador.
	* @return resultado String con las direcciones de maquinas con un determinado contador.
	*/
	public String direccionMaquinasConContador(int contador){
		
		String resultado = "";
		Iterator<DireccionMaquinas> ite = this.treeDirecciones.iterator();
		Iterator<MaquinaContador> iteMaq;
		
		while(ite.hasNext()){
			
			DireccionMaquinas auxDir = ite.next();
			iteMaq = auxDir.getMaquinas().iterator();
			
			while(iteMaq.hasNext()){
				
				MaquinaContador auxMaq = iteMaq.next();
				
				if(auxMaq.getContador()==contador){
					
					resultado +="("+auxDir.getDireccion()+", "+auxMaq.getMaquina()+")\n";
				}
			}
		}
		return resultado;
	}
	
	
	/**
	* Obtiene el contador de una determinada maquina y dierccion.
	* @param direccion String dieccion ip de la maquina.
	* @param maquina String con el nombre de la maquina.
	* @return resultado String con las direcciones de maquinas con un determinado contador.
	*/
	public int contadorDeDireccionMaquina(String direccion, String maquina){
		
		DireccionMaquinas auxDir = this.treeDirecciones.find(new DireccionMaquinas(direccion));
		
		if(auxDir != null){
			
			MaquinaContador auxMaq = auxDir.getMaquinas().find(new MaquinaContador(maquina));
			
			if(auxMaq != null){
				
				return auxMaq.getContador();
				
			}else{
				
				return 0;
			}
			
		}else{
			
			return 0;
		}
	}

}
