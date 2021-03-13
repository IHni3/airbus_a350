public class GPS {
    // static instance
    private static GPS instance = new GPS();
    // port
    public Port port;
    private String manufacturer = "Student 2627585 / 4485500";
    private boolean isOn = false;
    private boolean isConnected = false;

    // private constructor
    private GPS () {
        port = new Port();
    }

    // static method getInstance
    public static GPS getInstance() {
        return instance;
    }

    // inner methods
    public String innerVersion() {
        return "GPS // " + manufacturer;
    }

    public boolean innerOn() {
        isOn = true;
        return isOn;
    }

    public boolean innerOff() {
        isOn = false;
        isConnected = false;
        return isOn;
    }

    public boolean innerConnect (String satelite) {
        System.out.println("***Connecting to " + satelite + "***");
        isConnected = true;
        return true;
    }

    public void innerSend (String request) {
        System.out.println("***Sending " + request + "***");
    }

    public String innerReceive () {
        return "GPS DATA EXAMPLE";
    }


    // inner class port
    public class Port implements IGPS {
        public String version() {
            return innerVersion();
        }

        public boolean on() {
            return innerOn();
        }

        public boolean off() {
            return innerOff();
        }

        @Override
        public boolean connect (String satelite) {
            return innerConnect(satelite);
        }

        @Override
        public void send (String request) {
            innerSend(request);
        }

        @Override
        public String receive () {
            return innerReceive();
        }
    }
}