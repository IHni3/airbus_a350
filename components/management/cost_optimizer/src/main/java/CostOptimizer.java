import domains.CheckPoint;

import java.util.LinkedList;
import java.util.List;

public class CostOptimizer {
    // static instance
    private static CostOptimizer instance = new CostOptimizer();
    // port
    public Port port;

    //student
    private String manufacturer = "6143217";
    private String type = "team 14";
    private String id = "6143217";

    //variables
    List<CheckPoint> checkpoints;
    boolean enabled;

    //constants


    // private constructor
    private CostOptimizer() {
        port = new Port();
        checkpoints = new LinkedList<>();
        enabled = false;
    }

    // static method getInstance
    public static CostOptimizer getInstance() {
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

    // inner class port
    public class Port implements ICostOptimizer {


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
        public int optimize(List<CheckPoint> checkPointList) {
            return 0; //TODO
        }

        @Override
        public int validate(int costIndex) {
           return 10; //TODO
        }


        @Override
        public boolean off() {
            return innerSetEnabled(false);
        }
    }
}