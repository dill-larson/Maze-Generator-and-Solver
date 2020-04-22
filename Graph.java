package cs146s20.larson.project03;

import java.util.*;

public class Graph<T> {
    private Map<T, List<T>> adjacencyList;

    public Graph() {
        adjacencyList = new HashMap<>();
    }

    public void addVertex(T vertex) {
        adjacencyList.put(vertex, new LinkedList<T>());
    }

    public void addEdge(T source, T destination, boolean bidirectional) {
        if(!this.containsVertex(source)) {
            addVertex(source);
        }
        if(!this.containsVertex(destination)) {
            addVertex(destination);
        }

        adjacencyList.get(source).add(destination);
        if(bidirectional) {
            adjacencyList.get(destination).add(source);
        }
    }

    /**
     * The number of vertices in the graph
     * @return integer representing the number of vertices
     */
    public int order() {
        return adjacencyList.keySet().size();
    }

    /**
     * The number of edges in the graph
     * @param bidirectional undirected graph
     * @return integer representing the number of edges
     */
    public int size(boolean bidirectional) {
        int count = 0;
        for (T vertex : adjacencyList.keySet()) {
            count += adjacencyList.get(vertex).size();
        }
        if (bidirectional) {
            count /= 2;
        }

        return count;
    }

    public boolean isEmpty() {
        return adjacencyList.isEmpty();
    }

    public boolean containsVertex(T vertex) {
        return adjacencyList.containsKey(vertex);
    }

    public boolean containsEdge(T source, T destination) {
        return adjacencyList.get(source).contains(destination);
    }

    public void clear() {
        adjacencyList.clear();
    }

    @Override
    public String toString()
    {
        String result = "";

        for (T vertex : adjacencyList.keySet()) {
            result += (vertex.toString() + ": ");
            for (T edge : adjacencyList.get(vertex)) {
                result += (edge.toString() + " ");
            }
            result += ("\n");
        }

        return result;
    }
}
