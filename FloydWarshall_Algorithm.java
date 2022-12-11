/**
 * Java program for Floyd Warshall All Pairs the shortest
 * Path algorithm.
 */
import java.lang.*;

class FloydWarshall_Algorithm {

    /**
     * Add all vertices one by one
     * 		to the set of intermediate
     * 		vertices.
     * 		---> Before start of an iteration,
     * 			we have the shortest
     * 			distances between all pairs
     * 			of vertices such that
     * 			the shortest distances consider
     * 			only the vertices in
     * 			set {0, 1, 2, .. k-1} as
     * 			intermediate vertices.
     * 		----> After the end of an iteration,
     * 				vertex no. k is added
     * 				to the set of intermediate
     * 				vertices and the set
     * 				becomes {0, 1, 2, .. k}
     */
    void FloydWarshall(int[][] dist, int vertex, int source, int destination_vertex) {

        // start measure runtime
        long startTime = System.nanoTime();

        int i, j, k;

        for (k = 0; k < vertex; k++) {
            // Pick all vertices as source one by one
            for (i = 0; i < vertex; i++) {
                // Pick all vertices as destination for the
                // above picked source
                for (j = 0; j < vertex; j++) {
                    // If vertex k is on the shortest path
                    // from i to j, then update the value of
                    // dist[i][j]
                    if (dist[i][k] + dist[k][j] < dist[i][j])
                        dist[i][j] = dist[i][k] + dist[k][j];
                }
            }
        }

        printSolution(dist, vertex, source, destination_vertex);

        // find  result of runtime
        long endTime = System.nanoTime();
        double time_in_second = (double)(endTime - startTime) / 1_000_000_000.0;
        System.out.println("Runtime " +  time_in_second + " seconds");
    }

    void printSolution(int[][] dist, int vertex, int source, int destination_vertex) {

        int distance;
        System.out.println(
                "The following table shows the shortest "
                        + "distances between every pair of vertices from a selected source \n");

        System.out.println(
                "Source \t Destination Vertex \t Shortest Distance from Source");

        for (int i = source; i < source + 1; ++i) {
            for (int j = 0; j < vertex; ++j) {
                if (dist[i][j] == Main.INF)
                    System.out.println("\t" + i + "\t\t\t" + j + " \t\t\t\t\t " + "INF");
                else{
                    distance = dist[i][j];
                    System.out.println("\t" + i + "\t\t\t" + j + " \t\t\t\t\t " + distance);}
            }

            System.out.println();
        }

        System.out.println("\n From the above result, if Source = " + source + ", and Vertex destination= "
                + destination_vertex + ", therefore the shortest Distance= " + dist[source][destination_vertex]);
    }
}


