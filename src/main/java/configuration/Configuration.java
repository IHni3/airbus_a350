
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
    public String pathToWeatherRadarJavaArchive = generatePathToJavaArchive("weather_radar");
    public int numberOfWeatherRadar = 2;

    // slat
    public String pathToSlatJavaArchive = generatePathToJavaArchive("slat");
    public int numberOfSlats = 6;

    //cost_optimizer
    public String pathToCostOptimizerJavaArchive = generatePathToJavaArchive("cost_optimizer");
    public int numberOfCostOptimizers = 2;

    //route_management
    public String pathToRouteManagementJavaArchive = generatePathToJavaArchive("route_management");
    public int numberOfRouteManagements = 2;

    //anti_collision_light
    public String pathToAntiCollisionLightJavaArchive = generatePathToJavaArchive("anti_collision_light");
    public int numberOfAntiCollisionLights = 2;

    //cargo_compartment_light
    public String pathToCargoCompartmentLightJavaArchive = generatePathToJavaArchive("cargo_compartment_light");
    public int numberOfCargoCompartmentLights = 4;

    //landing_light
    public String pathToLandingLightJavaArchive = generatePathToJavaArchive("landing_light");
    public int numberOfLandingLightsBody = 2;
    public int numberOfLandingLightsWing = 2;

    public String generatePathToJavaArchive(String archiveName){
        return commonPathToJavaArchive + archiveName + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + archiveName + ".jar";
    }
}