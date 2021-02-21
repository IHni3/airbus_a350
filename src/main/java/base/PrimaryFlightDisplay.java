package base;

import logging.LogEngine;
import recorder.FlightRecorder;

public enum PrimaryFlightDisplay {
    instance;
    public boolean isWeatherRadarOn;

    private int degreeRudder;

    private int degreeSpoiler;

    private int degreeLeftAileron;

    private int degreeRightAileron;

    private int slatDegree;

    private boolean antiCollisionLightEnabled;

    private boolean cargoCompartmentLightEnabled;

    private boolean landingLightBodyEnabled;
    private boolean landingLightWingEnabled;

    private int indexRouteManagement;
    private int numberOfCheckPointsRouteManagement;
    private boolean routeManagementEnabled;

    private boolean costOptimizerEnabled;
    private int numberOfCheckPointsCostOptimizer;
    private int indexCostOptimizer;


    public int getSlagDegree() {
        return slatDegree;
    }

    public boolean isAntiCollisionLightEnabled() {
        return antiCollisionLightEnabled;
    }

    public boolean isCargoCompartmentLightEnabled() {
        return cargoCompartmentLightEnabled;
    }

    public boolean isLandingLightBodyEnabled() {
        return landingLightBodyEnabled;
    }

    public boolean isLandingLightWingEnabled() {
        return landingLightWingEnabled;
    }

    public int getIndexRouteManagement() {
        return indexRouteManagement;
    }

    public int getSizeRouteManagement() {
        return numberOfCheckPointsRouteManagement;
    }

    public boolean isRouteManagementEnabled() {
        return routeManagementEnabled;
    }

    public boolean isCostOptimizerEnabled() {
        return costOptimizerEnabled;
    }

    public int getSizeCostOptimizer() {
        return numberOfCheckPointsCostOptimizer;
    }

    public int getIndexCostOptimizer() {
        return indexCostOptimizer;
    }

    public int getDegreeRudder() {return degreeRudder;}

    public int getDegreeSpoiler() {return degreeSpoiler;}

    public int getDegreeLeftAileron() {return degreeLeftAileron;}

    public int getDegreeRightAileron() {return degreeRightAileron;}

    public void setSlatDegree(int slagDegree) {
        this.slatDegree = slagDegree;
        LogEngine.instance.write("PrimaryFlightDisplay (slatDegree): " + slagDegree);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "slatDegree: " + slagDegree);
    }

    public void setDegreeRudder(int rudderDegree) {
        this.degreeRudder = rudderDegree;
        LogEngine.instance.write("PrimaryFlightDisplay (degreeRudder): " + degreeRudder);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "degreeRudder: " + degreeRudder);
    }

    public void setDegreeSpoiler(int spoilerDegree) {
        this.degreeSpoiler = spoilerDegree;
        LogEngine.instance.write("PrimaryFlightDisplay (degreeSpoiler): " + degreeSpoiler);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "degreeSpoiler: " + degreeSpoiler);
    }

    public void setDegreeLeftAileron(int degreeLeftAileron) {
        this.degreeLeftAileron = degreeLeftAileron;
        LogEngine.instance.write("PrimaryFlightDisplay (degreeeLeftAileron): " + degreeLeftAileron);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "degreeLeftAileron: " + degreeLeftAileron);
    }

    public void setDegreeRightAileron(int degreeRightAileron) {
        this.degreeRightAileron = degreeRightAileron;
        LogEngine.instance.write("PrimaryFlightDisplay (degreeeRightAileron): " + degreeRightAileron);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "degreeRightAileron: " + degreeRightAileron);
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
        routeManagementEnabled = isOn;

        LogEngine.instance.write("PrimaryFlightDisplay (isCostOptimizerOn): " + isOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isCostOptimizerOn: " + isOn);
    }

    public void setIndexCostOptimizer(int index) {
        indexCostOptimizer = index;

        LogEngine.instance.write("PrimaryFlightDisplay (indexCostOptimizer): " + index);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "indexCostOptimizer: " + index);
    }
}