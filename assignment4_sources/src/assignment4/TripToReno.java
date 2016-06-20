package assignment4;

/**
 * @author Annika Baldi
 * @author Milan Proell
 */


public class TripToReno {

    public static void main(String[] args) {
        int[] tankstellen = {34, 37, 19};
        int[] ret = TripToReno.getStops(50, 100, tankstellen);
        System.out.println(ret[1]);
    }

    /**
     * public static int[] getStops(int d, int z, int[] t) {
     * int[] stops = new int[t.length];
     * double voll = d; //Tank voll; Annahme er fährt immer mit vollem Tank los
     * int strecke = 0; //Bereits zurückgelegte Strecke
     * for(int i= 0; i<t.length; i++){
     * if(strecke>=z) break; //Angekommen oder nie los gefahren
     * voll = voll-t[i];
     * strecke = strecke+t[i];
     * if(voll<t[i+1]){ //Kommt nicht mehr bis zur nöchsten Tanke
     * //stops.add(i,i+1); //Tankt
     * stops[i]=i;
     * voll = d; //Tank voll
     * }
     * }
     * //int[] arr = stops.stream().mapToInt(i -> i).toArray();
     * return stops;
     * }
     */
    public static int[] getStops(int d, int z, int[] t) {
        int tank = 0;
        int[] tanken = new int[t.length];
        int index = 0;
        int strecke = 0;
        for (int i = 0; i < t.length; i++) {
            if (strecke < z) {
                tank = tank + t[i];
                strecke = strecke + t[i];
                if (tank == d) {
                    tanken[index] = i;
                    index++;
                    tank = 0;
                } else if (tank > d) {
                    tanken[index] = i - 1;
                    strecke = strecke - t[i];
                    i--;
                    index++;
                    tank = 0;
                }
            } else {
                int[] stops = new int[index];
                for (int j = 0; j < index; j++) {
                    stops[j] = tanken[j];
                }
                return stops;
            }
        }
        int[] stops = new int[index];
        for (int j = 0; j < index; j++) {
            stops[j] = tanken[j];
        }
        return stops;
    }
    /*
     * Time Complexity of int[] getStops(int d, int z, int[] t):
	 * 
	 * // TODO
	 */

    public static int timeCompare(int kmhProf, int kmhBike, int d, int z, int[] t) {
        double timebike = kmhBike / z;
        double timeprof = kmhProf / z;
        int[] stops = getStops(d, z, t);
        timeprof = timeprof + (stops.length * 0.25);
        return (int) ((timebike - timeprof) / 60);
    }

}