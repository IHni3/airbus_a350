package factory;

import configuration.Configuration;
import recorder.FlightRecorder;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class TurbulentAirFlowSensorFactory {
    public static Object build () {
        Object turbulentAirFlowSensorPort = null;

        try {
            URL[] urls = {new File(Configuration.instance.pathToTurbulentAirFlowSensorJavaArchive).toURI().toURL()};
            URLClassLoader urlClassLoader = new URLClassLoader(urls, TurbulentAirFlowSensorFactory.class.getClassLoader());
            Class turbulentAirFlowSensorClass = Class.forName("TurbulentAirFlowSensor", true, urlClassLoader);
            FlightRecorder.instance.insert("TurbulentAirFlowSensorFactory", "turbulentAirFlowSensorClass: " + turbulentAirFlowSensorClass.hashCode());

            Object turbulentAirFlowSensorInstance = turbulentAirFlowSensorClass.getMethod("getInstance").invoke(null);
            FlightRecorder.instance.insert("TurbulentAirFlowSensorFactory", "turbulentAirFlowSensorInstance: " + turbulentAirFlowSensorInstance.hashCode());

            turbulentAirFlowSensorPort = turbulentAirFlowSensorClass.getDeclaredField("port").get(turbulentAirFlowSensorInstance);
            FlightRecorder.instance.insert("TurbulentAirFlowSensorFactory", "turbulentAirFlowSensorPort: " + turbulentAirFlowSensorPort.hashCode());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return turbulentAirFlowSensorPort;
    }
}
