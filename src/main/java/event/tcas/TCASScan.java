package event.tcas;

public class TCASScan {
    private String environment;

    public TCASScan (String environment) {
        this.environment = environment;
    }

    public String getEnvironment () {
        return environment;
    }

    public void setEnvironment (String environment) {
        this.environment = environment;
    }

    @Override
    public String toString () {
        return "Event: TCAS - Scan - " + environment;
    }
}
