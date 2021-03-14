package factory;

import configuration.Configuration;

public class ExhaustGasTemperatureSensorFactory {

    public static Object build() {
        return FactoryUtils.build("ExhaustGasTemperatureSensorFactory",
                Configuration.instance.pathToExhaustGasTemperatureSensorJavaArchive,
                "ExhaustGasTemperatureSensor");
    }
}

