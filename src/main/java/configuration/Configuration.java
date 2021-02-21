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

    // pitot_tube
    public String pathToPitotTubeJavaArchive = generatePathToJavaArchive("sensor_03", "pitot_tube");
    public int numberOfPitotTube = 2;

    // radar_altimeter
    public String pathToRadarAltimeterJavaArchive = generatePathToJavaArchive("sensor_03", "radar_altimeter");
    public int numberOfRadarAltimeter = 2;

    // engine_oil_tank
    public String pathToEngineOilTankJavaArchive = generatePathToJavaArchive("tank_bottle", "engine_oil_tank");
    public int numberOfEngineOilTank = 4;

    // fuel_tank
    public String pathToFuelTankJavaArchive = generatePathToJavaArchive("tank_bottle", "fuel_tank");
    public int numberOfFuelTank = 3;

    // slat
    public String pathToSlatJavaArchive = generatePathToJavaArchive("slat");
    public int numberOfSlats = 6;

    public String generatePathToJavaArchive(String archiveName) {
        return commonPathToJavaArchive + archiveName + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + archiveName + ".jar";
    }

    public String generatePathToJavaArchive(String dir, String archiveName) {
        return commonPathToJavaArchive + dir + fileSeparator + archiveName + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + archiveName + ".jar";
    }
}