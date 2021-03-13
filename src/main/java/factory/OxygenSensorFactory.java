package factory;

import configuration.Configuration;

public class OxygenSensorFactory {

    public static Object build() {
        return FactoryUtils.build("OxygenSensorFactory",
                Configuration.instance.pathToOxygenSensorJavaArchive,
                "OxygenSensor");
    }
}

