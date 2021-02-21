package event.radar_altimeter;

public class RadarAltimeterReceive {
    String radioWave;

    public RadarAltimeterReceive(String radioWave) {
        this.radioWave = radioWave;
    }

    public String toString() {
        return "Event: RadarAltimeter - RadarAlitmeter - Receive - RadioWave: " + radioWave;
    }
}
