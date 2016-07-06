package assignment6;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.TreeSet;

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
                String line;
                String cvsSplitBy1 = ",";
                String cvsSplitBy2 = "\\|";
                GraphStruct graph = new GraphStruct();
                try {
                    int counter = 0;
                    br = new BufferedReader(new FileReader(csvFile));
                    br.readLine();
                    while ((line = br.readLine()) != null) {
                        String[] movie = line.split(cvsSplitBy1);
                        String[] actors = movie[2].split(cvsSplitBy2);
                        Vertex vert = new Vertex(Integer.parseInt(movie[0]),movie[1]);
                        // use comma as separator
                        for(int i = 0; i<actors.length;i++){
                            vert.addEdge(new Edge(actors[i]));
                            }
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

    public static int baconness( String actor ) {
		// TODO: return distance between "Kevin Bacon" and actor
        return 0;
    }
}

class GraphStruct{
    final private Comparator<Vertex> comparator2 = new Comparator<Vertex>() {
        @Override
        public int compare(Vertex v1, Vertex v2) {
            if (v1.size() > v2.size()) {
                return -1;
            }
            else if (v1.size() < v2.size()) {
                return 1;
            }
            else return 0;

        }
    };
    private TreeSet<Vertex> vertices= new TreeSet<>(comparator2);

    public void addVertex(Vertex vertex) {
        this.vertices.add(vertex);
    }
}

class Vertex{
    private int id;
    private String name;
    final private Comparator<Edge> comparator = (e1, e2) -> e1.getEdge().compareTo(e2.getEdge());
    final private TreeSet<Edge> edges = new TreeSet<>(comparator);
    Vertex(int id, String name){
        this.setId(id);
        this.setName(name);
    }

    public void addEdge(Edge edge){
        this.edges.add(edge);
    }

    public boolean hasEdge(Edge edge){
        return edges.contains(edge);
    }

    // getter / setter
    public String getName() {
        return name;
    }

    public int size() { return edges.size(); }

    private void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }
}

class Edge{
    private String edge;
    private int bacon;
    Edge(String v){
        setEdge(v);
    }

    public boolean equ(Edge e) { return this.getEdge().equals(e.getEdge()); }

    // getter/ setter
    public String getEdge() {
        return edge;
    }

    private void setEdge(String edge) {
        this.edge = edge;
    }

    public int getBacon() {
        return bacon;
    }

    public void setBacon(int bacon) {
        this.bacon = bacon;
    }
}