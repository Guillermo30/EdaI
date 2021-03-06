package org.eda1.actividad05;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
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
    	//Actividad ///////////////////////
    	//////////////
    	/**
    	 * Cosnulta el grado de entrada de un vertice dado.
    	 *@param v vertice dado
    	 *@return
    	 */
    	public int inDegree(Vertex v){
    		
    		int contador=0;
    		Iterator<Entry<Vertex,TreeMap<Vertex,Double>>> iterador = this.adjacencyMap.entrySet().iterator();
    		
    		while(iterador.hasNext()){
    			
    			Vertex verticeIterador = iterador.next().getKey();
    			
    			if(!v.equals(verticeIterador)){
    				
    				if(this.adjacencyMap.get(verticeIterador).containsKey(v)){
    					
    					contador++;
    					
    				}
    				
    			}   
    		}
    		return contador;
    	}
    	/**
    	 * Cosnulta el grado de salida du un vertice dado.
    	 *@param v vertice dado
    	 *@return
    	 */
    	public int outDegree(Vertex v){
    		
    		TreeMap<Vertex, Double> nuevo = this.adjacencyMap.get(v);
    		
    		return nuevo.size();
    	}
    	
    	/**
    	 * Comprueva si  es alcanzable desde otro vertice dado.
    	 *@param v vertice dado
    	 *@return
    	 */
    	public boolean isReachable(Vertex source, Vertex destination) {
    		ArrayList<String> resultado = new ArrayList<String>();
      		ALStack<Vertex> pila=new ALStack<Vertex>();
      		Vertex actual;
      		
      		resultado.add(source.toString());  		
      		for(Vertex _vecino: getNeighbors(source))
      			pila.push(_vecino);
      		
      		while(!pila.isEmpty()){
      			actual=pila.pop();
      			if(actual.equals(destination))
      				return true; 				
      			if(!resultado.contains(actual.toString()))
      				resultado.add(actual.toString());
      			for(Vertex _vertice: getNeighbors(actual))
      				if(!resultado.contains(_vertice.toString()))
      					pila.push(_vertice);	        	  
      		}
      		
      		return false;
    	}
    	
    	/*private boolean isReachableAux(Vertex current, Vertex destination) {
    		
    		this.visited  = new TreeMap<Vertex,Boolean>();
    		
    		if(current.equals(destination)){
    			return true;
    		}
    		
    		this.visited.put(current,true);
    		
    		Iterator<Entry<Vertex, Double>> iterador = this.adjacencyMap.get(current).entrySet().iterator();
    		
    		while(iterador.hasNext()){
    			
    			Vertex keyIt = iterador.next().getKey();
    			
    			if(!this.visited.containsKey(keyIt)){
    				
    				if(isReachableAux(keyIt,destination)){
    					
    					return true;
    					
    				}
    				
    			}
    		
    		}
    		return false;
    	}
    	
    	/**
    	 * Leer archivo de texto y lo combierte en una estructura graph.
    	 *@param  file name nombre del archivo.
    	 *@return
    	 */
    	public static Network<String> readNetwork(String filename) throws FileNotFoundException {
    		
    		int tipoGraf;
    		int i, numeroVertices, numeroAristas;
    		String v1, v2;
    		double peso;
    		Network<String> net = new Network<String>();
    		
            try {
            	
            	BufferedReader br = new BufferedReader(new FileReader(filename));
            	String linea = br.readLine();
            	
            	if(linea=="0"){
            		net.setDirected(false);
            	}else{
            		net.setDirected(true);
            	}
            	
            	int numVertices = Integer.parseInt(br.readLine());
            	
            	for(int j=0; j<numVertices; j++){
            		
            		linea = br.readLine();
            		net.addVertex(linea);
            	}
            	
            	int numAristas = Integer.parseInt(br.readLine());
            	
            	for(int k=0; k<numAristas; k++){
            		
            		linea = br.readLine();
            		String s1 = linea.split("  ")[0];
            		String s2 = linea.split("  ")[1];
            		String s3 = linea.split("  ")[2];
            		net.addEdge(s1, s2, Integer.parseInt(s3));
            	}
            	
    			br.close();
    		} catch (IOException e) {
    			
    			throw new FileNotFoundException();
    		}

    		return net;
    	}
    	/**
    	 * Comprueva si  el vertice dados es fuente es decir, si es un v�rtice del que s�lo salen aristas. No se puede hacer uso de la funci�n inDegree, ya que un v�rtice fuente es aqu�l con grado de entrada igual a cero.
    	 *@param v vertice dado
    	 *@return
    	 */
    	public boolean isSource(Vertex v){
    		
    		Iterator<Entry<Vertex,TreeMap<Vertex, Double>>> iterador = this.adjacencyMap.entrySet().iterator();
    		
    		while(iterador.hasNext()){
    			
    			Vertex key = iterador.next().getKey();
    			
    			if(!key.equals(v)){
    				
    				if(this.adjacencyMap.get(key).containsKey(v)){
    					
    					return false;
    				}
    			}
    		}
    		return true;
    	}
    	
    	/**
    	 *Comprobar si un v�rtice es un sumidero (es decir, un v�rtice al que s�lo llegan aristas) al que llegan aristas de todos los dem�s v�rtices del grafo. No se puede hacer uso de la funci�n outDegree, ya que un v�rtice sumidero es aqu�l con grado de salida igual a cero. 
    	 *@param v vertice dado
    	 *@return boolean false si el vertice es sumidero false si no.
    	 */
    	public boolean isSink(Vertex v) {
    		
    		if(this.adjacencyMap.get(v).size()==0){
    			
    			return true;
    			
    		}else{
    			return false;
    		}
    		
    	}
    	
    	/**
    	 *Muestra todos los posibles caminos simples de un v�rtice a otro. Recuerde que un camino simple es aquel en el que todos los v�rtices son distintos (excepto el primero y el �ltimo que pueden ser iguales).
    	 *@param source vertice origen
    	 *@param destination vertice destino
    	 *@return lista de todos los posibles caminos
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
    	private void simplePathsAux(Vertex current, Vertex destination) { 
    		
    		result.add(current);
    		if(current.equals(destination)){
    			
    			ArrayList<Vertex> resultNuevo = new ArrayList<Vertex>();
    			
    			for(int i = 0;i<this.result.size();i++){
    				
    				resultNuevo.add(this.result.get(i));
    				
    			}
    			
    			this.resultSimplePaths.add(resultNuevo);
    			
    		}else{
    			
    			TreeMap<Vertex,Double> vecino = this.adjacencyMap.get(current);
    			
    			for(Map.Entry<Vertex, Double> entrada: vecino.entrySet()){
    				
    				Vertex otro = entrada.getKey();
    				
    				if(!result.contains(otro)){
    					
    					simplePathsAux(otro,destination);
    				}
    			}
    		}
    		
    		result.remove(result.size()-1);
    		
    	}
    	
    	/**
    	 *Obtiene en un ArrayList de v�rtices, los v�rtices que se visitan siguiendo un recorrido en profundidad (Depth-First traversal) recursivo.
    	 *@param v vertice de comienzo.
    	 *@return result ArrayList con el camino recorrido. 
    	 */
    	public ArrayList<Vertex> toArrayDFS(Vertex start){
    		
    		this.result =  new ArrayList<Vertex>();
    		this.result.add(start);
    		Iterator<Entry<Vertex, Double>> iterador = this.adjacencyMap.get(start).entrySet().iterator();
    		
    		while(iterador.hasNext()){
    			
    			Vertex key = iterador.next().getKey();
    			
    			if(!this.result.contains(key)){
    				
    				this.result.add(key);
    				toArrayDFSAux(key);
    				
    			}
    		}
    		return this.result;
    		
    	}
    	
    	private void toArrayDFSAux(Vertex current){
    		
    		Iterator<Entry<Vertex, Double>> iterador = this.adjacencyMap.get(current).entrySet().iterator();
    		
    		while(iterador.hasNext()){
    			Vertex key = iterador.next().getKey();
    			
    			if(!this.result.contains(key)){
    				
    				this.result.add(key);
    				toArrayDFSAux(key);
    			}
    		}
    		
    	}
    	
    	/**
    	 *Obtiene en un ArrayList de v�rtices, los v�rtices que se visitan siguiendo un recorrido en profundidad Depth-First traversal iterativo (sin utilizar la recursividad).
    	 *@param v vertice dado
    	 *@return result ArrayList con el camino recorrido.
    	 */
    	public ArrayList<Vertex> toArrayDFSIterative(Vertex start) {
    		
    		Stack<Vertex> stack = new Stack<Vertex>();
    		this.result.clear();
    		stack.push(start);
    		
    		while(!stack.isEmpty()){
    			
    			Vertex current = stack.pop();
    			
    			if(!this.result.contains(current)){
    				
    				this.result.add(current);
    				Iterator<Entry<Vertex, Double>> iterador = this.adjacencyMap.get(current).entrySet().iterator();
    				
    				while(iterador.hasNext()){
    					
    					Vertex key = iterador.next().getKey();
    					stack.push(key);
    				}
    			}
    		}
    		return this.result;
    		
    	}
    	
    	/**
    	 *Funci�n para obtener en un array de v�rtices, los v�rtices que se visitan siguiendo un recorrido en anchura o amplitud (Breadth-First traversal).
    	 *@param start Vertex vertice donde inicial.
    	 *@return result ArrayList con el camino recorrido.
    	 */
    	public ArrayList<Vertex> toArrayBFS(Vertex start) { 
    		
    		this.result.clear();
    		LinkedQueue<Vertex> queue = new LinkedQueue<Vertex>();
    		queue.push(start);
    		
    		while(!queue.isEmpty()){
    			
    			Vertex current = queue.pop();
    			
    			if(!this.result.contains(current)){
    				
    				this.result.add(current);
    				Iterator<Entry<Vertex, Double>> ite = this.adjacencyMap.get(current).entrySet().iterator();
    				
    				while(ite.hasNext()){
    					
    					Vertex key = ite.next().getKey();
    					queue.push(key);
    				}
    			}
    		}
    		return this.result;
    		
    	}
    	
    	/**
    	 *Funci�n que devuelva el camino m�s corto (si hay m�s de uno, que devuelva uno de ellos) seg�n el peso de las aristas entre un v�rtice source y otro destination haciendo uso del algoritmo de caminos simples. Adem�s debe ser posible calcular f�cilmente su coste total.
    	 *@param source vertice dado.
    	 *@param vertice de destino.
    	 *@return String
    	 */
    	public String shortestPathWithSimplePaths(Vertex source, Vertex destination) { 
    	
    		String resultado = "The shortest path using the simple paths algorithm from "+source+" to "+destination+" is:\n";
    		shortestPathWeight = 999999999.9;
    		this.result = new ArrayList<Vertex>();
    		this.resultShortestPath = new ArrayList<Vertex>();
    		shortestPathWithSimplePathsAux(source, destination);
    		Iterator<Vertex> ite = resultShortestPath.iterator();
    		while(ite.hasNext()){
    			resultado += ite.next();
    			if(ite.hasNext())
    				resultado += " --> ";
    			else
    				resultado += " => (";
    		}
    		return resultado+shortestPathWeight+")";
    		
    	}
    	private void shortestPathWithSimplePathsAux(Vertex current, Vertex destination) { 
    		
    		result.add(current);
    		if(current.equals(destination)){
    			double aux = 0.0;
    			Iterator<Vertex> ite = this.result.iterator();
    			Vertex ot = ite.next();
    			Vertex to;
    			while(ite.hasNext()){
    				to = ite.next();
    				aux += this.adjacencyMap.get(ot).get(to);
    				ot = to;
    			}
    			if(aux<shortestPathWeight){
    				shortestPathWeight = aux;
    				resultShortestPath = (ArrayList<Vertex>) result.clone();
    			}
    		}else{
    			Iterator<Entry<Vertex, Double>> iteAdj = this.adjacencyMap.get(current).entrySet().iterator();
    			while(iteAdj.hasNext()){
    				Vertex to = iteAdj.next().getKey();
    				if(!result.contains(to))
    					shortestPathWithSimplePathsAux(to, destination);
    			}
    		}
    		result.remove(result.size()-1);
    		
    	}
    	
    	/**
    	 *Implementar una funci�n que devuelva el camino con mayor longitud (si hay m�s de uno, que devuelvauno de ellos) entre un v�rtice source a un v�rtice destination, y su correspondiente longitud de camino (recordad que la longitud de un camino es su n�mero de v�rtices menos uno o equivalentemente el n�mero de aristas). Adem�s, y al final, debe devolver la longitud media de los caminos que van desde dicho v�rtice source hasta el v�rtice destination.
    	 *@param v vertice dado
    	 *@return
    	 */
    	public String largestLenghtPathWithSimplePaths(Vertex source, Vertex destination) {
    		
    		String resultado = "The largest length path using the simple paths algorithm from "+source+" to "+destination+" is:\n";
    		largestLengthPath = 0;
    		sumOfLengthOfSimplePaths = 0;
    		numberOfSimplePaths = 0;
    		this.result = new ArrayList<Vertex>();
    		resultLargestLengthPath = new ArrayList<Vertex>();
    		largestLenghtPathWithSimplePathsAux(source, destination);
    		Iterator<Vertex> ite = resultLargestLengthPath.iterator();
    		while(ite.hasNext()){
    			resultado += ite.next();
    			if(ite.hasNext())
    				resultado += " --> ";
    			else
    				resultado += " => (";
    		}
    		resultado += largestLengthPath-1+")\nThe average length of the simple paths from "+source+" to "+destination+" is: ";
    		double aux1 = (double)sumOfLengthOfSimplePaths;
    		double aux2 = (double)numberOfSimplePaths;
    		double aux = aux1/aux2-1;
    		DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
    		simbolos.setDecimalSeparator('.');
    		DecimalFormat df = new DecimalFormat("#0.00", simbolos);
    		return resultado+df.format(aux);
    		
    	}
    	private void largestLenghtPathWithSimplePathsAux(Vertex current, Vertex destination) {
    		
    		result.add(current);
    		if(current.equals(destination)){
    			int aux = this.result.size();
    			sumOfLengthOfSimplePaths += aux;
    			numberOfSimplePaths ++;
    			if(aux>largestLengthPath){
    				largestLengthPath = aux;
    				resultLargestLengthPath = (ArrayList<Vertex>) result.clone();
    			}
    		}else{
    			Iterator<Entry<Vertex, Double>> iteAdj = this.adjacencyMap.get(current).entrySet().iterator();
    			while(iteAdj.hasNext()){
    				Vertex to = iteAdj.next().getKey();
    				if(!result.contains(to))
    					largestLenghtPathWithSimplePathsAux(to, destination);
    			}
    		}
    		result.remove(result.size()-1);
    		
    	}
    	
    	/**
    	 *Implementar una funci�n que devuelva true en el caso de que exista un camino entre el v�rtice source y el destination y tiene una longitud de length, false en cualquier otro caso. Recordad que la longitud de un camino es su n�mero de v�rtices menos uno o equivalentemente el n�mero de aristas.
    	 *@param v vertice dado
    	 *@return
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
    	 * Este metodo comprueba si el grafo es fuertemente conexo
    	 * @return true en caso de que el grafo sea fuertemente conexo y false en caso contrario
    	 */
    	public boolean isStronglyConnected() {
    		
    		if (!directed)
    			return false; 
    			Network<Vertex> transposeGraph = new Network(directed);
    			for (Map.Entry<Vertex, TreeMap<Vertex, Double>> e1 : adjacencyMap.entrySet()) {
    				TreeMap<Vertex, Double> neighborMap = e1.getValue();
    				for (Map.Entry<Vertex, Double> e2 : neighborMap.entrySet()) {
    					transposeGraph.addEdge(e2.getKey(), e1.getKey(), e2.getValue());
    				}
    			}
    			for (Vertex v : adjacencyMap.keySet()) {
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
    	 *Metodo para determinar si un grafo no orientado es un �rbol libre (tree). Un grafo no orientado es un �rbol libre si es conexo y ac�clico (sin ciclos), o equivalentemente, si es conexo y tiene exactamente n � 1 aristas para n nodos.
    	 *@return Boolean true si es arbol libre y false si no.
    	 */
    	public boolean isTree() { 
    		
    		if(isConnected()){
    			
    			if(numberOfEdges()/2 == numberOfVertices()-1){
    				
    				return true;
    			}	
    			
    		}
    		
    		return false;
    		
    	}
    	
    	/**
    	 *Metodo para determinar si un grafo es conexo o no.
    	 *@return Boolean true si es arbol un arbol conexo y false si no lo es.
    	 */
    	public boolean isConnected() {
    		
    		if(this.directed){
    			return false;
    		}
    		
    		for(Vertex vertice : adjacencyMap.keySet()){
    		
    			Iterator<Vertex> iterador = new BreadthFirstIterator(vertice);
    			int count = 0;
    			
    			while(iterador.hasNext()){
    				
    				iterador.next();
    				count ++;
    			}
    			
    			if(count < adjacencyMap.size()){
    				
    				return false;
    			}	
    		}
    		
    		return true;
    	}
    	
    	
    	/**
    	 *Metodo para generar una lista de nodos con el camino mas corto entre dos nodos.
    	 *@param source Vertex vertice de origen.
    	 *@param destination Vertex vertice de destino
    	 *@return ArrayList con los pesos del camino mas corto.
    	 */
    	public ArrayList<Object> Dijkstra(Vertex source, Vertex destination){
    		
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
    	 *Metodo para generar una lista de nodos con el camino mas corto entre dos nodos.
    	 *@param source Vertex vertice de origen.
    	 *@param destination Vertex vertice de destino
    	 *@return ArrayList con los pesos del camino mas corto.
    	 */
    	public ArrayList DijkstraPQ(Vertex source, Vertex destination) {
    		
    		TreeMap<Vertex, Double> D = new TreeMap<Vertex, Double>();
    		TreeMap<Vertex, Vertex> S = new TreeMap<Vertex, Vertex>();
    		LinkedQueue<Vertex> V_minus_S = new LinkedQueue<Vertex>();
    		Vertex vertex, to = null, from;
    		
    		for(Map.Entry<Vertex, TreeMap<Vertex, Double>> e: adjacencyMap.entrySet()){
    			
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
    		
    	private boolean isAdjacent(Vertex source, Vertex destination) {
    		
    		if(this.adjacencyMap.get(source).containsKey(destination)){
    			
    			return true;
    			
    		}else{	
    			
    			return false;
    		}
    	}
    	/**
    	 *Hemos estudiado tanto teor�a como en pr�cticas el algoritmo
    	 *de Floyd para obtener el camino m�nimo entre todos los posibles
    	 *pares de v�rtices del grafo (public void Floyd()). 
    	 *En este contexto, sea G un grafo orientado y valorado, 
    	 *y vi un v�rtice de G. Se define la excentricidad del v�rtice 
    	 *vi respecto del grafo G como el m�ximo de los costes de los
    	 *caminos m�nimos (matriz D del algoritmo de Floyd) que van
    	 *desde cualquier otro v�rtice vj del grafo al v�rtice vi.
    	 *Tambi�n se define el centro del grafo como el v�rtice que 
    	 *tiene m�nima excentricidad. Se pide implementar en Java
    	 *(public String FloydEC()) una funci�n que calcule la
    	 *excentricidad de cada uno de los v�rtices vi de G y 
    	 *el centro del grafo junto con el coste del camino m�nimo
    	 *de cada uno de los v�rtices de G accesibles desde dicho 
    	 *centro. Debe ayudarse de la funci�n public void Floyd() 
    	 *estudiada en clase.
    	 *@param v vertice dado
    	
    	 *@return
    	 */
    	public String FloydEC(){
    		
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
    	
    	private void showPath(int i, int j, int[][] A, TreeMap<Vertex, Integer> vI){
      		int k = A[i][j];
      		if(k>=0){
      			showPath(i, k, A, vI);
      			System.out.println(getVertexFromIndex(vI, k)+",");
      			showPath(k, j, A, vI);
      		}
      	}
    	
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
    	
    	/**
    	 * Transforma un grafo cualquiera en grafo de Floyd.
    	 */
    	public void adaptToFloydGraph() {
    		
    		for(Map.Entry<Vertex, TreeMap<Vertex, Double>> key : adjacencyMap.entrySet()){
    			
    			Vertex v = key.getKey();
    			TreeMap<Vertex, Double> neighborMap = key.getValue();
    			
    			if(neighborMap == null){
    				
    				adjacencyMap.put(v, new TreeMap<Vertex, Double>());
    				adjacencyMap.get(v).put(v, new Double(0.0));
    				
    			}else{
    				
    				addEdge(v, v, 0.0);
    			}
    		}
    	}
    	
    	/**
    	 * Comprueba si es un grafo de Floid.
    	 * @return boolean true si es de floid
    	 */
    	public boolean isFloydGraph() {
    		
    		Vertex actual;
    		
    		for (Iterator<Vertex> iterador = iterator(); iterador.hasNext();) {
    			
    			actual = iterador.next();
    			
    			if (!getNeighbors(actual).contains(actual)|| getWeight(actual, actual) != 0.0){
    				return false;
    			}
    		}
    		return true;
    	}

    	
    	//Clase EdgeWeight
    	public class EdgeWeight implements Comparable<EdgeWeight>{
    		
    		Vertex from;
    		Vertex to;
    		double weight;
    		
    		public EdgeWeight(Vertex from, Vertex to, double weight){
    			
    			this.from = from;
    			this.to = to;
    			this.weight = weight;
    			
    		}
    		
    		public Vertex getFromVertex(){
    			
    			return this.from;
    		}
    		
    		public Vertex getToVertex(){
    			
    			return this.to;
    		}
    		
    		public double getWeight(){
    			
    			return this.weight;
    		}
    		
    		public int compareTo(EdgeWeight edge){
    			
    			return (int)(this.weight-edge.getWeight());
    		}
    		
    		public boolean equals(Object obj){
    			
    			EdgeWeight edge = (EdgeWeight) obj;
    			
    			if(this.weight!=edge.getWeight()){
    				
    				return false;
    			}
    			
    			if(!this.from.equals(edge.getFromVertex())){
    				
    				return false;
    			}
    			
    			if(!this.to.equals(edge.getToVertex())){
    				return false;
    			}
    			
    			return true;
    		}
    		
    		public String toString(){
    			
    			return this.from+" -> "+this.to+" => "+this.weight;
    		}
    	}

	}// class Network
