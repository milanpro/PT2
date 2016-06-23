package assignment5;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class ArcheologyTest {
	public static final class Friend { private Friend() {}}
	public static final Friend friend = new Friend();
	
	public static boolean isSolution(ArrayList<WordPair> result, int[][] solution){
		boolean allIn = true;
		for(int[] pair : solution){
			allIn &= result.contains(new WordPair(pair[0], pair[1]));
		}
		return (allIn && (result.size() == solution.length));
	}
	
	@Test
	public void testWithEmpty() {
		Shard[] shards = {
				new Shard(""), 
				new Shard("T"), 
				new Shard("ET"), 
				new Shard("ABA")
		};
		int[][] solution = {
				{0, 1},
				{1, 0},
				{0, 3},
				{3, 0},
				{1, 2},
		};
		ArrayList<WordPair> result = Archeology.findCombinations(shards);
		assertTrue(isSolution(result, solution));
	}
	
	@Test
	public void testWithoutEmpty() {
		Shard[] shards = {
				new Shard("T"), 
				new Shard("ET"), 
				new Shard("ABA")
		};
		int[][] solution = {
				{0, 1},
		};
		ArrayList<WordPair> result = Archeology.findCombinations(shards);
		assertTrue(isSolution(result, solution));
	}
	
	@Test
	public void testSomeWords() {
		Shard[] shards = {
				new Shard("ABC"), 
				new Shard("BA"), 
				new Shard("AB")
		};
		int[][] solution = {
				{0, 1},
				{1, 2},
				{2, 1},
		};
		ArrayList<WordPair> result = Archeology.findCombinations(shards);
		assertTrue(isSolution(result, solution));
	}
	
	@Test
	public void testLowSplitCount() {
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
		hashCode();
        Shard.splitCount = 0;
		Archeology.findCombinations(shards);
		//Our implementation used 247416 splits
		assertTrue(Shard.getSplitCount() <= 500000);
	}
}
