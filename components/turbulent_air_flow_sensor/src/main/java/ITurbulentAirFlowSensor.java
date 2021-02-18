public interface ITurbulentAirFlowSensor {
    String version();

    int measure(String airFlow);

    boolean alarm();
}