package org.eda1.actividad05;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class NetworkTestJUnit4 {

	String directorioEntrada;
	
	@Before
	public void setUp() throws Exception {
		directorioEntrada = System.getProperty("user.dir");

		directorioEntrada = directorioEntrada 
				+ File.separator + "src"
				+ File.separator + "org" 
				+ File.separator + "eda1"
				+ File.separator + "actividad05"
				+ File.separator;		
	}

	@Test
	public void testNetwork() throws FileNotFoundException  {
		
		String graphFile = "graphFile.txt";
		graphFile = directorioEntrada + graphFile;
		
		Network<String> net = Network.readNetwork(graphFile);
		
		assertTrue(net.numberOfVertices() == 5);
		assertTrue(net.numberOfEdges() == 8);

		assertTrue(net.inDegree("A") == 1);
		assertTrue(net.outDegree("A") == 3);

		assertTrue(net.getWeight("A", "B") == 4.0);
		double previousWeight = net.getWeight("A", "B");
		net.setWeight("A", "B", 8);
		assertTrue(net.getWeight("A", "B") == 8.0);
		
		net.setWeight("A", "B", previousWeight);
		assertTrue(net.getWeight("A", "B") == 4.0);
		
		net.addVertex("F");
		assertTrue(net.numberOfVertices() == 6);
		
		net.addEdge("F", "D", 3);
		assertTrue(net.numberOfEdges() == 9);
		
		String str = "";
		Set<String> vtxSet = net.vertexSet();
		Iterator vtxIter = vtxSet.iterator();
		String vtxName;
		Set<String> neighborSet;
		while(vtxIter.hasNext()) {
			vtxName = (String)vtxIter.next();
			neighborSet = net.getNeighbors(vtxName);
			str = str + vtxName + neighborSet + "; ";
		}
		assertEquals(str, "A[B, C, D]; B[A]; C[B, E]; D[E]; E[C]; F[D]; ");
		
		str = "";
		Iterator<String> itrBFS = net.breadthFirstIterator("A");
		while (itrBFS.hasNext())
  			str = str + itrBFS.next() + " ";
		assertEquals(str, "A B C D E ");
		
		str = "";
		Iterator<String> itrDFS = net.depthFirstIterator("A");
		while (itrDFS.hasNext())
  			str = str + itrDFS.next() + " ";
		assertEquals(str, "A D E C B ");
		
		str = "";
		ArrayList<String> aLDFS = net.toArrayDFS("A");
		for (int i = 0; i < aLDFS.size(); i++)
			str = str + aLDFS.get(i) + " ";
		assertEquals(str, "A B C E D ");
		
		str = "";
		ArrayList<String> aLDFSI = net.toArrayDFSIterative("A");
		for (int i = 0; i < aLDFSI.size(); i++)
			str = str + aLDFSI.get(i) + " ";
		assertEquals(str, "A D E C B ");

		str = "";
		ArrayList<String> aLBFS = net.toArrayBFS("A");
		for (int i = 0; i < aLBFS.size(); i++)
			str = str + aLBFS.get(i) + " ";
		assertEquals(str, "A B C D E ");

		assertTrue(net.isReachable("C", "D"));
		assertTrue(net.isReachable("D", "A"));
		assertFalse(net.isReachable("B", "F"));		
		
		net.addEdge("E", "D", 4);
		net.addEdge("B", "C", 2);
		assertTrue(net.isSource("F"));
		assertTrue(!net.isSink("A"));
		
		assertTrue(net.numberOfVertices() == 6);
		assertTrue(net.numberOfEdges() == 11);
		
		net.addVertex("G");
		net.addEdge("A", "G", 1);
		net.addEdge("B", "G", 1);
		net.addEdge("C", "G", 1);
		net.addEdge("D", "G", 1);
		net.addEdge("E", "G", 1);
		net.addEdge("F", "G", 1);
		assertTrue(net.isSink("G"));

		assertTrue(net.numberOfVertices() == 7);
		assertTrue(net.numberOfEdges() == 17);

		net.removeVertex("G");
		assertTrue(net.numberOfVertices() == 6);
		assertTrue(net.numberOfEdges() == 11);

		str = "";
		ArrayList<ArrayList<String>> resultSimplePaths;
		resultSimplePaths = net.simplePaths("A", "D");
		for (int i = 0; i < resultSimplePaths.size(); i++)
			str = str + resultSimplePaths.get(i) + "\n";
		String salida = "[A, B, C, E, D]" + "\n"
						+ "[A, C, E, D]" + "\n"
						+ "[A, D]" + "\n";
		assertEquals(str, salida);
		
		str = net.shortestPathWithSimplePaths("F", "A");
		salida = "The shortest path using the simple paths algorithm from F to A is:\n";
		salida += "F --> D --> E --> C --> B --> A => (13.0)";
		assertEquals(str, salida);
	
		str = net.shortestPathWithSimplePaths("A", "E");
		salida = "The shortest path using the simple paths algorithm from A to E is:\n";
		salida += "A --> B --> C --> E => (8.0)";
		assertEquals(str, salida);
		
		str = net.largestLenghtPathWithSimplePaths("F", "A");
		salida = "The largest length path using the simple paths algorithm from F to A is:\n";
		salida += "F --> D --> E --> C --> B --> A => (5)\n";
		salida += "The average length of the simple paths from F to A is: 5.00";
		assertEquals(str, salida);

		str = net.largestLenghtPathWithSimplePaths("A", "E");
		salida = "The largest length path using the simple paths algorithm from A to E is:\n";
		salida += "A --> B --> C --> E => (3)\n";
		salida += "The average length of the simple paths from A to E is: 2.33";
		assertEquals(str, salida);
		
		assertTrue(net.isPathLength("F", "A", 5));
		assertFalse(net.isPathLength("F", "A", 4));
		assertTrue(net.isPathLength("A", "E", 3));
		assertTrue(net.isPathLength("A", "E", 2));
		assertTrue(net.isPathLength("A", "D", 1));
		assertFalse(net.isPathLength("A", "D", 2));
		assertTrue(net.isPathLength("A", "D", 3));
		assertTrue(net.isPathLength("A", "D", 4));
		
		assertFalse(net.isStronglyConnected());
		
		net.removeVertex("F");
		
		assertTrue(net.isStronglyConnected());
		
	}
	
	@Test
	public void testRemoveNetwork() throws FileNotFoundException  {
		String graphFile = "graphFile.txt";
		graphFile = directorioEntrada + graphFile;
		
		Network<String> net = Network.readNetwork(graphFile);

		net.removeEdge("B", "A");
		assertTrue(net.numberOfEdges() == 7);

		net.removeVertex("E");
		assertTrue(net.numberOfVertices() == 4);
		assertTrue(net.numberOfEdges() == 4);
	}

	@Test
	public void testUnDirectedNetwork() {
		Network<String> newNet = new Network<String>(false);
		
		newNet.addEdge("A", "B", 1.0);
		newNet.addEdge("B", "C", 2.0);
		newNet.addEdge("B", "D", 3.0);
		newNet.addEdge("D", "E", 4.0);
		newNet.addEdge("D", "F", 5.0);

		assertTrue(newNet.isConnected());
		assertTrue(newNet.isTree());
		
		newNet.addEdge("C", "F", 6.0);
		
		assertTrue(newNet.isConnected());
		assertFalse(newNet.isTree());
		
		newNet.removeEdge("C", "F");
		newNet.addEdge("X", "Y", 7.0);

		assertFalse(newNet.isConnected());
		assertFalse(newNet.isTree());

	}
	
	@Test
	public void testDijkstraPQ() {
		Network<String> net = new Network<String>();
		
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
	public void testFloydEC() {
		Network<String> net = new Network<String>();
		
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
		
	  	if (!net.isFloydGraph())
			net.adaptToFloydGraph();
	  	String str = "";
	  	str = net.FloydEC();
	  	String strVal = "{0=-1.0, 1=2.0, 2=5.0, 3=7.0, 4=6.0, 5=8.0, 6=3.0, 7=4.0}; centro = 1" + "\n" +
	  			"[(1, 2)=3.0, (1, 3)=5.0, (1, 4)=4.0, (1, 5)=6.0, (1, 7)=3.0]" + "\n";
	  	assertEquals(str, strVal);
	}
	
}
