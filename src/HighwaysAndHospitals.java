import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Highways & Hospitals
 * A puzzle created by Zach Blick
 * for Adventures in Algorithms
 * at Menlo School in Atherton, CA
 *
 * Completed by: Sohum Berry
 *
 */

public class HighwaysAndHospitals {

    public static long cost(int n, int hospitalCost, int highwayCost, int[][] cities) {
        // Starting case to check if the cost of hospitals are lower, making it constant time
        if (hospitalCost < highwayCost) {
            return (long) hospitalCost * n;
        }

        // Run union find to get the number of clusters
        int numClusters = unionFind(cities, n);

        // Run calculation to get the price from the number of clusters and cast to a long
        return (long) numClusters*hospitalCost + (long) (n-numClusters)*highwayCost;
    }

    private static int unionFind(int[][] cities, int numCities) {
        // Create 1-indexed map of cities to their roots
        int[] map = new int[numCities+1];
        // Store number of cities to subtract from to get number of clusters without having to iterate through it
        int numClusters = numCities;
        for (int[] edge : cities) {
            // Union-finding to get the root of a city
            int X = edge[0];
            while (map[X] > 0) {
                X = map[X];
            }
            // Path compression for the first city
            while (map[edge[0]] > 0) {
                int temp = map[edge[0]];
                map[edge[0]] = X;
                edge[0] = temp;
            }

            // Regular union-find
            int Y = edge[1];
            while (map[Y] > 0) {
                Y = map[Y];
            }
            // Path compression for the second city
            while (map[edge[1]] > 0) {
                int temp = map[edge[1]];
                map[edge[1]] = Y;
                edge[1] = temp;
            }

            // If the edge isn't already in the map...
            if (edge[0] != edge[1]) {
                // Make sure the city is currently a root
                int R = Math.min(map[edge[0]], 0);
                int S = Math.min(map[edge[1]], 0);
                // Add the weight of one city to the other if it exists
                map[edge[1]] += (map[edge[0]] < 0 ? map[edge[0]] - 1 : -1);
                if (R < S) {
                    map[edge[1]] = edge[0];
                } else {
                    map[edge[0]] = edge[1];
                }
                // Reduce the city from the available number of cities to be a cluster
                numClusters--;
            }
        }
        return numClusters;
    }
}
