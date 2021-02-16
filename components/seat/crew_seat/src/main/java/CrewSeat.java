public class CrewSeat {
    // static instance
    private static CrewSeat instance = new CrewSeat();
    // port
    public Port port;
    private String manufacturer = "1103207 / 1044480";
    private String type = "team 15";
    private String id = "1103207 / 1044480";

    private boolean isNonSmokingSignOn;
    private boolean isSeatBeltSignOn;

    // private constructor
    private CrewSeat() {
        port = new Port();
    }

    // static method getInstance
    public static CrewSeat getInstance() {
        return instance;
    }

    // inner methods
    public String innerVersion() {
        return "WeatherRadar // " + manufacturer + " - " + type + " - " + id;
    }

    public int innerAssign(CrewMember crewMember) {
        return 0; // ?
    }

    public boolean innerNonSmokingSignOn() {
        isNonSmokingSignOn = true;
        return true;
    }

    public boolean innerNonSmokingSignOff() {
        isNonSmokingSignOn = false;
        return false;
    }

    public boolean innerBeltSignOn() {
        isSeatBeltSignOn = true;
        return true;
    }

    public boolean innerBeltSignOff() {
        isSeatBeltSignOn = false;
        return false;
    }

    public boolean innerReadingLightOn() {
        return true; // ?
    }

    public boolean innerReadingLightOff() {
        return false; // ?
    }

    // inner class port
    public class Port implements ICrewSeat {
        public String version() {
            return innerVersion();
        }

        public int assign(CrewMember crewMember) {
            return innerAssign(crewMember);
        }

        public boolean nonSmokingSignOn() {
            return innerNonSmokingSignOn();
        }

        public boolean nonSmokingSignOff() {
            return innerNonSmokingSignOff();
        }

        public boolean beltSignOn() {
            return innerBeltSignOn();
        }

        public boolean beltSignOff() {
            return innerBeltSignOff();
        }

        public boolean readingLightOn() {
            return innerReadingLightOn();
        }

        public boolean readingLightOff() {
            return innerReadingLightOff();
        }
	}
}
