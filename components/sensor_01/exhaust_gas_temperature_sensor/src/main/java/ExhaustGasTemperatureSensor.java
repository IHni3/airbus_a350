public class ExhaustGasTemperatureSensor {
    // static instance
    private static ExhaustGasTemperatureSensor instance = new ExhaustGasTemperatureSensor();
    // port
    public Port port;
    private String manufacturer = "1103207 / 1044480";
    private String type = "team 15";
    private String id = "1103207 / 1044480";

    private int temperature;
    private boolean isAlarmMajor;
    private boolean isAlarmCritical;


    // private constructor
    private ExhaustGasTemperatureSensor() {
        port = new Port();
    }

    // static method getInstance
    public static ExhaustGasTemperatureSensor getInstance() {
        return instance;
    }

    // inner methods
    public String innerVersion() {
        return "ExhaustGasTemperatureSensor // " + manufacturer + " - " + type + " - " + id;
    }

    public int innerMeasure() {
        return temperature;
    }

    public boolean innerAlarmMajor(int threshold) {
        isAlarmMajor = temperature >= threshold;
        return isAlarmMajor;
    }

    public boolean innerAlarmCritical(int threshold) {
        isAlarmCritical = temperature >= threshold;
        return isAlarmCritical;
    }

    // inner class port
    public class Port implements IExhaustGasTemperatureSensor {
        public String version() {
            return innerVersion();
        }

        public int measure() {
            return innerMeasure();
        }

        public boolean alarmMajor(int threshold) {
            return innerAlarmMajor(threshold);
        }

        public boolean alarmCritical(int threshold) {
            return innerAlarmCritical(threshold);
        }
	}
}
