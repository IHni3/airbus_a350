package factory;

import configuration.Configuration;
import recorder.FlightRecorder;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class GpsFactory {
    public static Object build() {
        Object gpsPort = null;

        try {
            URL[] urls = {new File(Configuration.instance.pathToGpsJavaArchive).toURI().toURL()};
            URLClassLoader urlClassLoader = new URLClassLoader(urls, GpsFactory.class.getClassLoader());
            Class gpsClass = Class.forName("GPS", true, urlClassLoader);
            FlightRecorder.instance.insert("GpsFactory", "gpsClass: " + gpsClass.hashCode());

            Object gpsInstance = gpsClass.getMethod("getInstance").invoke(null);
            FlightRecorder.instance.insert("GpsFactory", "gpsInstance: " + gpsInstance.hashCode());

            gpsPort = gpsClass.getDeclaredField("port").get(gpsInstance);
            FlightRecorder.instance.insert("GpsFactory", "gpsPort: " + gpsPort.hashCode());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return gpsPort;
    }
}
