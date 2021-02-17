public class CheckPoint {
    private int sequenceID;
    private String name;
    private String latitude;
    private String longitude;

    public CheckPoint(int sequenceID, String name, String latitude, String longitude) {
        this.sequenceID = sequenceID;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getSequenceID() {
        return sequenceID;
    }

    public String getName() {
        return name;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String toString() {
        return "{" +
                    " sequenceID: " + sequenceID +
                    " name: " + name +
                    " latitude: " + latitude +
                    " longitude: " + longitude +
                " }";
    }
}
