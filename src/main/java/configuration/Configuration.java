
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

    // apu
    public String pathToAPUJavaArchive = commonPathToJavaArchive + "apu_engine_gear_pump"+ fileSeparator + "apu" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "apu.jar";
    public int numberOfAPU = 1;

    // engine
    public String pathToEngineJavaArchive = commonPathToJavaArchive + "apu_engine_gear_pump"+ fileSeparator + "engine" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "engine.jar";
    public int numberOfEngine = 1;

    // gear
    public String pathToGearJavaArchive = commonPathToJavaArchive + "apu_engine_gear_pump"+ fileSeparator + "gear" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "gear.jar";
    public int numberOfGear = 3;

     // hydraulic_pump
    public String pathToHydraulicPumpJavaArchive = commonPathToJavaArchive + "apu_engine_gear_pump"+ fileSeparator + "hydraulic_pump" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "hydraulic_pump.jar";
    public int numberOfHydraulicPumpBody = 6;
    public int numberOfHydraulicPumpWing = 4;

    // air_conditioning
    public String pathToAirConditioningJavaArchive = commonPathToJavaArchive + "cabin"+ fileSeparator + "air_conditioning" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "air_conditioning.jar";
    public int numberOfAirConditioning = 4;

    // kitchen
    public String pathToKitchenJavaArchive = commonPathToJavaArchive + "cabin"+ fileSeparator + "kitchen" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "kitchen.jar";
    public int numberOfKitchen = 3;

    // slat
    public String pathToSlatJavaArchive = generatePathToJavaArchive("slat");
    public int numberOfSlats = 6;

    public String generatePathToJavaArchive(String archiveName){
        return commonPathToJavaArchive + archiveName + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + archiveName + ".jar";
    }
}