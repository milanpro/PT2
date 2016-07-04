package assignment5;

import java.util.ArrayList;

public class Archeology {

    public static void main(String[] args) {}
    public static ArrayList<WordPair> findCombinations(Shard[] shards){
        //Creating two times the same ArrayList, so I can manipulate both later without getting crazy
        ArrayList<CustomDataStructure> shards1 = new ArrayList<>();
        ArrayList<CustomDataStructure> shards2 = new ArrayList<>();
        int lengthofshard;
        for(Shard Sha: shards){
            lengthofshard = Sha.length();
            shards1.add(new CustomDataStructure());
            shards2.add(new CustomDataStructure());
            for(int i=0;i < lengthofshard;i++){
                shards1.get(shards1.size()-1).SetValue(Sha.getFirstChar());
                shards2.get(shards2.size()-1).SetValue(Sha.getFirstChar());
                Sha = Sha.getRightOfIncluding(1);
            }
        }
        return Combinate(shards1,shards2);
    }

    /*
    In this function the shards will get compared to find the anagrams.
     */
    private static ArrayList<WordPair> Combinate(ArrayList<CustomDataStructure> LeftShards, ArrayList<CustomDataStructure> RightShards) {

        CustomDataStructure LeftShard; //This will be the Left Part of those two Shards in my CustomDataStructure
        CustomDataStructure RightShard;

        int LengthOfBoth; //Length of both Structures combined, so i know how long i need to iterate

        boolean Reversable = true; // this gets set so the function knows if it is an Anagram
        boolean nulled = false; // if one of the Shards has no value (is null) this gets set, so the function can handle it
        CustomDataStructure tempShard = null; // to temporary save a shard
        ArrayList<WordPair> result = new ArrayList<>(); //Array List to save the result


        for(int i = 0;i<LeftShards.size();i++){ // iterating over every shard for the Left ones
            LeftShard = LeftShards.get(i);
            for (int j=0;j<RightShards.size();j++){ //iterating over every shard for the right ones
                if(j!=i) { // if shards are the same ignore
                    RightShard = RightShards.get(j);

                    if(LeftShard.GetValue(0)!=null&&RightShard.GetValue(0)!=null){//normal case, attaching the left and right shard
                        LengthOfBoth = LeftShard.length() + RightShard.length();
                        LeftShard.SetLastNode(RightShard);
                    }


                    else if(LeftShard.GetValue(0)==null){ // if one of the Shards is null
                        LengthOfBoth = RightShard.length();
                        tempShard = LeftShard;
                        LeftShard = RightShard;
                        nulled = true;
                    }
                    else LengthOfBoth = LeftShard.length();

                    // iterating over half of the shard (more is not necessary)
                    for (int k = 0; k < (GetNextEven(LengthOfBoth) / 2); k++) {
                        if (LeftShard.GetValue(k) != LeftShard.GetValue((LengthOfBoth-1) - k)) { //comparing
                            Reversable = false;
                            break;
                        }
                    }

                    //if one Shard was null
                    if(nulled){
                        LeftShard=tempShard;
                        nulled = false;
                    }
                    else if(LeftShard.GetValue(0)!=null&&RightShard.GetValue(0)!=null)LeftShard.DetachNode(RightShard);

                    //if those shards are an anagram together
                    if(Reversable) {
                        result.add(new WordPair(i, j));
                    }
                    else Reversable=true;
                }
            }
        }
        return result; // Our Split Count is 52728
    }

    // Gets the Next Even Number if x is Odd
    private static int GetNextEven(int x){
        return (x % 2)==1 ? x+1 : x;
    }
}
