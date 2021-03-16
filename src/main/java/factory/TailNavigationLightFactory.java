package factory;

import configuration.Configuration;

public class TailNavigationLightFactory {

    public static Object build() {
        return FactoryUtils.build("TailNavigationLightFactory",
                Configuration.instance.pathToTailNavigationLightJavaArchive,
                "TailNavigationLight");
    }
}

