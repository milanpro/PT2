package assignment1;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OperatorsTest {
    @Test
    public void test() {

        assertEquals(Operators.addValuesIntInt(6, 660), 666);
        assertEquals(Operators.addValuesIntString(6, "660"), 666);
        assertEquals(Operators.addValuesStringStringtoInt("6", "660"), 666);
        assertEquals(Operators.addValuesStringStringtoString("6", "660"), "666");
        assertEquals(Operators.concatValuesStringInttoString("606", 6), "6066");
        assertEquals(Operators.concatValuesIntInttoString(6, 606), "6606");
        assertEquals(Operators.concatValuesIntInttoInt(6, 606), 6606);
    }
}
