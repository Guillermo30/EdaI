package org.eda1.actividad04.ejercicio01;

import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

import org.eda1.estructurasdedatos.Iterator;
/**
 * This class implements the Collection interface using a hash table
 * as the underlying storage structure.
 */
public class HashAVL<T> {
	
	private AVLTree<T> [] table;
	private int hashTableSize;
    private int defaultHashTableSize;
	private double maxLoadFactor;
	private int tableThreshold;

	// for iterator consistency checks
	private int modCount = 0;

	// ***
	private void rehash(int newTableSizeRehash) {
		// allocate the new hash table and record a reference
		// to the current one in oldTable
		int newTableSize = nextPrime(newTableSizeRehash);
		AVLTree<T> [] newTable = new AVLTree[newTableSize];
		for (int i = 0; i < newTableSize; i++)
			newTable[i] = new AVLTree<T>();
		
		AVLTree<T> [] oldTable = table;

		int index;
		
		// cycle through the current hash table
		for (int i = 0; i < table.length; i++) {
			// see if there is a linked list present
			if (table[i] != null) {
				java.util.Iterator<T> iter = table[i].iterator();
				T currItem;
				while (iter.hasNext()) {
					currItem = iter.next();
					
					index = (currItem.hashCode() & Integer.MAX_VALUE) % newTableSize;
					
					newTable[index].add(currItem);
				}
			}
		}

		// the table is now newTable
		table = newTable;
		// update the table threshold
		tableThreshold = (int)(table.length * maxLoadFactor);
		// let garbage collection get rid of oldTable
		oldTable = null;
	}

    /**
     * Internal method to find a prime number at least as large as n.
     * @param n the starting number (must be positive).
     * @return a prime number larger than or equal to n.
     */
    private static int nextPrime(int n) {
        if (n % 2 == 0)
            n++;

        for(; !isPrime(n); n += 2)
            ;

        return n;
    }

    /**
     * Internal method to test if a number is prime.
     * Not an efficient algorithm.
     * @param n the number to test.
     * @return the result of the test.
     */
    private static boolean isPrime(int n) {
        if (n == 2 || n == 3)
            return true;

        if (n == 1 || n % 2 == 0)
            return false;

        for (int i = 3; i * i <= n; i += 2)
            if (n % i == 0)
                return false;

        return true;
    }

    /**
     * Creates an empty hash table with 11 buckets.
     */
	// construct an empty hash table with 11 buckets
	public HashAVL() {
		defaultHashTableSize = 11;
		table = new AVLTree[defaultHashTableSize];
		for (int i = 0; i < defaultHashTableSize; i++)
			table[i] = new AVLTree<T>();
		hashTableSize = 0;
		maxLoadFactor = 0.75;
		tableThreshold = (int)(table.length * maxLoadFactor);
	}

	public HashAVL(int defaultTableSize, double loadFactor) {
		defaultHashTableSize = defaultTableSize;
		table = new AVLTree[defaultHashTableSize];
		for (int i = 0; i < defaultHashTableSize; i++)
			table[i] = new AVLTree<T>();
		hashTableSize = 0;
		maxLoadFactor = loadFactor;
		tableThreshold = (int)(table.length * maxLoadFactor);
	}

    /**
     * Adds the specified item to this hash table if it is not already present.
     *
     * @param item element to be added to this hash table.
     * @return <tt>true</tt> if the hash table did not already contain the specified
     *         element.
     */
	public boolean add(T item) {
		// compute the hash table index
		int index = (item.hashCode() & Integer.MAX_VALUE) % table.length;
		
		// find the item and return false if item is in the ArrayList
		if (table[index].contains((T)item))
			return false;
		
		if (!table[index].add(item))
			return false;
		
		// we will add item, so increment modCount
		modCount++;

		hashTableSize++;

		if (hashTableSize >= tableThreshold)
			rehash(2 * table.length + 1);

		return true;
	}

    /**
     * Removes all of the elements from this hash table. The resulting table is empty
     * but has the same number of buckets after the method executes.
     */
	public void clear()	{
		// make all hash table entries null
		for (int i = 0; i < table.length; i++)
			table[i] = null;

		// we have modified the hash table, and it has
		// no entries
		modCount++;
		hashTableSize = 0;
	}

    /**
     * Returns <tt>true</tt> if this hash table contains the specified element.
     *
     * @param item the object to be checked for containment in this hash table.
     * @return <tt>true</tt> if this hash table contains the specified element.
     */
	public boolean contains(Object item) {
		// compute the hash table index
		int index = (item.hashCode() & Integer.MAX_VALUE) % table.length;

		// find the item and return false if item is in the ArrayList
		if (table[index].contains((T)item))
			return true;
		else
			return false;
	}
	
	public int find(Object item) {
		int hash  = item.hashCode() & Integer.MAX_VALUE;
		int index = hash % table.length;
		if(table[index] != null){
			return table[index].pathHeight((T)item) +1;
		}
		else
			return -1;
	}

     /**
     * Returns <tt>true</tt> if this hash table contains no elements.
     *
     * @return <tt>true</tt> if this hash table contains no elements.
     */
	public boolean isEmpty() {
		return hashTableSize == 0;
	}

    /**
     * Returns an iterator over the elements in this tree.
     *
     * @return an iterator over the elements in this tree.
     */
	public Iterator<T> iterator() {
		// create and return an instance of the inner class IteratorImpl
		return new HashArrayListIterator();
	}

    /**
     * Removes the specified item from this hash table if it is present.
     *
     * @param item object to be removed from this hash table, if present.
     * @return <tt>true</tt> if the hash table contained the specified element.
     */
	public boolean remove(Object item) {
		// compute the hash table index
		int index = (item.hashCode() & Integer.MAX_VALUE) % table.length;
		
		if (table[index].remove(item)) {
			modCount++;
			hashTableSize--;
			return true;
		}
		else
			return false;

	}

     /**
     * Returns the number of elements in this hash table.
     *
     * @return the number of elements in this hash table.
     */
	public int size() {
		return hashTableSize;
	}

     /**
     * Returns an array containing all of the elements in this hash table.
     *
     * @return an array containing all of the elements in this hash table.
     */
   public Object[] toArray() {
		// allocate the array an an iterator
		Object[] arr = new Object[hashTableSize];
		Iterator<T> iter = iterator();
		int i = 0;

		// iterate the hash table and assign its
		// values into the array
		while (iter.hasNext()) {
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
   public String toString() {
		int max = hashTableSize - 1;
		StringBuffer buf = new StringBuffer();
		Iterator<T> iter = iterator();

		buf.append("[");
		for (int i = 0; i <= max; i++) {
			buf.append(iter.next());

	    	if (i < max)
				buf.append(", ");
		}
		buf.append("]");
		return buf.toString();
	}

	// inner class that implement hash table iterators
	private class HashArrayListIterator implements Iterator<T> {
		int expectedModCount;	// to check iterator consistency
		int index;         		// index of current hash table bucket
		int indexOfCurrentAL;	// index of the current ArrayList
		int globalIndex;		// global index
		T lastReturned;			// reference to the last value returned by next()

		HashArrayListIterator() {
			// the expected modCount starts at modCount
			expectedModCount = modCount;

			// find the first non-empty bucket
			if (hashTableSize != 0) {
				int i = 0;
				while ((i < table.length) && (table[i].isEmpty()))
					i++;
				index = i;
				indexOfCurrentAL = 0;
			}
			else {
				index = -1;
				indexOfCurrentAL = 0;				
			}
				
			globalIndex = 0;
			lastReturned = null;				
		}
		
		public int tableLength() {
			return table.length;
		}

		public int maxEntrySize() {
			int max = Integer.MIN_VALUE;
			for (int i = 0; (i < table.length); i++) {
				if (table [i] != null) {
					if (table[i].size() > max)
						max = table[i].size();
				}
			}
			return max;
		}

		public int minEntrySize() {
			int min = Integer.MAX_VALUE;
			for (int i = 0; (i < table.length); i++) {
				if (table [i] != null) {
					if (table[i].size() < min)
						min = table[i].size();
				}
			}
			return min;
		}
		public boolean hasNext() {
			// we are at the end of the table if next == null
			return globalIndex < hashTableSize;
		}

		public T current() {
			return lastReturned;
		}

		public T next() {
			// check for iterator consistency
			if (modCount != expectedModCount)
				 throw new ConcurrentModificationException();

			// if entry is null, we are at the end of the table
			if (table[index].isEmpty())
				 throw new NoSuchElementException();

			//System.out.println(index + ", " + indexOfCurrentAL + ", " + globalIndex);
			
			// capture the value we will return
			T [] aux = (T[]) table[index].toArray();
			lastReturned = (T) aux[indexOfCurrentAL];
			
			// move to the next entry in the current ArrayList
			if (indexOfCurrentAL + 1 == table[index].size()) {
				// record the current bucket index
				int i = index;

				i++;
				if (i < table.length) {
					while ((i < table.length) && (table[i].isEmpty()))
						i++;
					
					index = i;
					indexOfCurrentAL = 0;
				}
			}
			else
				indexOfCurrentAL++;
			
			globalIndex++;
			
			return lastReturned;
		}

		public void remove() {
		   // check for a missing call to next() or previous()
		   if (lastReturned == null)
		      throw new IllegalStateException(
		         "Iterator call to next() " +
		         "required before calling remove()");
			if (modCount != expectedModCount)
				 throw new ConcurrentModificationException();

			// remove lastReturned by calling remove() in Hash.
			// this call will increment modCount
			HashAVL.this.remove(lastReturned);
			expectedModCount = modCount;
			lastReturned = null;
		}
	}

	public int tableLength() {
		return table.length;
	}

	public int maxEntrySize() {
		int max = Integer.MIN_VALUE;
		for (int i = 0; (i < table.length); i++) {
			if (table [i] != null) {
				if (table[i].size() > max)
					max = table[i].size();
			}
		}
		return max;
	}

	public int minEntrySize() {
		int min = Integer.MAX_VALUE;
		for (int i = 0; (i < table.length); i++) {
			if (table [i] != null) {
				if (table[i].size() < min)
					min = table[i].size();
			}
		}
		return min;
	}
}
