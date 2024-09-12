import java.util.ArrayList;

public class City {
    private boolean hasHighway;
    private ArrayList<Integer> possibleConnections;
    private boolean hasHospital;
    private int cityNum;

    public City(int cityNum, ArrayList<Integer> possibleConnections) {
        this.hasHighway = false;
        this.possibleConnections = possibleConnections;
        this.hasHospital = false;
        this.cityNum = cityNum;
    }


    public String toString() {
        String out = "City #" + cityNum + " does " + (hasHighway ? "" : "not ") + "have a highway.\n";
        out += "City #" + cityNum + " does " + (hasHospital ? "" : "not ") + "have a hospital.\n";
        out += "Possible connections:\n" + possibleConnections;

        return out;
    }

    public int getNumHighways() {
        return possibleConnections.size();
    }

    public void giveHospital() {
        hasHospital = true;
    }
}
