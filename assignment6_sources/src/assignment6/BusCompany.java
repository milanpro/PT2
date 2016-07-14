package assignment6;

import java.util.*;

public class BusCompany {
    private static Graph GRAPH = null;
    public static void main(String[] args) {
        Collection<Route> routes = Arrays.asList(
                new Route("Paris", "Saint-Denis", 10),
                new Route("Saint-Denis", "Lens", 190)
        );
        System.out.println(minimumTaxableKilometers(3, routes));
    }

    public static int minimumTaxableKilometers(int cityCount, Collection<Route> routes) {
        Route[] r = routes.toArray(new Route[routes.size()]); //Collection in Route-Array umwandeln
        GRAPH=new Graph(r);
        GRAPH.dijkstra("Paris");
        return 0;
    }
}




/*class Dijkstra {
    private static final Graph.Edge[] GRAPH = {
            new Graph.Edge("a", "b", 7),
            new Graph.Edge("a", "c", 9),
            new Graph.Edge("a", "f", 14),
            new Graph.Edge("b", "c", 10),
            new Graph.Edge("b", "d", 15),
            new Graph.Edge("c", "d", 11),
            new Graph.Edge("c", "f", 2),
            new Graph.Edge("d", "e", 6),
            new Graph.Edge("e", "f", 9),
    };
    private static final String START = "a";
    private static final String END = "e";

    public static void main(String[] args) {
        Graph g = new Graph(GRAPH);
        g.dijkstra(START);
        g.printPath(END);
        //g.printAllPaths();
    }
}
*/
class Graph {
    private final Map<String, Vertex> graph;

    public static class Vertex implements Comparable<Vertex> {
        public final String name;
        public int dist = Integer.MAX_VALUE; // MAX_VALUE -> als unendlich
        public Vertex previous = null;
        public final Map<Vertex, Integer> neighbours = new HashMap<>();

        public Vertex(String name) {
            this.name = name;
        }

        private void printPath() {
            if (this == this.previous) {
                System.out.printf("%s", this.name);
            } else if (this.previous == null) {
                System.out.printf("%s(unreached)", this.name);
            } else {
                this.previous.printPath();
                System.out.printf(" -> %s(%d)", this.name, this.dist);
            }
        }

        public int compareTo(Vertex other) {
            return Integer.compare(dist, other.dist);
        }
    }

    public Graph(Route[] routes) {
        graph = new HashMap<>(routes.length);

        //one pass to find all vertices
        for (Route e : routes) {
            if (!graph.containsKey(e.getCity1())) graph.put(e.getCity1(), new Vertex(e.getCity1()));
            if (!graph.containsKey(e.getCity2())) graph.put(e.getCity2(), new Vertex(e.getCity2()));
        }

        //another pass to set neighbouring vertices
        for (Route e : routes) {
            graph.get(e.getCity1()).neighbours.put(graph.get(e.getCity2()), e.getTaxableKilometers());
            graph.get(e.getCity2()).neighbours.put(graph.get(e.getCity1()), e.getTaxableKilometers()); // also do this for an undirected graph
        }
    }

    public Vertex MaxNeighbours(){
        Vertex result = null;
        for(Vertex  v : graph.values()){
            if(result != null){
                if(result.neighbours.size()<v.neighbours.size()) result = v;
            }
            else result = v;
        }
        return result;
    }

    /** Runs dijkstra using a specified source vertex */
    public void dijkstra(String startName) {
        if (!graph.containsKey(startName)) {
            System.err.printf("Graph doesn't contain start vertex \"%s\"\n", startName);
            return;
        }
        final Vertex source = graph.get(startName);
        NavigableSet<Vertex> q = new TreeSet<>();

        // set-up vertices
        for (Vertex v : graph.values()) {
            v.previous = v == source ? source : null;
            v.dist = v == source ? 0 : Integer.MAX_VALUE;
            q.add(v);
        }

        dijkstra(q);
    }

    /** Implementation of dijkstra's algorithm using a binary heap. */
    private void dijkstra(final NavigableSet<Vertex> q) {
        Vertex u, v;
        while (!q.isEmpty()) {

            u = q.pollFirst(); // vertex with shortest distance (first iteration will return source)
            if (u.dist == Integer.MAX_VALUE) break; // we can ignore u (and any other remaining vertices) since they are unreachable

            //look at distances to each neighbour
            for (Map.Entry<Vertex, Integer> a : u.neighbours.entrySet()) {
                v = a.getKey(); //the neighbour in this iteration

                final int alternateDist = u.dist + a.getValue();
                if (alternateDist < v.dist) { // shorter path to neighbour found
                    q.remove(v);
                    v.dist = alternateDist;
                    v.previous = u;
                    q.add(v);
                }
            }
        }
    }
    
    /** Prints a path from the source to the specified vertex */
    public void countPath(String endName) {
        if (!graph.containsKey(endName)) {
            System.err.printf("Graph doesn't contain end vertex \"%s\"\n", endName);
            return;
        }

        graph.get(endName).printPath();
        System.out.println();
    }

    private void setprevious(){
        for(Vertex v : graph.values()){
            v.previous = null;
        }
    }
    /** Prints the path from the source to every vertex (output order is not guaranteed) */
    public void getMostUsedRoute() {
        dijkstra(MaxNeighbours().name);

    }
}