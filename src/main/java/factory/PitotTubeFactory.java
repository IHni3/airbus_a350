package factory;

import configuration.Configuration;

public class PitotTubeFactory {

    public static Object build() {
        return FactoryUtils.build("PitotTubeFactory",
                Configuration.instance.pathToPitotTubeJavaArchive,
                "PitotTube");
    }
}

