import base.PrimaryFlightDisplay;
import factory.TurbulentAirFlowSensorFactory;
import logging.LogEngine;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import recorder.FlightRecorder;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestTurbulentAirFlowSensor {
    private Object componentPort;

    @BeforeEach
    public void init () {
        LogEngine.instance.init();
        FlightRecorder.instance.startup();
        FlightRecorder.instance.init();
    }

    @Test
    @Order(1)
    public void factory () {
        componentPort = TurbulentAirFlowSensorFactory.build();
        assertNotNull(componentPort);
    }

    @Test
    @Order(2)
    public void methods () {
        componentPort = TurbulentAirFlowSensorFactory.build();
        try {
            Method measureMethod = componentPort.getClass().getDeclaredMethod("measure", String.class);
            assertNotNull(measureMethod);

            Method alarmMethod = componentPort.getClass().getDeclaredMethod("alarm");
            assertNotNull(alarmMethod);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(3)
    public void measure () {
        componentPort = TurbulentAirFlowSensorFactory.build();
        try {
            Method measureMethod = componentPort.getClass().getDeclaredMethod("measure", String.class);
            int turbulences = (int) measureMethod.invoke(componentPort, "windturbulencelaminarturbulencewind");
            assertEquals(2, turbulences);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(4)
    public void alarm () {
        componentPort = TurbulentAirFlowSensorFactory.build();
        try {
            Method alarmMethod = componentPort.getClass().getDeclaredMethod("alarm");
            boolean isAlarm = (boolean) alarmMethod.invoke(componentPort);
            assertTrue(isAlarm);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(5)
    public void takeoffUntilLanding () {
        componentPort = TurbulentAirFlowSensorFactory.build();
        try {
            Method measureMethod = componentPort.getClass().getDeclaredMethod("measure", String.class);
            int amount = (int) measureMethod.invoke(componentPort, "windturbulenceturbulenceturbulenceturbulencelaminarturbulencewind");
            assertEquals(5, amount);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @AfterEach
    public void close () {
        FlightRecorder.instance.shutdown();
        LogEngine.instance.close();
    }
}