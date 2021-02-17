public class PotableWaterTank {
    // static instance
    private static PotableWaterTank instance = new PotableWaterTank();
    // port
    public Port port;
    private String manufacturer = "4485500 / 2627585";
    private String type = "team 17";
    private String id = "4485500 / 2627585";

    private int amount=1000;
    private boolean isLocked;

    // private constructor
    private PotableWaterTank() {
        port = new Port();
    }

    // static method getInstance
    public static PotableWaterTank getInstance() {
        return instance;
    }

    // inner methods
    public String innerVersion() {
        return "PotableWaterTank // " + manufacturer + " - " + type + " - " + id;
    }

    public boolean innerLock() {
        isLocked = true;
        return true;
    }

    public boolean innerUnlock() {
        isLocked = false;
        return  false;
    }

    public int innerTakeOut(int amount) {
        this.amount = this.amount - amount;
        return this.amount;
    }

    public int innerRefill() {
        this.amount = 1000;
        return amount;
    }

    public int innerRefill(int amount) {
        this.amount = this.amount + amount;
        return this.amount;
    }


    // inner class port
    public class Port implements IPotableWaterTank {
        public String version() {
            return innerVersion();
        }

        public boolean lock() {
            return innerLock();
        }

        public boolean unlock() {
            return innerUnlock();
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
