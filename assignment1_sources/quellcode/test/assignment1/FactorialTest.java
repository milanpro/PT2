package assignment1;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FactorialTest {
    @Test
    public void test() {

        assertEquals("1", Factorial.fact(1));
        assertEquals("5040", Factorial.fact(7));
        assertEquals(
                "93326215443944152681699238856266700490715968264381621468592963895217599993229915608941463976156518286253697920827223758251185210916864000000000000000000000000",
                Factorial.fact(100));
    }
}
