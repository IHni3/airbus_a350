package factory;

import configuration.Configuration;

public class EngineOilTankFactory {

    public static Object build() {
        return FactoryUtils.build("EngineOilTankFactory",
                Configuration.instance.pathToEngineOilTankJavaArchive,
                "EngineOilTank");
    }
}

