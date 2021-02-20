public class PitotTube {
    // static instance
    private static PitotTube instance = new PitotTube();
    // port
    public Port port;
    private String manufacturer = "8843476 / 9668368";
    private String type = "team 16";
    private String id = "8843476 / 9668368";

    private boolean isCleaned;
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
        //TODO
    }

    public void innerClean(){
        isCleaned = true;
    }

    public int innerMeasureTotalPressure(){
        //TODO
    }

    public int innerMeasureVelocity(){
        velocity = innerMeasureTotalPressure() - innerMeasureStaticPressure();
        return velocity;
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
            return innerMeasureTotalPressure();
        }
        public int measureVelocity(){
            return innerMeasureVelocity();
        }

        public void clean(){
            innerClean();
        }

	}
}
