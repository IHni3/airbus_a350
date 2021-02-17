package factory;

import configuration.Configuration;
import recorder.FlightRecorder;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class SatComFactory {
    public static Object build() {
        Object satComPort = null;

        try {
            URL[] urls = {new File(Configuration.instance.pathToSatComJavaArchive).toURI().toURL()};
            URLClassLoader urlClassLoader = new URLClassLoader(urls, SatComFactory.class.getClassLoader());
            Class satComClass = Class.forName("SatCom", true, urlClassLoader);
            FlightRecorder.instance.insert("SatComFactory", "satComClass: " + satComClass.hashCode());

            Object satComInstance = satComClass.getMethod("getInstance").invoke(null);
            FlightRecorder.instance.insert("SatComFactory", "satComInstance: " + satComInstance.hashCode());

            satComPort = satComClass.getDeclaredField("port").get(satComInstance);
            FlightRecorder.instance.insert("SatComFactory", "satComPort: " + satComPort.hashCode());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return satComPort;
    }
}
