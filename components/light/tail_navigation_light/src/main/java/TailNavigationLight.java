public class TailNavigationLight {
    // static instance
    private static TailNavigationLight instance = new TailNavigationLight();
    // port
    public Port port;
    private String manufacturer = "1103207 / 1044480";
    private String type = "team 15";
    private String id = "1103207 / 1044480";

    private boolean isOn;

    // private constructor
    private TailNavigationLight() {
        port = new Port();
    }

    // static method getInstance
    public static TailNavigationLight getInstance() {
        return instance;
    }

    // inner methods
    public String innerVersion() {
        return "TailNavigationLight // " + manufacturer + " - " + type + " - " + id;
    }

    public boolean innerOn() {
        isOn = true;
        return true;
    }

    public boolean innerOff() {
        isOn = false;
        return false;
    }

    // inner class port
    public class Port implements ITailNavigationLight {
        public String version() {
            return innerVersion();
        }

        public boolean on() {
            return innerOn();
        }

        public boolean off() {
            return innerOff();
        }


	}
}
