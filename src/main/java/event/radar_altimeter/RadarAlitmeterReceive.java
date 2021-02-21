package event.radar_altimeter;

public class RadarAlitmeterReceive {
    String radioWave;

    public RadarAlitmeterReceive(String radioWave) {
        this.radioWave = radioWave;
    }

    public String toString() {
        return "Event: RadarAltimeter - RadarAlitmeter - Receive - RadioWave: " + radioWave;
    }
}
