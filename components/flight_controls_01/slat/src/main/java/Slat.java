public class Slat {
    // static instance
    private static Slat instance = new Slat();
    // port
    public Port port;

    //student
    private String manufacturer = "6143217";
    private String type = "team 14";
    private String id = "6143217";

    //variables
    private int degree = 0;

    //constants
    private static final int NEUTRAL = 0;
    private static final int MAX_DEGREE = 0;
    private static final int MIN_DEGREE = -90;
    private static final int FULL_DOWN = MIN_DEGREE;

    // private constructor
    private Slat() {
        port = new Port();
    }

    // static method getInstance
    public static Slat getInstance() {
        return instance;
    }

    // inner methods
    public String innerVersion() {
        return "Slat // " + manufacturer + " - " + type + " - " + id;
    }

    public void innerDegreeSet(int degree) {
        if(degree <= MAX_DEGREE && degree >= MIN_DEGREE)
            this.degree = degree;
    }
    public int innerDegreeGet() {
        return degree;
    }


    // inner class port
    public class Port implements ISlat {

        @Override
        public String version() {
            return innerVersion();
        }

        @Override
        public int neutral() {
            innerDegreeSet(NEUTRAL);
            return innerDegreeGet();
        }

        @Override
        public int fullDown() {
            innerDegreeSet(FULL_DOWN);
            return innerDegreeGet();
        }

        @Override
        public int down(int degree) {
            var newValue = innerDegreeGet() - degree;
            innerDegreeSet(newValue);
            return innerDegreeGet();
        }

        @Override
        public int up(int degree) {
            var newValue = innerDegreeGet() + degree;
            innerDegreeSet(newValue);
            return innerDegreeGet();
        }
    }
}