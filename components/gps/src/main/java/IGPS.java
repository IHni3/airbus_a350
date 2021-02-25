public interface IGPS {
    String version();

    boolean on();

    boolean off();

    boolean connect(String satelite);

    void send(String request);

    String receive();
}