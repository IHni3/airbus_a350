package factory;

import configuration.Configuration;
import recorder.FlightRecorder;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class NitrogenBottleFactory {
    public static Object build() {
        Object nitrogenBottlePort = null;

        try {
            URL[] urls = {new File(Configuration.instance.pathToNitrogenBottleJavaArchive).toURI().toURL()};
            URLClassLoader urlClassLoader = new URLClassLoader(urls, NitrogenBottleFactory.class.getClassLoader());
            Class nitrogenBottleClass = Class.forName("NitrogenBottle", true, urlClassLoader);
            FlightRecorder.instance.insert("NitrogenBottleFactory", "nitrogenBottleClass: " + nitrogenBottleClass.hashCode());

            Object nitrogenBottleInstance = nitrogenBottleClass.getMethod("getInstance").invoke(null);
            FlightRecorder.instance.insert("NitrogenBottleFactory", "nitrogenBottleInstance: " + nitrogenBottleInstance.hashCode());

            nitrogenBottlePort = nitrogenBottleClass.getDeclaredField("port").get(nitrogenBottleInstance);
            FlightRecorder.instance.insert("NitrogenBottleFactory", "nitrogenBottlePort: " + nitrogenBottlePort.hashCode());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return nitrogenBottlePort;
    }
}
