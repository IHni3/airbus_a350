import factory.RadarAltimeterFactory;
import logging.LogEngine;
import org.junit.jupiter.api.*;
import recorder.FlightRecorder;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestRadarAltimeter {
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
        componentPort = RadarAltimeterFactory.build();
        assertNotNull(componentPort);
    }

    @Test
    @Order(2)
    public void methods() {
        componentPort = RadarAltimeterFactory.build();
        try {
            Method versionMethod = componentPort.getClass().getDeclaredMethod("version");
            assertNotNull(versionMethod);

            Method onMethod = componentPort.getClass().getDeclaredMethod("on");
            assertNotNull(onMethod);

            Method sendMethod = componentPort.getClass().getDeclaredMethod("send", String.class);
            assertNotNull(sendMethod);

            Method recMethod = componentPort.getClass().getDeclaredMethod("receive", String.class);
            assertNotNull(recMethod);

            Method measMethod = componentPort.getClass().getDeclaredMethod("measureAltitude");
            assertNotNull(measMethod);

            Method offMethod = componentPort.getClass().getDeclaredMethod("off");
            assertNotNull(offMethod);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @Test
    @Order(3)
    public void version() {
        componentPort = RadarAltimeterFactory.build();
        try {
            Method versionMethod = componentPort.getClass().getDeclaredMethod("version");
            String version = (String) versionMethod.invoke(componentPort);

            assertEquals(version, "RadarAltimeter // 8843476 / 9668368 - team 16 - 8843476 / 9668368");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @Test
    @Order(4)
    public void on() {
        componentPort = RadarAltimeterFactory.build();
        try {
            Method increaseMethod = componentPort.getClass().getDeclaredMethod("on");
            boolean result = (boolean) increaseMethod.invoke(componentPort);
            assertEquals(result, true);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @Test
    @Order(5)
    public void receive() {
        componentPort = RadarAltimeterFactory.build();
        try {
            Method outMethod = componentPort.getClass().getDeclaredMethod("receive", String.class);
            int result = (int) outMethod.invoke(componentPort, "ping");
            assertEquals(result, -1);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @Test
    @Order(6)
    public void measureAltitude() {
        componentPort = RadarAltimeterFactory.build();
        try {
            Method outMethod = componentPort.getClass().getDeclaredMethod("measureAltitude");
            int result = (int) outMethod.invoke(componentPort);
            assertEquals(result, -1);

        } catch (Exception e) {
            System.out.println(e);
            fail();
        }
    }

    @Test
    @Order(7)
    public void off() {
        componentPort = RadarAltimeterFactory.build();
        try {
            Method increaseMethod = componentPort.getClass().getDeclaredMethod("off");
            boolean result = (boolean) increaseMethod.invoke(componentPort);
            assertEquals(result, false);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @AfterEach
    public void close() {
        FlightRecorder.instance.shutdown();
        LogEngine.instance.close();
    }
}