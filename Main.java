import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

    final static int INF = 99999;
    final static Scanner sc = new Scanner(System.in);

    // Prompt the user to enter n and a vertex source
    // Driver's code
    public static void main(String[] args)
    {
        System.out.println("Our system will generate a graph using random number generator and representing in Matrix");
        System.out.print("Enter n:    ");
        int n = sc.nextInt();
        int[][] graph_A = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j)
                    graph_A[i][j] = 0;
                else
                    graph_A[i][j] = getRandomValue();}
        }

        printMatrices(graph_A, n);

        System.out.print("\n Enter the source as a start vertex. " +
                "Then, we will find the shortest distance to other vertices:    ");
        int source = sc.nextInt();

        System.out.print("Choose a destination vertex, we will find the short distance from the selected source:  ");
        int destination_vertex = sc.nextInt();

        System.out.println("\n\n========================================================================================");
        System.out.println("1. Dijkstra’s algorithm ");
        System.out.println("========================================================================================");


        Dijkstra_Algorithm t_graph = new Dijkstra_Algorithm();

        // Function call
        t_graph.dijkstra(graph_A, source, n, destination_vertex);


        System.out.println("\n\n=======================================================================================");
        System.out.println("2. Floyd-Warshall algorithm");
        System.out.println("========================================================================================");


        // make a copy of graph_A
        int[][] graph_B = new int [n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j)
                    graph_B[i][j] = 0;
                else {
                    if (graph_A[i][j] == 0)
                        graph_B[i][j] = INF;
                    else
                        graph_B[i][j] = graph_A[i][j];
                }
            }
        }
        FloydWarshall_Algorithm t_FloydWarshall= new FloydWarshall_Algorithm();
        t_FloydWarshall.FloydWarshall(graph_B, n, source, destination_vertex);
    }

    static void printMatrices(int[][] graph_A, int length) {

        System.out.println("Given Graph with size nxn = " + length + "x" + length + " represents as below matrix:\n");
        int i, j;
        for (i = 0; i < length; i++) {
            System.out.print("[\t");
            for (j = 0; j < length; j++)
                System.out.print(graph_A[i][j] + "\t");
            System.out.println("]");
        }
    }

    public static int getRandomValue()
    {
        int min = 0;
        int max = 10;
        // Get and return the random integer
        // within Min and Max
        return ThreadLocalRandom
                .current()
                .nextInt(min, max + 1);
    }
}

