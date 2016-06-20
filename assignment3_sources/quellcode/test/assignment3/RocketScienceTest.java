package assignment3;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RocketScienceTest {

    @Test
    public void testFewestFailures() {
        int actualPayload = (int) (Rocket.LOWER_BOUND + Math.random() * (Rocket.UPPER_BOUND - Rocket.LOWER_BOUND));

        Rocket rocket = new Rocket(actualPayload);
        int deducedPayload = RocketScience.fewestFailures(rocket);

        assertEquals(actualPayload, deducedPayload);
        assertTrue(rocket.getTries() == actualPayload);
    }

    @Test
    public void testFewestAttemptsLogN() {
        int actualPayload = 666;

        Rocket rocket = new Rocket(actualPayload);
        int deducedPayload = RocketScience.fewestAttemptsLogN(rocket);

        assertEquals(actualPayload, deducedPayload);
        assertTrue(rocket.getTries() <= 10);
    }

    @Test
    public void testFewestAttempstLogK() {
        int actualPayload = 666;

        Rocket rocket = new Rocket(actualPayload);
        int deducedPayload = RocketScience.fewestAttemptsLogK(rocket);

        assertEquals(actualPayload, deducedPayload);
        assertTrue(rocket.getTries() <= 19);
    }


    @Test
    public void testFewestAttemptsTwoRockets() {
        int actualPayload = 500;

        Rocket rocket = new Rocket(actualPayload);
        int deducedPayload = RocketScience.fewestAttemptsTwoRockets(rocket);

        assertEquals(actualPayload, deducedPayload);
        assertTrue(rocket.getTries() <= 36);
    }

}
