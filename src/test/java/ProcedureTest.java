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
    int amountOfFuelInTank;
    int exhaustGasTemperature;
    int fuelFlow;
    int amountOfNitrogen;
    int amountOfOxygen;
    int altitudeRadarAltimeter;
    int levelEngineOilTank;

    @BeforeEach
    public void init() {


        System.out.println("Ich werde ausgeführt");
        LogEngine.instance.init();
        FlightRecorder.instance.startup();
        FlightRecorder.instance.init();
        airplane = new Airplane();
        airplane.build();
        cockpit = new Cockpit(airplane);
        amountOfFuelInTank = PrimaryFlightDisplay.instance.amountOfFuelInTank;
        levelEngineOilTank = PrimaryFlightDisplay.instance.levelEngineOilTank;
        exhaustGasTemperature = PrimaryFlightDisplay.instance.temperatureExhaustGasTemperatureSensor;
        fuelFlow = PrimaryFlightDisplay.instance.fuelFlow;
        amountOfOxygen = PrimaryFlightDisplay.instance.amountOfOxygen;
        amountOfNitrogen = PrimaryFlightDisplay.instance.amountOfNitrogen;
        altitudeRadarAltimeter = PrimaryFlightDisplay.instance.altitudeRadarAltimeter;
        System.out.println(amountOfFuelInTank);
        System.out.println(amountOfNitrogen);
        System.out.println(amountOfOxygen);
        System.out.println(altitudeRadarAltimeter);
        System.out.println(levelEngineOilTank);
        System.out.println(fuelFlow);
        System.out.println(exhaustGasTemperature);

    }

    @Test
    @Order(1)
    public void startUpTest() {
        cockpit.startup();
        //seat
        assertTrue(PrimaryFlightDisplay.instance.isNonSmokingSignOn);
        assertFalse(PrimaryFlightDisplay.instance.isSeatBeltSignOn);

        //camera
        assertTrue(PrimaryFlightDisplay.instance.isCameraOn);

        //engine_oil_tank
        assertTrue(PrimaryFlightDisplay.instance.levelEngineOilTank>levelEngineOilTank);

        //exhaust_gas_temperature_sensor
        assertTrue(PrimaryFlightDisplay.instance.temperatureExhaustGasTemperatureSensor>-1000);

        //fuel_flow_sensor
        assertTrue(PrimaryFlightDisplay.instance.fuelFlow>0);

        //fuel_tank
        assertTrue(PrimaryFlightDisplay.instance.amountOfFuelInTank>amountOfFuelInTank);

        //gps
        assertTrue(PrimaryFlightDisplay.instance.isGPSOn);
        assertFalse(PrimaryFlightDisplay.instance.isGPSConnected);

        //ice_detector_probe
        assertTrue(PrimaryFlightDisplay.instance.isIceDetectorProbeBodyActivated);
        assertTrue(PrimaryFlightDisplay.instance.isIceDetectorProbeWingActivated);

        //nitrogen_bottle
        assertTrue(PrimaryFlightDisplay.instance.amountOfNitrogen==250);

        //oxygen_bottle
        assertTrue(PrimaryFlightDisplay.instance.amountOfOxygen==100);

        //pitot_tube
        assertTrue(PrimaryFlightDisplay.instance.isPitotTubeCleaned);

        //radar_altimeter
        assertTrue(PrimaryFlightDisplay.instance.isRadarAltimeterOn);
        assertTrue(PrimaryFlightDisplay.instance.altitudeRadarAltimeter==0);

        //right_navigation_light
        assertFalse(PrimaryFlightDisplay.instance.isRightNavigationLightOn);

        //tail_navigation_light
        assertFalse(PrimaryFlightDisplay.instance.isTailNavigationLightOn);

        //taxi_light
        assertFalse(PrimaryFlightDisplay.instance.isTaxiLightOn);

        //tcas
        assertTrue(PrimaryFlightDisplay.instance.isTCASOn);
        assertFalse(PrimaryFlightDisplay.instance.isTCASConnected);

        // turbulent_air_flow_sensor
        assertFalse(PrimaryFlightDisplay.instance.isTurbulentAirFlowAlarm);

        // weather_radar
        assertTrue(PrimaryFlightDisplay.instance.isWeatherRadarOn);
    }

    @Test
    @Order(2)
    public void taxiTest() {
        cockpit.taxi();

        //seat
        assertTrue(PrimaryFlightDisplay.instance.isNonSmokingSignOn);
        assertTrue(PrimaryFlightDisplay.instance.isSeatBeltSignOn);

        //camera

        //engine_oil_tank

        //exhaust_gas_temperature_sensor
        assertTrue(PrimaryFlightDisplay.instance.temperatureExhaustGasTemperatureSensor>-1000);

        //fuel_flow_sensor
        assertTrue(PrimaryFlightDisplay.instance.fuelFlow>0);

        //fuel_tank
        assertTrue(PrimaryFlightDisplay.instance.amountOfFuelInTank==999);

        //gps

        //ice_detector_probe
        assertTrue(PrimaryFlightDisplay.instance.isIceDetectorProbeWingActivated);
        assertTrue(PrimaryFlightDisplay.instance.isIceDetectorProbeBodyActivated);

        //nitrogen_bottle
        assertTrue(PrimaryFlightDisplay.instance.amountOfNitrogen==245);

        //oxygen_bottle

        //pitot_tube

        //radar_altimeter
        assertTrue(PrimaryFlightDisplay.instance.isRadarAltimeterOn);
        assertTrue(PrimaryFlightDisplay.instance.altitudeRadarAltimeter==0);

        //right_navigation_light
        assertTrue(PrimaryFlightDisplay.instance.isRightNavigationLightOn);

        //tail_navigation_light
        assertFalse(PrimaryFlightDisplay.instance.isTailNavigationLightOn);

        //taxi_light
        assertTrue(PrimaryFlightDisplay.instance.isTaxiLightOn);

        //tcas

        // turbulent_air_flow_sensor

        // weather_radar
        assertTrue(PrimaryFlightDisplay.instance.isWeatherRadarOn);


    }

    @Test
    @Order(3)
    public void takeOffTest() {
        cockpit.takeoff();
        //seat
        assertTrue(PrimaryFlightDisplay.instance.isNonSmokingSignOn);
        assertTrue(PrimaryFlightDisplay.instance.isSeatBeltSignOn);

        //camera

        //engine_oil_tank
        assertTrue(PrimaryFlightDisplay.instance.levelEngineOilTank<levelEngineOilTank);

        //exhaust_gas_temperature_sensor
        assertTrue(PrimaryFlightDisplay.instance.temperatureExhaustGasTemperatureSensor>-1000);
        assertFalse(PrimaryFlightDisplay.instance.isAlarmCriticalExhaustGasTemperatureSensor);
        assertFalse(PrimaryFlightDisplay.instance.isAlarmMajorExhaustGasTemperatureSensor);

        //fuel_flow_sensor
        assertTrue(PrimaryFlightDisplay.instance.fuelFlow>0);

        //fuel_tank
        assertTrue(PrimaryFlightDisplay.instance.amountOfFuelInTank<amountOfFuelInTank);

        //gps

        //ice_detector_probe
        assertTrue(PrimaryFlightDisplay.instance.isIceDetectorProbeBodyActivated);
        assertTrue(PrimaryFlightDisplay.instance.isIceDetectorProbeWingActivated);

        //nitrogen_bottle
        assertTrue(PrimaryFlightDisplay.instance.amountOfNitrogen<amountOfNitrogen);

        //oxygen_bottle
        assertTrue(PrimaryFlightDisplay.instance.amountOfOxygen<amountOfOxygen);

        //pitot_tube

        //radar_altimeter
        assertTrue(PrimaryFlightDisplay.instance.isRadarAltimeterOn);
        assertTrue(PrimaryFlightDisplay.instance.altitudeRadarAltimeter>0);

        //right_navigation_light
        assertFalse(PrimaryFlightDisplay.instance.isRightNavigationLightOn);

        //tail_navigation_light
        assertTrue(PrimaryFlightDisplay.instance.isTailNavigationLightOn);

        //taxi_light
        assertFalse(PrimaryFlightDisplay.instance.isTaxiLightOn);

        //tcas

        // turbulent_air_flow_sensor
        assertFalse(PrimaryFlightDisplay.instance.isTurbulentAirFlowAlarm);

        // weather_radar
        assertTrue(PrimaryFlightDisplay.instance.isWeatherRadarOn);


    }

    @Test
    @Order(4)
    public void climbingTest() {
        cockpit.climbing();
        //seat
        assertTrue(PrimaryFlightDisplay.instance.isNonSmokingSignOn);
        assertFalse(PrimaryFlightDisplay.instance.isSeatBeltSignOn);

        //engine_oil_tank
        assertTrue(PrimaryFlightDisplay.instance.levelEngineOilTank<1010);

        //exhaust_gas_temperature_sensor
        assertTrue(PrimaryFlightDisplay.instance.temperatureExhaustGasTemperatureSensor>-1000);

        //fuel_flow_sensor
        assertTrue(PrimaryFlightDisplay.instance.fuelFlow>0);

        //fuel_tank
        assertTrue(PrimaryFlightDisplay.instance.amountOfFuelInTank==996);


        //gps

        //ice_detector_probe
        assertTrue(PrimaryFlightDisplay.instance.isIceDetectorProbeBodyActivated);
        assertTrue(PrimaryFlightDisplay.instance.isIceDetectorProbeWingActivated);

        //nitrogen_bottle
        assertTrue(PrimaryFlightDisplay.instance.amountOfNitrogen==240);

        //oxygen_bottle
        assertTrue(PrimaryFlightDisplay.instance.amountOfOxygen==98);

        //pitot_tube

        //radar_altimeter
        assertTrue(PrimaryFlightDisplay.instance.isRadarAltimeterOn);
        assertTrue(PrimaryFlightDisplay.instance.altitudeRadarAltimeter>0);

        //right_navigation_light
        assertFalse(PrimaryFlightDisplay.instance.isRightNavigationLightOn);

        //tail_navigation_light
        assertTrue(PrimaryFlightDisplay.instance.isTailNavigationLightOn);

        //taxi_light
        assertFalse(PrimaryFlightDisplay.instance.isTaxiLightOn);

        //tcas
        assertTrue(PrimaryFlightDisplay.instance.altitudeTCAS>0);

        // turbulent_air_flow_sensor
        assertFalse(PrimaryFlightDisplay.instance.isTurbulentAirFlowAlarm);

        // weather_radar
        assertTrue(PrimaryFlightDisplay.instance.isWeatherRadarOn);
    }

    @Test
    @Order(5)
    public void rightTurnTest() {
        cockpit.rightTurn();
        //seat
        assertTrue(PrimaryFlightDisplay.instance.isNonSmokingSignOn);
        assertFalse(PrimaryFlightDisplay.instance.isSeatBeltSignOn);

        //exhaust_gas_temperature_sensor
        assertTrue(PrimaryFlightDisplay.instance.temperatureExhaustGasTemperatureSensor>-1000);

        //fuel flow sensor
        assertTrue(PrimaryFlightDisplay.instance.fuelFlow>0);

        //fuel_tank
        assertTrue(PrimaryFlightDisplay.instance.amountOfFuelInTank==998);

        // ice_detector_probe
        assertTrue(PrimaryFlightDisplay.instance.isIceDetectorProbeBodyActivated);
        assertTrue(PrimaryFlightDisplay.instance.isIceDetectorProbeWingActivated);

        // nitrogen_bottle
        assertTrue(PrimaryFlightDisplay.instance.amountOfNitrogen==240);

        //oxygen_bottle
        assertTrue(PrimaryFlightDisplay.instance.amountOfOxygen==98);

        //pitot_tube

        //radar_altimeter
        assertTrue(PrimaryFlightDisplay.instance.isRadarAltimeterOn);
        assertTrue(PrimaryFlightDisplay.instance.altitudeRadarAltimeter>0);

        //right_navigation_light
        assertTrue(PrimaryFlightDisplay.instance.isRightNavigationLightOn);

        //tail_navigation_light
        assertTrue(PrimaryFlightDisplay.instance.isTailNavigationLightOn);

        //taxi_light
        assertFalse(PrimaryFlightDisplay.instance.isTaxiLightOn);

        // tcas
        assertTrue(PrimaryFlightDisplay.instance.altitudeTCAS==1000);

        // turbulent_air_flow_sensor
        assertFalse(PrimaryFlightDisplay.instance.isTurbulentAirFlowAlarm);

        // weather_radar
        assertTrue(PrimaryFlightDisplay.instance.isWeatherRadarOn);
    }

    @Test
    @Order(6)
    public void leftTurnTest() {
        cockpit.leftTurn();

        //seat
        assertTrue(PrimaryFlightDisplay.instance.isNonSmokingSignOn);
        assertFalse(PrimaryFlightDisplay.instance.isSeatBeltSignOn);

        //engine_oil_tank

        //exhaust_gas_temperature_sensor
        assertTrue(PrimaryFlightDisplay.instance.temperatureExhaustGasTemperatureSensor>-1000);

        //fuel flow sensor
        assertTrue(PrimaryFlightDisplay.instance.fuelFlow>0);

        //fuel_tank
        assertTrue(PrimaryFlightDisplay.instance.amountOfFuelInTank==998);

        //gps

        // ice_detector_probe
        assertTrue(PrimaryFlightDisplay.instance.isIceDetectorProbeBodyActivated);
        assertTrue(PrimaryFlightDisplay.instance.isIceDetectorProbeWingActivated);

        // nitrogen_bottle
        assertTrue(PrimaryFlightDisplay.instance.amountOfNitrogen==240);

        //oxygen_bottle
        assertTrue(PrimaryFlightDisplay.instance.amountOfOxygen==98);

        //pitot_tube

        //radar_altimeter
        assertTrue(PrimaryFlightDisplay.instance.isRadarAltimeterOn);
        assertTrue(PrimaryFlightDisplay.instance.altitudeRadarAltimeter>0);

        //right_navigation_light
        assertTrue(PrimaryFlightDisplay.instance.isRightNavigationLightOn);

        //tail_navigation_light
        assertTrue(PrimaryFlightDisplay.instance.isTailNavigationLightOn);

        //taxi_light
        assertFalse(PrimaryFlightDisplay.instance.isTaxiLightOn);

        // tcas
        assertTrue(PrimaryFlightDisplay.instance.altitudeTCAS==1000);

        // turbulent_air_flow_sensor
        assertFalse(PrimaryFlightDisplay.instance.isTurbulentAirFlowAlarm);

        // weather_radar
        assertTrue(PrimaryFlightDisplay.instance.isWeatherRadarOn);
    }

    @Test
    @Order(7)
    public void descentTest() {
        cockpit.descent();

        //seat
        assertTrue(PrimaryFlightDisplay.instance.isNonSmokingSignOn);
        assertTrue(PrimaryFlightDisplay.instance.isSeatBeltSignOn); //economy class sind die SeatBealtsOn

        //engine_oil_tank

        //exhaust_gas_temperature_sensor
        assertTrue(PrimaryFlightDisplay.instance.temperatureExhaustGasTemperatureSensor>-1000);

        //fuel flow sensor
        assertTrue(PrimaryFlightDisplay.instance.fuelFlow>0);

        //fuel_tank
        assertTrue(PrimaryFlightDisplay.instance.amountOfFuelInTank==999);

        //gps

        // ice_detector_probe
        assertTrue(PrimaryFlightDisplay.instance.isIceDetectorProbeBodyActivated);
        assertTrue(PrimaryFlightDisplay.instance.isIceDetectorProbeWingActivated);

        // nitrogen_bottle
        assertTrue(PrimaryFlightDisplay.instance.amountOfNitrogen==240);

        //oxygen_bottle
        assertTrue(PrimaryFlightDisplay.instance.amountOfOxygen==98);

        //pitot_tube

        //radar_altimeter
        assertTrue(PrimaryFlightDisplay.instance.isRadarAltimeterOn);
        assertTrue(PrimaryFlightDisplay.instance.altitudeRadarAltimeter>0);

        //right_navigation_light
        assertTrue(PrimaryFlightDisplay.instance.isRightNavigationLightOn);

        //tail_navigation_light
        assertTrue(PrimaryFlightDisplay.instance.isTailNavigationLightOn);

        //taxi_light
        assertFalse(PrimaryFlightDisplay.instance.isTaxiLightOn);

        // tcas
        assertTrue(PrimaryFlightDisplay.instance.altitudeTCAS==400);

        // turbulent_air_flow_sensor
        assertFalse(PrimaryFlightDisplay.instance.isTurbulentAirFlowAlarm);

        // weather_radar
        assertTrue(PrimaryFlightDisplay.instance.isWeatherRadarOn);
    }

    @Test
    @Order(8)
    public void landingTest() {
        cockpit.landing();

        //seat
        assertTrue(PrimaryFlightDisplay.instance.isNonSmokingSignOn);
        assertTrue(PrimaryFlightDisplay.instance.isSeatBeltSignOn); //economy class sind die SeatBealtsOn

        //engine_oil_tank

        //exhaust_gas_temperature_sensor
        assertTrue(PrimaryFlightDisplay.instance.temperatureExhaustGasTemperatureSensor>-1000);

        //fuel flow sensor
        assertTrue(PrimaryFlightDisplay.instance.fuelFlow>0);

        //fuel_tank
        assertTrue(PrimaryFlightDisplay.instance.amountOfFuelInTank==999);

        //gps

        // ice_detector_probe
        assertTrue(PrimaryFlightDisplay.instance.isIceDetectorProbeBodyActivated);
        assertTrue(PrimaryFlightDisplay.instance.isIceDetectorProbeWingActivated);

        // nitrogen_bottle
        assertTrue(PrimaryFlightDisplay.instance.amountOfNitrogen==240);

        //oxygen_bottle
        assertTrue(PrimaryFlightDisplay.instance.amountOfOxygen==98);

        //pitot_tube

        //radar_altimeter
        assertTrue(PrimaryFlightDisplay.instance.isRadarAltimeterOn);
        assertTrue(PrimaryFlightDisplay.instance.altitudeRadarAltimeter>0);

        //right_navigation_light
        assertTrue(PrimaryFlightDisplay.instance.isRightNavigationLightOn);

        //tail_navigation_light
        assertTrue(PrimaryFlightDisplay.instance.isTailNavigationLightOn);

        //taxi_light
        assertFalse(PrimaryFlightDisplay.instance.isTaxiLightOn);

        // tcas


        // turbulent_air_flow_sensor
        assertFalse(PrimaryFlightDisplay.instance.isTurbulentAirFlowAlarm);

        // weather_radar
        assertTrue(PrimaryFlightDisplay.instance.isWeatherRadarOn);
    }

    @Test
    @Order(9)
    public void shutdownTest() {
        cockpit.shutdown();

        //seat
        assertFalse(PrimaryFlightDisplay.instance.isNonSmokingSignOn);
        assertFalse(PrimaryFlightDisplay.instance.isSeatBeltSignOn); //economy class sind die SeatBealtsOn

        //camera
        assertFalse(PrimaryFlightDisplay.instance.isCameraOn);

        //engine_oil_tank
        assertTrue(PrimaryFlightDisplay.instance.levelEngineOilTank==1010);

        //exhaust_gas_temperature_sensor
        assertTrue(PrimaryFlightDisplay.instance.temperatureExhaustGasTemperatureSensor>-1000);

        //fuel flow sensor
        assertTrue(PrimaryFlightDisplay.instance.fuelFlow>0);

        //fuel_tank
        assertTrue(PrimaryFlightDisplay.instance.amountOfFuelInTank==1000);

        //gps
        assertFalse(PrimaryFlightDisplay.instance.isGPSOn);

        // ice_detector_probe
        assertFalse(PrimaryFlightDisplay.instance.isIceDetectorProbeBodyActivated);
        assertFalse(PrimaryFlightDisplay.instance.isIceDetectorProbeWingActivated);

        // nitrogen_bottle
        assertTrue(PrimaryFlightDisplay.instance.amountOfNitrogen==250);

        //oxygen_bottle
        assertTrue(PrimaryFlightDisplay.instance.amountOfOxygen==100);

        //pitot_tube
        assertTrue(PrimaryFlightDisplay.instance.isPitotTubeCleaned);

        //radar_altimeter
        assertFalse(PrimaryFlightDisplay.instance.isRadarAltimeterOn);
        assertTrue(PrimaryFlightDisplay.instance.altitudeRadarAltimeter==0);

        //right_navigation_light
        assertFalse(PrimaryFlightDisplay.instance.isRightNavigationLightOn);

        //tail_navigation_light
        assertFalse(PrimaryFlightDisplay.instance.isTailNavigationLightOn);

        //taxi_light
        assertFalse(PrimaryFlightDisplay.instance.isTaxiLightOn);

        // tcas
        assertFalse(PrimaryFlightDisplay.instance.isTCASOn);

        // turbulent_air_flow_sensor


        // weather_radar
        assertFalse(PrimaryFlightDisplay.instance.isWeatherRadarOn);
    }
}