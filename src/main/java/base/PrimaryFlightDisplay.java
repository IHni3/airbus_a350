package base;

public enum PrimaryFlightDisplay {
    instance;
    public boolean isWeatherRadarOn;
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