public interface IOxygenSensor {
    String version();
    int measure(String airFlow);
    boolean alarm();
}
