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
        if(amount > this.amount){
            this.amount = 0;
            return amount;
        }else{
            this.amount -= amount;
            return amount;
        }
    }

    public int innerRefill() {
        this.amount = 1000;
        return amount;
    }

    public int innerRefill(int amount) {
        if(this.amount + amount > 1000){
            int oldAmount = this.amount;
            this.amount = 1000;

            return oldAmount + amount - 1000;
        }else{
            this.amount += amount;
            return 0;
        }
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
