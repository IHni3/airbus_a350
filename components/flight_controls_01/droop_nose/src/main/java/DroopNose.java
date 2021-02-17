public class DroopNose {
    // static instance
    private static DroopNose instance = new DroopNose();
    // port
    public Port port;
    private String manufacturer = "4485500 / 2627585";
    private String type = "team 17";
    private String id = "4485500 / 2627585";

    private int degree;

    // private constructor
    private DroopNose() {
        port = new Port();
    }

    // static method getInstance
    public static DroopNose getInstance() {
        return instance;
    }

    // inner methods
    public String innerVersion() {
        return "DroopNose // " + manufacturer + " - " + type + " - " + id;
    }

    public int innerFullDown() {
        degree = -10;
        return degree;
    }

    public int innerDown(int degree) {
        this.degree = this.degree - degree;
        return this.degree;
    }

    public int innerUp(int degree) {
        this.degree = this.degree + degree;
        return this.degree;
    }

    public int innerNeutral() {
        degree = 0;
        return degree;
    }


    // inner class port
    public class Port implements IDroopNose {
        public String version() {
            return innerVersion();
        }

        public int fullDown() {
            return innerFullDown();
        }

        public int down(int degree) {
            return innerDown(degree);
        }

        public int up(int degree) {
            return innerUp(degree);
        }

        public int neutral() {
            return innerNeutral();
        }


	}
}
