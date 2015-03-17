package org.eda1.tema02.ejemplo01;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.PriorityQueue;
import java.util.Random;

import org.eda1.utilidades.Greater;
import org.eda1.utilidades.Less;


/**
 * Clase que contiene los metodos necesarios para
 * obteber un topN con la PrioriryQueue de Java
 * 
 * @author Antonio Corral Liria
 */
public class UsingPriorityQueue {

	public static void main(String[] args) {

		ArrayList<Double> salida = getTopN(5);

		System.out.println("\n\n");
		for (int i = 0; i < salida.size(); i++)
        	System.out.println(salida.get(i));
	}

	public static ArrayList<Double> getTopN(int n) {	    
	    //Comparator<Double> comp = new Less<Double>();
		Comparator<Double> comp = new Greater<Double>();
        PriorityQueue<Double> pQ = new PriorityQueue<Double>(n, comp); 
        	
        Date date = new Date();
        long time = date.getTime();
        Random r = new Random();
        r.setSeed(time);
        double nrand;
        for (int i = 0; i < 10; i++) {
        	nrand =  r.nextDouble();
	        if (pQ.size() == n) {
	        	Double top = pQ.peek();
	        	if (nrand < top) {	//if (nrand > top) {
	        		pQ.poll();
	        		pQ.add(nrand);
	        	}	
	        }
	        else
	        	pQ.add(nrand);
       	
        	System.out.println(nrand);
        }
		
	    ArrayList<Double> salida = new ArrayList<Double>();

	    while (!pQ.isEmpty()) {
	        Double x = pQ.poll();
		    salida.add(x);
	    }
	    
	    return salida;
	}

}
