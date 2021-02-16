package factory;

import configuration.Configuration;
import recorder.FlightRecorder;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class WeatherRadarFactory {

    public static Object build() {
        return FactoryUtils.build("WeatherRadarFactory",
                Configuration.instance.pathToWeatherRadarJavaArchive,
                "WeatherRadar");
    }
}