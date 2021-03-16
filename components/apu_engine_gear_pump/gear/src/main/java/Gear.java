public class Gear {
    // static instance
    private static Gear instance = new Gear();
    // port
    public Port port;
    private String manufacturer = "9282087/5404118";
    private String type = "team 13";
    private String id = "9282087/5404118";

//    private ArrayList<Wheel> wheels;
    private boolean isDown;

    // private constructor
    private Gear() {
        port = new Port();
    }

    // static method getInstance
    public static Gear getInstance() {
        return instance;
    }

    // inner methods
    public String innerVersion() {
        return "Gear // " + manufacturer + " - " + type + " - " + id;
    }

//    public GearType innerSetGearType(String type) {
//    }

    public boolean innerDown() {
        isDown = true;
        return isDown;
    }

    public boolean innerUp() {
        isDown = false;
        return isDown;
    }

    public int innerSetBreak() {
        return 0;
    }

    public int innerSetBreak(int percentage) {
        return 0;
    }

    public int innerReleaseBreak() {
        return 0;
    }


    // inner class port
    public class Port implements IGear {
        public String version() {
            return innerVersion();
        }
//
//        public GearType setGearType(String type) {
//            return innerSetGearType(type);
//        }

        public boolean down() {
            return innerDown();
        }

        public boolean up() {
            return innerUp();
        }

        public int setBreak() {
            return innerSetBreak();
        }

        public int setBreak(int percentage) {
            return innerSetBreak(percentage);
        }

        public int releaseBreak() {
            return innerReleaseBreak();
        }


	}
}
