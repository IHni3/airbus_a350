public interface IAirConditioning {
    String version();
    boolean on();
    String clean(String airFlow);
    int heat(String airFlow, int temperatur);
    int cool(String airFlow, int temperatur);
    boolean off();

}
