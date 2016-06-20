package assignment1;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SbahnTest {

    @Test
    public void test() {
        assertEquals(1, Sbahn.numVerbindungen(143, 143));
        assertEquals(0, Sbahn.numVerbindungen(144, 144));
        assertEquals(1, Sbahn.numVerbindungen(141, 141));
    }

}
