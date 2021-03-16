public class HydraulicPump {
    // static instance
    private static HydraulicPump instance = new HydraulicPump();
    // port
    public Port port;
    private String manufacturer = "9282087/5404118";
    private String type = "team 13";
    private String id = "9282087/5404118";

    private int amount = 100;

    // private constructor
    private HydraulicPump() {
        port = new Port();
    }

    // static method getInstance
    public static HydraulicPump getInstance() {
        return instance;
    }

    // inner methods
    public String innerVersion() {
        return "HydraulicPump // " + manufacturer + " - " + type + " - " + id;
    }

    public int innerCompress() {
        amount = amount / 100;
        return amount;
    }

    public int innerCompress(int amount) {
        this.amount = this.amount / amount;
        return this.amount;
    }

    public int innerDecompress() {
        amount = amount * 100;
        return amount;
    }

    public int innerRefillOil() {
        amount = 100;
        return amount;
    }

    public int innerRefillOil(int amount) {
        this.amount += amount;
        return this.amount;
    }


    // inner class port
    public class Port implements IHydraulicPump {
        public String version() {
            return innerVersion();
        }

        public int compress() {
            return innerCompress();
        }

        public int compress(int amount) {
            return innerCompress(amount);
        }

        public int decompress() {
            return innerDecompress();
        }

        public int refillOil() {
            return innerRefillOil();
        }

        public int refillOil(int amount) {
            return innerRefillOil(amount);
        }


	}
}
