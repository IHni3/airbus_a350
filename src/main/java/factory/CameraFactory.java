package factory;

import configuration.Configuration;
import recorder.FlightRecorder;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class CameraFactory {
    public static Object build() {
        Object cameraPort = null;

        try {
            URL[] urls = {new File(Configuration.instance.pathToCameraJavaArchive).toURI().toURL()};
            URLClassLoader urlClassLoader = new URLClassLoader(urls, CameraFactory.class.getClassLoader());
            Class cameraClass = Class.forName("Camera", true, urlClassLoader);
            FlightRecorder.instance.insert("CameraFactory", "cameraClass: " + cameraClass.hashCode());

            Object cameraInstance = cameraClass.getMethod("getInstance").invoke(null);
            FlightRecorder.instance.insert("CameraFactory", "cameraInstance: " + cameraInstance.hashCode());

            cameraPort = cameraClass.getDeclaredField("port").get(cameraInstance);
            FlightRecorder.instance.insert("CameraFactory", "cameraPort: " + cameraPort.hashCode());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return cameraPort;
    }
}
