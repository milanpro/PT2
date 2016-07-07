package assignment6;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Stream;

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
                    int counter = 0;
                    br = new BufferedReader(new FileReader(csvFile));
                    br.readLine();
                    while ((line = br.readLine()) != null) {

                        // use comma as separator
                        String[] movies = line.split(cvsSplitBy1);
                        if (movies.length == 3) {
                            String[] actors = movies[2].split(cvsSplitBy2);
                            //System.out.print("MovieID= " + movies[0]
                            //        + " , MovieName=" + movies[1] + " Actors: ");
                            graph.addConnectedVertList(actors);
                        }
                    }
                    graph.setBacon();
                    graph.sortgraph();
                    return graph;
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
        return null;
    }

    public static int baconness(String actor ) {
        if(a_graph.hasBacon()){
            if(actor == a_graph.returnBacon().getName()){
                return a_graph.returnBacon().getBaconness();
            }
            else{
                return baconnesssearch(actor);
            }
        }
        else return -1;
    }

    public static int baconnesssearch(String actor){
        int thisbaconness = 1;
                for (Map.Entry<String, Vertex> entry : a_graph.vertices.entrySet()) {
                    if (a_graph.getBaconnessingraph() >= thisbaconness) {
                        if (entry.getValue().getName() == actor) return entry.getValue().getBaconness();
                        if (entry.getValue().getBaconness()!=thisbaconness)thisbaconness = entry.getValue().getBaconness();
                    } else {
                        if (entry.getValue().getBaconness() == thisbaconness) {
                            a_graph.setBaconnessingraph(thisbaconness);
                            thisbaconness++;
                            System.out.println(thisbaconness - 1);
                        }
                        if (entry.getValue().getBaconness() == thisbaconness - 1) {
                            for (Vertex newconn : entry.getValue().Connections()) {
                                if (newconn.getName() == actor) {
                                    return thisbaconness;
                                }
                                if (newconn.getBaconness() == -1){
                                    newconn.setBaconness(thisbaconness);
                                    a_graph.sortgraph();
                                }
                            }
                        }
                    }
                    //baconness ab hier generieren
                }
                return -1;

    }


}



class GraphStruct {
    public Map<String, Vertex> vertices = new HashMap<String, Vertex>();
    private Vertex Bacon;
    private int baconnessingraph;
    public Vertex getVert(String str) {
        return vertices.get(str);
    }
    public void sortgraph(){
        vertices = MapUtil.sortByValue( vertices );
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
            Bacon.setBaconness(0);
            setBaconnessingraph(0);
        }
        catch (Exception e){
            System.out.println("Kevin Bacon not in graph");
        }
    }

    public int getBaconnessingraph() {
        return baconnessingraph;
    }

    public void setBaconnessingraph(int baconnessingraph) {
        this.baconnessingraph = baconnessingraph;
    }
}

class Vertex implements Comparable{
    private String name;
    private int bacon;
    private VertComparator comparator = new VertComparator();
    private TreeSet<Vertex> connected = new TreeSet<>(comparator);
    Vertex(String name){
        setName(name);
        this.bacon = -1;
    }

    public void connectWith(Vertex v){
        this.connected.add(v);
    }

    public boolean isConnected(Vertex v){
        return connected.contains(v);
    }
    public TreeSet<Vertex> Connections() { return connected; }

    public boolean equ(Vertex v) { return this.getName().equals(v.getName()); }
    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public void setBaconness(int baconness) {
        if (bacon == -1)this.bacon = baconness;
    }

    public int getBaconness(){
            return this.bacon;
    }

    @Override
    public int compareTo(Object o) throws ClassCastException {
        if (!(o instanceof Vertex))
            throw new ClassCastException("A Vertex object expected.");
        if(this.getBaconness()==-1&&((Vertex) o).getBaconness()==-1) return this.getName().compareTo(((Vertex) o).getName());
        else if(this.getBaconness()==-1) return 1;
        else if(((Vertex) o).getBaconness()==-1) return -1;
        else if (this.getBaconness()>((Vertex) o).getBaconness()) return -1; // noch nicht generierte werte nach hinten packen
        else if(this.getBaconness()<((Vertex) o).getBaconness()) return 1;
        else return  this.getName().compareTo(((Vertex) o).getName());
    }
}

class VertComparator implements Comparator <Vertex> {
    @Override
    public int compare(Vertex v1, Vertex v2) {
        return v1.compareTo(v2);
    }
}



class MapUtil
{
    public static <K, V extends Comparable<? super V>> Map<K, V>
    sortByValue( Map<K, V> map )
    {
        Map<K, V> result = new LinkedHashMap<>();
        Stream<Map.Entry<K, V>> st = map.entrySet().stream();

        st.sorted( Map.Entry.comparingByValue() )
                .forEachOrdered( e -> result.put(e.getKey(), e.getValue()) );

        return result;
    }
}