public class Camera {
    // static instance
    private static Camera instance = new Camera();
    // port
    public Port port;
    private final String manufacturer = "Student 2627585";
    private CameraType type;
    private final String id = "2627585";
    private boolean isOn = false;

    // private constructor
    private Camera () {
        port = new Port();
    }

    // static method getInstance
    public static Camera getInstance() {
        return instance;
    }

    // inner methods
    public String innerVersion() {
        return "Camera // " + manufacturer + " - " + type + " - " + id;
    }

    public boolean innerOn() {
        isOn = true;
        return isOn;
    }

    public boolean innerOff() {
        isOn = false;
        return isOn;
    }

    public CameraType innerSetType (String type) {
        this.type = CameraType.valueOf(type);
        return this.type;
    }

    public String innerRecord () {
        return "Clouds";
    }

    public String innerZoomIn(double factor) {
        return factor + "x scaled \"" + innerRecord() + "\"";
    }

    public String innerZoomOut(double factor) {
        return factor + "x scaled \"" + innerRecord() + "\"";
    }

    // inner class port
    public class Port implements ICamera {
        @Override
        public String version () {
            return innerVersion();
        }

        @Override
        public boolean on () {
            return innerOn();
        }

        @Override
        public boolean off () {
            return innerOff();
        }

        @Override
        public CameraType setType (String type) {
            return innerSetType(type);
        }

        @Override
        public String record () {
            return innerRecord();
        }

        @Override
        public String zoomIn (double factor) {
            return innerZoomIn(factor);
        }

        @Override
        public String zoomOut (double factor) {
            return innerZoomOut(factor);
        }
    }
}