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

        // weather_radar
        assertTrue(PrimaryFlightDisplay.instance.isWeatherRadarOn);
    }

    @Test
    @Order(2)
    public void taxiTest() {
        cockpit.taxi();

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