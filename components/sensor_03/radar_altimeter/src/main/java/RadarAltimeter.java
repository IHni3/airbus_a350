public class RadarAltimeter {
    // static instance
    private static RadarAltimeter instance = new RadarAltimeter();
    // port
    public Port port;
    private String manufacturer = "8843476 / 9668368";
    private String type = "team 16";
    private String id = "8843476 / 9668368";

    private boolean isOn;
    private int altitude;

    // private constructor
    private RadarAltimeter() {
        port = new Port();
    }

    // static method getInstance
    public static RadarAltimeter getInstance() {
        return instance;
    }

    // inner methods
    public String innerVersion() {
        return "RadarAltimeter // " + manufacturer + " - " + type + " - " + id;
    }

    public boolean innerOn() {
    // TODO
    }

    public void innerSend(String radioWave) {
    // TODO
    }

    public int innerRecieve(String radioWave) {
    // TODO
    }

    public int innerMeasureAltitude() {
    // TODO
    }

    public boolean innerOff() {
    // TODO
    }


    // inner class port
    public class Port implements IRadarAltimeter {
        public String version() {
            return innerVersion();
        }

        public boolean on() {
            return innerOn();
        }

        public void send(String radioWave) {
            return innerSend(radioWave);
        }

        public int recieve(String radioWave) {
            return innerRecieve(radioWave);
        }

        public int measureAltitude() {
            return innerMeasureAltitude();
        }

        public boolean off() {
            return innerOff();
        }


	}
}
