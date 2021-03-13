public class IceDetectorProbe {
    // static instance
    private static IceDetectorProbe instance = new IceDetectorProbe();
    // port
    public Port port;
    private String manufacturer = "1103207 / 1044480";
    private String type = "team 15";
    private String id = "1103207 / 1044480";

    private boolean isActivated;
    private boolean isIceDetected;

    // private constructor
    private IceDetectorProbe() {
        port = new Port();
    }

    // static method getInstance
    public static IceDetectorProbe getInstance() {
        return instance;
    }

    // inner methods
    public String innerVersion() {
        return "IceDetectorProbe // " + manufacturer + " - " + type + " - " + id;
    }

    public boolean innerDetect() {
        return isIceDetected;
    }

    public boolean innerDetect(String surface) {
        isIceDetected = !surface.isBlank();
        return isIceDetected;
    }

    public boolean innerDetect(String surface, String pattern) {
        isIceDetected = surface.contains(pattern);
        return isIceDetected;
    }

    public boolean innerDeactivate() {
        isActivated = false;
        return false;
    }

    public boolean innerActivate() {
        isActivated = true;
        return true;
    }


    // inner class port
    public class Port implements IIceDetectorProbe {
        public String version() {
            return innerVersion();
        }

        public boolean detect() {
            return innerDetect();
        }

        public boolean detect(String surface) {
            return innerDetect(surface);
        }

        public boolean detect(String surface, String pattern) {
            return innerDetect(surface, pattern);
        }

        public boolean deactivate() {
            return innerDeactivate();
        }

        public boolean activate() {
            return innerActivate();
        }
	}
}
