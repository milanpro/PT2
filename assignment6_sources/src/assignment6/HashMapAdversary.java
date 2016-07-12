package assignment6;

import java.util.ArrayList;

public class HashMapAdversary {
	
	public static ArrayList<Particle> generateWorstCase(int keyCount){
		if(keyCount<= 0) return null;
		ArrayList<Particle> SameHash = new ArrayList<Particle>();
		for(int i = 0; i < keyCount; i++){
			int y = 317-i;
			Particle in = new Particle(i, y);
			SameHash.add(i,in);
		}
		return SameHash;
	}
}
