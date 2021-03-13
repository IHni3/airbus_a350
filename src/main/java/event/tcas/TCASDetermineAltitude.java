package event.tcas;

public class TCASDetermineAltitude {
    private String environment;

    public TCASDetermineAltitude(String environment) {
        this.environment = environment;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    @Override
    public String toString() {
        return "Event: TCAS - DetermineAltitude - " + environment;
    }
}
