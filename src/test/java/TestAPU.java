import base.PrimaryFlightDisplay;
import factory.APUFactory;
import logging.LogEngine;
import org.junit.jupiter.api.*;
import recorder.FlightRecorder;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestAPU {
    private Object componentPort;

    @BeforeEach
    public void init() {
        LogEngine.instance.init();
        FlightRecorder.instance.startup();
        FlightRecorder.instance.init();
    }

    @Test
    @Order(1)
    public void factory() {
        componentPort = APUFactory.build();
        assertNotNull(componentPort);
    }

    @Test
    @Order(2)
    public void methods() {
        componentPort = APUFactory.build();
        try {
            Method startMethod = componentPort.getClass().getDeclaredMethod("start");
            assertNotNull(startMethod);

            Method shutdownMethod = componentPort.getClass().getDeclaredMethod("shutdown");
            assertNotNull(shutdownMethod);

            Method increaseRPMMethod = componentPort.getClass().getDeclaredMethod("increaseRPM", Integer.class);
            assertNotNull(increaseRPMMethod);

            Method decreaseRPMMethod = componentPort.getClass().getDeclaredMethod("decreaseRPM", Integer.class);
            assertNotNull(decreaseRPMMethod);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(3)
    public void start() {
        componentPort = APUFactory.build();
        try {
            Method onMethod = componentPort.getClass().getDeclaredMethod("start");
            boolean isStarted = (boolean) onMethod.invoke(componentPort);
            assertTrue(isStarted);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(4)
    public void increaseRPM() {
        componentPort = APUFactory.build();
        try {
            Method rpmMethod = componentPort.getClass().getDeclaredMethod("increaseRPM", Integer.class);
            int result = (int) rpmMethod.invoke(componentPort, 10);
            assertEquals(result, 10);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(5)
    public void decreaseRPM() {
        componentPort = APUFactory.build();
        try {
            Method rpmMethod = componentPort.getClass().getDeclaredMethod("scan", String.class);
            int result = (int) rpmMethod.invoke(componentPort, 10);
            assertEquals(result, -10);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(6)
    public void off() {
        componentPort = APUFactory.build();
        try {
            Method shutdownMethod = componentPort.getClass().getDeclaredMethod("shutdown");
            boolean isStarted = (boolean) shutdownMethod.invoke(componentPort);
            assertFalse(isStarted);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    @AfterEach
    public void close() {
        FlightRecorder.instance.shutdown();
        LogEngine.instance.close();
    }
}