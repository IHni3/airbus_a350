public class Spoiler {
    // static instance
    private static Spoiler instance = new Spoiler();
    // port
    public Port port;

    private String manufacturer = "9899545";
    private String type = "team 14";
    private String id = "9899545";

    //attributes
    private int degreeSpoiler = 0;

    // private constructor
    private Spoiler() {
        port = new Port();
    }

    // static method getInstance
    public static Spoiler getInstance() {
        return instance;
    }

    // inner methods
    public String innerVersion() {
        return "Spoiler // " + manufacturer + " - " + type + " - " + id;
    }

    public int innerSpoilerNeutral() {
        degreeSpoiler=0;
        return degreeSpoiler;
    }

    public int innerSpoilerFullUp() {
        degreeSpoiler=90;
        return degreeSpoiler;
    }

    public int innerSpoilerUp(int degree)
    {
        degreeSpoiler=degreeSpoiler+degree;
        return degreeSpoiler;
    }

    public int innerSpoilerDown(int degree)
    {
        degreeSpoiler=degreeSpoiler-degree;
        return degreeSpoiler;
    }

    // inner class port
    public class Port implements ISpoiler {
        public String version() {
            return innerVersion();
        }

        public int neutral() {
            return innerSpoilerNeutral();
        }

        public int fullUp() {
            return innerSpoilerFullUp();
        }

        public int up(int degree) {
            return innerSpoilerUp(degree);
        }

        public int down(int degree){ return innerSpoilerDown(degree); }
    }
}