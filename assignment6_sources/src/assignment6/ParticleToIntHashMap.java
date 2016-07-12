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
			if(key == null) throw new NullPointerException();
			this.k = key;
		}
	}

	private LinkedList<PartyParticle> [] hashmap;
	private HashFunction<Particle> f;
	private int s; //size

	public ParticleToIntHashMap(int size, HashFunction<Particle> hashFunction){
		if(hashFunction == null) throw new NullPointerException();
		hashmap = new LinkedList[size];
		f = hashFunction;
		if(size > 0){
			s = size;
		}
		else{throw new IllegalArgumentException();}

	}

	public void put(Particle key, int value){
		if(key == null) throw new NullPointerException();
		else{
			int finalhash = f.hash(key)%s;
			finalhash = finalhash < 0 ? finalhash + s : finalhash;
			//System.out.println(finalhash+"="+f.hash(key)+"%"+s+" x="+key.getxPosition()+" y="+key.getyPosition());
			if(hashmap[finalhash] == null){
				hashmap[finalhash] = new LinkedList<>();
			}
			hashmap[finalhash].add(new PartyParticle(value, key));
		}
	}


	public Integer get(Particle key) {
		if (key == null) throw new NullPointerException();
		else {
			int finalhash = f.hash(key) % s;
			LinkedList<PartyParticle> party;
			party = hashmap[finalhash];
			int i = 0;
			while (true) {
				if (party==null||party.size()<=i) {
					return null;
				} else {
					if (party.get(i).getKey().equals(key)) {
						return party.get(i).getValue();
					} else {
						i++;
					}
				}

			}
		}
	}

	public int largestBucketSize(){ //Bucket mit den meisten PartyParticeln --> davon Anzahl
		int max = 0;
		for (int j = 0; j < s; j++){
			if(hashmap[j] != null){
				if(!hashmap[j].isEmpty()){   //TODO braucht es das
					if(max < hashmap[j].size()){
						max = hashmap[j].size();
					}
				}}
		}
		return max;
	}
}
