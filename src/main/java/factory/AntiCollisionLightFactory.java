package factory;

import configuration.Configuration;

public class AntiCollisionLightFactory {

    public static Object build() {
        return FactoryUtils.build("AntiCollisionLightFactory",
                Configuration.instance.pathToAntiCollisionLightJavaArchive,
                "AntiCollisionLight");
    }
}