package event.sat_com;

public class SatComConnect {
    private String satelite;
    private String frequency;

    public SatComConnect(String satelite, String frequency) {
        this.satelite = satelite;
        this.frequency = frequency;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getSatelite() {
        return satelite;
    }

    public void setSatelite(String satelite) {
        this.satelite = satelite;
    }

    public String toString() {
        return "Event: SatCom - Connect";
    }
}
