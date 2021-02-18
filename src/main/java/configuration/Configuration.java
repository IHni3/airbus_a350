
package configuration;

public enum Configuration {
    instance;

    public String userDirectory = System.getProperty("user.dir");
    public String fileSeparator = System.getProperty("file.separator");
    public String commonPathToJavaArchive = userDirectory + fileSeparator + "components" + fileSeparator;

    public String lineSeparator = System.getProperty("line.separator");
    public String logFileDirectory = userDirectory + fileSeparator + "log" + fileSeparator;
    public String logFile = logFileDirectory + "airbus_a350.log";
    public String dataDirectory = userDirectory + fileSeparator + "data" + fileSeparator;
    public String databaseFile = dataDirectory + "flight_recorder_a350.db";

    // weather_radar
    public String pathToWeatherRadarJavaArchive = generatePathToJavaArchive("", "weather_radar");
    public int numberOfWeatherRadar = 2;

    // slat
    public String pathToSlatJavaArchive = generatePathToJavaArchive("flight_controls_01", "slat");
    public int numberOfSlats = 6;

    //cost_optimizer
    public String pathToCostOptimizerJavaArchive = generatePathToJavaArchive("management", "cost_optimizer");
    public int numberOfCostOptimizers = 2;

    //route_management
    public String pathToRouteManagementJavaArchive = generatePathToJavaArchive("management", "route_management");
    public int numberOfRouteManagements = 2;

    //anti_collision_light
    public String pathToAntiCollisionLightJavaArchive = generatePathToJavaArchive("light", "anti_collision_light");
    public int numberOfAntiCollisionLights = 2;

    //cargo_compartment_light
    public String pathToCargoCompartmentLightJavaArchive = generatePathToJavaArchive("light", "cargo_compartment_light");
    public int numberOfCargoCompartmentLights = 4;

    //landing_light
    public String pathToLandingLightJavaArchive = generatePathToJavaArchive("light", "landing_light");
    public int numberOfLandingLightsBody = 2;
    public int numberOfLandingLightsWing = 2;

    public String pathToLeftAileronJavaArchive = generatePathToJavaArchive("flight_controls_02", "left_aileron");
    public int numberOfLeftAileron = 3;

    public String pathToRightAileronJavaArchive = generatePathToJavaArchive("flight_controls_02", "right_aileron");
    public int numberOfRightAileron = 3;

    public String pathToRudderJavaArchive = generatePathToJavaArchive("flight_controls_02", "rudder");
    public int numberOfRudder = 2;

    public String pathToSpoilerJavaArchive = generatePathToJavaArchive("flight_controls_02", "spoiler");
    public int numberOfSpoiler = 8;

    public String generatePathToJavaArchive(String packageName, String archiveName){
        return commonPathToJavaArchive + packageName + fileSeparator + archiveName + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + archiveName + ".jar";
    }
}