package assignment4;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MessageReaderHiddenTest {

    @Test
    public void testEmpty() {
        assertEquals(MessageReader.findMessage("", ""), "");
        assertEquals(MessageReader.findMessage("", "HALLOWELT"), "");
        assertEquals(MessageReader.findMessage("HALLOWELT", ""), "");
    }

    @Test
    public void testSame() {
        assertEquals(MessageReader.findMessage("HALLOWELT", "HALLOWELT"), "HALLOWELT");
    }

    @Test
    public void testDisjoint() {
        assertEquals(MessageReader.findMessage("ABC", "DEF"), "");
    }
}
