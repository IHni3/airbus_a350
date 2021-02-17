
public class AntiCollisionLight {
    // static instance
    private static AntiCollisionLight instance = new AntiCollisionLight();
    // port
    public Port port;

    //student
    private String manufacturer = "6143217";
    private String type = "team 14";
    private String id = "6143217";

    //variables
    boolean enabled;

    //constants


    // private constructor
    private AntiCollisionLight() {
        port = new Port();
        enabled = false;
    }

    // static method getInstance
    public static AntiCollisionLight getInstance() {
        return instance;
    }

    // inner methods
    public String innerVersion() {
        return "AntiCollisionLight // " + manufacturer + " - " + type + " - " + id;
    }

    public boolean innerSetEnabled(boolean enabled){
        this.enabled = enabled;
        return enabled;
    }

    // inner class port
    public class Port implements IAntiCollisionLight {

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