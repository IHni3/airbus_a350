public interface IRadar {
    boolean on();
    boolean scan(String environment);
    boolean off();

    String version();
}
