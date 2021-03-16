package factory;

import configuration.Configuration;

public class APUFactory {

    public static Object build() {
        return FactoryUtils.build("APUFactory",
                Configuration.instance.pathToAPUJavaArchive,
                "APU");
    }
}

