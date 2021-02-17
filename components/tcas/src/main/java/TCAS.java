public class TCAS {
    // static instance
    private static TCAS instance = new TCAS();
    // port
    public Port port;
    private String manufacturer = "Student 2627585";
    private String type = "team 17";
    private String id = "2627585";
    private boolean isOn = false;
    private boolean isConnected = false;
    private boolean isAlarm = false;
    private int altitude;

    // private constructor
    private TCAS () {
        port = new Port();
    }

    // static method getInstance
    public static TCAS getInstance () {
        return instance;
    }

    // inner methods
    public String innerVersion () {
        return "TCAS // " + manufacturer + " - " + type + " - " + id;
    }

    public boolean innerOn () {
        isOn = true;
        return isOn;
    }

    public boolean innerOff () {
        isOn = false;
        return isOn;
    }

    public boolean innerConnect (String frequency) {
        isConnected = true;
        return isConnected;
    }

    public boolean innerScan (String environment) {
        return environment.contains("plane");
    }

    public boolean innerAlarm () {
        isAlarm = true;
        return isAlarm;
    }

    public int innerDetermineAltitude (String environment) {
        try {
            int altIndex = environment.indexOf("alt=") + 4;
            int foundAltitude = Integer.parseInt(
                    environment.substring(
                            altIndex,
                            4 + environment.substring(altIndex).indexOf("m")
                    )
            );
            altitude = foundAltitude;
            return foundAltitude;
        } catch (Exception ex) {
            ex.printStackTrace();
            System.err.println("Could not determine altitude");
            return Integer.MAX_VALUE;
        }
    }

    public int innerSetAltitude (int value) {
        return altitude = value;
    }

    // inner class port
    public class Port implements ITCAS {
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
        public boolean connect (String frequency) {
            return innerConnect(frequency);
        }

        @Override
        public boolean scan (String environment) {
            return innerScan(environment);
        }

        @Override
        public boolean alarm () {
            return innerAlarm();
        }

        @Override
        public int determineAltitude (String environment) {
            return innerDetermineAltitude(environment);
        }

        @Override
        public int setAltitude (int value) {
            return innerSetAltitude(value);
        }
    }
}