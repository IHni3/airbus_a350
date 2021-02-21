public class Rudder {
    // static instance
    private static Rudder instance = new Rudder();
    // port
    public Port port;
    private String manufacturer = "9899545";
    private String type = "team 14";
    private String id = "9899545";
    //attributes
    private int degreeRudder = 0;

    // private constructor
    private Rudder() {
        port = new Port();
    }

    // static method getInstance
    public static Rudder getInstance() {
        return instance;
    }

    // inner methods
    public String innerVersion() {
        return "Rudder // " + manufacturer + " - " + type + " - " + id;
    }

    public int innerNeutral() {
        degreeRudder = 0;
        return degreeRudder;
    }


    public int innerLeft(int degree) {
        degreeRudder = degreeRudder +degree;
        return degreeRudder;
    }
    public int innerRight(int degree) {
        degreeRudder = degreeRudder -degree;
        return degreeRudder;
    }

    public int innerFullLeft() {
        degreeRudder = 90;
        return degreeRudder;
    }
    public int innerFullRight() {
        degreeRudder = -90;
        return degreeRudder;
    }

    // inner class port
    public class Port implements IRudder {
        public String version() {
            return innerVersion();
        }

        public int rudderNeutral(){ return innerNeutral();}

        public int rudderFullLeft(){ return innerFullLeft();}

        public int rudderFullRight(){ return innerFullRight();}

        public int rudderLeft(int degree){ return innerLeft(degree);}

        public int rudderRight(int degree){ return innerRight(degree);}
    }
}