package factory;

import configuration.Configuration;

public class WeatherRadarFactory {

    public static Object build() {
        return FactoryUtils.build("WeatherRadarFactory",
                Configuration.instance.pathToWeatherRadarJavaArchive,
                "WeatherRadar");
    }
}