package base;

public enum PrimaryFlightDisplay {
    instance;
    public boolean isWeatherRadarOn;

    // camera
    public boolean isCameraOn;

    // gps
    public boolean isGPSOn;
    public boolean isGPSConnected;

    // nitrogen_bottle
    public int amountOfNitrogen;

    // oxygen_bottle
    public int amountOfOxygen;

    // tcas
    public boolean isTCASOn;
    public boolean isTCASConnected;
    public boolean isTCASAlarm;
    public int altitudeTCAS;

    // turbulent_air_flow_sensor
    public boolean isTurbulentAirFlowAlarm;
    public boolean isRightNavigationLightOn;
    public boolean isTailNavigationLightOn;
    public boolean isTaxiLightOn;
    public boolean isNonSmokingSignOn;
    public boolean isSeatBeltSignOn;
    public int levelSeat;
    public int temperatureExhaustGasTemperatureSensor;
    public boolean isAlarmMajorExhaustGasTemperatureSensor;
    public boolean isAlarmCriticalExhaustGasTemperatureSensor;
    public int fuelFlow;
    public double amountOfFuel;
    public boolean isAlarmReserveFuelSensor;
    public boolean isAlarmMajorFuelSensor;
    public boolean isAlarmCriticalFuelSensor;
    public boolean isIceDetectorProbeBodyActivated;
    public boolean isIceDetectorProbeWingActivated;
    public boolean isIceDetected;
    public boolean isFireDetectedBody;
    public boolean isFireDetectedWing;
    public boolean isOxgenSensorAlarm;
}
