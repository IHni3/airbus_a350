
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

    // right_navigation_light
    public String pathToRightNavigationLight = generatePathToJavaArchive("light" + fileSeparator + "right_navigation_light");
    public int numberOfRightNavigationLight = 1;

    // tail_navigation_light
    public String pathToTailNavigationLight = generatePathToJavaArchive("light" + fileSeparator + "tail_navigation_light");
    public int numberOfTailNavigationLight = 2;

    // taxi_light
    public String pathToTaxiLight = generatePathToJavaArchive("light" + fileSeparator + "taxi_light");
    public int numberOfTaxiLight = 2;

    // business_class_seat
    public String pathToBusinessClassSeat = generatePathToJavaArchive("seat" + fileSeparator + "business_class_seat");
    public int numberOfBusinessClassSeat = 36;

    // crew_seat
    public String pathToCrewSeat = generatePathToJavaArchive("seat" + fileSeparator + "crew_seat");
    public int numberOfCrewSeat = 8;

    // economy_class_seat
    public String pathToEconomyClassSeat = generatePathToJavaArchive("seat" + fileSeparator + "economy_class_seat");
    public int numberOfEconomyClassSeat = 262;

    // exhaust_gas_temperature_sensor
    public String pathToExhaustGasTemperatureSensor = generatePathToJavaArchive("sensor_01" + fileSeparator + "exhaust_gas_temperature_sensor");
    public int numberOfExhaustGasTemperatureSensor = 2;

    // fuel_flow_sensor
    public String pathToFuelFlowSensor = generatePathToJavaArchive("sensor_01" + fileSeparator + "fuel_flow_sensor");
    public int numberOfFuelFlowSensor = 4;

    // fuel_sensor
    public String pathToFuelSensor = generatePathToJavaArchive("sensor_01" + fileSeparator + "fuel_sensor");
    public int numberOfFuelSensor = 4;

    // ice_detector_probe
    public String pathToIceDetectorProbe = generatePathToJavaArchive("sensor_01" + fileSeparator + "ice_detector_probe");
    public int numberOfIceDetectorProbeBody = 2;
    public int numberOfIceDetectorProbeWing = 2;

    // fire_detector_probe
    public String pathToFireDetectorProbe = generatePathToJavaArchive("sensor_02" + fileSeparator + "fire_detector_probe");
    public int numberOfFireDetectorProbeBody = 14;
    public int numberOfFireDetectorProbeWing = 4;

    // oxygen_sensor
    public String pathToOxygenSensor = generatePathToJavaArchive("sensor_02" + fileSeparator + "oxygen_sensor");
    public int numberOfOxygenSensor = 4;

    public String generatePathToJavaArchive(String archiveName){
        return commonPathToJavaArchive + archiveName + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + archiveName + ".jar";
    }
}
