public interface IOxygenSensor {
    int measure(String airFlow);
    boolean alarm();
}
