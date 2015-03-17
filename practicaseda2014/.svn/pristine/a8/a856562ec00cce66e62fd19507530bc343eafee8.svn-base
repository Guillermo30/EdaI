package org.eda1.tema05.TreeSet_TreeMap;

import java.io.IOException;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.TreeSet;

class Person{
	protected String id;
	protected String name;
	protected Integer age;
	
	public Person(String id, String name, Integer age) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
	}

	public Person() {
		super();
	}

	@Override
	public String toString() {
		return "Person {id = " + id + ", name = " + name + ", age = " + age + "}";
	}
	
}

class PersonById extends Person implements Comparable{
	public PersonById(String id, String name, Integer age) {
		super(id, name, age);
	}
	
	public PersonById() {
		super();
	}

	public PersonById(Person p) {
		super(p.id, p.name, p.age);
	}

	@Override
	public int compareTo(Object p) {
		// TODO Auto-generated method stub
		return id.compareTo(((Person)p).id);
	}
}

class PersonByName extends Person implements Comparable{
	public PersonByName(String id, String name, Integer age) {
		super(id, name, age);
	}
	
	public PersonByName() {
		super();
	}

	public PersonByName(Person p) {
		super(p.id, p.name, p.age);
	}

	@Override
	public int compareTo(Object p) {
		// TODO Auto-generated method stub
		return name.compareTo(((Person)p).name);
	}
}

class PersonByAge extends Person implements Comparable{
	public PersonByAge(String id, String name, Integer age) {
		super(id, name, age);
	}
	
	public PersonByAge() {
		super();
	}

	public PersonByAge(Person p) {
		super(p.id, p.name, p.age);
	}

	@Override
	public int compareTo(Object p) {
		// TODO Auto-generated method stub
		return age - ((Person)p).age;
	}
}

class PersonComparator implements Comparator{
	@Override
	public int compare(Object obj1, Object obj2) {
		// TODO Auto-generated method stub
		if (obj1 == obj2)
			return 0;
		else if (obj1 == null)
			return -1;
		else if (obj2 == null)
			return 1;
		else
			return ((Person)obj1).name.compareTo(((Person)obj2).name);
	}
}

public class TreeSet_TreeMap {
	public static void main(String[] args) throws IOException {
		TreeSet ts1 = new TreeSet();
		ts1.add(new Integer(2));
		ts1.add(new Integer(1));
		ts1.add(new Integer(4));
		ts1.add(new Integer(3));
		ts1.add(new Integer(2));
		ts1.add(new Integer(7));
		ts1.add(new Integer(1));
		
		System.out.println("ts1 = " + ts1);
		System.out.println(ts1.contains(new Integer(3)));
		System.out.println(ts1.contains(new Integer(5)));
		System.out.println(ts1.first() + " " + ts1.last());
		System.out.println(ts1.headSet(new Integer(4)));
		System.out.println(ts1.tailSet(new Integer(4)));
		
		TreeSet ts2 = new TreeSet(ts1);
		System.out.println("ts2 = " + ts2);
		ts2.remove(new Integer(4));
		System.out.println("ts2 = " + ts2);
		ts1.removeAll(ts2);
		System.out.println("ts1 = " + ts1);
		ts1.addAll(ts2);
		System.out.println("ts1 = " + ts1);

		
	    String [] pico =
	        {   "Tetica de Bacares", "Calar Gallinero", "Pico de la Serrata", 
	    		"Collado Garcia", "Morrón de Visorio", "Calar Alto",
	    		"Peñón de Polarda", "Chullo", "Lúcar",
	    		"Cerro Poyo", "Almirez", "Pico Colativí",
	    		"Morrón de la Lagunilla", "Pico del Puntal"};
	    int [] altura = 
	       {    2083, 2049, 562, 
	    		1246, 2246, 2168,
	    		2150, 2611, 1722, 
	    		2045, 2518, 1387,
	    		2249, 1287}; 
	    
	    TreeMap mapa;
	    mapa = new TreeMap();
	     
	    for (int i = 0; i < pico.length; i++)
	      mapa.put(pico[i], new Integer(altura[i]));
	      //mapa.put(new Integer(altura[i]), pico[i]);
	    
	    System.out.println("\t Mapa creado \n" + mapa);
	    
	    SortedMap sub1 = null;
	    sub1 = mapa.subMap("B", "M");  // desde 'B' hasta 'M' (exclusive)
	    System.out.println("\t Submapa en el rango [B ... M) \n" + sub1);
	    
	    SortedMap sub2 = null;
	    sub2 = (SortedMap) mapa.headMap("M");      // claves menores que 'M'
	    System.out.println("\t Submapa de claves menores que M \n" + sub2);
	    
	    SortedMap sub3 = null;
	    sub3 = (SortedMap) mapa.tailMap("M");    // claves mayores o iguales que 'M'
	    System.out.println("\t Submapa de claves mayores que M \n" + sub3);
	    
	    System.out.println("Borra primer elemento: " + mapa.remove(mapa.firstKey()));
	    System.out.println("\t Mapa actual \n" + mapa);

	    
		TreeSet pTS1 = new TreeSet();
		Person [] p = {	new Person("77777777A", "Antonio Corral", new Integer(22)),
						new Person("22222222B", "Pedro Lopez", new Integer(20)),
						new Person("88888888C", "Juan Sanchez", new Integer(21)),
						new Person("99999999D", "Luisa Martinez", new Integer(20)),
						new Person("33333333E", "Manuel Garcia", new Integer(22)),
						new Person("44444444F", "Rafael Fernandez", new Integer(23)),
						new Person("66666666G", "Maria Sanzhez", new Integer(19)),
						new Person("55555555H", "Elena Casas", new Integer(19)),
						new Person("11111111I", "Ana Ramos", new Integer(22))};

		for (int i = 0; i < p.length; i++)
			pTS1.add(new PersonById(p[i]));
		System.out.println(pTS1.size() + " pTS1 = " + pTS1);

		pTS1.clear();
		for (int i = 0; i < p.length; i++)
			pTS1.add(new PersonByName(p[i]));
		System.out.println(pTS1.size() + " pTS1 = " + pTS1);

		pTS1.clear();
		for (int i = 0; i < p.length; i++)
			pTS1.add(new PersonByAge(p[i]));
		System.out.println(pTS1.size() + " pTS1 = " + pTS1);

		TreeSet pTS2 = new TreeSet();
		for (int i = 0; i < p.length; i++)
			pTS2.add(new PersonByAge(p[i]));
		System.out.println(pTS2.size() + " pTS2 = " + pTS2);

		Iterator it = pTS2.iterator();
		it.next();
		((Person)it.next()).age = 40;
		System.out.println(pTS2.size() + " pTS2 = " + pTS2);
		
		pTS2.add(new PersonByAge("12345678J", "Beatriz Lopez", new Integer(35)));
		System.out.println(pTS2.size() + " pTS2 = " + pTS2);
		for (int i = 0; i < p.length; i++)
			System.out.println(p[i] + " => " + pTS2.contains(new PersonByAge(p[i])));
		

		TreeSet pTS3 = new TreeSet(new PersonComparator());
		for (int i = 0; i < p.length; i++)
			pTS3.add(p[i]);
		pTS3.add(null);
		pTS3.add(null);
		System.out.println(pTS3.size() + " pTS3 = " + pTS3);
		
		TreeMap pTM = new TreeMap();
		pTM.put(new PersonByName("12345678J", "Jaime Perez", new Integer(21)), "Almeria");
		pTM.put(new PersonByName("87654321K", "Puri Serna", new Integer(22)), "Granada");
		pTM.put(new PersonByName("24681012L", "Maria Castelo", new Integer(21)), "Madrid");
		System.out.println(pTM.size() + " pTM = " + pTM);
		
		pTM.put(new PersonByName("24681012L", "Maria Castelo", new Integer(21)), "Sevilla");
		System.out.println(pTM.size() + " pTM = " + pTM);
		
		System.out.println(pTM.containsKey(new PersonByName("87654321K", "Puri Serna", new Integer(22))));
		System.out.println(pTM.containsKey(new PersonByName("87654321K", "Puri Sernas", new Integer(22))));
		System.out.println(pTM.containsValue("Madrid"));
		System.out.println(pTM.containsValue("Sevilla"));
		
		System.out.println(pTM.firstKey() + " " + pTM.lastKey());
		System.out.println(pTM.get(new PersonByName("87654321K", "Puri Serna", new Integer(22))));
		System.out.println(pTM.keySet());
		
		System.out.println(pTM.remove(new PersonByName("87654321K", "Puri Serna", new Integer(22))));
		System.out.println(pTM.size() + " pTM = " + pTM);
		
		System.out.println(pTM.size() + " pTM = " + pTM.entrySet());
		Iterator itr = pTM.entrySet().iterator();
		while (itr.hasNext()) {
			Map.Entry entry = (Map.Entry)itr.next();
			System.out.println (entry.getKey() + " " + entry.getValue());
		}

	}
}
