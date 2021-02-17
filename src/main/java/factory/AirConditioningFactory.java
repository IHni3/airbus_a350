package factory;

import configuration.Configuration;

public class AirConditioningFactory {

    public static Object build() {
        return FactoryUtils.build("AirConditioningFactory",
                Configuration.instance.pathToAirConditioningJavaArchive,
                "AirConditioning");
    }
}

