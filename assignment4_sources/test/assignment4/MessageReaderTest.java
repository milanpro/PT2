package assignment4;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MessageReaderTest {

    @Test
    public void testGivenExamples() {
        assertEquals(MessageReader.findMessage("XHAYLLYIOWZIEZLT", "QQHALKKLOWJJELNT"), "HALLOWELT");
        assertEquals(MessageReader.findMessage("FFOOOOBBAARR", "FOOBAR"), "FOOBAR");
        assertEquals(MessageReader.findMessage("BANANENEIS", "ANANAS"), "ANANS");
    }
}
