package factory;

import configuration.Configuration;
import recorder.FlightRecorder;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class VHFFactory {
    public static Object build() {
        Object vHFPort = null;

        try {
            URL[] urls = {new File(Configuration.instance.pathToVHFJavaArchive).toURI().toURL()};
            URLClassLoader urlClassLoader = new URLClassLoader(urls, VHFFactory.class.getClassLoader());
            Class vHFClass = Class.forName("VHF", true, urlClassLoader);
            FlightRecorder.instance.insert("VHFFactory", "vHFClass: " + vHFClass.hashCode());

            Object vHFInstance = vHFClass.getMethod("getInstance").invoke(null);
            FlightRecorder.instance.insert("VHFFactory", "vHFInstance: " + vHFInstance.hashCode());

            vHFPort = vHFClass.getDeclaredField("port").get(vHFInstance);
            FlightRecorder.instance.insert("VHFFactory", "vHFPort: " + vHFPort.hashCode());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return vHFPort;
    }
}
