/*
 * @(#)BSTree.java
 */






package org.eda1.examenParcialGrupoB.ejercicio02;


import java.util.ArrayList;
import java.util.Collections;
import java.util.NoSuchElementException;
import java.lang.IllegalStateException;
import java.util.ConcurrentModificationException;

import org.eda1.estructurasdedatos.ALStack;
import org.eda1.estructurasdedatos.BinarySearchTree;
import org.eda1.estructurasdedatos.Collection;
import org.eda1.estructurasdedatos.Iterator;
import org.eda1.utilidades.Less;



/**
 * This class implements the Collection interface using a binary search
 * tree as the underlying storage structure.
 */
@SuppressWarnings("unchecked")
public class BSTree<T> implements Collection<T>, Iterable<T>, Cloneable, BinarySearchTree<T> {
	// reference to tree root
	//transient
	private BSTNode<T> root;

	// number of elements in the tree
	private int treeSize;

	// increases whenever the tree changes. used by an iterator
	// to verify that it is in a consistent state
	transient private int modCount;
	

	// private method used by remove() and the iterator
	// remove() to delete a node
	private void removeNode(BSTNode<T> dNode)
	{
	   if (dNode == null)
	      return;

	   // dNode = reference to node D that is deleted
	   // pNode = reference to parent P of node D
	   // rNode = reference to node R that replaces D
	   BSTNode<T> pNode, rNode;

	   // assign pNode as a reference to P
	   pNode = dNode.parent;

	   // if D has a null child, the
	   // replacement node is the other child
	   if (dNode.left == null || dNode.right == null)
	   {
	      if (dNode.right == null)
	         rNode = dNode.left;
	      else
	         rNode = dNode.right;

	      if (rNode != null)
	         // the parent of R is now the parent of D
	         rNode.parent = pNode;

			// complete the link to the parent node.

			// deleting the root node. assign new root
			if (pNode == null)
				root = rNode;
			// attach R to the correct branch of P
			else if (((Comparable<T>)dNode.nodeValue).
					compareTo(pNode.nodeValue) < 0)
				pNode.left = rNode;
			else
				pNode.right = rNode;
	   }
	   // both children of dNode are non-null.
	   else
	   {
	      // find and unlink replacement node for D.
	      // starting at the right child of node D,
	      // find the node whose value is the smallest of all
	      // nodes whose values are greater than the value in D.
	      // unlink the node from the tree.

	      // pOfRNode is reference to parent of replacement node
	      BSTNode<T> pOfRNode = dNode;

			// first possible replacement is right child of D
			rNode = dNode.right;

			// descend down left subtree of the right child of D,
			// keeping a record of current node and its parent.
			// when we stop, we have found the replacement
			while(rNode.left != null)
			{
				pOfRNode = rNode;
				rNode = rNode.left;
			}

			// copy the value in R to D
			dNode.nodeValue = rNode.nodeValue;

			if (pOfRNode == dNode)
				dNode.right = rNode.right;
			else
				pOfRNode.left = rNode.right;

			// the parent of the right child of R is the
			// parent of R
			if (rNode.right != null)
				rNode.right.parent = pOfRNode;


			// we want to dispose of rNode
			dNode = rNode;
		}

		// make the reference to the deleted node null
		dNode = null;
	}

	// iteratively traverse a path from the root to the node
	// whose value is item; return a reference to the node
	// containing item or null if the search fails
	private BSTNode<T> findNode(Object item)
	{
	   // t is current node in traversal
	   BSTNode<T> t = root;
	   int orderValue;

	   // terminate on on empty subtree
	   while(t != null)
	   {
	      // compare item and the current node value
	      orderValue = ((Comparable<T>)item).compareTo(t.nodeValue);

	      // if a match occurs, return true; otherwise, go left
	      // or go right following search tree order
	      if (orderValue == 0)
	         return t;
	      else if (orderValue < 0)
	         t = t.left;
	      else
	         t = t.right;
	   }

	   return null;
	}

    /**
     * Creates an empty binary search tree. All elements inserted into the
     * tree must implement the <tt>Comparable</tt> interface.
     */
	public BSTree()
	{
	   root = null;
	   modCount = 0;
	   treeSize = 0;
	}

    /* (non-Javadoc)
	 * @see org.eda.estructurasdatos.Tree#add(T)
	 */
	@Override
	public boolean add(T item)
	{
		// t is current node in traversal, parent the previous node
		BSTNode<T> t = root, parent = null, newNode;
		int orderValue = 0;

		// terminate on on empty subtree
		while(t != null)
		{
			// update the parent reference.
			parent = t;

			// compare item and the current node value
			orderValue = ((Comparable<T>)item).compareTo(t.nodeValue);

			// if a match occurs, return false; otherwise, go left
			// or go right following search tree order
			if (orderValue == 0)
				return false;
			else if (orderValue < 0)
				t = t.left;
			else
				t = t.right;
		}

		// create the new node
		newNode = new BSTNode<T>(item,parent);

		if (parent == null)
			// this is the first node added. make it root
			root = newNode;
		else if (orderValue < 0)
			// attach newNode as the left child of parent
			parent.left = newNode;
		else
			// attach newNode as the right child of parent
			parent.right = newNode;

		// increment the tree size and modCount
		treeSize++;
		modCount++;

		// we added a node to the tree
		return true;
	}

    /* (non-Javadoc)
	 * @see org.eda.estructurasdatos.Tree#clear()
	 */
	@Override
	public void clear()
   {
	  modCount++;
      treeSize = 0;
      root = null;
   }

    /* (non-Javadoc)
	 * @see org.eda.estructurasdatos.Tree#contains(java.lang.Object)
	 */
   @Override
public boolean contains(Object item)
   {
      BSTNode<T> t = findNode(item);
      return (t == null) ? false : true;
   }

     /* (non-Javadoc)
	 * @see org.eda.estructurasdatos.Tree#isEmpty()
	 */
   @Override
public boolean isEmpty()
   {
      return treeSize == 0;
   }

    /* (non-Javadoc)
	 * @see org.eda.estructurasdatos.Tree#iterator()
	 */
   @Override
public Iterator<T> iterator()
   {
      return new TreeIterator();
   }

    /* (non-Javadoc)
	 * @see org.eda.estructurasdatos.Tree#remove(java.lang.Object)
	 */
	@Override
	public boolean remove(Object item)
	{
		// search tree for item
		BSTNode<T> dNode  = findNode(item);

		if (dNode == null)
			return false;

		removeNode(dNode);

		treeSize--;
		modCount++;

		return true;
	}


     /* (non-Javadoc)
	 * @see org.eda.estructurasdatos.Tree#size()
	 */
  	@Override
	public int size()
   {
      return treeSize;
   }

     /**
     * Returns an array containing all of the elements in this tree.
     * The order of elements in the array is the iterator order of elements
     * in the tree.
     *
     * @return an array containing all of the elements in this tree.
     */
	@SuppressWarnings("rawtypes")
	public Object[] toArray()
 	{
		Object[] arr = new Object[treeSize];
		Iterator iter = iterator();
		int i = 0;

		while (iter.hasNext())
		{
			arr[i] = iter.next();
			i++;
		}

		return arr;
	}

   /**
    * Returns a string representation of this tree. The
    * representation is a comma separated list in iterator order
    * enclosed in square brackets.
    */
   @SuppressWarnings("rawtypes")
   public String toString()
   {
      int i;

      // create the return string object
      String returnStr = "[";
      Iterator iter = this.iterator();

      // output values separated by commas
      for (i = 0; i < treeSize; i++)
      {
         returnStr += iter.next();
         if (i < treeSize - 1)
            //returnStr += ", ";
        	returnStr += "\n";		// *** MIO ***
      }
      returnStr += "]";

      // return the string
      return returnStr;
   }

	/* (non-Javadoc)
	 * @see org.eda.estructurasdatos.Tree#find(T)
	 */
	
	public T find(T item)
	{
		BSTNode<T> t = findNode(item);
		T value = null;

		if (t != null)
			value = t.nodeValue;

		return value;
	}
	////////////////EJERCICIO01///////////////////////
	private int height(BSTNode<T> t) {
		int altura=0;
		int alturaHijoIzq=0;
		int alturaHijoDer=0;
		if((t.left!=null) || (t.right!=null)){
			if(t.left!=null){
				alturaHijoIzq = 1 + height(t.left);
			}
			if(t.right!=null){
				alturaHijoDer = 1 + height(t.right);
			}
			if(alturaHijoIzq>=alturaHijoDer)
				altura += alturaHijoIzq;
			else
				altura +=alturaHijoDer;
		}
		return altura;
	}
	public int height() {
		return height(this.root);
	}
	////////////////EJERCICIO02///////////////////////
	public int numberOfLeaves() {
		return numberOfLeaves(root);
	}
	private int numberOfLeaves(BSTNode<T> t) {
		int hojas=0;
		if((t.left==null) && (t.right==null))
			hojas++;
		else{
			if(t.left!=null)
				hojas+=numberOfLeaves(t.left);
			if(t.right!=null)
				hojas+=numberOfLeaves(t.right);
		}
		return hojas;
	}
	////////////////EJERCICIO03///////////////////////
	public T findMin() {
		return findMin(root).nodeValue;
	}
	public T findMax() {
		return findMax(root).nodeValue;
	}
	private BSTNode<T> findMin(BSTNode<T> t) {
		if(t.left==null)
			return t;
		return findMin(t.left);	
	}
	public T findMaxIterative() {
		return findMaxIterative(root).nodeValue;
	}
	public T findMinIterative() {
		return findMinIterative(root).nodeValue;
	}
	private BSTNode<T> findMinIterative(BSTNode<T> t) {
		while(t.left!=null)
			t=t.left;
		return t;
	}
	private BSTNode<T> findMax(BSTNode<T> t) {
		if(t.right==null)
			return t;
		return findMax(t.right);	
	}
	
	private BSTNode<T> findMaxIterative(BSTNode<T> t) {
		while(t.right!=null)
			t=t.right;
		return t;
	}
	////////////////EJERCICIO 04///////////////////////
	public String toStringLevel(int level) {
		return toStringLevel(this.root, level);
	}
	private String toStringLevel(BSTNode<T> t, int level) {
		String salida="";
		if(t!=null)
			if (level==0)
				salida+=t.nodeValue.toString()+" ";
			else
				salida+=toStringLevel(t.left,level-1) + toStringLevel(t.right,level-1);
	
		return salida;
	}
	////////////////EJERCICIO 05///////////////////////
		///Iterativo Inorden (SubArbolIzq, Raiz, SubArbolDer)
	public String toStringIterativeInorder() {
		String s = toStringIterativeInorder(root);
		return s;
	}
	public static <T> String toStringIterativeInorder(BSTNode<T> t) {
		ALStack<BSTNode<T>> pila = new ALStack<BSTNode<T>>(); //Para recorridos en profundidad: PILA
		BSTNode<T> p;
		
		String s = "";
		pila.push(t);
		if(t != null){
			while(!pila.isEmpty()){
				p = pila.peek();
				pila.pop();
				if(p.right==null && p.left==null){
					s += p.nodeValue + "  ";
				}else{
					if(p.right != null){
						pila.push(p.right);
					} 
					pila.push(new BSTNode<T>(p.nodeValue, null));
					if(p.left != null){
						pila.push(p.left);
					}
				}
			}
		}
		return s;
	}
		///Recursivo Inorden (SubArbolIzq, Raiz, SubArbolDer)
	public String toStringInorder(){		
		return toStringInorder(this.root);
	}
	private static <T> String toStringInorder(BSTNode<T> t){
		String s = "";
		if (t != null) {
			s += toStringInorder(t.left);	// descend left
			s += t.nodeValue + "  ";		// display the node
			s += toStringInorder(t.right);	// descend right
		}
		return s;

	}
	////////////////EJERCICIO 06///////////////////////
		///Iterativo Postorden (SubArbolIzq, SubArbolDer, Raiz)
	public String toStringIterativePostorder() {
		String s = toStringIterativePostorder(root);
		return s;
		}
	public static <T> String toStringIterativePostorder(BSTNode<T> t) {
		ALStack<BSTNode<T>> pila = new ALStack<BSTNode<T>>(); //Para recorridos en profundidad: PILA
		BSTNode<T> p;
		
		String s = "";
		if(t != null){
			pila.push(t);
			while(!pila.isEmpty()){
				p = pila.peek();
				pila.pop();
				if(p.right==null && p.left==null){
					s += p.nodeValue + "  ";
				}else{
					pila.push(new BSTNode<T>(p.nodeValue, null));
					if(p.right != null){
						pila.push(p.right);
					}
					if(p.left != null){
						pila.push(p.left);
					}
				}
			}
		}
		return s;
	}
		///Recursivo Postorden (SubArbolIzq, SubArbolDer, Raiz)
	public String toStringPostorder(){		
		return toStringPostorder(this.root);
	}
	private static <T> String toStringPostorder(BSTNode<T> t) {
		String s = "";
		if (t != null) {
			s += toStringPostorder(t.left);	// descend left
			s += toStringPostorder(t.right);	// descend right
			s += t.nodeValue + "  ";		// display the node
		}
		return s;
	}
	////////////////EJERCICIO 07///////////////////////
	public void addBalanced(ArrayList<T> aL) {
		Less<T> comp = new Less<T>(); //Ordenamos de menor a mayor en este caso, segun el comparator
		Collections.sort(aL, comp);
		addBalanced(aL, 0, aL.size() - 1);
	}
	private void addBalanced(ArrayList<T> aL, int left, int right) {
		int central = 0;
		if (left <= right) {
			central = (left + right) / 2;
			this.add(aL.get(central)); //Vamos agregando la raiz primero
			
			
			addBalanced(aL, left, central - 1);
			addBalanced(aL, central + 1, right);
		}

	}
	////////////////EJERCICIO 08///////////////////////
	public BSTree<T> buildSimetricTree() {
		BSTree<T> arbol = new BSTree<T>();
		if (this.root != null)
			arbol.root = buildSimetricTree(this.root); //Construimos el arbol
		return arbol;
	}
	private BSTNode<T> buildSimetricTree(BSTNode<T> t) {
		BSTNode<T> raiz = t;
		BSTNode<T> derecho = null;
		BSTNode<T> izquierdo = null;
		if (raiz != null) {
			derecho = buildSimetricTree(t.left);
			izquierdo = buildSimetricTree(t.right);
			raiz.left = izquierdo;
			raiz.right = derecho;
		}
		return raiz;
	}
	////////////////EJERCICIO 09///////////////////////
	public int pathHeight(T x) {
		int altura = 0;
		BSTNode<T> ite = root;
		
		while(ite!=null){
			int comparacion = ((Comparable<T>)ite.nodeValue).compareTo(x);
			if(comparacion == 0)
				return altura;
			if(comparacion < 0 ) {
				if(ite.right==null) 
					break;
				ite = ite.right;
				altura ++;
			}
			else{
				if(ite.left==null)
					break;
				ite = ite.left;
				altura ++;
			}
		}
		return -1;
	}
	////////////////EJERCICIO EXTRA (EST� EN EL TEST PERO NO EN LA ACTIVIDAD)///////////////////////
	public String toStringPreorder(){ //Usamos el m�todo recursivo ya creado...preorderDisplay()
		return toStringPreoder(this.root);
	}
	private static <T> String toStringPreoder(BSTNode<T> t){
		return preorderDisplay(t);
	}
	public String toStringIterativePreorder(){ //M�todo iterativo.
		return toStringIterativePreorder(this.root);
	}
	public static <T> String toStringIterativePreorder(BSTNode<T> t){ //Copiado integro del temario
		ALStack<BSTNode<T>> st = new ALStack<BSTNode<T>>();  //Utiliza una pila ya que lo recorremos en profundidad.
		BSTNode<T> p;
		
		String s = "";
		if (t != null) {
			st.push(t);
			while (!st.isEmpty()) {
				p = st.peek();
				st.pop();
				s += p.nodeValue + "  ";
				if (p.right != null)
					st.push(p.right);
				if (p.left != null)
					st.push(p.left);
			}
		}
		return s;
	}
	///////////////////////EXAMEN GRUPO B////////////////////////////
	private BSTNode<T> removeLeaves(BSTNode<T> t){
		ArrayList<BSTNode<T>> aBorrar = new ArrayList<BSTNode<T>>();
		if((t.left==null) && (t.right==null))
			remove(t);
		else{
			if(t.left!=null)
				removeLeaves(t.left);
			if(t.right!=null)
				removeLeaves(t.right);
		}
		return null;
	}
	public void removeLeaves(){
		removeLeaves(this.root);
	}
	
	
	
	
	
	
	private class TreeIterator implements Iterator<T>
	{
		// set expectedModCount to the number of list changes
		// at the time of iterator creation
		private int expectedModCount = modCount;
		// node of the last value returned by next() if that
		// value was deleted by the iterator method remove()
		private BSTNode<T> lastReturned = null;
		// node whose value is returned a subsequent call to next()
		private BSTNode<T> nextNode = null;

		// constructor
		TreeIterator()
		{
			nextNode = root;

			// if the tree is not empty, the first node inorder is the farthest node left from root
			if (nextNode != null)
				while (nextNode.left != null)
					nextNode = nextNode.left;
		}

		// returns true if the tree has more
		// unvisited elements
		public boolean hasNext()
		{
			// elements remain if nextNode is not null
			return nextNode != null;
		}

		
		public T current() {
			return lastReturned.nodeValue;			
		}
		
		
		// returns the next element in the iteration.
		// throws NoSuchElementException if the iteration
		// has no more elements
		//public T next()
		public T next()
		{
			// check that the iterator is in a consistent state.
			// throws ConcurrentModificationException if it
			// it is not
			checkIteratorState();

			// check if the iteration has an another element
			// if not, throw NoSuchElementException
			if (nextNode == null)
				throw new NoSuchElementException(
						"Iteration has no more elements");

			// save current value of next in lastReturned.
			lastReturned = nextNode;

			// set nextNode to the next node in order
			BSTNode<T> p;

			if (nextNode.right != null)
			{
				// successor is the furthest left node of
				// right subtree
				nextNode = nextNode.right;

				while (nextNode.left != null)
					nextNode = nextNode.left;
			}
			else
			{
				// have already processed the left subtree, and
				// there is no right subtree. move up the tree,
				// looking for a parent for which nextNode is a left child,
				// stopping if the parent becomes null. a non-null parent
				// is the successor. if parent is null, the original node
				// was the last node inorder
				p = nextNode.parent;

				while (p != null && nextNode == p.right)
				{
					nextNode = p;
					p = p.parent;
				}

				// if we were previously at the right-most node in
				// the tree, nextNode = null
				nextNode = p;
			}

			return lastReturned.nodeValue;
		}

      // removes the last element returned by next() from the
      // underlying collection. this method can be called only
      // once per call to next(). it is an error to modify
      // the underlying collection while the iteration is in
      // progress in any way other than by calling this method.
      // throws IllegalStateException if next() has not yet been
      // called,or remove() has already been called after the last
      // call to next()
      public void remove()
      {
         // check for a missing call to next() or previous()
         if (lastReturned == null)
            throw new IllegalStateException(
               "Iterator call to next() " +
               "required before calling remove()");

         // make sure our state is good
         checkIteratorState();

			// if lastReturned has two children, the replacement node
			// during deletion is nextNode. the value in nextNode
			// is copied to lastReturned. nextNode must be
			// lastReturned
			if (lastReturned.left != null && lastReturned.right != null)
				 nextNode = lastReturned;
         removeNode(lastReturned);

         // list has been modified
         modCount++;
         expectedModCount = modCount;

         // we did a deletion. indicate this by setting lastReturned
         // to null and decrementing treeSize
         lastReturned = null;
         treeSize--;
      }

      private void checkIteratorState()
      {
         if (expectedModCount != modCount)
            throw new ConcurrentModificationException(
               "Inconsistent iterator");
      }
   }

	/**
	 * Return a copy of this <tt>STree</tt> instance.
	 */
	public Object clone()
	{
		BSTree<T> copy = null;

		try
		{
			copy = (BSTree<T>)super.clone();
		}
		catch (CloneNotSupportedException cnse)
		{ throw new InternalError(); }

		copy.root = copyTree(root);
		// return the cloned object
		return copy;
	}

   /*
    * Uses the static method copyTree() in the class BinaryTree as a model
    * to create a duplicate of the tree with root <tt>t</tt> and returns
	* a reference to the root of the copied tree.  In this case, the elements
	* are STNode<T> objects
    */
	private static <T> BSTNode<T> copyTree(BSTNode<T> t)
	{
		// newNode points at a new node that the algorithm
		// creates. newLptr. and newRptr point to the subtrees
		// of newNode
		BSTNode<T> newLeft, newRight, newNode;

		// stop the recursive scan when we arrive at empty tree
		if (t == null)
			return null;

		// build the new tree from the bottom up by building the two
		// subtrees and then building the parent. at node t, make
		// a copy of the left subtree and assign its root node reference
		// to newLeft. make a copy of the right subtree and assign its
		// root node reference to newRight
		newLeft = copyTree(t.left);
		newRight = copyTree(t.right);

		// create a new node whose value is the same as the value in t
		// and whose children are the copied subtrees
		newNode = new BSTNode<T> (t.nodeValue, null);
		newNode.left = newLeft;
		newNode.right = newRight;
		if (newLeft != null)
			newLeft.parent = newNode;
		if (newRight != null)
			newRight.parent = newNode;

		// return a reference to the root of the newly copied tree
		return newNode;
	}

   /**
	* Returns a string that displays the elements in the binary tree using
	* the preorder (NLR) scan.  The description is a listing of
	* elements separated by two blanks.
	* @param t a <tt>TNode</tt> that designates the root of the tree.
	* @return string that contains the list of elements in the array.
	*/
	private static <T> String preorderDisplay(BSTNode<T> t)
	{
			// return value
			String s = "";

	      // the recursive scan terminates on a empty subtree
	      if (t != null)
	      {
	         s += t.nodeValue + "  ";  			// display the node
	         s += preorderDisplay(t.left);		// descend left
	         s += preorderDisplay(t.right);	// descend right
	      }

	      return s;
	   }

	public String preorderDisplay()
	{
			// return value
			String s = preorderDisplay(root);

	      return s;
	   }
	
	private static <T> String displayTree(BSTNode<T> t, int level)
	{
		  String s = "";

	      // the recursive scan terminates on a empty subtree
	      if (t != null)
	      {
	    	  if (t.right != null)
	    		  s += displayTree(t.right, level + 1);
	    	  for (int i = 0; i < level; i++)
	    		  s += "     ";
	    	  s += "(" + t.nodeValue.toString() + ")[" + level + "]\n";
	    	  if (t.left != null)
	    		  s += displayTree(t.left, level + 1);
	      }
	      
	      return s;
	   }

	public String displayTree() {
		
		  String s = displayTree(root, 0);
			
	      return s;
	   }
	
	
	// declares a binary search tree node object
	private static class BSTNode<T> implements Comparable<T>
	{
		// STNode is used to implement the binary search tree class
		// making the data public simplifies building the class functions

		// node data
		T nodeValue;

		// child links and link to the node's parent
		BSTNode<T> left, right, parent;

		// constructor that initializes the value and parent fields and sets
		// the link fields left and right to null
		public BSTNode (T item, BSTNode<T> parentNode)
		{
			nodeValue = item;
			left = null;
			right = null;
			parent = parentNode;
		}
		@Override
		public int compareTo(T o) {
			return ((Integer) nodeValue).compareTo((Integer) o);
		}
	}

}
