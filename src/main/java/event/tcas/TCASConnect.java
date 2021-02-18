package event.tcas;

public class TCASConnect {
    private String frequency;

    public String getFrequency () {
        return frequency;
    }

    public void setFrequency (String frequency) {
        this.frequency = frequency;
    }

    public TCASConnect (String frequency) {
        this.frequency = frequency;
    }

    @Override
    public String toString () {
        return "Event: TCAS - Connect - " + frequency;
    }
}
