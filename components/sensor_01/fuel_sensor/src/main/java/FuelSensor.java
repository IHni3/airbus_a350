public class FuelSensor {
    // static instance
    private static FuelSensor instance = new FuelSensor();
    // port
    public Port port;
    private String manufacturer = "1103207 / 1044480";
    private String type = "team 15";
    private String id = "1103207 / 1044480";

    private double amountOfFuel;
    private boolean isAlarmReserve;
    private boolean isAlarmMajor;
    private boolean isAlarmCritical;

    // private constructor
    private FuelSensor() {
        port = new Port();
    }

    // static method getInstance
    public static FuelSensor getInstance() {
        return instance;
    }

    // inner methods
    public String innerVersion() {
        return "FuelSensor // " + manufacturer + " - " + type + " - " + id;
    }

    public boolean innerAlarmReserve(int threshold) {
        isAlarmReserve = amountOfFuel < threshold;
        return isAlarmReserve;
    }

    public boolean innerAlarmMajor(int threshold) {
        isAlarmMajor = amountOfFuel < threshold;
        return isAlarmMajor;
    }

    public boolean innerAlarmCritical(int threshold) {
        isAlarmCritical = amountOfFuel < threshold;
        return isAlarmCritical;
    }

    public int innerMeasure() {
        return (int) amountOfFuel;
    }


    // inner class port
    public class Port implements IFuelSensor {
        public String version() {
            return innerVersion();
        }

        public boolean alarmReserve(int threshold) {
            return innerAlarmReserve(threshold);
        }

        public boolean alarmMajor(int threshold) {
            return innerAlarmMajor(threshold);
        }

        public boolean alarmCritical(int threshold) {
            return innerAlarmCritical(threshold);
        }

        public int measure() {
            return innerMeasure();
        }
	}
}
