package factory;

import configuration.Configuration;

public class KitchenFactory {

    public static Object build() {
        return FactoryUtils.build("KitchenFactory",
                Configuration.instance.pathToKitchenJavaArchive,
                "Kitchen");
    }
}

