package assignment6;

import static org.junit.Assert.*;

import org.junit.Test;

import assignment6.JavaMDHashFunction;



public class StringBloomfilterTest {
	
	@Test
	public void testNoHitInEmpty() {
		StringBloomfilter testFilter = new StringBloomfilter(10,JavaMDHashFunction.MD5,JavaMDHashFunction.SHA1,JavaMDHashFunction.SHA256);
		assertFalse(testFilter.mightContain("test"));
	}
	
	@Test
	public void testExistingElement() {
		StringBloomfilter testFilter = new StringBloomfilter(10,JavaMDHashFunction.MD5,JavaMDHashFunction.SHA1,JavaMDHashFunction.SHA256);
		
		testFilter.add("Hallo");
		assertTrue(testFilter.mightContain("Hallo"));
		assertTrue(testFilter.mightContain("339a2d0b-faf4-498a-9577-3be154cc4d6e"));
		assertFalse(testFilter.mightContain("Narf"));
		testFilter.add("Narf");
		assertTrue(testFilter.mightContain("Narf"));
	}
	
	@Test
	public void testElementAdding() {
		StringBloomfilter testFilter = new StringBloomfilter(10,JavaMDHashFunction.MD5,JavaMDHashFunction.SHA1,JavaMDHashFunction.SHA256);
		
		assertEquals(0,testFilter.elementCount());
		testFilter.add("Halloooooo");
		assertEquals(1,testFilter.elementCount());
		testFilter.add("WEEEELT");
		assertEquals(2,testFilter.elementCount());
	}
}
