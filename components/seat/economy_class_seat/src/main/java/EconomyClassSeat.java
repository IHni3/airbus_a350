public class EconomyClassSeat {
    // static instance
    private static EconomyClassSeat instance = new EconomyClassSeat();
    // port
    public Port port;
    private String manufacturer = "1103207 / 1044480";
    private String type = "team 15";
    private String id = "1103207 / 1044480";

    private Passenger passenger;
    private int level;
    private boolean isSmokingSignOn;
    private boolean isSeatBeltSignOn;

    // private constructor
    private EconomyClassSeat() {
        port = new Port();
    }

    // static method getInstance
    public static EconomyClassSeat getInstance() {
        return instance;
    }

    // inner methods
    public String innerVersion() {
        return "WeatherRadar // " + manufacturer + " - " + type + " - " + id;
    }

    public int innerAssign(Passenger passenger) {
        this.passenger = passenger;
        return level;
    }

    public boolean innerNonSmokingSignOn() {
        isSmokingSignOn = true;
        return true;
    }

    public boolean innerNonSmokingSignOff() {
        isSmokingSignOn = false;
        return false;
    }

    public boolean innerSeatBeltSignOn() {
        isSeatBeltSignOn = true;
        return true;
    }

    public boolean innerSeatBeltSignOff() {
        isSeatBeltSignOn = false;
        return false;
    }

    public void innerUpRight() {
        level = 0;
    }

    public int innerLevel1() {
        level = -10;
        return -10;
    }

    // inner class port
    public class Port implements IEconomyClassSeat {
        public String version() {
            return innerVersion();
        }

        public int assign(Passenger passenger) {
            return innerAssign(passenger);
        }

        public boolean nonSmokingSignOn() {
            return innerNonSmokingSignOn();
        }

        public boolean nonSmokingSignOff() {
            return innerNonSmokingSignOff();
        }

        public boolean seatBeltSignOn() {
            return innerSeatBeltSignOn();
        }

        public boolean seatBeltSignOff() {
            return innerSeatBeltSignOff();
        }

        public void upRight() {
            innerUpRight();
        }

        public int level1() {
            return innerLevel1();
        }
	}
}
