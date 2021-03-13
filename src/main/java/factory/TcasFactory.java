package factory;

import configuration.Configuration;
import recorder.FlightRecorder;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class TcasFactory {
    public static Object build() {
        Object tcasPort = null;

        try {
            URL[] urls = {new File(Configuration.instance.pathToTcasJavaArchive).toURI().toURL()};
            URLClassLoader urlClassLoader = new URLClassLoader(urls, TcasFactory.class.getClassLoader());
            Class tcasClass = Class.forName("TCAS", true, urlClassLoader);
            FlightRecorder.instance.insert("TcasFactory", "tcasClass: " + tcasClass.hashCode());

            Object tcasInstance = tcasClass.getMethod("getInstance").invoke(null);
            FlightRecorder.instance.insert("TcasFactory", "tcasInstance: " + tcasInstance.hashCode());

            tcasPort = tcasClass.getDeclaredField("port").get(tcasInstance);
            FlightRecorder.instance.insert("TcasFactory", "tcasPort: " + tcasPort.hashCode());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return tcasPort;
    }
}
