package assignment6;

public class ActorGraph {
	
    public static void main( String[] args ) {
        readcsv();
		System.out.println( baconness( "Kevin Bacon" ) ); // 0
		System.out.println( baconness( "Tom Hanks" ) );   // 1
		System.out.println( baconness( "Liam Neeson" ) ); // 2
    }
	
	public static void readcsv() {
        // TODO: read csv into graph-like structure
    }

    public static int baconness( String actor ) {
		// TODO: return distance between "Kevin Bacon" and actor
        return 0;
    }
}
