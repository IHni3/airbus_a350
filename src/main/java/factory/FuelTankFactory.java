package factory;

import configuration.Configuration;

public class FuelTankFactory {

    public static Object build() {
        return FactoryUtils.build("FuelTankFactory",
                Configuration.instance.pathToFuelTankJavaArchive,
                "FuelTank");
    }
}

