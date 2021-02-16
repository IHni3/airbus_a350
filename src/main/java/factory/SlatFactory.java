package factory;

import configuration.Configuration;

public class SlatFactory {

    public static Object build() {
        return FactoryUtils.build("SlatFactory",
                Configuration.instance.pathToSlatJavaArchive,
                "Slat");
    }
}