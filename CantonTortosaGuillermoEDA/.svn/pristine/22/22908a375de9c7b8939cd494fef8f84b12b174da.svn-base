package org.eda1.actividadesSeptiembre.actividad05;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import org.eda1.actividadesSeptiembre.actividad05.Network.EdgeWeight;
import org.junit.Before;
import org.junit.Test;

public class NetworkSeptiembre2014TestJUnit4 {

	Network<String> net;
	
	@Before
	public void setUp() throws Exception {
		net = new Network<String>();
		
		net.addEdge("0", "1", 2.0);
		net.addEdge("0", "6", 3.0);
		net.addEdge("0", "7", 4.0);
		net.addEdge("1", "2", 3.0);
		net.addEdge("1", "7", 3.0);
		net.addEdge("2", "3", 4.0);
		net.addEdge("2", "4", 1.0);
		net.addEdge("4", "3", 1.0);
		net.addEdge("4", "5", 2.0);
		net.addEdge("6", "4", 3.0);
		net.addEdge("6", "5", 5.0);
		net.addEdge("6", "7", 1.0);
		net.addEdge("7", "2", 1.0);
		net.addEdge("7", "4", 2.0);		
	}

	@Test
	public void testGraphSerialization() throws FileNotFoundException, IOException, ClassNotFoundException {
		String inputDirectory = System.getProperty("user.dir");
		inputDirectory = inputDirectory
				+ File.separator
				+ "src" + File.separator
				+ "org" + File.separator
				+ "eda1" + File.separator
				+ "actividadesSeptiembre" + File.separator
				+ "actividad05" + File.separator;

		String storeFile = inputDirectory + "Network.dat";

		String str = "";
		ArrayList<ArrayList<String>> resultSimplePathsNetwork;
		resultSimplePathsNetwork = net.simplePaths("0", "5");
		for (int i = 0; i < resultSimplePathsNetwork.size(); i++)
			str = str + resultSimplePathsNetwork.get(i) + "\n";

		// object stream connected to file "storeFile" for output
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(storeFile));
	      		
	    // send object and close down the output stream
	    oos.writeObject(net);
	    oos.flush();
	    oos.close();
	    
	    // object stream connected to file "storeFile" for output
	    ObjectInputStream ois = new ObjectInputStream(new FileInputStream(storeFile));

	    // reconstruct object and allocate new current object
	    Network<String> netSerialized = new Network<String>();
	    netSerialized = (Network<String>)ois.readObject();

		assertEquals(net.toString(), netSerialized.toString());

		String strSerialized = "";
		ArrayList<ArrayList<String>> resultSimplePathsNetworkSerialized;
		resultSimplePathsNetworkSerialized = netSerialized.simplePaths("0", "5");
		for (int i = 0; i < resultSimplePathsNetworkSerialized.size(); i++)
			strSerialized = strSerialized + resultSimplePathsNetworkSerialized.get(i) + "\n";

		assertEquals(str, strSerialized);

		netSerialized.addEdge("6", "2", 2.0);
		netSerialized.addEdge("7", "5", 3.0);
		
		assertFalse(net.toString().equals(netSerialized.toString()));
				
		ois.close();
		
	    ois = new ObjectInputStream(new FileInputStream(storeFile));
	    netSerialized = (Network<String>)ois.readObject();
		assertTrue(net.toString().equals(netSerialized.toString()));
		ois.close();
	}

	@Test
	public void testSomePathsWithSimplePaths() {
		String str = "";
		ArrayList<ArrayList<String>> resultSomePathsWithSimplePaths;
		resultSomePathsWithSimplePaths = net.somePathWithSimplePaths("0", "3", "2");
		for (int i = 0; i < resultSomePathsWithSimplePaths.size(); i++)
			str = str + resultSomePathsWithSimplePaths.get(i) + "\n";
		
		String strOutput = "[0, 1, 2, 3]" + "\n";
		strOutput += "[0, 1, 2, 4, 3]" + "\n";
		strOutput += "[0, 1, 7, 2, 3]" + "\n";
		strOutput += "[0, 1, 7, 2, 4, 3]" + "\n";
		strOutput += "[0, 6, 7, 2, 3]" + "\n";
		strOutput += "[0, 6, 7, 2, 4, 3]" + "\n";
		strOutput += "[0, 7, 2, 3]" + "\n";
		strOutput += "[0, 7, 2, 4, 3]" + "\n";
		assertEquals(str, strOutput);

		resultSomePathsWithSimplePaths = net.somePathWithSimplePaths("0", "3", "5");
		str = "";
		for (int i = 0; i < resultSomePathsWithSimplePaths.size(); i++)
			str = str + resultSomePathsWithSimplePaths.get(i) + "\n";

		assertEquals(str, "");
		assertTrue(resultSomePathsWithSimplePaths.size() == 0);

		str = ""; strOutput = "";
		resultSomePathsWithSimplePaths = net.somePathWithSimplePaths("0", "3", "6");
		for (int i = 0; i < resultSomePathsWithSimplePaths.size(); i++)
			str = str + resultSomePathsWithSimplePaths.get(i) + "\n";
		
		strOutput = "[0, 6, 4, 3]" + "\n";
		strOutput += "[0, 6, 7, 2, 3]" + "\n";
		strOutput += "[0, 6, 7, 2, 4, 3]" + "\n";
		strOutput += "[0, 6, 7, 4, 3]" + "\n";
		assertEquals(str, strOutput);

	}
	
	@Test
	public void testModifiedDijkstraForUniqueSP() {
		String str = net.modifiedDijkstraForUniqueSP("0");
		assertEquals(str, "{0=true, 1=true, 2=false, 3=true, 4=false, 5=false, 6=true, 7=false}");
	}

	@Test
	public void testDijkstraPQ() {
		String str = "";
		ArrayList shortestPath = net.Dijkstra("0", "3");
		double distanceShortestPath = 0.0;
		for (int i = 0; i < shortestPath.size(); i++) {
			Network.EdgeWeight e = (Network.EdgeWeight)shortestPath.get(i);
			if (i < shortestPath.size() - 1)
				str += e.from + " -> ";
			else
				str += e.from + " -> " + e.to;
			distanceShortestPath += e.getWeight();
		}
		str += " => " + distanceShortestPath;
		
		String strPQ = "";
		ArrayList shortestPathPQ = net.DijkstraPQ("0", "3");
		double distanceShortestPathPQ = 0.0;
		for (int i = 0; i < shortestPathPQ.size(); i++) {
			Network.EdgeWeight e = (Network.EdgeWeight)shortestPathPQ.get(i);
			if (i < shortestPathPQ.size() - 1)
				strPQ += e.from + " -> ";
			else
				strPQ += e.from + " -> " + e.to;
			distanceShortestPathPQ += e.getWeight();
		}
		strPQ += " => " + distanceShortestPathPQ;

		assertEquals(str, strPQ);
	}

	@Test
	public void testSomePathsWithFloyd() {
	  	if (!net.isFloydGraph())
			net.adaptToFloydGraph();
	  	String strF1 = "";
	  	EdgeWeight e1 = null;
	  	ArrayList rF1 = net.SomePathsWithFloyd("0", "2");
		for (int i = 0; i < rF1.size(); i++) {
			for (int j = 0; j < ((ArrayList<EdgeWeight>)rF1.get(i)).size(); j++) {
				e1 = (EdgeWeight)((ArrayList<EdgeWeight>)rF1.get(i)).get(j);
				strF1 += e1.from + " ";
			}
			strF1 += e1.to + "\n";
		}
		String strOutputF1 = "";
		strOutputF1 += "0 1 2 4 3" + "\n";
		strOutputF1 += "0 1 2 4" + "\n";
		strOutputF1 += "0 1 2 4 5" + "\n";
	  	assertEquals(strF1, strOutputF1);
	  	
	  	String strF2 = "";
	  	EdgeWeight e2 = null;
	  	ArrayList rF2 = net.SomePathsWithFloyd("0", "4");
		for (int i = 0; i < rF2.size(); i++) {
			for (int j = 0; j < ((ArrayList<EdgeWeight>)rF2.get(i)).size(); j++) {
				e2 = (EdgeWeight)((ArrayList<EdgeWeight>)rF2.get(i)).get(j);
				strF2 += e2.from + " ";
			}
			strF2 += e2.to + "\n";
		}
		String strOutputF2 = "";
		strOutputF2 += "0 1 2 4 3" + "\n";
		strOutputF2 += "0 1 2 4 5" + "\n";
	  	assertEquals(strF2, strOutputF2);
		
	  	String strF3 = "";
	  	EdgeWeight e3 = null;
	  	ArrayList rF3 = net.SomePathsWithFloyd("1", "2");
		for (int i = 0; i < rF3.size(); i++) {
			for (int j = 0; j < ((ArrayList<EdgeWeight>)rF3.get(i)).size(); j++) {
				e3 = (EdgeWeight)((ArrayList<EdgeWeight>)rF3.get(i)).get(j);
				strF3 += e3.from + " ";
			}
			strF3 += e3.to + "\n";
		}
		String strOutputF3 = "";
		strOutputF3 += "1 2 4 3" + "\n";
		strOutputF3 += "1 2 4" + "\n";
		strOutputF3 += "1 2 4 5" + "\n";
	  	assertEquals(strF3, strOutputF3);
		
		assertTrue(rF1.size() == rF3.size());
		
	  	String strF4 = "";
	  	EdgeWeight e4 = null;
	  	ArrayList rF4 = net.SomePathsWithFloyd("1", "4");
		for (int i = 0; i < rF4.size(); i++) {
			for (int j = 0; j < ((ArrayList<EdgeWeight>)rF4.get(i)).size(); j++) {
				e4 = (EdgeWeight)((ArrayList<EdgeWeight>)rF4.get(i)).get(j);
				strF4 += e4.from + " ";
			}
			strF4 += e4.to + "\n";
		}
		String strOutputF4 = "";
		strOutputF4 += "1 2 4 3" + "\n";
		strOutputF4 += "1 2 4 5" + "\n";
	  	assertEquals(strF4, strOutputF4);

		assertTrue(rF2.size() == rF4.size());
		
	}
	
}
