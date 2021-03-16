public interface ITCAS {
    String version();

    boolean on();

    boolean off();

    boolean connect(String frequency);

    boolean scan(String environment);

    boolean alarm();

    int determineAltitude(String environment);

    int setAltitude(int value);
}