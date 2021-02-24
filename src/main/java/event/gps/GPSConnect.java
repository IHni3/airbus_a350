package event.gps;

public class GPSConnect {
    private String satelite;

    public GPSConnect(String satelite) {
        this.satelite = satelite;
    }

    public String getSatelite() {
        return satelite;
    }

    public void setSatelite(String satelite) {
        this.satelite = satelite;
    }

    @Override
    public String toString() {
        return "Event: GPS - Connect - " + satelite;
    }
}
