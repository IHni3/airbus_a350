package factory;

import configuration.Configuration;
import recorder.FlightRecorder;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class DroopNoseFactory {
    public static Object build() {
        Object droopNosePort = null;

        try {
            URL[] urls = {new File(Configuration.instance.pathToDroopNoseJavaArchive).toURI().toURL()};
            URLClassLoader urlClassLoader = new URLClassLoader(urls, DroopNoseFactory.class.getClassLoader());
            Class droopNoseClass = Class.forName("DroopNose", true, urlClassLoader);
            FlightRecorder.instance.insert("DroopNoseFactory", "droopNoseClass: " + droopNoseClass.hashCode());

            Object droopNoseInstance = droopNoseClass.getMethod("getInstance").invoke(null);
            FlightRecorder.instance.insert("DroopNoseFactory", "droopNoseInstance: " + droopNoseInstance.hashCode());

            droopNosePort = droopNoseClass.getDeclaredField("port").get(droopNoseInstance);
            FlightRecorder.instance.insert("DroopNoseFactory", "droopNosePort: " + droopNosePort.hashCode());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return droopNosePort;
    }
}
