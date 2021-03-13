public interface IRadarAltimeter {
    String version();

    boolean on();

    void send(String radioWave);

    int receive(String radioWave);

    int measureAltitude();

    boolean off();

}
