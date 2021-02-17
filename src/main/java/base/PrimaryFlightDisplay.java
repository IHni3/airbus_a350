package base;

import logging.LogEngine;
import recorder.FlightRecorder;

public enum PrimaryFlightDisplay {
    instance;
    public boolean isWeatherRadarOn;
    private int slagDegree;
    private boolean antiCollisionLightEnabled;
    private boolean cargoCompartmentLightEnabled;
    private boolean landingLightBodyEnabled;
    private boolean landingLightWingEnabled;
    private int indexRouteManagement;
    private boolean costOptimizerEnabled;
    private int numberOfCheckPointsRouteManagement;
    private int numberOfCheckPointsCostOptimizer;
    private boolean isCostOptimizerOn;
    private int indexCostOptimizer;

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

    public void setCargoCompartmentLightEnabled(boolean enabled) {
        cargoCompartmentLightEnabled = enabled;
        LogEngine.instance.write("PrimaryFlightDisplay (cargoCompartmentLightEnabled): " + enabled);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "cargoCompartmentLightEnabled: " + enabled);
    }

    public void setLandingLightWingEnabled(boolean enabled) {
        landingLightWingEnabled = enabled;
        LogEngine.instance.write("PrimaryFlightDisplay (landingLightWingEnabled): " + enabled);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "landingLightWingEnabled: " + enabled);
    }
    public void setLandingLightBodyEnabled(boolean enabled) {
        landingLightBodyEnabled = enabled;
        LogEngine.instance.write("PrimaryFlightDisplay (landingLightBodyEnabled): " + enabled);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "landingLightBodyEnabled: " + enabled);
    }

    public void setCostOptimizerEnabled(boolean isOn) {
        costOptimizerEnabled = isOn;

        LogEngine.instance.write("PrimaryFlightDisplay (costOptimizerEnabled): " + isOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "costOptimizerEnabled: " + isOn);
    }

    public void setNumberOfCostOptimizerCheckpoints(int size) {
        numberOfCheckPointsCostOptimizer  = size;

        LogEngine.instance.write("PrimaryFlightDisplay (numberOfCheckPointsCostOptimizer): " + size);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "numberOfCheckPointsCostOptimizer: " + size);
    }

    public void setIndexRouteManagement(int index) {
        indexRouteManagement = index;

        LogEngine.instance.write("PrimaryFlightDisplay (indexRouteManagement): " + index);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "indexRouteManagement: " + index);
    }

    public void setNumberOfCheckPointsRouteManagement(int size) {
        numberOfCheckPointsRouteManagement = size;

        LogEngine.instance.write("PrimaryFlightDisplay (indexRouteManagement): " + size);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "indexRouteManagement: " + size);
    }

    public void setRouteManagementEnabled(boolean isOn) {
        isCostOptimizerOn = isOn;

        LogEngine.instance.write("PrimaryFlightDisplay (isCostOptimizerOn): " + isOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isCostOptimizerOn: " + isOn);
    }

    public void setIndexCostOptimizer(int index) {
        indexCostOptimizer = index;

        LogEngine.instance.write("PrimaryFlightDisplay (indexCostOptimizer): " + index);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "indexCostOptimizer: " + index);
    }
}