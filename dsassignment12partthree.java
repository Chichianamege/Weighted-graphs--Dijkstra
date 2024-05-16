// Name: Chidera Anamege
// Class: CS 3305 W01
// Term: Spring 2024
// Instructor: Carla McManus
// Assignment: 12-ExtraCredit-2-Dijkstra


import java.util.Arrays;

public class dsassignment12partthree {
    private int V; // Number of vertices
    private int[][] graph; // Adjacency matrix representation of the graph

    // Constructor to initialize the graph with the specified number of vertices
    public dsassignment12partthree(int vertices) {
        this.V = vertices;
        this.graph = new int[vertices][vertices];
    }

    // Method to print the computed shortest distances from the source vertex
    public void printSolution(int[] dist) {
        System.out.println("Vertex \tDistance from Source");
        for (int node = 0; node < V; node++) {
            System.out.println(node + "\t" + dist[node]);
        }
    }

    // Helper method to find the vertex with the minimum distance from the source vertex
    public int minDistance(int[] dist, boolean[] sptSet) {
        int minDist = Integer.MAX_VALUE;
        int minIndex = 0;

        for (int v = 0; v < V; v++) {
            if (!sptSet[v] && dist[v] < minDist) {
                minDist = dist[v];
                minIndex = v;
            }
        }

        return minIndex;
    }

    // Method to compute the shortest path distances using Dijkstra's algorithm
    public void dijkstra(int src) {
        int[] dist = new int[V]; // Array to store the shortest distances
        boolean[] sptSet = new boolean[V]; // Array to track vertices included in the shortest path tree

        Arrays.fill(dist, Integer.MAX_VALUE); // Initialize distances to infinity
        dist[src] = 0; // Distance from source vertex to itself is 0

        // Find shortest path for all vertices
        for (int count = 0; count < V - 1; count++) {
            // Select the vertex with the minimum distance from the set of vertices not yet processed
            int u = minDistance(dist, sptSet);
            sptSet[u] = true; // Mark the selected vertex as processed

            // Update dist[v] for all adjacent vertices of u
            for (int v = 0; v < V; v++) {
                // Update dist[v] if v is not in sptSet, there is an edge from u to v,
                // and total weight of path from src to v through u is smaller than current value of dist[v]
                if (!sptSet[v] && graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE
                        && dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
                }
            }
        }

        // Print the computed shortest distances from the source vertex
        printSolution(dist);
    }

    public static void main(String[] args) {
        // Create an instance of my class for a graph with 11 vertices
        dsassignment12partthree graph = new dsassignment12partthree(11);

        // Define the graph's adjacency matrix with weighted edges
        int[][] adjMatrix = {
                {0, 807, 0, 0, 0, 381, 0, 0, 0, 0, 0},
                {807, 0, 0, 761, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1316, 0, 0, 0, 0, 0, 0, 0},
                {0, 761, 1316, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {381, 0, 0, 0, 0, 0, 1251, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1251, 0, 1015, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 1015, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 1733, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 1733, 0, 864},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 864, 0}
        };

        // Assign the adjacency matrix to the graph instance
        graph.graph = adjMatrix;

        // Source vertex is San Francisco (index 0), apply Dijkstra's algorithm
        graph.dijkstra(0);
    }
}
