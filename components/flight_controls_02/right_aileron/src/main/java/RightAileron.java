public class RightAileron {
    // static instance
    private static RightAileron instance = new RightAileron();
    // port
    public Port port;
    //student
    private String manufacturer = "9899545";
    private String type = "team 14";
    private String id = "9899545";

    //attributes
    private int degreeRightAileron = 0;

    // private constructor
    private RightAileron() {
        port = new Port();
    }

    // static method getInstance
    public static RightAileron getInstance() {
        return instance;
    }

    // inner methods
    public String innerVersion() {
        return "RightAileron // " + manufacturer + " - " + type + " - " + id;
    }

    public int innerNeutral() {
        degreeRightAileron = 0;
        return degreeRightAileron;
    }


    public int innerUp(int degree) {
        degreeRightAileron = degreeRightAileron+degree;
        return degreeRightAileron;
    }
    public int innerDown(int degree) {
        degreeRightAileron = degreeRightAileron-degree;
        return degreeRightAileron;
    }

    public int innerFullUp() {
        degreeRightAileron = 90;
        return degreeRightAileron;
    }
    public int innerFullDown() {
        degreeRightAileron = -90;
        return degreeRightAileron;
    }

    // inner class port
    public class Port implements IRightAileron {
        public String version() {
            return innerVersion();
        }

        public int neutral(){ return innerNeutral();}

        public int fullUp(){ return innerFullUp();}

        public int fullDown(){ return innerFullDown();}

        public int up(int degree){ return innerUp(degree);}

        public int down(int degree){ return innerDown(degree);}
    }
}