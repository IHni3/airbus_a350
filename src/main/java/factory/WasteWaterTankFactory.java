package factory;

import configuration.Configuration;
import recorder.FlightRecorder;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class WasteWaterTankFactory {
    public static Object build() {
        Object wasteWaterTankPort = null;

        try {
            URL[] urls = {new File(Configuration.instance.pathToWasteWaterTankJavaArchive).toURI().toURL()};
            URLClassLoader urlClassLoader = new URLClassLoader(urls, WasteWaterTankFactory.class.getClassLoader());
            Class wasteWaterTankClass = Class.forName("WasteWaterTank", true, urlClassLoader);
            FlightRecorder.instance.insert("WasteWaterTankFactory", "wasteWaterTankClass: " + wasteWaterTankClass.hashCode());

            Object wasteWaterTankInstance = wasteWaterTankClass.getMethod("getInstance").invoke(null);
            FlightRecorder.instance.insert("WasteWaterTankFactory", "wasteWaterTankInstance: " + wasteWaterTankInstance.hashCode());

            wasteWaterTankPort = wasteWaterTankClass.getDeclaredField("port").get(wasteWaterTankInstance);
            FlightRecorder.instance.insert("WasteWaterTankFactory", "wasteWaterTankPort: " + wasteWaterTankPort.hashCode());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return wasteWaterTankPort;
    }
}
