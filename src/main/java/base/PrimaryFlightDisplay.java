package base;

import logging.LogEngine;
import recorder.FlightRecorder;

public enum PrimaryFlightDisplay {
    instance;
    public boolean isWeatherRadarOn;
    private int slagDegree;
    private boolean antiCollisionLightEnabled;

    public int getSlagDegree() {
        return slagDegree;
    }

    public void setSlatDegree(int slagDegree) {
        this.slagDegree = slagDegree;
        LogEngine.instance.write("PrimaryFlightDisplay (slagDegree): " + slagDegree);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "slagDegree: " + slagDegree);
    }

    public void setAntiCollisionLightEnabled(boolean enabled) {
        antiCollisionLightEnabled = enabled;
        LogEngine.instance.write("PrimaryFlightDisplay (antiCollisionLightEnabled): " + enabled);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "antiCollisionLightEnabled: " + enabled);
    }
}