package org.eda1.actividad04.ejercicio01;

import java.util.NoSuchElementException;
import java.lang.IllegalStateException;
import java.util.ConcurrentModificationException;

import org.eda1.estructurasdedatos.Collection;
import org.eda1.estructurasdedatos.Iterable;
import org.eda1.estructurasdedatos.Iterator;

/**
 * This class implements the Collection interface using a hash table
 * as the underlying storage structure.
 */
public class Hash<T> implements Collection<T>, Iterable<T> {
	// the hash table
	private HashEntry<T> [] table;
	private int hashTableSize;
    private static final int DEFAULT_TABLE_SIZE = 11;
	private final double MAX_LOAD_FACTOR = 10.0; //.75;
	private int tableThreshold;

	// for iterator consistency checks
	private int modCount = 0;

	private void rehash(int newTableSize) {
		// allocate the new hash table and record a reference
		// to the current one in oldTable
		HashEntry<T>[] newTable = new HashEntry[nextPrime(newTableSize)],
				  oldTable = table;
		HashEntry<T> entry, nextEntry;
		int index;
		
		// cycle through the current hash table
		for (int i = 0; i < table.length; i++) {
			// record the current entry
			entry = table[i];
			// see if there is a linked list present
			if (entry != null) {
				// have at least 1 element in a linked list. assign
				// current table element to null to facilitate
				// faster garbage collection
				table[i] = null;
				do
				{
					// record the next entry in the original linked
					// list
					nextEntry = entry.next;
					// compute the new table index. notice how
					// the saved hashValue prevents a possibly
					// expensive call to hashCode()
					index = entry.hashValue % newTableSize;
					// insert entry the front of the new table's
					// linked list at location index
					entry.next = newTable[index];
					newTable[index] = entry;
					// assign the next entry in the original linked
					// list to entry
					entry = nextEntry;
				} while (entry != null);
			}
		}

		// the table is now newTable
		table = newTable;
		// update the table threshold
		tableThreshold = (int)(table.length * MAX_LOAD_FACTOR);
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
     * Creates an empty hash table with 17 buckets.
     */
	// construct an empty hash table with 17 buckets
	public Hash() {
		table = new HashEntry[DEFAULT_TABLE_SIZE];
		hashTableSize = 0;
		tableThreshold = (int)(table.length * MAX_LOAD_FACTOR);
        for (int i = 0; i < table.length; i++)
            table[i] = null;
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
		int hashValue = item.hashCode() & Integer.MAX_VALUE;
		int index = hashValue % table.length;
		HashEntry<T> entry;

		// entry references the front of a linked list of colliding
		// values
		entry = table[index];

		// scan the linked list and return false if item is in list
		while (entry != null) {
			if (entry.value.equals(item))
				return false;

			entry = entry.next;
		}

		// we will add item, so increment modCount
		modCount++;

		// create the new table entry so its successor
		// is the current head of the list

		entry =	new HashEntry<T>(item, hashValue, (HashEntry<T>)table[index]);

		// add it at the front of the linked list and increment
		// the size of the hash table
		table[index] = entry;
		hashTableSize++;

		if (hashTableSize >= tableThreshold)
			rehash(2 * table.length + 1);

		return true;
	}

    /**
     * Removes all of the elements from this hash table. The resulting table is empty
     * but has the same number of buckets after the method executes.
     */
	public void clear() {
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
		HashEntry entry;

		// entry references the front of a linked list of colliding
		// values
		entry = table[index];

		// scan the linked list and return true if item is in list
		while (entry != null) {
			if (entry.value.equals(item))
				return true;
			entry = entry.next;
		}

		return false;
	}

	public int find(Object item) {
		// compute the hash table index
		int index = (item.hashCode() & Integer.MAX_VALUE) % table.length;
		HashEntry entry;

		// entry references the front of a linked list of colliding
		// values
		entry = table[index];

		// scan the linked list and return true if item is in list
		int count = 0;
		while (entry != null) {
			if (entry.value.equals(item))
				return count;
			entry = entry.next;
			count++;
		}

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
		return new HashIterator();
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
		HashEntry<T> curr, prev;

		// curr references the front of a linked list of colliding
		// values. initialize prev to null
		curr = table[index];
		prev = null;
		// scan the linked list for item
		while (curr != null) {
			if (curr.value.equals(item)) {
				// we have located item and will remove
				// it. increment modCount
				modCount++;
				// if prev is not null, curr is not the front
				// of the list. just skip over curr
				if (prev != null)
				 	prev.next = curr.next;
				else
					// curr is front of the list. the new front
					// of the list is curr.next
				 	table[index] = curr.next;

				// decrement hash table size and return true
				hashTableSize--;

				return true;
			 }
			 else {
				// move prev and curr forward
				prev = curr;
				curr = curr.next;
			}
		}

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
	private class HashIterator implements Iterator<T> {
		HashEntry<T> next;		// next entry to return
		int expectedModCount;	// to check iterator consistency
		int index;         		// index of current hash table bucket
		T lastReturned;			// reference to the last value returned by next()

		HashIterator() {
			int i = 0;
			HashEntry<T> n = null;

			// the expected modCount starts at modCount
			expectedModCount = modCount;

			// find the first non-empty bucket
			if (hashTableSize != 0)
				while (i < table.length && ((n = table[i]) == null))
					i++;

			next = n;
			index = i;
			lastReturned = null;
		}

		public boolean hasNext() {
			// we are at the end of the table if next == null
			return next != null;
		}

		public T current() {
			return lastReturned;
		}

		public T next() {
			// check for iterator consistency
			if (modCount != expectedModCount)
				 throw new ConcurrentModificationException();

			// we will return the value in Entry object next
			HashEntry<T> entry = next;

			// if entry is null, we are at the end of the table
			if (entry == null)
				 throw new NoSuchElementException();

			// capture the value we will return
			lastReturned = entry.value;
			// move to the next entry in the current linked list
			HashEntry<T> n = entry.next;
			// record the current bucket index
			int i = index;

			if (n == null) {
				// we are at the end of a bucket. search for the
				// next non-empty bucket
				i++;
				while (i < table.length && (n = table[i]) == null)
					i++;
			}

			index = i;
			next = n;

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
			Hash.this.remove(lastReturned);
			expectedModCount = modCount;
			lastReturned = null;
		}
	}
	
	public int tableLength() {
		return table.length;
	}

	private static class HashEntry<T> {
		// value in the hash table
	   T value;
	   // saved value.hashCode() & Integer.MAX_VALUE
	   int hashValue;
	   // next entry in the linked list of colliding values
	   HashEntry<T> next;

	   // make a new entry with given value and reference to
	   // the next Entry
	   HashEntry(T value, int hashValue, HashEntry<T> next) {
	      this.value = value;
	      this.hashValue = hashValue;
	      this.next = next;
	   }
	}
	
}
