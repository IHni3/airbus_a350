package factory;

import recorder.FlightRecorder;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class FactoryUtils {

    public static Object build(String factoryName, String archivePath, String className) {
        Object port = null;

        try {
            URL[] urls = {new File(archivePath).toURI().toURL()};
            URLClassLoader urlClassLoader = new URLClassLoader(urls, FactoryUtils.class.getClassLoader());
            Class<?> archiveClass = Class.forName(className, true, urlClassLoader);
            FlightRecorder.instance.insert(factoryName, className + "Class: " + archiveClass.hashCode());

            Object archiveInstance = archiveClass.getMethod("getInstance").invoke(null);
            FlightRecorder.instance.insert(factoryName, className + "Instance: " + archiveInstance.hashCode());

            port = archiveClass.getDeclaredField("port").get(archiveInstance);
            FlightRecorder.instance.insert(factoryName, className+ "Port: " + port.hashCode());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return port;
    }
}
