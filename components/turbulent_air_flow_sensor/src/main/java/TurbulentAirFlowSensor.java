public class TurbulentAirFlowSensor {
    // static instance
    private static TurbulentAirFlowSensor instance = new TurbulentAirFlowSensor();
    // port
    public Port port;
    private String manufacturer = "Kilian Krampf / Johannes Löh";
    private String type = "team 17";
    private String id = "2627585 / 4485500";
    private boolean isAlarm = false;

    // private constructor
    private TurbulentAirFlowSensor () {
        port = new Port();
    }

    // static method getInstance
    public static TurbulentAirFlowSensor getInstance() {
        return instance;
    }

    // inner methods
    public String innerVersion() {
        return "WeatherRadar // " + manufacturer + " - " + type + " - " + id;
    }

    public int innerMeasure(String airFlow) {
        return airFlow.split("turbulence").length -1;
    }

    public boolean innerAlarm() {
        isAlarm = false;
        return isAlarm;
    }

    // inner class port
    public class Port implements ITurbulentAirFlowSensor {
        public String version() {
            return innerVersion();
        }

        @Override
        public int measure (String airFlow) {
            return innerMeasure(airFlow);
        }

        @Override
        public boolean alarm () {
            return innerAlarm();
        }

    }
}