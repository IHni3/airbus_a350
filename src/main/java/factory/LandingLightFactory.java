package factory;

import configuration.Configuration;

public class LandingLightFactory {

    public static Object build() {
        return FactoryUtils.build("LandingLightFactory",
                Configuration.instance.pathToLandingLightJavaArchive,
                "LandingLight");
    }
}