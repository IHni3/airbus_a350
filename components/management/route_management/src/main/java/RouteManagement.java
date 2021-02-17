import java.util.LinkedList;
import java.util.List;

public class RouteManagement {
    // static instance
    private static RouteManagement instance = new RouteManagement();
    // port
    public Port port;

    //student
    private String manufacturer = "6143217";
    private String type = "team 14";
    private String id = "6143217";

    //variables
    List<CheckPoint> checkpoints;
    boolean enabled;
    int costIndex;

    //constants


    // private constructor
    private RouteManagement() {
        port = new Port();
        checkpoints = new LinkedList<>();
        enabled = false;
        costIndex = 0;
    }

    // static method getInstance
    public static RouteManagement getInstance() {
        return instance;
    }

    // inner methods
    public String innerVersion() {
        return "CostOptimizer // " + manufacturer + " - " + type + " - " + id;
    }

    public int innerAddCheckpoint(CheckPoint checkPoint) {
        checkpoints.add(checkPoint);
        return checkpoints.size();
    }
    public int innerRemoveCheckpoint(CheckPoint checkPoint) {
        checkpoints.remove(checkPoint);
        return checkpoints.size();
    }
    public boolean innerSetEnabled(boolean enabled){
        this.enabled = enabled;
        return enabled;
    }
    private int innerSetCostIndex(int value) {
        costIndex = value;
        return costIndex;
    }

    private void innerPrintCheckPoints() {
        for (var checkpoint : checkpoints) {
            System.out.println(checkpoint.toString());
        }
    }

    // inner class port
    public class Port implements IRouteManagement {


        @Override
        public String version() {
            return innerVersion();
        }

        @Override
        public boolean on() {
            return innerSetEnabled(true);
        }

        @Override
        public int add(CheckPoint checkpoint) {
            return innerAddCheckpoint(checkpoint);
        }

        @Override
        public int remove(CheckPoint checkPoint) {
            return innerRemoveCheckpoint(checkPoint);
        }

        @Override
        public void printCheckPoints() {
            innerPrintCheckPoints();
        }

        @Override
        public int setCostIndex(int value) {
            return innerSetCostIndex(value);
        }

        @Override
        public boolean off() {
            return innerSetEnabled(false);
        }
    }
}