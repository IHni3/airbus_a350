package factory;

import configuration.Configuration;

public class FuelFlowSensorFactory {

    public static Object build() {
        return FactoryUtils.build("FuelFlowSensorFactory",
                Configuration.instance.pathToFuelFlowSensorJavaArchive,
                "FuelFlowSensor");
    }
}

