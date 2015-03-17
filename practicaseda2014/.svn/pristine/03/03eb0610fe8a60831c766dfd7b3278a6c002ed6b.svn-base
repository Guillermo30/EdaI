package org.eda1.actividad05;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

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
  	public int numberOfVertices() {
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
		Network<String> net = new Network<String>();

        // READ THE FILE ACCORDING TO THE SPECIFIED FORMAT
        // ...

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

} // class Network
