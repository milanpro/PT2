package assignment6;

import java.util.Arrays;
import java.util.Collection;

public class BusCompany {
    public static void main(String[] args) {
        Collection<Route> routes = Arrays.asList(
                new Route("Paris", "Saint-Denis", 10),
                new Route("Saint-Denis", "Lens", 190)
        );
        System.out.println(minimumTaxableKilometers(3, routes));
    }

    public static int minimumTaxableKilometers(int cityCount, Collection<Route> routes) {
        // TODO: Implement

        return 0;
    }
}
