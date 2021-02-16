package event.weather_radar;

public class WeatherRadarScan {
    String environment;

    public WeatherRadarScan(String environment) {
        this.environment = environment;
    }

    public String toString() {
        return "Event: WeatherRadar - Scan";
    }
}