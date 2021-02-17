
public class CargoCompartmentLight {
    // static instance
    private static CargoCompartmentLight instance = new CargoCompartmentLight();
    // port
    public Port port;

    //student
    private String manufacturer = "6143217";
    private String type = "team 14";
    private String id = "6143217";

    //enum
    enum LightState {
        ON,OFF,DIM
    }

    //variables
    LightState lightState;

    //constants


    // private constructor
    private CargoCompartmentLight() {
        port = new Port();
        lightState = LightState.OFF;
    }

    // static method getInstance
    public static CargoCompartmentLight getInstance() {
        return instance;
    }

    // inner methods
    public String innerVersion() {
        return "CargoCompartmentLight // " + manufacturer + " - " + type + " - " + id;
    }

    public boolean innerSetEnabled(boolean enabled){
        if(enabled) {
            lightState = LightState.ON;
        } else {
            lightState = LightState.OFF;
        }
        return enabled;
    }
    public void innerDim() {
        lightState = LightState.DIM;
    }

    // inner class port
    public class Port implements ICargoCompartmentLight {

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

        @Override
        public void dim() {
            innerDim();
        }
    }
}