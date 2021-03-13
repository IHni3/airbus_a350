import java.util.Locale;

public class RightNavigationLight {
    // static instance
    private static RightNavigationLight instance = new RightNavigationLight();
    // port
    public Port port;
    private String manufacturer = "1103207 / 1044480";
    private String type = "team 15";
    private String id = "1103207 / 1044480";

    private boolean isOn;

    // private constructor
    private RightNavigationLight() {
        port = new Port();
    }

    // static method getInstance
    public static RightNavigationLight getInstance() {
        return instance;
    }

    // inner methods
    public String innerVersion() {
        return "RightNavigationLight // " + manufacturer + " - " + type + " - " + id;
    }

    public LightType innerSetLightType(String type) {
        LightType lightType = LightType.valueOf(type.toUpperCase(Locale.ENGLISH));
        if (lightType == LightType.GREEN) {
            innerOn();
        } else {
            innerOff();
        }
        return lightType;
    }

    public Position innerSetPosition(String position) {
        Position _position = Position.valueOf(position.toUpperCase(Locale.ROOT));
        if (_position == Position.STARBOARD) {
            innerOn();
        } else {
            innerOff();
        }
        return _position;
    }

    public boolean innerOn() {
        isOn = true;
        return true;
    }

    public boolean innerOff() {
        isOn = false;
        return false;
    }

    // inner class port
    public class Port implements IRightNavigationLight {
        public String version() {
            return innerVersion();
        }

        public LightType setLightType(String type) {
            return innerSetLightType(type);
        }

        public Position setPosition(String position) {
            return innerSetPosition(position);
        }

        public boolean on() {
            return innerOn();
        }

        public boolean off() {
            return innerOff();
        }
	}
}
