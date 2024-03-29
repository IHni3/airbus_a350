package event.radar_altimeter;

public class RadarAltimeterSend {
    String radioWave;

    public RadarAltimeterSend(String radioWave) {
        this.radioWave = radioWave;
    }

    public String toString() {
        return "Event: RadarAltimeter - Send - RadioWave: " + radioWave;
    }

    public String getRadioWave() {
        return radioWave;
    }
}
