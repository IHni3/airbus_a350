package factory;

import configuration.Configuration;

public class FuelSensorFactory {

    public static Object build() {
        return FactoryUtils.build("FuelSensorFactory",
                Configuration.instance.pathToFuelSensorJavaArchive,
                "FuelSensor");
    }
}

