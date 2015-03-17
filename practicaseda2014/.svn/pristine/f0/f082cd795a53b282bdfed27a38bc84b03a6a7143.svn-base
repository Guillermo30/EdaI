package org.eda1.tema04.HashTable;

import java.util.*;

public class HashTable
{
  public static void main(String[] args)
  {
       
    String [] ciclista = {"Lansen", "Messaria", "Ripolles", "Delgado",
                        "Cabestani","Kloster", "Bustaria", "Merx",
                        "Sanroma", "Juliani"};
    double [] tiempo = {45.40, 50.40, 47.0, 49.0, 51.20, 46.0, 48.0, 
                        45.6, 52.30, 53.25};
    Hashtable tab = new Hashtable();
    // inserta los elementos en la tabla
    for (int i = 0; i < ciclista.length; i++)
      tab.put(new Double(tiempo[i]), ciclista[i]);
    System.out.println("Tabla hash creada: " + tab);
    // búsqueda por clave
    if (tab.containsKey(new Double(46.0)))
      System.out.println("Clave encontrada: " + tab.get(new Double(46.0)));
    else 
      System.out.println("Clave no está en la tabla");
    // elimina un elemento
    Object q = tab.remove(new Double(45.6));
    if (q != null) System.out.println("Elemento " + q + " eliminado");
    // Conjunto de claves
    Set cv;
    cv = tab.keySet();
    System.out.println("Conjunto de claves: " + cv);
    // Enumeración de valores
    Enumeration en;
    en = tab.elements(); 
    System.out.print("Ciclistas(valores): " );
    while (en.hasMoreElements())
    {
      System.out.print(en.nextElement());
      if (en.hasMoreElements()) System.out.print(", ");
    }
    System.out.println();
  }
}

