public interface IRadarAltimeter {
    String version();

    boolean on();

    void send(String radioWave);

    int recieve(String radioWave);

    int measureAltitude();

    boolean off();

}
