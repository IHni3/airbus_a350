public class Engine {
    // static instance
    private static Engine instance = new Engine();
    // port
    public Port port;
    private String manufacturer = "9282087/5404118";
    private String type = "team 13";
    private String id = "9282087/5404118";

    private boolean isStarted = false;
    private int rpm = 0;
    private boolean isFire;

    // private constructor
    private Engine() {
        port = new Port();
    }

    // static method getInstance
    public static Engine getInstance() {
        return instance;
    }

    // inner methods
    public String innerVersion() {
        return "Engine // " + manufacturer + " - " + type + " - " + id;
    }

    public boolean innerStart() {
        isStarted = true;
        return isStarted;
    }

    public int innerIncreaseRPM(int value) {
        rpm += value;
        return rpm;
    }

    public int innerDecreaseRPM(int value) {
        rpm -= value;
        return rpm;
    }

    public boolean innerShutdown() {
        isStarted = false;
        return isStarted;
    }

    public void innerExtinguishFire() {
        isFire = false;
    }


    // inner class port
    public class Port implements IEngine {
        public String version() {
            return innerVersion();
        }

        public boolean start() {
            return innerStart();
        }

        public int increaseRPM(int value) {
            return innerIncreaseRPM(value);
        }

        public int decreaseRPM(int value) {
            return innerDecreaseRPM(value);
        }

        public boolean shutdown() {
            return innerShutdown();
        }

        public void extinguishFire() {
            innerExtinguishFire();
        }


	}
}
