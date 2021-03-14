public class OxygenBottle {
    // static instance
    private static OxygenBottle instance = new OxygenBottle();
    // port
    public Port port;
    private String manufacturer = "Student 2627585 / 4485500";
    private int amount = 100;

    // private constructor
    private OxygenBottle () {
        port = new Port();
    }

    // static method getInstance
    public static OxygenBottle getInstance () {
        return instance;
    }

    // inner methods
    public String innerVersion () {
        return "OxygenBottle // " + manufacturer;
    }

    public int innerTakeOut (int amount) {
        if (this.amount < amount)
            throw new RuntimeException("Insufficient Oxygen!");
        this.amount -= amount;
        return this.amount;
    }

    public int innerRefill () {
        this.amount = 100;
        return this.amount;
    }

    public int innerRefill (int amount) {
        this.amount = Math.min(this.amount + amount, 100);
        return this.amount;
    }


    // inner class port
    public class Port implements IOxygenBottle {
        public String version () {
            return innerVersion();
        }

        @Override
        public int takeOut (int amount) {
            return innerTakeOut(amount);
        }

        @Override
        public int refill () {
            return innerRefill();
        }

        @Override
        public int refill (int amount) {
            return innerRefill(amount);
        }
    }
}