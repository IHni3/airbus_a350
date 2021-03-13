public class FuelFlowSensor {
    // static instance
    private static FuelFlowSensor instance = new FuelFlowSensor();
    // port
    public Port port;
    private String manufacturer = "1103207 / 1044480";
    private String type = "team 15";
    private String id = "1103207 / 1044480";

    private int fuelFlow;

    // private constructor
    private FuelFlowSensor() {
        port = new Port();
    }

    // static method getInstance
    public static FuelFlowSensor getInstance() {
        return instance;
    }

    // inner methods
    public String innerVersion() {
        return "FuelFlowSensor // " + manufacturer + " - " + type + " - " + id;
    }

    public int innerMeasure() {
    	fuelFlow = (int) (Math.random() * 100D);
        return fuelFlow;
    }

    // inner class port
    public class Port implements IFuelFlowSensor {
        public String version() {
            return innerVersion();
        }

        public int measure() {
            return innerMeasure();
        }
	}
}
