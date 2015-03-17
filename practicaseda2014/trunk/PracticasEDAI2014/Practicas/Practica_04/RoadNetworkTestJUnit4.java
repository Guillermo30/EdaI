package org.eda1.practica04;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class RoadNetworkTestJUnit4 {

	String directorioEntrada;
	
	@Before
	public void setUp() throws Exception {
		directorioEntrada = System.getProperty("user.dir");

		directorioEntrada = directorioEntrada 
				+ File.separator + "src"
				+ File.separator + "org" 
				+ File.separator + "eda1"
				+ File.separator + "practica04"
				+ File.separator;		
	}

	@Test
	public void testNetworkSpanishRoad() throws FileNotFoundException {
		String graphFile = "graphSpain.txt";
		graphFile = directorioEntrada + graphFile;
		
		RoadNetwork<String> net = RoadNetwork.readRoadNetwork(graphFile);

		assertTrue(net.numberOfVertices() == 21);
		assertTrue(net.numberOfEdges() == 58);
		
		String str = "";
		Set<String> vtxSet = net.vertexSet();
		Iterator vtxIter = vtxSet.iterator();
		String vtxName;
		Set<String> neighborSet;
		while(vtxIter.hasNext()) {
			vtxName = (String)vtxIter.next();
			neighborSet = net.getNeighbors(vtxName);
			str = str + vtxName + neighborSet + ";\n";
		}
		String cadena = "";
		cadena += "Albacete[Madrid, Murcia, Valencia];" + "\n";
		cadena += "Almeria[Granada, Murcia];" + "\n";
		cadena += "Badajoz[Caceres, Huelva, Madrid];" + "\n";
		cadena += "Barcelona[Gerona, Valencia, Zaragoza];" + "\n";
		cadena += "Bilbao[Madrid, Oviedo, Valladolid, Zaragoza];" + "\n";
		cadena += "Caceres[Badajoz];" + "\n";
		cadena += "Cadiz[Sevilla];" + "\n";
		cadena += "Corunya[Valladolid, Vigo];" + "\n";
		cadena += "Gerona[Barcelona, Lerida];" + "\n";
		cadena += "Granada[Almeria, Jaen, Murcia, Sevilla];" + "\n";
		cadena += "Huelva[Badajoz, Sevilla];" + "\n";
		cadena += "Jaen[Granada, Madrid, Sevilla];" + "\n";
		cadena += "Lerida[Gerona];" + "\n";
		cadena += "Madrid[Albacete, Badajoz, Bilbao, Jaen, Valladolid, Zaragoza];" + "\n";
		cadena += "Murcia[Albacete, Almeria, Granada, Valencia];" + "\n";
		cadena += "Oviedo[Bilbao];" + "\n";
		cadena += "Sevilla[Cadiz, Granada, Huelva, Jaen];" + "\n";
		cadena += "Valencia[Albacete, Barcelona, Murcia];" + "\n";
		cadena += "Valladolid[Bilbao, Corunya, Madrid, Vigo];" + "\n";
		cadena += "Vigo[Corunya, Valladolid];" + "\n";
		cadena += "Zaragoza[Barcelona, Bilbao, Madrid];" + "\n";
		assertEquals(str, cadena);
		
		str = "";
		Iterator<String> itr = net.breadthFirstIterator ("Almeria");
		while (itr.hasNext())
  			str += itr.next() + " ";
		assertEquals(str, "Almeria Granada Murcia Jaen Sevilla Albacete Valencia Madrid Cadiz Huelva Barcelona Badajoz Bilbao Valladolid Zaragoza Gerona Caceres Oviedo Corunya Vigo Lerida ");
		
		str = "";
		itr = net.depthFirstIterator ("Almeria");
		while (itr.hasNext())
 			str += itr.next() + " ";
		assertEquals(str, "Almeria Murcia Valencia Barcelona Zaragoza Madrid Valladolid Vigo Corunya Jaen Sevilla Huelva Cadiz Badajoz Caceres Bilbao Oviedo Gerona Lerida Albacete Granada ");
		
		str = "";
		ArrayList<String> aLDFS = net.toArrayDFS("Almeria");
		for (int i = 0; i < aLDFS.size(); i++)
			str += aLDFS.get(i) + " ";
		assertEquals(str, "Almeria Granada Jaen Madrid Albacete Murcia Valencia Barcelona Gerona Lerida Zaragoza Bilbao Oviedo Valladolid Corunya Vigo Badajoz Caceres Huelva Sevilla Cadiz ");
		
		str = "";
		ArrayList<String> aLBFS = net.toArrayBFS("Almeria");
		for (int i = 0; i < aLBFS.size(); i++)
			str += aLBFS.get(i) + " ";
		assertEquals(str, "Almeria Granada Murcia Jaen Sevilla Albacete Valencia Madrid Cadiz Huelva Barcelona Badajoz Bilbao Valladolid Zaragoza Gerona Caceres Oviedo Corunya Vigo Lerida ");

		str = "";
		ArrayList shortestPathAO = net.Dijkstra("Almeria", "Oviedo");
		double distanceShortestPathAO = 0.0;
		for (int i = 0; i < shortestPathAO.size(); i++) {
			RoadNetwork.EdgeWeight e = (RoadNetwork.EdgeWeight)shortestPathAO.get(i);
			if (i < shortestPathAO.size() - 1)
				str += e.from + " -> ";
			else {
				str += e.from + " -> ";
				str += e.to;
			}
			distanceShortestPathAO += e.getWeight();
		}
		assertEquals(str, "Almeria -> Granada -> Jaen -> Madrid -> Bilbao -> Oviedo");
		assertTrue(distanceShortestPathAO == 1306.0);

		str = "";
		ArrayList shortestPathAC = net.Dijkstra("Almeria", "Caceres");
		double distanceShortestPathAC = 0.0;
		for (int i = 0; i < shortestPathAC.size(); i++) {
			RoadNetwork.EdgeWeight e = (RoadNetwork.EdgeWeight)shortestPathAC.get(i);
			if (i < shortestPathAC.size() - 1)
				str += e.from + " -> ";
			else {
				str += e.from + " -> ";
				str += e.to;
			}
			distanceShortestPathAC += e.getWeight();
		}
		assertEquals(str, "Almeria -> Granada -> Sevilla -> Huelva -> Badajoz -> Caceres");
		assertTrue(distanceShortestPathAC == 845.0);

		str = net.shortestPathSTD("Almeria", "Madrid", "Lerida");
		assertEquals(str, "Almeria -> Granada -> Jaen -> Madrid -> Zaragoza -> Barcelona -> Gerona -> Lerida => 1550.0");
		
		str = "";
		double sumDistanceOfDijkstraTree = 0.0;
		ArrayList SPT = net.DijkstraTree("Almeria");
		for (int i = 0; i < SPT.size(); i++) {
			str += SPT.get(i) + "\n";
			sumDistanceOfDijkstraTree += ((RoadNetwork.EdgeWeight)SPT.get(i)).getWeight();
		}
		cadena = "";
		cadena += "<Almeria, Granada; 173.0>" + "\n";
		cadena += "<Almeria, Murcia; 224.0>" + "\n";
		cadena += "<Granada, Jaen; 99.0>" + "\n";
		cadena += "<Murcia, Albacete; 150.0>" + "\n";
		cadena += "<Granada, Sevilla; 256.0>" + "\n";
		cadena += "<Murcia, Valencia; 241.0>" + "\n";
		cadena += "<Sevilla, Huelva; 92.0>" + "\n";
		cadena += "<Sevilla, Cadiz; 125.0>" + "\n";
		cadena += "<Jaen, Madrid; 335.0>" + "\n";
		cadena += "<Huelva, Badajoz; 234.0>" + "\n";
		cadena += "<Madrid, Valladolid; 193.0>" + "\n";
		cadena += "<Valencia, Barcelona; 349.0>" + "\n";
		cadena += "<Badajoz, Caceres; 90.0>" + "\n";
		cadena += "<Barcelona, Gerona; 100.0>" + "\n";
		cadena += "<Madrid, Zaragoza; 325.0>" + "\n";
		cadena += "<Madrid, Bilbao; 395.0>" + "\n";
		cadena += "<Gerona, Lerida; 222.0>" + "\n";
		cadena += "<Valladolid, Vigo; 356.0>" + "\n";
		cadena += "<Valladolid, Corunya; 455.0>" + "\n";
		cadena += "<Bilbao, Oviedo; 304.0>" + "\n";
		assertEquals(str, cadena);
		assertTrue(sumDistanceOfDijkstraTree == 4718.0);
		
		str = "";
		ArrayList farthestCities = net.DijkstraFarthest("Almeria");
		for (int i = 0; i < farthestCities.size(); i++) {
			String strOfPath = "";
			double distancefarthestCity = 0.0;
			RoadNetwork.EdgeWeight e = null;
			int sizeOfPath = ((ArrayList<RoadNetwork.EdgeWeight>)farthestCities.get(i)).size();
			for (int j = 0; j < sizeOfPath; j++) {
				e = (RoadNetwork.EdgeWeight)((ArrayList<RoadNetwork.EdgeWeight>)farthestCities.get(i)).get(j);
				if (j < sizeOfPath - 1)
					strOfPath += e.from + " -> ";
				else {
					strOfPath += e.from + " -> ";
					strOfPath += e.to;
				}
				distancefarthestCity += e.getWeight();
			}
			strOfPath += " => " + distancefarthestCity + "\n";
			str += strOfPath;
		}
		cadena = "";
		cadena += "Almeria -> Granada -> Jaen -> Madrid -> Bilbao -> Oviedo => 1306.0" + "\n";
		cadena += "Almeria -> Granada -> Jaen -> Madrid -> Valladolid -> Corunya => 1255.0" + "\n";
		cadena += "Almeria -> Granada -> Jaen -> Madrid -> Valladolid -> Vigo => 1156.0" + "\n";
		cadena += "Almeria -> Murcia -> Valencia -> Barcelona -> Gerona -> Lerida => 1136.0" + "\n";
		cadena += "Almeria -> Granada -> Jaen -> Madrid -> Bilbao => 1002.0" + "\n";
		cadena += "Almeria -> Granada -> Jaen -> Madrid -> Zaragoza => 932.0" + "\n";
		cadena += "Almeria -> Murcia -> Valencia -> Barcelona -> Gerona => 914.0" + "\n";
		cadena += "Almeria -> Granada -> Sevilla -> Huelva -> Badajoz -> Caceres => 845.0" + "\n";
		cadena += "Almeria -> Murcia -> Valencia -> Barcelona => 814.0" + "\n";
		cadena += "Almeria -> Granada -> Jaen -> Madrid -> Valladolid => 800.0" + "\n";
		cadena += "Almeria -> Granada -> Sevilla -> Huelva -> Badajoz => 755.0" + "\n";
		cadena += "Almeria -> Granada -> Jaen -> Madrid => 607.0" + "\n";
		cadena += "Almeria -> Granada -> Sevilla -> Cadiz => 554.0" + "\n";
		cadena += "Almeria -> Granada -> Sevilla -> Huelva => 521.0" + "\n";
		cadena += "Almeria -> Murcia -> Valencia => 465.0" + "\n";
		cadena += "Almeria -> Granada -> Sevilla => 429.0" + "\n";
		cadena += "Almeria -> Murcia -> Albacete => 374.0" + "\n";
		cadena += "Almeria -> Granada -> Jaen => 272.0" + "\n";
		cadena += "Almeria -> Murcia => 224.0" + "\n";
		cadena += "Almeria -> Granada => 173.0" + "\n";
		assertEquals(str, cadena);

		ArrayList<ArrayList<String>> resultSimplePaths;
		resultSimplePaths = net.simplePaths("Almeria", "Bilbao");
		assertTrue(resultSimplePaths.size() == 60);

		str = "";
		ArrayList resultLargestPathWithSimplePaths;
		resultLargestPathWithSimplePaths = net.largestPathWithSimplePaths("Almeria", "Oviedo");
		double distanceLargestPath = 0.0;
		for (int i = 0; i < resultLargestPathWithSimplePaths.size(); i++) {
			RoadNetwork.EdgeWeight e = (RoadNetwork.EdgeWeight)resultLargestPathWithSimplePaths.get(i);
			if (i < resultLargestPathWithSimplePaths.size() - 1)
				str += e.from + " -> ";
			else {
				str += e.from + " -> ";
				str += e.to;
			}
			distanceLargestPath += e.getWeight();
		}
		assertEquals(str, "Almeria -> Murcia -> Granada -> Jaen -> Sevilla -> Huelva -> Badajoz -> Madrid -> Albacete -> Valencia -> Barcelona -> Zaragoza -> Bilbao -> Oviedo");
		assertTrue(distanceLargestPath == 3287.0);

		int numberOfShortestPathsByFloydWithDijkstra = net.FloydWithDijkstra();

		assertFalse(net.isFloydGraph());
		
		RoadNetwork<String> netFloyd = new RoadNetwork(net);
		if (!netFloyd.isFloydGraph())
			netFloyd.adaptToFloydGraph();
		
		assertTrue(netFloyd.isFloydGraph());
		
		assertTrue(netFloyd.numberOfVertices() == 21);
		assertTrue(netFloyd.numberOfEdges() == 79);

		ArrayList rFloyd = netFloyd.Floyd();
		
		assertTrue(numberOfShortestPathsByFloydWithDijkstra == rFloyd.size());
		
		str = "";
		ArrayList rFloydFilterByDistances = netFloyd.FloydFilterByDistances(1300.0, 1600.0);
		for (int i = 0; i < rFloydFilterByDistances.size(); i++) {
			String strOfPath = "";
			double distanceShortestPathFloyd = 0.0;
			RoadNetwork.EdgeWeight e = null;
			for (int j = 0; j < ((ArrayList<RoadNetwork.EdgeWeight>)rFloydFilterByDistances.get(i)).size(); j++) {
				e = (RoadNetwork.EdgeWeight)((ArrayList<RoadNetwork.EdgeWeight>)rFloydFilterByDistances.get(i)).get(j);
				strOfPath += e.from + " -> ";
				distanceShortestPathFloyd += e.getWeight();
			}
			strOfPath += e.to + " => " + distanceShortestPathFloyd + "\n";
			str += strOfPath;
		}
		cadena = "";
		cadena += "Almeria -> Granada -> Jaen -> Madrid -> Bilbao -> Oviedo => 1306.0" + "\n";
		cadena += "Badajoz -> Madrid -> Zaragoza -> Barcelona -> Gerona -> Lerida => 1346.0" + "\n";
		cadena += "Caceres -> Badajoz -> Madrid -> Zaragoza -> Barcelona -> Gerona -> Lerida => 1436.0" + "\n";
		cadena += "Cadiz -> Sevilla -> Jaen -> Madrid -> Valladolid -> Corunya => 1350.0" + "\n";
		cadena += "Cadiz -> Sevilla -> Granada -> Murcia -> Valencia -> Barcelona -> Gerona => 1349.0" + "\n";
		cadena += "Cadiz -> Sevilla -> Granada -> Murcia -> Valencia -> Barcelona -> Gerona -> Lerida => 1571.0" + "\n";
		cadena += "Cadiz -> Sevilla -> Jaen -> Madrid -> Bilbao -> Oviedo => 1401.0" + "\n";
		cadena += "Corunya -> Valladolid -> Madrid -> Jaen -> Sevilla -> Cadiz => 1350.0" + "\n";
		cadena += "Corunya -> Valladolid -> Madrid -> Zaragoza -> Barcelona -> Gerona => 1369.0" + "\n";
		cadena += "Corunya -> Valladolid -> Madrid -> Zaragoza -> Barcelona -> Gerona -> Lerida => 1591.0" + "\n";
		cadena += "Gerona -> Barcelona -> Valencia -> Murcia -> Granada -> Sevilla -> Cadiz => 1349.0" + "\n";
		cadena += "Gerona -> Barcelona -> Zaragoza -> Madrid -> Valladolid -> Corunya => 1369.0" + "\n";
		cadena += "Gerona -> Barcelona -> Valencia -> Murcia -> Granada -> Sevilla -> Huelva => 1316.0" + "\n";
		cadena += "Huelva -> Sevilla -> Granada -> Murcia -> Valencia -> Barcelona -> Gerona => 1316.0" + "\n";
		cadena += "Huelva -> Sevilla -> Granada -> Murcia -> Valencia -> Barcelona -> Gerona -> Lerida => 1538.0" + "\n";
		cadena += "Huelva -> Badajoz -> Madrid -> Bilbao -> Oviedo => 1336.0" + "\n";
		cadena += "Lerida -> Gerona -> Barcelona -> Zaragoza -> Madrid -> Badajoz => 1346.0" + "\n";
		cadena += "Lerida -> Gerona -> Barcelona -> Zaragoza -> Madrid -> Badajoz -> Caceres => 1436.0" + "\n";
		cadena += "Lerida -> Gerona -> Barcelona -> Valencia -> Murcia -> Granada -> Sevilla -> Cadiz => 1571.0" + "\n";
		cadena += "Lerida -> Gerona -> Barcelona -> Zaragoza -> Madrid -> Valladolid -> Corunya => 1591.0" + "\n";
		cadena += "Lerida -> Gerona -> Barcelona -> Valencia -> Murcia -> Granada -> Sevilla -> Huelva => 1538.0" + "\n";
		cadena += "Lerida -> Gerona -> Barcelona -> Valencia -> Murcia -> Granada -> Sevilla => 1446.0" + "\n";
		cadena += "Lerida -> Gerona -> Barcelona -> Zaragoza -> Madrid -> Valladolid -> Vigo => 1492.0" + "\n";
		cadena += "Oviedo -> Bilbao -> Madrid -> Jaen -> Granada -> Almeria => 1306.0" + "\n";
		cadena += "Oviedo -> Bilbao -> Madrid -> Jaen -> Sevilla -> Cadiz => 1401.0" + "\n";
		cadena += "Oviedo -> Bilbao -> Madrid -> Badajoz -> Huelva => 1336.0" + "\n";
		cadena += "Sevilla -> Granada -> Murcia -> Valencia -> Barcelona -> Gerona -> Lerida => 1446.0" + "\n";
		cadena += "Vigo -> Valladolid -> Madrid -> Zaragoza -> Barcelona -> Gerona -> Lerida => 1492.0" + "\n";
		assertEquals(str, cadena);
		
		str = "";
		ArrayList rFloydClosestFarthest = netFloyd.FloydClosestFarthest();
		for (int i = 0; i < rFloydClosestFarthest.size(); i++) {
			String strOfPath = "";
			double distanceShortestPathFloyd = 0.0;
			RoadNetwork.EdgeWeight e = null;
			for (int j = 0; j < ((ArrayList<RoadNetwork.EdgeWeight>)rFloydClosestFarthest.get(i)).size(); j++) {
				e = (RoadNetwork.EdgeWeight)((ArrayList<RoadNetwork.EdgeWeight>)rFloydClosestFarthest.get(i)).get(j);
				strOfPath += e.from + " -> ";
				distanceShortestPathFloyd += e.getWeight();
			}
			strOfPath += e.to + " => " + distanceShortestPathFloyd + "\n";
			str += strOfPath;
		}
		cadena = "";
		cadena += "Badajoz -> Caceres => 90.0" + "\n";
		cadena += "Corunya -> Valladolid -> Madrid -> Zaragoza -> Barcelona -> Gerona -> Lerida => 1591.0" + "\n";
		assertEquals(str, cadena);
		
		str = "";
		ArrayList rFloydFilterByNameOfCity = netFloyd.FloydFilterByNameOfCity("Madrid");
		for (int i = 0; i < rFloydFilterByNameOfCity.size(); i++) {
			String strOfPath = "";
			double distanceShortestPathFloyd = 0.0;
			RoadNetwork.EdgeWeight e = null;
			for (int j = 0; j < ((ArrayList<RoadNetwork.EdgeWeight>)rFloydFilterByNameOfCity.get(i)).size(); j++) {
				e = (RoadNetwork.EdgeWeight)((ArrayList<RoadNetwork.EdgeWeight>)rFloydFilterByNameOfCity.get(i)).get(j);
				strOfPath += e.from + " -> ";
				distanceShortestPathFloyd += e.getWeight();
			}
			strOfPath += e.to + " => " + distanceShortestPathFloyd + "\n";
			str += strOfPath;
		}
		cadena = "";
		cadena += "Madrid -> Albacete => 251.0" + "\n";
		cadena += "Madrid -> Jaen -> Granada -> Almeria => 607.0" + "\n";
		cadena += "Madrid -> Badajoz => 403.0" + "\n";
		cadena += "Madrid -> Zaragoza -> Barcelona => 621.0" + "\n";
		cadena += "Madrid -> Bilbao => 395.0" + "\n";
		cadena += "Madrid -> Badajoz -> Caceres => 493.0" + "\n";
		cadena += "Madrid -> Jaen -> Sevilla -> Cadiz => 702.0" + "\n";
		cadena += "Madrid -> Valladolid -> Corunya => 648.0" + "\n";
		cadena += "Madrid -> Zaragoza -> Barcelona -> Gerona => 721.0" + "\n";
		cadena += "Madrid -> Jaen -> Granada => 434.0" + "\n";
		cadena += "Madrid -> Badajoz -> Huelva => 637.0" + "\n";
		cadena += "Madrid -> Jaen => 335.0" + "\n";
		cadena += "Madrid -> Zaragoza -> Barcelona -> Gerona -> Lerida => 943.0" + "\n";
		cadena += "Madrid -> Albacete -> Murcia => 401.0" + "\n";
		cadena += "Madrid -> Bilbao -> Oviedo => 699.0" + "\n";
		cadena += "Madrid -> Jaen -> Sevilla => 577.0" + "\n";
		cadena += "Madrid -> Albacete -> Valencia => 442.0" + "\n";
		cadena += "Madrid -> Valladolid => 193.0" + "\n";
		cadena += "Madrid -> Valladolid -> Vigo => 549.0" + "\n";
		cadena += "Madrid -> Zaragoza => 325.0" + "\n";
		assertEquals(str, cadena);

		str = "";
		double sumDistanceOfPrimMST = 0.0;
		ArrayList PrimMST = net.Prim("Almeria");
		for (int i = 0; i < PrimMST.size(); i++) {
			str += PrimMST.get(i) + "\n";
			sumDistanceOfPrimMST += ((RoadNetwork.EdgeWeight)PrimMST.get(i)).getWeight();
		}
		cadena = "";
		cadena += "<Almeria, Granada; 173.0>" + "\n";
		cadena += "<Granada, Jaen; 99.0>" + "\n";
		cadena += "<Almeria, Murcia; 224.0>" + "\n";
		cadena += "<Murcia, Albacete; 150.0>" + "\n";
		cadena += "<Albacete, Valencia; 191.0>" + "\n";
		cadena += "<Jaen, Sevilla; 242.0>" + "\n";
		cadena += "<Sevilla, Huelva; 92.0>" + "\n";
		cadena += "<Sevilla, Cadiz; 125.0>" + "\n";
		cadena += "<Huelva, Badajoz; 234.0>" + "\n";
		cadena += "<Badajoz, Caceres; 90.0>" + "\n";
		cadena += "<Albacete, Madrid; 251.0>" + "\n";
		cadena += "<Madrid, Valladolid; 193.0>" + "\n";
		cadena += "<Valladolid, Bilbao; 280.0>" + "\n";
		cadena += "<Bilbao, Oviedo; 304.0>" + "\n";
		cadena += "<Bilbao, Zaragoza; 324.0>" + "\n";
		cadena += "<Zaragoza, Barcelona; 296.0>" + "\n";
		cadena += "<Barcelona, Gerona; 100.0>" + "\n";
		cadena += "<Gerona, Lerida; 222.0>" + "\n";
		cadena += "<Valladolid, Vigo; 356.0>" + "\n";
		cadena += "<Vigo, Corunya; 171.0>" + "\n";
		assertEquals(str, cadena);
		assertTrue(sumDistanceOfPrimMST == 4117.0);
		
		assertTrue(sumDistanceOfPrimMST < sumDistanceOfDijkstraTree);
		
	}

}
