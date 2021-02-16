package event.gps;

public class GPSConnect {
    private String satelite;

    public String getSatelite () {
        return satelite;
    }

    public void setSatelite (String satelite) {
        this.satelite = satelite;
    }

    public GPSConnect (String satelite) {
        this.satelite = satelite;
    }

    public String toString () {
        return "Event: GPS - Connect - " + satelite;
    }
}
