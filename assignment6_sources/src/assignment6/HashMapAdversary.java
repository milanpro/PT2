package assignment6;

import java.util.ArrayList;

public class HashMapAdversary {
	
	public static ArrayList<Particle> generateWorstCase(int keyCount){
		if(keyCount<= 0) return null;

		ArrayList<Particle> SameHash = new ArrayList<Particle>(); //neue Array List

		for(int i = 0; i < keyCount; i++){
			int y = 317-i; //da Hashfunktion (x+y)%317
			Particle in = new Particle(i, y); //neues Particle erzeugen
			SameHash.add(i,in); //Particle in SameHash einfügen
		}
		return SameHash; //Hashmap zurückgeben
	}
}
