package factory;

import configuration.Configuration;

public class Rudder {

    public static Object build() {
        return FactoryUtils.build("RudderFactory",
                Configuration.instance.pathToRudderJavaArchive,
                "Rudder");
    }
}
