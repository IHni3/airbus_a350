package base;

public enum PrimaryFlightDisplay {
    instance;
    public boolean isWeatherRadarOn;
    public boolean isAPUStarted;
    public boolean isEngineStarted;
    public int rpmEngine;
    public int rpmAPU;
}