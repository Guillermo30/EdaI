package org.eda1.practica04;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

import org.eda1.estructurasdedatos.ALStack;
import org.eda1.estructurasdedatos.Graph;
import org.eda1.estructurasdedatos.LinkedQueue;

// TODO: Auto-generated Javadoc
/**
 * The Class RoadNetwork.
 *
 * @param <Vertex> the generic type
 */
public class RoadNetwork<Vertex> implements Graph<Vertex>, Iterable<Vertex> {

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

	/** The largest path weight. */
	Double largestPathWeight;
	
	/** The result largest path. */
	protected ArrayList<Vertex> resultLargestPath;

	/** The result floyd. */
	protected ArrayList<ArrayList<Vertex>> resultFloyd;
	
	/** The path floyd. */
	ArrayList<Vertex> pathFloyd;

	/**
	 * Initialized this Network object to be empty.
	 */
	public RoadNetwork() {
		directed = true;
		adjacencyMap = new TreeMap<Vertex, TreeMap<Vertex, Double>>();
	}

	/**
	 * Instantiates a new road network.
	 *
	 * @param uDOrD the u d or d
	 */
	public RoadNetwork(boolean uDOrD) {
		directed = uDOrD;
		adjacencyMap = new TreeMap<Vertex, TreeMap<Vertex, Double>>();
	}

	/**
	 * Initializes this Network object to a shallow copy of a specified Network
	 * object. The averageTime(V, E) is O(V + E).
	 * 
	 * @param network
	 *            - the Network object that this Network object is initialized
	 *            to a shallow copy of.
	 * 
	 */
	public RoadNetwork(RoadNetwork<Vertex> network) {
		this.directed = network.directed;
		this.adjacencyMap = new TreeMap<Vertex, TreeMap<Vertex, Double>>(
				network.adjacencyMap);
	}

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
	}

	/**
	 * Determines the number of vertices in this Network object.
	 * 
	 * @return the number of vertices in this Network object.
	 * 
	 */
	public int numberOfVertices() {
		return adjacencyMap.size();
	}

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
	}

	/* (non-Javadoc)
	 * @see org.eda1.estructurasdedatos.Graph#clear()
	 */
	public void clear() {
		adjacencyMap.clear();
	}

	/**
	 * Determines the weight of an edge in this Network object. The averageTime
	 * (V, E) is O (E / V).
	 * 
	 * @param v1
	 *            - the beginning Vertex object of the edge whose weight is
	 *            sought.
	 * @param v2
	 *            - the ending Vertex object of the edge whose weight is sought.
	 * 
	 * @return the weight of edge <v1, v2>, if <v1, v2> forms an edge; return
	 *         –1.0 if <v1, v2> does not form an edge in this Network object.
	 * 
	 */
	public double getWeight(Vertex v1, Vertex v2) {
		if (!(adjacencyMap.containsKey(v1) && adjacencyMap.get(v1).containsKey(
				v2)))
			return -1.0;

		return adjacencyMap.get(v1).get(v2);
	}

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
	 * Checks if is adjacent.
	 *
	 * @param v1 the v1
	 * @param v2 the v2
	 * @return true, if is adjacent
	 */
	public boolean isAdjacent(Vertex v1, Vertex v2) {
		if ((adjacencyMap.containsKey(v1))
				&& (adjacencyMap.get(v1).containsKey(v2)))
			return true;
		else
			return false;
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
	}

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
	}

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
	}

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
	}

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
		}
		adjacencyMap.remove(vertex);
		return true;
	}

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
	}

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
	}

	/**
	 * Read road network.
	 *
	 * @param filename the filename
	 * @return the road network
	 * @throws FileNotFoundException the file not found exception
	 */
	public static RoadNetwork<String> readRoadNetwork(String filename)
			throws FileNotFoundException {
		// type of Graph
		int typeOfGraph;
		// nVertices is number of vertices to read
		int i, nVertices, nEdges;
		// use for input of vertices (v1) and edges ( {v1, v2} )
		String v1, v2;
		// edge weight
		double weight;

		RoadNetwork<String> net = new RoadNetwork<String>();

		// READ THE FILE ACCORDING TO THE SPECIFIED FORMAT
		// ...

		Scanner scan = new Scanner(new File(filename));
		typeOfGraph = scan.nextInt();
		if (typeOfGraph == 1)
			net.setDirected(true);
		else
			net.setDirected(false);

		nVertices = scan.nextInt();
		scan.nextLine();

		for (i = 1; i <= nVertices; i++)
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

	// IMPLEMENT THE EXERCISES SUGGESTED AT THE PRACTICE 04
	// ...

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

	// ...

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
			RoadNetwork.this.removeVertex(current);
		}

	}

	/**
	 * The Class VertexWeightPair.
	 */
	protected class VertexWeightPair implements Comparable<VertexWeightPair> {
		
		/** The vertex. */
		Vertex vertex;
		
		/** The weight. */
		double weight;

		/**
		 * Initializes this VertexWeightPair from vertex and weight.
		 *
		 * @param vertex the vertex
		 * @param weight the weight
		 */
		public VertexWeightPair(Vertex vertex, double weight) {
			this.vertex = vertex;
			this.weight = weight;
		}

		/**
		 * Returns the vertex in this VertexWeightPair.
		 *
		 * @return the vertex
		 */
		public Vertex getVertex() {
			return vertex;
		}

		/**
		 * Returns the weight in this VertexWeightPair.
		 *
		 * @return the weight
		 */
		public double getWeight() {
			return weight;
		}

		/**
		 * Set the weight in this VertexWeightPair.
		 *
		 * @param w the new weight
		 */
		public void setWeight(double w) {
			weight = w;
		}

		/**
		 * Returns an int <, = or > 0 , depending on whether this
		 * VertexWeightPair's weight is <, = or > other's weight.
		 *
		 * @param other the other
		 * @return the int
		 */
		public int compareTo(VertexWeightPair other) {
			return (int) (weight - other.getWeight());
		}

		/**
		 * Returns a String representation of this VertexWeightPair.
		 *
		 * @return the string
		 */
		public String toString() {
			return vertex.toString() + "  " + String.valueOf(weight);
		}

	}

	/**
	 * The Class EdgeWeight.
	 */
	protected class EdgeWeight implements Comparable<EdgeWeight> {
		
		/** The from. */
		Vertex from;
		
		/** The to. */
		Vertex to;
		
		/** The weight. */
		double weight;

		/**
		 * Initializes this EdgeWeight from v1, v2 and weight.
		 *
		 * @param from the from
		 * @param to the to
		 * @param weight the weight
		 */
		public EdgeWeight(Vertex from, Vertex to, double weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		/**
		 * Returns the "from" vertex in this EdgeWeight.
		 *
		 * @return the from vertex
		 */
		public Vertex getFromVertex() {
			return from;
		}

		/**
		 * Returns the "to" vertex in this EdgeWeight.
		 *
		 * @return the to vertex
		 */
		public Vertex getToVertex() {
			return to;
		}

		/**
		 * Returns the weight vertex in this EdgeWeight.
		 *
		 * @return the weight
		 */
		public double getWeight() {
			return weight;
		}

		/**
		 * Returns an int <, = or > 0, depending on whether this EdgeWeight's
		 * weight is <, = or > edge's weight.
		 *
		 * @param edge the edge
		 * @return the int
		 */
		public int compareTo(EdgeWeight edge) {
			return (int) (weight - edge.weight);
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		@Override
		public boolean equals(Object obj) {
			EdgeWeight e = (EdgeWeight) obj;
			if ((from == e.from) && (to == e.to) && (weight == e.weight))
				return true;
			else
				return false;
		}

		/**
		 * Returns a String representation of this EdgeWeight.
		 *
		 * @return the string
		 */
		public String toString() {
			return "<" + from.toString() + ", " + to.toString() + "; "
					+ String.valueOf(weight) + ">";
		}

	}

	/**
	 * To array dfs.
	 *
	 * @param start the start
	 * @return the array list
	 */
	public ArrayList<Vertex> toArrayDFS(Vertex start) {
		result = new ArrayList<Vertex>();
		visited = new TreeMap<Vertex, Boolean>();
		for (Iterator<Vertex> itr = iterator(); itr.hasNext();)
			visited.put(itr.next(), false);
		toArrayDFSAux(start);
		return result;
	}

	/**
	 * To array dfs aux.
	 *
	 * @param current the current
	 */
	public void toArrayDFSAux(Vertex current) {
		result.add(current);
		visited.put(current, true);

		for (Vertex _vecino : getNeighbors(current))
			if (!visited.get(_vecino))
				toArrayDFSAux(_vecino);

	}

	/**
	 * To array bfs.
	 *
	 * @param start the start
	 * @return the array list
	 */
	public ArrayList<Vertex> toArrayBFS(Vertex start) {
		Vertex current;
		result = new ArrayList<Vertex>();
		visited = new TreeMap<Vertex, Boolean>();
		LinkedQueue<Vertex> cola = new LinkedQueue<Vertex>();
		for (Iterator<Vertex> iterador = iterator(); iterador.hasNext();)
			visited.put(iterador.next(), false);
		cola.push(start);
		visited.put(start, true);
		while (!cola.isEmpty()) {
			current = cola.pop();
			result.add(current);
			for (Vertex _vecino : getNeighbors(current))
				if (!visited.get(_vecino)) {
					visited.put(_vecino, true);
					cola.push(_vecino);
				}
		}
		return result;
	}

	/**
	 * Dijkstra.
	 *
	 * @param origen the origen
	 * @param destino the destino
	 * @return the array list
	 */
	public ArrayList<EdgeWeight> Dijkstra(Vertex origen, Vertex destino) {
		TreeMap<Vertex, Double> D = new TreeMap<Vertex, Double>();
		TreeMap<Vertex, Vertex> S = new TreeMap<Vertex, Vertex>();
		TreeSet<Vertex> V_minus_S = new TreeSet<Vertex>();
		ArrayList<Vertex> path = new ArrayList<Vertex>();
		ALStack<Vertex> pila = new ALStack<Vertex>();
		ArrayList<EdgeWeight> resultado = new ArrayList<EdgeWeight>();
		Vertex from;
		double peso, minPeso;

		if (origen == null || destino == null)
			return resultado;
		if (origen.equals(destino))
			return resultado;
		if (!(adjacencyMap.containsKey(origen) && adjacencyMap
				.containsKey(destino)))
			return resultado;
		for (Vertex _nuevoVertice : adjacencyMap.keySet())
			if (!(origen.equals(_nuevoVertice)))
				V_minus_S.add(_nuevoVertice);
		for (Vertex _restante : V_minus_S) {
			if (isAdjacent(origen, _restante)) {
				S.put(_restante, origen);
				D.put(_restante, getWeight(origen, _restante));
			} else {
				S.put(_restante, null);
				D.put(_restante, Double.MAX_VALUE);
			}
		}
		S.put(origen, origen);
		D.put(origen, 0.0);
		while (!V_minus_S.isEmpty()) {
			minPeso = Double.MAX_VALUE;
			from = null;
			for (Vertex _vertice : V_minus_S) {
				if (D.get(_vertice) < minPeso) {
					minPeso = D.get(_vertice);
					from = _vertice;
				}
			}
			if (from != null) {
				V_minus_S.remove(from);
				for (Vertex _to : V_minus_S)
					if (isAdjacent(from, _to)) {
						peso = getWeight(from, _to);
						if (D.get(from) + peso < D.get(_to)) {
							D.put(_to, D.get(from) + peso);
							S.put(_to, from);
						}
					}
			} else
				break;
		}
		if (S.get(destino) == null) {
			System.out.println("El vertice " + destino
					+ " no es alcanzable desde " + origen);
			return resultado;
		} else {
			pila.push(destino);
			while (!pila.peek().equals(origen))
				pila.push(S.get(pila.peek()));
			while (!(pila.isEmpty()))
				path.add(pila.pop());
		}
		for (int i = 0; (i < (path.size() - 1)); i++)
			resultado.add(new EdgeWeight(path.get(i), path.get(i + 1),
					getWeight(path.get(i), path.get(i + 1))));

		return resultado;
	}

	/**
	 * Shortest path std.
	 *
	 * @param origen the origen
	 * @param pasando the pasando
	 * @param destino the destino
	 * @return the string
	 */
	public String shortestPathSTD(Vertex origen, Vertex pasando, Vertex destino) {
		ArrayList<EdgeWeight> shortestPathST = Dijkstra(origen, pasando);
		ArrayList<EdgeWeight> shortestPathTD = Dijkstra(pasando, destino);
		double distanceShortestPathAC = 0.0;
		String str = "";
		for (int i = 0; i < shortestPathST.size(); i++) {
			str += shortestPathST.get(i).from + " -> ";
			distanceShortestPathAC += shortestPathST.get(i).getWeight();
		}

		for (int i = 0; i < shortestPathTD.size(); i++) {
			str += shortestPathTD.get(i).from + " -> ";
			if (i == shortestPathTD.size() - 1)
				str += shortestPathTD.get(i).to;
			distanceShortestPathAC += shortestPathTD.get(i).getWeight();
		}
		str += " => " + distanceShortestPathAC;
		return str;
	}

	/**
	 * Dijkstra tree.
	 *
	 * @param source the source
	 * @return the array list
	 */
	public ArrayList<EdgeWeight> DijkstraTree(Vertex source) {
		TreeMap<Vertex, Double> D = new TreeMap<Vertex, Double>();
		TreeMap<Vertex, Vertex> S = new TreeMap<Vertex, Vertex>();
		TreeSet<Vertex> V_minus_S = new TreeSet<Vertex>();
		ArrayList<EdgeWeight> resultado = new ArrayList<EdgeWeight>();
		Vertex vertex, to = null, from;
		double weight;

		if (source == null)
			return resultado;
		if (!adjacencyMap.containsKey(source))
			return resultado;

		for (Vertex e : adjacencyMap.keySet())
			if (!(source.equals(e)))
				V_minus_S.add(e);

		for (Iterator<Vertex> iterador = V_minus_S.iterator(); iterador
				.hasNext();) {
			vertex = iterador.next();
			if (isAdjacent(source, vertex)) {
				S.put(vertex, source);
				D.put(vertex, getWeight(source, vertex));
			} else {
				S.put(vertex, null);
				D.put(vertex, Double.MAX_VALUE);
			}
		}

		S.put(source, source);
		D.put(source, 0.0);

		while (!V_minus_S.isEmpty()) {
			Double minWeight = Double.MAX_VALUE;
			from = null;
			for (Iterator<Vertex> iterador = V_minus_S.iterator(); iterador
					.hasNext();) {
				vertex = iterador.next();
				if (D.get(vertex) < minWeight) {
					minWeight = D.get(vertex);
					from = vertex;
				}
			}

			resultado.add(new EdgeWeight(S.get(from), from, getWeight(
					S.get(from), from)));

			if (!from.equals(null)) {
				V_minus_S.remove(from);
				for (Iterator<Vertex> iterador = V_minus_S.iterator(); iterador
						.hasNext();) {
					to = iterador.next();
					if (isAdjacent(from, to)) {
						weight = getWeight(from, to);
						if (D.get(from) + weight < D.get(to)) {
							D.put(to, D.get(from) + weight);
							S.put(to, from);
						}
					}
				}
			} else
				break;
		}
		return resultado;
	}

	/**
	 * Dijkstra farthest.
	 *
	 * @param source the source
	 * @return the array list
	 */
	public ArrayList<ArrayList<EdgeWeight>> DijkstraFarthest(Vertex source) {
		ArrayList<ArrayList<EdgeWeight>> resultado = new ArrayList<ArrayList<EdgeWeight>>();
		ArrayList<EdgeWeight> tempResultado = new ArrayList<EdgeWeight>();
		ALStack<ArrayList<EdgeWeight>> pila = new ALStack<ArrayList<EdgeWeight>>();
		ALStack<EdgeWeight> tempPila = new ALStack<EdgeWeight>();
		TreeMap<Vertex, Double> D = new TreeMap<Vertex, Double>();
		TreeMap<Vertex, Vertex> S = new TreeMap<Vertex, Vertex>();
		TreeSet<Vertex> V_minus_S = new TreeSet<Vertex>();
		Vertex vertice, to = null, from, toAux, fromAux;

		if (source == null)
			return resultado;
		if (!adjacencyMap.containsKey(source))
			return resultado;

		for (Vertex e : adjacencyMap.keySet())
			if (!(source.equals(e)))
				V_minus_S.add(e);

		for (Iterator<Vertex> iterador = V_minus_S.iterator(); iterador
				.hasNext();) {
			vertice = iterador.next();
			if (isAdjacent(source, vertice)) {
				S.put(vertice, source);
				D.put(vertice, getWeight(source, vertice));
			} else {
				S.put(vertice, null);
				D.put(vertice, Double.MAX_VALUE);
			}
		}

		S.put(source, source);
		D.put(source, 0.0);

		while (!V_minus_S.isEmpty()) {
			Double minWeight = Double.MAX_VALUE;
			from = null;
			for (Iterator<Vertex> iterador1 = V_minus_S.iterator(); iterador1
					.hasNext();) {
				vertice = iterador1.next();
				if (D.get(vertice) < minWeight) {
					minWeight = D.get(vertice);
					from = vertice;
				}
			}

			fromAux = from;
			tempResultado = new ArrayList<EdgeWeight>();
			tempPila = new ALStack<EdgeWeight>();
			do {
				toAux = fromAux;
				fromAux = S.get(toAux);
				tempPila.push(new EdgeWeight(fromAux, toAux, getWeight(toAux,
						fromAux)));
			} while (!fromAux.equals(source));
			while (!tempPila.isEmpty())
				tempResultado.add(tempPila.pop());
			pila.push(tempResultado);

			if (!from.equals(null)) {
				V_minus_S.remove(from);
				for (Iterator<Vertex> iterador2 = V_minus_S.iterator(); iterador2
						.hasNext();) {
					to = iterador2.next();
					if (isAdjacent(from, to)) {
						double weight = getWeight(from, to);
						if (D.get(from) + weight < D.get(to)) {
							D.put(to, D.get(from) + weight);
							S.put(to, from);
						}
					}
				}
			} else
				break;
		}

		while (!pila.isEmpty())
			resultado.add(pila.pop());

		return resultado;
	}

	/**
	 * Simple paths.
	 *
	 * @param source the source
	 * @param destination the destination
	 * @return the array list
	 */
	public ArrayList<ArrayList<Vertex>> simplePaths(Vertex source,
			Vertex destination) {
		result = new ArrayList<Vertex>();
		resultSimplePaths = new ArrayList<ArrayList<Vertex>>();
		simplePathsAux(source, destination);

		return resultSimplePaths;
	}

	/**
	 * Simple paths aux.
	 *
	 * @param current the current
	 * @param destination the destination
	 */
	private void simplePathsAux(Vertex current, Vertex destination) {
		result.add(current);

		if (current.equals(destination)) {
			ArrayList<Vertex> resultTemp = new ArrayList<Vertex>();
			for (int i = 0; i < result.size(); i++)
				resultTemp.add(result.get(i));
			resultSimplePaths.add(resultTemp);
		} else {
			for (Vertex _vecino : getNeighbors(current))
				if (!result.contains(_vecino))
					simplePathsAux(_vecino, destination);
		}

		result.remove(result.size() - 1);
	}

	/**
	 * Largest path with simple paths.
	 *
	 * @param source the source
	 * @param destination the destination
	 * @return the array list
	 */
	public ArrayList<EdgeWeight> largestPathWithSimplePaths(Vertex source,
			Vertex destination) {
		ArrayList<EdgeWeight> resultado = new ArrayList<EdgeWeight>();
		resultLargestPath = new ArrayList<Vertex>();
		largestPathWeight = Double.MIN_VALUE;

		LargestPathWithSimplePathsAux(source, destination);
		for (int i = 0; i < resultLargestPath.size() - 1; i++)
			resultado.add(new EdgeWeight(resultLargestPath.get(i),
					resultLargestPath.get(i + 1), getWeight(
							resultLargestPath.get(i),
							resultLargestPath.get(i + 1))));

		return resultado;
	}

	/**
	 * Largest path with simple paths aux.
	 *
	 * @param current the current
	 * @param destination the destination
	 */
	private void LargestPathWithSimplePathsAux(Vertex current,
			Vertex destination) {
		result.add(current);

		// Si llegamos al final/destino
		if (current.equals(destination)) {
			double pesoCaminoActual = 0.0;
			for (int i = 0; i < result.size() - 1; i++)
				pesoCaminoActual += getWeight(result.get(i), result.get(i + 1));
			if (pesoCaminoActual > largestPathWeight) {
				largestPathWeight = pesoCaminoActual;
				resultLargestPath.clear();
				for (int j = 0; j < result.size(); j++)
					resultLargestPath.add(result.get(j));
			}
		} else {
			for (Vertex _vecino : getNeighbors(current))
				if (!result.contains(_vecino))
					LargestPathWithSimplePathsAux(_vecino, destination);
		}

		result.remove(result.size() - 1);
	}

	/**
	 * Floyd.
	 *
	 * @return the array list
	 */
	public ArrayList<EdgeWeight> Floyd() {
		final double INFINITY = Double.MAX_VALUE;
		double peso;
		double[][] D;
		int index = 0, n = numberOfVertices();
		int[][] A;
		Vertex from, to;
		TreeMap<Vertex, Integer> vtxIndex = new TreeMap<Vertex, Integer>();

		if (n <= 0)
			return new ArrayList<EdgeWeight>();
		D = new double[n][n];
		A = new int[n][n];

		for (Vertex e : adjacencyMap.keySet())
			vtxIndex.put(e, index++);
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++) {
				D[i][j] = INFINITY;
				A[i][j] = -1;
			}

		for (Map.Entry<Vertex, TreeMap<Vertex, Double>> e1 : adjacencyMap
				.entrySet())
			for (Map.Entry<Vertex, Double> e2 : e1.getValue().entrySet()) {
				from = e1.getKey();
				to = e2.getKey();
				peso = e2.getValue();
				D[vtxIndex.get(from)][vtxIndex.get(to)] = peso;
			}

		for (int k = 0; k < n; k++)
			for (int i = 0; i < n; i++)
				for (int j = 0; j < n; j++)
					if ((D[i][k] < INFINITY) && (D[k][j] < INFINITY))
						if ((D[i][k] + D[k][j]) < D[i][j]) {
							D[i][j] = D[i][k] + D[k][j];
							A[i][j] = k;
						}

		return showPaths(D, A, vtxIndex);
	}

	/**
	 * Show paths.
	 *
	 * @param D the d
	 * @param A the a
	 * @param vI the v i
	 * @return the array list
	 */
	private ArrayList<EdgeWeight> showPaths(double D[][], int A[][],
			TreeMap<Vertex, Integer> vI) {
		ArrayList<EdgeWeight> resultado = new ArrayList<EdgeWeight>();
		final double INFINITY = Double.MAX_VALUE;
		Double weight;
		Vertex to, from;
		int n = numberOfVertices();
		resultFloyd = new ArrayList<ArrayList<Vertex>>();

		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				if (D[i][j] >= 0 && D[i][j] < INFINITY)
					if (!getVertexFromIndex(vI, i).equals(
							getVertexFromIndex(vI, j))) {
						from = getVertexFromIndex(vI, i);
						to = getVertexFromIndex(vI, j);
						weight = D[i][j];
						pathFloyd = new ArrayList<Vertex>();
						pathFloyd.add(from);
						showPath(i, j, A, vI);
						pathFloyd.add(to);
						resultado.add(new EdgeWeight(from, to, weight));
						resultFloyd.add(pathFloyd);
						// System.out.print("Camino de "+from+" a "+to+": "+from+", ");
						// System.out.println(to+"("+weight+")");
					}
		// System.out.println();
		return resultado;
	}

	/**
	 * Show path.
	 *
	 * @param i the i
	 * @param j the j
	 * @param A the a
	 * @param vI the v i
	 */
	private void showPath(int i, int j, int A[][], TreeMap<Vertex, Integer> vI) {
		int k = A[i][j];
		if (k >= 0) {
			showPath(i, k, A, vI);
			// System.out.print(getVertexFromIndex(vI,k)+", ");
			pathFloyd.add(getVertexFromIndex(vI, k));
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
	private Vertex getVertexFromIndex(TreeMap<Vertex, Integer> vI, int index) {
		Vertex v = null;
		for (Map.Entry<Vertex, Integer> ei : vI.entrySet())
			if (ei.getValue() == index) {
				v = ei.getKey();
				break;
			}
		return v;
	}

	/**
	 * Floyd filter by distances.
	 *
	 * @param d1 the d1
	 * @param d2 the d2
	 * @return the array list
	 */
	public ArrayList<ArrayList<EdgeWeight>> FloydFilterByDistances(double d1,
			double d2) {
		ArrayList<ArrayList<EdgeWeight>> resultadoFloyd = new ArrayList<ArrayList<EdgeWeight>>();
		ArrayList<EdgeWeight> temp = new ArrayList<EdgeWeight>();
		Double distancia, vdistancia;

		if (d1 >= d2)
			return resultadoFloyd;

		Floyd();
		for (int i = 0; i < resultFloyd.size() - 1; i++) {
			temp = new ArrayList<EdgeWeight>();
			vdistancia = distancia = 0.0;
			for (int j = 0; j < resultFloyd.get(i).size() - 1; j++) {
				result = resultFloyd.get(i);
				vdistancia = getWeight(result.get(j), result.get(j + 1));
				temp.add(new EdgeWeight(result.get(j), result.get(j + 1),
						vdistancia));
				distancia += vdistancia;
			}
			if (distancia >= d1 && distancia <= d2)
				resultadoFloyd.add(temp);
		}
		return resultadoFloyd;
	}

	/**
	 * Floyd closest farthest.
	 *
	 * @return the array list
	 */
	public ArrayList<ArrayList<EdgeWeight>> FloydClosestFarthest() {
		ArrayList<ArrayList<EdgeWeight>> resultadoClosestFarthest = new ArrayList<ArrayList<EdgeWeight>>();
		ArrayList<EdgeWeight> temp = new ArrayList<EdgeWeight>();
		shortestPathWeight = Double.MAX_VALUE;
		largestPathWeight = Double.MIN_VALUE;
		Double distancia;

		Floyd();
		for (int i = 0; i < resultFloyd.size() - 1; i++) {
			distancia = 0.0;
			result = resultFloyd.get(i);
			for (int j = 0; j < result.size() - 1; j++)
				distancia += getWeight(result.get(j), result.get(j + 1));

			// Comprobamos el minimo
			if (distancia < shortestPathWeight) {
				resultShortestPath = new ArrayList<Vertex>();
				for (Vertex _vertice : result) {
					resultShortestPath.add(_vertice);
					shortestPathWeight = distancia;
				}
			}
			// Comprobamos el maximo
			if (distancia > largestPathWeight) {
				resultLargestPath = new ArrayList<Vertex>();
				for (Vertex _vertice : result) {
					resultLargestPath.add(_vertice);
					largestPathWeight = distancia;
				}
			}
		}

		for (int i = 0; i < resultShortestPath.size() - 1; i++)
			temp.add(new EdgeWeight(resultShortestPath.get(i),
					resultShortestPath.get(i + 1), getWeight(
							resultShortestPath.get(i),
							resultShortestPath.get(i + 1))));
		resultadoClosestFarthest.add(temp);
		temp = new ArrayList<EdgeWeight>();
		for (int i = 0; i < resultLargestPath.size() - 1; i++)
			temp.add(new EdgeWeight(resultLargestPath.get(i), resultLargestPath
					.get(i + 1), getWeight(resultLargestPath.get(i),
					resultLargestPath.get(i + 1))));
		resultadoClosestFarthest.add(temp);

		return resultadoClosestFarthest;
	}

	/**
	 * Floyd filter by name of city.
	 *
	 * @param origen the origen
	 * @return the array list
	 */
	public ArrayList<ArrayList<EdgeWeight>> FloydFilterByNameOfCity(
			Vertex origen) {
		ArrayList<ArrayList<EdgeWeight>> resultadoFloydFilterByNameOfCity = new ArrayList<ArrayList<EdgeWeight>>();
		ArrayList<EdgeWeight> temp;

		Floyd();
		for (int j = 0; j < resultFloyd.size() - 1; j++) {
			result = resultFloyd.get(j);
			if (!result.get(0).equals(origen))
				continue;
			temp = new ArrayList<EdgeWeight>();

			for (int i = 0; i < result.size() - 1; i++)
				temp.add(new EdgeWeight(result.get(i), result.get(i + 1),
						getWeight(result.get(i), result.get(i + 1))));
			resultadoFloydFilterByNameOfCity.add(temp);
		}
		return resultadoFloydFilterByNameOfCity;
	}

	/**
	 * Floyd with dijkstra.
	 *
	 * @return the int
	 */
	public int FloydWithDijkstra() {
		int contador = 0;
		for (Iterator<Vertex> iterador = iterator(); iterador.hasNext();)
			contador += (DijkstraTree(iterador.next())).size();
		return contador;
	}

	/**
	 * Prim.
	 *
	 * @param source the source
	 * @return the array list
	 */
	public ArrayList<EdgeWeight> Prim(Vertex source) {
		final double INFINITY = Double.MAX_VALUE;
		TreeMap<Vertex, Double> D = new TreeMap<Vertex, Double>();
		TreeMap<Vertex, Vertex> S = new TreeMap<Vertex, Vertex>();
		TreeSet<Vertex> V_minus_S = new TreeSet<Vertex>();
		ArrayList<EdgeWeight> MST = new ArrayList<EdgeWeight>();
		Double minWeight, weight;
		Vertex vertice, to = null, from;

		if (source == null)
			return MST;
		if (!(adjacencyMap.containsKey(source)))
			return MST;
		for (Vertex e : adjacencyMap.keySet())
			if (!(source.equals(e)))
				V_minus_S.add(e);

		for (Iterator<Vertex> iterador = V_minus_S.iterator(); iterador
				.hasNext();) {
			vertice = iterador.next();
			if (isAdjacent(source, vertice)) {
				S.put(vertice, source);
				D.put(vertice, getWeight(source, vertice));
			} else {
				S.put(vertice, null);
				D.put(vertice, INFINITY);
			}
		}
		S.put(source, source);
		D.put(source, 0.0);
		while (!V_minus_S.isEmpty()) {
			minWeight = INFINITY;
			from = null;
			for (Iterator<Vertex> iterador = V_minus_S.iterator(); iterador
					.hasNext();) {
				vertice = iterador.next();
				if (D.get(vertice) < minWeight) {
					minWeight = D.get(vertice);
					from = vertice;
				}
			}
			if (from != null) {
				V_minus_S.remove(from);
				MST.add(new EdgeWeight(S.get(from), from, getWeight(
						S.get(from), from)));
				for (Iterator<Vertex> iterador = V_minus_S.iterator(); iterador
						.hasNext();) {
					to = iterador.next();
					if (isAdjacent(from, to)) {
						weight = getWeight(from, to);
						if (weight < D.get(to)) {
							D.put(to, weight);
							S.put(to, from);
						}
					}
				}
			} else
				break;
		}
		return MST;
	}

	/**
	 * Checks if is floyd graph.
	 *
	 * @return true, if is floyd graph
	 */
	public boolean isFloydGraph() {
		Vertex actual;
		for (Iterator<Vertex> iterador = iterator(); iterador.hasNext();) {
			actual = iterador.next();
			if (!getNeighbors(actual).contains(actual)
					|| getWeight(actual, actual) != 0.0)
				return false;
		}
		return true;
	}

	/**
	 * Adapt to floyd graph.
	 */
	public void adaptToFloydGraph() {
		Vertex actual;
		for (Iterator<Vertex> iterador = iterator(); iterador.hasNext();) {
			actual = iterador.next();
			addEdge(actual, actual, 0.0);
		}
	}

}
