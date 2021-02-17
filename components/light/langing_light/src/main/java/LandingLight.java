
public class LandingLight {
    // static instance
    private static LandingLight instance = new LandingLight();
    // port
    public Port port;

    //student
    private String manufacturer = "6143217";
    private String type = "team 14";
    private String id = "6143217";

    //variables
    boolean isOn;

    //constants


    // private constructor
    private LandingLight() {
        port = new Port();
        isOn = false;
    }

    // static method getInstance
    public static LandingLight getInstance() {
        return instance;
    }

    // inner methods
    public String innerVersion() {
        return "LandingLight // " + manufacturer + " - " + type + " - " + id;
    }

    public boolean innerSetEnabled(boolean enabled){
        this.isOn = enabled;
        return enabled;
    }

    // inner class port
    public class Port implements ILandingLight {

        @Override
        public String version() {
            return innerVersion();
        }

        @Override
        public boolean on() {
            return innerSetEnabled(true);
        }

        @Override
        public boolean off() {
            return innerSetEnabled(false);
        }
    }
}