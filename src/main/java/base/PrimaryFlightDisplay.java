package base;

public enum PrimaryFlightDisplay {
    instance;
    public boolean isWeatherRadarOn;

    //engine_oil_tank
    public int levelEngineOilTank;

    //fuel_tank
    public int amountOfFuelInTank;

    //pitot_tube
    public boolean isPitotTubeCleaned;
    public int velocity;

    //radar_altimeter
    public boolean isRadarAltimeterOn;
    public int altitudeRadarAltimeter;
}