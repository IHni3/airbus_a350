package factory;

import configuration.Configuration;
public class RightAileronFactory {
    public static Object build() {
        return FactoryUtils.build("RightAileronFactory",
                Configuration.instance.pathToRightAileronJavaArchive,
                "RightAileron");
    }
}
