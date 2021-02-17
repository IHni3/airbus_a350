public class Radar {
    // static instance
    private static Radar instance = new Radar();
    // port
    public Port port;
    private String manufacturer = "4485500 / 2627585";
    private String type = "team 17";
    private String id = "4485500 / 2627585";

    private boolean isOn;

    // private constructor
    private Radar() {
        port = new Port();
    }

    // static method getInstance
    public static Radar getInstance() {
        return instance;
    }

    // inner methods
    public String innerVersion() {
        return "Radar // " + manufacturer + " - " + type + " - " + id;
    }

    public boolean innerOn() {
        isOn = true;
        return true;
    }

    public boolean innerScan(String environment) {
        return environment.contains("bird");
    }

    public boolean innerOff() {
        isOn = false;
        return false;
    }


    // inner class port
    public class Port implements IRadar {
        public String version() {
            return innerVersion();
        }

        public boolean on() {
            return innerOn();
        }

        public boolean scan(String environment) {
            return innerScan(environment);
        }

        public boolean off() {
            return innerOff();
        }


	}
}
