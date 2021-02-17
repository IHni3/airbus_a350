public interface ICargoCompartmentLight {
    String version();

    boolean on();
    boolean off();
    void dim();
}