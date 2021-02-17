package event.radar;

public class RadarScan {

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    private String environment;

    public RadarScan (String environment){
        this.environment = environment;
    }

    public String toString() {
        return "Event: Radar - Scan";
    }
}
