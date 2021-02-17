package factory;

import configuration.Configuration;

public class HydraulicPumpFactory {

    public static Object build() {
        return FactoryUtils.build("HydraulicPumpFactory",
                Configuration.instance.pathToHydraulicPumpJavaArchive,
                "HydraulicPump");
    }
}

