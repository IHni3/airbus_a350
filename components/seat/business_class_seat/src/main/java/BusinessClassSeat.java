public class BusinessClassSeat {
    // static instance
    private static BusinessClassSeat instance = new BusinessClassSeat();
    // port
    public Port port;
    private String manufacturer = "1103207 / 1044480";
    private String type = "team 15";
    private String id = "1103207 / 1044480";

    private Passenger passenger;
    private int level;
    private boolean isNonSmokingSignOn;
    private boolean isSeatBeltSignOn;

    // private constructor
    private BusinessClassSeat() {
        port = new Port();
    }

    // static method getInstance
    public static BusinessClassSeat getInstance() {
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
        isNonSmokingSignOn = true;
        return true;
    }

    public boolean innerNonSmokingSignOff() {
        isNonSmokingSignOn = false;
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

    public void innerReadingLightOn() {
        // pass
    }

    public void innerReadingLightOff() {
        // pass
    }

    public int innerUpRight() {
        level = 0;
        return 0;
    }

    public int innerLevel1() {
        level = -10;
        return -10;
    }

    public int innerLevel2() {
        level = -20;
        return -20;
    }


    // inner class port
    public class Port implements IBusinessClassSeat {
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

        public void readingLightOn() {
            innerReadingLightOn();
        }

        public void readingLightOff() {
            innerReadingLightOff();
        }

        public int upRight() {
            return innerUpRight();
        }

        public int level1() {
            return innerLevel1();
        }

        public int level2() {
            return innerLevel2();
        }
	}
}
