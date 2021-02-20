public class PitotTube {
    // static instance
    private static PitotTube instance = new PitotTube();
    // port
    public Port port;
    private String manufacturer = "8843476 / 9668368";
    private String type = "team 16";
    private String id = "8843476 / 9668368";

    private boolean isCleanded;
    private int velocity;

    // private constructor
    private PitotTube() {
        port = new Port();
    }

    // static method getInstance
    public static PitotTube getInstance() {
        return instance;
    }

    // inner methods
    public String innerVersion() {
        return "PitotTube // " + manufacturer + " - " + type + " - " + id;
    }

    public int innerMeasureStaticPressure() {
    // TODO
    }


    // inner class port
    public class Port implements IPitotTube {
        public String version() {
            return innerVersion();
        }

        public int measureStaticPressure() {
            return innerMeasureStaticPressure();
        }

        public int measureTotalPressure(){
            return 1;
        }
        public int measureVelocity(){
            return 1;
        }

        public void clean(){

        }

	}
}
