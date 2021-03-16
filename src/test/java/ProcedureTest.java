import base.Airplane;
import base.Cockpit;
import base.PrimaryFlightDisplay;
import logging.LogEngine;
import org.junit.jupiter.api.*;
import recorder.FlightRecorder;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProcedureTest {
    private Airplane airplane;
    private Cockpit cockpit;

    @BeforeEach
    public void init() {
        LogEngine.instance.init();
        FlightRecorder.instance.startup();
        FlightRecorder.instance.init();

        airplane = new Airplane();
        airplane.build();

        cockpit = new Cockpit(airplane);
    }

    @Test
    @Order(1)
    public void startUpTest() {
        cockpit.startup();
        // business_class_seat
        assertTrue(PrimaryFlightDisplay.instance.isNonSmokingSignOn);
        assertFalse(PrimaryFlightDisplay.instance.isSeatBeltSignOn);

        // camera
        assertTrue(PrimaryFlightDisplay.instance.isCameraOn);

        // exhaust_gas_temperaure_sensor
        assertTrue(PrimaryFlightDisplay.instance.levelEngineOilTank>0);

        //fuel_flow_sensor
        assertTrue(PrimaryFlightDisplay.instance.temperatureExhaustGasTemperatureSensor>0);

        assertTrue(PrimaryFlightDisplay.instance.fuelFlow>0);
        assertTrue(PrimaryFlightDisplay.instance.amountOfFuelInTank>0);
        assertTrue(PrimaryFlightDisplay.instance.isGPSOn);
        //assertTrue(PrimaryFlightDisplay.instance.isGPSConnected);
        assertTrue(PrimaryFlightDisplay.instance.isIceDetectorProbeBodyActivated);
        assertTrue(PrimaryFlightDisplay.instance.isIceDetectorProbeWingActivated);
        assertTrue(PrimaryFlightDisplay.instance.amountOfNitrogen>0);
        assertTrue(PrimaryFlightDisplay.instance.amountOfOxygen>0);
        assertTrue(PrimaryFlightDisplay.instance.isPitotTubeCleaned);

        assertTrue(PrimaryFlightDisplay.instance.isRadarAltimeterOn);
        //assertTrue(PrimaryFlightDisplay.instance.altitudeRadarAltimeter>0);

        //assertFalse(PrimaryFlightDisplay.instance.isRightNavigationLightOn);
        assertFalse(PrimaryFlightDisplay.instance.isTailNavigationLightOn);
        assertFalse(PrimaryFlightDisplay.instance.isTaxiLightOn);
        assertTrue(PrimaryFlightDisplay.instance.isTCASOn);
        //assertTrue(PrimaryFlightDisplay.instance.isTCASConnected);

        // weather_radar
        assertTrue(PrimaryFlightDisplay.instance.isWeatherRadarOn);
    }

    @Test
    @Order(2)
    public void taxiTest() {
        cockpit.taxi();

        assertTrue(PrimaryFlightDisplay.instance.isNonSmokingSignOn);
        assertTrue(PrimaryFlightDisplay.instance.isSeatBeltSignOn);
        assertFalse(PrimaryFlightDisplay.instance.temperatureExhaustGasTemperatureSensor>0);
        assertTrue(PrimaryFlightDisplay.instance.fuelFlow>0);
        //assertTrue(PrimaryFlightDisplay.instance.amountOfFuelInTank>0);
        assertTrue(PrimaryFlightDisplay.instance.isIceDetectorProbeWingActivated);
        assertTrue(PrimaryFlightDisplay.instance.isIceDetectorProbeBodyActivated);
        assertTrue(PrimaryFlightDisplay.instance.amountOfNitrogen>0);
        assertTrue(PrimaryFlightDisplay.instance.isRadarAltimeterOn);
        assertFalse(PrimaryFlightDisplay.instance.altitudeRadarAltimeter>0);
        assertTrue(PrimaryFlightDisplay.instance.isRightNavigationLightOn);
        assertFalse(PrimaryFlightDisplay.instance.isTailNavigationLightOn);
        assertTrue(PrimaryFlightDisplay.instance.isTaxiLightOn);

        // weather_radar
        assertTrue(PrimaryFlightDisplay.instance.isWeatherRadarOn);
    }

    @Test
    @Order(3)
    public void takeOffTest() {
        cockpit.takeoff();

        // weather_radar
        assertTrue(PrimaryFlightDisplay.instance.isWeatherRadarOn);
    }

    @Test
    @Order(4)
    public void climbingTest() {
        cockpit.climbing();

        // weather_radar
        assertTrue(PrimaryFlightDisplay.instance.isWeatherRadarOn);
    }

    @Test
    @Order(5)
    public void rightTurnTest() {
        cockpit.rightTurn();

        // weather_radar
        assertTrue(PrimaryFlightDisplay.instance.isWeatherRadarOn);
    }

    @Test
    @Order(6)
    public void leftTurnTest() {
        cockpit.leftTurn();

        // weather_radar
        assertTrue(PrimaryFlightDisplay.instance.isWeatherRadarOn);
    }

    @Test
    @Order(7)
    public void descentTest() {
        cockpit.descent();

        // weather_radar
        assertTrue(PrimaryFlightDisplay.instance.isWeatherRadarOn);
    }

    @Test
    @Order(8)
    public void landingTest() {
        cockpit.landing();

        // weather_radar
        assertTrue(PrimaryFlightDisplay.instance.isWeatherRadarOn);
    }

    @Test
    @Order(9)
    public void shutdownTest() {
        cockpit.shutdown();

        // weather_radar
        assertFalse(PrimaryFlightDisplay.instance.isWeatherRadarOn);
    }
}