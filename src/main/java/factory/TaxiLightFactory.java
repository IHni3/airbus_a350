package factory;

import configuration.Configuration;

public class TaxiLightFactory {

    public static Object build() {
        return FactoryUtils.build("TaxiLightFactory",
                Configuration.instance.pathToTaxiLightJavaArchive,
                "TaxiLight");
    }
}

