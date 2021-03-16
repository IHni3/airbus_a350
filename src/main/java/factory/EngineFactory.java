package factory;

import configuration.Configuration;

public class EngineFactory {

    public static Object build() {
        return FactoryUtils.build("EngineFactory",
                Configuration.instance.pathToEngineJavaArchive,
                "Engine");
    }
}

