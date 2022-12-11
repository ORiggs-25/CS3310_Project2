/**
 * Java program for Dijkstra's single source the shortest path
 * algorithm. The program is for adjacency matrix
 * representation of the graph
 */
import java.lang.*;

/**
 * A utility function to find the vertex with minimum
 * distance value, from the set of vertices not yet
 * included in the shortest path tree
 */
class Dijkstra_Algorithm {

    int minDistance(int[] dist, Boolean[] sptSet, int V)
    {
        // Initialize min value
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v < V; v++)
            if (!sptSet[v] && dist[v] <= min) {
                min = dist[v];
                min_index = v;
            }

        return min_index;
    }

    /**
     * A utility function to print the constructed distance
     * array
     * @param dist
     * @param src
     * @param V
     */
    void printSolution(int[] dist, int src, int V, int destination_vertex)
    {
        int distance = 0;

        System.out.println(
                "The following table shows the shortest "
                        + "distances between every pair of vertices from a selected source. \n");
        System.out.println(
                "Source \t Destination Vertex \t Shortest Distance from Source");
        for (int i = 0; i < V; i++) {
            System.out.println("\t" + src + "\t\t\t" + i + " \t\t\t\t\t\t " + dist[i]);
            if (i == destination_vertex) {
                distance = dist[i];
            }
        }

        System.out.println("\n From the above result, if Source = " + src + ", and Vertex destination= "
                        + destination_vertex + ", therefore the shortest Distance= " + distance);

    }

    /**
     * Function that implements Dijkstra's single source
     * the shortest path algorithm for a graph represented using
     * adjacency matrix representation
     * @param graph
     * @param source
     * @param V
     */
    void dijkstra(int[][] graph, int source, int V, int destination_vertex)
    {
        // start measure runtime
        long startTime = System.nanoTime();

        int[] dist = new int[V]; // The output array.
        // dist[i] will hold
        // the shortest distance from src to i

        // sptSet[i] will true if vertex in index i is included in
        // the shortest path tree or shortest distance from src
        // to i is finalized
        Boolean[] sptSet = new Boolean[V];

        // Initialize all distances as INFINITE and stpSet[]
        // as false
        for (int i = 0; i < V; i++) {
            dist[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
        }

        // Distance of source vertex from itself is always 0
        dist[source] = 0;

        // Find the shortest path for all vertices
        //for (int count = 0; count < V - 1; count++) {
        for (int count = 0; count < V - 1; count++) {

        // Pick the minimum distance vertex from the set
            // of vertices not yet processed. u is always
            // equal to src in first iteration.
            int u = minDistance(dist, sptSet, V);

            // Mark the picked vertex as processed
            sptSet[u] = true;

            // Update dist value of the adjacent vertices of
            // the picked vertex.
            for (int v = 0; v < V; v++)

                // Update dist[v] only if is not in sptSet,
                // there is an edge from u to v, and total
                // weight of path from src to v through u is
                // smaller than current value of dist[v]
                if (!sptSet[v] && graph[u][v] != 0
                        && dist[u] != Integer.MAX_VALUE
                        && dist[u] + graph[u][v] < dist[v])
                    dist[v] = dist[u] + graph[u][v];
        }

        // print the constructed distance array
        printSolution(dist, source, V, destination_vertex);

        // find  result of runtime
        long endTime = System.nanoTime();
        double time_in_second = (double)(endTime - startTime) / 1_000_000_000.0;
        System.out.println("Runtime " +  time_in_second + " seconds");
    }
}

