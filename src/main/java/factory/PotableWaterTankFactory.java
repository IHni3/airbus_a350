package factory;

import configuration.Configuration;
import recorder.FlightRecorder;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class PotableWaterTankFactory {
    public static Object build() {
        Object potableWaterTankPort = null;

        try {
            URL[] urls = {new File(Configuration.instance.pathToPotableWaterTankJavaArchive).toURI().toURL()};
            URLClassLoader urlClassLoader = new URLClassLoader(urls, PotableWaterTankFactory.class.getClassLoader());
            Class potableWaterTankClass = Class.forName("PotableWaterTank", true, urlClassLoader);
            FlightRecorder.instance.insert("PotableWaterTankFactory", "potableWaterTankClass: " + potableWaterTankClass.hashCode());

            Object potableWaterTankInstance = potableWaterTankClass.getMethod("getInstance").invoke(null);
            FlightRecorder.instance.insert("PotableWaterTankFactory", "potableWaterTankInstance: " + potableWaterTankInstance.hashCode());

            potableWaterTankPort = potableWaterTankClass.getDeclaredField("port").get(potableWaterTankInstance);
            FlightRecorder.instance.insert("PotableWaterTankFactory", "potableWaterTankPort: " + potableWaterTankPort.hashCode());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return potableWaterTankPort;
    }
}
