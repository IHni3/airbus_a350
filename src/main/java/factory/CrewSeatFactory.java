package factory;

import configuration.Configuration;

public class CrewSeatFactory {

    public static Object build() {
        return FactoryUtils.build("CrewSeatFactory",
                Configuration.instance.pathToCrewSeatJavaArchive,
                "CrewSeat");
    }
}

