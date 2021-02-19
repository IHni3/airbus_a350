package factory;

import configuration.Configuration;

public class SpoilerFactory {

    public static Object build() {
        return FactoryUtils.build("SpoilerFactory",
                Configuration.instance.pathToSpoilerJavaArchive,
                "Spoiler");
    }

}
