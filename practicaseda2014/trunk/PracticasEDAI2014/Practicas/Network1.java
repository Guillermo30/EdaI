package org.eda1.actividad05;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.DecimalFormat;
import java.util.*;
import java.util.Map.Entry;

import org.eda1.estructurasdedatos.ALStack;
import org.eda1.estructurasdedatos.Graph;
import org.eda1.estructurasdedatos.LinkedQueue;



public class Network<Vertex> implements Graph<Vertex>, Iterable<Vertex> {
	protected boolean directed; 	// directed = false (unDirected), directed = true (DiGraph)
	protected TreeMap<Vertex, TreeMap<Vertex, Double>> adjacencyMap;
	protected TreeMap<Vertex, Boolean> visited;
	protected ArrayList<Vertex> result;
	protected ArrayList<ArrayList<Vertex>> resultSimplePaths;
	Double shortestPathWeight;
	protected ArrayList<Vertex> resultShortestPath;
	int largestLengthPath;
	protected ArrayList<Vertex> resultLargestLengthPath;
	int numberOfSimplePaths, sumOfLengthOfSimplePaths;


  	/**
   	 *  Initialized this Network object to be empty.
   	 */
  	public Network() {
  		directed = true;
    	adjacencyMap = new TreeMap<Vertex, TreeMap<Vertex, Double>>();
  	} // default constructor

  	public Network(boolean uDOrD) {
  		directed = uDOrD;
		adjacencyMap = new TreeMap<Vertex, TreeMap<Vertex, Double>>();
	} // default constructor

  	
  	/**
   	 *  Initializes this Network object to a shallow copy of a specified Network
   	 *  object.
   	 *  The averageTime(V, E) is O(V + E).
   	 *
   	 *  @param network - the Network object that this Network object is
	 *                                 initialized to a shallow copy of.
  	 *
  	 */
  	public Network(Network<Vertex> network) {
  		this.directed = network.directed;
    	this.adjacencyMap = new TreeMap<Vertex, TreeMap<Vertex, Double>>(network.adjacencyMap);
  	} // copy constructor

  	public void setDirected(boolean uDOrD) {
  		directed = uDOrD;
  	}

  	public boolean getDirected() {
  		return directed;
  	}


  	/**
 	 *  Determines if this Network object contains no vertices.
  	 *
  	 *  @return true - if this Network object contains no vertices.
  	 *
  	 */
  	public boolean isEmpty() {
    	return adjacencyMap.isEmpty();
  	} // method isEmpty


  	/**
  	 *  Determines the number of vertices in this Network object.
  	 *
  	 *  @return the number of vertices in this Network object.
  	 *
  	 */
  	public int numeroDefVertices() {
    	return adjacencyMap.size();
  	} // method size


  	/**
  	 *  Returns the number of edges in this Network object.
  	 *  The averageTime (V, E) is O (V).
  	 *
  	 *  @return the number of edges in this Network object.
  	 *
  	 */
  	public int numberOfEdges() {
  		int count = 0;
  		for (Map.Entry<Vertex, TreeMap<Vertex, Double>> entry : adjacencyMap.entrySet())
  			count += entry.getValue().size();
  		return count;
  	} // method getEdgeCount


	public void clear() {
		adjacencyMap.clear();
	}

  	public double getWeight(Vertex v1, Vertex v2) {
    		if (! (adjacencyMap.containsKey(v1) && adjacencyMap.get(v1).containsKey(v2)))
      			return -1.0;

    		return adjacencyMap.get(v1).get(v2);
   	} // method getWeight

  	public double setWeight(Vertex v1, Vertex v2, double w) {
		if (! (adjacencyMap.containsKey (v1) && adjacencyMap.get(v1).containsKey(v2)))
  			return -1.0;

		TreeMap<Vertex, Double> neighborMap = adjacencyMap.get(v1);
		double oldWeight = neighborMap.get(v2);
		adjacencyMap.get(v1).put(v2, w);
		return oldWeight;
	}


  	/**
  	 *  Determines if this Network object contains a specified Vertex object.
  	 *
  	 *  @param vertex - the Vertex object whose presence is sought.
  	 *
  	 *  @return true - if vertex is an element of this Network object.
  	 */
  	public boolean containsVertex(Vertex vertex) {
    	return adjacencyMap.containsKey(vertex);
  	} // method containsVertex


  	/**
  	 *  Determines if this Network object contains an edge specified by two vertices.
  	 *  The averageTime (V, E) is O (E / V).
  	 *
  	 *  @param v1 - the beginning Vertex object of the edge sought.
  	 *  @param v2 - the ending Vertex object of the edge sought.
  	 *
  	 *  @return true - if this Network object contains the edge <v1, v2>.
  	 *
  	 */
  	public boolean containsEdge(Vertex v1, Vertex v2) {
    	if (adjacencyMap.containsKey(v1) && adjacencyMap.get(v1).containsKey(v2))
      		return true;
    	else
    		return false;
  	} // method containsEdge


  	/**
  	 *  Ensures that a specified Vertex object is an element of this Network object.
  	 *
  	 *  @param vertex - the Vertex object whose presence is ensured.
  	 *
  	 *  @return true - if vertex was added to this Network object by this call; returns
  	 *               false if vertex was already an element of this Network object when
  	 *               this call was made.
  	 */
  	public boolean addVertex(Vertex vertex) {
        if (adjacencyMap.containsKey(vertex))
            return false;
        adjacencyMap.put(vertex, new TreeMap<Vertex, Double>());
        	return true;
  	} // method addVertex


  	/**
  	 *  Ensures that an edge is in this Network object.
  	 *
  	 *  @param v1 - the beginning Vertex object of the edge whose presence
  	 *                         is ensured.
  	 *  @param v2 - the ending Vertex object of the edge whose presence is
  	 *                        ensured.
  	 *  @param weight - the weight of the edge whose presence is ensured.
  	 *
  	 *  @return true - if the given edge (and weight) were added to this Network
  	 *                         object by this call; return false, if the given edge (and weight)
  	 *                         were already in this Network object when this call was made.
  	 *
  	 */
  	public boolean addEdge(Vertex v1, Vertex v2, double w) {
    	addVertex(v1);
    	addVertex(v2);
    	adjacencyMap.get(v1).put(v2, w);
    	if (!directed)
        	adjacencyMap.get(v2).put(v1, w);
    	return true;
  	} // method addEdge


  	/**
  	 *  Ensures that a specified Vertex object is not an element of this Network object.
  	 *  The averageTime (V, E) is O (V + E).
  	 *
  	 *  @param vertex - the Vertex object whose absence is ensured.
  	 *
  	 *  @return true - if vertex was removed from this Network object by this call;
  	 *                returns false if vertex was not an element of this Network object
  	 *                when this call was made.
         *
	 */
  	public boolean removeVertex(Vertex vertex) {
        if (!adjacencyMap.containsKey(vertex))
            return false;

        for (Map.Entry<Vertex, TreeMap<Vertex, Double>> entry: adjacencyMap.entrySet()) {
        	TreeMap<Vertex, Double> neighborMap = entry.getValue();
        	neighborMap.remove(vertex);
        } // for each vertex in the network
        adjacencyMap.remove(vertex);
        return true;
   	} // removeVertex


  	/**
   	 *  Ensures that an edge specified by two vertices is absent from this Network
   	 *  object.
   	 *  The averageTime (V, E) is O (E / V).
   	 *
   	 *  @param v1 - the beginning Vertex object of the edge whose absence is
   	 *                          ensured.
   	 *  @param v2 - the ending Vertex object of the edge whose absence is
   	 *                        ensured.
   	 *
   	 *  @return true - if the edge <v1, v2> was removed from this Network object
   	 *                          by this call; return false if the edge <v1, v2> was not in this
   	 *                          Network object when this call was made.
    	 *
   	 */
  	public boolean removeEdge(Vertex v1, Vertex v2) {
    	if (!adjacencyMap.containsKey(v1) || !adjacencyMap.get(v1).containsKey(v2))
	      	return false;

    	TreeMap<Vertex, Double> neighborMap = adjacencyMap.get(v1);
    	neighborMap.remove(v2);
    	if (!directed) {
        	TreeMap<Vertex, Double> neighborMapV2 = adjacencyMap.get(v2);
        	neighborMapV2.remove(v1);
    	}
    	return true;
  	} // method removeEdge


  	public Set<Vertex> vertexSet() {
    	return adjacencyMap.keySet();
  	}

  	public Set<Vertex> getNeighbors(Vertex v) {
    	TreeSet<Vertex> neighbors = new TreeSet<Vertex>();
    	if (adjacencyMap.containsKey(v)) {
    		TreeMap<Vertex, Double> neighborMap = adjacencyMap.get(v);
        	for (Map.Entry<Vertex, Double> entry : neighborMap.entrySet()) {
        		neighbors.add(entry.getKey());
        	}
    	}
    	return neighbors;
  	}

  	/**
  	 *  Returns a String representation of this Network object.
  	 *  The averageTime(V, E) is O(V + E).
  	 *
  	 *  @return a String representation of this Network object.
  	 *
  	 */
  	public String toString() {
    	return adjacencyMap.toString();
  	} // method toString

	/**
	 * Builds a graph whose vertices are strings by reading the
	 * vertices and edges from the textfile <tt>filename</tt>. The format
	 * of the file is <code>
	 *     nVertices
	 *     vertex_1 vertex_2 ...vertex_n
	 *     nEdges
	 *     vertex_i vertex_j weight
	 *     . . .  </code>         ...
	 * @param filename name of the text file with vertex and edge specifications.
	 * @return <tt>DiGraph</tt> object with generic type String.
	 */
	public static Network<String> readNetwork(String filename) throws FileNotFoundException {
		// type of Graph
		int typeOfGraph;
		// nVertices is number of vertices to read
		int i, nVertices, nEdges;
		// use for input of vertices (v1) and edges ( {v1, v2} )
		String v1, v2;
		// edge weight
		double weight;
		Network<String> net = 


		   Scanner scan= new Scanner(new File (filename));
	       typeOfGraph=scan.nextInt();
	       if(typeOfGraph==1)
	          net.setDirected(true);
	       else net.setDirected(false);
	       scan.nextLine();
	       
	       //Leemos el numero de vertices
	       nVertices=scan.nextInt();
	       scan.nextLine();	       
	       //Añadimos los vertices
	       for(i=0;i<nVertices;i++)
		       	  net.addVertex(scan.nextLine());
	       
	       //Leemos el numero de aristas
	       nEdges=scan.nextInt();
	       scan.nextLine();
	       
	       for(i=1;i<=nEdges;i++){
	    	   v1=scan.next();
	    	   v2=scan.next();
	    	   weight=scan.nextDouble();
	    	   net.addEdge(v1, v2, weight);
	    	   }       
		return net;
	}

	public void showNetwork() {
    	for (Map.Entry<Vertex, TreeMap<Vertex, Double>> e1 : adjacencyMap.entrySet()) {
    		TreeMap<Vertex, Double> neighborMap = e1.getValue();
        	for (Map.Entry<Vertex, Double> e2 : neighborMap.entrySet()) {
				System.out.println(e1.getKey() + " " + e2.getKey() + " " + e2.getValue());
        	}
    	}
	}

    // IMPLEMENT THE EXERCISES SUGGESTED AT THE ACTTIVITY 05
    // ...

  	public Iterator<Vertex> iterator() {
        return adjacencyMap.keySet().iterator();
  	}

  	public BreadthFirstIterator breadthFirstIterator(Vertex v) {
    	if (!adjacencyMap.containsKey(v))
      		return null;
    	return new BreadthFirstIterator(v);
  	}

  	public DepthFirstIterator depthFirstIterator(Vertex v) {
    	if (!adjacencyMap.containsKey (v))
      		return null;
    	return new DepthFirstIterator(v);
  	}

  	protected class BreadthFirstIterator implements Iterator<Vertex> {
    	protected LinkedQueue<Vertex> queue;
    	protected TreeMap<Vertex, Boolean> reached;
    	protected Vertex current;

    	public BreadthFirstIterator(Vertex start) {
      		queue = new LinkedQueue<Vertex>();
      		reached = new TreeMap<Vertex, Boolean>();
      		Iterator<Vertex> itr = adjacencyMap.keySet().iterator();
      		
      		while (itr.hasNext())
        		reached.put (itr.next(), false);

      		queue.push(start);
      		reached.put(start, true);
    	}


    	public boolean hasNext() {
      		return !(queue.isEmpty());
    	}


    	public Vertex next() {
      		Vertex to;
      		current = queue.pop();
      		TreeMap<Vertex, Double> neighborMap = adjacencyMap.get(current);
      		
            for (Map.Entry<Vertex, Double> entry : neighborMap.entrySet()) {
            	to = entry.getKey();

        		if (!reached.get(to)) {
          			reached.put(to, true);
          			queue.push(to);
        		}
      		}
      		return current;
    	}


    	public void remove() {
      		removeVertex(current);
    	}

  	}

  	protected class DepthFirstIterator implements Iterator<Vertex> {
    	ALStack<Vertex> stack;
    	TreeMap<Vertex, Boolean> reached;
    	Vertex current;

    	public DepthFirstIterator(Vertex start) {
      		stack = new ALStack<Vertex>();
      		reached = new TreeMap<Vertex, Boolean>();
      		Iterator<Vertex> itr = adjacencyMap.keySet().iterator();
      		
      		while (itr.hasNext())
        		reached.put(itr.next(), false);

      		stack.push(start);
      		reached.put(start, true);
    	}

    	public boolean hasNext() {
      		return !(stack.isEmpty());
    	}

    	public Vertex next() {
      		Vertex to;
      		current = stack.pop();
          	TreeMap<Vertex, Double> neighborMap = adjacencyMap.get(current);
          	
            for (Map.Entry<Vertex, Double> entry : neighborMap.entrySet()) {
                to = entry.getKey();

        		if (!reached.get(to)) {
          			reached.put(to, true);
          			stack.push(to);
        		}
      		}
      		return current;
    	}


    	public void remove() {
      		Network.this.removeVertex(current);
    	}

  	}

  	//Devuelve el grado de entrada de un vertice
	public int inDegree(Vertex vertice){
		int contador=0;
		for(Iterator<Entry<Vertex, TreeMap<Vertex, Double>>> iterador = adjacencyMap.entrySet().iterator();iterador.hasNext();)	
            if(iterador.next().getValue().containsKey(vertice))
            	contador++;
		
		return contador;
	}

  	//Devuelve el grado de salida de un vertice
	public int outDegree(Vertex vertice){		
		return adjacencyMap.get(vertice).size();
	}

	//Devuelve un arrayList con la busqueda en profundidad
	public ArrayList<String> toArrayDFS(Vertex inicial){
		ArrayList<String> resultado=new ArrayList<String>();
  		result=new ArrayList<Vertex>();  		
  		visited= new TreeMap<Vertex, Boolean>();
  		for(Iterator<Vertex> itr=iterator();itr.hasNext();)
  			visited.put(itr.next(),false);
  		toArrayDFSAux(inicial);
  		
  		for(Vertex _vertice: result)
  			resultado.add(_vertice.toString());
  		
  		return resultado;
	}
	
	public void toArrayDFSAux(Vertex current){		
  		result.add(current);
  		visited.put(current, true);  		
  		for (Vertex _vecino: getNeighbors(current))		
  			if(!visited.get(_vecino))
  				toArrayDFSAux(_vecino);  		
  	}

	//Devuelve un array usando la busqueda en profundidad iterativamente
	public ArrayList<String> toArrayDFSIterative(Vertex vertice){
  		ArrayList<String> resultado = new ArrayList<String>();
  		ALStack<Vertex> pila=new ALStack<Vertex>();
  		Vertex actual=null;
  		resultado.add(vertice.toString());
  		
  		for (Vertex _vecino: getNeighbors(vertice))  		
  			pila.push(_vecino);
  		
  		while(!pila.isEmpty()){
  			actual=pila.pop();	        	  
  			if(!resultado.contains(actual.toString()))
  				resultado.add(actual.toString());
  			for(Vertex _vecino: getNeighbors(actual))
  				if(!resultado.contains(_vecino.toString()))
  					pila.push(_vecino);	        	  
  		}
	
  		return resultado;
	}

	//Devuelve un array con el resultado de la busqueda en anchura
	public ArrayList<String> toArrayBFS(Vertex inicial){  		
  		ArrayList<String> resultado = new ArrayList<String>();
  		LinkedQueue<Vertex> q = new LinkedQueue<Vertex>();
  		result = new ArrayList<Vertex>();
  		visited = new TreeMap<Vertex, Boolean>();
  		Vertex current;
  		for(Iterator<Vertex> iterador=iterator();iterador.hasNext();)
  			visited.put(iterador.next(),false);
  		
  		q.push(inicial);
  		visited.put(inicial, true);
  		while (!q.isEmpty()){  			
  			current=q.pop();  			
  			result.add(current);
  			for(Vertex _vecino: getNeighbors(current))  				
  				if (!visited.get(_vecino)){
  					visited.put(_vecino,true);
  					q.push(_vecino);
  				}  			
  		}
  		
  		for(Vertex _vertice: result)
  			resultado.add(_vertice.toString());
  		
  		return resultado;
  	}
  	

	//Devuelve true si to es alcanzable desde from
	public boolean isReachable(Vertex from,Vertex to){
		ArrayList<String> resultado = new ArrayList<String>();
  		ALStack<Vertex> pila=new ALStack<Vertex>();
  		Vertex actual;
  		
  		resultado.add(from.toString());  		
  		for(Vertex _vecino: getNeighbors(from))
  			pila.push(_vecino);
  		
  		while(!pila.isEmpty()){
  			actual=pila.pop();
  			if(actual.equals(to))
  				return true; 				
  			if(!resultado.contains(actual.toString()))
  				resultado.add(actual.toString());
  			for(Vertex _vertice: getNeighbors(actual))
  				if(!resultado.contains(_vertice.toString()))
  					pila.push(_vertice);	        	  
  		}
  		
  		return false;
	}

	//Devuelve true si el vertice es fuente 
	public boolean isSource(Vertex vertice){
		//Si no tiene ninguna entrada no puede ser fuente
		if (adjacencyMap.get(vertice).size()==0)
			return false;
		
		for(Iterator<Entry<Vertex, TreeMap<Vertex, Double>>> iterador = adjacencyMap.entrySet().iterator();iterador.hasNext();)
	        if(iterador.next().getValue().containsValue(vertice))
	        	return false;
		return true;
	}

	//Vertice sumidero, solo llegan aristas
	public boolean isSink(Vertex vertice) {
		//Si tiene alguna entrada no puede ser sumidero
		if (adjacencyMap.get(vertice).size()>0)
			return false;

		return true;
	}

	public ArrayList<ArrayList<String>> simplePaths(Vertex from,Vertex to) {
		ArrayList<ArrayList<String>> resultado=new ArrayList<ArrayList<String>>();
		ArrayList<String> temp;
		result = new ArrayList<Vertex>();
		resultSimplePaths = new ArrayList<ArrayList<Vertex>>();	
		
		simplePathsAux(from,to);

		for(ArrayList<Vertex> _deVertice: resultSimplePaths){
			temp=new ArrayList<String>();
			for(Vertex _aVertice: _deVertice)
				temp.add(_aVertice.toString());
			resultado.add(temp);
		}
		
		return resultado;
	}


	private void simplePathsAux(Vertex current, Vertex destination){
		result.add(current);

		if (current.equals(destination)){
			ArrayList<Vertex> resultAux=new ArrayList<Vertex>();
			for (int i=0;i<result.size();i++)
				resultAux.add(result.get(i));
			resultSimplePaths.add(resultAux);
		}

		for(Vertex _vecino: getNeighbors(current))
			if (!result.contains(_vecino))
				simplePathsAux(_vecino,destination);
		
		result.remove(result.size()-1);
	}
	
	public String shortestPathWithSimplePaths(Vertex origen,Vertex destino) {
		Double pesoDelCaminoActual;
		ArrayList<Vertex> actual;
		shortestPathWeight=Double.MAX_VALUE;
		simplePaths(origen,destino);		
		for (int i=0;i<resultSimplePaths.size();i++){
			actual=resultSimplePaths.get(i);
			pesoDelCaminoActual=0.0;
			for(int j=0;j<actual.size()-1;j++)
				pesoDelCaminoActual+=getWeight(actual.get(j),actual.get(j+1));
			if(pesoDelCaminoActual<shortestPathWeight){
				shortestPathWeight=0.0;
				resultShortestPath=new ArrayList<Vertex>();
	  		for (int j=0;j<actual.size()-1;j++){
	  			resultShortestPath.add(actual.get(j));	  			
	  			shortestPathWeight+=getWeight(actual.get(j),actual.get(j+1));
	  		  }
			}
		}
		String resultado="The shortest path using the simple paths algorithm from "+origen.toString()+" to "+destino.toString()+" is:\n";
		for(int i=0;i<resultShortestPath.size();i++)
		  resultado+=resultShortestPath.get(i)+" --> ";		
		return resultado+destino.toString()+" => ("+shortestPathWeight+")";
	}
	

	public String largestLenghtPathWithSimplePaths(Vertex origen,Vertex destino){				
		numberOfSimplePaths=sumOfLengthOfSimplePaths=largestLengthPath=0;
		Double averageLength=0.00;
		simplePaths(origen,destino);		

		for (int i=0;i<resultSimplePaths.size();i++){ 
			if(resultSimplePaths.get(i).size()>largestLengthPath){
				largestLengthPath=resultSimplePaths.get(i).size();
				resultLargestLengthPath=new ArrayList<Vertex>();
				for (int j=0;j<largestLengthPath-1;j++)
					resultLargestLengthPath.add(resultSimplePaths.get(i).get(j));				
			}
			numberOfSimplePaths++;	
			sumOfLengthOfSimplePaths+=resultSimplePaths.get(i).size()-1;
		}
		
		String resultado="The largest length path using the simple paths algorithm from "+origen.toString()+" to "+destino.toString()+" is:\n";
		for(int i=0;i<resultLargestLengthPath.size();i++)
			  resultado+=resultLargestLengthPath.get(i)+" --> ";		
		resultado+=destino.toString()+" => ("+(largestLengthPath-1)+")\n";  		
		averageLength=(double)sumOfLengthOfSimplePaths/(double)numberOfSimplePaths;	
		resultado+="The average length of the simple paths from "+origen.toString()+" to "+destino.toString()+" is: "+String.format("%.2f",averageLength).replace(',','.');

		return resultado;
	}
	
	//Metodo del examen
	public String simplePathsAndLargestPath(Vertex origen, Vertex destino){
		result= new ArrayList<Vertex>();
		resultSimplePaths=new ArrayList<ArrayList<Vertex>>();
		resultLargestLengthPath=new ArrayList<Vertex>();
		largestLengthPath=0;
		
		simplePathsAndLargestPathAux(origen,destino);
		String resultado="The simple paths from "+origen.toString()+" to "+destino.toString()+" are:\n";
		for(int i=0;i<resultSimplePaths.size();i++){
			  resultado+=resultSimplePaths.get(i)+" --> ";
			  double pathWeightSum=0.0;
			  for(int j=0;j<resultSimplePaths.get(i).size()-1;j++)
				  pathWeightSum+=getWeight(resultSimplePaths.get(i).get(j),resultSimplePaths.get(i).get(j+1));
				resultado+=" => "+pathWeightSum+"\n";
		}
        resultado+="\n The largest path from "+origen.toString()+" to "+destino.toString()+" is:\n";
		for(int i=0;i<resultLargestLengthPath.size();i++)
			resultado+=resultLargestLengthPath.get(i)+" --> ";
		resultado+=" => ("+largestLengthPath+")";		

       return resultado;		
	}

	//Metodo del examen auxiliar	
	public void simplePathsAndLargestPathAux(Vertex current, Vertex destino){
		result.add(current);
		if(current.equals(destino)){
			ArrayList<Vertex> resultAux=new ArrayList<Vertex>();
			for(int i=0;i<result.size();i++)
				resultAux.add(result.get(i));
			resultSimplePaths.add(resultAux);
			
			double pathWeightSum=0.0;
			for(int i=0;i<result.size();i++)
				pathWeightSum+=getWeight(result.get(i),result.get(i+1));
			if(pathWeightSum>largestLengthPath){
				largestLengthPath=(int) pathWeightSum;
				resultLargestLengthPath.clear();
				for(int j=0;j<result.size();j++)
					resultLargestLengthPath.add(result.get(j));
			}
		}
		for(Vertex _vertice: adjacencyMap.get(current).keySet())
		  if(!result.contains(_vertice))
			  simplePathsAndLargestPathAux(_vertice, destino);
		
		result.remove(result.size()-1);
	}

	public boolean isPathLength(Vertex source,Vertex destination,int length){
		simplePaths(source,destination);
		for (int i = 0; i < resultSimplePaths.size(); i++)
			if(resultSimplePaths.get(i).size()-1==length)
				return true;		
		return false;
	}

	public boolean isStronglyConnected(){
		if (!directed)
			return false;

		Network<Vertex> transposeGraph = new Network(directed);
		for (Map.Entry<Vertex, TreeMap<Vertex, Double>> e1 : adjacencyMap.entrySet()) 
			for (Map.Entry<Vertex, Double> e2 : e1.getValue().entrySet())
				transposeGraph.addEdge(e2.getKey(), e1.getKey(), e2.getValue());
		
		for (Vertex v: adjacencyMap.keySet()){
			int countS = 0;
			Iterator<Vertex> itrS = new BreadthFirstIterator(v);
			while (itrS.hasNext()) {
				itrS.next();	
				countS++;
			}
			if (countS < adjacencyMap.size())
				return false;
			int countP = 0;
			Iterator<Vertex> itrP = transposeGraph.breadthFirstIterator(v);
			while (itrP.hasNext()) {
				itrP.next();
				countP++;
			}
			if (countP < adjacencyMap.size())
				return false;
		}
		return true;
	}

	public boolean isConnected(){	
	    	if (directed)
	        	return false;
	    	for (Vertex v : adjacencyMap.keySet()) {
	      		Iterator<Vertex> itr = new BreadthFirstIterator(v);
	      		int count = 0;
	      		while (itr.hasNext()) {
	        		itr.next();
	        		count++;
	      		}
	      		if (count < adjacencyMap.size())
	        		return false;
	    	} 
	    	return true;
	}

	
	public boolean isTree(){
    	if (directed)
        	return false;
    	
    	if(!isConnected())
    		return false;
    	if(numeroDefVertices()-1!=numberOfEdges()>>1)
    		return false;
		return true;
	}
	
	public void showNetworkVertexes(){
		Vertex v1;
		for(Iterator<Entry<Vertex, TreeMap<Vertex, Double>>> itr=adjacencyMap.entrySet().iterator();itr.hasNext();){
			System.out.print(itr.next()+" ->");			
		}
			
	}
	
} // class Network
