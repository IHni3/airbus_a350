public class FireDetector {
    // static instance
    private static FireDetector instance = new FireDetector();
    // port
    public Port port;
    private String manufacturer = "1103207 / 1044480";
    private String type = "team 15";
    private String id = "1103207 / 1044480";

    private boolean isAlarm;

    // private constructor
    private FireDetector() {
        port = new Port();
    }

    // static method getInstance
    public static FireDetector getInstance() {
        return instance;
    }

    // inner methods
    public String innerVersion() {
        return "FireDetector // " + manufacturer + " - " + type + " - " + id;
    }

    public boolean innerScan(String air) {
        isAlarm = air.contains("ice");
        return isAlarm;
    }

    public boolean innerAlarm() {
        isAlarm = true;
        return true;
    }


    // inner class port
    public class Port implements IFireDetector {
        public String version() {
            return innerVersion();
        }

        public boolean scan(String air) {
            return innerScan(air);
        }

        public boolean alarm() {
            return innerAlarm();
        }
	}
}
