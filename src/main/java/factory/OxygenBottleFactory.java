package factory;

import configuration.Configuration;
import recorder.FlightRecorder;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class OxygenBottleFactory {
    public static Object build() {
        Object oxygenBottlePort = null;

        try {
            URL[] urls = {new File(Configuration.instance.pathToOxygenBottleJavaArchive).toURI().toURL()};
            URLClassLoader urlClassLoader = new URLClassLoader(urls, OxygenBottleFactory.class.getClassLoader());
            Class oxygenBottleClass = Class.forName("OxygenBottle", true, urlClassLoader);
            FlightRecorder.instance.insert("OxygenBottleFactory", "oxygenBottleClass: " + oxygenBottleClass.hashCode());

            Object oxygenBottleInstance = oxygenBottleClass.getMethod("getInstance").invoke(null);
            FlightRecorder.instance.insert("OxygenBottleFactory", "oxygenBottleInstance: " + oxygenBottleInstance.hashCode());

            oxygenBottlePort = oxygenBottleClass.getDeclaredField("port").get(oxygenBottleInstance);
            FlightRecorder.instance.insert("OxygenBottleFactory", "oxygenBottlePort: " + oxygenBottlePort.hashCode());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return oxygenBottlePort;
    }
}
