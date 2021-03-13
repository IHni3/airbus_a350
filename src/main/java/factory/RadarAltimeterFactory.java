package factory;

import configuration.Configuration;

public class RadarAltimeterFactory {

    public static Object build() {
        return FactoryUtils.build("RadarAltimeterFactory",
                Configuration.instance.pathToRadarAltimeterJavaArchive,
                "RadarAltimeter");
    }
}

