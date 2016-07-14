package assignment6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ActorGraph {
	public static GraphStruct a_graph;
    public static void main(String[] args ) {
        readcsv();

		System.out.println( baconness( "Kevin Bacon" ) ); // 0
		System.out.println( baconness( "Tom Hanks" ) );   // 1
        System.out.println( baconness( "Liam Neeson" ) ); // 2

    }

	public static void readcsv() {
        // TODO: read csv into graph-like structure

                String csvFile = ".\\assignment6_sources\\actors.csv";
                BufferedReader br = null;
                String line = "";
                String cvsSplitBy1 = ","; //erster split bei ,
                String cvsSplitBy2 = "\\|"; //zweiter split bei |

                try {
                    GraphStruct graph = new GraphStruct(); //graph erstellen
                    br = new BufferedReader(new FileReader(csvFile));
                    br.readLine(); //erste Zeile ignorieren
                    while ((line = br.readLine()) != null) { // alle Zeilen einlesen
                        String[] movies = line.split(cvsSplitBy1);  //split 1
                        if (movies.length > 2) { // es gibt actors
                            String[] actors = movies[movies.length-1].split(cvsSplitBy2); //falls im movietitle ein , war => letzter index muss actor sein
                            graph.addConnectedVertList(actors); //methode zum einfügen der actors
                        }
                    }
                    graph.setBacon(); // methode zum finden des Vertex von Bacon und speichern eines pointers zu diesem
                    a_graph = graph; // speichern des graphes
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
    }

    public static int baconness(String actor ) {
        if(a_graph.hasBacon()){ // wenn Bacon im graph
            if(actor.equals(a_graph.returnBacon().getName())){
                return 0; // falls nach Bacon gesucht wird
            }
            else{
                TreeSet<String> alr_searched = new TreeSet<>(); //bereits verglichene actors
                TreeSet<String> next_search = new TreeSet<>(); // nächste Suchiteration (mit jeder steigt die baconness)
                next_search.add(a_graph.returnBacon().getName()); // erste suche beginnt mit Bacon
                return baconnesssearch(actor, next_search,alr_searched,0); // rekursiv im graphen suchen
            }
        }
        else throw new IllegalStateException("Kevin Bacon ist nicht im Graph");
    }

    public static int baconnesssearch(String actor,TreeSet<String> n_s, TreeSet<String> a_s, int baconness){
        baconness++;
        TreeSet<String> new_next = new TreeSet<>();
        for(String s_actor : n_s) // iteration über alle zu suchenden actors
            if (a_graph.vertices.get(s_actor).Connections().contains(a_graph.getVert(actor))) return baconness; //falls actor gefunden
            else {
                a_s.add(s_actor); // schiebe schauspieler von next zu already searched
                for (Vertex new_actor : a_graph.vertices.get(s_actor).Connections()) //falls nicht schon in einer liste, füge alle verbundenen actors zur neuen next liste
                    if (!a_s.contains(new_actor.getName())&&!n_s.contains(new_actor.getName())&&!new_next.contains(new_actor.getName())) new_next.add(new_actor.getName());
            }
        return baconnesssearch(actor,new_next,a_s,baconness); // rekursiver aufruf, falls noch nicht gefunden
    }


}



class GraphStruct {
    public SortedMap<String, Vertex> vertices = new TreeMap<String, Vertex>(); //sortedMap um über den String schnell den entsprechenden Vertex zu finden
    private Vertex Bacon; // pointer auf Bacon Vertex

    public Vertex getVert(String str) {
        return vertices.get(str);
    }

    public void addConnectedVertList(String[] vertices) { // hinzufügen einer liste an actors zum graphen
        ArrayList<Vertex> verts = new ArrayList<>(); // liste der Vertices der actors aus der liste um sie später zu connecten
        Vertex temp;
        for (String vertex : vertices) {
            if (this.vertices.containsKey(vertex)) { // falls actor schon vorhanden
                verts.add(this.vertices.get(vertex));
            } else {
                temp = new Vertex(vertex); //sonst erstelle neuen Vertex
                addVert(temp); // füge neuen Vertex hinzu
                verts.add(temp);
            }
        }
        for (Vertex ver1 : verts){ // verbinde alle vertices über den Film
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

class Vertex implements Comparable{ // Vertex oder actor
    private String name; // name des actors
    private TreeSet<Vertex> connected = new TreeSet<>(); // sortierte liste aller verbundenen actors mit diesem actor
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

    @Override
    public int compareTo(Object o) {
        if(o instanceof Vertex)
        return this.getName().compareTo(((Vertex)o).getName());
        else throw new IllegalArgumentException();
    }
}
