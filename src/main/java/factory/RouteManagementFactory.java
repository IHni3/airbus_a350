package factory;

import configuration.Configuration;

public class RouteManagementFactory {

    public static Object build() {
        return FactoryUtils.build("RouteManagementFactory",
                Configuration.instance.pathToRouteManagementJavaArchive,
                "RouteManagement");
    }
}