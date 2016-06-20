package assignment3;

public class Rocket {

    public static final int LOWER_BOUND = 1;
    public static final int UPPER_BOUND = 1000;
    private int payload;
    private int tries = 0;

    public Rocket() {
        payload = (int) (LOWER_BOUND + Math.random() * (UPPER_BOUND - LOWER_BOUND));
        System.out.println("payload:" + payload);
    }

    public Rocket(int payload) throws IllegalArgumentException {
        if (payload < LOWER_BOUND || payload > UPPER_BOUND) {
            throw new IllegalArgumentException();
        }

        this.payload = payload;
    }

    public boolean canBear(int payload) {
        tries++;
        if (!(this.payload >= payload)) {
        }
        return this.payload >= payload;
    }

    public int getTries() {
        return tries;
    }

}
