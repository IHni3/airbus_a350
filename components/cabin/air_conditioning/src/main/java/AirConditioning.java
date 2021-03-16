public class AirConditioning {
    // static instance
    private static AirConditioning instance = new AirConditioning();
    // port
    public Port port;
    private String manufacturer = "9282087/5404118";
    private String type = "team 13";
    private String id = "9282087/5404118";

    private int temperatur;
    private boolean isOn;

    // private constructor
    private AirConditioning() {
        port = new Port();
    }

    // static method getInstance
    public static AirConditioning getInstance() {
        return instance;
    }

    // inner methods
    public String innerVersion() {
        return "AirConditioning // " + manufacturer + " - " + type + " - " + id;
    }

    public boolean innerOn() {
        isOn = true;
        return isOn;
    }

    public String innerClean(String airFlow) {
        airFlow = airFlow.replaceAll("[!-~]", " ");
        return airFlow;
    }

    public int innerHeat(String airFlow, int temperatur) {
        this.temperatur += temperatur;
        return temperatur;
    }

    public int innerCool(String airFlow, int temperatur) {
        this.temperatur -= temperatur;
        return temperatur;
    }

    public boolean innerOff() {
        isOn = false;
        return isOn;
    }


    // inner class port
    public class Port implements IAirConditioning {
        public String version() {
            return innerVersion();
        }

        public boolean on() {
            return innerOn();
        }

        public String clean(String airFlow) {
            return innerClean(airFlow);
        }

        public int heat(String airFlow, int temperatur) {
            return innerHeat(airFlow, temperatur);
        }

        public int cool(String airFlow, int temperatur) {
            return innerCool(airFlow, temperatur);
        }

        public boolean off() {
            return innerOff();
        }


	}
}
