package factory;

import configuration.Configuration;

public class GearFactory {

    public static Object build() {
        return FactoryUtils.build("GearFactory",
                Configuration.instance.pathToGearJavaArchive,
                "Gear");
    }
}

