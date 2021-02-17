public class SatCom {
    // static instance
    private static SatCom instance = new SatCom();
    // port
    public Port port;
    private String manufacturer = "4485500 / 2627585";
    private String type = "team 17";
    private String id = "4485500 / 2627585";

    private boolean isConnected;

    // private constructor
    private SatCom() {
        port = new Port();
    }

    // static method getInstance
    public static SatCom getInstance() {
        return instance;
    }

    // inner methods
    public String innerVersion() {
        return "SatCom // " + manufacturer + " - " + type + " - " + id;
    }

    public boolean innerOn() {
        //connecting for the sake of doing anything
        isConnected = true;
        return true;
    }

    public boolean innerConnect(String satelite, String frequency) {
        isConnected = true;
        return isConnected;
    }

    public void innerSend(String request) {
        System.out.println(request);
    }

    public String innerReceive() {
        return "A350 you are about to crash";
    }

    public boolean innerOff() {
        isConnected = false;
        return false;
    }


    // inner class port
    public class Port implements ISatCom {
        public String version() {
            return innerVersion();
        }

        public boolean on() {
            return innerOn();
        }

        public boolean connect(String satelite, String frequency) {
            return innerConnect(satelite, String);
        }

        public void send(String request) {
        }

        public String receive() {
            return innerReceive();
        }

        public boolean off() {
            return innerOff();
        }


	}
}
