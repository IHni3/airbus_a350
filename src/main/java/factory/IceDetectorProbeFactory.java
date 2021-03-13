package factory;

import configuration.Configuration;

public class IceDetectorProbeFactory {

    public static Object build() {
        return FactoryUtils.build("IceDetectorProbeFactory",
                Configuration.instance.pathToIceDetectorProbeJavaArchive,
                "IceDetectorProbe");
    }
}

