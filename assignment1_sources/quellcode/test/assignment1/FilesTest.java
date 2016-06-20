package assignment1;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class FilesTest {

    @Test
    public void test() {
        try {
            assertEquals(79, Files.sumFiles("file1.txt"));
        } catch (FileNotFoundException e) {
            fail("Should read the file");
        } catch (IOException e) {
            fail("Should read the file");
        }

        try {
            assertEquals(80, Files.sumFiles2("file2.txt"));
        } catch (FileNotFoundException e) {
            fail("Should read the file");
        } catch (IOException e) {
            fail("Should read the file");
        }
    }
}
