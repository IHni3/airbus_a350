public class OxygenSensor {
    // static instance
    private static OxygenSensor instance = new OxygenSensor();
    // port
    public Port port;
    private String manufacturer = "1103207 / 1044480";
    private String type = "team 15";
    private String id = "1103207 / 1044480";

    private boolean isAlarm;

    // private constructor
    private OxygenSensor() {
        port = new Port();
    }

    // static method getInstance
    public static OxygenSensor getInstance() {
        return instance;
    }

    // inner methods
    public String innerVersion() {
        return "WeatherRadar // " + manufacturer + " - " + type + " - " + id;
    }

    public int innerMeasure(String airFlow) {
        int oxygen = (int) airFlow.chars().filter(c -> c == 'o').count();
        isAlarm = oxygen < .15 || oxygen > 0.18;
        return oxygen;
    }

    public boolean innerAlarm() {
        isAlarm = true;
        return true;
    }

    // inner class port
    public class Port implements IOxygenSensor {
        public String version() {
            return innerVersion();
        }

        public int measure(String airFlow) {
            return innerMeasure(airFlow);
        }

        public boolean alarm() {
            return innerAlarm();
        }
	}
}
