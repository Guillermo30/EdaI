package org.eda1.examenSegundoParcial.ejercicio01;

import java.util.TreeSet;

public class DatosCiudad implements Comparable{

	String pais;
	
	String continente;
	
	TreeSet<String> direcciones = new TreeSet<String>();
	
	public DatosCiudad(String pais, String conti, TreeSet<String> direc){
		this.pais = pais;
		this.continente = conti;
		this.direcciones = direc;
	}
	
	/**
	 * @return the pais
	 */
	public String getPais() {
		return pais;
	}

	/**
	 * @param pais the pais to set
	 */
	public void setPais(String pais) {
		this.pais = pais;
	}

	/**
	 * @return the continente
	 */
	public String getContinente() {
		return continente;
	}

	/**
	 * @param continente the continente to set
	 */
	public void setContinente(String continente) {
		this.continente = continente;
	}

	/**
	 * @return the direcciones
	 */
	public TreeSet<String> getDirecciones() {
		return direcciones;
	}

	/**
	 * @param direcciones the direcciones to set
	 */
	public void setDirecciones(TreeSet<String> direcciones) {
		this.direcciones = direcciones;
	}

	public int compareTo(Object otroDatosCuidad){
		return this.direcciones.size() - ((DatosCiudad)otroDatosCuidad).getDirecciones().size();
	}
	
}
