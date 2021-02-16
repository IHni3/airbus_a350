package factory;

import configuration.Configuration;
import recorder.FlightRecorder;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class WeatherRadarFactory {
    public static Object build() {
        Object weatherRadarPort = null;

        try {
            URL[] urls = {new File(Configuration.instance.pathToWeatherRadarJavaArchive).toURI().toURL()};
            URLClassLoader urlClassLoader = new URLClassLoader(urls, WeatherRadarFactory.class.getClassLoader());
            Class weatherRadarClass = Class.forName("WeatherRadar", true, urlClassLoader);
            FlightRecorder.instance.insert("WeatherRadarFactory", "weatherRadarClass: " + weatherRadarClass.hashCode());

            Object weatherRadarInstance = weatherRadarClass.getMethod("getInstance").invoke(null);
            FlightRecorder.instance.insert("WeatherRadarFactory", "weatherRadarInstance: " + weatherRadarInstance.hashCode());

            weatherRadarPort = weatherRadarClass.getDeclaredField("port").get(weatherRadarInstance);
            FlightRecorder.instance.insert("WeatherRadarFactory", "weatherRadarPort: " + weatherRadarPort.hashCode());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return weatherRadarPort;
    }
}