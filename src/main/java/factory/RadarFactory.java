package factory;

import configuration.Configuration;
import recorder.FlightRecorder;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class RadarFactory {
    public static Object build() {
        Object radarPort = null;

        try {
            URL[] urls = {new File(Configuration.instance.pathToRadarJavaArchive).toURI().toURL()};
            URLClassLoader urlClassLoader = new URLClassLoader(urls, RadarFactory.class.getClassLoader());
            Class radarClass = Class.forName("Radar", true, urlClassLoader);
            FlightRecorder.instance.insert("RadarFactory", "radarClass: " + radarClass.hashCode());

            Object radarInstance = radarClass.getMethod("getInstance").invoke(null);
            FlightRecorder.instance.insert("RadarFactory", "radarInstance: " + radarInstance.hashCode());

            radarPort = radarClass.getDeclaredField("port").get(radarInstance);
            FlightRecorder.instance.insert("RadarFactory", "radarPort: " + radarPort.hashCode());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return radarPort;
    }
}
