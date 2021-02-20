public class FuelTank {
    // static instance
    private static FuelTank instance = new FuelTank();
    // port
    public Port port;
    private String manufacturer = "8843476 / 9668368";
    private String type = "team 16";
    private String id = "8843476 / 9668368";

    private int amount = 1000;

    // private constructor
    private FuelTank() {
        port = new Port();
    }

    // static method getInstance
    public static FuelTank getInstance() {
        return instance;
    }

    // inner methods
    public String innerVersion() {
        return "FuelTank // " + manufacturer + " - " + type + " - " + id;
    }

    public int innerTakeOut(int amount) {
    // TODO
    }

    public int innerRefill() {
    // TODO
    }

    public int innerRefill(int amount) {
    // TODO
    }


    // inner class port
    public class Port implements IFuelTank {
        public String version() {
            return innerVersion();
        }

        public int takeOut(int amount) {
            return innerTakeOut(amount);
        }

        public int refill() {
            return innerRefill();
        }

        public int refill(int amount) {
            return innerRefill(amount);
        }


	}
}
