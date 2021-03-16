package factory;

import configuration.Configuration;

public class EconomyClassSeatFactory {

    public static Object build() {
        return FactoryUtils.build("EconomyClassSeatFactory",
                Configuration.instance.pathToEconomyClassSeatJavaArchive,
                "EconomyClassSeat");
    }
}

