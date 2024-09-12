import java.util.ArrayList;

public class City {
    private boolean hasHighway;
    private ArrayList<Integer> possibleConnections;
    private boolean hasHospital;
    private int cityNum;
    private boolean isBFSVisited;

    public City(int cityNum, ArrayList<Integer> possibleConnections) {
        this.hasHighway = false;
        this.possibleConnections = possibleConnections;
        this.hasHospital = false;
        this.cityNum = cityNum;
        this.isBFSVisited = false;
    }


    public String toString() {
        return "" + cityNum;
//        String out = "City #" + cityNum + " does " + (hasHighway ? "" : "not ") + "have a highway.\n";
//        out += "City #" + cityNum + " does " + (hasHospital ? "" : "not ") + "have a hospital.\n";
//        out += "Possible connections:\n" + possibleConnections;
//
//        return out;
    }

    public int getNumHighways() {
        return possibleConnections.size();
    }

    public boolean isBFSVisited() {
        return isBFSVisited;
    }

    public void setBFSVisited(boolean BFSVisited) {
        isBFSVisited = BFSVisited;
    }

    public ArrayList<Integer> getPossibleConnections() {
        return possibleConnections;
    }

    public void giveHospital() {
        hasHospital = true;
    }
}
