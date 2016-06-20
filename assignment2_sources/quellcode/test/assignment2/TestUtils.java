package assignment2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TestUtils {

    public int[] readIntArrayResource(String resource) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(
                TestUtils.class.getResourceAsStream(resource)))) {

            String[] numberStings = in.readLine().split(",");
            int[] result = new int[numberStings.length];

            for (int i = 0; i < numberStings.length; i++) {
                result[i] = Integer.valueOf(numberStings[i]);
            }

            return result;
        } catch (IOException e) {
            throw new IllegalStateException("Unable to read " + resource, e);
        }
    }
}
