public class APU {
    // static instance
    private static APU instance = new APU();
    // port
    public Port port;
    private String manufacturer = "9282087/5404118";
    private String type = "team 13";
    private String id = "9282087/5404118";

    private boolean isStarted = false;
    private int rpm = 0;

    // private constructor
    private APU() {
        port = new Port();
    }

    // static method getInstance
    public static APU getInstance() {
        return instance;
    }

    // inner methods
    public String innerVersion() {
        return "APU // " + manufacturer + " - " + type + " - " + id;
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

    public void innerShutdown() {
        isStarted = false;
    }


    // inner class port
    public class Port implements IAPU {
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

        public void shutdown() {
            innerShutdown();
        }


	}
}
