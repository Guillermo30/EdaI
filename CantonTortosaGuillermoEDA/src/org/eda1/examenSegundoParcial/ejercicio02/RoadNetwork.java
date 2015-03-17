package org.eda1.examenSegundoParcial.ejercicio02;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

import org.eda1.estructurasdedatos.ALStack;
import org.eda1.estructurasdedatos.Graph;
import org.eda1.estructurasdedatos.LinkedQueue;

public class RoadNetwork<Vertex> implements Graph<Vertex>, Iterable<Vertex> {

	protected boolean directed; // directed = false (unDirected), directed =
								// true (DiGraph)

	protected TreeMap<Vertex, TreeMap<Vertex, Double>> adjacencyMap;

	protected TreeMap<Vertex, Boolean> visited;

	protected ArrayList<Vertex> result;

	protected ArrayList<ArrayList<Vertex>> resultSimplePaths;

	Double shortestPathWeight;
	protected ArrayList<Vertex> resultShortestPath;

	Double largestPathWeight;
	protected ArrayList<Vertex> resultLargestPath;

	protected ArrayList<ArrayList<Vertex>> resultFloyd;
	ArrayList<Vertex> pathFloyd;

	/**
	 * Initialized this Network object to be empty.
	 */
	public RoadNetwork() {
		directed = true;
		adjacencyMap = new TreeMap<Vertex, TreeMap<Vertex, Double>>();
	}

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

	public void setDirected(boolean uDOrD) {
		directed = uDOrD;
	}

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

	public double setWeight(Vertex v1, Vertex v2, double w) {
		if (!(adjacencyMap.containsKey(v1) && adjacencyMap.get(v1).containsKey(
				v2)))
			return -1.0;

		TreeMap<Vertex, Double> neighborMap = adjacencyMap.get(v1);
		double oldWeight = neighborMap.get(v2);
		adjacencyMap.get(v1).put(v2, w);
		return oldWeight;
	}

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
	 * @param v1
	 *            - the beginning Vertex object of the edge whose presence is
	 *            ensured.
	 * @param v2
	 *            - the ending Vertex object of the edge whose presence is
	 *            ensured.
	 * @param weight
	 *            - the weight of the edge whose presence is ensured.
	 * 
	 * @return true - if the given edge (and weight) were added to this Network
	 *         object by this call; return false, if the given edge (and weight)
	 *         were already in this Network object when this call was made.
	 * 
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
	 * Returns a String representation of this Network object. The
	 * averageTime(V, E) is O(V + E).
	 * 
	 * @return a String representation of this Network object.
	 * 
	 */
	public String toString() {
		return adjacencyMap.toString();
	}

	public static RoadNetwork<String> readRoadNetwork(String filename)
			throws FileNotFoundException {
		// type of Graph
		int typeOfGraph;
		// nVertices is number of vertices to read
		int i, nVertices, nEdges;

		RoadNetwork<String> net = new RoadNetwork<String>();

		// READ THE FILE ACCORDING TO THE SPECIFIED FORMAT
		try {
			FileReader fr = new FileReader(new File(filename));
			BufferedReader br = new BufferedReader(fr);

			typeOfGraph = Integer.parseInt(br.readLine());
			nVertices = Integer.parseInt(br.readLine());

			net.setDirected((typeOfGraph == 1) ? true : false);

			for (i = 0; i < nVertices; i++)
				net.addVertex(br.readLine());

			nEdges = Integer.parseInt(br.readLine());
			String[] words;

			for (i = 0; i < nEdges; i++) {
				words = br.readLine().split(" ");
				net.addEdge(words[0], words[1], Double.parseDouble(words[2]));
			}

			br.close();
			fr.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return net;
	}

	// IMPLEMENT THE EXERCISES SUGGESTED AT THE PRACTICE 04
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
		if (!adjacencyMap.containsKey(v))
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
				reached.put(itr.next(), false);

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
			RoadNetwork.this.removeVertex(current);
		}

	}

	protected class VertexWeightPair implements Comparable<VertexWeightPair> {
		Vertex vertex;
		double weight;

		/**
		 * Initializes this VertexWeightPair from vertex and weight.
		 * 
		 */
		public VertexWeightPair(Vertex vertex, double weight) {
			this.vertex = vertex;
			this.weight = weight;
		}

		/**
		 * Returns the vertex in this VertexWeightPair.
		 * 
		 */
		public Vertex getVertex() {
			return vertex;
		}

		/**
		 * Returns the weight in this VertexWeightPair.
		 * 
		 */
		public double getWeight() {
			return weight;
		}

		/**
		 * Set the weight in this VertexWeightPair.
		 * 
		 */
		public void setWeight(double w) {
			weight = w;
		}

		/**
		 * Returns an int <, = or > 0 , depending on whether this
		 * VertexWeightPair's weight is <, = or > other's weight.
		 * 
		 */
		public int compareTo(VertexWeightPair other) {
			return (int) (weight - other.getWeight());
		}

		/**
		 * Returns a String representation of this VertexWeightPair.
		 * 
		 */
		public String toString() {
			return vertex.toString() + "  " + String.valueOf(weight);
		}

	}

	protected class EdgeWeight implements Comparable<EdgeWeight> {
		Vertex from;
		Vertex to;
		double weight;

		/**
		 * Initializes this EdgeWeight from v1, v2 and weight.
		 */
		public EdgeWeight(Vertex from, Vertex to, double weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		/**
		 * Returns the "from" vertex in this EdgeWeight.
		 */
		public Vertex getFromVertex() {
			return from;
		}

		/**
		 * Returns the "to" vertex in this EdgeWeight.
		 */
		public Vertex getToVertex() {
			return to;
		}

		/**
		 * Returns the weight vertex in this EdgeWeight.
		 */
		public double getWeight() {
			return weight;
		}

		/**
		 * Returns an int <, = or > 0, depending on whether this EdgeWeight's
		 * weight is <, = or > edge's weight.
		 */
		public int compareTo(EdgeWeight edge) {
			return (int) (weight - edge.weight);
		}

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
		 */
		public String toString() {
			return "<" + from.toString() + ", " + to.toString() + "; "
					+ String.valueOf(weight) + ">";
		}

	}

	/**
	 * Desde el vertice start, utiliza un método recursivo para pasar a un ArrayList el resultado de 
	 * una búsqueda en profundidad.
	 * 
	 * @param start
	 * @return
	 */
	public ArrayList<Vertex> toArrayDFS(Vertex start) {
		result = new ArrayList<Vertex>();
		visited = new TreeMap<Vertex, Boolean>();
		Iterator<Vertex> itr = adjacencyMap.keySet().iterator();
		while (itr.hasNext())
			visited.put(itr.next(), false);
		toArrayDFSAux(start);
		return result;
	}
	
	/**
	 * Método recursivo para pasar a un ArrayList el resultado de 
	 * una búsqueda en profundidad.
	 * @param current
	 */
	public void toArrayDFSAux(Vertex current) {
		result.add(current);
		visited.put(current, true);
		TreeMap<Vertex, Double> neighborMap = adjacencyMap.get(current);
		for (Map.Entry<Vertex, Double> entry : neighborMap.entrySet()) {
			Vertex to = entry.getKey();
			if (!visited.get(to))
				toArrayDFSAux(to);
		}
	}

	/**
	 * Desde el vertice start, pasa a un ArrayList la iteración de una búsqueda
	 * en anchura del grafo
	 * 
	 * @param start
	 * @return
	 */
	public ArrayList<Vertex> toArrayBFS(Vertex start) {
		Iterator it = this.breadthFirstIterator(start);
		ArrayList<Vertex> toReturn = new ArrayList<Vertex>();
		while (it.hasNext())
			toReturn.add((Vertex) it.next());
		return toReturn;
	}

	/**
	 * Utiliza el algoritmo de Dijkstra para devolver el camino mas corto de
	 * source a destination
	 * 
	 * @param source
	 * @param destination
	 * @return
	 */
	public ArrayList<Object> Dijkstra(Vertex source, Vertex destination) {
		TreeMap<Vertex, Double> D = new TreeMap<Vertex, Double>();
		TreeMap<Vertex, Vertex> S = new TreeMap<Vertex, Vertex>();
		TreeSet<Vertex> V_minus_S = new TreeSet<Vertex>();
		Vertex vertex, to = null, from;
		if (source == null || destination == null)
			return new ArrayList<Object>();
		if (source.equals(destination))
			return new ArrayList<Object>();
		if (!(adjacencyMap.containsKey(source) && adjacencyMap
				.containsKey(destination)))
			return new ArrayList<Object>();
		for (Map.Entry<Vertex, TreeMap<Vertex, Double>> e : adjacencyMap
				.entrySet())
			if (!(source.equals(e.getKey())))
				V_minus_S.add(e.getKey());
		Iterator itr = V_minus_S.iterator();
		while (itr.hasNext()) {
			vertex = (Vertex) itr.next();
			if (isAdjacent(source, vertex)) {
				S.put(vertex, source);
				D.put(vertex, getWeight(source, vertex));
			} else {
				S.put(vertex, null);
				D.put(vertex, Double.POSITIVE_INFINITY);
			}
		}
		S.put(source, source);
		D.put(source, 0.0);
		while (!V_minus_S.isEmpty()) {
			Double minWeight = Double.POSITIVE_INFINITY;
			from = null;
			Iterator itr1 = V_minus_S.iterator();
			while (itr1.hasNext()) {
				vertex = (Vertex) itr1.next();
				if (D.get(vertex) < minWeight) {
					minWeight = D.get(vertex);
					from = vertex;
				}
			}
			if (!from.equals(null)) {
				V_minus_S.remove(from);
				Iterator itr2 = V_minus_S.iterator();
				while (itr2.hasNext()) {
					to = (Vertex) itr2.next();
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
		ArrayList<Object> path = new ArrayList<Object>();
		ALStack<Vertex> st = new ALStack<Vertex>();
		if (S.get(destination).equals(null)) {
			System.out.println("The vertex " + destination
					+ " is not reachable from " + source);
			return path;
		} else {
			st.push(destination);
			while (!(st.peek().equals(source)))
				st.push(S.get(st.peek()));
			while (!(st.isEmpty())) {
				path.add(st.peek());
				st.pop();
			}
		}
		ArrayList<Object> edgePath = new ArrayList<Object>();
		for (int i = 0; (i < (path.size() - 1)); i++) {
			EdgeWeight edgeWeight = new EdgeWeight((Vertex) path.get(i),
					(Vertex) path.get(i + 1), getWeight((Vertex) path.get(i),
							(Vertex) path.get(i + 1)));
			edgePath.add(edgeWeight);
		}
		return edgePath;
	}

	/**
	 * Determina el camino más corto de s a d pasando por t
	 * 
	 * @param s
	 * @param t
	 * @param d
	 * @return
	 */
	public String shortestPathSTD(Vertex s, Vertex t, Vertex d) {
		ArrayList path = new ArrayList<Object>();
		path.addAll(this.Dijkstra(s, t));
		path.addAll(this.Dijkstra(t, d));
		return stringPath(path);
	}

	/**
	 * Escribe el camino y devuelve un string
	 * 
	 * @param path
	 * @return
	 */
	private String stringPath(ArrayList<Object> path) {
		String toReturn = "";
		double distanceShortestPathAO = 0.0;
		for (int i = 0; i < path.size(); i++) {
			EdgeWeight e = (EdgeWeight) path.get(i);
			distanceShortestPathAO += e.getWeight();
			if (i < path.size() - 1)
				toReturn += e.from + " -> ";
			else {
				toReturn += e.from + " -> ";
				toReturn += e.to + " => ";
				toReturn += distanceShortestPathAO;
			}
		}

		return toReturn;
	}

	/**
	 * Genera un arbol con los caminos mínimo generados por el algoritmo de Dijkstra partiendo de source
	 * 
	 * @param source
	 * @return
	 */
	public ArrayList<Object> DijkstraTree(Vertex source) {
		TreeMap<Vertex, Double> D = new TreeMap<Vertex, Double>();
		TreeMap<Vertex, Vertex> S = new TreeMap<Vertex, Vertex>();
		TreeSet<Vertex> V_minus_S = new TreeSet<Vertex>();
		ArrayList<Object> toReturn = new ArrayList<Object>();
		Vertex vertex, to = null, from;

		if (source == null)
			return new ArrayList<Object>();
		if (!adjacencyMap.containsKey(source))
			return new ArrayList<Object>();
		for (Map.Entry<Vertex, TreeMap<Vertex, Double>> e : adjacencyMap
				.entrySet())
			if (!(source.equals(e.getKey())))
				V_minus_S.add(e.getKey());
		Iterator itr = V_minus_S.iterator();
		while (itr.hasNext()) {
			vertex = (Vertex) itr.next();
			if (isAdjacent(source, vertex)) {
				S.put(vertex, source);
				D.put(vertex, getWeight(source, vertex));
			} else {
				S.put(vertex, null);
				D.put(vertex, Double.POSITIVE_INFINITY);
			}
		}
		S.put(source, source);
		D.put(source, 0.0);
		while (!V_minus_S.isEmpty()) {
			Double minWeight = Double.POSITIVE_INFINITY;
			from = null;
			Iterator itr1 = V_minus_S.iterator();
			while (itr1.hasNext()) {
				vertex = (Vertex) itr1.next();
				if (D.get(vertex) < minWeight) {
					minWeight = D.get(vertex);
					from = vertex;
				}
			}

			toReturn.add(new EdgeWeight(S.get(from), from, getWeight(
					S.get(from), from)));

			if (!from.equals(null)) {
				V_minus_S.remove(from);
				Iterator itr2 = V_minus_S.iterator();
				while (itr2.hasNext()) {
					to = (Vertex) itr2.next();
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
		return toReturn;
	}

	/**
	 * Devuelve un Arraylist de ArrayList con los caminos desde f hasta todos
	 * los vertices posibles ordenado de mayor a menor coste
	 * 
	 * @param f
	 * @return
	 */
	public ArrayList<ArrayList<EdgeWeight>> DijkstraFarthest(Vertex source) {
		TreeMap<Vertex, Double> D = new TreeMap<Vertex, Double>();
		TreeMap<Vertex, Vertex> S = new TreeMap<Vertex, Vertex>();
		TreeSet<Vertex> V_minus_S = new TreeSet<Vertex>();
		ArrayList<ArrayList<EdgeWeight>> toReturn = new ArrayList<ArrayList<EdgeWeight>>();
		ArrayList<EdgeWeight> camino = new ArrayList<EdgeWeight>();
		ALStack<EdgeWeight> stackAux = new ALStack<EdgeWeight>();
		ALStack<ArrayList<EdgeWeight>> stack = new ALStack<ArrayList<EdgeWeight>>();
		Vertex vertex, to = null, from, fromAux, toAux;

		if (source == null)
			return new ArrayList<ArrayList<EdgeWeight>>();
		if (!adjacencyMap.containsKey(source))
			return new ArrayList<ArrayList<EdgeWeight>>();
		for (Map.Entry<Vertex, TreeMap<Vertex, Double>> e : adjacencyMap
				.entrySet())
			if (!(source.equals(e.getKey())))
				V_minus_S.add(e.getKey());
		Iterator itr = V_minus_S.iterator();
		while (itr.hasNext()) {
			vertex = (Vertex) itr.next();
			if (isAdjacent(source, vertex)) {
				S.put(vertex, source);
				D.put(vertex, getWeight(source, vertex));
			} else {
				S.put(vertex, null);
				D.put(vertex, Double.POSITIVE_INFINITY);
			}
		}
		S.put(source, source);
		D.put(source, 0.0);
		while (!V_minus_S.isEmpty()) {
			Double minWeight = Double.POSITIVE_INFINITY;
			from = null;
			Iterator itr1 = V_minus_S.iterator();
			while (itr1.hasNext()) {
				vertex = (Vertex) itr1.next();
				if (D.get(vertex) < minWeight) {
					minWeight = D.get(vertex);
					from = vertex;
				}
			}

			fromAux = from;
			camino = new ArrayList<EdgeWeight>();
			stackAux = new ALStack<EdgeWeight>();
			do {
				toAux = fromAux;
				fromAux = S.get(toAux);
				stackAux.push(new EdgeWeight(fromAux, toAux, getWeight(toAux,
						fromAux)));
			} while (!fromAux.equals(source));
			// Rellenamos el temporal con los datos de la cola
			while (!stackAux.isEmpty())
				camino.add(stackAux.pop());
			stack.push(camino);

			if (!from.equals(null)) {
				V_minus_S.remove(from);
				Iterator itr2 = V_minus_S.iterator();
				while (itr2.hasNext()) {
					to = (Vertex) itr2.next();
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

		while (!stack.isEmpty())
			toReturn.add(stack.pop());

		return toReturn;

	}

	/**
	 * Método que calcula todos los caminos posibles desde source a destination.
	 * 
	 * @param source
	 * @param destination
	 * @return
	 */
	public ArrayList<ArrayList<Vertex>> simplePaths(Vertex source,
			Vertex destination) {
		result = new ArrayList<Vertex>();
		resultSimplePaths = new ArrayList<ArrayList<Vertex>>();

		simplePathsAux(source, destination);

		return resultSimplePaths;

	}

	/**
	 * Método recursivo para calcular todos los caminos posibles desde source a
	 * destination.
	 * 
	 * @param current
	 * @param destination
	 */
	private void simplePathsAux(Vertex current, Vertex destination) {
		result.add(current);

		if (current.equals(destination)) {
			ArrayList<Vertex> resultAux = new ArrayList<Vertex>();
			for (int i = 0; i < result.size(); i++)
				resultAux.add(result.get(i));
			resultSimplePaths.add(resultAux);
		} else {
			TreeMap<Vertex, Double> neighborMap = adjacencyMap.get(current);
			for (Map.Entry<Vertex, Double> entry : neighborMap.entrySet()) {
				Vertex to = entry.getKey();
				if (!result.contains(to))
					simplePathsAux(to, destination);
			}
		}
		result.remove(result.size() - 1);
	}

	/**
	 * Devuelve el camino con mayor coste de los caminos simples.
	 * 
	 * @param source
	 * @param destination
	 * @return
	 */
	public ArrayList<EdgeWeight> largestPathWithSimplePaths(Vertex source,
			Vertex destination) {
		ArrayList<EdgeWeight> toReturn = new ArrayList<EdgeWeight>();
		ArrayList<EdgeWeight> toReturnAux = new ArrayList<EdgeWeight>();
		EdgeWeight ewAux;
		simplePathsAux(source, destination);
		double totalWeightAux = 0.0, weightAux, totalWeight = Double.MIN_VALUE;
		for (int i = 0; i < resultSimplePaths.size(); i++) {
			for (int j = 0; j < resultSimplePaths.get(i).size() - 1; j++) {
				weightAux = getWeight(resultSimplePaths.get(i).get(j),
						resultSimplePaths.get(i).get(j + 1));
				ewAux = new EdgeWeight(resultSimplePaths.get(i).get(j),
						resultSimplePaths.get(i).get(j + 1), weightAux);
				toReturnAux.add(ewAux);
				totalWeightAux += weightAux;
			}
			if (totalWeightAux > totalWeight) {
				totalWeight = totalWeightAux;
				toReturn = (ArrayList<EdgeWeight>) toReturnAux.clone();
			}
			totalWeightAux = 0.0;
			toReturnAux.clear();
		}
		return toReturn;
	}

	/**
	 * Devuelve el numero total de caminos simples entre todos los vertices por
	 * medio de Dijkstra
	 * 
	 * @return
	 */
	public int FloydWithDijkstra() {
		int count = 0;
		Iterator<Vertex> it = iterator();
		while (it.hasNext()) {
			count += (DijkstraTree(it.next())).size();
		}
		return count;
	}

	/**
	 * Devuelve true si el grafo es de Floyd
	 * 
	 * @return
	 */
	public boolean isFloydGraph() {
		Vertex current;
		Iterator<Vertex> it = iterator();
		while (it.hasNext()) {
			current = it.next();
			if (!getNeighbors(current).contains(current)
					|| getWeight(current, current) != 0.0)
				return false;
		}
		return true;
	}

	/**
	 * Transforma el grafo en un grafo de Floyd
	 */
	public void adaptToFloydGraph() {
		Vertex current;
		Iterator<Vertex> it = iterator();
		while (it.hasNext()) {
			current = it.next();
			addEdge(current, current, 0.0);
		}
	}

	/**
	 * Algoritmo de Floyd
	 * 
	 * @return
	 */
	public ArrayList<EdgeWeight> Floyd() {
		final double INFINITY = Double.MAX_VALUE;
		double[][] D;
		int[][] A;
		TreeMap<Vertex, Integer> vtxIndex = new TreeMap<Vertex, Integer>();
		Vertex vertex, from, to;
		EdgeWeight edgeWeight;
		double weight;
		int i, j, k;
		int n = numberOfVertices();
		if (n <= 0)
			return new ArrayList<EdgeWeight>();
		D = new double[n][n];
		A = new int[n][n];
		int index = 0;
		for (Map.Entry<Vertex, TreeMap<Vertex, Double>> e : adjacencyMap
				.entrySet())
			vtxIndex.put(e.getKey(), index++);
		for (i = 0; i < n; i++) {
			for (j = 0; j < n; j++) {
				D[i][j] = INFINITY;
				A[i][j] = -1;
			}
		}
		for (Map.Entry<Vertex, TreeMap<Vertex, Double>> e1 : adjacencyMap
				.entrySet()) {
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
		return showPaths(D, A, vtxIndex);
	}

	/**
	 * Crea los caminos mínimos del grafo generados por Floyd
	 * 
	 * @param D
	 * @param A
	 * @param vI
	 * @return
	 */
	private ArrayList<EdgeWeight> showPaths(double D[][], int A[][],
			TreeMap<Vertex, Integer> vI) {
		ArrayList<EdgeWeight> toReturn = new ArrayList<EdgeWeight>();
		final double INFINITY = Double.MAX_VALUE;
		int i, j;
		resultFloyd = new ArrayList<ArrayList<Vertex>>();

		for (i = 0; (i < numberOfVertices()); i++) {
			for (j = 0; (j < numberOfVertices()); j++) {
				if ((D[i][j] >= 0) && (D[i][j] < INFINITY)) {
					if (!(getVertexFromIndex(vI, i).equals(getVertexFromIndex(
							vI, j)))) {
						pathFloyd = new ArrayList<Vertex>();
						pathFloyd.add(getVertexFromIndex(vI, i));
						showPath(i, j, A, vI);
						pathFloyd.add(getVertexFromIndex(vI, j));
						toReturn.add(new EdgeWeight(getVertexFromIndex(vI, i),
								getVertexFromIndex(vI, j), D[i][j]));
						resultFloyd.add(pathFloyd);
					}
				}
			}
		}
		return toReturn;
	}

	/**
	 * Crea un camino mínimo generado por el algoritmo de de Floyd
	 * 
	 * @param i
	 * @param j
	 * @param A
	 * @param vI
	 */
	private void showPath(int i, int j, int A[][], TreeMap<Vertex, Integer> vI) {
		int k = A[i][j];
		if (k >= 0) {
			showPath(i, k, A, vI);
			pathFloyd.add(getVertexFromIndex(vI, k));
			showPath(k, j, A, vI);
		}
	}

	/**
	 * Devuelve un vertice de un TreeMap correspondiente a su valor index
	 * 
	 * @param vI
	 * @param index
	 * @return
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
	 * Devuelve los caminos de Floyd que hay entre d1 y d2
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public ArrayList<ArrayList<EdgeWeight>> FloydFilterByDistances(double d1,
			double d2) {
		ArrayList<ArrayList<EdgeWeight>> toReturn = new ArrayList<ArrayList<EdgeWeight>>();
		ArrayList<EdgeWeight> aux = new ArrayList<EdgeWeight>();
		double dA, dB;
		if (d1 >= d2)
			return toReturn;
		Floyd();
		for (int i = 0; i < resultFloyd.size() - 1; i++) {
			aux = new ArrayList<EdgeWeight>();
			dA = dB = 0.0;
			for (int j = 0; j < resultFloyd.get(i).size() - 1; j++) {
				result = resultFloyd.get(i);
				dB = getWeight(result.get(j), result.get(j + 1));
				aux.add(new EdgeWeight(result.get(j), result.get(j + 1), dB));
				dA += dB;
			}
			if (dA >= d1 && dA <= d2)
				toReturn.add(aux);
		}
		return toReturn;
	}

	/**
	 * Devuelve el camino máximo y el mínimo entre dos puntos
	 * 
	 * @return
	 */
	public ArrayList<ArrayList<EdgeWeight>> FloydClosestFarthest() {
		ArrayList<ArrayList<EdgeWeight>> toReturn = new ArrayList<ArrayList<EdgeWeight>>();
		ArrayList<EdgeWeight> aux = new ArrayList<EdgeWeight>();
		shortestPathWeight = Double.MAX_VALUE;
		largestPathWeight = Double.MIN_VALUE;
		double d;

		Floyd();
		for (int i = 0; i < resultFloyd.size() - 1; i++) {
			d = 0.0;
			result = resultFloyd.get(i);
			for (int j = 0; j < result.size() - 1; j++)
				d += getWeight(result.get(j), result.get(j + 1));
			if (d < shortestPathWeight) {
				resultShortestPath = new ArrayList<Vertex>();
				for (Vertex _vertice : result) {
					resultShortestPath.add(_vertice);
					shortestPathWeight = d;
				}
			}
			if (d > largestPathWeight) {
				resultLargestPath = new ArrayList<Vertex>();
				for (Vertex _vertice : result) {
					resultLargestPath.add(_vertice);
					largestPathWeight = d;
				}
			}
		}
		for (int i = 0; i < resultShortestPath.size() - 1; i++)
			aux.add(new EdgeWeight(resultShortestPath.get(i),
					resultShortestPath.get(i + 1), getWeight(
							resultShortestPath.get(i),
							resultShortestPath.get(i + 1))));
		toReturn.add(aux);
		aux = new ArrayList<EdgeWeight>();
		for (int i = 0; i < resultLargestPath.size() - 1; i++)
			aux.add(new EdgeWeight(resultLargestPath.get(i), resultLargestPath
					.get(i + 1), getWeight(resultLargestPath.get(i),
					resultLargestPath.get(i + 1))));
		toReturn.add(aux);

		return toReturn;
	}

	/**
	 * Devuelve todos los caminos que empiezan poe source
	 * 
	 * @param source
	 * @return
	 */
	public ArrayList FloydFilterByNameOfCity(Vertex source) {
		ArrayList<ArrayList<EdgeWeight>> toReturn = new ArrayList<ArrayList<EdgeWeight>>();
		ArrayList<EdgeWeight> aux;

		Floyd();
		for (int i = 0; i < resultFloyd.size() - 1; i++) {
			result = resultFloyd.get(i);
			if (result.get(0).equals(source)) {
				aux = new ArrayList<EdgeWeight>();
				for (int j = 0; j < result.size() - 1; j++)
					aux.add(new EdgeWeight(result.get(j), result.get(j + 1),
							getWeight(result.get(j), result.get(j + 1))));
				toReturn.add(aux);
			}
		}
		return toReturn;
	}

	/**
	 * Algoritmo de Prim
	 * @param source
	 * @return
	 */
	public ArrayList<Object> Prim(Vertex source) {
		final double INFINITY = Double.MAX_VALUE;
		TreeMap<Vertex, Double> D = new TreeMap<Vertex, Double>();
		TreeMap<Vertex, Vertex> S = new TreeMap<Vertex, Vertex>();
		TreeSet<Vertex> V_minus_S = new TreeSet<Vertex>();
		Vertex vertex, to = null, from;
		ArrayList<Object> MST = new ArrayList<Object>();
		if (source == null)
			return new ArrayList<Object>();
		if (!(adjacencyMap.containsKey(source)))
			return new ArrayList<Object>();
		for (Map.Entry<Vertex, TreeMap<Vertex, Double>> e : adjacencyMap
				.entrySet()) {
			if (!(source.equals(e.getKey())))
				V_minus_S.add(e.getKey());
		}
		Iterator itr = V_minus_S.iterator();
		while (itr.hasNext()) {
			vertex = (Vertex) itr.next();
			if (isAdjacent(source, vertex)) {
				S.put(vertex, source);
				D.put(vertex, getWeight(source, vertex));
			} else {
				S.put(vertex, null);
				D.put(vertex, INFINITY);
			}
		}
		S.put(source, source);
		D.put(source, 0.0);
		while (!V_minus_S.isEmpty()) {
			Double minWeight = INFINITY;
			from = null;
			Iterator itr1 = V_minus_S.iterator();
			while (itr1.hasNext()) {
				vertex = (Vertex) itr1.next();
				if (D.get(vertex) < minWeight) {
					minWeight = D.get(vertex);
					from = vertex;
				}
			}
			if (from != null) {
				V_minus_S.remove(from);
				EdgeWeight edgeWeight = new EdgeWeight(S.get(from), from,
						getWeight(S.get(from), from));
				MST.add(edgeWeight);
				Iterator itr2 = V_minus_S.iterator();
				while (itr2.hasNext()) {
					to = (Vertex) itr2.next();
					if (isAdjacent(from, to)) {
						double weight = getWeight(from, to);
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

	public TreeMap<Double, ArrayList<Vertex>> intervalPaths(Vertex source,
			Vertex destination, double d1, double d2) {
		TreeMap<Double, ArrayList<Vertex>> toReturn = new TreeMap<Double, ArrayList<Vertex>>();
		simplePaths(source, destination);
		for(int i = 0; i < this.resultSimplePaths.size(); i++){
			double aux = 0.0;
			for(int j = 0; j < this.resultSimplePaths.get(i).size() - 1; j++){
				aux += this.getWeight(resultSimplePaths.get(i).get(j), resultSimplePaths.get(i).get(j+1)); 
			}
			if(d1 < aux && aux < d2) toReturn.put(aux, this.resultSimplePaths.get(i));
		}
		return toReturn;
	}

}
