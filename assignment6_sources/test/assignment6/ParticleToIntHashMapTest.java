package assignment6;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Random;

import org.junit.Test;

public class ParticleToIntHashMapTest {

	@Test
	public void testMapAccess(){
		ParticleToIntHashMap map = new ParticleToIntHashMap(10, new ParticleHashFunction());
		map.put(new Particle(1, 5), 314);
		map.put(new Particle(100, 2), 1414);
		assertEquals(map.get(new Particle(1, 5)), new Integer(314));
		assertEquals(map.get(new Particle(100, 2)), new Integer(1414));
		assertEquals(map.get(new Particle(101, 2)), null);
	}
	
	@Test
	public void testMaxBucketSize(){
		ParticleToIntHashMap map = new ParticleToIntHashMap(1, new ParticleHashFunction());
		assertEquals(map.largestBucketSize(), 0);
		for(int i = 0; i < 100; i++){
			map.put(new Particle(i, 0), 0);
			assertEquals(i + 1, map.largestBucketSize());
		}
		map = new ParticleToIntHashMap(2, new ParticleHashFunction());
		for(int i = 0; i < 100; i++){
			map.put(new Particle(i, 0), 0);
		}
		assertTrue(map.largestBucketSize() >= 50);
	}
	
	@Test
	public void testGoodHashFunction(){
		ParticleToIntHashMap map = new ParticleToIntHashMap(20, new ParticleHashFunction());
		for(int i = 0; i < 10000; i++){
			map.put(new Particle(i / 100, i % 100), i);
		}
		assertTrue(map.largestBucketSize() >= 100);
		assertTrue(map.largestBucketSize() < 570);
		
		//Random testing is bad practice! However I want my test to be unpredictable by the students.
		map = new ParticleToIntHashMap(20, new ParticleHashFunction());
		Random random = new Random();
		for(int i = 0; i < 10000; i++){
			map.put(new Particle(random.nextInt(), random.nextInt()), i);
		}
		assertTrue(map.largestBucketSize() < 570);
	}
	
	@Test
	public void testWorstCase(){
		ParticleToIntHashMap map = new ParticleToIntHashMap(1000, new ParticleHashFunction());
		ArrayList<Particle> badKeys = HashMapAdversary.generateWorstCase(1000);
		for(Particle key : badKeys){
			map.put(key, 0);
		}
		assertEquals(map.largestBucketSize(), 1000);
	}
	
	@Test
	public void testWorstCaseWithOtherHash(){
		ParticleToIntHashMap map = new ParticleToIntHashMap(1000, new OtherParticleHashFunction());
		ArrayList<Particle> badKeys = HashMapAdversary.generateWorstCase(1000);
		for(Particle key : badKeys){
			map.put(key, 0);
		}
		assertTrue(map.largestBucketSize() >= 1);
		assertTrue(map.largestBucketSize() < 570);
	}
}
