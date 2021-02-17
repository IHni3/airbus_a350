package factory;

import configuration.Configuration;

public class CargoCompartmentLightFactory {

    public static Object build() {
        return FactoryUtils.build("CargoCompartmentLightFactory",
                Configuration.instance.pathToCargoCompartmentLightJavaArchive,
                "CargoCompartmentLight");
    }
}