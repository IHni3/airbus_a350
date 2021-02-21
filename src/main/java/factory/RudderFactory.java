package factory;

import configuration.Configuration;

public class RudderFactory {

    public static Object build() {
        return FactoryUtils.build("RudderFactory",
                Configuration.instance.pathToRudderJavaArchive,
                "Rudder");
    }
}
