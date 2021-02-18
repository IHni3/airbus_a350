package factory;

import configuration.Configuration;

public class Spoiler {

    public static Object build() {
        return FactoryUtils.build("SpoilerFactory",
                Configuration.instance.pathToSpoilerJavaArchive,
                "Spoiler");
    }

}
