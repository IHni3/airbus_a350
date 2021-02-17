package factory;

import configuration.Configuration;

public class BusinessClassSeatFactory {

    public static Object build() {
        return FactoryUtils.build("BusinessClassSeatFactory",
                Configuration.instance.pathToBusinessClassSeatJavaArchive,
                "BusinessClassSeat");
    }
}

