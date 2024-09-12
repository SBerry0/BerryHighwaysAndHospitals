import java.util.ArrayList;
import java.util.Arrays;

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
        for (int i = 0; i < n; i++) {
            map[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < cities.length; i++) {
            map[cities[i][0]].add(cities[i][1]);
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

        int numHospitals = 0;

        ArrayList<Integer>[] map = makeMap(cities, n);
        City[] cityClasses = new City[n];
        for (int i = 0; i < n; i++) {
            cityClasses[i] = new City(i, map[i]);
        }
        for (City c : cityClasses) {
            if (c.getNumHighways() == 0) {
                numHospitals ++;
                c.giveHospital();
            }
            System.out.println(c);
        }

        int mostConnections = Integer.MIN_VALUE;
        for (City c : cityClasses) {

        }

        return (long) numHospitals*hospitalCost;
    }
}
