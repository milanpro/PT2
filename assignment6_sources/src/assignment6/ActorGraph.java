package assignment6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ActorGraph {
	public static GraphStruct a_graph;
    public static void main(String[] args ) {
        a_graph = readcsv();

		System.out.println( baconness( "Kevin Bacon" ) ); // 0
		System.out.println( baconness( "Tom Hanks" ) );   // 1
        System.out.println( baconness( "Liam Neeson" ) ); // 2

    }

	public static GraphStruct readcsv() {
        // TODO: read csv into graph-like structure

                String csvFile = ".\\assignment6_sources\\actors.csv";
                BufferedReader br = null;
                String line = "";
                String cvsSplitBy1 = ",";
                String cvsSplitBy2 = "\\|";

                try {
                    GraphStruct graph = new GraphStruct();
                    br = new BufferedReader(new FileReader(csvFile));
                    br.readLine();
                    while ((line = br.readLine()) != null) {
                        String[] movies = line.split(cvsSplitBy1);
                        if (movies.length == 3) {
                            String[] actors = movies[2].split(cvsSplitBy2);
                            graph.addConnectedVertList(actors);
                        }
                    }
                    graph.setBacon();
                    return graph;
                } catch (Exception e) {
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
        return null;
    }

    public static int baconness(String actor ) {
        if(a_graph.hasBacon()){
            if(actor.equals(a_graph.returnBacon().getName())){
                return 0;
            }
            else{
                TreeSet<String> alr_searched = new TreeSet<>();
                TreeSet<String> next_search = new TreeSet<>();
                next_search.add(a_graph.returnBacon().getName());
                return baconnesssearch(actor, next_search,alr_searched,0);
            }
        }
        else return -1;
    }

    public static int baconnesssearch(String actor,TreeSet<String> n_s, TreeSet<String> a_s, int baconness){
        baconness++;
        TreeSet<String> new_next = new TreeSet<>();
        for(String s_actor : n_s)
            if (a_graph.vertices.get(s_actor).Connections().contains(a_graph.getVert(actor))) return baconness;
            else {
                a_s.add(s_actor);
                for (Vertex new_actor : a_graph.vertices.get(s_actor).Connections())
                    if (!a_s.contains(new_actor.getName())) new_next.add(new_actor.getName());
            }
        return baconnesssearch(actor,new_next,a_s,baconness);
    }


}



class GraphStruct {
    public SortedMap<String, Vertex> vertices = new TreeMap<String, Vertex>();
    private Vertex Bacon;

    public Vertex getVert(String str) {
        return vertices.get(str);
    }

    public void addConnectedVertList(String[] vertices) {
        ArrayList<Vertex> verts = new ArrayList<>();
        Vertex temp;
        for (String vertex : vertices) {
            if (this.vertices.containsKey(vertex)) {
                verts.add(this.vertices.get(vertex));
            } else {
                temp = new Vertex(vertex);
                addVert(temp);
                verts.add(temp);
            }
        }
        for (Vertex ver1 : verts){
            for (Vertex ver2 : verts){
                if(ver1 != ver2){
                    if(!ver1.isConnected(ver2)) ver1.connectWith(ver2);
                }
            }
        }
    }

    public void addVert(Vertex v){
        vertices.put(v.getName(),v);
    }

    public Boolean hasBacon(){
        try{
            return vertices.containsValue(Bacon);
        }
        catch (Exception e){
            return false;
        }
    }

    public Vertex returnBacon(){
        return Bacon;
    }

    public void setBacon(){
        try{
            Bacon = vertices.get("Kevin Bacon");
        }
        catch (Exception e){
            System.out.println("Kevin Bacon not in graph");
        }
    }
}

class Vertex{
    private String name;
    private VertComparator comparator = new VertComparator();
    private TreeSet<Vertex> connected = new TreeSet<>(comparator);
    Vertex(String name){
        setName(name);
    }

    public void connectWith(Vertex v){
        this.connected.add(v);
    }

    public boolean isConnected(Vertex v){
        return connected.contains(v);
    }
    public TreeSet<Vertex> Connections() { return connected; }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }
}

class VertComparator implements Comparator <Vertex> {
    @Override
    public int compare(Vertex v1, Vertex v2) {
        return v1.getName().compareTo(v2.getName());
    }
}