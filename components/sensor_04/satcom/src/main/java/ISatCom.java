public interface ISatCom {
    boolean on();
    boolean connect(String satelite, String frequency);
    void send(String request);
    String receive();
    boolean off();

    String version();
}
