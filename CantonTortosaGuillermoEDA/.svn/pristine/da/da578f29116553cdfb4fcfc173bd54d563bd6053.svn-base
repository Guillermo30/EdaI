package org.eda1.actividadesSeptiembre.actividad03;

public class HashTableExponentProbing<T> {
	
	int contador;
	
	private static final int DEFAULT_TABLE_SIZE = 71;
	private final double MAX_LOAD_FACTOR = .5;
	private HashEntry<T>[]table;
	private int occupiedEntries; 
	private int hashTableSize; 
	private int tableThreshold;
	private int numberOfCollisions;
	private int exponent;
	
	public HashTableExponentProbing() {
		this(DEFAULT_TABLE_SIZE);
	}
	
	public HashTableExponentProbing(int exp) {
		// TODO Auto-generated constructor stub
		allocateTable(DEFAULT_TABLE_SIZE);
		occupiedEntries = 0;
		hashTableSize = 0;
		tableThreshold = (int) (table.length * MAX_LOAD_FACTOR);
		numberOfCollisions = 0;
		exponent = exp;
		for (int i = 0; i < table.length; i++){
			table[i]= null;
			
		}
		contador=0;
	}
	
	private void rehash(int newTableSizeRehash) {
		// allocate the new hash table and record a reference
		// to the current one in oldTable
		HashEntry<T> [] oldTable = table.clone();
		clear();
		allocateTable(newTableSizeRehash);
		// update the table threshold
		tableThreshold=(int)(table.length*MAX_LOAD_FACTOR);
		
		
		
		
		
		// cycle through the current hash table
		for (int i = 0; i <  oldTable.length; i++) {
			// see if there is a linked list present
			if (( oldTable[i] != null)&&(oldTable[i].isActive) ){
				add(oldTable[i].element);	
			}
		}
		
	}
	
	private int findPos(T x){
		
		int pos= myHash(x);
		int contador=1;
		
		while((table[pos]!=null) && (!table[pos].element.equals(x))){
			
			pos=(int)((pos+Math.pow(contador, exponent))%table.length);
			contador++;
			numberOfCollisions++;
		}
		return pos;
	}
	

	public boolean add(T item) {
		
		//int index = (item.hashCode() & Integer.MAX_VALUE) % table.length;
		int index = findPos(item);
		//HashEntry<T> entry;
		// find the item and return false if item is in the ArrayList
		if(table[index]==null){
			table[index]=new HashEntry<T>(item,true);
			occupiedEntries++;
			hashTableSize++;
		}else{	
					//if(table[index].isActive==true || table[index].element.equals(item)){
			table[index]=new HashEntry(item);
			occupiedEntries++;
					//
					//}	
					//}else{
					//return false;

					////entry = entry.next;
					//}
				//return false;

		}
		
	
		if (hashTableSize >= tableThreshold){
			rehash(2 * table.length + 1);
			//numberOfCollisions++;
		}
		
		return true;
	}
	
	public boolean remove(T x){
		int index = myHash(x);
		if(contains(x)){
			this.table[index].isActive=false;
			return true;
		}
		
		return false;
	}
	
	public int size(){
		return this.hashTableSize;
	}
	public int capacity() {
		// TODO Auto-generated method stub
		return table.length; 
	}
	
	public int numberOfOccupiedEntries() {
		return occupiedEntries; 
	}
	

	public int getNumberOfCollisions() {
		// TODO Auto-generated method stub
		return numberOfCollisions;
	}
	public boolean contains(T x){
		int index = myHash(x);
		if(isEmpity()){
			return false;
		}else if(this.table!=null ){
			return true;
		}else{
			return false;
		}
	}
	
	private boolean isActive(int currentPos){
		
		return table[currentPos].isActive;
	}
	
	public void clear(){
		
		occupiedEntries=0;
		hashTableSize=0;
		
		for (int i=0;i<table.length;i++){
			table[i]=null;
		}
	}
	
	public boolean isEmpity(){
		return this.table==null;
	}
	
	public Object[] toArray(){
			return this.table;
	}
	
	public String toString(){
		
		String resultado="[";
		
		for(int i=0;i<table.length;i++){
			if((table[i]!=null) && (table[i].isActive)){
				
				resultado+=table[i].element;
				
				if(i+1<table.length)
				{
					resultado+=", ";
				}
			}
		}
		resultado+="]";
		
		return resultado;
	}
	
	private int myHash(T x) {
		int hashVal = (x.hashCode() & Integer.MAX_VALUE) % table.length;
		if (hashVal < 0)
		hashVal += table.length;
		return hashVal;
	}
	
	private void allocateTable(int tableSize){
		table = new HashEntry[nextPrime(tableSize)]; 
	}
	
	private static int nextPrime(int n) {
		if (n % 2 == 0)
		n++;
		for(; !isPrime(n); n += 2)
		;
		return n;
	}
	
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
	
	private static class HashEntry<T> {
		public T element; 
		public boolean isActive; 
		
		public HashEntry(T e) {
			this(e, true);
		}

		public HashEntry(T e, boolean i) {
			element = e;
			isActive = i;
		}
	}
	
}
