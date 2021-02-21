package factory;

import configuration.Configuration;
public class LeftAileronFactory {
    public static Object build() {
        return FactoryUtils.build("LeftAileronFactory",
                Configuration.instance.pathToLeftAileronJavaArchive,
                "LeftAileron");
    }
}
