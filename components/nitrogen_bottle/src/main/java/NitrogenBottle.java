public class NitrogenBottle {
    // static instance
    private static NitrogenBottle instance = new NitrogenBottle();
    // port
    public Port port;
    private String manufacturer = "Student 2627585 / 4485500";
    private int amount = 250;

    // private constructor
    private NitrogenBottle () {
        port = new Port();
    }

    // static method getInstance
    public static NitrogenBottle getInstance() {
        return instance;
    }

    // inner methods
    public String innerVersion() {
        return "NitrogenBottle // " + manufacturer;
    }

    public int innerTakeOut (int amount) {
        if(this.amount < amount)
            throw new RuntimeException("Insufficient Nitrogen!");
        this.amount -= amount;
        return this.amount;
    }

    public int innerRefill () {
        this.amount = 250;
        return this.amount;
    }

    public int innerRefill (int amount) {
        this.amount = Math.min(this.amount + amount,250);
        return this.amount;
    }


    // inner class port
    public class Port implements INitrogenBottle {
        public String version() {
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