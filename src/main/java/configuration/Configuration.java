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

    // camera
    public String pathToCameraJavaArchive = commonPathToJavaArchive + "camera" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "camera.jar";
    public int numberOfCameraBody = 2;
    public int numberOfCameraWing = 1;

    // gps
    public String pathToGpsJavaArchive = commonPathToJavaArchive + "gps" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "gps.jar";
    public int numberOfGps = 2;

    // nitrogen_bottle
    public String pathToNitrogenBottleJavaArchive = commonPathToJavaArchive + "nitrogen_bottle" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "nitrogen_bottle.jar";
    public int numberOfNitrogen = 6;

    // oxygen_bottle
    public String pathToOxygenBottleJavaArchive = commonPathToJavaArchive + "oxygen_bottle" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "oxygen_bottle.jar";
    public int numberOfOxygen = 10;

    // tcas
    public String pathToTcasJavaArchive = commonPathToJavaArchive + "tcas" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "tcas.jar";
    public int numberOfTCAS = 2;

    // turbulent_air_flow_sensor
    public String pathToTurbulentAirFlowSensorJavaArchive = commonPathToJavaArchive + "turbulent_air_flow_sensor" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "turbulent_air_flow_sensor.jar";
    public int numberOfTurbulentAirFlowSensorBody = 2;
    public int numberOfTurbulentAirFlowSensorWing = 4;

    // weather_radar
    public String pathToWeatherRadarJavaArchive = generatePathToJavaArchive("weather_radar");
    public int numberOfWeatherRadar = 2;

    // slat
    public String pathToSlatJavaArchive = generatePathToJavaArchive("slat");
    public int numberOfSlats = 6;

    // right_navigation_light
    public String pathToRightNavigationLightJavaArchive = generatePathToJavaArchive("light", "right_navigation_light");
    public int numberOfRightNavigationLight = 1;

    // tail_navigation_light
    public String pathToTailNavigationLightJavaArchive = generatePathToJavaArchive("light", "tail_navigation_light");
    public int numberOfTailNavigationLight = 2;

    // taxi_light
    public String pathToTaxiLightJavaArchive = generatePathToJavaArchive("light", "taxi_light");
    public int numberOfTaxiLight = 2;

    // business_class_seat
    public String pathToBusinessClassSeatJavaArchive = generatePathToJavaArchive("seat", "business_class_seat");
    public int numberOfBusinessClassSeat = 36;

    // crew_seat
    public String pathToCrewSeatJavaArchive = generatePathToJavaArchive("seat", "crew_seat");
    public int numberOfCrewSeat = 8;

    // economy_class_seat
    public String pathToEconomyClassSeatJavaArchive = generatePathToJavaArchive("seat", "economy_class_seat");
    public int numberOfEconomyClassSeat = 262;

    // exhaust_gas_temperature_sensor
    public String pathToExhaustGasTemperatureSensorJavaArchive = generatePathToJavaArchive("sensor_01", "exhaust_gas_temperature_sensor");
    public int numberOfExhaustGasTemperatureSensor = 2;

    // fuel_flow_sensor
    public String pathToFuelFlowSensorJavaArchive = generatePathToJavaArchive("sensor_01", "fuel_flow_sensor");
    public int numberOfFuelFlowSensor = 4;

    // fuel_sensor
    public String pathToFuelSensorJavaArchive = generatePathToJavaArchive("sensor_01", "fuel_sensor");
    public int numberOfFuelSensor = 4;

    // ice_detector_probe
    public String pathToIceDetectorProbeJavaArchive = generatePathToJavaArchive("sensor_01", "ice_detector_probe");
    public int numberOfIceDetectorProbeBody = 2;
    public int numberOfIceDetectorProbeWing = 2;

    // fire_detector_probe
    public String pathToFireDetectorProbeJavaArchive = generatePathToJavaArchive("sensor_02", "fire_detector");
    public int numberOfFireDetectorProbeBody = 14;
    public int numberOfFireDetectorProbeWing = 4;

    // oxygen_sensor
    public String pathToOxygenSensorJavaArchive = generatePathToJavaArchive("sensor_02", "oxygen_sensor");
    public int numberOfOxygenSensor = 4;

    public String generatePathToJavaArchive(String archiveName) {
        return commonPathToJavaArchive + archiveName + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + archiveName + ".jar";
    }

    public String generatePathToJavaArchive(String dir, String archiveName){
        return commonPathToJavaArchive + dir + fileSeparator + archiveName + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + archiveName + ".jar";
    }
}
