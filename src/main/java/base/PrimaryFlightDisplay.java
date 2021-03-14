package base;

public enum PrimaryFlightDisplay {
    instance;
    public boolean isWeatherRadarOn;

    // camera
    public boolean isCameraOn;

    //engine_oil_tank
    public int levelEngineOilTank;

    // exhaust_gas_temperature_sensor
    public int temperatureExhaustGasTemperatureSensor;
    public boolean isAlarmMajorExhaustGasTemperatureSensor;
    public boolean isAlarmCriticalExhaustGasTemperatureSensor;

    // fire_detector
    public boolean isFireDetectedBody;
    public boolean isFireDetectedWing;
    public boolean isOxgenSensorAlarm;

    // fuel_flow_sensor
    public int fuelFlow;

    // fuel_sensor
    public double amountOfFuel;
    public boolean isAlarmReserveFuelSensor;
    public boolean isAlarmMajorFuelSensor;
    public boolean isAlarmCriticalFuelSensor;

    //fuel_tank
    public int amountOfFuelInTank;

    // gps
    public boolean isGPSOn;
    public boolean isGPSConnected;

    // ice_detector_probe
    public boolean isIceDetectorProbeBodyActivated;
    public boolean isIceDetectorProbeWingActivated;
    public boolean isIceDetected;

    // nitrogen_bottle
    public int amountOfNitrogen;

    // oxygen_bottle
    public int amountOfOxygen;

    //pitot_tube
    public boolean isPitotTubeCleaned;
    public int velocity;

    //radar_altimeter
    public boolean isRadarAltimeterOn;
    public int altitudeRadarAltimeter;

    // right_navigation_light
    public boolean isRightNavigationLightOn;

    // seats
    public boolean isNonSmokingSignOn;
    public boolean isSeatBeltSignOn;
    public int levelSeat;

    // tail_navigation_light
    public boolean isTailNavigationLightOn;

    // taxi_light
    public boolean isTaxiLightOn;

    // tcas
    public boolean isTCASOn;
    public boolean isTCASConnected;
    public boolean isTCASAlarm;
    public int altitudeTCAS;

    // turbulent_air_flow_sensor
    public boolean isTurbulentAirFlowAlarm;
}