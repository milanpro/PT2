package assignment6;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class ParticleToIntHashMap {

	private class PartyParticle{ //neues Objekt, was sowohl key, als auch value speichert
		private Integer v; //Wert
		private Particle k; //key

		public PartyParticle(){
			this(0, null);
		}

		public PartyParticle(Integer value, Particle key){
			this.v = value;
			this.k = key;
		}
		public Integer getValue(){
			return v;
		}

		public Particle getKey(){
			return k;
		}

		public void setValue(Integer value){
			this.v = value;
		}

		public void setKey(Particle key){
			if(key == null) throw new NullPointerException(); //key darf nicht null sein
			this.k = key;
		}
	}

	private LinkedList<PartyParticle> [] hashmap;
	private HashFunction<Particle> f;
	private int s; //size

	public ParticleToIntHashMap(int size, HashFunction<Particle> hashFunction){
		if(hashFunction == null) throw new NullPointerException(); //hashFunction darf nicht null sein
		hashmap = new LinkedList[size];
		f = hashFunction;
		if(size > 0){ //size muss größer 0 sein
			s = size;
		}
		else{throw new IllegalArgumentException();}

	}

	public void put(Particle key, int value){ //einfügen in Hashmap
		if(key == null) throw new NullPointerException(); //falls key null
		else{
			int finalhash = f.hash(key)%s; //%s um nur Hashwerte zu bekommen, die Index der Hashmap sind
			finalhash = finalhash < 0 ? finalhash + s : finalhash; //keine negativen Hashwerte
			if(hashmap[finalhash] == null){ //noch keine Linked Lists im Array
				hashmap[finalhash] = new LinkedList<>();
			}
			hashmap[finalhash].add(new PartyParticle(value, key)); //neues key, value Paar hinzufügen zur Linked List
		}
	}


	public Integer get(Particle key) {
		if (key == null) throw new NullPointerException(); //falls key null
		else {
			int finalhash = f.hash(key) % s; //finalhash berechnen
			LinkedList<PartyParticle> party;
			party = hashmap[finalhash]; //LinkedList an Stelle finalhash in "party" speichern
			int i = 0;
			while (true) {
				if (party==null||party.size()<=i) {
					return null; //key gibt es nicht
				} else {
					if (party.get(i).getKey().equals(key)) { //falls keys gleich
						return party.get(i).getValue(); //Wert zurückgeben
					} else {
						i++;
					}
				}

			}
		}
	}

	public int largestBucketSize(){ //Bucket mit den meisten PartyParticeln --> davon Anzahl
		int max = 0; //Maximalwert
		for (int j = 0; j < s; j++){
			if(hashmap[j] != null){
				if(!hashmap[j].isEmpty()){ //falls LinkedList existent und nicht leer
					if(max < hashmap[j].size()){ //falls max kleiner als size der aktuellen LinkedList
						max = hashmap[j].size(); //max setzen
					}
				}}
		}
		return max;
	}
}
