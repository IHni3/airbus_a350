public class EngineOilTank {
    // static instance
    private static EngineOilTank instance = new EngineOilTank();
    // port
    public Port port;
    private String manufacturer = "8843476 / 9668368";
    private String type = "team 16";
    private String id = "8843476 / 9668368";

    private int level;

    // private constructor
    private EngineOilTank() {
        port = new Port();
    }

    // static method getInstance
    public static EngineOilTank getInstance() {
        return instance;
    }

    // inner methods
    public String innerVersion() {
        return "EngineOilTank // " + manufacturer + " - " + type + " - " + id;
    }

    public int innerIncreaseLevel(int value) {
        level += value;
        level < 0 ? level = 0;
        return level;
    }

    public int innerDecreaseLevel(int value) {
        level -= value;
        level < 0 ? level = 0;
        return level;
    }


    // inner class port
    public class Port implements IEngineOilTank {
        public String version() {
            return innerVersion();
        }

        public int increaseLevel(int value) {
            return innerIncreaseLevel(value);
        }

        public int decreaseLevel(int value) {
            return innerDecreaseLevel(value);
        }


	}
}
