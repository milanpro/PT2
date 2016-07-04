package assignment6;

/**
 * Represents a single route between two cities that can be travelled both ways.
 */
public class Route {
    private String city1;
    private String city2;
    private int taxableKilometers;

    public Route(String city1, String city2, int taxableKilometers) {
        this.city1 = city1;
        this.city2 = city2;
        this.taxableKilometers = taxableKilometers;
    }

    /**
     * The first city.
     */
    public String getCity1() {
        return city1;
    }

    /**
     * The second city.
     */
    public String getCity2() {
        return city2;
    }

    /**
     * How many kilometres of the route are using autoroutes.
     */
    public int getTaxableKilometers() {
        return taxableKilometers;
    }
}
