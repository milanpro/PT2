package assignment1;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TriangularNumberTest {
    @Test
    public void test() {
        assertEquals("1", TriangularNumber.hex(1));
        assertEquals("1c", TriangularNumber.hex(7));
        assertEquals("13ba", TriangularNumber.hex(100));
    }
}
