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
        isOn = true;
        return isOn;
    }

    public void innerSend(String radioWave) {
        if(radioWave == null){
            return;
        }
        altitude = radioWave.length()*100;
    }

    public int innerReceive(String radioWave) {
        if (radioWave == null) {
            return altitude;
        }
        altitude = radioWave.length() * 100;
        return altitude;
    }

    public int innerMeasureAltitude() {
        innerSend(null);
        innerReceive(null);
        return altitude;
    }

    public boolean innerOff() {
        isOn = false;
        return isOn;
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
            innerSend(radioWave);
        }

        public int receive(String radioWave) {
            return innerReceive(radioWave);
        }

        public int measureAltitude() {
            return innerMeasureAltitude();
        }

        public boolean off() {
            return innerOff();
        }


    }
}
