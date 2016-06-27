package assignment5;

import java.util.ArrayList;

public class Archeology {
	
	public static void main(String[] args) {
		System.out.println("You open the door. Once the dust settles, you can make out shards "
				+ "filled with ancient knowledge. However, the air is still filled with the might of "
				+ "shaman rituals. Danger looms. Do you dare to go forward?");
	}
	public static ArrayList<WordPair> findCombinations(Shard[] shards){
        ArrayList<CustomDataStructure> normalshards = new ArrayList<>();
        ArrayList<CustomDataStructure> reversedshards = new ArrayList<>();
        Shard rev;
        CustomDataStructure nShard = new CustomDataStructure();
        CustomDataStructure rShard = new CustomDataStructure();
        for(Shard Sha: shards){
            rev = Sha.reverseShard();
            for(int i=0;i < Sha.length();i++){
                nShard.SetValue(Sha.getFirstChar());
                rShard.SetValue(rev.getFirstChar());
            }
            normalshards.add(nShard);
            reversedshards.add(rShard);
        }


		return new ArrayList<WordPair>();
	}
}
