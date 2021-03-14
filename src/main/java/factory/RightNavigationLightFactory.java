package factory;

import configuration.Configuration;

public class RightNavigationLightFactory {

    public static Object build() {
        return FactoryUtils.build("RightNavigationLightFactory",
                Configuration.instance.pathToRightNavigationLightJavaArchive,
                "RightNavigationLight");
    }
}

