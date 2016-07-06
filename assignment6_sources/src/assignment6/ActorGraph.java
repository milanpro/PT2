package assignment6;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ActorGraph {
	
    public static void main( String[] args ) {
        readcsv();
        /*
		System.out.println( baconness( "Kevin Bacon" ) ); // 0
		System.out.println( baconness( "Tom Hanks" ) );   // 1
		System.out.println( baconness( "Liam Neeson" ) ); // 2
        */
    }
	
	public static void readcsv() {
        // TODO: read csv into graph-like structure

                String csvFile = ".\\assignment6_sources\\actors.csv";
                BufferedReader br = null;
                String line = "";
                String cvsSplitBy1 = ",";
                String cvsSplitBy2 = "\\|";

                try {
                    int counter = 0;
                    br = new BufferedReader(new FileReader(csvFile));
                    br.readLine();
                    while ((line = br.readLine()) != null) {

                        // use comma as separator
                        String[] movies = line.split(cvsSplitBy1);
                        String[] actors = movies[2].split(cvsSplitBy2);
                        System.out.print("MovieID= " + movies[0]
                                + " , MovieName=" + movies[1] + " Actors: ");
                        for(int i = 0; i<actors.length;i++){
                            if(i==actors.length-1)System.out.println(" "+actors[i]);
                            else System.out.print(", "+actors[i]);}
                        counter++;
                        if(counter==10) break;

                    }

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (br != null) {
                        try {
                            br.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                System.out.println("Done");



    }
    /*
    public static int baconness( String actor ) {
		// TODO: return distance between "Kevin Bacon" and actor
        return 0;
    }
    */
}
