import java.lang.reflect.Array;
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
        for (int i = 1; i < n+1; i++) {
            map[i] = new ArrayList<Integer>();
        }
        for (int[] city : cities) {
            map[city[0]].add(city[1]);
            map[city[1]].add(city[0]);
        }

        return map;
    }


    public static long cost(int n, int hospitalCost, int highwayCost, int cities[][]) {
        System.out.println("Hospital cost: " + hospitalCost);
        System.out.println("Highway cost:" + highwayCost);
        System.out.println("Num Cities: " + n);
        if (hospitalCost < highwayCost) {
            return (long) hospitalCost * n;
        }

        int numHospitals = 0,
            numHighways = 0;

        ArrayList<Integer>[] map = makeMap(cities, n);
//        System.out.println(Arrays.toString(map));
        // 1-indexed
        City[] cityClasses = new City[n+1];
        for (int i = 1; i < n+1; i++) {
            cityClasses[i] = new City(i, map[i]);
        }
//        System.out.println(Arrays.toString(cityClasses));
        for (int i = 1; i < cityClasses.length; i++) {
            if (cityClasses[i].getNumHighways() == 0) {
                numHospitals ++;
                cityClasses[i].giveHospital();
            }
        }
        ArrayList<ArrayList<City>> clusters = new ArrayList<>();
        // maybe bfs to find each city cluster...
        int length = -1;
        while (length != 0) {
            ArrayList<City> cluster = BFS(cityClasses);
            length = cluster.size();
            if (length != 0) {
                clusters.add(cluster);
            }
        }
//        int mostConnections = Integer.MIN_VALUE;
//        for (City c : cityClasses) {
//
//        }

        return (long) numHospitals*hospitalCost + numHighways*highwayCost;
    }

    private static ArrayList<City> BFS(City[] cities) {
        ArrayList<City> cluster = new ArrayList<>();
        Queue<City> possibilities = new LinkedList<>();
        int i = -1;
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
        System.out.println(cluster);
        return cluster;
    }
}
