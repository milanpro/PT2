package assignment5;

import java.util.ArrayList;

public class Archeology {
	
	public static void main(String[] args) {
        System.out.println("works");
        Shard[] shards = new Shard[17576];
        for(int i = 0; i < 26; i++){
            for(int j = 0; j < 26; j++){
                for(int k = 0; k < 26; k++){
                    String shardString = "" + (char) ('A' + i) + (char) ('A' + j) + (char) ('A' + k);
                    shards[i + 26 * j + 676 * k] = new Shard(shardString);
                }
            }
        }
        //Shard.resetSplitCount(friend);

        //hashCode();
        Shard.splitCount = 0;
        Archeology.findCombinations(shards);
        //Our implementation used 247416 splits
        System.out.println(Shard.getSplitCount());
	}
	public static ArrayList<WordPair> findCombinations(Shard[] shards){
        ArrayList<CustomDataStructure> shards1 = new ArrayList<>();
        ArrayList<CustomDataStructure> shards2 = new ArrayList<>();
        CustomDataStructure nShard;
        CustomDataStructure rShard;
        int ShardLength;
        for(Shard Sha: shards){
            ShardLength = Sha.length();
            shards1.add(new CustomDataStructure());
            shards2.add(new CustomDataStructure());
            for(int i=0;i < ShardLength;i++){
                shards1.get(shards1.size()-1).SetValue(Sha.getFirstChar());
                shards2.get(shards2.size()-1).SetValue(Sha.getFirstChar());
                Sha = Sha.getRightOfIncluding(1);
            }
        }
        int lengthofboth;
        int roundedlob;
        boolean reversable = true;
        boolean nulled = false;
        CustomDataStructure tempShard = null;
        ArrayList<WordPair> result = new ArrayList<>();
        for(int i = 0;i<shards1.size();i++){
            nShard = shards1.get(i);
            for (int j=0;j<shards2.size();j++){
                if(j!=i) {
                    rShard = shards2.get(j);
                    if(nShard.GetValue(0)!=null&&rShard.GetValue(0)!=null){
                        lengthofboth = nShard.length() + rShard.length();
                        nShard.SetLastNode(rShard);
                    }
                    else if(nShard.GetValue(0)==null){
                        lengthofboth = rShard.length();
                        tempShard = nShard;
                        nShard = rShard;
                        nulled = true;
                    }
                    else lengthofboth = nShard.length();
                    roundedlob = (lengthofboth % 2)==1 ? lengthofboth+1 : lengthofboth;
                    for (int k = 0; k < (roundedlob) / 2; k++) {
                        if (nShard.GetValue(k) != nShard.GetValue((lengthofboth-1) - k)) {
                            reversable = false;
                            break;
                        }
                    }
                    if(nulled){
                        nShard=tempShard;
                        nulled = false;
                    }
                    else if(nShard.GetValue(0)!=null&&rShard.GetValue(0)!=null)nShard.DetachNode(rShard);
                    if(reversable) {
                        result.add(new WordPair(i, j));
                    }
                    else reversable=true;
                }
            }
        }

		return result;
	}
}
