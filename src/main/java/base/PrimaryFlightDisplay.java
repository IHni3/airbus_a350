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
}