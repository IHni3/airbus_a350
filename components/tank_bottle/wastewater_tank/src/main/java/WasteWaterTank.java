public class WasteWaterTank {
    // static instance
    private static WasteWaterTank instance = new WasteWaterTank();
    // port
    public Port port;
    private String manufacturer = "4485500 / 2627585";
    private String type = "team 17";
    private String id = "4485500 / 2627585";

    private int capacity=1000;
    private boolean isLocked;

    // private constructor
    private WasteWaterTank() {
        port = new Port();
    }

    // static method getInstance
    public static WasteWaterTank getInstance() {
        return instance;
    }

    // inner methods
    public String innerVersion() {
        return "WasteWaterTank // " + manufacturer + " - " + type + " - " + id;
    }

    public boolean innerLock() {
        isLocked = true;
        return true;
    }

    public boolean innerUnlock() {
        isLocked = false;
        return false;
    }

    public int innerAdd(int amount) {
        capacity = capacity - amount;
        return capacity;
    }

    public int innerPumpOut() {
        capacity = 1000;
        return capacity;
    }


    // inner class port
    public class Port implements IWasteWaterTank {
        public String version() {
            return innerVersion();
        }

        public boolean lock() {
            return innerLock();
        }

        public boolean unlock() {
            return innerUnlock();
        }

        public int add(int amount) {
            return innerAdd(amount);
        }

        public int pumpOut() {
            return innerPumpOut();
        }


	}
}
