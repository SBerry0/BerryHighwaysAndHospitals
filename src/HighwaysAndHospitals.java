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

    /**
     * TODO: Complete this function, cost(), to return the minimum cost to provide
     *  hospital access for all citizens in Menlo County.
     */
    private static ArrayList<Integer>[] makeMap(int[][] cities, int n) {
        ArrayList<Integer>[] map = new ArrayList[n+1];
//        System.out.println(Arrays.toString(cities[228449]));
        for (int i = 1; i < n+1; i++) {
            map[i] = new ArrayList<Integer>();
        }
        for (int[] city : cities) {
            if (city[0] == 19) {
                System.out.println(city[0] + " to " + city[1]);
            }
            if (city[1] == 19) {
                System.out.println(city[1] + " to " + city[0]);
            }
            // Both sides get linked
            map[city[0]].add(city[1]);
            map[city[1]].add(city[0]);
        }

        return map;
    }


    public static long cost(int n, int hospitalCost, int highwayCost, int[][] cities) {
        System.out.println("Hospital cost: " + hospitalCost);
        System.out.println("Highway cost:" + highwayCost);
        System.out.println("Num Cities: " + n);
        if (hospitalCost < highwayCost) {
            return (long) hospitalCost * n;
        }

//        ArrayList<Integer>[] map = makeMap(cities, n);
//        System.out.println(java.util.Arrays.toString(map));
        // 1-indexed
//        City[] cityClasses = new City[n+1];
//        for (int i = 1; i < n+1; i++) {
//            cityClasses[i] = new City(i, map[i]);
//        }

//        int[] unionMap = unionFind(cities, n);
//        System.out.println(Arrays.toString(unionMap));
        int numClusters = unionFind(cities, n);
        System.out.println(numClusters);

        return (long) numClusters*hospitalCost + (long) (n-numClusters)*highwayCost;
    }

    private static int unionFind(int[][] cities, int numCities) {
        int[] map = new int[numCities+1];
        int numClusters = numCities;
        System.out.println("union finding");
        for (int[] edge : cities) {
            System.out.println(Arrays.toString(edge));
            int X = edge[0];
            while (map[X] > 0) {
                X = map[X];
            }
            while (map[edge[0]] > 0) {
                int temp = map[edge[0]];
                map[edge[0]] = X;
                edge[0] = temp;
            }
            int Y = edge[1];
            while (map[Y] > 0) {
                Y = map[Y];
            }

            while (map[edge[1]] > 0) {
                int temp = map[edge[1]];
                map[edge[1]] = Y;
                edge[1] = temp;
            }
            System.out.println(edge[0] + " =? " + edge[1]);
            if (edge[0] != edge[1]) {
                int R = Math.min(map[edge[0]], 0);
                int S = Math.min(map[edge[1]], 0);


                map[edge[1]] -= (map[edge[0]] < 0 ? map[edge[0]] + 1 : 1);
                System.out.println(R + " <? " + S);
                if (R < S) {
                    System.out.println("weighted!");
                    map[edge[1]] = edge[0];
                } else {
                    map[edge[0]] = edge[1];
                }
                System.out.println(Arrays.toString(map));
                numClusters--;
            }
        }
        return numClusters;




//        for (int i = 0; i < cities.length; i++) {
//            // If there is no root already... make it the root
//            long root = cities[i][1];
//            boolean doAdd = true;
//            System.out.println(Arrays.toString(map));
//            System.out.println(map[(int) root] + " =? " + map[cities[i][0]]);
//            System.out.println(Arrays.toString(cities[i]));
//            doAdd = ((map[(int) root] != map[cities[i][0]]) || map[cities[i][0]] == 0) && numClusters > 1;
//            while (map[(int) root] != 0) {
////                System.out.println(map[(int) root]);
//
//                root = map[(int) root];
//            }
//            if (doAdd) {
//                map[(int) root] = cities[i][0];
//                numClusters--;
//            } else {
//                System.out.println("didn't add");
//            }
//        }
//        for (int i = 0; i < map.length; i++) {
//            System.out.print(" " + i + " ");
//        }
//        System.out.println();
//        System.out.println(Arrays.toString(map));
//        return numClusters;
    }

    private static ArrayList<City> getClusters(City[] cities) {
        ArrayList<City> cluster = new ArrayList<>();
        Queue<City> possibilities = new LinkedList<>();
        int i = -1;
        // this gets slow
        for (int j = 1; j < cities.length; j++) {
            if (!cities[j].isBFSVisited()) {
                i = j;
                break;
            }
        }
        // Return empty arraylist if all cities have been checked
        if (i == -1) {
            return new ArrayList<City>();
        }
        possibilities.add(cities[i]);
        cities[i].setBFSVisited(true);
        cluster.add(cities[i]);
        while (!possibilities.isEmpty()) {
            ArrayList<Integer> connections = possibilities.remove().getPossibleConnections();
            for (int city : connections) {
                if (!cities[city].isBFSVisited()) {
                    possibilities.add(cities[city]);
                    cities[city].setBFSVisited(true);
                    cluster.add(cities[city]);
                }
            }
        }
//        System.out.println(cluster);
        return cluster;
    }
}
