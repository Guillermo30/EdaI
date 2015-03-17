package org.eda1.actividad05;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.Map.Entry;

import org.eda1.estructurasdedatos.ALStack;
import org.eda1.estructurasdedatos.Graph;
import org.eda1.estructurasdedatos.LinkedQueue;

// TODO: Auto-generated Javadoc
/**
 * The Class Network.
 *
 * @param <Vertex> the generic type
 */
public class Network<Vertex> implements Graph<Vertex>, Iterable<Vertex> {

	/** The directed. */
	protected boolean directed; // directed = false (unDirected), directed =
								// true (DiGraph)

	/** The adjacency map. */
								protected TreeMap<Vertex, TreeMap<Vertex, Double>> adjacencyMap;

	/** The visited. */
	protected TreeMap<Vertex, Boolean> visited;

	/** The result. */
	protected ArrayList<Vertex> result;

	/** The result simple paths. */
	protected ArrayList<ArrayList<Vertex>> resultSimplePaths;

	/** The shortest path weight. */
	Double shortestPathWeight;
	
	/** The result shortest path. */
	protected ArrayList<Vertex> resultShortestPath;

	/** The largest length path. */
	int largestLengthPath;
	
	/** The result largest length path. */
	protected ArrayList<Vertex> resultLargestLengthPath;

	/** The sum of length of simple paths. */
	int numberOfSimplePaths, sumOfLengthOfSimplePaths;

	/**
	 * Initialized this Network object to be empty.
	 */
	public Network() {
		directed = true;
		adjacencyMap = new TreeMap<Vertex, TreeMap<Vertex, Double>>();
	} // default constructor

	/**
	 * Instantiates a new network.
	 *
	 * @param uDOrD the u d or d
	 */
	public Network(boolean uDOrD) {
		directed = uDOrD;
		adjacencyMap = new TreeMap<Vertex, TreeMap<Vertex, Double>>();
	} // default constructor

	/**
	 * Initializes this Network object to a shallow copy of a specified Network
	 * object. The averageTime(V, E) is O(V + E).
	 * 
	 * @param network
	 *            - the Network object that this Network object is initialized
	 *            to a shallow copy of.
	 * 
	 */
	public Network(Network<Vertex> network) {
		this.directed = network.directed;
		this.adjacencyMap = new TreeMap<Vertex, TreeMap<Vertex, Double>>(
				network.adjacencyMap);
	} // copy constructor

	/**
	 * Sets the directed.
	 *
	 * @param uDOrD the new directed
	 */
	public void setDirected(boolean uDOrD) {
		directed = uDOrD;
	}

	/**
	 * Gets the directed.
	 *
	 * @return the directed
	 */
	public boolean getDirected() {
		return directed;
	}

	/**
	 * Determines if this Network object contains no vertices.
	 * 
	 * @return true - if this Network object contains no vertices.
	 * 
	 */
	public boolean isEmpty() {
		return adjacencyMap.isEmpty();
	} // method isEmpty

	/**
	 * Determines the number of vertices in this Network object.
	 * 
	 * @return the number of vertices in this Network object.
	 * 
	 */
	public int numberOfVertices() {
		return adjacencyMap.size();
	} // method size

	/**
	 * Returns the number of edges in this Network object. The averageTime (V,
	 * E) is O (V).
	 * 
	 * @return the number of edges in this Network object.
	 * 
	 */
	public int numberOfEdges() {
		int count = 0;
		for (Map.Entry<Vertex, TreeMap<Vertex, Double>> entry : adjacencyMap
				.entrySet())
			count += entry.getValue().size();
		return count;
	} // method getEdgeCount

	/* (non-Javadoc)
	 * @see org.eda1.estructurasdedatos.Graph#clear()
	 */
	public void clear() {
		adjacencyMap.clear();
	}

	/* (non-Javadoc)
	 * @see org.eda1.estructurasdedatos.Graph#getWeight(java.lang.Object, java.lang.Object)
	 */
	public double getWeight(Vertex v1, Vertex v2) {
		if (!(adjacencyMap.containsKey(v1) && adjacencyMap.get(v1).containsKey(
				v2)))
			return -1.0;

		return adjacencyMap.get(v1).get(v2);
	} // method getWeight

	/* (non-Javadoc)
	 * @see org.eda1.estructurasdedatos.Graph#setWeight(java.lang.Object, java.lang.Object, double)
	 */
	public double setWeight(Vertex v1, Vertex v2, double w) {
		if (!(adjacencyMap.containsKey(v1) && adjacencyMap.get(v1).containsKey(
				v2)))
			return -1.0;

		TreeMap<Vertex, Double> neighborMap = adjacencyMap.get(v1);
		double oldWeight = neighborMap.get(v2);
		adjacencyMap.get(v1).put(v2, w);
		return oldWeight;
	}

	/**
	 * Determines if this Network object contains a specified Vertex object.
	 * 
	 * @param vertex
	 *            - the Vertex object whose presence is sought.
	 * 
	 * @return true - if vertex is an element of this Network object.
	 */
	public boolean containsVertex(Vertex vertex) {
		return adjacencyMap.containsKey(vertex);
	} // method containsVertex

	/**
	 * Determines if this Network object contains an edge specified by two
	 * vertices. The averageTime (V, E) is O (E / V).
	 * 
	 * @param v1
	 *            - the beginning Vertex object of the edge sought.
	 * @param v2
	 *            - the ending Vertex object of the edge sought.
	 * 
	 * @return true - if this Network object contains the edge <v1, v2>.
	 * 
	 */
	public boolean containsEdge(Vertex v1, Vertex v2) {
		if (adjacencyMap.containsKey(v1)
				&& adjacencyMap.get(v1).containsKey(v2))
			return true;
		else
			return false;
	} // method containsEdge

	/**
	 * Ensures that a specified Vertex object is an element of this Network
	 * object.
	 * 
	 * @param vertex
	 *            - the Vertex object whose presence is ensured.
	 * 
	 * @return true - if vertex was added to this Network object by this call;
	 *         returns false if vertex was already an element of this Network
	 *         object when this call was made.
	 */
	public boolean addVertex(Vertex vertex) {
		if (adjacencyMap.containsKey(vertex))
			return false;
		adjacencyMap.put(vertex, new TreeMap<Vertex, Double>());
		return true;
	} // method addVertex

	/**
	 * Ensures that an edge is in this Network object.
	 *
	 * @param v1 - the beginning Vertex object of the edge whose presence is
	 * ensured.
	 * @param v2 - the ending Vertex object of the edge whose presence is
	 * ensured.
	 * @param w the w
	 * @return true - if the given edge (and weight) were added to this Network
	 * object by this call; return false, if the given edge (and weight)
	 * were already in this Network object when this call was made.
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
	 * Ensures that a specified Vertex object is not an element of this Network
	 * object. The averageTime (V, E) is O (V + E).
	 * 
	 * @param vertex
	 *            - the Vertex object whose absence is ensured.
	 * 
	 * @return true - if vertex was removed from this Network object by this
	 *         call; returns false if vertex was not an element of this Network
	 *         object when this call was made.
	 * 
	 */
	public boolean removeVertex(Vertex vertex) {
		if (!adjacencyMap.containsKey(vertex))
			return false;

		for (Map.Entry<Vertex, TreeMap<Vertex, Double>> entry : adjacencyMap
				.entrySet()) {
			TreeMap<Vertex, Double> neighborMap = entry.getValue();
			neighborMap.remove(vertex);
		} // for each vertex in the network
		adjacencyMap.remove(vertex);
		return true;
	} // removeVertex

	/**
	 * Ensures that an edge specified by two vertices is absent from this
	 * Network object. The averageTime (V, E) is O (E / V).
	 * 
	 * @param v1
	 *            - the beginning Vertex object of the edge whose absence is
	 *            ensured.
	 * @param v2
	 *            - the ending Vertex object of the edge whose absence is
	 *            ensured.
	 * 
	 * @return true - if the edge <v1, v2> was removed from this Network object
	 *         by this call; return false if the edge <v1, v2> was not in this
	 *         Network object when this call was made.
	 * 
	 */
	public boolean removeEdge(Vertex v1, Vertex v2) {
		if (!adjacencyMap.containsKey(v1)
				|| !adjacencyMap.get(v1).containsKey(v2))
			return false;

		TreeMap<Vertex, Double> neighborMap = adjacencyMap.get(v1);
		neighborMap.remove(v2);
		if (!directed) {
			TreeMap<Vertex, Double> neighborMapV2 = adjacencyMap.get(v2);
			neighborMapV2.remove(v1);
		}
		return true;
	} // method removeEdge

	/* (non-Javadoc)
	 * @see org.eda1.estructurasdedatos.Graph#vertexSet()
	 */
	public Set<Vertex> vertexSet() {
		return adjacencyMap.keySet();
	}

	/* (non-Javadoc)
	 * @see org.eda1.estructurasdedatos.Graph#getNeighbors(java.lang.Object)
	 */
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
	 * Returns a String representation of this Network object. The
	 * averageTime(V, E) is O(V + E).
	 * 
	 * @return a String representation of this Network object.
	 * 
	 */
	public String toString() {
		return adjacencyMap.toString();
	} // method toString

	/**
	 * Builds a graph whose vertices are strings by reading the vertices and
	 * edges from the textfile <tt>filename</tt>. The format of the file is
	 * <code>
	 * nVertices
	 * vertex_1 vertex_2 ...vertex_n
	 * nEdges
	 * vertex_i vertex_j weight
	 * . . .  </code> ...
	 *
	 * @param filename name of the text file with vertex and edge specifications.
	 * @return <tt>DiGraph</tt> object with generic type String.
	 * @throws FileNotFoundException the file not found exception
	 */
	public static Network<String> readNetwork(String filename)
			throws FileNotFoundException {
		// type of Graph
		int typeOfGraph;
		// nVertices is number of vertices to read
		int i, nVertices, nEdges;
		// use for input of vertices (v1) and edges ( {v1, v2} )
		String v1, v2;
		// edge weight
		double weight;
		Network<String> net = new Network<String>();

		// READ THE FILE ACCORDING TO THE SPECIFIED FORMAT
		// ...

		Scanner scan = new Scanner(new File(filename));
		typeOfGraph = scan.nextInt();
		if (typeOfGraph == 1)
			net.setDirected(true);
		else
			net.setDirected(false);
		scan.nextLine();

		nVertices = scan.nextInt();
		scan.nextLine();
		for (i = 0; i < nVertices; i++)
			net.addVertex(scan.nextLine());

		nEdges = scan.nextInt();
		scan.nextLine();

		for (i = 1; i <= nEdges; i++) {
			v1 = scan.next();
			v2 = scan.next();
			weight = scan.nextDouble();
			net.addEdge(v1, v2, weight);
		}

		return net;
	}

	/**
	 * Show network.
	 */
	public void showNetwork() {
		for (Map.Entry<Vertex, TreeMap<Vertex, Double>> e1 : adjacencyMap
				.entrySet()) {
			TreeMap<Vertex, Double> neighborMap = e1.getValue();
			for (Map.Entry<Vertex, Double> e2 : neighborMap.entrySet()) {
				System.out.println(e1.getKey() + " " + e2.getKey() + " "
						+ e2.getValue());
			}
		}
	}

	// IMPLEMENT THE EXERCISES SUGGESTED AT THE ACTTIVITY 05
	// ...

	/**
	 * The Class EdgeWeight.
	 */
	public class EdgeWeight {

		/** The from. */
		Vertex from = null;
		
		/** The to. */
		Vertex to = null;
		
		/** The weight. */
		Double weight = null;

		/**
		 * Instantiates a new edge weight.
		 *
		 * @param from the from
		 * @param to the to
		 * @param weight the weight
		 */
		public EdgeWeight(Vertex from, Vertex to, double weight){
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		
		/**
		 * Gets the from vertex.
		 *
		 * @return the from vertex
		 */
		public Vertex getFromVertex(){
			return this.from;
		}
		
		/**
		 * Gets the to vertex.
		 *
		 * @return the to vertex
		 */
		public Vertex getToVertex(){
			return this.to;
		}
		
		/**
		 * Gets the weight.
		 *
		 * @return the weight
		 */
		public double getWeight(){
			return this.weight;
		}
		
		/**
		 * Compare to.
		 *
		 * @param edge the edge
		 * @return the int
		 */
		public int compareTo(EdgeWeight edge){
			return (int)(this.weight-edge.getWeight());
		}
		
		/* (non-Javadoc)
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		public boolean equals(Object obj){
			EdgeWeight edge = (EdgeWeight) obj;
			if(this.weight!=edge.getWeight())
				return false;
			if(!this.from.equals(edge.getFromVertex()))
				return false;
			if(!this.to.equals(edge.getToVertex()))
				return false;
			return true;
		}
		
		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		public String toString(){
			return this.from+" -> "+this.to+" => "+this.weight;
		}

	}
	
	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	public Iterator<Vertex> iterator() {
		return adjacencyMap.keySet().iterator();
	}

	/**
	 * Breadth first iterator.
	 *
	 * @param v the v
	 * @return the breadth first iterator
	 */
	public BreadthFirstIterator breadthFirstIterator(Vertex v) {
		if (!adjacencyMap.containsKey(v))
			return null;
		return new BreadthFirstIterator(v);
	}

	/**
	 * Depth first iterator.
	 *
	 * @param v the v
	 * @return the depth first iterator
	 */
	public DepthFirstIterator depthFirstIterator(Vertex v) {
		if (!adjacencyMap.containsKey(v))
			return null;
		return new DepthFirstIterator(v);
	}

	/**
	 * The Class BreadthFirstIterator.
	 */
	protected class BreadthFirstIterator implements Iterator<Vertex> {
		
		/** The queue. */
		protected LinkedQueue<Vertex> queue;

		/** The reached. */
		protected TreeMap<Vertex, Boolean> reached;

		/** The current. */
		protected Vertex current;

		/**
		 * Instantiates a new breadth first iterator.
		 *
		 * @param start the start
		 */
		public BreadthFirstIterator(Vertex start) {
			queue = new LinkedQueue<Vertex>();

			reached = new TreeMap<Vertex, Boolean>();

			Iterator<Vertex> itr = adjacencyMap.keySet().iterator();
			while (itr.hasNext())
				reached.put(itr.next(), false);

			queue.push(start);
			reached.put(start, true);
		}

		/* (non-Javadoc)
		 * @see java.util.Iterator#hasNext()
		 */
		public boolean hasNext() {
			return !(queue.isEmpty());
		}

		/* (non-Javadoc)
		 * @see java.util.Iterator#next()
		 */
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

		/* (non-Javadoc)
		 * @see java.util.Iterator#remove()
		 */
		public void remove() {
			removeVertex(current);
		}

	}

	/**
	 * The Class DepthFirstIterator.
	 */
	protected class DepthFirstIterator implements Iterator<Vertex> {
		
		/** The stack. */
		ALStack<Vertex> stack;

		/** The reached. */
		TreeMap<Vertex, Boolean> reached;

		/** The current. */
		Vertex current;

		/**
		 * Instantiates a new depth first iterator.
		 *
		 * @param start the start
		 */
		public DepthFirstIterator(Vertex start) {
			stack = new ALStack<Vertex>();

			reached = new TreeMap<Vertex, Boolean>();

			Iterator<Vertex> itr = adjacencyMap.keySet().iterator();
			while (itr.hasNext())
				reached.put(itr.next(), false);

			stack.push(start);
			reached.put(start, true);
		}

		/* (non-Javadoc)
		 * @see java.util.Iterator#hasNext()
		 */
		public boolean hasNext() {
			return !(stack.isEmpty());
		}

		/* (non-Javadoc)
		 * @see java.util.Iterator#next()
		 */
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

		/* (non-Javadoc)
		 * @see java.util.Iterator#remove()
		 */
		public void remove() {
			Network.this.removeVertex(current);
		}

	}

	/**
	 * In degree.
	 *
	 * @param v the v
	 * @return the int
	 */
	public int inDegree(Vertex v) {
		int contador=0;
		for(Iterator<Entry<Vertex, TreeMap<Vertex, Double>>> iterador = adjacencyMap.entrySet().iterator();iterador.hasNext();)	
            if(iterador.next().getValue().containsKey(v))
            	contador++;
		
		return contador;
	}

	/**
	 * Out degree.
	 *
	 * @param v the v
	 * @return the int
	 */
	public int outDegree(Vertex v) {
		// TODO Auto-generated method stub
		return adjacencyMap.get(v).size();
	}

	/**
	 * To array dfs.
	 *
	 * @param v the v
	 * @return the array list
	 */
	public ArrayList<String> toArrayDFS(Vertex v) {
		// TODO Auto-generated method stub
		ArrayList<String> resultado=new ArrayList<String>();
  		result=new ArrayList<Vertex>();  		
  		visited= new TreeMap<Vertex, Boolean>();
  		for(Iterator<Vertex> itr=iterator();itr.hasNext();)
  			visited.put(itr.next(),false);
  		toArrayDFSAux(v);
   		
  		for(Vertex _vertice: result)
  			resultado.add(_vertice.toString());
  		
  		return resultado;
	}
	
	/**
	 * To array dfs aux.
	 *
	 * @param current the current
	 */
	public void toArrayDFSAux(Vertex current){		
  		result.add(current);
  		visited.put(current, true);  		
  		for (Vertex _vecino: getNeighbors(current))		
  			if(!visited.get(_vecino))
  				toArrayDFSAux(_vecino);  		
  	}

	/**
	 * To array dfs iterative.
	 *
	 * @param v the v
	 * @return the array list
	 */
	public ArrayList<String> toArrayDFSIterative(Vertex v) {
		ArrayList<String> resultado = new ArrayList<String>();
  		ALStack<Vertex> pila=new ALStack<Vertex>();
  		Vertex actual=null;
  		resultado.add(v.toString());
  		
  		for (Vertex _vecino: getNeighbors(v))  		
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

	/**
	 * To array bfs.
	 *
	 * @param v the v
	 * @return the array list
	 */
	public ArrayList<String> toArrayBFS(Vertex v) {
		// TODO Auto-generated method stub
		ArrayList<String> resultado = new ArrayList<String>();
  		LinkedQueue<Vertex> q = new LinkedQueue<Vertex>();
  		result = new ArrayList<Vertex>();
  		visited = new TreeMap<Vertex, Boolean>();
  		Vertex current;
  		for(Iterator<Vertex> iterador=iterator();iterador.hasNext();)
  			visited.put(iterador.next(),false);
  		
  		q.push(v);
  		visited.put(v, true);
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

	/**
	 * Checks if is reachable.
	 *
	 * @param vOut the v out
	 * @param vIn the v in
	 * @return true, if is reachable
	 */
	public boolean isReachable(Vertex vOut, Vertex vIn) {
		// TODO Auto-generated method stub
		ArrayList<String> resultado = new ArrayList<String>();
  		ALStack<Vertex> pila=new ALStack<Vertex>();
  		Vertex actual;
  		
  		resultado.add(vOut.toString());  		
  		for(Vertex _vecino: getNeighbors(vOut))
  			pila.push(_vecino);
  		
  		while(!pila.isEmpty()){
  			actual=pila.pop();
  			if(actual.equals(vIn))
  				return true; 				
  			if(!resultado.contains(actual.toString()))
  				resultado.add(actual.toString());
  			for(Vertex _vertice: getNeighbors(actual))
  				if(!resultado.contains(_vertice.toString()))
  					pila.push(_vertice);	        	  
  		}
  		
  		return false;
	}

	/**
	 * Checks if is source.
	 *
	 * @param v the v
	 * @return true, if is source
	 */
	public boolean isSource(Vertex v) {
		// TODO Auto-generated method stub
		if (adjacencyMap.get(v).size()==0)
			return false;
		
		for(Iterator<Entry<Vertex, TreeMap<Vertex, Double>>> iterador = adjacencyMap.entrySet().iterator();iterador.hasNext();)
	        if(iterador.next().getValue().containsValue(v))
	        	return false;
		return true;
	}

	/**
	 * Checks if is sink.
	 *
	 * @param v the v
	 * @return true, if is sink
	 */
	public boolean isSink(Vertex v) {
		// TODO Auto-generated method stub
		if (adjacencyMap.get(v).size()>0)
			return false;

		return true;
	}

	/**
	 * Simple paths.
	 *
	 * @param vOut the v out
	 * @param vIn the v in
	 * @return the array list
	 */
	public ArrayList<ArrayList<String>> simplePaths(Vertex vOut, Vertex vIn) {
		// TODO Auto-generated method stub
		ArrayList<ArrayList<String>> resultado=new ArrayList<ArrayList<String>>();
		ArrayList<String> temp;
		result = new ArrayList<Vertex>();
		resultSimplePaths = new ArrayList<ArrayList<Vertex>>();	
		
		simplePathsAux(vOut,vIn);

		for(ArrayList<Vertex> _deVertice: resultSimplePaths){
			temp=new ArrayList<String>();
			for(Vertex _aVertice: _deVertice)
				temp.add(_aVertice.toString());
			resultado.add(temp);
		}
		
		return resultado;
	}
	
	/**
	 * Simple paths aux.
	 *
	 * @param vOut the v out
	 * @param vIn the v in
	 */
	private void simplePathsAux(Vertex vOut, Vertex vIn){
		result.add(vOut);

		if (vOut.equals(vIn)){
			ArrayList<Vertex> resultAux=new ArrayList<Vertex>();
			for (int i=0;i<result.size();i++)
				resultAux.add(result.get(i));
			resultSimplePaths.add(resultAux);
		}

		for(Vertex _vecino: getNeighbors(vOut))
			if (!result.contains(_vecino))
				simplePathsAux(_vecino,vIn);
		
		result.remove(result.size()-1);
	}

	/**
	 * Shortest path with simple paths.
	 *
	 * @param origen the origen
	 * @param destino the destino
	 * @return the string
	 */
	public String shortestPathWithSimplePaths(Vertex origen, Vertex destino) {
		// TODO Auto-generated method stub
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

	/**
	 * Largest lenght path with simple paths.
	 *
	 * @param origen the origen
	 * @param destino the destino
	 * @return the string
	 */
	public String largestLenghtPathWithSimplePaths(Vertex origen, Vertex destino) {
		// TODO Auto-generated method stub
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

	/**
	 * Checks if is path length.
	 *
	 * @param original the original
	 * @param destino the destino
	 * @param length the length
	 * @return true, if is path length
	 */
	public boolean isPathLength(Vertex original, Vertex destino, int length) {
		// TODO Auto-generated method stub
		simplePaths(original,destino);
		for (int i = 0; i < resultSimplePaths.size(); i++)
			if(resultSimplePaths.get(i).size()-1==length)
				return true;		
		return false;
	}

	/**
	 * Checks if is strongly connected.
	 *
	 * @return true, if is strongly connected
	 */
	public boolean isStronglyConnected() {
		// TODO Auto-generated method stub
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

	/**
	 * Checks if is connected.
	 *
	 * @return true, if is connected
	 */
	public boolean isConnected() {
		// TODO Auto-generated method stub
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

	/**
	 * Checks if is tree.
	 *
	 * @return true, if is tree
	 */
	public boolean isTree() {
		// TODO Auto-generated method stub
		if (directed)
        	return false;
    	
    	if(!isConnected())
    		return false;
    	if(numberOfVertices()-1!=numberOfEdges()>>1)
    		return false;
		return true;
	}

	/**
	 * Dijkstra.
	 *
	 * @param source the source
	 * @param destination the destination
	 * @return the array list
	 */
	public ArrayList Dijkstra(Vertex source, Vertex destination) {
		TreeMap<Vertex, Double> D = new TreeMap<Vertex, Double>();
		TreeMap<Vertex, Vertex> S = new TreeMap<Vertex, Vertex>();
		TreeSet<Vertex> V_minus_S = new TreeSet<Vertex>();
		Vertex vertex, to = null, from;
		if(!isReachable(source, destination))
			return null;
		if (source == null || destination == null)
			return new ArrayList<Object>();
		if (source.equals(destination))
			return new ArrayList<Object>();
		if (!(adjacencyMap.containsKey(source) && adjacencyMap.containsKey(destination)))
			return new ArrayList<Object>();
		for (Map.Entry<Vertex, TreeMap<Vertex, Double>> e : adjacencyMap.entrySet())
			if (!(source.equals(e.getKey())))
				V_minus_S.add(e.getKey());
		Iterator itr = V_minus_S.iterator();
		while(itr.hasNext()) {
			vertex = (Vertex)itr.next();
			if (isAdjacent(source, vertex)) {
				S.put(vertex, source); D.put(vertex, getWeight(source, vertex));
			}
			else {
				S.put(vertex, null); D.put(vertex, Double.POSITIVE_INFINITY);
			}
		}
		S.put(source, source);
		D.put(source, 0.0);
		while (!V_minus_S.isEmpty()) {
			Double minWeight = Double.POSITIVE_INFINITY; from = null;
			Iterator itr1 = V_minus_S.iterator();
			while(itr1.hasNext()) {
				vertex = (Vertex)itr1.next();
				if (D.get(vertex) < minWeight) {
					minWeight = D.get(vertex); from = vertex;
				}			
			}
			if (!from.equals(null)) {
				V_minus_S.remove(from);
				Iterator itr2 = V_minus_S.iterator();
				while(itr2.hasNext()) {
					to = (Vertex)itr2.next();
					if (isAdjacent(from, to)) {
						double weight = getWeight(from, to);
						if (D.get(from) + weight < D.get(to)) {
							D.put(to, D.get(from) + weight);
							S.put(to, from);
						}
					}
				}				
				
			}else
				break;
		}
		ArrayList<Object> path = new ArrayList<Object>();
		ALStack<Vertex> st = new ALStack<Vertex>();
		if (S.get(destination).equals(null)) {
			System.out.println ("The vertex " + destination + " is not reachable from " + source);
			return path;
		}else {
			st.push(destination);
			while (!(st.peek().equals(source)))
				st.push(S.get(st.peek()));
			while (!(st.isEmpty())) {
				path.add(st.peek());
				st.pop();
			}
		}
		ArrayList<Object> edgePath = new ArrayList<Object>();
		this.shortestPathWeight = 0.0;
		for (int i = 0; (i < (path.size() - 1)); i++) {
			EdgeWeight edgeWeight = new EdgeWeight((Vertex)path.get(i), (Vertex)path.get(i + 1), getWeight((Vertex)path.get(i),(Vertex)path.get(i + 1)));
			shortestPathWeight += getWeight((Vertex)path.get(i),(Vertex)path.get(i + 1));
			edgePath.add(edgeWeight);
		}
		return edgePath;
	}

	/**
	 * Checks if is adjacent.
	 *
	 * @param source the source
	 * @param destination the destination
	 * @return true, if is adjacent
	 */
	private boolean isAdjacent(Vertex source, Vertex destination) {
		if(this.adjacencyMap.get(source).containsKey(destination))
			return true;
		return false;
	}
	
	/**
	 * Dijkstra pq.
	 *
	 * @param source the source
	 * @param destination the destination
	 * @return the array list
	 */
	public ArrayList DijkstraPQ(Vertex source, Vertex destination) {
		TreeMap<Vertex, Double> D = new TreeMap<Vertex, Double>();
		TreeMap<Vertex, Vertex> S = new TreeMap<Vertex, Vertex>();
		LinkedQueue<Vertex> V_minus_S = new LinkedQueue<Vertex>();
		Vertex vertex, to = null, from;
		for(Map.Entry<Vertex, TreeMap<Vertex, Double>> e: adjacencyMap.entrySet()){
//			V_minus_S.add(e.getKey());
			D.put(e.getKey(), Double.POSITIVE_INFINITY);
			S.put(e.getKey(), null);
		}
		D.put(source, 0.0);
		S.put(source, source);
		V_minus_S.push(source);
		while(!V_minus_S.isEmpty()){
			Vertex u = V_minus_S.pop();
			for(Map.Entry<Vertex, Double> f: adjacencyMap.get(u).entrySet()){
				if(D.get(f.getKey()) > (D.get(u) + adjacencyMap.get(u).get(f.getKey()))){
					D.put(f.getKey(), (D.get(u) + adjacencyMap.get(u).get(f.getKey())));
					S.put(f.getKey(), u);
					V_minus_S.push(f.getKey());
				}
			}
		}
		ALStack<Vertex> st = new ALStack<Vertex>();
		from = destination;
		st.push(from);
		while(!from.equals(source)){
			to = S.get(from);
			st.push(to);
			from = to;
		}
		this.shortestPathWeight = 0.0;
		ArrayList resultado = new ArrayList();
		from = st.pop();
		while(!st.isEmpty()){
			to = st.pop();
			EdgeWeight aux = new EdgeWeight(from, to, adjacencyMap.get(from).get(to));
			shortestPathWeight += adjacencyMap.get(from).get(to);
			resultado.add(aux);
			from = to;
		}
		return resultado;
	}

	/**
	 * Checks if is floyd graph.
	 *
	 * @return true, if is floyd graph
	 */
	public boolean isFloydGraph() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Adapt to floyd graph.
	 */
	public void adaptToFloydGraph() {
		for(Map.Entry<Vertex, TreeMap<Vertex, Double>> eI : adjacencyMap.entrySet()){
			Vertex v = eI.getKey();
			TreeMap<Vertex, Double> neighborMap = eI.getValue();
			if(neighborMap == null){
				adjacencyMap.put(v, new TreeMap<Vertex, Double>());
				adjacencyMap.get(v).put(v, new Double(0.0));
			}else{
				addEdge(v, v, 0.0);
			}
		}
	}

	/**
	 * Floyd ec.
	 *
	 * @return the string
	 */
	public String FloydEC() {
		final double INFINITY = Double.MAX_VALUE;
  		double [][] D;
  		int [][] A;
  		TreeMap<Vertex, Integer> vtxIndex = new TreeMap<Vertex, Integer>();
  		TreeMap<Vertex, Double> vtxPesoMax = new TreeMap<Vertex, Double>();
  		Vertex vertex, from, to;
  		EdgeWeight edgeWeight;
  		double weight;
  		int i, j, k;
  		int n = numberOfVertices();
  		if (n <= 0)
  			return "";
  		D = new double [n] [n];
  		A = new int [n] [n];
  		int index = 0;
  		for (Map.Entry<Vertex, TreeMap<Vertex, Double>> e : adjacencyMap.entrySet())
  			vtxIndex.put(e.getKey(), index++);
  		for (i = 0; i < n; i++) {
  	  		for (j = 0; j < n; j++) {
  	    		D[i][j] = INFINITY;
  	    		A[i][j] = -1;
  	    	}
  		}
  		for (Map.Entry<Vertex, TreeMap<Vertex, Double>> e1 : adjacencyMap.entrySet()) {
  			TreeMap<Vertex, Double> neighborMap = e1.getValue();
  			for (Map.Entry<Vertex, Double> e2 : neighborMap.entrySet()) {
  	  			from = e1.getKey();
  	  			to = e2.getKey();
  	  			weight = e2.getValue();
  	  			D[vtxIndex.get(from)][vtxIndex.get(to)] = weight;
  			}
  		}
  		for (k = 0; (k < n); k++) {
  			for (i = 0; (i < n); i++) {
	  			for (j = 0; (j < n); j++) {
	  				if ((D[i][k] < INFINITY) && (D[k][j] < INFINITY)) {
		  				if ((D[i][k] + D[k][j]) < D[i][j]) {
		  		  			D[i][j] = D[i][k] + D[k][j];
		  		  			A[i][j] = k;
		  		  		}
	  	  			}
	  			}
  			}
  		}
  		//TODO
  		String result = "{";
  		double auxMax;
  		double auxMin = Double.POSITIVE_INFINITY;
  		ArrayList<EdgeWeight> listaResult = new ArrayList<EdgeWeight>();
  		Vertex auxVxt = null;
  		for(Map.Entry<Vertex, Integer> e: vtxIndex.entrySet()){
  			auxMax = -1.0;
  			result += e.getKey()+"=";
  			for(i=0; i<D.length; i++){
  				if(D[i][vtxIndex.get(e.getKey())] < 1.7976931348623157E308 && D[i][vtxIndex.get(e.getKey())] != 0.0 && D[i][vtxIndex.get(e.getKey())] > auxMax){
  					auxMax = D[i][vtxIndex.get(e.getKey())];
  				}
  			}
  			if(auxMax > 0.0 && auxMin > auxMax){
  				auxMin = auxMax;
  				auxVxt = e.getKey();
  			}
  			result += auxMax+", ";
  		}
  		result = result.substring(0, result.length()-2);
  		result += "}; centro = "+auxVxt+"\n[";
  		for(Map.Entry<Vertex, TreeMap<Vertex, Double>> e: adjacencyMap.entrySet()){
  			double valorAux = D[vtxIndex.get(auxVxt)][vtxIndex.get(e.getKey())];
  			if(valorAux<1.7976931348623157E308){
  				if(!e.getKey().equals(auxVxt)){
  	  				result += "("+auxVxt+", "+e.getKey()+")="+valorAux+", ";
  	  			}
  			}
  		}
  		result = result.substring(0, result.length()-2);
  		result += "]\n";
		return result;		
	}
	
  	/**
	   * Floyd.
	   */
	  public void Floyd(){
  		final double INFINITY = Double.MAX_VALUE;
  		double [][] D;
  		int [][] A;
  		TreeMap<Vertex, Integer> vtxIndex = new TreeMap<Vertex, Integer>();
  		Vertex vertex, from, to;
  		EdgeWeight edgeWeight;
  		double weight;
  		int i, j, k;
  		int n = numberOfVertices();
  		if (n <= 0)
  			return;
  		D = new double [n] [n];
  		A = new int [n] [n];
  		int index = 0;
  		for (Map.Entry<Vertex, TreeMap<Vertex, Double>> e : adjacencyMap.entrySet())
  			vtxIndex.put(e.getKey(), index++);
  		for (i = 0; i < n; i++) {
  	  		for (j = 0; j < n; j++) {
  	    		D[i][j] = INFINITY;
  	    		A[i][j] = -1;
  	    	}
  		}
  		for (Map.Entry<Vertex, TreeMap<Vertex, Double>> e1 : adjacencyMap.entrySet()) {
  			TreeMap<Vertex, Double> neighborMap = e1.getValue();
  			for (Map.Entry<Vertex, Double> e2 : neighborMap.entrySet()) {
  	  			from = e1.getKey();
  	  			to = e2.getKey();
  	  			weight = e2.getValue();
  	  			D[vtxIndex.get(from)][vtxIndex.get(to)] = weight;
  			}
  		}
  		for (k = 0; (k < n); k++) {
  			for (i = 0; (i < n); i++) {
	  			for (j = 0; (j < n); j++) {
	  				if ((D[i][k] < INFINITY) && (D[k][j] < INFINITY)) {
		  				if ((D[i][k] + D[k][j]) < D[i][j]) {
		  		  			D[i][j] = D[i][k] + D[k][j];
		  		  			A[i][j] = k;
		  		  		}
	  	  			}
	  			}
  			}
  		}
  		showPaths(D, A, vtxIndex);
  	}
	
  	/**
	   * Show paths.
	   *
	   * @param D the d
	   * @param A the a
	   * @param vI the v i
	   */
	  private void showPaths(double[][] D,int[][] A, TreeMap<Vertex, Integer> vI){
		final double INFINITY = Double.MAX_VALUE;  
		int i, j;
		for (i = 0; (i < numberOfVertices()); i++) {
			for (j = 0; (j < numberOfVertices()); j++) {
				if ((D[i][j] >= 0) && (D[i][j] < INFINITY)) {
					if (!(getVertexFromIndex(vI, i).equals(getVertexFromIndex(vI, j)))) {
						System.out.print("Camino de " + getVertexFromIndex(vI, i) +	" a " + getVertexFromIndex(vI, j) + ": " + getVertexFromIndex(vI, i) + ", ");
						showPath(i, j, A, vI);
						System.out.println(getVertexFromIndex(vI, j) + " (" + D[i][j] + ")");
					}
				}
			}
			
		}
		System.out.println();
	}
  
  	/**
	   * Show path.
	   *
	   * @param i the i
	   * @param j the j
	   * @param A the a
	   * @param vI the v i
	   */
	  private void showPath(int i, int j, int[][] A, TreeMap<Vertex, Integer> vI){
  		int k = A[i][j];
  		if(k>=0){
  			showPath(i, k, A, vI);
  			System.out.println(getVertexFromIndex(vI, k)+",");
  			showPath(k, j, A, vI);
  		}
  	}
	
  	/**
	   * Gets the vertex from index.
	   *
	   * @param vI the v i
	   * @param index the index
	   * @return the vertex from index
	   */
	  private Vertex getVertexFromIndex(TreeMap<Vertex, Integer> vI, int index){
		Vertex v = null;
		for(Map.Entry<Vertex, Integer> ei:vI.entrySet()){
			if(ei.getValue() == index){
				v=ei.getKey();
				break;
			}
		}
		return v;
	}
  	
} // class Network
