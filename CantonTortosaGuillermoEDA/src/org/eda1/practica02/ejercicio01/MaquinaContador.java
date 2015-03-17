//
// Universidad de Almer�a
// Grado en Ingenier�a Inform�tica
// Fuente Java seg�n Plantilla
//
// PRACTICA 2 : Uso de �rboles binarios de b�squeda en Java
// ASIGNATURA : Estructura de datos y Algoritmos.
//
package org.eda1.practica02.ejercicio01;


/**
 * Clase para crear un contador de maquinas.
 * @author Guillermo Cant�n Tortosa
 * @version 1.0. 11.2013
 */
	public  class MaquinaContador implements Comparable<Object>{
		
		private String maquina;
		private int contador;
		
		//consturctores
		
		/**
		* Constructor contador de maquina.
		* @param contador int el numero de maquinas con igual direccion y nombre.
		* @param maquina String con el nombre de la maquina.
		*/
		public MaquinaContador(String maquina, int contador){
			
			this.maquina = maquina;
			this.contador = contador;		
			
		}
		
		/**
		* Constructor contador de maquina.
		* @param maquina String con el nombre de la maquina.
		*/
		public MaquinaContador(String maquina){
			
			this.maquina = maquina;
			this.contador = 1;
			
		}
		
		/**
		* Constructor vacio de contador de maquina.
		*/
		public MaquinaContador(){
			
			this.maquina = "";
			this.contador = 1;
		}
		
		/**
		* M�todo cambiar el nombre de una maquina.
		* @param maquina String con el nombre de la maquina.
		*/
		public void setMaquina(String maquina){
			
			this.maquina = maquina;
			
		}
		
		/**
		* M�todo obtener el nombre de una maquina.
		*@return String con el nombre de la maquina.
		*/
		public String getMaquina(){
			
			return this.maquina;
			
		}
		
		/**
		* M�todo cambiar el contador.
		* @param contador int numero de contador.
		*/
		public void setContador(int contador){
			
			this.contador = contador;
			
		}
		
		/**
		* M�todo obtener el contador.
		* @return contador int devuelve el numero de contador.
		*/
		public int getContador(){
			
			return this.contador;
			
		}
		
		/**
		* M�todo incrementar el contador.
		*/
		public void incrementarContador(){
			
			this.contador = this.contador+1;
		}
		
		/**
		* M�todo CompareTo
		* @param otraMaquinaContador Object objeto a comparar
		* @return int 1 si es mayor, -1 si es menor y 0 si son iguales
		*/
		@Override
		public int compareTo(Object otraMaquinaContador){
			
			MaquinaContador nueva = (MaquinaContador)otraMaquinaContador;
			return this.maquina.compareTo(nueva.maquina);
			
			
		}
		
		/**
		* M�todo Equals
		* @param obj Object objeto a comparar
		* @return boolean True si es iguala, False si es diferente.
		*/
		@Override
		public boolean equals(Object obj){
			MaquinaContador aux = (MaquinaContador) obj;
			return this.maquina.equals(aux.maquina);
		}

		/**
		* M�todo ToString
		* @return boolean True si es iguala, False si es diferente.
		*/
		public String toString(){
			
			String resultado = "[";
			resultado += this.maquina+", "+this.contador+"]";
			return resultado;

			
		}
	
}
