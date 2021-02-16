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
    public String pathToWeatherRadarJavaArchive = commonPathToJavaArchive + "weather_radar" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "weather_radar.jar";
    public int numberOfWeatherRadar = 2;

    // potable_watertank
    public String pathToPortableWatertankJavaArchive = commonPathToJavaArchive + "potable_watertank" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "potable_watertank.jar";
    public int numberOfPotableWaterTank  = 8;

    //wastewater_tank

    //radar
    public String pathToRadarJavaArchive = commonPathToJavaArchive + "radar" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "radar.jar";
    public int numberOfRadar = 2;

    //satcom

    //vhf

    //droop_noose
}