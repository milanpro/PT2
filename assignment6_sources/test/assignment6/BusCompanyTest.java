package assignment6;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

public class BusCompanyTest {
    @Test
    public void testSmallSample() {
        Collection<Route> routes = Arrays.asList(
                new Route("Lens", "Lille", 35),
                new Route("Saint-Denis", "Lens", 190),
                new Route("Saint-Denis", "Lille", 211),
                new Route("Saint-Denis", "Paris", 10),
                new Route("Paris", "Bordeaux", 580),
                new Route("Paris", "Toulouse", 675),
                new Route("Paris", "Marseille", 714),
                new Route("Paris", "Saint-Étienne", 482),
                new Route("Paris", "Lyon", 463),
                new Route("Saint-Étienne", "Lyon", 63),
                new Route("Lyon", "Nizza", 472),
                new Route("Lyon", "Marseille", 316),
                new Route("Saint-Étienne", "Nizza", 489),
                new Route("Saint-Étienne", "Marseille", 332),
                new Route("Marseille", "Nizza", 197),
                new Route("Marseille", "Toulouse", 405),
                new Route("Toulouse", "Bordeaux", 243),
                new Route("Bordeaux", "Saint-Étienne", 511),
                new Route("Toulouse", "Saint-Étienne", 557),
                new Route("Bordeaux", "Marseille", 647),
                new Route("Toulouse", "Nizza", 561)
        );
        assertEquals(1922, BusCompany.minimumTaxableKilometers(10, routes));
    }
}
