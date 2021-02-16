public interface IWeatherRadar {
    String version();

    boolean on();

    boolean off();

    boolean scan(String environment);
}