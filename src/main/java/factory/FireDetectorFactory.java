package factory;

import configuration.Configuration;

public class FireDetectorFactory {

    public static Object build() {
        return FactoryUtils.build("FireDetectorFactory",
                Configuration.instance.pathToFireDetectorProbeJavaArchive,
                "FireDetector");
    }
}

