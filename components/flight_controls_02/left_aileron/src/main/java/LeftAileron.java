public class LeftAileron {
    // static instance
    private static LeftAileron instance = new LeftAileron();
    // port
    public Port port;
    //student
    private String manufacturer = "9899545";
    private String type = "team 14";
    private String id = "9899545";

    //attributes
    private int degreeLeftAileron = 0;

    // private constructor
    private LeftAileron() {
        port = new Port();
    }

    // static method getInstance
    public static LeftAileron getInstance() {
        return instance;
    }

    // inner methods
    public String innerVersion() {
        return "LeftAileron // " + manufacturer + " - " + type + " - " + id;
    }

    public int innerNeutral() {
        degreeLeftAileron = 0;
        return degreeLeftAileron;
    }


    public int innerUp(int degree) {
        degreeLeftAileron = degreeLeftAileron+degree;
        return degreeLeftAileron;
    }
    public int innerDown(int degree) {
        degreeLeftAileron = degreeLeftAileron-degree;
        return degreeLeftAileron;
    }

    public int innerFullUp() {
        degreeLeftAileron = 90;
        return degreeLeftAileron;
    }
    public int innerFullDown() {
        degreeLeftAileron = -90;
        return degreeLeftAileron;
    }

    // inner class port
    public class Port implements ILeftAileron {
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